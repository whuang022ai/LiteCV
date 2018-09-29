package com.whuang022.litecv.math;

import java.util.ArrayList;

/**
 *基本統計方法
 * @author lovedoglion5
 */

public class Statistics implements StatisticsAPI
{
    private MatrixAPI matrix;
    public Statistics()
    {
        matrix=new Matrix();
    }
    @Override
    public double avg(double[] x) 
    {
        double sum=0,avg=0;
        for(int i=0 ; i<x.length ; i++)
        {
            sum+=x[i];
        }
        avg=sum/x.length;
        return avg;
    }

    @Override
    public double variance(double[] x,double avg) 
    {
        double sum=0,variance=0;
        for(int i=0 ; i<x.length ; i++)
        {
            sum+=(x[i]-avg)*(x[i]-avg);
        } 
        variance=sum/(x.length-1);
        return variance;
    }

    @Override//陣列樣本標準差
    public double standardDeviation(double variance) 
    {
        double standardDeviation=Math.sqrt(variance);
        return standardDeviation;
    }

    @Override
    public double cov(double[] x,double xAvg, double[] y,double yAvg) 
    {
        double sum=0,covariance=0;
        for(int i=0 ; i<x.length ; i++)
        {
            sum+=(x[i]-xAvg)*(y[i]-yAvg);
        } 
        covariance=sum/(x.length-1);
        return covariance;
    }
    @Override
    public double[] getAvgArray(ArrayList<double[]> dataList)
    {
        double[] avgArray=new double[dataList.size()];
        for(int i=0;i<dataList.size();i++)
        {
            double[] x=dataList.get(i);
            double xAvg=avg(x);
            avgArray[i]=xAvg;
        }
        return avgArray;
    }
    
    @Override
    public double[][] covMat(ArrayList<double[]> xList) 
    {
        double[][] covMat = new double[xList.size()][xList.size()];
        double[] xAvgArray=new double[xList.size()];
        for(int i=0;i<xList.size();i++)
        {
            double[] x=xList.get(i);
            double xAvg=avg(x);
            xAvgArray[i]=xAvg;
        }
        for (int i=0;i<xList.size();i++)
        {
            for(int j=0;j<=i;j++)
            {
                if(i==j)
                {
                    double[] x=xList.get(i);
                    double variance = variance(x,xAvgArray[i]);
                    covMat[i][j]= variance;
                }
                else//兩兩不同的情況
                {
                    double[] x=xList.get(i);
                    double[] y=xList.get(j);
                    double cov=cov(x, xAvgArray[i] ,y, xAvgArray[j]);//計算共變異數
                    covMat[i][j]=cov;
                    covMat[j][i]=cov;
                }    
            }
        }
        return covMat;
    }

    @Override
    public double[][] covMat2D(double[][] dataMat) 
    {
        ArrayList<double[]> cols=matrix.getMatrixToList(dataMat, MatrixDirect.column);//矩陣拆成兩個維度
        return  covMat(cols);//返回共變異數矩陣
    }

    
    
}
