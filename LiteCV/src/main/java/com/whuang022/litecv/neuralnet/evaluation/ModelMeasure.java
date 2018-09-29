/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.neuralnet.evaluation;

import com.whuang022.litecv.math.Matrix;
import com.whuang022.litecv.math.MatrixAPI;
import java.util.TreeMap;
import java.util.Vector;



/**
 * 模型指標量化工具
 * @author user
 */
public class ModelMeasure 
{
    //運算輔助
    private  MatrixAPI mat=new Matrix();//矩陣運算
    //資料儲存
    private double[][]confusionMat;//混淆矩陣
    private int dim=0;//類別數量
    private double[]diagArray;//對角線陣列
    private double[] colSum;//第n類的預測總數
    private double[] rowSum;//第n類的實際總數
    private double nSize=0;//實際總數(第n類的實際總數的加總)
    //
    private double []SDN;// Sum of (Diagonal(Not Class))
    private double []SRS;//Sum of (Sum of each row(Not Class)) 
    private double []SCS;//Sum of (Sum of each colm(Not Class)) 
        
    //各類別分類能力指標
    private double[]precision;//精密度陣列 預測為正的樣本中有多少預測對了
    private double[]recall;//召回率陣列 真實正的樣本有多少被預測對了
    private double[]F1;//(綜合考量 Precision與Recall)
    
    private double[]TPR;//真正類率 被模型預測為正的正樣本的比例
    private double[]TNR;//真負類率 被模型預測為負的負樣本的比例
    private double[]FPR;//假正類率 被模型預測為正的負樣本的比例
    
    //整體分類能力指標
    private double accuracy=0;//準確度 有多少比例的樣本預測對了
    private double error=0;//錯誤率 有多少比例的樣本預測錯了
    //
    private double precisionAvg=0;
    private double recallAvg=0;
    private double F1Avg=0;
    public ModelMeasure(double[][]confusionMat)
    {
        this.confusionMat=confusionMat;
        dim=confusionMat.length;
        diagArray=mat.getDiagonalArray(this.confusionMat);
        precision=new double[dim];
        recall=new double[dim];
        TPR=new double[dim];
        TNR=new double[dim];
        FPR=new double[dim];
        colSum =new double[dim];
        rowSum =new double[dim];
        F1 =new double[dim];
        SDN=new double[diagArray.length];// Sum of (Diagonal(Not Class))
        SRS=new double[rowSum.length];//Sum of (Sum of each row(Not Class)) 
        SCS=new double[colSum.length];//Sum of (Sum of each row(Not Class)) 
        
        calculateRowColSum();
        calculatePrecisionRecallF1();
        calculateTNR();
        calculateFPR();
        calculateAccuracy();
        calculateError();
        calculatePrecisionRecallF1Avg();
        /////debug
        /*
        mat.printM(diagArray);
        System.out.println();
        mat.printM(colSum);
        System.out.println();
        mat.printM(rowSum);
        System.out.println();
        mat.printM(precision);
        System.out.println();
        mat.printM(recall);
        System.out.println();
        mat.printM(F1);
        System.out.println();
        mat.printM(TPR);
        System.out.println();
        mat.printM(TNR);
        System.out.println();
        mat.printM(FPR);
        System.out.println();
        System.out.println(accuracy);
        System.out.println();
        System.out.println(error);
        */
        /////
    }
    public double getAccuracy()
    {
        return this.accuracy;
    }
    public double getError()
    {
        return this.error;
    }
    private void calculateRowColSum()
    {
        for (int i = 0; i < confusionMat.length; i++)
        {  
            int rsum=0;
            for (int j = 0; j < confusionMat[i].length; j++){                
                rsum += confusionMat[i][j];
                colSum[j] += confusionMat[i][j];
            }
            rowSum[i]=rsum;
        } 
    }
    private void  calculatePrecisionRecallF1()
    {
        for (int i = 0; i < precision.length; i++)
        { 
            precision[i]=diagArray[i]/colSum[i];
            recall[i]=diagArray[i]/rowSum[i];
            F1[i]=2*recall[i]*precision[i]/ (recall[i] + precision[i]);
        }
        TPR=recall;
    }
    private void  calculateTNR()
    {
        
        for(int i=0;i<diagArray.length;i++)
        {
            double sumNotClass=0;
            for(int j=0;j<diagArray.length;j++)
            {
                if(i!=j)
                {
                    sumNotClass+=diagArray[j];
                }
            }
            SDN[i]=sumNotClass;
        }
        for(int i=0;i<rowSum.length;i++)
        {
            double sumNotClass=0;
            for(int j=0;j<rowSum.length;j++)
            {
                if(i!=j)
                {
                    sumNotClass+=rowSum[j];
                }
            }
            SRS[i]=sumNotClass;
        }
        for(int i=0;i<TNR.length;i++)
        {
            TNR[i]=SDN[i]/SRS[i];
        }
    }
    private void  calculateFPR()
    {
       for(int i=0;i<FPR.length;i++)
       {
            FPR[i]= (colSum[i]-diagArray[i])/SRS[i];
       }
    }
    private void  calculateAccuracy()
    {
       nSize=0;
       for(int i=0;i<rowSum.length;i++)
       {
           nSize+=rowSum[i];
       }
       double correctSize=0;
       for(int i=0;i<diagArray.length;i++)
       {
           correctSize+=diagArray[i];
       }
       accuracy=correctSize/nSize;
    }
    private void  calculateError()
    {
        for(int i=0;i<SCS.length;i++)
        {  
            SCS[i]=colSum[i]-diagArray[i];
        }
        
        double uncorrectSize=0;
        for(int i=0;i<SCS.length;i++)
        {  
            uncorrectSize+=SCS[i];
          
        }
        error=uncorrectSize/nSize;
    }
    private void calculatePrecisionRecallF1Avg()
    {
        double precisionSum=0;
        double recallSum=0;
        double F1Sum=0;
        for(int i=0;i<dim;i++)
        {
            precisionSum+=precision[i];
            recallSum+=recall[i];
            F1Sum+=F1[i];
        }
        precisionAvg=precisionSum/dim;
        recallAvg=recallSum/dim;
        F1Avg=F1Sum/dim;
    }
    public double[] getPrecision()
    {
        return this.precision;
    }
    public double[] getRecall()
    {
        return this.recall;
    }
     public double[] getF1()
    {
        return this.F1;
    }
    public double getPrecisionAvg()
    {
        return this.precisionAvg;
    }
    public double geRecallAvg()
    {
        return this.recallAvg;
    }
    public double geF1Avg()
    {
        return this.F1Avg;
    }
}
