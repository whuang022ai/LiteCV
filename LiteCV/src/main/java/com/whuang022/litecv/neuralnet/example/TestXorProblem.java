package com.whuang022.litecv.neuralnet.example;

import com.whuang022.litecv.neuralnet.net.NeuralNetFeedforwardThreeLayer;
import com.whuang022.litecv.neuralnet.active.HyperbolicTangentFuction;
import com.whuang022.litecv.neuralnet.data.Data;
import java.util.ArrayList;
import com.whuang022.litecv.neuralnet.modle.NeuralNetFeedforwardThreeLayerModle;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerFactory;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;


/**
 * XOR 問題
 * 將原本0,1問題轉為-1,0(1) & 0,-1(0)的輸出
 * 使用tanh作為輸出
 * @author whuang022
 */
public class TestXorProblem 
{
    static double[][]I11={
                    {1,1}
                    };
    static   double[][]D0={
                    {0,-1},
                  };
   static       double[][]D1={
                    {-1,0},
                  };
    static     double[][]I10={
                    {1,0}
                  };
    static     double[][]I01={
                    {0,1},
                  };
    static     double[][]I00={
                    {0,0},
                  };
    public static void main(String[] args) 
    {
        trainModle();
       // testModle();
    }
    public static void testModle()//載入模型並判讀
    {
       NeuralNetFeedforwardThreeLayerModle M=new NeuralNetFeedforwardThreeLayerModle("XOR_Modle.xml");
       NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(M);
       double [][] output=nn.ForwardPropagation(I01);
       Quantizer q=QuantizerFactory.getQuantizer(QuantizerType.Onehot);
       System.out.println(q.quantizer(output[0]));
    }
    public static void trainModle()//訓練模型並保存
    {
        NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(2,3,2,0.2,new HyperbolicTangentFuction(),false,false,QuantizerType.Onehot);
        ArrayList<Data> trainSet=new ArrayList<>();
        
        Data d1=new Data(I00,D0);
        d1.classID=0;
        trainSet.add(d1);

        Data d2=new Data(I01,D1);
        d2.classID=1;
        trainSet.add(d2);
        
        Data d3=new Data(I10,D1);
        d3.classID=1;
        trainSet.add(d3);
        
        Data d4=new Data(I11,D0);
        d4.classID=0;
        trainSet.add(d4);
        
        nn.BatchCycle(trainSet, true, 10000, 0.5, true);
        NeuralNetFeedforwardThreeLayerModle M=nn.getCurrentModle();
        M.saveXML("XOR_Modle.xml");
    }
   
}
