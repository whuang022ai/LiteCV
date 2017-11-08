/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.example;

import com.whuang022.litecv.io.ImageIO;
import com.whuang022.litecv.resize.ImageResize;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageResizeProcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ImageIO io=new ImageIO();
        BufferedImage imageBuff= io.getImage("test.jpg");
        int[][]image=io.getGrayF(imageBuff);
        BufferedImage imageGrayBuff=io.matrixToImage(image);
        io.showImage(imageGrayBuff);
        ImageResize r=new ImageResize();
        int[][]imageBig=r.nearestInterpolation(image,3.5);
        BufferedImage imageBigGrayBuff=io.matrixToImage(imageBig);
        io.showImage(imageBigGrayBuff);
    }
    
}
