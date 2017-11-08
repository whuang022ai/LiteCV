/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.math;

/**
 *
 * @author user
 */
public class ImageMatrix 
{
    
    public static double[][] product(double[][] x, double[][] y) //矩陣相乘
    {
        double[][] product=new double[x.length][y[0].length];
        for(int i=0;i<product.length;i++)
        {
            for(int j=0;j<product[0].length;j++)
            {
                for(int k=0;k<y.length;k++)
                {
                    product[i][j] += x[i][k]*y[k][j];
                }
            }
        }
        return product;     
    }
     public static int[][] product(int[][] x,int[][] y) //矩陣相乘
    {
        int[][] product=new int[x.length][y[0].length];
        for(int i=0;i<product.length;i++)
        {
            for(int j=0;j<product[0].length;j++)
            {
                for(int k=0;k<y.length;k++)
                {
                    product[i][j] += x[i][k]*y[k][j];
                }
            }
        }
        return product;     
    }
      public static int[][] mul(int[][] x,int[][] y) //逐點相乘
    {
        int[][] product=new int[x.length][x[0].length];
        for(int i=0;i<product.length;i++)
        {
            for(int j=0;j<product[0].length;j++)
            {
                
                    product[i][j] = x[i][j]*y[i][j];
                
            }
        }
        return product;     
    }
     public static int[][] sub(int[][] x, int[][] y) //矩陣相減
    {
        int[][] sum =  new int[x.length][x[0].length];
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                sum[i][j]=x[i][j]-y[i][j];
            }
        }
        return sum;
    }
      public static int[][] sum(int[][] x, int[][] y) //矩陣相加
    {
        int[][] sum =  new int[x.length][x[0].length];
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                sum[i][j]=x[i][j]+y[i][j];
            }
        }
        return sum;
    }
    public static double[][]transposeMatrix(double[][] A)//轉置矩陣
    {
       double [][]output=new double[A[0].length][A.length];
       for(int i=0; i<A.length; i++)
       {
           for(int j=0; j<A[0].length; j++)
           {
                output[j][i] = A[i][j];
           }
       }
        return output;
    }
    public double getDeterminant3Dim(double[][] A) //三階行列式值(快速算法)
    {
       return (A[0][0] * (A[1][1]*A[2][2]-A[1][2]*A[2][1])-A[1][0] * (A[0][1]*A[2][2]-A[0][2]*A[2][1])+A[2][0] * (A[0][1]*A[1][2]-A[0][2]*A[1][1]) );
    }
      
    public double getDeterminant2Dim(double[][] A)//二階行列式值(快速算法)
    {
       return (A[0][0]*A[1][1] - A[0][1]*A[1][0]);
    }
    public double[][]getMinorMatrix(double [][]A,int I,int J)//取一矩陣第I高 第J 寬 的 子行列式
    {
       double [][]Minor=new double[A.length-1][A[0].length-1];
       int m=0;
       for(int i=0;i<A.length;i++)
       {
          if(i!=I)//去掉第I行
          {
              int n=0;
              for(int j=0;j<A[0].length;j++)
              {
                 if(j!=J)//去掉第J列
                 {
                    Minor[m][n]=A[i][j];
                    n++;
                 }
              }
              m++;
          }
       }
       return Minor;
    }
    public double getDeterminantNDim(double[][] A)//N階行列式值(遞迴)
    {
        switch (A.length) 
        {
        //BasCase 1
            case 2:
                return getDeterminant2Dim(A);
        //BasCase 2
            case 3:
                return getDeterminant3Dim(A);
            default:
                double det=0;
                for(int i=0;i<A.length;i++)
                {
                    double a=A[i][0];
                    int I=i;
                    int J=0;
                    double[][]minor=getMinorMatrix(A,I,J);
                    det+=a*Math.pow(-1,i)*getDeterminantNDim(minor);
                }
                return det;
        }
       
    }
}
