package com.whuang022.litecv.colorspace;

/**
 *
 * @author user
 */
public class ImageSpaceFactory 
{
    public static Image getImageColorSpaceInstance(ImageColorSpaceType type)
    {
        if(type.equals(ImageColorSpaceType.ColorSpaceRGB))
        {
            return new ImageRGB();
        }
        else if(type.equals(ImageColorSpaceType.ColorSpaceGray))
        {
            return new ImageGray();
        }
        return null;
    }
}
