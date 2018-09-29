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
public class PCATest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Matrix m=new Matrix();
        PCA  pca=new PCA ("C:\\Users\\user\\Desktop\\PCA\\PCA範例數據2.csv");
        m.printM(pca.getEigenValueMatrix());
       // System.out.println();
    //    m.printM(pca.getEigenVectorMatrix());
        ArrayList<MatrixEigen> eigenList=pca.getEigenList();
        for(MatrixEigen e:eigenList)
        {
           // System.out.println(e.eigenValue);
       //     m.printM(e.eigenVector);
          //  System.out.println();
        }
        pca.projectDataList();
        double [][] adj=pca.getDataAdjustMatrix();
       // System.out.println("CCV");
       // m.printM(adj);
    }
    
}
