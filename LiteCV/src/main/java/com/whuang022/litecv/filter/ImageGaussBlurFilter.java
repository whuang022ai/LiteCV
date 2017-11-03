package com.whuang022.litecv.filter;
import com.whuang022.litecv.convolution.ImageConvolution;
import com.whuang022.litecv.convolution.ImageConvolutionFFT;
import com.whuang022.litecv.kernel.ImageBlurKernel;

/**
 * Image Gauss Blur Filter
 * @author whuang022
 */
public class ImageGaussBlurFilter  implements ImageFilter 
{
    public ImageConvolution  imageConvolution=new ImageConvolutionFFT();
    private int ksize=3;//defult 3*3
    private double sigma=1;///defult sigma=1
    @Override
    public int[][] filter(int[][] image) 
    {
        double[][]gaussianKernal= ImageBlurKernel.gaussianBlur(ksize,  sigma);
        int [][]output=imageConvolution.convolution(image, gaussianKernal);
        return output;
    }

    @Override
    public int[][] filter(int[][] image, ImageFilterConfig config) {
        
        this.ksize=(int)Math.round(Integer.parseInt(config.setting.get("ksize")));
        this.sigma=Double.parseDouble(config.setting.get("sigma"));
        double[][]gaussianKernal= ImageBlurKernel.gaussianBlur(ksize,  sigma);
        int [][]output=imageConvolution.convolution(image, gaussianKernal);
        return output;
    }
}
