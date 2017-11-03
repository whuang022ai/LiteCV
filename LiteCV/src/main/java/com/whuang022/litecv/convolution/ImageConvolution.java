package com.whuang022.litecv.convolution;

/**
 * Image Convolution Interface
 * @author whuang022
 */
public interface ImageConvolution 
{
    public int[][]convolution(int [][]image,double[][]kernal);
    public int[][]convolution(int [][]image,int[][]kernal);
}
