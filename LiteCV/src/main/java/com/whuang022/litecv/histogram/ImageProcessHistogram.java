package com.whuang022.litecv.histogram;

/**
 * 
 * @author user
 */
public class ImageProcessHistogram 
{
    private  int imageSize=0;
    private  int []histogram=new int[256];//灰度統計
    private double pdf[]=new double[256];//灰度機率分布
    private double[]cdf=new double[256];//灰度機率累積分布
    private double avgHistogram=0;//灰度平均值
    
    public ImageProcessHistogram(int [][] image)
    {
        this.imageSize=image.length*image[0].length;
        getHistogram(image);
        getPDFCDFAVG(this.histogram,imageSize);
    }
    public int imageSize()
    {
        return this.imageSize;
    }
    public int[]histogram()
    {
        return this. histogram;
    }
    public double[]pdf()
    {
        return this.pdf;
    }
    public double[]cdf()
    {
        return this.cdf;
    }
     public double avgHistogram()
    {
        return this.avgHistogram;
    }
    private void getPDFCDFAVG(int[] histogram,int imageSize)
    {
        double pdf[]=new double[256];
        double cdf[]=new double[256];
        double avg=0;
        for(int i=0;i<histogram.length;i++)
        {
            double tmp=histogram[i];
            double pdfValue=tmp/imageSize;
            pdf[i]=pdfValue;
            avg+=i*pdfValue;
            for(int j=0;j<=i;j++)
            {
                cdf[i]+= pdf[j];
            }
        }
        this.pdf=pdf;
        this.cdf=cdf;
        this.avgHistogram=avg;
    }
    private void getHistogram(int [][] image)
    {
        int []histogram=new int[256];//灰度統計
        for (int[] image1 : image) 
        {
            for (int j = 0; j<image[0].length; j++) 
            {
                int tmp = image1[j];
                if(tmp>0&&tmp<=255)
                {
                    histogram[tmp]++; 
                }
            }
        }
       this. histogram=histogram;
    }
}
