package com.whuang022.litecv.cam;

import com.whuang022.litecv.neuralnet.modle.NeuralNetFeedforwardThreeLayerModle;
import com.whuang022.litecv.neuralnet.net.NeuralNetFeedforwardThreeLayer;
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
import com.whuang022.litecv.io.ImageIO;
import com.whuang022.litecv.paint.ImageCombine;
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
public class ImageProcessorFace4 implements ImageProcessor
{
    private  ImageColorReader reader=new ImageColorReader();
    private ImageDynamicComparator threshold = null;
    ImageColorFilter filter =new ImageHSVFilter();
    private   NeuralNetFeedforwardThreeLayer nn;
    private ImageResize r;
    private  BufferedImage imageBuff2;
    public ImageProcessorFace4()
    {
       ImageIO io=new ImageIO();
       NeuralNetFeedforwardThreeLayerModle M=new NeuralNetFeedforwardThreeLayerModle("Face2.XML");
       nn=new NeuralNetFeedforwardThreeLayer(M);
       r=new ImageResize();
       imageBuff2= io.getImage("C:\\Users\\user\\Desktop\\mask2.png");
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
           if(!area.isFatArea())
           {
                ImageGray ROI=ImageROI.cropImageRGB_ImageGrayWidthSquare(area.getMinH(), area.getMinV(),area.getMaxH(), area.getMaxV(), imageOutTmp);
                ImageGray o=new  ImageGray();
                o.G=r.getImageToSizeBiLinear(ROI.G,8, 8);
                if(o.G.length*o.G[0].length==64){
                double [][]Input=new double[1][o.G.length*o.G[0].length];
                int r=0;
                for(int q=0;q<o.G.length;q++)
                {
                   for(int p=0;p<o.G[0].length;p++)
                   {
                       double pi=0.0+o.G[p][q];
                       pi=pi/255.0;
                       Input[0][r]=pi;
                       r++;
                   }
                }
                double [][]out=nn.ForwardPropagation(Input);
                //ROI
                if(out[0][0]<0.4)
                {
                    double s=area.getMaxH()-area.getMinH();
                   s=s/0.1;
                    input=ImageCombine.imageAdd(input,imageBuff2,area.getMinH()+50,area.getMinV()+20);
                }
                }
           }
        }
        //imageOutTmp.mask=new boolean[imageOutTmp.R.length][imageOutTmp.R[0].length];
       return input;
    }   
}
