/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.example;

import com.whuang022.litecv.filter.ImageFilter;
import com.whuang022.litecv.filter.ImageFilterConfig;
import com.whuang022.litecv.filter.ImageGaussBlurFilter;
import com.whuang022.litecv.filter.ImageMotionBlurFilter;
import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageMotionBlurProcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ImageIO io=new ImageIO();
        BufferedImage imageBuff= io.getImage("test.jpg");
        int[][]image=io.getGrayF(imageBuff);
        BufferedImage imageGrayBuff=io.matrixToImage(image);
        io.showImage(imageGrayBuff);
        ImageFilter filter=new ImageMotionBlurFilter();
        ImageFilterConfig config=new ImageFilterConfig();
        config.setting.put("ksize", "5");
        config.setting.put("dir", "1");
        int[][]imageBlured=filter.filter(image,config);
        BufferedImage imageBluredBuff=io.matrixToImage(imageBlured);
        io.showImage(imageBluredBuff);
    }
    
}
