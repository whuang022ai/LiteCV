/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.histogram;

import com.whuang022.litecv.colorspace.ImageGray;

/**
 *
 * @author user
 */
public class ImageProcessBin 
{
    public  ImageGray getImageGraytoImageBin(ImageGray gray)
    {
        ImageProcessHistogram hist=new ImageProcessHistogram(gray.G);
        ImageProcessThresholdOtsu otsu=new ImageProcessThresholdOtsu();
        int threshold=otsu.getThreshold(hist);
        int[][]imageOut=new int[ gray.G.length][gray.G[0].length];
        for(int i=0;i< imageOut.length;i++)
        {
            for(int j=0;j<  imageOut[0].length;j++)
            {
                if(gray.G[i][j]>=threshold)
                {
                    imageOut[i][j]=255;
                }
              
                else
                {
                    imageOut[i][j]=0;
                }
            }
        }
        gray.G=imageOut;
        return gray;
    }
}
