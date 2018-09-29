/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.math;

import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageGray;
import com.whuang022.litecv.colorspace.ImageMatrix;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class MatrixTest2 {
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
        MatrixAPI m=new Matrix();
        String path="C:\\Users\\user\\Desktop\\PCA\\201.jpg";//
        ImageMatrix image =null;
        ImageColorReader reader=new ImageColorReader();
        image=reader.imageRead(path, ImageColorSpaceType.ColorSpaceGray);
        ImageGray img=(ImageGray)image;
        int [][]imgMat=img.G;
        ArrayList<double[]>d=new  ArrayList<>();
        for(int i=0;i<imgMat.length;i++)
        {
            for(int j=0;j<imgMat[0].length;j++)
            {
                if(imgMat[i][j]<100)
                {
                    double []dd=new double [2];
                    dd[0]=i;
                    dd[1]=j;
                    d.add(dd);
                }
            }
        }
       
        double[][] dataMat=m.getListToMatrix(d,MatrixDirect.row);
        
        StatisticsAPI s=new Statistics();
        
        
      // double[][]dataMat
        ArrayList<double[]> datass= m.getMatrixToList(dataMat,MatrixDirect.column) ;
        //   m.printM(dataMat);
        double[][] covMat= s.covMat(datass);
        m.printM(covMat);
         System.out.println();
        ArrayList<double[][]> output =m.jacobiEigenMethod(covMat, 0.0000001, 1000);
        m.printM(output.get(0)) ;
        System.out.println();
        m.printM(output.get(1)) ;
        
        ArrayList<double[]> eV= m.getMatrixToList(output.get(0),MatrixDirect.column) ;
        double[] ev1=eV.get(0);
        double theta=Math.abs(Math.toDegrees(Math.atan2(ev1[1], ev1[0])));
 

        System.out.println("圖像偏角估計:"+theta+"度");
    }
}
