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
public class ImageProcessorFace2 implements ImageProcessor
{

    private  ImageColorReader reader=new ImageColorReader();
    private ImageDynamicComparator threshold = null;
    ImageColorFilter filter =new ImageHSVFilter();
    //
    private ImageMatrix faceM =null;
    private ImageSimilarity sm=null;
    private ImageResize r=null;
    public ImageProcessorFace2()
    {
     //   String skinCondiction="(((H >= 0)&& (H <= 25 )) || (H >= 335 ) && (H <= 360 ))&&(((S >= 0.2 )&& (S <= 0.6 )) && (V >= 0.4 ))";
            String skinCondiction="(((H >= 0)&& (H <= 50 )) || (H >= 340 ) && (H <= 360 ))&&(((S <= 0.6 )) && (V >= 0.35 ))";
        try 
        {
           threshold=ImageDynamicComparatorFactory.getComparator( skinCondiction, "skinCondictionHSV");
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) 
        {
            Logger.getLogger(ImageDynamicComparatorFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        /////
        /////
        faceM =reader.imageRead("C:\\Users\\user\\Desktop\\average_face.jpg", ImageColorSpaceType.ColorSpaceGray);
        sm=new ImageSimilarity( (ImageGray) faceM);
        r=new ImageResize();
        /////
    }
    @Override
    public BufferedImage process(BufferedImage input) 
    {
        ImageMatrix image,imageYCbCr =null;
        
        image=reader.imageRead(input, ImageColorSpaceType.ColorSpaceRGB);
        imageYCbCr =reader.imageRead(input, ImageColorSpaceType.ColorSpaceYCbCr);
        
        imageYCbCr=ImageYCbCrSkinFilter.filter(imageYCbCr);
        ImageYCbCr skin=(ImageYCbCr)imageYCbCr;
        
        ImageRGB imageOutTmp= (ImageRGB) image;
        ArrayList<ImageAreaObject> areas=ImageAreaFilter.seedFilling_4Bin(skin.mask,800);
        for(int i=0;i<areas.size();i++)
        {
            ImageAreaObject area=areas.get(i);
           if(!area.isFatArea()){
         //   ImagePainter.drawBox(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);}
         
            ImageGray ROI=ImageROI.cropImageRGB_ImageGrayWidthSquare(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
            ImageRGB ROIC=ImageROI.cropImageRGB_ImageRGBWidthSquare(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
            ImageGray o=new  ImageGray();
            o.G=r.getImageToSizeBiLinear(ROI.G,26, 26);
           // 
            o.mask=new boolean[26][26];
           // o.display();
            double MSE=sm.MSE(o);
           // if(MSE<8000&&MSE>7500)
          if(MSE<7000)
            //if(MSE<9000&&MSE>7500)
            {
                ImagePainter.drawBox(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
                UUID uuid = UUID.randomUUID();
                //ROIC.save("C:\\Users\\user\\Desktop\\face\\face"+uuid+".jpg", "jpg");
            }
        }
        }
        imageOutTmp.mask=new boolean[imageOutTmp.R.length][imageOutTmp.R[0].length];
       return imageOutTmp.getBufferedImage();   
    }   
}
