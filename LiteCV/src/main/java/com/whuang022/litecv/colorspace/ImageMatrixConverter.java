/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorspace;

import java.util.Vector;

/**
 *
 * @author user
 */
public class ImageMatrixConverter 
{
    public static ImageMatrix convert(ImageMatrix src,ImageColorSpaceType toType)
    {
        String typ=toType.toString();

        if(typ.equals("ColorSpaceRGB"))
        {
           // return BufferedImagetoRGB(src);
        }
        else  if(typ.equals("ColorSpaceGray"))
        {
         //   return BufferedImagetoGray(src);
        }
        else  if(typ.equals("ColorSpaceHSV"))
        {
            return RGBtoHSV(src);
        }
        return null;
    }
    private static ImageMatrix RGBtoHSV(ImageMatrix in)
    {
        ImageRGB src=(ImageRGB)in;
        ImageHSV dst=new ImageHSV();
        double [][]H=new double[src.R.length][src.R[0].length];
        double [][]S=new double[src.R.length][src.R[0].length];
        double [][]V=new double[src.R.length][src.R[0].length];
        for(int i=0;i<src.R.length;i++)
        {
            for(int j=0;j<src.R[0].length;j++)
            {
                int R=src.R[i][j];
                int G=src.G[i][j];
                int B=src.B[i][j];
                Vector<Double> hsv=ImageColorSpaceConverter.RGBtoHSV(R, G, B);
                H[i][j]=hsv.get(0);
                S[i][j]=hsv.get(1);
                V[i][j]=hsv.get(2);
            }
        }
        dst.H=H;
        dst.S=S;
        dst.V=V;
        dst.mask=src.mask;
        return dst;
    }
}
