/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.math;

/**
 *
 * @author user
 */
public class Array 
{
    public static boolean arrayEquals(double arr[],double[]arr2)
    {
        boolean equal=true;
        if (arr.length!=arr2.length)
        {
            return false;
        }
        else
        {
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]!=arr2[i])
                {
                    equal=false;
                }
            }
        }
        return equal;
    }
    public static double getMaxValue(double arr[])
    {
        double max=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            if(max<arr[i])
            {
                max=arr[i];
            }
        }
        return max;
   }
    public static int getMaxIndex(double arr[])
    {
        double max=arr[0];
        int maxIndex=0;
        for(int i=1;i<arr.length;i++)
        {
            if(max<arr[i])
            {
                max=arr[i];
                maxIndex=i;
            }
        }
        return maxIndex;
   }
   
    public static double getMinValue(double arr[])
    {
        double min=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            if(min>arr[i])
            {
                min=arr[i];
            }
        }
        return min;
   }
    public static int getMinIndex(double arr[])
    {
        double min=arr[0];
        int minIndex=0;
        for(int i=1;i<arr.length;i++)
        {
            if(min>arr[i])
            {
                min=arr[i];
                minIndex=i;
            }
        }
        return minIndex;
   }
}
