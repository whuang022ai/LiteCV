package com.whuang022.litecv.fft;

/**
 * Complex Number Interface
 * @author whuang022
 */
public interface ImageComplex 
{
    public double getRealNum();  
    public double getImageNum(); 
    public ImageComplex plus(ImageComplex  b);
    public ImageComplex sub(ImageComplex b);
    public ImageComplex times(ImageComplex b);
    public ImageComplex scale(double k);
    public ImageComplex conjugate();
}
