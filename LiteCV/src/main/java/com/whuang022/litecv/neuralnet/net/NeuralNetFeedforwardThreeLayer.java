package com.whuang022.litecv.neuralnet.net;

import com.whuang022.litecv.neuralnet.active.ActivationFunction;
import com.whuang022.litecv.neuralnet.active.ActivationFunctionType;
import com.whuang022.litecv.neuralnet.active.HyperbolicTangentFuction;
import com.whuang022.litecv.neuralnet.active.SigmodFunction;
import com.whuang022.litecv.neuralnet.data.Data;
import com.whuang022.litecv.neuralnet.evaluation.ModelMeasure;
import java.util.ArrayList;
import java.util.Random;
import com.whuang022.litecv.neuralnet.modle.NeuralNetFeedforwardThreeLayerModle;

import com.whuang022.litecv.neuralnet.quantizer.QuantizerIdOnehot;
import java.util.Arrays;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerFactory;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;

/**
 *  三層神經網路
 * 
 * @author whuang022
 */
public class NeuralNetFeedforwardThreeLayer 
{  
    private Quantizer QLayer=new  QuantizerIdOnehot();
    private Matrix  m;
    
    //各層維度
    private int I=0;
    private int H=0;
    private int O=0;
    //超參數
    private double learningRate=0.7;
    private boolean inputLayerNormal=false;//是否壓縮輸入層輸入總和=1(輸入層維度高時使用)
    private boolean hiddenLayerNormal=false;//是否壓縮隱藏層輸入總和=1(輸入層維度高時使用)
    //各層神經元矩陣
    private double [][]InputLayer;
    private double [][]HiddenLayer;
    private double [][]OutputLayer;
    private double [][]Desire_Output;
    //權重矩陣
    private double [][]WeightsIH;
    private double[][] WeightsHO;
    //偏權矩陣
    private double [][]BiasHiddenLayer;
    private double [][]BiasOutputLayer;
    //誤差敏感度矩陣
    private double [][]dHiddenLayer;
    private double [][]dOutputLayer;
    //權重更新矩陣
    private double [][]dWeightsIH;
    private double [][]dWeightsHO;
    private double [][]dBiasHiddenLayer;
    private double [][]dBiasOutputLayer;
    
    private double [][]confusionMat;//垂直方向實際值 水平方向預測值
    public NeuralNetFeedforwardThreeLayer(NeuralNetFeedforwardThreeLayerModle modle)
    {
        I=modle.getI();
        H=modle.getH();
        O=modle.getO();
        inputLayerNormal=modle.getInputLayerNormal();
        WeightsIH=modle.getWeightsIH();
        WeightsHO=modle.getWeightsHO();
        BiasHiddenLayer=modle.getBiasHiddenLayer();
        BiasOutputLayer=modle.getBiasOutputLayer();
        ActivationFunctionType type=modle.getActivationFunctionType();
        this.QLayer=QuantizerFactory.getQuantizer(modle.getQuantizerType());
         hiddenLayerNormal=modle.getHiddenLayerNormal();
       // System.out.println("XXX"+type);
        m=new Matrix ();//矩陣運算物件
        if(type.equals(ActivationFunctionType.SigmodFunction))
        {
            m.act=new SigmodFunction();
        }
        else if(type.equals(ActivationFunctionType.HyperbolicTangentFuction))
        {
            //System.out.println("XCCX"+type);
            m.act=new HyperbolicTangentFuction();
          //  System.out.println("XCCXCCCVV"+m.act.getType());
        }
        if(O>1)
        {
          confusionMat=new double[O][O];  
        }
        else
        {
            confusionMat=new double[1][1];  
        }
        
    }
    
