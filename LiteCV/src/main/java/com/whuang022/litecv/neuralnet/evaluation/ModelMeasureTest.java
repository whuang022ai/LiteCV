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
public class ModelMeasureTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double [][] con={{2,2,0},{1,2,0},{0,0,3}};
        ModelMeasure mm=new ModelMeasure(con);
        System.out.println(mm.geF1Avg());
        System.out.println(mm.geRecallAvg());
        System.out.println(mm.getPrecisionAvg());
        System.out.println(mm.getAccuracy());
    }
    
}
