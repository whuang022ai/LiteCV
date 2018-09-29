package com.whuang022.litecv.math;

import java.util.ArrayList;

/**
 *  統計計算API
 * 
 * @author lovedoglion5
 */
public interface StatisticsAPI 
{
    public double avg(double[] x);//平均值
    public double variance(double[] x,double avg);//變異數
    public double standardDeviation(double variance);//標準差
    public double cov(double[] x,double xAvg, double[] y,double yAvg);//共變異數
    public double [][]covMat(ArrayList <double[]> xList);//共變異數矩陣 
    public double [][]covMat2D(double[][] dataMat);//取得兩變數的共變異矩陣
    public double[]getAvgArray(ArrayList<double[]> dataList);
    
}
