/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorfilter;

import com.whuang022.litecv.colorspace.ImageColorSpaceReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.filter.ImageFilter;
import com.whuang022.litecv.filter.ImageFilterConfig;
import com.whuang022.litecv.threshold.ImageThresholdComparator;
import com.whuang022.litecv.threshold.ImageTwiceDoubleThreshold;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparator;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparatorFactory;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparatorFactoryTest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ImageHSVSkinFilterTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        String path="http://www.barabbas.com/wp-content/uploads/2015/04/2012.happy-people.jpg";
        ImageMatrix image =null;
        ImageColorSpaceReader reader=new ImageColorSpaceReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceRGB);
        image.display();
        ImageColorFilter filter =new ImageHSVFilter();
        
        String skinCondiction="(((H >= 0)&& (H <= 25 )) || (H >= 335 ) && (H <= 360 ))&&(((S >= 0.2 )&& (S <= 0.6 )) && (V >= 0.4 ))";
        ImageDynamicComparator threshold = null;
        try 
        {
           threshold=ImageDynamicComparatorFactory.getComparator( skinCondiction, "skinCondiction");
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) 
        {
            Logger.getLogger(ImageDynamicComparatorFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageMatrix imageOut=filter.filter(image, ImageColorSpaceType.ColorSpaceHSV, threshold);
        imageOut.display();
    }
    
}
