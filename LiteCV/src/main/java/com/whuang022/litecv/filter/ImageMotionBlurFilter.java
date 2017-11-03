package com.whuang022.litecv.filter;

import com.whuang022.litecv.convolution.ImageConvolution;
import com.whuang022.litecv.convolution.ImageConvolutionFFT;
import static com.whuang022.litecv.filter.ImageBoxBlurFilter.logger;
import com.whuang022.litecv.kernel.ImageBlurKernel;


/**
 * Image Motion Blur Filter
 * @author whuang022
 */
public class ImageMotionBlurFilter implements ImageFilter 
{
   public ImageConvolution  imageConvolution=new ImageConvolutionFFT();
    @Override
    public int[][] filter(int[][] image) 
    {
        double[][]motionBlurLeft= ImageBlurKernel.motionBlurLeft;
        int [][] output=imageConvolution.convolution(image,motionBlurLeft);
       return output;
    }

    @Override
    public int[][] filter(int[][] image, ImageFilterConfig config) 
    {
        int ksize=Integer.parseInt(config.setting.get("ksize"));
        int dir=Integer.parseInt(config.setting.get("dir"));
        int [][]output=null;
        switch(ksize) 
        { 
            case 3:
              if(dir==0)//L
              {
                output=imageConvolution.convolution(image,ImageBlurKernel.motionBlurLeft);
              }
              else if(dir==1)//F
              {
                output=imageConvolution.convolution(image,ImageBlurKernel.motionBlurRight);
              }
              break; 
            case 5: 
              if(dir==0)
              {
                 output=imageConvolution.convolution(image,ImageBlurKernel.motionBlurLeft5x5);
              }
              else if(dir==1)
              {
                 output=imageConvolution.convolution(image,ImageBlurKernel.motionBlurRight5x5);  
              }
              break;
            case 7: 
              if(dir==0)
              {
                 output=imageConvolution.convolution(image,ImageBlurKernel.motionBlurLeft7x7);
              }
              else if(dir==1)
              {
                 output=imageConvolution.convolution(image,ImageBlurKernel.motionBlurRight7x7);  
              }
              break;
           default: 
             logger.warn("Kernel size dose not implement . Use  default size.");
       }
       return output;
      
    }
    
}
