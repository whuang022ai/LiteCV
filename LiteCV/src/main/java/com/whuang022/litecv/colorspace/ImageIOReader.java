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
import java.net.URL;

/**
 *
 * @author user
 */
public class ImageIOReader implements ImageIO
{

    @Override
    public BufferedImage getBufferedImage(String path) 
    {
        try 
        {
            File input = new File(path);
            BufferedImage image = javax.imageio.ImageIO.read(input);
            return image;
        } 
        catch (IOException ie) 
        {
            logger.error(ie.getMessage());
        }
        return null;
    }
    @Override
    public BufferedImage getBufferedImage(URL path) 
    {
        try 
        {
            BufferedImage image = javax.imageio.ImageIO.read(path);
            return image;
        } 
        catch (IOException ie) 
        {
            logger.error(ie.getMessage());
        }
        return null;
    }
    
}
