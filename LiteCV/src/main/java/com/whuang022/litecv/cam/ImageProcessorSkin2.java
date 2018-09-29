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
public class ImageProcessorSkin2 implements ImageProcessor
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
        imageOutTmp.mask=skin.mask;
        
       return imageOutTmp.getBufferedImage();   
    }   
}
