
package com.whuang022.litecv.cam;

import com.whuang022.litecv.light.ImageGammaCorrection;
import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;
import java.awt.image.BufferedImage;


public class ImageProcessCameraGammaTest {

    
     
    public static void main(String[] args) 
    {
        ImageProcessor processor=new ImageProcessor() 
        {
            private com.whuang022.litecv.io.ImageIO io=new com.whuang022.litecv.io.ImageIO();
            private ImageColorReader reader=new ImageColorReader();
            @Override
            public BufferedImage process(BufferedImage input) 
            {
                ImageMatrix image =null;
                reader=new ImageColorReader();
                image=reader.imageRead(input, ImageColorSpaceType.ColorSpaceRGB);
              //  image=ImageGammaCorrection.gammaCorrection(image,1);
                ImageRGB img=(ImageRGB) image;
                return img.getBufferedImage();
            }
        };
        new ImageProcessCameraStandard("Gamma Filter",100,processor);
    }
    
}
