package com.whuang022.litecv.example;

import com.whuang022.litecv.filter.ImageFilter;
import com.whuang022.litecv.filter.ImageFilterConfig;
import com.whuang022.litecv.filter.ImageGaussBlurFilter;
import com.whuang022.litecv.io.ImageIO;
import java.awt.image.BufferedImage;

/**
 *com.whuang022.litecv.example.ImageGaussianBlurProcess
 * @author user
 */
public class ImageGaussianBlurProcess 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        int ksize=5;
        int padding=ksize/2;
        ImageIO io=new ImageIO();
        BufferedImage imageBuff= io.getImage("test.jpg");
       // System.out.println(imageBuff.getHeight()+":"+imageBuff.getWidth());
       // int[][]image=io.getGrayF(imageBuff,padding);
       int[][]image=io.getGrayF(imageBuff);
       // System.out.println(image.length+":"+image[0].length);
        BufferedImage imageGrayBuff=io.matrixToImage(image);
        //printMix(image);
        io.showImage(imageGrayBuff);
        ImageFilter filter=new ImageGaussBlurFilter();
        ImageFilterConfig config=new ImageFilterConfig();
        config.setting.put("ksize", ""+ksize);
        config.setting.put("sigma", "5");
        int[][]imageBlured=filter.filter(image,config);
        //System.out.println(imageBlured.length+":"+imageBlured[0].length);
        //printMix(imageBlured);
        BufferedImage imageBluredBuff=io.matrixToImage(imageBlured);
        io.showImage(imageBluredBuff);
    }
    public static   void printMix(int [][]valueGray)
    {
        for(int i=0;i<  valueGray.length;i++)
        {
            for(int j=0;j<  valueGray[0].length;j++)
            {
                System.out.printf( "%3d ",valueGray[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
