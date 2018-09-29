/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorfilter;

import com.whuang022.litecv.area.ImageAreaFilter;
import com.whuang022.litecv.area.ImageAreaObject;
import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;
import com.whuang022.litecv.colorspace.ImageYCbCr;
import com.whuang022.litecv.paint.ImagePainter;
import com.whuang022.litecv.roi.ImageROI;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author user
 */
public class ImageYCbCrSkinFilterTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="C:\\Users\\user\\Desktop\\faceData\\jpg\\i418wb-fn.jpg";//
        ImageMatrix image,imageYCbCr=null;
        ImageRGB imageRGB=null;
        
        ImageColorReader reader=new ImageColorReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceRGB);
        imageRGB=(ImageRGB)image;
        imageYCbCr =reader.imageRead(path, ImageColorSpaceType.ColorSpaceYCbCr);
        imageYCbCr=ImageYCbCrSkinFilter.filter(imageYCbCr);
        ImageYCbCr skin=(ImageYCbCr)imageYCbCr;
        imageRGB.mask= skin.mask;
        imageRGB.display();
        
        ArrayList<ImageAreaObject> areas=ImageAreaFilter.seedFilling_4Bin(skin.mask,10000);
        for(int i=0;i<areas.size();i++)
        {
            ImageAreaObject area=areas.get(i);
           if(!area.isFatArea()){
               UUID uuid = UUID.randomUUID();
               ImageRGB ROIC=ImageROI.cropImageRGB_ImageRGBWidthSquare(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), (ImageRGB) image);
                ROIC.save("C:\\Users\\user\\Desktop\\face\\face"+uuid+".jpg", "jpg");
           }
           else
           {
                 UUID uuid = UUID.randomUUID();
               ImageRGB ROIC=ImageROI.cropImageRGB_ImageRGBHidthSquare(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), (ImageRGB) image);
                ROIC.save("C:\\Users\\user\\Desktop\\face\\face"+uuid+".jpg", "jpg");
           }
         
        }
        
    }
    
}
