/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.paint;

import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;

/**
 *
 * @author user
 */
public class ImagePainterTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="https://upload.wikimedia.org/wikipedia/commons/2/2a/RGB_24bits_palette_R85.png";
        ImageMatrix image =null;
        ImageColorReader reader=new ImageColorReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceRGB);
       
        ImagePainter.drawBox(50, 50,100, 100, (ImageRGB) image); 
        image.display();
    }
    
}
