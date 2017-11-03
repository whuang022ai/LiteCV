package com.whuang022.litecv.colorspace;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class ImageRGB implements Image
{
    public int [][] R;
    public int [][] G;
    public int [][] B;     

    @Override
    public void display() {
        BufferedImage image=matrixToImage(R,G,B);
        showImage(image);
    }
     public  void showImage(BufferedImage img)
    {
      JFrame frame = new JFrame();
      frame.getContentPane().setLayout(new FlowLayout());
      frame.getContentPane().add(new JLabel(new ImageIcon(img)));
      frame.pack();
      frame.setVisible(true);
    }
    private  BufferedImage matrixToImage(int [][] R,int [][]G,int[][]B)//矩陣轉影像
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
}
