package com.whuang022.litecv.kernel;

/**
 * Image Sharpen Kernel Template
 * 3 level high pass kernel
 * @author whuang022
 */
public class ImageSharpenKernel 
{
    public static final int[][]sharpen0=
    {
        {0,-1,0},
        {0,3,0},
        {0,-1,0}
    };
    public static final int[][]sharpen1=
    {
        {0,-1,0},
        {-1,5,-1},
        {0,-1,0}
    };
    public static final int[][]sharpen2=
    {
        {-1,-1,-1},
        {-1,9,-1},
        {-1,-1,-1}
    };
  
}
