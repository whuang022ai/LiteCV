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
                int red= R[i][j];
                int blue=B[i][j];
                int green=G[i][j];
                int pixel=new Color(red,green,blue).getRGB(); 
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
               int pixel=G[i][j];
                pixel = G[i][j] <<8 | G[i][j]<<16;
                bufferedImage.setRGB(j, i, pixel);
            }
        }
        return  bufferedImage;
    }
    public BufferedImage matrixToImage(double [][] H,double [][]S,double[][]V)//HSV矩陣轉影像
    {
        BufferedImage bufferedImage = new BufferedImage( H[0].length,  H.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < H.length; i++) 
        {
            for (int j = 0; j < H[0].length; j++) 
            {
                int red= (int) Math.round(H[i][j]*(1.0/360.0)*255);
                int green=(int) Math.round(S[i][j]*255);
                int blue=(int) Math.round(V[i][j]);
                if(red>255){red=255;}
                if(blue>255){blue=255;}
                if(green>255){green=255;}
                int pixel=new Color(blue,green,red).getRGB(); 
                bufferedImage.setRGB(j, i, pixel);
            }
        }
        return  bufferedImage;
    }
}
