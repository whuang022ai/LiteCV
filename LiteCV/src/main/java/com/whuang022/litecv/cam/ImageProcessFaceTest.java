/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.cam;

/**
 *
 * @author user
 */
public class ImageProcessFaceTest {

    public static void main(String[] args) 
    {
        ImageProcessor processor=new ImageProcessorFace4();
        new ImageProcessCameraStandard("Face ",100,processor);
    }
    
}
