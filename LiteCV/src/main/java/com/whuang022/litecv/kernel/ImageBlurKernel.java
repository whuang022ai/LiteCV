package com.whuang022.litecv.kernel;

/**
 * Image Blur Kernel 
 * motion blur ,box blur,gaussian blur
 * @author user
 */
public class ImageBlurKernel 
{
    
    public static final double[][]motionBlurLeft=
    {
        { 0.333333, 0,  0},
        { 0, 0.333333,  0},
        { 0,  0, 0.333333}
    };
    public static final double[][]motionBlurRight=
    {
        { 0, 0,  0.333333},
        { 0, 0.333333,  0},
        { 0.333333,0,  0}
    };
     public static final double[][]motionBlurLeft5x5=
    {
        { 0.2, 0,0,0,0},
        { 0, 0.2,0,0,0},
        { 0, 0,0.2,0,0},
        { 0, 0,0,0.2,0},
        { 0, 0,0,0,0.2}
    };
    public static final double[][]motionBlurRight5x5=
    {
        { 0,0,0,0,0.2},
        { 0,0,0,0.2,0},
        { 0,0,0.2,0,0},
        { 0,0.2,0,0,0},
        { 0.2,0,0,0,0}
    };
    public static final double[][]motionBlurLeft7x7=
    {
        { 0.142857, 0,0,0,0,0,0},
        { 0,0.142857,0,0,0,0,0 },
        { 0, 0,0.142857,0,0,0,0},
        { 0, 0,0,0.142857,0,0,0},
        { 0, 0,0,0,0.142857,0,0},
        { 0, 0,0,0,0,0.142857,0},
        { 0, 0,0,0,0,0,0.142857},
    };
    public static final double[][]motionBlurRight7x7=
    {
        { 0, 0,0,0,0,0,0.142857},
        { 0,0,0,0,0,0.142857,0 },
        { 0, 0,0,0,0.142857,0,0},
        { 0, 0,0,0.142857,0,0,0},
        { 0, 0,0.142857,0,0,0,0},
        { 0, 0.142857,0,0,0,0,0},
        { 0.142857, 0,0,0,0,0,0},
    };
    public static final double[][]boxBlur=
   {
       {0.111111,0.111111,0.111111},//1/9
       {0.111111,0.111111,0.111111},
       {0.111111,0.111111,0.111111}
   };
    public static final double[][]boxBlur5x5=
   {
       {0.04,0.04,0.04,0.04,0.04},
       {0.04,0.04,0.04,0.04,0.04},
       {0.04,0.04,0.04,0.04,0.04},
       {0.04,0.04,0.04,0.04,0.04},
       {0.04,0.04,0.04,0.04,0.04}
   };
   public static final double[][]boxBlur7x7=
   {
       {0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816},
       {0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816},
       {0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816},
       {0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816},
       {0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816},
       {0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816},
       {0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816,0.02040816}
   };

    public static double[][] gaussianBlur( int ksize, double sigma)
    {
        double[][]kernal=new double[ksize][ksize];
        
        int center = ksize / 2; // 模板的中心位置
        double x2, y2;
        double sum = 0;
        for (int i = 0; i < ksize; i++)
        {
            x2 = Math.pow(i - center, 2);//x距離中心距離
            for (int j = 0; j < ksize; j++)
            {
                y2 = Math.pow(j - center, 2);//y距離中心距離
                double g = Math.exp(-(x2 + y2) / (2 * sigma * sigma));
                g /= 2 * Math.PI * sigma;
                sum += g;
                kernal[i][j] = g;
            }
        }
        for (int i = 0; i < ksize; i++)
        {
            for (int j = 0; j < ksize; j++)
            {
                kernal[i][j] /= sum;
            }
        }
        return kernal;
    }
}
