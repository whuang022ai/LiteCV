/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.example;

import com.whuang022.litecv.filter.ImageFilter;

import com.whuang022.litecv.filter.ImageSobelEdgeFilter;
import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageSobelEdgeProcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // int ksize=3;
        //  int padding=ksize/2;
        ImageIO io=new ImageIO();
        BufferedImage imageBuff= io.getImage("test.jpg");
        int[][]image=io.getGrayF(imageBuff);
        BufferedImage imageGrayBuff=io.matrixToImage(image);
        io.showImage(imageGrayBuff);
        ImageFilter filter=new ImageSobelEdgeFilter();
        int[][]imageBlured=filter.filter(image);
        BufferedImage imageBluredBuff=io.matrixToImage(imageBlured);
        io.showImage(imageBluredBuff);
    }
    
}
