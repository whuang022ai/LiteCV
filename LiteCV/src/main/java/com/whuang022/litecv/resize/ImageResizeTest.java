/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.resize;

import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageResizeTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ImageIO io=new ImageIO();
        BufferedImage imageBuff= io.getImage("C:\\Users\\user\\Desktop\\average_face.jpg");
        int[][]image=io.getGrayF(imageBuff);
        BufferedImage imageGrayBuff=io.matrixToImage(image);
         System.out.println(imageGrayBuff.getWidth()+":"+imageGrayBuff.getHeight());
        io.showImage(imageGrayBuff);
        ImageResize r=new ImageResize();
        int[][]imageBig=r.biCubicInterpolation(image,2);
        BufferedImage imageBigGrayBuff=io.matrixToImage(imageBig);
        io.showImage(imageBigGrayBuff);
        System.out.println(imageBigGrayBuff.getWidth()+":"+imageBigGrayBuff.getHeight());
    }
    
}
