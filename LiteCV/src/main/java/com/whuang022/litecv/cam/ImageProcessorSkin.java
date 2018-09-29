package com.whuang022.litecv.cam;

import com.whuang022.litecv.area.ImageAreaFilter;
import com.whuang022.litecv.area.ImageAreaObject;
import com.whuang022.litecv.colorfilter.ImageColorFilter;
import com.whuang022.litecv.colorfilter.ImageHSVFilter;
import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageGray;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;
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
public class ImageProcessorSkin implements ImageProcessor
{

    private  ImageColorReader reader=new ImageColorReader();
    private ImageDynamicComparator threshold = null;
    ImageColorFilter filter =new ImageHSVFilter();

    public ImageProcessorSkin()
    {
       // String skinCondiction="(((H >= 0)&& (H <= 25 )) || (H >= 335 ) && (H <= 360 ))&&(((S >= 0.2 )&& (S <= 0.6 )) && (V >= 0.4 ))";
           String skinCondiction="(((H >= 0)&& (H <= 50 )) || (H >= 340 ) && (H <= 360 ))&&(((S <= 0.6 )) && (V >= 0.35 ))";
        try 
        {
           threshold=ImageDynamicComparatorFactory.getComparator( skinCondiction, "skinCondictionHSV");
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) 
        {
            Logger.getLogger(ImageDynamicComparatorFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    @Override
    public BufferedImage process(BufferedImage input) 
    {
        ImageMatrix image =null;
        image=reader.imageRead(input, ImageColorSpaceType.ColorSpaceRGB);
        ImageMatrix imageOut=filter.filter(image, ImageColorSpaceType.ColorSpaceHSV, threshold);
        ImageRGB imageOutTmp= (ImageRGB) imageOut;
     
        
        ArrayList<ImageAreaObject> areas=ImageAreaFilter.seedFilling_4Bin(imageOutTmp.mask,800);
        for(int i=0;i<areas.size();i++)
        {
            ImageAreaObject area=areas.get(i);
           if(!area.isFatArea()){
               
           ImagePainter.drawBox(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
           }
         
        }
        imageOutTmp.mask=new boolean[imageOutTmp.R.length][imageOutTmp.R[0].length];
       return imageOutTmp.getBufferedImage();   
    }   
}
