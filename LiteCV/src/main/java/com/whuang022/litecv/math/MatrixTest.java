/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.math;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class MatrixTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        MatrixAPI m=new Matrix();
         double[][] A = new double[][]
        {{0.616555,0.615444},
        {0.615444,0.716555}};
        System.out.println(m.isSymmetric(A));
        m.printM(A);
        */
        
        StatisticsAPI s=new Statistics();
        MatrixAPI m=new Matrix();
        ArrayList<double[]> datas=m.getDataCSV("C:\\Users\\user\\Desktop\\PCA\\PCA範例數據2.csv", true, ",");
        ArrayList<double[]> datass= m.transposeList(datas);
        double[][] covMat= s.covMat(datass);
        m.printM(covMat);
        System.out.println();
        ArrayList<double[][]> output =m.jacobiEigenMethod(covMat, 0.0000001, 1000);
        m.printM(output.get(0)) ;
        System.out.println();
        m.printM(output.get(1)) ;
        
        ArrayList<double[]> eV= m.getMatrixToList(output.get(0),MatrixDirect.column) ;
        double[] ev1=eV.get(0);
        double theta=Math.toDegrees(Math.atan2(ev1[1], ev1[0]));
        System.out.println(theta);
    }
    
}
