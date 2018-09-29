package com.whuang022.litecv.math;

import java.util.ArrayList;

/**
 * 矩陣計算API
 * @author lovedoglion5
 */
public interface MatrixAPI 
{
    //基本運算
    public double[][] sum(double[][] x,double[][]y);//矩陣加法
    public double[][] sub(double[][] x,double[][]y);//矩陣減法
    public double[][] scalarProduct (double[][] x,double y);//矩陣係數積
    public double[][] product(double[][] x,double[][]y);//矩陣乘法
    public double[][] product(ArrayList<double[]> x, double[][] y) ;//List格式的矩陣乘法
    
    public double[][]getRowColumTHadamardProduction(double[][]rowMat,double[][]colMat);//特殊形式的Hadamard積
    
    public double[][]   transposeMatrix(double[][] A);//轉置矩陣
    public ArrayList<double[]> transposeList(ArrayList<double[]> dataList);//轉置List矩陣
    
    //對角線相關矩陣操作
    public double[][]    getDiagonalMatrix(double[][] x);//取矩陣對角線
    public double[]     getDiagonalArray(double[][] x);//取矩陣對角線
    public MatrixElement getDiagonalMaxElement(double[][]a);//在非對角線的元素中找最大值
    public double[][]   getMinorMatrix(double [][]A,int I,int J);//取一矩陣第I高 第J 寬 的 子行列式
    
    //矩陣行或列操作
    public double[][] swapMatrix(double [][] dest,double []src,int index,MatrixDirect dir);//將目標矩陣的第index MatrixDirect置換成src的值
    
    //基本矩陣數值運算
    public double    determinant(double[][] A);//行列式值
    public double[][] cholesky(double[][] x);//cholesky分解
    public double[]   cramerSlover(double [][]coefficientMatrix,double []answerColumn);//使用克拉瑪法則求線性方程式的解
    public ArrayList<double[][]> jacobiEigenMethod(double[][]A ,double eps,int itmax);//使用jacobi方法求矩陣特徵值&特徵向量
    public void givensRotationJacobi( int i ,int  j ,double [][]A,double[][]V,int n); //jacobi 的 givens 旋轉變換
    
    //性質判斷
    public boolean isSquare(double[][] x);   //是否為方陣
    public boolean isSymmetric(double[][] x);//是否對稱矩陣
    
    //檔案IO
    public  ArrayList<double[]> getDataCSV(String filePath,boolean ignoreLine1,String spliter);//讀取數據陣列(數據位置,是否夫忽略掉第一行(通常是數據的維度名稱),切分符號)
    
    //格式轉換
    
    //矩陣<->列組或行組
    public  double[][]        getListToMatrix(ArrayList<double[]> datas,MatrixDirect dir);//將List按照方向一組一組拆到矩陣
    public ArrayList<double[]> getMatrixToList(double[][] dataMat,MatrixDirect dir);//將矩陣按照方向一組一組拆到List
    //矩陣<->元素組(一維陣列)
    public  double[] getMatrixToArray(double[][] dataMat,MatrixDirect dirFirst);//矩陣以dirFirst的方向拆解到一維陣列
    public  double[][] getArrayToMatrix(double[] dataArray,MatrixDirect dirFirst,int row,int column);//陣列以dirFirst的方向組回到二維陣列
    
    //測試輔助
    public void printM(double[][] x);//印出矩陣
    public void printM(double[] x); //印出陣列
}
