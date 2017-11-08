package com.whuang022.litecv.colorspace;

import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;


public class ImageRGBTest 
{

    public static void main(String[] args) 
    {
        String path="C:\\Users\\user\\Desktop\\hsv.jpg";
        ImageMatrix image =null;
        ImageColorSpaceReader reader=new ImageColorSpaceReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceHSV);
        image.display();
    }
    
}
