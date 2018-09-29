/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.neuralnet.modle;

import com.whuang022.litecv.neuralnet.active.ActivationFunctionType;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author user
 */
public class ModleTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        //SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy", Locale.US);
        //System.out.println(sdf.format(new Date()));
        //3 2 2
        double [][]WIO={{1,8},{3,8},{5,6}};
        double [][]WHO={{1,2},{3,4}};
        double [][]BH={{-1,-1}};
        double [][]BO={{-2,-2}};
        NeuralNetFeedforwardThreeLayerModle m=new NeuralNetFeedforwardThreeLayerModle(3,2,2,ActivationFunctionType.SigmodFunction,WIO,WHO,BH,BO,true,true,QuantizerType.Onehot);
         m.saveXML("C:\\Users\\user\\Desktop\\新增資料夾 (3)\\test.xml");
        m.loadXML("C:\\Users\\user\\Desktop\\新增資料夾 (3)\\test.xml");
       // m.saveXML("C:\\Users\\user\\Desktop\\新增資料夾 (3)\\test.xml");
    }
    
}
