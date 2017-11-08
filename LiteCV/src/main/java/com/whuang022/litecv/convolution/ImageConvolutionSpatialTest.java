/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.convolution;

/**
 *
 * @author user
 */
public class ImageConvolutionSpatialTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          int [][] image={
                            {17,24,1,8,15,3,1},
                            {23,5,7,14,16,2,1},
                            {4,6,13,20,22,4,1},
                            {10,12,19,21,3,7,1},
                            {11,18,25,2,9,9,1},
                            {11,18,25,2,9,9,1},
                            {11,18,25,2,9,9,1},
                      };
          printM(image) ;
          int [][] kernal7={{1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1},
                         {1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
          int [][] kernal3={{1,1,1},{1,1,1},{1,1,1}
                         };
        ImageSpatialScan sp=new ImageSpatialScan();
        
        int [][] out=sp.scan(image, kernal3);
        System.out.println();
         printM(out) ;
    }
     public static  void printM(int[][] x) //印出矩陣
    {
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                System.out.print(x[i][j]+" ");
            }
            System.out.println();
        }     
    }
}
