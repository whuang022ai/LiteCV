/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.noise;

import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageGray;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;
import com.whuang022.litecv.paint.ImagePainter;

/**
 *
 * @author user
 */
public class ImageNoiseGeneratorTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="test.jpg";
        ImageMatrix image =null;
        ImageColorReader reader=new ImageColorReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceGray);
        ImageGray imgG=(ImageGray)image;
        image.display();
        //int [][]grayImg=ImageNoiseGenerator.generateSaltPepperNoiseNoise(imgG.G, 0.997);
        int [][]grayImg=ImageNoiseGenerator.generateGaussianNoise(imgG.G, 0,10);
        imgG.G=grayImg;
        imgG.display();
    }
    
}
