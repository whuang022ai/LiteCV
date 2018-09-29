/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.cornea;

import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.thresholdDynamic.ImageDynamicComparator;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public interface ImageCornea 
{
    public  ImageMatrix imageRead(BufferedImage src,ImageColorSpaceType dst,ImageDynamicComparator thresholder);
    public  ImageMatrix imageRead(String path,ImageColorSpaceType dst,ImageDynamicComparator thresholder);
    public  BufferedImage imageReadBufferedImage(BufferedImage src,ImageColorSpaceType dst, ImageDynamicComparator thresholder);
    public  BufferedImage imageReadBufferedImage(String path,ImageColorSpaceType dst, ImageDynamicComparator thresholder);
}