    public NeuralNetFeedforwardThreeLayer(int inputDim,int hiddenDim,int outputDim,double learningRate,ActivationFunction act,boolean inputLayerNormal,boolean hiddenLayerNormal,QuantizerType QT)
    {
        this.inputLayerNormal=inputLayerNormal;
        this.hiddenLayerNormal=hiddenLayerNormal;
        this.learningRate=learningRate;
        I=inputDim;
        H=hiddenDim;
        O=outputDim;
        
        WeightsIH=new double[I][H];             //輸入層到隱藏層權重
        dWeightsIH=new double[I][H];            //輸入層到隱藏層權重修正量
        
        BiasHiddenLayer=new double[1][H];       //隱藏層偏權值
        dBiasHiddenLayer=new double[1][H];      //隱藏層偏權值修正量
        
        dHiddenLayer=new double[1][H];         //隱藏層誤差敏感度
        dOutputLayer=new double[1][O];         //輸出層誤差敏感度
        
        WeightsHO=new double[H][O];            //隱藏層到輸出層權重
        dWeightsHO=new double[H][O];           //隱藏層到輸出層權重修正量
        
        BiasOutputLayer=new double[1][O];      //輸出層偏權值
        dBiasOutputLayer=new double[1][O];     //輸出層偏權值修正量

        InputLayer=new double[1][I];          //輸入層
        HiddenLayer=new double[1][H];         //隱藏層
        OutputLayer=new double[1][O];         //輸出層
        Desire_Output=new double[1][O];       //期望輸出
        
        m=new Matrix ();//矩陣運算物件
        m.act=act;//設定函數
        
        initWeight();
        if(O>1)
        {
          confusionMat=new double[O][O];  
        }
        else
        {
            confusionMat=new double[2][2];  
        }
        this.QLayer=QuantizerFactory.getQuantizer(QT);
    }
    private double getGaussian(double mean, double variance)//高斯分布亂數
    {
        return mean + new Random().nextGaussian() * variance;
    }
    private void initWeight()//初始化權值
    {
        for(int m=0;m<WeightsIH.length;m++)
        {
            for(int n=0;n<WeightsIH[0].length;n++)
            {
                WeightsIH[m][n]=0.1*getGaussian(I,H);
            }
        }
        for(int m=0;m< WeightsHO.length;m++)
        {
            for(int n=0;n< WeightsHO[0].length;n++)
            {
                WeightsHO[m][n]=0.1*getGaussian(H,O);
            }
        }
        for(int m=0;m<BiasHiddenLayer.length;m++)
        {
            for(int n=0;n<BiasHiddenLayer[0].length;n++)
           {
              BiasHiddenLayer[m][n]=0.1*getGaussian(1,H);
           }
        }
        for(int m=0;m<BiasOutputLayer.length;m++)
        {
            for(int n=0;n<BiasOutputLayer[0].length;n++)
           {
              BiasOutputLayer[m][n]=0.1*getGaussian(1,O);
           }
        }
    }
    public void BatchCycle(ArrayList<Data> dataList,boolean printLog,int batchTimes,double batchRate,boolean avgG)//批次訓練循環
    {
        double sizeAll=dataList.size();
        for(int i=0;i<batchTimes;i++)
        {
            int sizeTrain=(int)(sizeAll*batchRate);
            int []IDList=new int[sizeTrain];
            for(int j=0;j<IDList.length;j++)
            {
                Random ran = new Random();
                IDList[j]=ran.nextInt(dataList.size());
                ran =null;
            }
            SingleBatchCycle(dataList, IDList,printLog,avgG);
            
        }
    }
    public void SingleBatchCycle(ArrayList<Data> dataList,int[] IDList,boolean printLog,boolean avgG)//訓練數據源/訓練名單/是否列印log
    {
       if(printLog)
        {
            System.out.println("\n----BatchCycle----\n");
            
        }
       //權重調整總和
       double[][]dWeightsHOSum=new double[H][O];
       double[][] dBiasOutputLayerSum=new double[1][O];
       double[][] dWeightsIHSum=new double[I][H];
       double[][] dBiasHiddenLayerSum=new double[1][H];
       //一輪Batch訓練
       for(int i=0;i<IDList.length;i++)
       {
           Data trainData=dataList.get(IDList[i]);//取出訓練物件
           
           Propagation( trainData, printLog);//走一步訓練
           //累加權重調整
           dWeightsHOSum=m.sum(dWeightsHOSum, dWeightsHO);
           dWeightsIHSum=m.sum(dWeightsIHSum,dWeightsIH);
           dBiasOutputLayerSum=m.sum(dBiasOutputLayerSum,dBiasOutputLayer);
           dBiasHiddenLayerSum=m.sum(dBiasHiddenLayerSum, dBiasHiddenLayer);
           //
           int outputClass=QLayer.getID( OutputLayer[0]);//輸出層量化
           confusionMat[trainData.classID][outputClass]++;//混淆矩陣對應位置紀錄
       }
       //平均梯度
       if(avgG)
       {
            double n=IDList.length;
            n=1/n;
            dWeightsHOSum=m.scalarProduct(dWeightsHOSum, n);
            dWeightsIHSum=m.scalarProduct(dWeightsIHSum, n);
            dBiasOutputLayerSum=m.scalarProduct(dBiasOutputLayerSum, n);
            dBiasHiddenLayerSum=m.scalarProduct(dBiasHiddenLayerSum,n);
       }
       //更新權重
        WeightsHO=m.sum(WeightsHO,dWeightsHOSum);
        BiasOutputLayer=m.sum(BiasOutputLayer, dBiasOutputLayerSum);
        WeightsIH=m.sum(WeightsIH,dWeightsIHSum);
        BiasHiddenLayer=m.sum(BiasHiddenLayer, dBiasHiddenLayerSum);
        //
        ModelMeasure measure=new ModelMeasure(confusionMat);
        if(printLog)
        {
            System.out.println("----BatchCycleInfo----");
            System.out.println("Accuracy="+measure.getAccuracy());
            System.out.println("Recall="+Arrays.toString(measure.getRecall()));
            System.out.println("Average Recall="+measure.geRecallAvg());
            System.out.println("----------------------");
        }
        
        measure=null;
    }

