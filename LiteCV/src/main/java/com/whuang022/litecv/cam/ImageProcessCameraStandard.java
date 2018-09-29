package com.whuang022.litecv.cam;

import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 *
 * @author user
 */
public class ImageProcessCameraStandard extends ImageProcessCamera
{
    private ImageProcessor processor=null;
    
    public ImageProcessCameraStandard(String title, int FPSLimit,ImageProcessor processor) 
    {
        super(title, FPSLimit);
        this.processor=processor;
    }
    @Override
    public void paintImage(WebcamPanel panel, BufferedImage image, Graphics2D g2) 
    {
        if (this.painter!= null)
        {
            painter.paintImage(panel, processor.process(image), g2);
        }
    
    }
    
}
