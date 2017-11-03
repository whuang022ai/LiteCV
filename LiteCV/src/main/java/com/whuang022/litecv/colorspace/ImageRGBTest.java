/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorspace;

import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageRGBTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ImageIO io=new ImageIO();
        BufferedImage src= io.getImage("test.jpg");
        Image image =new ImageRGB();
        ImageConvert convert=new ImageConvert();
        Image c=convert.convert(src, image);
        c.display();

    }
    
}
