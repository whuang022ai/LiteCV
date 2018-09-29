/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.area;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author user
 */
public class ImageAreaFilter 
{
    public  static ArrayList<ImageAreaObject> seedFilling_4( int [][]mix1,int binColor)//種子填充法
    {
       int w1 = mix1[0].length;//寬
       int h1 = mix1.length;//高
       boolean [][]mixUsed=new boolean [h1][w1];//紀錄已使用之點
      // int [][]mixLabel=new int [h1][w1];//紀錄每個點的標籤
       ArrayList<ImageAreaObject> AreaLists = new ArrayList<ImageAreaObject>();
       //初始化紀錄表
       for(int i=0;i<h1;i++)//往下走
       {
            for(int j=0;j<w1;j++)//往右走
            {
               mixUsed[i][j]=false;//座標i,j的點沒使用過
            }
       }
       //遍歷每個像素
       int label=1;
       for(int i=0;i<h1;i++)//往下走
       {
            for(int j=0;j<w1;j++)//往右走
            {
                if(mix1[i][j]==binColor&&mixUsed[i][j]==false)//如果值為白色 且為未使用之點
                {
                    //以此點為一個新區域進行連通標記
                    //以下為連通標記搜尋
                    Stack<Vector> trace = new Stack<Vector>();//本區域用於追蹤的堆疊
                    ImageAreaObject Area=new ImageAreaObject();
                    int centeri=i;//種子點座標初始化
                    int centerj=j;//種子點座標初始化
                    mixUsed[centeri][centerj]=true;//將該點設為已處理
                   // mixLabel[centeri][centerj]=label;
                    Vector   nodeStart=new  Vector ();
                    nodeStart.add(centeri);
                    nodeStart.add(centerj);
                    Area.pixels.add(nodeStart);
                    do
                    {
                        try
                        {
                            Vector nodeOut=trace.pop();//堆疊取出一個像素
                            Area.pixels.add(nodeOut);
                            //更新種子點座標
                            centeri=(int)nodeOut.get(0);
                            centerj=(int)nodeOut.get(1);
                        }
                        catch(Exception e)
                        {
                            
                        }
                        //檢查上方點是否連通
                        if(centeri-1>=0)//避免上方點超出邊界
                        {
                            if(mix1[centeri-1][centerj]==binColor&&mixUsed[centeri-1][centerj]==false)//上方點連通且未使用
                            {
                                Vector node= new Vector();
                                node.add(centeri-1);//0
                                node.add(centerj);//1
                                trace.push(node);//送入堆疊
                                mixUsed[centeri-1][centerj]=true;//將該點設為已處理
                            }
                        }
                        //檢查下方點是否連通
                        if(centeri+1<=h1-1)//避免下方點超出邊界
                        {
                            if(mix1[centeri+1][centerj]==binColor&&mixUsed[centeri+1][centerj]==false)//下方點連通且未使用
                            {
                                Vector node= new Vector();
                                node.add(centeri+1);//0
                                node.add(centerj);//1
                                trace.push(node);//送入堆疊
                                mixUsed[centeri+1][centerj]=true;//將該點設為已處理
                            }
                        }
                        //檢查左方點是否連通
                        if(centerj-1>=0)//避免下方點超出邊界
                        {
                            if(mix1[centeri][centerj-1]==binColor&&mixUsed[centeri][centerj-1]==false)//左方點連通且未使用
                            {
                                Vector node= new Vector();
                                node.add(centeri);//0
                                node.add(centerj-1);//1
                                trace.push(node);//送入堆疊
                                mixUsed[centeri][centerj-1]=true;//將該點設為已處理
                            }
                        }
                        //檢查右方點是否連通
                        if(centerj+1<=w1-1)//避免右方點超出邊界
                        {
                            if(mix1[centeri][centerj+1]==binColor&&mixUsed[centeri][centerj+1]==false)//右方點連通且未使用
                            {
                                Vector node= new Vector();
                                node.add(centeri);//0
                                node.add(centerj+1);//1
                                trace.push(node);//送入堆疊
                                mixUsed[centeri][centerj+1]=true;//將該點設為已處理
                            }
                        }
                    }
                    while(!trace.empty());//堆疊不為空重複以上動作
                  //  label++;//增加類別
                   AreaLists.add(Area);
                }      
            }
            
       }
       return AreaLists;
    }
    
