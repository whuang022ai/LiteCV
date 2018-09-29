/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.roi;

import com.whuang022.litecv.colorspace.ImageColorSpaceConverter;
import com.whuang022.litecv.colorspace.ImageGray;
import com.whuang022.litecv.colorspace.ImageRGB;

/**
 *
 * @author user
 */
public class ImageROI 
{
     public static ImageGray cropImageRGB_ImageGray(int startX,int startY,int endX,int endY,ImageRGB src)
    {
        
       int w1 = endX-startX;
       int h1 = endY-startY;
       int [][] mix= new int[h1][w1];
       for (int i =startY,m=0; i < endY; i++,m++)
       {
            for (int j = startX,n=0; j < endX; j++,n++) 
            {
                int R = src.R[i][j];
                int G = src.G[i][j];
                int B = src.B[i][j];
                int mixGray=ImageColorSpaceConverter.RGBtoGray(R, G, B);
                mix[m][n] = mixGray;
            }
       }
        ImageGray gray=new ImageGray();
        gray.G= mix;
        gray.mask=new boolean[h1][w1];
        return gray;
    }
    public static ImageGray cropImageRGB_ImageGrayWidthSquare(int startX,int startY,int endX,int endY,ImageRGB src)
    {
        
       int w1 = endX-startX;
       int h1 = w1;
       int [][] mix= new int[h1][w1];
       for (int i =startY,m=0; m <w1; i++,m++)
       {
            for (int j = startX,n=0; j < endX; j++,n++) 
            {
                
                int R = src.R[i][j];
                int G = src.G[i][j];
                int B = src.B[i][j];
                int mixGray=ImageColorSpaceConverter.RGBtoGray(R, G, B);
                mix[m][n] = mixGray;
            }
       }
        ImageGray gray=new ImageGray();
        gray.G= mix;
        gray.mask=new boolean[h1][w1];
        return gray;
    }
     public static ImageRGB cropImageRGB_ImageRGBWidthSquare(int startX,int startY,int endX,int endY,ImageRGB src)
    {
        
       int w1 = endX-startX;
       int h1 = w1;
       int [][] R= new int[h1][w1];
       int [][] G= new int[h1][w1];
       int [][] B= new int[h1][w1];
       for (int i =startY,m=0; m <w1; i++,m++)
       {
            for (int j = startX,n=0; j < endX; j++,n++) 
            {
                
                R [m][n] = src.R[i][j];
                G [m][n]= src.G[i][j];
                B [m][n]= src.B[i][j];
            }
       }
        ImageRGB rgb=new ImageRGB();
        rgb.R=R;
        rgb.G=G;
        rgb.B=B;
        rgb.mask=new boolean[h1][w1];
        return rgb;
    }
      public static ImageRGB cropImageRGB_ImageRGBHidthSquare(int startX,int startY,int endX,int endY,ImageRGB src)
    {
        
       int h1 = endY-startY;
       int w1 = h1;
       int [][] R= new int[h1][w1];
       int [][] G= new int[h1][w1];
       int [][] B= new int[h1][w1];
       for (int i =startY,m=0; m <h1; i++,m++)
       {
            for (int j = startX,n=0; n < w1; j++,n++) 
            {
                
                R [m][n] = src.R[i][j];
                G [m][n]= src.G[i][j];
                B [m][n]= src.B[i][j];
            }
       }
        ImageRGB rgb=new ImageRGB();
        rgb.R=R;
        rgb.G=G;
        rgb.B=B;
        rgb.mask=new boolean[h1][w1];
        return rgb;
    }
}
