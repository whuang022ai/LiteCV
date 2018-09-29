/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.light;

import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;

/**
 *
 * @author user
 */
public class ImageGammaCorrection 
{
    public static ImageMatrix gammaCorrection(ImageMatrix image,double gamma)
    {
        double gammaI = 1 / gamma;
        int[] gammaTable= getGammaTable(gammaI);
        ImageRGB rgb=(ImageRGB)image;
        for(int i=0;i<rgb.R.length;i++)
        {
            for(int j=0;j<rgb.R[0].length;j++)
            {
                rgb.R[i][j]=gammaTable[rgb.R[i][j]];
                rgb.G[i][j]=gammaTable[rgb.G[i][j]];
                rgb.B[i][j]=gammaTable[rgb.B[i][j]];
            }
        }
        return rgb;
    }
    private  static int[] getGammaTable(double gamma) 
    {
        int[] gammaTable = new int[256];
        for (int i = 0; i < gammaTable.length; i++) 
        {
            gammaTable[i] = (int) (255 * (Math.pow((double) i / (double) 255, gamma)));
        }
        return gammaTable;
     }
}
