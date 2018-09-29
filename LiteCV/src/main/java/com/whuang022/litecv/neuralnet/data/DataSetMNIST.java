
package com.whuang022.litecv.neuralnet.data;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Mnist 圖檔轉CSV訓練檔
 * @author whaung022
 */
public class DataSetMNIST 
{
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException 
    {
        FileOutputStream fos=new FileOutputStream(new File("./mnist3.csv"));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        File path= new File("C:\\user\\Documents\\NetBeansProjects\\NeuralNet\\mnist_8.8");
        File []filePaths=path.listFiles();
        for(File filePath:filePaths)
        {
            BufferedImage img=DataIO.getBufferedImage(filePath);
            String fileName=filePath.getName();
            String[]fileNameArr=fileName.split("\\.");
            int ID=Integer.parseInt(fileNameArr[0]);
            System.out.println(ID);
            String vec=DataIO.getImagetoVector(img, ID, 10,true);
            bw.write(vec+"\t\n");
        }
        bw.close();
        osw.close();
        fos.close();
    }
    
}
