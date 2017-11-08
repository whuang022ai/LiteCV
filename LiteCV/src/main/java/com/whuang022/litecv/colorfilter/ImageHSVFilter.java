package com.whuang022.litecv.colorfilter;

import com.whuang022.litecv.colorspace.ImageColorSpaceConverter;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;
import com.whuang022.litecv.filter.ImageFilterConfig;
import com.whuang022.litecv.threshold.ImageThresholdComparator;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparator;
import java.util.Vector;

/**
 *
 * @author user
 */
public class ImageHSVFilter implements ImageColorFilter
{
    private ImageDynamicComparator   theshold;
    
    @Override
    
    public ImageMatrix filter(ImageMatrix image,ImageColorSpaceType thresholdColorSpace, ImageDynamicComparator  threshold) //影像:色彩過濾空間:閥值比較子
    {
        this.theshold=threshold;
        ImageColorSpaceType srcType= image.getType();
        ImageRGB imageSrc= (ImageRGB) image;
        for(int i=0;i<imageSrc.R.length;i++)
        {
            for(int j=0;j<imageSrc.R[0].length;j++)
            {
                int R=imageSrc.R[i][j];
                int G=imageSrc.G[i][j];
                int B=imageSrc.B[i][j];
                Vector<Double> hsv=ImageColorSpaceConverter.RGBtoHSV(R, G, B);
                double H=hsv.get(0);
                double S=hsv.get(1);
                double V=hsv.get(2);
                ImageFilterConfig config=new ImageFilterConfig();
                config.setting.put("H", ""+H);
                config.setting.put("S", ""+S);
                config.setting.put("V", ""+V);
                imageSrc.mask[i][j]=!threshold.threshold(config);
            }
        }
        //
       //如果過濾色彩空間(thresholdColorSpace)不等於輸入色彩空間 執行轉換過濾 
       ///如果過濾色彩空間(thresholdColorSpace)等於輸入色彩空間 執行過濾
        return imageSrc;
    }
    
     private ImageMatrix convertFilter(ImageMatrix src,String typ)
    {
        if(typ.equals("ColorSpaceRGB"))
        {
           // return BufferedImagetoRGB(src);
        }
        else  if(typ.equals("ColorSpaceGray"))
        {
          //  return BufferedImagetoGray(src);
        }
        else  if(typ.equals("ColorSpaceHSV"))
        {
          //  return BufferedImagetoHSV(src);
        }
        return null;
    }

    @Override
    public int[][] filter(int[][] image, ImageFilterConfig config) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] filter(int[][] image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
