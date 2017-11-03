package com.whuang022.litecv.fft;

/**
 * Complex Number Object
 * @author whuang022
 */
public class ImageComplexIm implements ImageComplex
{
    private final double realNum;   //實部
    private final double imageNum;  //虛部
    @Override
    public double getRealNum()  //返回實部 
    { 
        return realNum; 
    } 
    @Override
    public double getImageNum() //返回虛部
    {
        return imageNum; 
    } 
    public ImageComplexIm(double real, double imag) 
    {
        this.realNum = real;
        this.imageNum = imag;
    }
    //  本複數加上另一複數
    @Override
    public ImageComplex plus(ImageComplex b) 
    {
        return new ImageComplexIm(realNum + b.getRealNum(),  imageNum + b.getImageNum());
    }
    // 本複數減掉上另一複數
    @Override
    public ImageComplex sub(ImageComplex b)
    {
        return new ImageComplexIm(realNum - b.getRealNum(), imageNum -  b.getImageNum());
    }
    // 本複數乘上另一複數
    @Override
    public ImageComplex times(ImageComplex b) 
    {
        return new ImageComplexIm(realNum * b.getRealNum() -imageNum *  b.getImageNum(), realNum *  b.getImageNum() + imageNum * b.getRealNum());
    }
    //複數放大k倍
    @Override
    public ImageComplex scale(double k) 
    {
        return new ImageComplexIm(k * realNum, k * imageNum);
    }
    //取共軛複數
    @Override
    public ImageComplex conjugate() 
    {
        return new ImageComplexIm(realNum, -imageNum);
    }
}