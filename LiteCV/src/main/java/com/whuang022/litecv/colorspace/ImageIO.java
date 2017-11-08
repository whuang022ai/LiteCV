/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorspace;

import java.awt.image.BufferedImage;
import java.net.URL;

/**
 *
 * @author user
 */
public interface ImageIO 
{
    public BufferedImage getBufferedImage(String path);
    public BufferedImage getBufferedImage(URL path);
}
