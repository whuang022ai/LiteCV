package com.whuang022.litecv.colorspace;

/**
 *
 * @author user
 */
public interface ImageMatrix 
{
    public void display();
    public void save(String path,String type);
    public ImageColorSpaceType getType();
}
