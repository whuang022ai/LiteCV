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
public class MatrixTest3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[][] A = new double[][]
        {{1,2},
        {3,4},
        {5,6},      
        };
       // double[]B = new double[]{7,8,9};
        MatrixAPI m=new Matrix();
        m.printM(A);
        double[] C =  m.getMatrixToArray(A,MatrixDirect.column);
       // System.out.println();
        m.printM(C);
        double[][] D=m.getArrayToMatrix(C, MatrixDirect.column, 3, 2);
        m.printM(D);
      //  ArrayList<double[]> data=m.getMatrixToList(A, MatrixDirect.row);
       // m.printM(data.get(0));
    }
    
}
