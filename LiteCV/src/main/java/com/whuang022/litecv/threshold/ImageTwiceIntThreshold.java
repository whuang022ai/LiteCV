package com.whuang022.litecv.threshold;

/**
 *
 * @author whuang022
 */
public class ImageTwiceIntThreshold implements ImageThresholdComparator
{
    @Override
    public boolean threshold(Object threshold, Object image) 
    {
       int []thresholdInt=(int[])threshold;
       int imageInt=(int)image;
       if(imageInt>=thresholdInt[0]&&imageInt<=thresholdInt[1])
       {
           return true;
       }
       else
       {
           return false;
       }
    }
}
