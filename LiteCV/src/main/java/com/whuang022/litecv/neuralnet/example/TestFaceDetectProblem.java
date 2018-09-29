/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.neuralnet.example;

import com.whuang022.litecv.neuralnet.active.HyperbolicTangentFuction;

import com.whuang022.litecv.neuralnet.data.Data;
import com.whuang022.litecv.neuralnet.data.DataIO;
import com.whuang022.litecv.neuralnet.net.NeuralNetFeedforwardThreeLayer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerIdSign;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class TestFaceDetectProblem 
{
    
    public static void main(String[] args) 
    {
        ArrayList<Data> datas=DataIO.getDataListCSV("face.csv", false, ",", 1,QuantizerType.Sign);
        NeuralNetFeedforwardThreeLayer nn=new NeuralNetFeedforwardThreeLayer(64,15,1,0.2,new HyperbolicTangentFuction(),false,true,QuantizerType.Sign);
        //nn.QLayer=new QuantizerIdSign();
        nn.BatchCycle(datas, true, 200000, 0.3,true);
        nn.getCurrentModle().saveXML("Face2.XML");
    }
}
