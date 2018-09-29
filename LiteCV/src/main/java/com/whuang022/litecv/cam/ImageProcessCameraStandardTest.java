
package com.whuang022.litecv.cam;

import java.awt.image.BufferedImage;


public class ImageProcessCameraStandardTest {

    
     
    public static void main(String[] args) 
    {
        ImageProcessor processor=new ImageProcessor() 
        {
            private com.whuang022.litecv.io.ImageIO io=new com.whuang022.litecv.io.ImageIO();
            @Override
            public BufferedImage process(BufferedImage input) 
            {
                 int[][]imaged=io.getGrayF(input);
                 return io.matrixToImage(imaged);
            }
        };
        new ImageProcessCameraStandard("Gray Filter",100,processor);
    }
    
}
