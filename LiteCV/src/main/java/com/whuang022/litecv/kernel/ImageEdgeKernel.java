package com.whuang022.litecv.kernel;

/**
 * Image Edge Kernel Template
 * sobel,prewitt,roberts,laplacian,laplacian Of Gaussian
 * @author whuang022
 */
public class ImageEdgeKernel 
{
    
    public static final   int[][] identity=
    {
       {0,0,0},
       {0,1,0},
       {0,0,0}
   };
    public static final   int[][] sobelGxR=
    {
       {1,0,-1},
       {2,0,-2},
       {1,0,-1}
   };
    public static final   int[][] sobelGx=
    {
       {-1,0,1},
       {-2,0,2},
       {-1,0,1}
   };
    
    public static final   int[][] sobelGyR=
    {
        {1,2,1},
       {0,0,0},
       {-1,-2,-1}
   };
    public static final   int[][] sobelGy=
    {
       {-1,-2,-1},
       {0,0,0},
       {1,2,1}
   };
   public static final   int[][] prewittGxR=
    {
       {1,0,-1},
       {1,0,-1},
       {1,0,-1}
   };
    public static final   int[][] prewittGx=
    {
       {-1,0,1},
       {-1,0,1},
       {-1,0,1}
   };
    
    public static final   int[][] prewittGyR=
    {
        {1,1,1},
       {0,0,0},
       {-1,-1,-1}
   };
    public static final   int[][] prewittGy=
    {
       {-1,-1,-1},
       {0,0,0},
       {1,1,1}
   };
    public static final   int[][] robertsGxR=
    {
       {-1,0},
       {0,1},
   };
    public static final   int[][] robertsGx=
    {
       {1,0},
       {0,-1},
   };
    public static final   int[][] robertsGyR=
    {
        {0,-1},
       {1,0},
   };
    public static final   int[][]  robertsGy=
    {
       {0,1},
       {-1,0},
   };
    public static final double[][]laplacian1=
    {
        { 0, 1,  0},
        {1,-4,  1},
        { 0, 1,  0}
    };
    public static final double[][]laplacian2=
    {
        { 1, 1,  1},
        {1,-8,  1},
        { 1, 1,  1}
    };
    public static final double[][]laplacian3=
    {
        { 1, 4,  1},
        {4,-20,  4},
        { 1, 4,  1}
    };
    public static final double[][]laplacianOfGaussian=
    {
        { 0,0,-1,0,0},
        {0,-1,-2,-1,0},
        { -1, -2,16, -2,-1},
        {0,-1,-2,-1,0},
        { 0,0,-1,0,0}
    };
}
