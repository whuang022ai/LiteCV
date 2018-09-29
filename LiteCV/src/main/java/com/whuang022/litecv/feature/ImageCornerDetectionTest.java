/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.feature;

import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageMatrix;

/**
 *
 * @author user
 */
public class ImageCornerDetectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ImageCornerDetection filter=new ImageCornerDetection();
        
        String path="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/China_go2.JPG/220px-China_go2.JPG";//        String path="C:\\Users\\user\\Desktop\\hsv.jpg";

        ImageMatrix image,image2 =null;
        ImageColorReader reader=new ImageColorReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceGray);
        image2=reader.imageRead(path, ImageColorSpaceType.ColorSpaceRGB);
        ImageMatrix out=filter.getHarris(image,image2, 15,0,0);
        out.display();
    }
    
}
