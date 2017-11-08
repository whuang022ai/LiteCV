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
public class ImageSingleIntThreshold implements ImageThresholdComparator 
{
    @Override
    public boolean threshold(Object threshold, Object image) 
    {
       int thresholdInt=(int)threshold;
       int imageInt=(int)image;
       if(imageInt>thresholdInt)
       {
           return true;
       }
       else
       {
           return false;
       }
    }
    
}
