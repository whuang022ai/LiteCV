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
public interface Quantizer 
{
    public int quantizer(double[] inputVector);
    public int getID(double[] inputVector);
    public QuantizerType getType();
}
