
package com.whuang022.litecv.neuralnet.active;

/**
 * HyperbolicTangent 類別
 * @author whuang022
 */
public class HyperbolicTangentFuction implements ActivationFunction
{

    @Override
    public double fuction(double x) 
    {
       return Math.tanh(x);
    }

    @Override
    public double fuctionDiv(double y) 
    {
	return 1 - (y * y);
    }

    @Override
    public ActivationFunctionType getType() {
       return ActivationFunctionType.HyperbolicTangentFuction;
    }

}
