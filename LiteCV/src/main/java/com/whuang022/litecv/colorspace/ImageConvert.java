package com.whuang022.litecv.colorspace;

import java.awt.image.BufferedImage;
import java.lang.reflect.Type;

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
            return convertRGB(src, (ImageRGB) dst);
        }
        return null;
    }
    private ImageRGB convertRGB(BufferedImage src,ImageRGB dst)
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
}
