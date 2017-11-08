/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.io;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class ImageIO 
{
    
    public  void showImage(BufferedImage img)
    {
      JFrame frame = new JFrame();
      frame.getContentPane().setLayout(new FlowLayout());
      frame.getContentPane().add(new JLabel(new ImageIcon(img)));
      frame.pack();
      frame.setVisible(true);
    }
     public  BufferedImage getImage(String imageName) //讀取影像
    {
        try 
        {
            File input = new File(imageName);
            BufferedImage image = javax.imageio.ImageIO.read(input);
            return image;
        } 
        catch (IOException ie) 
        {
            System.out.println("Error:" + ie.getMessage());
        }
        return null;
    }
    public int [][] getGrayF(BufferedImage img) //一次運算三個通道的灰化
    {
       int w1 = img.getWidth();
       int h1 = img.getHeight();
       int [][] mix= new int[h1][w1];
       int valueR =0;
       int valueG =0;
       int valueB=0;
       for (int i = 0; i < h1; i++)
       {
            for (int j = 0; j < w1; j++) 
            {
                valueR = img.getRGB(j, i); 
                valueG = img.getRGB(j, i);
                valueB = img.getRGB(j, i);
                int red = ( valueR >> 16) & 0xff;
                int green = ( valueG >> 8) & 0xff;
                int blue = ( valueB) & 0xff;
                int mixGray=(int) Math.round(red*0.299+ green*0.587+  blue*0.114);
                mix[i][j] =   mixGray;
            }
       }
        return mix;
    }
 
    public int [][] getGrayF(BufferedImage img,int paddingSize) //一次運算三個通道的灰化+影像邊界保護擴充
    {
       int w1 = img.getWidth()+paddingSize*2;//水平
       int h1 = img.getHeight()+paddingSize*2;//垂直
       int [][] mix= new int[h1][w1];//填充後的結果
       /*
       //----填充120讓邊界的像素不會因為0填充導致輸出顏色過深 看起來像多一條線
       for (int[] row:mix){Arrays.fill(row, 120);}
       //*/
       
       int valueR =0;
       int valueG =0;
       int valueB=0;
       for (int i = 0,H=paddingSize; i <h1; i++,H++)
       {
            for (int j = 0,W=paddingSize; j < w1; j++,W++) 
            {
                if(i<img.getHeight()&&j<img.getWidth())
                {
                    valueR = img.getRGB(j, i); 
                    valueG = img.getRGB(j, i);
                    valueB = img.getRGB(j, i);
                    int red = ( valueR >> 16) & 0xff;
                    int green = ( valueG >> 8) & 0xff;
                    int blue = ( valueB) & 0xff;
                    int mixGray=(int) Math.round(red*0.299+ green*0.587+  blue*0.114);
                    mix[H][W] =   mixGray;
                }
            }
       }
        return mix;
    }
    public  BufferedImage matrixToImage(int [][] matrix)//矩陣轉影像
    {
        BufferedImage bufferedImage = new BufferedImage( matrix[0].length,  matrix.length, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < matrix.length; i++) 
        {
            for (int j = 0; j < matrix[0].length; j++) 
            {
                int pixel=matrix[i][j];
                pixel =  matrix[i][j] <<8 |matrix[i][j]<<16;
                bufferedImage.setRGB(j, i, pixel);
            }
            
        }
        return  bufferedImage;
    }    
   
}
