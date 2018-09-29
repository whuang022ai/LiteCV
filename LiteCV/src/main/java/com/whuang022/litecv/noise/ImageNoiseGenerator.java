
package com.whuang022.litecv.noise;

import java.util.Random;

/**
 * 
 * @author whuang022
 */
public class ImageNoiseGenerator 
{
    
    public static int[][]generateSaltPepperNoiseNoise(int[][]image,double SNR)
    {
        Random ran = new Random();
        
        int size= (int)(image.length*image[0].length * (1-SNR));
        int [][]imageOut =image.clone();
        for(int i=0; i<size; i++) 
        {
            int row = (int)(Math.random()* (double)image.length);
            int col = (int)(Math.random()* (double)image[0].length);
            double random=ran.nextDouble();
            if(random>0.5)
            {
                imageOut[row][col]=255;
            }
            else
            {
                imageOut[row][col]=0;
            }
       
        }
        return imageOut;
    }
    public static int[][]generateGaussianNoise(int[][] image,double mean,double variance)
    {
        Random random = new Random();
        int    nrows, ncols;
        nrows = image.length;
        ncols = image[0].length;
        int [][]imageOut = new int[nrows][ncols];
        for (int i=0; i<nrows; i++) 
        {
          for (int j=0; j<ncols; j++) 
          {
            int gray = (int)(random.nextGaussian() * variance + mean);
            gray += image[i][j];
            if (gray < 0) gray = 0;
            if (gray > 255) gray = 255;
            imageOut[i][j]=gray;
          }
        }
        return imageOut;
    }
}
