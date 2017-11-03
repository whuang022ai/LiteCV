/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.filter;

import com.whuang022.litecv.kernel.ImageMedianFilterKernel;

/**
 *
 * @author user
 */
public class ImageMedianFilter extends ImageSortFilter{

    @Override
    public int[][] filter(int[][] image, ImageFilterConfig config) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] filter(int[][] image) {
       return super.sortFilter(image, new ImageMedianFilterKernel());
    }
    
}
