package com.whuang022.litecv.threshold;

/**
 * 
 * @author whuang022
 */
public class ImageThreshold 
{
    public int[][]threshold(int[][] image,Object threshold,ImageThresholdComparator thresholdComparator)
    {
       int[][]  imageOut = new int[ image.length][ image[0].length];
       for(int i=0;i<  image.length;i++)
       {
            for(int j=0;j<  image[0].length;j++)
            {
                boolean overThreshold=thresholdComparator.threshold(threshold, image[i][j]);
                if(!overThreshold)
                {
                    imageOut[i][j]=0;
                }
                else
                {
                    imageOut[i][j]=image[i][j];
                }
               
            }
       }
        return imageOut;
    }
}
