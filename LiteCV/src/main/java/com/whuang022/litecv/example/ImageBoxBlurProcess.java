package com.whuang022.litecv.example;

import com.whuang022.litecv.filter.ImageBoxBlurFilter;
import com.whuang022.litecv.filter.ImageFilter;
import com.whuang022.litecv.filter.ImageFilterConfig;
import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageBoxBlurProcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ImageIO io=new ImageIO();
        BufferedImage imageBuff= io.getImage("test.jpg");
        int[][]image=io.getGrayF(imageBuff);
        BufferedImage imageGrayBuff=io.matrixToImage(image);
        io.showImage(imageGrayBuff);
        ImageFilter filter=new ImageBoxBlurFilter();
        ImageFilterConfig config=new ImageFilterConfig();
        config.setting.put("ksize", "3");
        int[][]imageBlured=filter.filter(image,config);
        BufferedImage imageBluredBuff=io.matrixToImage(imageBlured);
        io.showImage(imageBluredBuff);
    }
    
}
