package com.whuang022.litecv.convolution;

import com.whuang022.litecv.fft.ImageComplex;
import com.whuang022.litecv.fft.ImageComplexIm;
import com.whuang022.litecv.fft.ImageFFTCooleyTukeyBase2;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ImageConvolutionFFT implements ImageConvolution
{
    
    private int[][] getComplexTtoImage( ImageComplex[][] valueGray,int w,int h)//將複數影像轉回普通影像
    {
       int [][]image=new int [h][w];
       for(int i=0;i< h;i++)
        {
            for(int j=0;j< w;j++)
            {
                double x=valueGray[i][j].getRealNum();
                int n = Math.abs((int) Math.round(x)); 
                if(n>=255){n=255;}   
                if(n<0){n=0;};
                image[i][j]= n; 
            }
        }
       return image;
    }
     private int[][] getComplexTtoImageH( ImageComplex[][] valueGray,int w,int h,int paddingSize)//將複數影像轉回普通影像
    {
       int [][]image=new int [(h-paddingSize*2)][(w-paddingSize*2)];
       for(int i=0;i< image.length;i++)
        {
            for(int j=0;j< image[0].length;j++)
            {
                double x=valueGray[i+ paddingSize][j+ paddingSize].getRealNum();
                int n = Math.abs((int) Math.round(x)); 
                if(n>=255){n=255;}   
                if(n<0){n=0;}
                image[i][j]= n; 
            }
        }
       return image;
    }
    private ImageComplex[][] getFFTShift( ImageComplex[][] valueGray)//將影像位移至中心
    {
      ImageComplex [][]image=new ImageComplex [valueGray.length][valueGray[0].length];
       for(int i=0;i< image.length;i++)
        {
            for(int j=0;j< image[0].length;j++)
            {
                image[i][j]=valueGray[i][j].scale(Math.pow(-1, i+j));
            }
        }
       return image;
    }
    private ImageComplex[][] imageFFT(int [][] image,boolean shiftImage)
    {
        
        int hight=image.length;
        int weight=image[0].length;
        if(!isPowerOfTwo(hight))//調整長為2的n次
        {
            hight=minPowerOfTwo(hight);
        }
        if(!isPowerOfTwo(weight))//調整寬為2的n次
        {
            weight=minPowerOfTwo(weight);
        }
        //進行0填充
        ImageComplex[][] imageP=getPaddingZeroFFT(image, hight,weight);
        ImageComplex[][] imageO=new ImageComplex[hight][weight];
   
        if(shiftImage)
        {
            imageP=getFFTShift(imageP);//空間域做位移
        }
        //以水平方向為單位逐垂直方向做一維FFT
        for(int i=0;i<imageP.length;i++)
        {
            ImageComplex complexArr[]=imageP[i];  
            imageP[i]=new ImageFFTCooleyTukeyBase2().fft(complexArr);
        }
        //以垂直方向為單位逐水平方向做一維FFT
        ArrayList<ImageComplex[]> tmp=new ArrayList<>();//暫存
        for(int i=0;i<imageP[0].length;i++)//對每個水平方向做
        {
            ImageComplex complexArr[]=new ImageComplex[imageP.length];//垂直方向一組
            for(int j=0;j<imageP.length;j++)//垂直方向把資料組起來
            {
                complexArr[j]=imageP[j][i];
            }
            ImageComplex complexArrR[]=new ImageFFTCooleyTukeyBase2().fft(complexArr);//每個垂直方向做一次FFT
            tmp.add(complexArrR);//暫存
        }
        for(int w=0;w<tmp.size();w++)
        {
            ImageComplex[] HBag=tmp.get(w);
            for(int h=0;h<hight;h++)
            {
               imageO[h][w]=HBag[h]; 
            }
        }
        //原圖乘255 %255
        return imageO;
    }
    private ImageComplex[][] imageFFT(double[][] image,boolean shiftImage)
    {
       
        int hight=image.length;
        int weight=image[0].length;
        if(!isPowerOfTwo(hight))//調整長為2的n次
        {
            hight=minPowerOfTwo(hight);
        }
        if(!isPowerOfTwo(weight))//調整寬為2的n次
        {
            weight=minPowerOfTwo(weight);
        }
        //進行0填充
        ImageComplex[][] imageP=getPaddingZeroFFT(image, hight,weight);
        ImageComplex[][] imageO=new ImageComplex[hight][weight];
        //printMix(imageP);
        if(shiftImage)
        {
            imageP=getFFTShift(imageP);//空間域做位移
        }
        //以水平方向為單位逐垂直方向做一維FFT
        for(int i=0;i<imageP.length;i++)
        {
            ImageComplex complexArr[]=imageP[i];  
            imageP[i]=new ImageFFTCooleyTukeyBase2().fft(complexArr);
        }
        //以垂直方向為單位逐水平方向做一維FFT
        ArrayList<ImageComplex[]> tmp=new ArrayList<>();//暫存
        for(int i=0;i<imageP[0].length;i++)//對每個水平方向做
        {
            ImageComplex complexArr[]=new ImageComplex[imageP.length];//垂直方向一組
            for(int j=0;j<imageP.length;j++)//垂直方向把資料組起來
            {
                complexArr[j]=imageP[j][i];
            }
            ImageComplex complexArrR[]=new ImageFFTCooleyTukeyBase2().fft(complexArr);//每個垂直方向做一次FFT
            tmp.add(complexArrR);//暫存
        }
        for(int w=0;w<tmp.size();w++)
        {
            ImageComplex[] HBag=tmp.get(w);
            for(int h=0;h<hight;h++)
            {
               imageO[h][w]=HBag[h]; 
            }
        }
        //原圖乘255 %255
        
        return imageO;
        //printMix( imageO);
    }
    private ImageComplex[][] imageIFFT(ImageComplex[][] image)
    {
        int hight=image.length;
        int weight=image[0].length;
       
        //進行0填充
        ImageComplex[][] imageP=image;
        ImageComplex[][] imageO=new  ImageComplex[hight][weight];
        //printMix(imageP);
        ArrayList<ImageComplex[]> tmp=new ArrayList<>();//暫存
        for(int i=0;i<imageP[0].length;i++)//對每個水平方向做
        {
            ImageComplex complexArr[]=new ImageComplex[imageP.length];//垂直方向一組
            for(int j=0;j<imageP.length;j++)//垂直方向把資料組起來
            {
                complexArr[j]=imageP[j][i];
            }
             ImageComplex complexArrR[]=new ImageFFTCooleyTukeyBase2().ifft(complexArr);//每個垂直方向做一次FFT
            tmp.add( complexArrR);//暫存
        }
        //
        for(int w=0;w<tmp.size();w++)
        {
            ImageComplex[] HBag=tmp.get(w);
            for(int h=0;h<hight;h++)
            {
                imageO[h][w]=HBag[h];
            }
        }
        //以水平方向為單位逐垂直方向做一維FFT
        for(int i=0;i<imageP.length;i++)
        {
            ImageComplex complexArr[]=imageO[i];  
            imageO[i]=new ImageFFTCooleyTukeyBase2().ifft(complexArr);
        }
        return imageO;
    }
    private ImageComplex[][] getPaddingZeroFFT(int [][]image,int hight,int weight)
    {
        ImageComplex[][] paddingImage=new ImageComplex[hight][weight];
        boolean isInBondHight=false;//是否在原本圖片大小之內
        boolean isInBondWeight=false;
        for(int i=0;i<hight;i++)
        {
            isInBondHight=false;//初始化
            isInBondWeight=false;
            if(i<image.length)
            {
                isInBondHight=true;
            }
            for(int j=0;j<weight;j++)
            {
                isInBondWeight=false;//初始化
                if(j<image[0].length)
                {
                    isInBondWeight=true;
                }
                if(isInBondHight&&isInBondWeight==true)
                {
                   double real=image[i][j];
                    ImageComplex c=new ImageComplexIm(real,0);
                    paddingImage[i][j]=c;
                }
                else
                {
                    ImageComplex c=new ImageComplexIm(0,0);
                    paddingImage[i][j]=c;
                }
            }
        }
        return paddingImage;
    }
    private ImageComplex[][] getPaddingZeroFFT(double [][]image,int hight,int weight)
    {
        ImageComplex[][] paddingImage=new ImageComplex[hight][weight];
        boolean isInBondHight=false;//是否在原本圖片大小之內
        boolean isInBondWeight=false;
        for(int i=0;i<hight;i++)
        {
            isInBondHight=false;//初始化
            isInBondWeight=false;
            if(i<image.length)
            {
                isInBondHight=true;
            }
            for(int j=0;j<weight;j++)
            {
                isInBondWeight=false;//初始化
                if(j<image[0].length)
                {
                    isInBondWeight=true;
                }
                if(isInBondHight&&isInBondWeight==true)
                {
                   double real=image[i][j];
                    ImageComplex c=new ImageComplexIm(real,0);
                    paddingImage[i][j]=c;
                }
                else
                {
                    ImageComplex c=new ImageComplexIm(0,0);
                    paddingImage[i][j]=c;
                }
            }
        }
        return paddingImage;
    }
    private int[][] getPaddingZeroArray(int [][]image,int hight,int weight)
    {
        int[][] paddingImage=new int[hight][weight];
        boolean isInBondHight=false;//是否在原本圖片大小之內
        boolean isInBondWeight=false;
        for(int i=0;i<hight;i++)
        {
            isInBondHight=false;//初始化
            isInBondWeight=false;
            if(i<image.length)
            {
                isInBondHight=true;
            }
            for(int j=0;j<weight;j++)
            {
                isInBondWeight=false;//初始化
                if(j<image[0].length)
                {
                    isInBondWeight=true;
                }
                if(isInBondHight&&isInBondWeight==true)
                {
                    paddingImage[i][j]=image[i][j];
                }
                else
                {
                    paddingImage[i][j]=0;
                }
            }
        }
        return paddingImage;
    }
    private double[][] getPaddingZeroArray(double [][]image,int hight,int weight)
    {
        double[][] paddingImage=new double[hight][weight];
        boolean isInBondHight=false;//是否在原本圖片大小之內
        boolean isInBondWeight=false;
        for(int i=0;i<hight;i++)
        {
            isInBondHight=false;//初始化
            isInBondWeight=false;
            if(i<image.length)
            {
                isInBondHight=true;
            }
            for(int j=0;j<weight;j++)
            {
                isInBondWeight=false;//初始化
                if(j<image[0].length)
                {
                    isInBondWeight=true;
                }
                if(isInBondHight&&isInBondWeight==true)
                {
                    paddingImage[i][j]=image[i][j];
                }
                else
                {
                    paddingImage[i][j]=0;
                }
            }
        }
        return paddingImage;
    }
    private int minPowerOfTwo(int x)
    {
        x|=x>>>1;   //*1*
        x|=x>>>2;   //*2*
        x|=x>>>4;   //*3*
        x|=x>>>8;   //*4*
        x|=x>>>16;  //*5*
        x=x+1;
        if(x<0)
        {
            x|=x>>>1;
        }
        return x;
    }
    public boolean isPowerOfTwo(int x) 
    {
        return x > 0 & (x & (x - 1)) == 0;
    } 
    @Override
    public int[][] convolution(int[][] image2, double[][] kernal) 
    {
        //int [][]image2=zeroPaddingMritx(image,1);
        int ksize=kernal.length;
        int padding=ksize/2;
        ImageComplex[][] imgC=imageFFT(image2,false);
        double[][] imgK=getPaddingZeroArray(kernal, imgC.length, imgC[0].length);
        ImageComplex[][] imgCK=imageFFT(imgK, false);
        for(int i=0;i<imgC.length;i++)
        {
            for(int j=0;j<imgC[0].length;j++)
            {
                imgC[i][j]=imgCK[i][j].times(imgC[i][j]);
            }
        }
        ImageComplex[][] imgC2=imageIFFT(imgC);
        int [][]gratImgF2=getComplexTtoImageH(imgC2, image2[0].length,image2.length,padding);
        return gratImgF2;
    }

    @Override
    public int[][] convolution(int[][] image, int[][] kernal) 
    {
        int ksize=kernal.length;
        int padding=ksize/2;
        ImageComplex[][] imgC=imageFFT(image,false);
        int[][] imgK=getPaddingZeroArray(kernal, imgC.length, imgC[0].length);
        ImageComplex[][] imgCK=imageFFT(imgK, false);
        for(int i=0;i<imgC.length;i++)
        {
            for(int j=0;j<imgC[0].length;j++)
            {
                imgC[i][j]=imgCK[i][j].times(imgC[i][j]);
            }
        }
        ImageComplex[][] imgC2=imageIFFT(imgC);
        int [][]gratImgF2=getComplexTtoImageH(imgC2, image[0].length,image.length,padding);
        return gratImgF2;
    }
    
    
}
