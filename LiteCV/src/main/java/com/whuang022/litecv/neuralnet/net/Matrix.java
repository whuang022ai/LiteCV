
package com.whuang022.litecv.neuralnet.net;

import com.whuang022.litecv.neuralnet.active.ActivationFunction;

/**
 * 矩陣運算類別
 * @author whuang022
 */

public  class Matrix 
{
    
    public ActivationFunction act;

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
    public double[][] sum(double[][] x, double[][] y) //矩陣相加
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
    
    public double[][] sub(double[][] x, double[][] y) //矩陣相減
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

    public double[][] product(double[][] x, double[][] y) //矩陣內積
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
    public double[][] productHadamard(double[][] x, double[][] y) //矩陣Hadamard乘積(元素對應位置相乘)
    {
        double[][] product=new double[x.length][x[0].length];
        for(int i=0;i<product.length;i++)
        {
            for(int j=0;j<product[0].length;j++)
            {
               product[i][j] = x[i][j]*y[i][j];
            }
        }
        return product;     
    }
    
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
    
    public double[][] fuctionMatrix(double[][] x)//矩陣每個元素輸入函數
    {
        double[][]  fuction = new double [x.length][x[0].length];
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                fuction[i][j]= act.fuction(x[i][j]);
            }
        }
        return  fuction;        
    }
     public double[][] fuctionMatrixDiv(double[][] x)//矩陣每個元素輸入函數的微分函數
    {
        double[][]  fuction = new double [x.length][x[0].length];
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                 fuction[i][j]= act.fuctionDiv(x[i][j]);
            }
        }
        return  fuction;        
    }
    public double elementAbsSum(double[][] x) //矩陣元素絕對值和
    {
        double sum =0;
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                sum+=Math.abs(x[i][j]);
            }
        }
        return sum;
    } 
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
    public  double[][]getRowColumTHadamardProduction(double[][]rowMat,double[][]colMat,double scale)
    {
        double[][]C=new double[rowMat[0].length][colMat[0].length];
        for(int i=0;i<C.length;i++)
        {
          for(int j=0;j<C[0].length;j++)
          {
            C[i][j]= scale*rowMat[0][i]*colMat[0][j];
          }
        }
        return C;
    }
}
