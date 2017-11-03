/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.example;

import com.whuang022.litecv.filter.ImageFilter;
import com.whuang022.litecv.filter.ImageMedianFilter;
import com.whuang022.litecv.filter.ImageSobelEdgeFilter;
import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageMedianFilterProcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ImageIO io=new ImageIO();
        BufferedImage imageBuff= io.getImage("C:\\Users\\user\\Desktop\\noisylena.jpg");
        int[][]image=io.getGrayF(imageBuff);
        BufferedImage imageGrayBuff=io.matrixToImage(image);
        io.showImage(imageGrayBuff);
        ImageFilter filter=new ImageMedianFilter();
        int[][]imageDenoise=filter.filter(image);
        BufferedImage imageBluredBuff=io.matrixToImage(imageDenoise);
        io.showImage(imageBluredBuff);
    }
    
}
