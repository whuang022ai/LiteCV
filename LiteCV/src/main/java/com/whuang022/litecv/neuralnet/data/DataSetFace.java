/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author user
 */
public class DataSetFace {
     public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException 
    {
        FileOutputStream fos=new FileOutputStream(new File("./face.csv"));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        File path= new File("./face_8.82");
        File []filePaths=path.listFiles();
        for(File filePath:filePaths)
        {
            BufferedImage img=DataIO.getBufferedImage(filePath);
            String fileName=filePath.getName();
            String[]fileNameArr=fileName.split("\\.");
            int ID=Integer.parseInt(fileNameArr[0]);
            String vec=DataIO.getImagetoVector(img, ID, 10,true);
            bw.write(vec+"\t\n");
        }
        bw.close();
        osw.close();
        fos.close();
    }
}
