package com.whuang022.litecv.colorspace;


import static com.whuang022.litecv.colorspace.ImageRGB.logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.awt.image.BufferedImage;
import java.util.Vector;



/**
 *

 * @author whuang022
 * ref:https://www.cs.rit.edu/~ncs/color/t_convert.html
 */
public class ImageColorSpaceReader implements ImageIO
{
    
   public ImageMatrix imageRead(BufferedImage src,ImageColorSpaceType dst)
   {
        String typ=dst.toString();
        return BufferedImagetoImageMatrix(src,typ);
    }
    public ImageMatrix imageRead(String path,ImageColorSpaceType dst)
    {
        String typ=dst.toString();
        if(isURL(path) )
        {
            try 
            {
                URL url=new URL(path);
                BufferedImage src=getBufferedImage(url);
                return BufferedImagetoImageMatrix(src,typ);
            }
            catch (MalformedURLException ex) 
            {
                logger.error(ex.getMessage());
                return null;
            }
        }
        else
        {
            BufferedImage src=getBufferedImage(path);
            return BufferedImagetoImageMatrix(src,typ);
        }
  
    }
    private boolean isURL(String url) 
    {
        try 
        {
            new URL(url);
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }
 



    private ImageMatrix BufferedImagetoGray(BufferedImage src)


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

    private ImageMatrix BufferedImagetoRGB(BufferedImage src)

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

    private ImageMatrix BufferedImagetoHSV(BufferedImage src)

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


    @Override
    public BufferedImage getBufferedImage(String path) 
    {
        try 
        {
            File input = new File(path);
            BufferedImage image = javax.imageio.ImageIO.read(input);
            return image;
        } 
        catch (IOException ie) 
        {
            logger.error(ie.getMessage());
        }
        return null;
    }
    @Override
    public BufferedImage getBufferedImage(URL path) 
    {
        try 
        {
            BufferedImage image = javax.imageio.ImageIO.read(path);
            return image;
        } 
        catch (IOException ie) 
        {
            logger.error(ie.getMessage());
        }
        return null;
    }


    public ImageMatrix BufferedImagetoImageMatrix(BufferedImage src, String dst)
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

    
}
