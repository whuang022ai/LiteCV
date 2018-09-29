
package com.whuang022.litecv.math;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author user
 */
public class PCA 
{
    private StatisticsAPI statistics=new Statistics();
    private MatrixAPI matrix=new Matrix();
    private ArrayList<double[]> dataList=new ArrayList<>();
   
    
    private double[] avgArray;
    private double[][] covarianceMatrix;
    private double[][] eigenVectorMatrix;
    private double[] eigenValueMatrix;
    private  double[][] dataAdjustMatrix;
     
    private ArrayList<MatrixEigen> eigenList=new  ArrayList<>();
    
    private static final double eps = 1e-6;
    private static final int iterationMax=1000;
    

    public PCA()
    {

    }
     public double[][] getDataAdjustMatrix()
    {
        return dataAdjustMatrix;
    }
    public double[][] getCovarianceMatrix()
    {
        return covarianceMatrix;
    }
    public double[][] getEigenVectorMatrix()
    {
        return eigenVectorMatrix;
    }
    public double[] getEigenValueMatrix()
    {
        return eigenValueMatrix;
    }
    public ArrayList<MatrixEigen> getEigenList()
    {
        return eigenList;
    }
    public PCA(String filePath)//load data from csv
    {
        dataList=matrix.getDataCSV(filePath, true, ",");
        double[][]dataMat=matrix.getListToMatrix(dataList, MatrixDirect.row);
        double[][]dataAdjustMat=new double[dataMat.length][dataMat[0].length];
        ArrayList<double[]> dataListT= matrix.transposeList(dataList);
        avgArray=statistics.getAvgArray(dataListT);
        for(int i=0;i<dataMat.length;i++)
        {
            for(int j=0;j<dataMat[0].length;j++)
            {
                dataAdjustMat[i][j]= dataMat[i][j]-avgArray[j];
            }
        }
        this.dataAdjustMatrix=dataAdjustMat;
        computeEigen(dataListT);
    }
    public void projectDataList ()
    {
        double[][] dataProject=matrix.product(dataAdjustMatrix, eigenVectorMatrix);
        matrix.printM(dataProject);
    }
    private void computeEigen(ArrayList<double[]> data)
    {
        Comparator<Double> keyComparator = new Comparator<Double>() 
        {
        @Override
            public int compare(Double o1,Double o2) 
            {
                return o2.compareTo(o1);
            }
        };
        
        covarianceMatrix= statistics.covMat(data);
        ArrayList<double[][]> output =matrix.jacobiEigenMethod( covarianceMatrix, eps, iterationMax);
        double[][] eigenVector=output.get(0);
        ArrayList<double[]> eigenVectorList=matrix.getMatrixToList(eigenVector, MatrixDirect.column);
        double[][] eigenValue=output.get(1);
        //對特徵值 特徵向量排序
        Map<Double, MatrixEigen> eigenVectorMap= new TreeMap<>( keyComparator);
        for(int i=0;i<eigenValue.length;i++)
        {
            MatrixEigen eigen=new MatrixEigen();
            eigen.eigenValue=eigenValue[i][i];
            eigen.eigenVector=eigenVectorList.get(i);
            eigenVectorMap.put(eigen.eigenValue, eigen);
        }
        double[][]eigenVectorSorted=new double[eigenVector.length][eigenVector[0].length];
        double[]eigenValueSorted=new double[eigenValue.length];
        int k=0;
        for(Double key : eigenVectorMap.keySet()) 
        {
            eigenValueSorted [k]=eigenVectorMap.get(key).eigenValue;
            eigenVectorSorted[k]=eigenVectorMap.get(key).eigenVector;
            eigenList.add(eigenVectorMap.get(key));
            k++;
        }
        eigenVectorMatrix=matrix.transposeMatrix(eigenVectorSorted);
        eigenValueMatrix=eigenValueSorted; 
    }
}


