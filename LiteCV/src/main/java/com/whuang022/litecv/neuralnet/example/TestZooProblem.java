/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.neuralnet.example;

import com.whuang022.litecv.neuralnet.active.SigmodFunction;
import com.whuang022.litecv.neuralnet.data.Data;
import com.whuang022.litecv.neuralnet.data.DataIO;
import com.whuang022.litecv.neuralnet.net.Matrix;
import com.whuang022.litecv.neuralnet.net.NeuralNetFeedforwardThreeLayer;

import com.whuang022.litecv.neuralnet.quantizer.QuantizerIdOnehot;
import java.util.ArrayList;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;

/**
 * Zoo 分類問題
 * 數據集:
 * http://archive.ics.uci.edu/ml/machine-learning-databases/zoo/zoo.data
 * (輸入數據已經預先把最後一欄1~7的分類轉換成長度為7位的one hot 編碼)
 * @author whuang022
 */
public class TestZooProblem 
{
    public static void main(String[] args) 
    {
        Quantizer q=new  QuantizerIdOnehot();
        ArrayList<Data> datas=DataIO.getDataListCSV("zoo.csv", false, ",", 7,QuantizerType.Onehot);
        Matrix m=new Matrix();
        NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(16,5,7,0.8,new SigmodFunction(),true,false,QuantizerType.Onehot);
        nn.BatchCycle(datas, true, 100000, 0.6,true);
    }  
}
