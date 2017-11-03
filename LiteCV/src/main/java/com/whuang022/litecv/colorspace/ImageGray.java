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
public class ImageGray extends ImageVisual implements Image ,Serializable 
{
    public int [][] G;
    
    @Override
    public void display() 
    {
        BufferedImage image=matrixToImage(G);
        showImage(image,"Gray Color Space");
    }
    @Override
    public void save(String path, String type) 
    {
        BufferedImage image=matrixToImage(G);
        File outputfile = new File(path);
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
