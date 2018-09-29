/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.neuralnet.quantizer;

/**
 *
 * @author user
 */
public class QuantizerIdSign implements Quantizer{

 @Override
    public int getID(double[] inputVector) {
        return  quantizer(inputVector);
    }

    @Override
    public int quantizer(double[] inputVector) {
        for(int i=0;i<inputVector.length;i++)
        {
            if(inputVector[i]<0)
            {
                return 0;
            }
        }
        return 1;
    }

     @Override
    public QuantizerType getType() {
        return QuantizerType.Sign;
    }
    
}
