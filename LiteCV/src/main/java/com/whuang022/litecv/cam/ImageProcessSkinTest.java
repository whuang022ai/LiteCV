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
public class ImageProcessSkinTest 
{
    
    public static void main(String[] args) 
    {
        ImageProcessor processor=new ImageProcessorSkin2();
        new ImageProcessCameraStandard("Skin ",100,processor);
    }
}
