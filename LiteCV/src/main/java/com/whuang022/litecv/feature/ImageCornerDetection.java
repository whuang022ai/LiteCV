
package com.whuang022.litecv.feature;

import com.whuang022.litecv.colorspace.ImageGray;
import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageRGB;
import com.whuang022.litecv.filter.ImageSobelEdgeFilter;
import com.whuang022.litecv.math.Matrix;
import com.whuang022.litecv.math.MatrixAPI;
import com.whuang022.litecv.math.Statistics;
import com.whuang022.litecv.math.StatisticsAPI;
import com.whuang022.litecv.paint.ImagePainter;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author user
 */
public class ImageCornerDetection 
{
    private StatisticsAPI statis=new Statistics();
    private MatrixAPI mat=new Matrix();
    public ImageMatrix getHarris( ImageMatrix image,ImageMatrix imageColor,int kernalSize,int x,int y)
    {
        ImageGray imageG=(ImageGray)image;
        ImageRGB imageRGBColor=(ImageRGB) imageColor;
        ImageSobelEdgeFilter sobel=new ImageSobelEdgeFilter ();
        ArrayList<int[][]> sobelImage=sobel.filterTwoPath(imageG.G);
        ArrayList<Vector> harris=scan( sobelImage, kernalSize);
        for(Vector vec:harris)
        {
            ImagePainter.drawDot((int) vec.get(0)+x,(int) vec.get(1)+y,imageRGBColor);
        }
        return imageRGBColor;
    }
     public ArrayList<Vector> scan( ArrayList<int[][]> mixs, int kernalSize)//整數 卷積運算 int [][] kernal
    {
         Vector t0 = new Vector();
         
        ArrayList<Vector>Harris=new  ArrayList<Vector>();
       int [][] mix1=mixs.get(0);
       int [][] mix2=mixs.get(1);
       int size=kernalSize*kernalSize;
       
       int step=1;//卷積步長
       int w1 = mix1[0].length;
       int h1 = mix1.length;
       int s=(kernalSize-1)/2;
       int s1=s+1;
       int [][] mix= new int[h1-(kernalSize-1)][w1-(kernalSize-1)];//卷積後的結果
 
       //起點座標
       int startX=0;
       int startY=0;
       
       //終止座標
       int endX=kernalSize-1;
       int endY=kernalSize-1;
       
       //中心座標
       int centerX=0;
       int centerY=0;
       
       int orgX=0;
       int orgY=0;
       // 
       int H=0;
       for(int j=0;centerY<h1-s1;j++)//垂直走
        {
            startX=0;
            endX=kernalSize-1;
            centerX=0;
            centerY=0;
            orgX=0;
            orgY=0;
            for(int i=0;centerX<w1-s1;i++)//水平走
            {
                //System.out.println("startY:"+startY+ "startX:"+ startX+","+"endY:"+endY+"endX:"+ endX);
                int w=0;
                double[][] output=new double[size][2];
                for(int q=startY,t=0;q<=endY;q++,t++)//垂直走
                {
                    for(int p=startX,r=0;p<=endX;p++,r++)//水平走
                    {
                        output[w][0]=mix1[q][p];
                        output[w][1]=mix2[q][p];
                        w++;
                    }
                }
                //
                //double[][]C=statis.covMat2D(output);
                double k=0.04;
                double[][]M=statis.covMat2D(output);
                double A=M[0][0]*g(0,0);
                double B=M[1][1]*g(1,1);
                double C=M[0][1]*g(0,1);
                
                double crf=( A*C-B*B)-k*(A+B);
              // System.out.println(crf);
              // mat.printM(C);
              // System.out.println();
               /*
                double lambda1 =eginValue[0][0];
                double lambda2 =eginValue[1][1];
               // System.out.println(lambda1+":"+lambda2);
               
                centerX= (endX+startX)/2;
                centerY= (endY+startY)/2;
                if(lambda1>3000&lambda2>3000)
                {
                   // if(Harris.size()==0)
                    {
                        Vector vec=new Vector();
                        vec.add( centerX);
                        vec.add( centerY);
                        Harris.add(vec);
                        t0=vec;
                    }
                 
                }*/
                centerX= (endX+startX)/2;
                centerY= (endY+startY)/2;
                if(crf>5000000)
                {
                   
                        Vector vec=new Vector();
                        vec.add( centerX);
                        vec.add( centerY);
                        Harris.add(vec);
                        t0=vec;
                }
                orgX=centerX-s;
                orgY=centerY-s;
                
                //左移1格
                startX++;
                endX++;
                //          
            }
        //下移一格
        startY++;
        endY++;
       }
        return Harris;


   }
     public double g(double x,double y)
     {
         return Math.exp((-1*(x*x+y*y))/1);
     }
}
