
package com.whuang022.litecv.neuralnet.active;

/**
 * SigmodFunction類別
 * @author whuang022
 */
public class SigmodFunction implements ActivationFunction{

    @Override
    public double fuction(double x) 
    {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public double fuctionDiv(double y) 
    {
        return y*(1-y);
    }

    @Override
    public ActivationFunctionType getType() {
        return ActivationFunctionType.SigmodFunction;
    }
    
}
