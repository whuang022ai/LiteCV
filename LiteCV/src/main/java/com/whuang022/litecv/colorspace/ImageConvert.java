package com.whuang022.litecv.colorspace;

import java.awt.image.BufferedImage;


/**
 *
 * @author user
 */
public class ImageConvert 
{
    public Image convert(BufferedImage src,Image dst)
    {
        String typ=dst.getClass().getTypeName();
        if(typ.equals("com.whuang022.litecv.colorspace.ImageRGB"))
        {
            return convertRGB(src);
        }
        else  if(typ.equals("com.whuang022.litecv.colorspace.ImageGray"))
        {
            return convertGray(src);
        }
        else  if(typ.equals("com.whuang022.litecv.colorspace.ImageHSV"))
        {
            return convertHSV(src);
        }
        return null;
    }
    private Image convertGray(BufferedImage src)
    {
       int w1 = src.getWidth();
       int h1 = src.getHeight();
       int [][] mix= new int[h1][w1];
       int valueR =0;
       int valueG =0;
       int valueB=0;
       for (int i = 0; i < h1; i++)
       {
            for (int j = 0; j < w1; j++) 
            {
                valueR = src.getRGB(j, i); 
                valueG = src.getRGB(j, i);
                valueB = src.getRGB(j, i);
                int red = ( valueR >> 16) & 0xff;
                int green = ( valueG >> 8) & 0xff;
                int blue = ( valueB) & 0xff;
                int mixGray=(int) Math.round(red*ImageColorVector.grayR+ green*ImageColorVector.grayG+  blue*ImageColorVector.grayB);
                mix[i][j] = mixGray;
            }
       }
        ImageGray gray=new ImageGray();
        gray.G= mix;
        return gray;
    }
    private Image convertRGB(BufferedImage src)
    {
       int w1 = src.getWidth();
       int h1 = src.getHeight();
       int [][] mixR= new int[h1][w1];
       int [][] mixG= new int[h1][w1];
       int [][] mixB= new int[h1][w1];
       int valueR=0;
       int valueG=0;
       int valueB=0;
       for (int i = 0; i < h1; i++)
       {
            for (int j = 0; j < w1; j++) 
            {
                valueR = src.getRGB(j, i); 
                valueG = src.getRGB(j, i);
                valueB = src.getRGB(j, i);
                int red = ( valueR >> 16) & 0xff;
                int green = ( valueG >> 8) & 0xff;
                int blue = ( valueB) & 0xff;
                mixR[i][j] = red;
                mixG[i][j] = green;
                mixB[i][j] = blue;
            }
       }
       ImageRGB rgb=new ImageRGB();
       rgb.R=mixR;
       rgb.G=mixG;
       rgb.B=mixB;
       return rgb;
    }
    private Image convertHSV(BufferedImage src)
    {
       int w1 = src.getWidth();
       int h1 = src.getHeight();
       double [][] mixH= new double[h1][w1];
       double [][] mixS= new double[h1][w1];
       double [][] mixV= new double[h1][w1];
       int valueR=0;
       int valueG=0;
       int valueB=0;
       for (int i = 0; i < h1; i++)
       {
            for (int j = 0; j < w1; j++) 
            {
                valueR = src.getRGB(j, i); 
                valueG = src.getRGB(j, i);
                valueB = src.getRGB(j, i);
                int R = ( valueR >> 16) & 0xff;
                int G = ( valueG >> 8) & 0xff;
                int B = ( valueB) & 0xff;
                double H=0,S=0,V=0;
                //
                double max = R;
                double min = R;
                if (G > max) {
                    max = G;
                }
                if (B> max) {
                    max = B;
                }

                if (G < min) {
                    min = G;
                }
                if (B < min) {
                    min = B;
                }
                //
                V=max;
                double D=max-min;
                if(R==G&&G==B)
                {
                    H=0;
                }
                else
                {
                    double theta=( (0.5*((R-G)+(R-B)))/Math.sqrt( (R-G)*(R-G)+(R-B)*(G-B)));
                    H=Math.acos(theta);
                }
                if(B>G)
                {
                    H=2*Math.PI-H;
                }
                if(max>0)
                {
                    S=D/max;
                }
                else
                {
                    S=0;
                }
                mixH[i][j] = H;
                mixS[i][j] = S;
                mixV[i][j] = V;
            }
       }
       ImageHSV hsv=new ImageHSV();
       hsv.H=mixH;
       hsv.S=mixS;
       hsv.V=mixV;
       return hsv;
    }
}
