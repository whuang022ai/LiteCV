
package com.whuang022.litecv.cornea;

import com.whuang022.litecv.colorspace.ImageColorSpaceConverter;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageIO;
import com.whuang022.litecv.colorspace.ImageIOReader;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageReader;
import com.whuang022.litecv.filter.ImageFilterConfig;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparator;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Vector;

/**
 * 眼角膜模擬
 * (具有過濾輸入影像之色彩頻道的影像讀取類別)
 * @author user
 */
public class ImageCorneaReader extends ImageIOReader implements ImageCornea
{

    @Override
    public ImageMatrix imageRead(BufferedImage src, ImageColorSpaceType dst, ImageDynamicComparator thresholder) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageMatrix imageRead(String path, ImageColorSpaceType dst, ImageDynamicComparator thresholder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   private BufferedImage BufferedImagetRGB_HSVFilter(BufferedImage src,ImageDynamicComparator thresholder)
   {
       int w1 = src.getWidth();
       int h1 = src.getHeight();
       BufferedImage bufferedImage = new BufferedImage( w1,   h1, BufferedImage.TYPE_INT_RGB);
       int valueR=0;
       int valueG=0;
       int valueB=0;
       for (int i = 0; i < h1; i++)
       {
            for (int j = 0; j < w1; j++) 
            {
                valueR = src.getRGB(j, i); 
                valueG = src.getRGB(j, i);
                valueB = src.getRGB(j, i);
                int R = ( valueR >> 16) & 0xff;
                int G = ( valueG >> 8) & 0xff;
                int B = ( valueB) & 0xff;
                Vector<Double> hsv=ImageColorSpaceConverter.RGBtoHSV( R, G, B);
                ImageFilterConfig config=new ImageFilterConfig();
                config.setting.put("H", ""+ hsv.get(0));
                config.setting.put("S", ""+ hsv.get(1));
                config.setting.put("V", ""+ hsv.get(2));
                int pixel=0;
                if(thresholder.threshold(config))
                {
                    int red= R;
                    int blue=B;
                    int green=G;
                    pixel=new Color(red,green,blue).getRGB(); 
                }
                else
                {
                    pixel=new Color(0,0,0).getRGB(); 
                }
                bufferedImage.setRGB(j, i, pixel);  
            }
       }
       return bufferedImage;
    }

    @Override
    public BufferedImage imageReadBufferedImage(BufferedImage src, ImageColorSpaceType dst, ImageDynamicComparator thresholder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BufferedImage imageReadBufferedImage(String path, ImageColorSpaceType dst, ImageDynamicComparator thresholder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
