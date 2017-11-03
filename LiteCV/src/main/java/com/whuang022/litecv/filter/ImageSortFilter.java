package com.whuang022.litecv.filter;

import com.whuang022.litecv.kernel.ImageSortKernalInterface;

/**
 *
 * @author user
 */
public abstract class ImageSortFilter implements ImageFilter{

    
    public int [][] sortFilter( int [][]image,ImageSortKernalInterface kernel)//整數 卷積運算 int [][] kernal
    {
       int step=1;//卷積步長
       int w1 = image[0].length;
       int h1 = image.length;
       int [][] mix= new int[h1-2][w1-2];//卷積後的結果
       //起點座標
       int startX=0;
       int startY=0;
       //終止座標
       int endX=2;
       int endY=2;
       //中心座標
       int centerX=0;
       int centerY=0;
       int orgX=0;
       int orgY=0;
       // 
       int H=0;
       for(int j=0;centerY<h1-2;j++)//垂直走
        {
            startX=0;
            endX=2;
            centerX=0;
            centerY=0;
            orgX=0;
            orgY=0;
            for(int i=0;centerX<w1-2;i++)//水平走
            {
                //這裡做點積
                int dotSum=0;
                int []region=new int[9];
                int k=0;
                for(int q=startY,t=0;q<=endY;q++,t++)//垂直走
                {
                    for(int p=startX,r=0;p<=endX;p++,r++)//水平走
                    {
                       int dot=image[q][p];
                       region[k]=dot;
                       k++;
                    }
                } 
                dotSum=kernel.getCenter(region);
                centerX= (endX+startX)/2;
                centerY= (endY+startY)/2;
                orgX=centerX-1;
                orgY=centerY-1;
                mix[orgY][orgX]=dotSum;
                //左移1格
                startX++;
                endX++;
                //          
            }
        //下移一格
        startY++;
        endY++;
        H++;
       }
       return mix;
   }
}
