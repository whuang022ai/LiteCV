/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.histogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author usery
 */
public class ImageProcessThresholdOtsu 
{
 
    public int getThreshold(ImageProcessHistogram data) 
    {
       Map<Integer, Double> map = new HashMap<>();
       int threshold=0;
       for(int i=0;i<256;i++)
       {
           if(data.histogram()[i]!=0)
           {
               int T=i;
               double w1=data.cdf()[T];
               double w2=1-w1;
               double u1=0;
               double u2=0;
               double sigma=0;
               //u1:
               if(w1!=0)
               {
                    for(int j=0;j<=T;j++)
                    {
                        u1+=data.pdf()[j]/w1*j;
                    }
               }
               else
               {
                  u1=0; 
               }
               //u2:
               if(w2!=0)
               {
                    for(int j=T+1;j<=255;j++)
                    {
                        u2+=data.pdf()[j]/w2*j;
                    }
               }
               else
               {
                  u2=0; 
               }
               //sigma:
               sigma=w1*(u1-data.avgHistogram())*(u1-data.avgHistogram())+
                     w2*(u2-data.avgHistogram())*(u2-data.avgHistogram())
                       ;
              // System.out.println("sigma"+T+"="+sigma);
               map.put(T, sigma);
           }
       }
       threshold=sortThreshold(map);
       return threshold;
    }
    private int sortThreshold(Map<Integer, Double> map)
    {
        List<Map.Entry<Integer, Double>> wordMap = new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());
        
        Collections.sort(wordMap, new Comparator<Map.Entry<Integer, Double>>() 
        {   
            // 根据value排序
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2)
            {
                double result = o2.getValue() - o1.getValue();
                if (result > 0)
                return 1;
                else if (result == 0)
                return 0;
                else
                return -1;
            }
        }
        );
        return wordMap.get(0).getKey();
    }
}
