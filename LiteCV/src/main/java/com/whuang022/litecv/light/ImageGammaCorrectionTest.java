/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.light;

import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;

/**
 *
 * @author user
 */
public class ImageGammaCorrectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         String path="test.jpg";//        String path="C:\\Users\\user\\Desktop\\hsv.jpg";

        ImageMatrix image =null;
        ImageColorReader reader=new ImageColorReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceRGB);
        image.display();
        image=ImageGammaCorrection.gammaCorrection(image, 0.5);
        image.display();
    }
    
}
