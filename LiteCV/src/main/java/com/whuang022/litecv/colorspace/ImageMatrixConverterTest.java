/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorspace;

/**
 *
 * @author user
 */
public class ImageMatrixConverterTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="C:\\Users\\user\\Desktop\\hsv.jpg";//
        ImageMatrix image =null;
        ImageColorReader reader=new ImageColorReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceRGB);
        image.display();
        ImageMatrix imageHSV =ImageMatrixConverter.convert(image, ImageColorSpaceType.ColorSpaceHSV);
        imageHSV.display();
    }
    
}