    private int RandomInt(int min, int max) 
    {
        Random rand=new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    private double Propagation(Data trainData,boolean printLog)//一個訓練步
    {
        ForwardPropagation(trainData.I);
        double sumError=BackwardPropagation(trainData.D);
        if(printLog)
        {
            PrintLog(trainData,sumError);  
        }
        return sumError;
    }
    public NeuralNetFeedforwardThreeLayerModle getCurrentModle()
    {
      return new NeuralNetFeedforwardThreeLayerModle( I, H,O,m.act.getType(),WeightsIH, WeightsHO,BiasHiddenLayer,BiasOutputLayer,inputLayerNormal, hiddenLayerNormal,QLayer.getType());
    }
    private void PrintLog(Data trainData,double sumError)//印出訓練過程
    {/*
        System.out.println("-----------");
        System.out.println("Input:");
        m.printM(trainData.I);
        System.out.println("Neural Network Output:");
        m.printM(OutputLayer);
        System.out.println("Desire Output::");
        m.printM(trainData.D);
        System.out.println("Total Abstract Error:");
        System.out.println(sumError);
 */
    }
    private double [][]NormalLize0to1(double [][]input)
    {
        double sum=0;
        for(int i=0;i<input[0].length;i++)
        {
            sum+=input[0][i];
        }
         for(int j=0;j<input[0].length;j++)
        {
            input[0][j]=input[0][j]/sum;
        }
        return input;
    }
    public double[][] ForwardPropagation(double [][]input)//前向傳播
    {
        if(inputLayerNormal)
        {
            InputLayer=NormalLize0to1(input);
        }
        InputLayer=input;
        //計算Hidden Layer     
        double[][] dot0=m.product(InputLayer, WeightsIH);
        double [][]sum0=m.sub(dot0, BiasHiddenLayer);
        //sum0=NormalLize0to1( sum0);
         if(hiddenLayerNormal)
        {
            sum0=NormalLize0to1( sum0);
        }
        HiddenLayer=m.fuctionMatrix(sum0);
        //計算Output Layer
        double[][] dot1=m.product(HiddenLayer, WeightsHO);
        double [][]sum1=m.sub(dot1, BiasOutputLayer);
        OutputLayer=m.fuctionMatrix(sum1);
        return OutputLayer;
    }
    public double BackwardPropagation(double [][]desire)//後向傳播
    {
        //計算Output Layer 誤差敏感度
        double[][]errorO=m.sub(desire, OutputLayer);//誤差=期望-輸出
        double sumE=m.elementAbsSum(errorO);//計算總誤差並輸出
        double [][]divY=m.fuctionMatrixDiv(OutputLayer);//偏微分輸出
        dOutputLayer=m.productHadamard(divY, errorO);//Output Layer 誤差敏感度=(偏微分HiddenLayer 哈達瑪乘積 輸入本層的誤差errorO)
        //計算Hidden Layer 誤差敏感度
        double [][]WeightsOH=m.transposeMatrix(WeightsHO);
        double[][]errorH=m.product(dOutputLayer, WeightsOH);//輸入本層的誤差=(Output Layer 誤差敏感度r dot WeightsOH)
        double [][]divH=m.fuctionMatrixDiv(HiddenLayer);
        dHiddenLayer=m.productHadamard(divH, errorH);//HiddenLayer 誤差敏感度=(偏微分HiddenLayer 哈達瑪乘積 輸入本層的誤差errorH)
        //隱藏層到輸出層權重修正量
        dWeightsHO=m.getRowColumTHadamardProduction(HiddenLayer,dOutputLayer,learningRate);
        //輸入層到隱藏層權重修正量
        dWeightsIH=m.getRowColumTHadamardProduction(InputLayer,dHiddenLayer,learningRate);
        //輸出層偏權修正量
        dBiasOutputLayer=m.scalarProduct(dOutputLayer, -learningRate);
        //隱藏層偏權修正量
        dBiasHiddenLayer=m.scalarProduct(dHiddenLayer, -learningRate);
        return sumE;
    }
}
