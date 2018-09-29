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
public class QuantizerFactory 
{
    
    public static Quantizer getQuantizer(String type)
    {
        if(type.equals("Onehot"))
        {
            return new QuantizerIdOnehot();
        }
        else if(type.equals("Sign"))
        {
            return new QuantizerIdSign();
        }
        return new QuantizerIdOnehot();
    }
    public static Quantizer getQuantizer( QuantizerType type)
    {
        if(type.equals(QuantizerType.Onehot))
        {
            return new QuantizerIdOnehot();
        }
        else if(type.equals(QuantizerType.Sign))
        {
            return new QuantizerIdSign();
        }
        return new QuantizerIdOnehot();
    }
}
