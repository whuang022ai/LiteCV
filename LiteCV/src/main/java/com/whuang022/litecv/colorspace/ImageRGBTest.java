package com.whuang022.litecv.colorspace;

import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;


public class ImageRGBTest 
{

    public static void main(String[] args) 
    {
        ImageIO io=new ImageIO();
        BufferedImage src= io.getImage("C:\\Users\\user\\Desktop\\hsv.jpg");
        
        Image image =null;
        ImageColorSpaceReader reader=new ImageColorSpaceReader();
        image=reader.imageRead(src, ImageColorSpaceType.ColorSpaceRGB);
        image.display();
    }
    
}
