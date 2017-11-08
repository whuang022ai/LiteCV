/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.threshold;

import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageThresholdTest {

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
        
        ImageThresholdComparator st=new ImageSingleIntThreshold();
        
      
        ImageThreshold t=new ImageThreshold();
        int []tt={100,150};
        int ttt=200;
        int[][]imageOut=t.threshold(image, ttt, st);
         BufferedImage imageOutGrayBuff=io.matrixToImage(imageOut);
        io.showImage(imageOutGrayBuff);
        
    }
    
}
