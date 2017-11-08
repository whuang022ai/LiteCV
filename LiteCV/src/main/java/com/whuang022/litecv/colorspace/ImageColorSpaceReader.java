package com.whuang022.litecv.colorspace;

import java.awt.image.BufferedImage;
import java.util.Vector;


/**
 *
 * @author user
 * ref:https://www.cs.rit.edu/~ncs/color/t_convert.html
 */
public class ImageColorSpaceReader 
{
    public Image imageRead(BufferedImage src,ImageColorSpaceType dst)
    {
        String typ=dst.toString();
        if(typ.equals("ColorSpaceRGB"))
        {
            return BufferedImagetoRGB(src);
        }
        else  if(typ.equals("ColorSpaceGray"))
        {
            return BufferedImagetoGray(src);
        }
        else  if(typ.equals("ColorSpaceHSV"))
        {
            return BufferedImagetoHSV(src);
        }
        return null;
    }
    private Image BufferedImagetoGray(BufferedImage src)
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
                int R = ( valueR >> 16) & 0xff;
                int G = ( valueG >> 8) & 0xff;
                int B = ( valueB) & 0xff;
                int mixGray=ImageColorSpaceConverter.RGBtoGray(R, G, B);
                mix[i][j] = mixGray;
            }
       }
        ImageGray gray=new ImageGray();
        gray.G= mix;
        gray.mask=new boolean[h1][w1];
        return gray;
    }
    private Image BufferedImagetoRGB(BufferedImage src)
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
                int R = ( valueR >> 16) & 0xff;
                int G = ( valueG >> 8) & 0xff;
                int B = ( valueB) & 0xff;
                mixR[i][j] = R;
                mixG[i][j] = G;
                mixB[i][j] = B;
            }
       }
       ImageRGB rgb=new ImageRGB();
       rgb.R=mixR;
       rgb.G=mixG;
       rgb.B=mixB;
       rgb.mask=new boolean[h1][w1];
       return rgb;
    }
    private Image BufferedImagetoHSV(BufferedImage src)
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
                Vector<Double> hsv=ImageColorSpaceConverter.RGBtoHSV( R, G, B);
                mixH[i][j] = hsv.get(0);
                mixS[i][j] = hsv.get(1);
                mixV[i][j] = hsv.get(2);
            }
       }
       ImageHSV hsv=new ImageHSV();
       hsv.H=mixH;
       hsv.S=mixS;
       hsv.V=mixV;
       hsv.mask=new boolean[h1][w1];
       return hsv;
    }
    
    
}
