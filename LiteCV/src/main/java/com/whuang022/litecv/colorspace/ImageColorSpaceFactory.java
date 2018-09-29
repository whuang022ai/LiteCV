package com.whuang022.litecv.colorspace;

/**
 *
 * @author user
 */
public class ImageColorSpaceFactory 
{
    public static ImageMatrix getImageColorSpaceInstance(ImageColorSpaceType type)
    {
        switch (type) 
        {
            case ColorSpaceRGB:
                return new ImageRGB();
            case ColorSpaceGray:
                return new ImageGray();
            case ColorSpaceHSV:
                return new ImageHSV();
            default:
                break;
        }
        return null;
    }
}
