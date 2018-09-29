/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorspace;

import java.io.Serializable;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class ImageYCbCr  extends ImageVisual implements ImageMatrix ,Serializable 
{
    static org.slf4j.Logger logger = LoggerFactory.getLogger(ImageRGB.class);
    public double [][]Y;
    public double [][]Cb;
    public double [][]Cr;
    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(String path, String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageColorSpaceType getType() {
        return ImageColorSpaceType.ColorSpaceYCbCr;
    }
    
}
