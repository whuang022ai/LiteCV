/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.threshold;

/**
 *
 * @author user
 */
public class ImageTwiceDoubleThreshold implements ImageThresholdComparator  {

    @Override
    public boolean threshold(Object threshold, Object image) {
       double []thresholdInt=(double[])threshold;
       
       
        if (image instanceof Integer)
        {
            int imageInt=(int)image;
            return  handle(imageInt ,thresholdInt);
        }
        else if(image instanceof Double)
        {
            double imageInt=(double)image;
            return  handle(imageInt ,thresholdInt);
        }
        else
        {
            return false;
        }
     
    }
    private boolean handle(double imageInt ,double []thresholdInt)
    {
       if(imageInt>=thresholdInt[0]&&imageInt<=thresholdInt[1])
       {
           return true;
       }
       else
       {
           return false;
       }
    }
     private boolean handle(int imageInt ,double []thresholdInt)
    {
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
