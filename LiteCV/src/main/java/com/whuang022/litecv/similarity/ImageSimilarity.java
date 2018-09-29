/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.similarity;

import com.whuang022.litecv.colorspace.ImageGray;

/**
 *
 * @author user
 */
public class ImageSimilarity 
{
    private final ImageGray template;
    private double size=0;
    public ImageSimilarity(ImageGray template)
    {
        this.template=template;
        this.size= (template.G.length*template.G[0].length);
    }
    public  double MSE(ImageGray src)
    {
        double error=0;
        for (int i = 0; i < template.G.length; i++) 
        {
            for (int j = 0; j <template.G[0].length; j++) 
            {
             double p=(template.G[i][j]-src.G[i][j]);
             p=p*p;
             error+=p;
            }
        }
        error/=size;
        return error;
    }
}
