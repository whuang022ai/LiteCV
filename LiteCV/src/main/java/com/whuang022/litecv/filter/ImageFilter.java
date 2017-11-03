package com.whuang022.litecv.filter;

/**
 * Image Filter API
 * @author whuang022
 */
public interface ImageFilter 
{
    public int[][]filter(int[][]image,ImageFilterConfig config);
    public int[][]filter(int[][]image);
}
