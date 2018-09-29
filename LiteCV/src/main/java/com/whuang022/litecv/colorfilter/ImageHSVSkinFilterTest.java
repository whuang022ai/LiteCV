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
import com.whuang022.litecv.colorspace.ImageGray;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;
import com.whuang022.litecv.filter.ImageFilter;
import com.whuang022.litecv.filter.ImageFilterConfig;
import com.whuang022.litecv.paint.ImagePainter;
import com.whuang022.litecv.resize.ImageResize;
import com.whuang022.litecv.roi.ImageROI;
import com.whuang022.litecv.similarity.ImageSimilarity;
import com.whuang022.litecv.threshold.ImageThresholdComparator;
import com.whuang022.litecv.threshold.ImageTwiceDoubleThreshold;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparator;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparatorFactory;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparatorFactoryTest;
import java.util.ArrayList;
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
        String path="https://www.bishophouse.com/wp-content/uploads/2014/10/large-group-of-employees-smallercopy.jpg";
        
        //https://execed.business.uq.edu.au/sites/default/files/styles/700x422ssc/public/programs/leadingpeopleteams_courseimage.jpg?itok=LlcnBr0X
        //https://www.bishophouse.com/wp-content/uploads/2014/10/large-group-of-employees-smallercopy.jpg
        //http://www.cs.ntue.edu.tw/cssa/joomla/images/autonomy/y102.jpg
        //http://www.devenir-photographe.fr/wp-content/uploads/2012/11/wpid-Photo-4-juin-2011-1343.jpg
        //https://www.ouest-france.fr/sites/default/files/styles/image-900x500/public/2015/09/04/deux-nouvelles-directrices-lecole-primaire-publique.jpg?itok=dNcSWl7k
        
        ImageMatrix image =null;
        ImageMatrix faceM =null;
        ImageColorReader reader=new ImageColorReader();
        ImageResize r=new ImageResize();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceRGB);
        faceM=reader.imageRead("C:\\Users\\user\\Desktop\\average_face.jpg", ImageColorSpaceType.ColorSpaceGray);
        ImageSimilarity sm=new ImageSimilarity( (ImageGray) faceM);
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
        // imageOut.display();
        ImageRGB imageOutTmp= (ImageRGB) imageOut;
        ArrayList<ImageAreaObject> areas=ImageAreaFilter.seedFilling_4Bin(imageOutTmp.mask,300);
        for(int i=0;i<areas.size();i++)
        {
            ImageAreaObject area=areas.get(i);
            //if(!area.isFatArea()){
            ImageGray ROI=ImageROI.cropImageRGB_ImageGrayWidthSquare(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
            ImageGray o=new  ImageGray();
            o.G=r.getImageToSizeBiLinear(ROI.G,25, 25);
            o.mask=new boolean[25][25];
            double MSE=sm.MSE(o);
            
            //sm
            //System.out.println( ROI.G.length+":"+ ROI.G[0].length);
            
            if(MSE<10000){
                ROI.display();
            System.out.println( MSE);}
           // ImagePainter.drawBox(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
            
        }
        
        imageOutTmp.mask=new boolean[imageOutTmp.R.length][imageOutTmp.R[0].length];
        imageOutTmp.display();
      //  imageOut.display();
    }
    
}
