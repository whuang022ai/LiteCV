/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorspace;

import static com.whuang022.litecv.colorspace.ImageRGB.logger;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class ImageHSV extends ImageVisual implements Image ,Serializable 
{
    double [][]H;
    double [][]S;
    double [][]V;
    @Override
    public void display() {
        BufferedImage image=matrixToImage(H,S,V);
        showImage(image,"HSV Color Space");
    }

    @Override
    public void save(String path, String type) {
       File outputfile = new File(path);
        BufferedImage image=matrixToImage(H,S,V);
        try
        {
            ImageIO.write(image, type, outputfile);
        } 
        catch (IOException ex) 
        {
            logger.error(ex.getMessage());
        }
    }
    
}
