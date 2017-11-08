package com.whuang022.litecv.threshold;

/**
 *
 * @author whuang022
 */
public  abstract interface ImageThresholdComparator 
{
    public boolean threshold(Object threshold, Object image);
}
