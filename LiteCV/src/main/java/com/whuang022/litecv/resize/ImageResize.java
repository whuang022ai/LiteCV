package com.whuang022.litecv.resize;

import com.whuang022.litecv.math.ImageMatrix;



/**
 * 影像放大縮小
 * 
 * @author user
 */
public class ImageResize 
{
    
    public int[][] nearestInterpolation(int [][] image,double t)//最鄰近插值
    {
       double srcWidth = image[0].length;//水平
       double srcHeight = image.length;//垂直
       double dstWidth=srcWidth*t;
       double dstHeight=srcHeight*t;
       int[][] dest=new int [(int)dstHeight][(int)dstWidth];
       for(int m=0;m<dest.length;m++)
       {
           /*
           
            double srcX= (n+0.5)* (srcWidth/(dstWidth-0.5)) ;
                double srcY =(m+0.5)* (srcHeight/(dstHeight-0.5));
           
           */
           for(int n=0;n<dest[0].length;n++)
           {
                double srcX= (n)* (srcWidth/(dstWidth)) ;
                double srcY =(m)* (srcHeight/(dstHeight));
             
                int i=(int) Math.round(srcX);
                int j=(int) Math.round(srcY);
                double u= srcX-i;
                double v=srcY-j;
                if(i>srcWidth-2||j>srcHeight-2)
                {
                    i=(int)Math.round(srcWidth-2);
                    j=(int)Math.round(srcHeight-2);
                }
                double data=image[j][i];
                dest[m][n]=(int)Math.round(data);
            }
       }
        return dest;
    }
    
    public int[][] bilinearInterpolation(int [][] image,double t)//雙線性插值
    {
       double srcWidth = image[0].length;//水平
       double srcHeight = image.length;//垂直
       double dstWidth=srcWidth*t;
       double dstHeight=srcHeight*t;
       int[][] dest=new int [(int)dstHeight][(int)dstWidth];
       for(int m=0;m<dest.length;m++)
       {
           for(int n=0;n<dest[0].length;n++)
           {
                double srcX= (n)* (srcWidth/(dstWidth)) ;
                double srcY =(m)* (srcHeight/(dstHeight));
                int i=(int) Math.round(srcX);
                int j=(int) Math.round(srcY);
                double u= srcX-i;
                double v=srcY-j;
                if(i>srcWidth-2||j>srcHeight-2)
                {
                    i=(int)Math.round(srcWidth-2);
                    j=(int)Math.round(srcHeight-2);
                }
                double data=(1-u)*(1-v)*image[j][i] + (1-u)*v*image[j+1][i] + u*(1-v)*image[j][i+1] + u*v*image[j+1][i+1];
                dest[m][n]=(int)Math.round(data);
            }
       }
        return dest;
    }
    //////////////
    private double bicubicConvolutionKernel(double x)
    {
        //bi-cubic interpolation 6 parameter compute
        double temp = 0;
        if(x<0)
        {
            x = -x;
        }
        if(x <= 1)
        {
            temp = (((float)(6.0/5.0))* x - (11.0/5.0)) * x * x + 1;
        }
        else if (x < 2) 
        {  
            temp = ((-0.6 * x + (16.0/5.0)) * x - (27.0/5.0)) * x + (14.0/5.0);  
        }
        else if (x < 3) 
        {  
            temp = ((0.2 * x - (8.0/5.0)) * x + (21.0/5.0)) * x - (18.0/5.0);  
        }
        return temp;
    }
    public int[][] biCubicInterpolation(int [][] image,double t)//雙三次插值
    {
       //http://www.voidcn.com/article/p-fjsapawl-tt.html
       double srcWidth = image[0].length;//水平
       double srcHeight = image.length;//垂直
       double dstWidth=srcWidth*t;
       double dstHeight=srcHeight*t;
       int[][] dest=new int [(int)dstHeight][(int)dstWidth];
       for(int m=0;m<dest.length;m++)
       {
           for(int n=0;n<dest[0].length;n++)
           {
               /*
               A(x,y)=A(X*(m/M),Y*(n/N))
               */
                double srcX= (n)* (srcWidth/(dstWidth)) ;
                double srcY =(m)* (srcHeight/(dstHeight));
                int i=(int) Math.round(srcX);
                int j=(int) Math.round(srcY);
                double u= srcX-i;
                double v=srcY-j;
                if(i>srcWidth-3)
                {
                   i=(int)Math.round(srcWidth-3);//i為x
                }
                if(j>srcHeight-3)
                {
                   j=(int)Math.round(srcHeight-3);//j為y
                }
                if(i<=1)
                {
                    i=1;
                }
                if(j<=1)
                {
                    j=1;
                }
                double [][]f=new double [4][4];
                
                f[0][0]=image[j-1][i-1];
                f[0][1]=image[j][i-1];
                f[0][2]=image[j+1][i-1];
                f[0][3]=image[j+2][i-1];
                
                f[1][0]=image[j-1][i];
                f[1][1]=image[j][i];
                f[1][2]=image[j+1][i];
                f[1][3]=image[j+2][i];
                
                f[2][0]=image[j-1][i+1];
                f[2][1]=image[j][i+1];
                f[2][2]=image[j+1][i+1];
                f[2][3]=image[j+2][i+1];
                
                f[3][0]=image[j-1][i+2];
                f[3][1]=image[j][i+2];
                f[3][2]=image[j+1][i+2];
                f[3][3]=image[j+2][i+2];
                
                double[][]uM=new double [4][1];
                
                uM[0][0]=bicubicConvolutionKernel(1+u);
                uM[1][0]=bicubicConvolutionKernel(u);
                uM[2][0]=bicubicConvolutionKernel(1-u);
                uM[3][0]=bicubicConvolutionKernel(2-u);
                
                double[][]vM=new double [1][4];
                
                vM[0][0]=bicubicConvolutionKernel(1+v);
                vM[0][1]=bicubicConvolutionKernel(v);
                vM[0][2]=bicubicConvolutionKernel(1-v);
                vM[0][3]=bicubicConvolutionKernel(2-v);
                
                double[][] tmp=ImageMatrix.product(vM, f);
                double[][] data=ImageMatrix.product(tmp, uM);
                
                dest[m][n]=(int)Math.round(data[0][0]);
            }
       }
        return dest;
    }
}