    public static ArrayList<ImageAreaObject> seedFilling_4Bin( boolean [][]mix1,int minSize)//種子填充法 針對圖像遮罩 有遮True表示非需要之點 False表留下來之關注點
    {
       int w1 = mix1[0].length;//寬
       int h1 = mix1.length;//高
       boolean [][]mixUsed=new boolean [h1][w1];//紀錄已使用之點
      // int [][]mixLabel=new int [h1][w1];//紀錄每個點的標籤
       ArrayList<ImageAreaObject> AreaLists = new ArrayList<ImageAreaObject>();
       //初始化紀錄表
       for(int i=0;i<h1;i++)//往下走
       {
            for(int j=0;j<w1;j++)//往右走
            {
               mixUsed[i][j]=false;//座標i,j的點沒使用過
            }
       }
       //遍歷每個像素
       int label=1;
       for(int i=0;i<h1;i++)//往下走
       {
            for(int j=0;j<w1;j++)//往右走
            {
                if(mix1[i][j]==false&&mixUsed[i][j]==false)//如果值為白色 且為未使用之點
                {
                    //以此點為一個新區域進行連通標記
                    //以下為連通標記搜尋
                    Stack<Vector> trace = new Stack<Vector>();//本區域用於追蹤的堆疊
                    ImageAreaObject Area=new ImageAreaObject();
                    int centeri=i;//種子點座標初始化
                    int centerj=j;//種子點座標初始化
                    mixUsed[centeri][centerj]=true;//將該點設為已處理
                   // mixLabel[centeri][centerj]=label;
                    Vector   nodeStart=new  Vector ();
                    nodeStart.add(centeri);
                    nodeStart.add(centerj);
                    Area.pixels.add(nodeStart);
                    do
                    {
                        try
                        {
                            Vector nodeOut=trace.pop();//堆疊取出一個像素
                            Area.pixels.add(nodeOut);
                            //更新種子點座標
                            centeri=(int)nodeOut.get(0);
                            centerj=(int)nodeOut.get(1);
                        }
                        catch(Exception e)
                        {
                            
                        }
                        //檢查上方點是否連通
                        if(centeri-1>=0)//避免上方點超出邊界
                        {
                            if(mix1[centeri-1][centerj]==false&&mixUsed[centeri-1][centerj]==false)//上方點連通且未使用
                            {
                                Vector node= new Vector();
                                node.add(centeri-1);//0
                                node.add(centerj);//1
                                trace.push(node);//送入堆疊
                                mixUsed[centeri-1][centerj]=true;//將該點設為已處理
                            }
                        }
                        //檢查下方點是否連通
                        if(centeri+1<=h1-1)//避免下方點超出邊界
                        {
                            if(mix1[centeri+1][centerj]==false&&mixUsed[centeri+1][centerj]==false)//下方點連通且未使用
                            {
                                Vector node= new Vector();
                                node.add(centeri+1);//0
                                node.add(centerj);//1
                                trace.push(node);//送入堆疊
                                mixUsed[centeri+1][centerj]=true;//將該點設為已處理
                            }
                        }
                        //檢查左方點是否連通
                        if(centerj-1>=0)//避免下方點超出邊界
                        {
                            if(mix1[centeri][centerj-1]==false&&mixUsed[centeri][centerj-1]==false)//左方點連通且未使用
                            {
                                Vector node= new Vector();
                                node.add(centeri);//0
                                node.add(centerj-1);//1
                                trace.push(node);//送入堆疊
                                mixUsed[centeri][centerj-1]=true;//將該點設為已處理
                            }
                        }
                        //檢查右方點是否連通
                        if(centerj+1<=w1-1)//避免右方點超出邊界
                        {
                            if(mix1[centeri][centerj+1]==false&&mixUsed[centeri][centerj+1]==false)//右方點連通且未使用
                            {
                                Vector node= new Vector();
                                node.add(centeri);//0
                                node.add(centerj+1);//1
                                trace.push(node);//送入堆疊
                                mixUsed[centeri][centerj+1]=true;//將該點設為已處理
                            }
                        }
                    }
                    while(!trace.empty());//堆疊不為空重複以上動作
                    
                  //  label++;//增加類別
                  if(Area.getAreaSize()>minSize){
                   AreaLists.add(Area);
                  }
                }      
            }
            
       }
       return AreaLists;
    }
}
