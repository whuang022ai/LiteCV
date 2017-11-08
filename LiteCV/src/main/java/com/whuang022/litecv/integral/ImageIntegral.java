/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.integral;

/**
 *
 * @author user
 */
public class ImageIntegral 
{
   public int [][] getIntegralImage(int [][] image)
   {
        int [][] integral=new int [image.length][image[0].length];
        int sum=0;
        for (int j = 0; j < image.length; j++)
        {
            sum = 0;
            for (int i = 0; i < image[0].length; i++)
            {
                sum += image[i][j];
                if(j==0)
                {
                     integral[i][j] = sum;
                }
                else
                {
                    integral[i][j] =integral[i][j-1] + sum;
                }
            }
        }
       return integral;
   }
   public int[][] getConVolImage(int[][]image,int ksize)
   {
	int w = (ksize- 1) / 2;
       int [][] convol=new int [image.length-w-1][image[0].length-w-1];
	for (int j = w ,l=0 ; j < image.length - w ; j++,l++)
	{
           for (int i = w,m=0; i < image[0].length - w ; i++,m++)
           {
                int A=image[j + w][ i + w] ;
                int B=0;
                int C=0;
                int D=0;
                if(i==1&&j>1)
                {
                    C=image[j - w-1][ i +w];
                }
                else if(j==1&&i>1)
                {
                    D=image[j +w][ i - w-1];
                }
                else if(j==1&&i==1)
                {
                 
                }
                else
                {
                    B=image[j - w-1][ i - w-1];
                    C=image[j - w-1][ i +w];
                    D=image[j +w][ i - w-1];
                }
                convol[l][m]=A +B-C-D;
            }
	}
       return convol;
   }
}
