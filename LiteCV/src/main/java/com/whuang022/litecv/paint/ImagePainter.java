package com.whuang022.litecv.paint;

import com.whuang022.litecv.colorspace.ImageRGB;

/**
 *
 * @author user
 */
public class ImagePainter 
{
    
    public static void drawDot(int X,int Y,ImageRGB src)
    {
        src.R[Y][X]=255;
        src.G[Y][X]=0;
        src.B[Y][X]=0;
    }
    public static void drawBox(int startX,int startY,int endX,int endY,ImageRGB src)
    {
        
        drawLine(startX,startY,startX,endY,src);//左上-左下
        drawLine(startX,startY,endX,startY,src);//左上-右上
        
        drawLine(endX,startY,endX,endY,src);//右上-右下
        drawLine(startX,endY,endX,endY,src);//左下-右下
    }
    public static void drawLine(int startX,int startY,int endX,int endY,ImageRGB src)
    {
       // System.out.println(startX+":"+startY+","+endX+":"+endY);
        int maxY=src.R.length;
        int maxX=src.R[0].length;
        int drawY=(endY+1>=maxY)?maxY:(endY+1);
        int drawX=(endX+1>=maxX)?maxX:(endX+1);
        for(int i=startY;i<drawY;i++)
        {
            for(int j=startX;j<drawX;j++)
            {
                src.R[i][j]=255;
                src.G[i][j]=0;
                src.B[i][j]=0;
            }
        }
    }
}
