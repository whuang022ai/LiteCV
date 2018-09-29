
package com.whuang022.litecv.neuralnet.example;

import com.whuang022.litecv.neuralnet.net.NeuralNetFeedforwardThreeLayer;

import com.whuang022.litecv.neuralnet.active.SigmodFunction;
import com.whuang022.litecv.neuralnet.data.DataIO;
import com.whuang022.litecv.neuralnet.data.Data;
import com.whuang022.litecv.neuralnet.modle.NeuralNetFeedforwardThreeLayerModle;
import java.util.ArrayList;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerFactory;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;

/**
 * Iris 三元分類問題
 * 原數據集:https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data
 * (已將原數據的末欄轉為3位的one hot 編碼)
 * @author whuang022
 */
public class TestIrisProblem2 
{
    static double [][]I0={{4.6,3.4,1.4,0.3}}; //class 0 (1,0,0)
    static double [][]I2={{5,2,3.5,1}};      //class 2 (0,0,1)
    static double [][]I1={{6.3,3.4,5.6,2.4}}; //class 1 (0,1,0)
    
    public static void main(String[] args) 
    {
        //trainModle();
        testModle();
    }
    public static void trainModle()
    {
        ArrayList<Data> datas=DataIO.getDataListCSV("Iris-setosa2.csv", false, ",", 3,QuantizerType.Onehot);
        NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(4,6,3,0.2,new SigmodFunction(),false,false,QuantizerType.Onehot);
        nn.BatchCycle(datas, true, 10000, 0.7,true);
        nn.getCurrentModle().saveXML("Iris_Modle_3D.xml");
    }
    public static void testModle()
    {
       NeuralNetFeedforwardThreeLayerModle M=new NeuralNetFeedforwardThreeLayerModle("Iris_Modle_3D.xml");
       NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(M);
       double [][] output=nn.ForwardPropagation(I0);
       Quantizer q=QuantizerFactory.getQuantizer(QuantizerType.Onehot);
       System.out.println(q.quantizer(output[0]));
       double [][] output2=nn.ForwardPropagation(I1);
       System.out.println(q.quantizer(output2[0]));
       double [][] output3=nn.ForwardPropagation(I2);
       System.out.println(q.quantizer(output3[0]));
    }
    
}
