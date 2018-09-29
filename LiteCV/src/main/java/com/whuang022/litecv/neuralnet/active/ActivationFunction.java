
package com.whuang022.litecv.neuralnet.active;

/**
 * 活化函數介面
 * @author whuang022
 */
public interface ActivationFunction 
{
    public double fuction(double x);
    public double fuctionDiv(double y);
    public ActivationFunctionType getType();
}
