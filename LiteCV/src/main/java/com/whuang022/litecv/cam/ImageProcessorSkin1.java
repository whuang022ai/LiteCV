package com.whuang022.litecv.cam;

import com.whuang022.litecv.area.ImageAreaFilter;
import com.whuang022.litecv.area.ImageAreaObject;
import com.whuang022.litecv.colorfilter.ImageColorFilter;
import com.whuang022.litecv.colorfilter.ImageHSVFilter;
import com.whuang022.litecv.colorfilter.ImageYCbCrSkinFilter;
import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageGray;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;
import com.whuang022.litecv.colorspace.ImageYCbCr;
import com.whuang022.litecv.paint.ImagePainter;
import com.whuang022.litecv.resize.ImageResize;
import com.whuang022.litecv.roi.ImageROI;
import com.whuang022.litecv.similarity.ImageSimilarity;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparator;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparatorFactory;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparatorFactoryTest;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ImageProcessorSkin1 implements ImageProcessor
{

    private  ImageColorReader reader=new ImageColorReader();
 
   

   
    @Override
    public BufferedImage process(BufferedImage input) 
    {
        ImageMatrix image,imageYCbCr =null;
        
        image=reader.imageRead(input, ImageColorSpaceType.ColorSpaceRGB);
        imageYCbCr =reader.imageRead(input, ImageColorSpaceType.ColorSpaceYCbCr);
        
        imageYCbCr=ImageYCbCrSkinFilter.filter(imageYCbCr);
        ImageYCbCr skin=(ImageYCbCr)imageYCbCr;
        
        ImageRGB imageOutTmp= (ImageRGB) image;
     
        
        ArrayList<ImageAreaObject> areas=ImageAreaFilter.seedFilling_4Bin(skin.mask,500);
        for(int i=0;i<areas.size();i++)
        {
            ImageAreaObject area=areas.get(i);
           if(!area.isFatArea()){
               UUID uuid = UUID.randomUUID();
               ImageRGB ROIC=ImageROI.cropImageRGB_ImageRGBWidthSquare(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
                ROIC.save("C:\\Users\\user\\Desktop\\face\\face"+uuid+".jpg", "jpg");
           ImagePainter.drawBox(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
           }
         
        }
        imageOutTmp.mask=new boolean[imageOutTmp.R.length][imageOutTmp.R[0].length];
       return imageOutTmp.getBufferedImage();   
    }   
}
