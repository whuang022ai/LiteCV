/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.paint;

import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageCombineTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here\
        ImageIO io=new ImageIO();
        BufferedImage imageBuff1= io.getImage("C:\\Users\\user\\Desktop\\TREE.jpg");
        BufferedImage imageBuff2= io.getImage("C:\\Users\\user\\Desktop\\mask1.png");
        io.showImage(ImageCombine.imageAdd(imageBuff1,imageBuff2,40,50));
    }
    
}
