
package com.whuang022.litecv.neuralnet.example;

import com.whuang022.litecv.neuralnet.net.NeuralNetFeedforwardThreeLayer;
import com.whuang022.litecv.neuralnet.active.SigmodFunction;
import com.whuang022.litecv.neuralnet.data.DataIO;
import com.whuang022.litecv.neuralnet.data.Data;
import com.whuang022.litecv.neuralnet.modle.NeuralNetFeedforwardThreeLayerModle;


import com.whuang022.litecv.neuralnet.quantizer.QuantizerIdOnehot;
import java.util.ArrayList;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerFactory;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;
import java.util.Arrays;

/**
 * Iris 二元分類問題
 * 原數據集:https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data
 * (取原數據前兩類別,已將原數據的末欄轉為2位的one hot 編碼) 左到右編ID
 */
public class TestIrisProblem 
{
    static double [][]I1={{4.6,3.4,1.4,0.3}};//class 1 (0,1)
    static double [][]I0={{5.8,2.7,3.9,1.2}};//class 0 (1,0)
    public static void main(String[] args) 
    {
        //trainModle();
        testModle();
    }
    public static void testModle()//載入模型並判讀
    {
       NeuralNetFeedforwardThreeLayerModle M=new NeuralNetFeedforwardThreeLayerModle("Iris_Modle_2D.xml");
       NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(M);
       double [][] output=nn.ForwardPropagation(I1);
       Quantizer q=QuantizerFactory.getQuantizer(QuantizerType.Onehot);
       System.out.println(q.quantizer(output[0]));
       double [][] output2=nn.ForwardPropagation(I0);
       System.out.println(q.quantizer(output2[0]));
    }
    public static void trainModle()//訓練模型並保存
    {
        ArrayList<Data> datas=DataIO.getDataListCSV("Iris-setosa.csv", false, ",", 2, QuantizerType.Onehot);
        NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(4,6,2,0.7,new SigmodFunction(),false,false,QuantizerType.Onehot);
        nn.BatchCycle(datas, true, 10000, 0.5,true);
        NeuralNetFeedforwardThreeLayerModle M=nn.getCurrentModle();
        M.saveXML("Iris_Modle_2D.xml");
    }
}
