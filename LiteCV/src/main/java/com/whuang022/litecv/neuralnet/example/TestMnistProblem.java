
package com.whuang022.litecv.neuralnet.example;

import com.whuang022.litecv.neuralnet.net.NeuralNetFeedforwardThreeLayer;
import com.whuang022.litecv.neuralnet.active.SigmodFunction;
import com.whuang022.litecv.neuralnet.data.DataIO;
import com.whuang022.litecv.neuralnet.data.Data;

import com.whuang022.litecv.neuralnet.quantizer.QuantizerIdOnehot;
import java.util.ArrayList;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;

/**
 * Mnist 8*8大小的手寫字辨識0~9
 * 原數據集:http://yann.lecun.com/exdb/mnist/
 * (已將輸入數據末10位設為0~9的one hot 編碼,前面64位為灰度的機率值)
 * @author whuang022
 */
public class TestMnistProblem 
{
    
    public static void main(String[] args) 
    {
        Quantizer q=new  QuantizerIdOnehot();
        ArrayList<Data> datas=DataIO.getDataListCSV("mnist.csv", false, ",", 10,QuantizerType.Onehot);
        NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(64,12,10,0.5,new SigmodFunction(),true,false,QuantizerType.Onehot);
        nn.BatchCycle(datas, true, 1250000, 0.01,false);
        nn.getCurrentModle().saveXML("mnist.XML");
    }
    
}
