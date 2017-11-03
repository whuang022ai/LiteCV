package com.whuang022.litecv.fft;

/**
 *
 * @author whuang022
 */
public class ImageFFTCooleyTukeyBase2 implements ImageFFT
{
    
   @Override
   public  ImageComplex[] fft(ImageComplex[] x) 
   {
        
        int N = x.length;

        // base case
        if (N == 1) return new ImageComplex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if ( isPowerOfTwo(N)==false ) { throw new RuntimeException("N is not a power of 2"); }

        // fft of even terms
        ImageComplex[] even = new ImageComplex[N/2];
        for (int k = 0; k < N/2; k++) {
            even[k] = x[2*k];
        }
        ImageComplex[] q = fft(even);

        // fft of odd terms
        ImageComplex[] odd  = even;  // reuse the array
        for (int k = 0; k < N/2; k++) {
            odd[k] = x[2*k + 1];
        }
        ImageComplex[] r = fft(odd);

        // combine
        ImageComplex[] y = new ImageComplex[N];
        for (int k = 0; k < N/2; k++) 
        {
            double kth = -2 * k * Math.PI / N;
            ImageComplex wk = new ImageComplexIm(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + N/2] = q[k].sub(wk.times(r[k]));
        }
        return y;
    }
   @Override
    public ImageComplex[] ifft(ImageComplex[] x) 
   {
        int N = x.length;
        ImageComplex[] y = new ImageComplexIm[N];

        // take conjugate
        for (int i = 0; i < N; i++) {
            y[i] = x[i].conjugate();
        }

        // compute forward FFT
        y = fft(y);

        // take conjugate again
        for (int i = 0; i < N; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by N
        for (int i = 0; i < N; i++) {
            y[i] = y[i].scale(1.0 / N);
        }

        return y;

    }
    private  boolean isPowerOfTwo(int x) 
   {
        return x > 0 & (x & (x - 1)) == 0;
   }
}