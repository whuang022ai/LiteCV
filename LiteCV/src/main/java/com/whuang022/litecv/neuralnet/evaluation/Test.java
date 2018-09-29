/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.neuralnet.evaluation;

/**
 *
 * @author user
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int [][] array={{2,2,0},{1,2,0},{0,0,3}};
        int[] colSum =new int[array[0].length];
        int[] rowSum =new int[array.length];
        for (int i = 0; i < array.length; i++)
        {  
            int rsum=0;
            for (int j = 0; j < array[i].length; j++){                
                rsum += array[i][j];
                colSum[j] += array[i][j];
            }
            rowSum[i]=rsum;
        }  

    }
    
}
