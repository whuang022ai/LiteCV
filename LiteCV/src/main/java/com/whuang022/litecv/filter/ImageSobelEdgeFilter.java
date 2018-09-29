package com.whuang022.litecv.filter;

import com.whuang022.litecv.convolution.ImageConvolution;
import com.whuang022.litecv.convolution.ImageConvolutionFFT;
import com.whuang022.litecv.kernel.ImageEdgeKernel;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ImageSobelEdgeFilter implements ImageFilter
{
    public ImageConvolution  imageConvolution=new ImageConvolutionFFT();
    @Override
    public int[][] filter(int[][] image, ImageFilterConfig config) 
    {
        
        return null;
    }
    @Override
    public int[][] filter(int[][] image) 
    {
       //System.out.println("src:"+ image.length+":"+image[0].length);
       int [][]outputY=imageConvolution.convolution(image, ImageEdgeKernel.sobelGxR);
       int [][]outputX=imageConvolution.convolution(image, ImageEdgeKernel.sobelGyR);
       int [][]output=new int[outputX.length][outputX[0].length];
       for(int i=0;i<  output.length;i++)
       {
            for(int j=0;j<  output[0].length;j++)
            {
               // int s=(int)Math.round(Math.sqrt(outputX[i][j]*outputX[i][j]+outputX[i][j]*outputY[i][j]));
                int s=(int)(Math.abs(outputX[i][j])+Math.abs(outputY[i][j]));
                output[i][j]=s;
            }
       }
      // System.out.println("src:"+ output.length+":"+output[0].length);
        return output;
    }
    public ArrayList<int[][]> filterTwoPath(int[][] image) 
    {
       ArrayList<int[][]> output=new ArrayList<> ();
       int [][]outputY=imageConvolution.convolution(image, ImageEdgeKernel.sobelGxR);
       int [][]outputX=imageConvolution.convolution(image, ImageEdgeKernel.sobelGyR);
       output.add(outputX);
       output.add(outputY);
       return output;
    }
    
}
