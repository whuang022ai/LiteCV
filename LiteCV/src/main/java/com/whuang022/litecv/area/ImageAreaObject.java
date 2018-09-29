/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.area;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author user
 */
public class ImageAreaObject 
{
    public ArrayList<Vector> pixels;//區塊的像素座標
    public double distence;//離群值
    public double squareFeature;//對角平方和特徵
    public ImageAreaObject() 
    {
        this.pixels = new ArrayList<Vector>();
    }
    public int getAreaSize() //取得區塊大小
    {
        return  pixels.size();
    }
    public boolean isFatArea() 
    {
        int V=Math.abs(getMaxV()-getMinV());//垂直距離
        int H=Math.abs(getMaxH()-getMinH());//水平距離
       // System.out.println("垂直"+V+":"+"水平"+H);
        if(V>H)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public Vector getAreaStart()//取得區塊起點(在寬度最小的座標裡面找高度最小)
    {
        ArrayList<Vector> minHs=new   ArrayList<Vector>();
        for(int i=0;i<pixels.size();i++)
        {
           if((int)pixels.get(i).get(1)==getMinH())
           {
               minHs.add(pixels.get(i));
           }
        }
        Vector minV= minHs.get(0);
        for(int i=0;i<minHs.size();i++)
        {
            Vector node=minHs.get(i);//取出各點
            if((int)node.get(0) <(int)minV.get(0))
            {
                minV=node;
            }
        }
        return minV;
    }
    public Vector getAreaEnd()//取得區塊終點(在高度最大的座標裡面找寬度最小)
    {
        ArrayList<Vector> maxVs=new   ArrayList<Vector>();
        for(int i=0;i<pixels.size();i++)
        {
           if((int)pixels.get(i).get(0)==getMaxV())
           {
               maxVs.add(pixels.get(i));
           }
        }
        Vector maxH= maxVs.get(0);
         for(int i=0;i<maxVs.size();i++)
        {
            Vector node=maxVs.get(i);//取出各點
            if((int)node.get(1) >(int)maxH.get(1))
            {
                maxH=node;
            }
        }
        return maxH;
    }
    
    public int getMaxV()//找垂直方向座標最大值
    {
        Vector maxV=pixels.get(0);
        for(int i=0;i<pixels.size();i++)
        {
            Vector node=pixels.get(i);//取出各點
            if( (int)node.get(0)>(int)maxV.get(0))//比較垂直方向
            {
                maxV=node;
            }
        }
        return (int)maxV.get(0);
    }
    public int getMinV()//找垂直方向座標最小值
    {
        Vector minV=pixels.get(0);
        for(int i=0;i<pixels.size();i++)
        {
            Vector node=pixels.get(i);//取出各點
            if( (int)node.get(0)<(int)minV.get(0))//比較垂直方向
            {
                minV=node;
            }
        }
        return (int)minV.get(0);
    }
    public int getMaxH()//找水平方向座標最大值
    {
        Vector maxH=pixels.get(0);
        for(int i=0;i<pixels.size();i++)
        {
            Vector node=pixels.get(i);//取出各點
            if( (int)node.get(1)>(int)maxH.get(1))//比較垂直方向
            {
                maxH=node;
            }
        }
        return (int)maxH.get(1);
    }
    public int getMinH()//找水平方向座標最小值
    {
        Vector minH=pixels.get(0);
        for(int i=0;i<pixels.size();i++)
        {
            Vector node=pixels.get(i);//取出各點
            if( (int)node.get(1)<(int) minH.get(1))//比較垂直方向
            {
                 minH=node;
            }
        }
        return (int) minH.get(1);
    } 
}
