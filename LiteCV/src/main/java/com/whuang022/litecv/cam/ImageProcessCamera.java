package com.whuang022.litecv.cam;
 
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPanel.Painter;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Graphics2D; 
import java.awt.image.BufferedImage; 
import java.util.List;
import javax.swing.JFrame; 
 

public abstract class ImageProcessCamera extends JFrame implements Painter 
{ 
 private Webcam webcam = null; 
protected WebcamPanel.Painter painter = null; 
 
 public ImageProcessCamera(String title,int FPSLimit)  
 { 
    super(); 
    List< Webcam> cams=Webcam.getWebcams();
    webcam = cams.get(0);
    webcam.setViewSize(WebcamResolution.VGA.getSize()); 
    webcam.open(true); 
    
    WebcamPanel panel = new WebcamPanel(webcam, false); 
    panel.setPreferredSize(WebcamResolution.VGA.getSize()); 
    panel.setPainter(this); 
    panel.setFPSDisplayed(true); 
 
   panel.setFPSLimited(false); 
  // panel.setFPSLimit(FPSLimit); 
    panel.setPainter(this); 
    panel.start(); 
    painter = panel.getDefaultPainter(); 
   
    add(panel); 
    setTitle(title); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    pack(); 
    setLocationRelativeTo(null); 
    setVisible(true); 
 } 
 
 @Override 
 public void paintPanel(WebcamPanel panel, Graphics2D g2) 
 { 
    if (painter != null) 
    { 
        painter.paintPanel(panel, g2); 
    } 
 } 
 
 @Override 
 public abstract void paintImage(WebcamPanel panel, BufferedImage image, Graphics2D g2);
  
     
}
