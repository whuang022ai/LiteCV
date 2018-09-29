/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.example;

import com.whuang022.litecv.neuralnet.modle.NeuralNetFeedforwardThreeLayerModle;
import com.whuang022.litecv.neuralnet.net.NeuralNetFeedforwardThreeLayer;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerFactory;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;
import com.whuang022.litecv.colorspace.ImageColorReader;
import com.whuang022.litecv.colorspace.ImageColorSpaceType;
import com.whuang022.litecv.colorspace.ImageGray;
import com.whuang022.litecv.colorspace.ImageRGB;
import com.whuang022.litecv.histogram.ImageProcessBin;
import com.whuang022.litecv.resize.ImageResize;
import java.util.Arrays;

/**
 *
 * @author user
 */
public class ImageHandOCR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         ImageProcessBin bin=new ImageProcessBin();

      NeuralNetFeedforwardThreeLayerModle M=new NeuralNetFeedforwardThreeLayerModle("mnist.XML");
    NeuralNetFeedforwardThreeLayer   nn=new NeuralNetFeedforwardThreeLayer(M);
    ImageResize    re=new ImageResize();
        // TODO code application logic here
        String path="C:\\user\\Documents\\NetBeansProjects\\NeuralNet\\mnist_8.8\\1.9.jpg";
       ImageColorReader reader=new ImageColorReader();
       ImageGray image=(ImageGray) reader.imageRead(path, ImageColorSpaceType.ColorSpaceGray);/*
        for(int i=0;i<image.G.length;i++)
       {
            for(int j=0;j<image.G[0].length;j++)
            {
                image.G[i][j]=255-image.G[i][j];
            }
       }*/
       //image=bin.getImageGraytoImageBin(image);
       
       ImageGray o=new  ImageGray();
       ////
       //o.G=re.getImageToSizeBiLinear(image .G,8, 8);
        o.G=image.G;
        if(o.G.length*o.G[0].length==64){
                double [][]Input=new double[1][o.G.length*o.G[0].length];
                int r=0;
                for(int q=0;q<o.G.length;q++)
                {
                   for(int p=0;p<o.G[0].length;p++)
                   {
                       double pi=0.0+o.G[p][q];
                       pi=pi/255.0;
                       Input[0][r]=pi;
                       r++;
                   }
                }
                double [][]out=nn.ForwardPropagation(Input);
                Quantizer q=QuantizerFactory.getQuantizer(QuantizerType.Onehot);
                System.out.println(Arrays.toString(out[0]));
            // System.out.println(q.getID(out[0]));
        
    }
    }
    
}
