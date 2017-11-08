/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorfilter;

import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.filter.ImageFilter;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparator;

/**
 *
 * @author user
 */
public abstract interface ImageColorFilter extends ImageFilter 
{
    public ImageMatrix filter(ImageMatrix image, ImageColorSpaceType thresholdColorSpace,ImageDynamicComparator  threshold);
}
