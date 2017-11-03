package com.whuang022.litecv.fft;

/**
 *
 * @author user
 */
public interface ImageFFT 
{
    public ImageComplex[] fft(ImageComplex[] x) ;
    public  ImageComplex[] ifft(ImageComplex[] x) ;//
}
