package com.whuang022.litecv.filter;

import com.whuang022.litecv.convolution.ImageConvolution;
import com.whuang022.litecv.convolution.ImageConvolutionFFT;
import com.whuang022.litecv.kernel.ImageBlurKernel;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * Image Box Blur Filter
 * @author whuang022
 */
public class ImageBoxBlurFilter implements ImageFilter 
{
    public ImageConvolution  imageConvolution=new ImageConvolutionFFT();
    static Logger logger = LoggerFactory.getLogger(ImageBoxBlurFilter.class);
    @Override
    public int[][] filter(int[][] image) 
    {
        
        int [][]output=imageConvolution.convolution(image, ImageBlurKernel.boxBlur );
        return output;
    }
    @Override
    public int[][] filter(int[][] image, ImageFilterConfig config) 
    {
        int ksize=Integer.parseInt(config.setting.get("ksize"));
        int [][]output=null;
        switch(ksize) 
        { 
            case 3:
                output=imageConvolution.convolution(image, ImageBlurKernel.boxBlur );
              break; 
            case 5: 
                output=imageConvolution.convolution(image, ImageBlurKernel.boxBlur5x5 );
              break;
            case 7: 
                output=imageConvolution.convolution(image, ImageBlurKernel.boxBlur7x7);
              break;
           default: 
             output=imageConvolution.convolution(image, ImageBlurKernel.boxBlur );
             logger.warn("Kernel size dose not implement . Use  default size.");
       }
        return output;
    }
    
}
