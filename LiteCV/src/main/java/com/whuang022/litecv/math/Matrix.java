package com.whuang022.litecv.math;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class Matrix implements MatrixAPI
{

    @Override
    public double[][] sum(double[][] x, double[][] y) 
    {
        double[][] sum = new double[x.length][x[0].length];
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                sum[i][j]=x[i][j]+y[i][j];
            }
        }
        return sum;
    }

    @Override
    public double[][] sub(double[][] x, double[][] y) 
    {
        double[][] sum =  new double[x.length][x[0].length];
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                sum[i][j]=x[i][j]-y[i][j];
            }
        }
        return sum;
    }

    @Override
    public double[][] scalarProduct(double[][] x, double y) 
    {
        double[][] scale = new double [x.length][x[0].length];
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                scale[i][j]=x[i][j]*y;
            }
        }
        return scale;        
    }

    @Override
    public double[][] product(double[][] x, double[][] y) 
    {
        double[][] product=new double[x.length][y[0].length];
        for(int i=0;i<product.length;i++)
        {
            for(int j=0;j<product[0].length;j++)
            {
                for(int k=0;k<y.length;k++)
                {
                    product[i][j] += x[i][k]*y[k][j];
                }
            }
        }
        return product;     
    }
    @Override
    public double[][] product(ArrayList<double[]> x, double[][] y) 
    {
        double[][] product=new double[x.size()][y[0].length];
        for(int i=0;i<product.length;i++)
        {
            for(int j=0;j<product[0].length;j++)
            {
                for(int k=0;k<y.length;k++)
                {
                    product[i][j] += x.get(i)[k]*y[k][j];
                }
            }
        }
        return product;     
    }
    @Override
    public  double[][] cholesky(double[][] a)
    {
       int m = a.length;
       double[][] l = new double[m][m];
       for(int i = 0; i< m;i++)
       {
            for(int k = 0; k < (i+1); k++)
            {
                double sum = 0;
                for(int j = 0; j < k; j++)
                {
                    sum += l[i][j] * l[k][j];
                }
                l[i][k] = (i == k) ? Math.sqrt(a[i][i] - sum) :
                          (1.0 / l[k][k] * (a[i][k] - sum));
            }
       }
        return l;
    }


    @Override
    public boolean isSquare(double[][] x) //是不是方陣
    {
        if(x.length==x[0].length)
            return true;
        else
            return false;
    }

    @Override
    public boolean isSymmetric(double[][] x) //是不是對稱矩陣
    {
            if(x.length==x[0].length)//對稱矩陣一定是方陣
            {
                for(int i=0;i<x.length;i++)
                {
                    for(int j=0 ;j<i;j++)
                    {
                        if(x[i][j]!=x[j][i])//不符合對稱矩陣
                            return false;
                        else
                            return true;
                    }
                }
            }
            return false;//不是方陣
    }
    
    //Extends Methods
    @Override
    public double[][] getDiagonalMatrix(double[][] x)//取一方陣的對角線
    {
       double[][] Q=new double[x.length][x[0].length];
       for(int i=0;i<x.length;i++)
       {
            for(int j=0;j<x[0].length;j++)
            {
                if(i==j)
                {
                    Q[i][j]=x[i][j];
                }
                else
                {
                    Q[i][j]=0;
                }
            }
       }
        return Q;
    }
   
    public double[][] getIdentityMatrix(int dim)//取一單位矩陣
    {
       double[][] I=new double[dim][dim];
       for(int i=0;i<I.length;i++)
       {
            for(int j=0;j<I[0].length;j++)
            {
                 if(i==j)
                 {
                    I[i][j]=1;
                 }
                 else
                 {
                    I[i][j]=0;    
                 }
            }
       }
        return I;
    }
  
    
    @Override
    public double[][]getMinorMatrix(double [][]A,int I,int J)//取一矩陣第I高 第J 寬 的 子行列式
    {
       double [][]Minor=new double[A.length-1][A[0].length-1];
       int m=0;
       for(int i=0;i<A.length;i++)
       {
          if(i!=I)//去掉第I行
          {
              int n=0;
              for(int j=0;j<A[0].length;j++)
              {
                 if(j!=J)//去掉第J列
                 {
                    Minor[m][n]=A[i][j];
                    n++;
                 }
              }
              m++;
          }
       }
       return Minor;
    }
    private double determinant3Dim(double[][] A) //三階行列式值(快速算法)
    {
       return (A[0][0] * (A[1][1]*A[2][2]-A[1][2]*A[2][1])-A[1][0] * (A[0][1]*A[2][2]-A[0][2]*A[2][1])+A[2][0] * (A[0][1]*A[1][2]-A[0][2]*A[1][1]) );
    }
    private double determinant2Dim(double[][] A)//二階行列式值(快速算法)
    {
       return (A[0][0]*A[1][1] - A[0][1]*A[1][0]);
    }
    
    @Override
    public double determinant(double[][] A)//N階行列式值(遞迴)
   {
        switch (A.length) {
        //BasCase 1
            case 2:
                return determinant2Dim(A);
        //BasCase 2
            case 3:
                return determinant3Dim(A);
            default:
                double det=0;
                for(int i=0;i<A.length;i++)
                {
                    double a=A[i][0];
                    int I=i;
                    int J=0;
                    double[][]minor=getMinorMatrix(A,I,J);
                    det+=a*Math.pow(-1,i)*determinant(minor);
                }
                return det;
        }
    }
    @Override
    public double[]cramerSlover(double [][]coefficientMatrix,double []answerColumn)//使用克拉瑪法則求線性方程式的解
    {
        double  detCoefficientMatrix=determinant(coefficientMatrix);
        double []slove=new double[answerColumn.length];
        for(int i=0;i<coefficientMatrix[0].length;i++)
        {
            double [][]Xn=swapMatrixFromColumn(coefficientMatrix,answerColumn,i);
            double detXn= determinant(Xn);
            slove[i]=(detXn/detCoefficientMatrix);
        }
        return slove;
    }
   
    private double[][]swapMatrixFromColumn(double [][] dest,double []col,int index)//將目標矩陣的第index行(垂直)置換成src的值
    {
        double [][]output=new double[dest.length][dest[0].length];
        int k=0;
        for(int i=0;i<output.length;i++)
        {
            for(int j=0;j<output[0].length;j++)
            {
                if(j!=index)
                {
                    output[i][j]=dest[i][j];
                }
                else
                {
                    output[i][j]=col[k];
                    k++;
                }
            }
        }
        return output;
    }
    private double[][]swapMatrixFromRow(double [][] dest,double []rowSrc,int index)//將目標矩陣的第index 列 Row(水平)置換成src的值
    {
        double [][]output=new double[dest.length][dest[0].length];
        int k=0;
        for(int i=0;i<output.length;i++)
        {
            for(int j=0;j<output[0].length;j++)
            {
                if(i!=index)
                {
                    output[i][j]=dest[i][j];
                }
                else
                {
                    output[i]=rowSrc;
                    k++;
                }
            }
        }
        return output;
    }
    @Override
    public double[][]transposeMatrix(double[][] A)//轉置矩陣
    {
       double [][]output=new double[A[0].length][A.length];
       for(int i=0; i<A.length; i++)
       {
           for(int j=0; j<A[0].length; j++)
           {
                output[j][i] = A[i][j];
           }
       }
        return output;
       
    }
    @Override
    public void printM(double[][] x) //印出矩陣
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
    @Override
    public void printM(double[] x) //印出陣列
    {
        for(int i=0;i<x.length;i++)
        {
           System.out.println(x[i]);
        }     
    }

    @Override
    public  ArrayList<double[]> getDataCSV(String filePath, boolean ignoreLine1,String spliter) 
    {
        BufferedReader reader;
        ArrayList<double[]> dataList=new  ArrayList<>();
        try 
       {
            reader = new BufferedReader(new FileReader(filePath)); //文件名
            String line = null;
            //忽略掉第一行
            if(ignoreLine1)
            {
                reader.readLine();
            }
            //按行讀取直到結束
            while((line=reader.readLine())!=null)
            {
                String[] items = line.split(spliter);
                double[] data=new double[items.length];
                for(int i=0;i<items.length;i++)
                {
                    data[i]=Double.parseDouble(items[i]);
                }
                dataList.add(data);
            }
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  dataList;
    }
    @Override
    public double[][] getListToMatrix(ArrayList<double[]> datas,MatrixDirect dir) //將ArrayList<double[]>轉換成double[][]
    {
         switch (dir) 
        {
            case column:
                return getListToMatrixColumn(datas);
            case row:
                return getListToMatrixRow(datas);
            default:
                return getListToMatrixColumn(datas);
        }
    }
    private double[][] getListToMatrixRow(ArrayList<double[]> datas) //將ArrayList<double[]>轉換成double[][]
    {
        double[][] dataMat=new double[datas.size()][datas.get(0).length];
        for(int i=0;i<datas.size();i++)
        {
            double[] data=datas.get(i);
            dataMat[i]=data;
        }
        return dataMat;
    }
    private double[][] getListToMatrixColumn(ArrayList<double[]> datas) //將ArrayList<double[]>轉換成double[][]
    {
        double[][] dataMat=new double[datas.get(0).length][datas.size()];
        for(int i=0;i<datas.get(0).length;i++)
        {
            for(int j=0;j<datas.size();j++)
            {
                dataMat[i][j]= datas.get(i)[j];
            }
        }
        return dataMat;
    }
    @Override
    public ArrayList<double[]> getMatrixToList(double[][] dataMat,MatrixDirect dir) //將double[][]轉換成ArrayList<double[]>
    {
        switch (dir) 
        {
            case column:
                return getMatrixToListColumn(dataMat);
            case row:
                return getMatrixToListRow(dataMat);
            default:
                return getMatrixToListColumn(dataMat); 
        }
    }
    
    private ArrayList<double[]> getMatrixToListColumn(double[][] dataMat)
    {
        ArrayList<double[]> dataList=new ArrayList<> ();
        for(int j=0;j<dataMat[0].length;j++)//走水平
        {
            double[] data=new double[dataMat.length];
            for(int i=0;i<data.length;i++)
            {
                data[i]=dataMat[i][j];
            }
            dataList.add(data);
        }
        return dataList;
    }
    
    private ArrayList<double[]> getMatrixToListRow(double[][] dataMat)
    {
        ArrayList<double[]> dataList=new ArrayList<> ();
        for(int i=0;i<dataMat.length;i++)//走垂直
        {
            double[] data=new double[dataMat[0].length];
            for(int j=0;j<dataMat[0].length;j++)
            {
                data[j]=dataMat[i][j];
            }
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public double[][] swapMatrix(double[][] dest, double[] src, int index, MatrixDirect dir) 
    {
        switch (dir) 
        {
            case column:
                return swapMatrixFromColumn(dest,src,index);
            case row:
                return swapMatrixFromRow(dest,src,index);
            default:
                return swapMatrixFromColumn(dest,src,index); 
        }
    }

    @Override
    public ArrayList<double[]> transposeList(ArrayList<double[]> dataList) 
    {
        ArrayList<double[]> outputList=new ArrayList<>();
        int dim=dataList.get(0).length;
        for(int i=0;i<dim;i++)
        {
            double[] data=new  double[dataList.size()];
            for(int j=0;j< dataList.size();j++)
            {
                data[j]=dataList.get(j)[i];
            }
            outputList.add(data);
        }
        return outputList;
    }

    @Override
    public MatrixElement getDiagonalMaxElement(double[][] a) 
    {
        MatrixElement e=new MatrixElement();
        double d=Double.MIN_VALUE;
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<a[0].length;j++)
            {
                if(i!=j)
                {
                    double tmp=Math.abs(a[i][j]);
                    if(tmp>d)
                    {
                        d=tmp;
                        e.rowIndex=i;
                        e.columnIndex=j;
                        e.value=d;
                    }
                }
            }
        }
        return e;
    }

    @Override
    public ArrayList<double[][]> jacobiEigenMethod(double[][] A, double eps, int itmax) 
    {
        ArrayList<double[][]>output=new ArrayList<>();
        int it;
        it=0;
        int n=A.length;
        double[][] V=getIdentityMatrix(n);
        while(it<itmax)
        {
            it++;
            MatrixElement maxE=getDiagonalMaxElement(A);
            if(maxE.value<eps)//非對角線的元素的最大值小於給定的eps -> 停止迭代
            {
                output.add(V);//特徵值向量
                output.add(A);//特徵值
                return output;//返回
            }
            else
            {
                givensRotationJacobi(maxE.rowIndex ,maxE.columnIndex ,A,V,n) ;
            }
        }
        output.add(V);
        output.add(A);
        return output;//返回
    }

    @Override
    public void givensRotationJacobi(int i, int j, double[][] A, double[][] V, int n) 
    {
        double theta = Math.atan2(2.0 * A[i][j], A[i][i] - A[j][j]) * 0.5;
        double sint = Math.sin(theta);
        double cost = Math.cos(theta);
        for (int k = 0; k < n; k++) 
        {
            double  tmpA = A[k][i]; 
            A[k][i] = cost *  A[k][i] + sint * A[k][j];
            A[k][j] = -sint * tmpA + cost * A[k][j];
            tmpA = V[k][i];
            V[k][i] = cost *  tmpA + sint * V[k][j];
            V[k][j] = -sint * tmpA + cost * V[k][j]; 
        }  
        A[i][i] = cost  *  A[i][i] + sint * A[j][i];
        A[j][j] = -sint *  A[i][j] + cost * A[j][j];
        A[i][j]=A[j][i] =  0;
        for (int k = 0; k < n; k++) 
        {
            A[i][k] = A[k][i];
            A[j][k] = A[k][j];
        }
    }

    @Override
    public double[] getDiagonalArray(double[][] x)
    {
       double[]Q=new double[x.length];
       for(int i=0;i<x.length;i++)
       {
            for(int j=0;j<x[0].length;j++)
            {
                if(i==j)
                {
                    Q[i]=x[i][j];
                }
            }
       }
        return Q;
    }

    @Override
    public double[] getMatrixToArray(double[][] dataMat, MatrixDirect dirFirst) {
        switch (dirFirst) 
        {
            case column:
                return getMatrixToArrayColumnMajor(dataMat);
            case row:
                return getMatrixToArrayRowMajor (dataMat);
            default:
                return getMatrixToArrayRowMajor(dataMat);
        }
    }
    private double[]getMatrixToArrayRowMajor(double [][] dataMat)
    {
        double[] array=new double[dataMat.length*dataMat[0].length];
        int k=0;
        for(int i=0;i< dataMat.length;i++)
        {
            for(int j=0;j< dataMat[0].length;j++)
            {
                array[k]=dataMat[i][j];
                k++;
            }
        }
        return array;
    }
    private double[]getMatrixToArrayColumnMajor(double [][] dataMat)
    {
        double[] array=new double[dataMat.length*dataMat[0].length];
        int k=0;
        for(int i=0;i< dataMat[0].length;i++)
        {
            for(int j=0;j< dataMat.length;j++)
            {
                array[k]=dataMat[j][i];
                k++;
            }
        }
        return array;
    }
    @Override
    public double[][] getArrayToMatrix(double[] dataArray, MatrixDirect dirFirst, int row, int column)
    {
       switch (dirFirst) 
        {
            case column:
             return getArrayToMatrixColumnMajor(dataArray, row, column);
            case row:
                return getArrayToMatrixRowMajor(dataArray, row, column);
            default:
                return getArrayToMatrixRowMajor(dataArray, row, column);
        }
    }
    public double[][]getArrayToMatrixRowMajor(double [] dataArray, int row, int column)
    {
        double[][]dataMat=new double[row][column];
        int j=0;
        for(int i=0;i<dataArray.length;i++)
        {
            dataMat[j][i%column]=dataArray[i];
           if(i%column==column-1)
           {
               j++;
           }
        }
        return dataMat;
    }
    public double[][]getArrayToMatrixColumnMajor(double [] dataArray, int row, int column)
    {
        double[][]dataMat=new double[row][column];
        int j=0;
        for(int i=0;i<dataArray.length;i++)
        {
            dataMat[i%row][j]=dataArray[i];
           if(i%row==row-1)
           {
               j++;
           }
        }
        return dataMat;
    }
    
    @Override
    public  double[][]getRowColumTHadamardProduction(double[][]rowMat,double[][]colMat)
    {
        double[][]C=new double[rowMat[0].length][colMat[0].length];
        for(int i=0;i<C.length;i++)
        {
          for(int j=0;j<C[0].length;j++)
          {
            C[i][j]=rowMat[0][i]*colMat[0][j];
          }
        }
        return C;
    }
}
