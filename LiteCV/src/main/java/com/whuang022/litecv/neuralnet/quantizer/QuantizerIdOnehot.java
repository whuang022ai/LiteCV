/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.neuralnet.quantizer;

import com.whuang022.litecv.math.Array;

/**
 *
 * @author user
 */
public class QuantizerIdOnehot implements Quantizer{
   @Override
    public int getID(double[] inputVector) 
    {
        return quantizer(inputVector);
    }

    @Override
    public int quantizer(double[] inputVector) {
       return Array.getMaxIndex(inputVector);
    }
    @Override
    public QuantizerType getType() {
        return QuantizerType.Onehot;
    }

    
}
