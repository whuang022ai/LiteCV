
package com.whuang022.litecv.neuralnet.data;

/**
 * 訓練資料類別
 * @author whuang022
 */
public class Data 
{  
    public double[][]I;//輸入特徵
    public double[][]D;//期望輸出
    public int classID=0;//期望分類ID
    public Data (double[][]I,double[][]D)
    {
        this.I=I;
        this.D=D;
    }
    
}
