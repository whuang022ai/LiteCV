/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.convolution;

/**
 *
 * @author user
 */
public class ImageSpatialScan 
{
    public static  void printM(int[][] x) //印出矩陣
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
    public int [][] scan( int [][]mix1,int [][] kernal)//整數 卷積運算 int [][] kernal
    {
       int step=1;//卷積步長
       int w1 = mix1[0].length;
       int h1 = mix1.length;
       int s=(kernal.length-1)/2;
       int s1=s+1;
       int [][] mix= new int[h1-(kernal.length-1)][w1-(kernal.length-1)];//卷積後的結果
 
       //起點座標
       int startX=0;
       int startY=0;
       
       //終止座標
       int endX=kernal.length-1;
       int endY=kernal.length-1;
       
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
            endX=kernal.length-1;
            centerX=0;
            centerY=0;
            orgX=0;
            orgY=0;
            for(int i=0;centerX<w1-s1;i++)//水平走
            {
                System.out.println("startY:"+startY+ "startX:"+ startX+","+"endY:"+endY+"endX:"+ endX);
                //這裡做點積
                int dotSum=0;
                int [][]featureMap=new int[kernal.length][kernal[0].length];
                for(int q=startY,t=0;q<=endY;q++,t++)//垂直走
                {
                    for(int p=startX,r=0;p<=endX;p++,r++)//水平走
                    {
                        featureMap[t][r]=mix1[q][p];
                    }
                }
                //
                printM(featureMap);
                System.out.println("");
                centerX= (endX+startX)/2;
                centerY= (endY+startY)/2;
                
                orgX=centerX-s;
                orgY=centerY-s;

                System.out.println("centerY:"+centerY+","+"centerX:"+centerX);
                System.out.println("orgY:"+orgY+","+"orgX:"+orgX);
                 mix[orgY][orgX]=dotSum;
                 System.out.println("");
                //左移1格
                startX++;
                endX++;
                //          
            }
        //下移一格
        startY++;
        endY++;
        System.out.println("----"+H);
        H++;
       }
        return mix;
   }
}
