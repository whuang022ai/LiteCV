package com.whuang022.litecv.colorspace;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class ImageRGB extends ImageVisual implements ImageMatrix ,Serializable 
{
    static org.slf4j.Logger logger = LoggerFactory.getLogger(ImageRGB.class);
    public int [][] R;
    public int [][] G;
    public int [][] B;   
   
    @Override
    public void display() 
    {
        BufferedImage image=matrixToImage(R,G,B);
        showImage(image,"RGB Color Space");
    }
    
    @Override
    public void save(String path,String type) 
    {
        File outputfile = new File(path);
        BufferedImage image=matrixToImage(R,G,B);
        try
        {
            ImageIO.write(image, type, outputfile);
        } 
        catch (IOException ex) 
        {
            logger.error(ex.getMessage());
        }
    }


    public ImageColorSpaceType getType() 
    {
        return ImageColorSpaceType.ColorSpaceRGB;
    }

   
   
 
}
