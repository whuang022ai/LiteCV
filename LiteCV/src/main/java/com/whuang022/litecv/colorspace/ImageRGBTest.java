package com.whuang022.litecv.colorspace;


import java.awt.image.BufferedImage;


public class ImageRGBTest 
{

    public static void main(String[] args) 
    {

        String path="test.jpg";//        String path="C:\\Users\\user\\Desktop\\hsv.jpg";

        ImageMatrix image =null;
        ImageColorReader reader=new ImageColorReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceHSV);
        image.display();
    }
    
}
