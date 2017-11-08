 package com.whuang022.litecv.colorspace;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class ImageVisual implements Serializable 
{
    public boolean [][] mask;//true代表被遮罩這掉 
    public  void showImage(BufferedImage img,String title)
    {
      JFrame frame = new JFrame();
      frame.getContentPane().setLayout(new FlowLayout());
      frame.getContentPane().add(new JLabel(new ImageIcon(img)));
      frame.pack();
      frame.setVisible(true);
      frame.setTitle(title);
    }
    public BufferedImage matrixToImage(int [][] R,int [][]G,int[][]B)//RGB矩陣轉影像
    {
        BufferedImage bufferedImage = new BufferedImage( R[0].length,  R.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < R.length; i++) 
        {
            for (int j = 0; j < R[0].length; j++) 
            {
                int pixel=0;
                if(!mask[i][j])
                {
                    int red= R[i][j];
                    int blue=B[i][j];
                    int green=G[i][j];
                    pixel=new Color(red,green,blue).getRGB(); 
                }
                else
                {
                    pixel=new Color(0,0,0).getRGB(); 
                }
                bufferedImage.setRGB(j, i, pixel);  
            }
        }
        return  bufferedImage;
    }
    public BufferedImage matrixToImage(int [][] G)//G矩陣轉影像
    {
        BufferedImage bufferedImage = new BufferedImage( G[0].length,  G.length, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < G.length; i++) 
        {
            for (int j = 0; j < G[0].length; j++) 
            {
                int pixel=0;
                if(!mask[i][j])
                {
                    pixel=G[i][j];
                    pixel = G[i][j] <<8 | G[i][j]<<16;
                }
                else
                {
                    pixel=new Color(0,0,0).getRGB(); 
                }
                bufferedImage.setRGB(j, i, pixel);
            }
        }
        return  bufferedImage;
    }
    public BufferedImage matrixToImage(double [][] H,double [][]S,double[][]V)//HSV矩陣轉影像
    {
        /*
        * 特別註明:HSV三個分量的意義不不是直接對應到RGB.為了顯示方便,將HSV分量正規化到0~255 並當作BGR混和產生圖片
        */
        BufferedImage bufferedImage = new BufferedImage( H[0].length,  H.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < H.length; i++) 
        {
            for (int j = 0; j < H[0].length; j++) 
            {
                int pixel=0;
                if(!mask[i][j])
                {
                    int blue= (int) Math.round(H[i][j]*(1.0/360.0)*255);
                    int green=(int) Math.round(S[i][j]*255);
                    int red=(int) Math.round(V[i][j]);
                    if(red>255){red=255;}
                    if(blue>255){blue=255;}
                    if(green>255){green=255;}
                    pixel=new Color(red,green,blue).getRGB(); 
                }
                else
                {
                   pixel=new Color(0,0,0).getRGB(); 
                } 
                bufferedImage.setRGB(j, i, pixel);
            }
        }
        return  bufferedImage;
    }
}
