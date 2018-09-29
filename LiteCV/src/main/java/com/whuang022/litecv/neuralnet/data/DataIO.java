
package com.whuang022.litecv.neuralnet.data;

import com.whuang022.litecv.neuralnet.net.Matrix;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerFactory;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;

/**
 * 訓練資料讀取
 * @author whuang022
 */
public class DataIO 
{
    
    public static BufferedImage getBufferedImage(String path) 
    {
        File input = new File(path);
        return getBufferedImage(input);
    }
    public static BufferedImage getBufferedImage(File input) 
    {
        try 
        {
            BufferedImage image = javax.imageio.ImageIO.read(input);
            return image;
        } 
        catch (IOException ie) 
        {
            System.out.println("Error Read Image:"+input.getAbsolutePath());
        }
        return null;
    }
    public static String getIDtoBinEncode(int labelID,int classSize)//ID轉二進編碼
    {
        String max=  Integer.toBinaryString(classSize);
       String str=  Integer.toBinaryString(labelID); 
       int sizeMax=max.length();
       int sizeStr=str.length();
       int zero=sizeMax-sizeStr;
       String vec="";
       for(int i=0;i<zero;i++)
       {
           vec+="0";
           vec+=",";
       }
        for(int i=0;i<str.length();i++)
       {
           
           if(i!=str.length()-1)
           {
               vec+=str.charAt(i)+",";
           }
           else
           {
               vec+=str.charAt(i);
           }
       }
       return vec;
    }
    public static String getIDtoOneHotEncode(int labelID,int classSize)//ID轉One Hot編碼
    {
       String vec="";
       for(int i=classSize;i>=0;i--)
       {
           if(i!=labelID)
           {
               vec+="0";
           }
           else
           {
               vec+="1";
           }
            if(i!=0)
           {
               vec+=",";
           }
         
       }
        return vec;
    }
    public static String getImagetoVector(BufferedImage src,int labelID,int classSize,boolean normalVec)//
    {
       //label id to one hot
       String classVec=getIDtoOneHotEncode(labelID,classSize);
       int w1 = src.getWidth();
       int h1 = src.getHeight();
       String vector="";
       int valueR =0;
       int valueG =0;
       int valueB=0;
       for (int i = 0; i < h1; i++)
       {
            for (int j = 0; j < w1; j++) 
            {
                valueR = src.getRGB(j, i); 
                valueG = src.getRGB(j, i);
                valueB = src.getRGB(j, i);
                int R = ( valueR >> 16) & 0xff;
                int G = ( valueG >> 8) & 0xff;
                int B = ( valueB) & 0xff;
                double mixGray=0.299*R+0.587*G+0.11*B;
                if(normalVec)
                {
                    mixGray=mixGray/255.0;
                }
                vector+=mixGray+",";
            }
       }
       vector+=classVec;
       return vector;
    }            
    public  static ArrayList<Data> getDataListCSV(String filePath, boolean ignoreLine1,String spliter,int m) //m=期望輸出的維度
    {
        BufferedReader reader;
        ArrayList<Data> dataList=new  ArrayList<>();
        try 
       {
            reader = new BufferedReader(new FileReader(filePath)); //文件名
            String line = null;
            //忽略掉第一行
            if(ignoreLine1)
            {
                reader.readLine();
            }
            //按行讀取直到結束
            while((line=reader.readLine())!=null)
            {
                String[] items = line.split(spliter);
                //System.out.println(Arrays.toString(items));
                double[] []data=new double[1][items.length-m]; 
                double[] []output=new double[1][m];
                int i=0;
                for(i=0;i<data[0].length;i++)
                {
                    data[0][i]=Double.parseDouble(items[i]);//前面的資料為輸入特徵
                   
                }
                for( int j=0;j<output[0].length;j++)
                {
                     output[0][j]=Double.parseDouble(items[i]);//後面的資料為期望輸出
                     i++;
                }
                Data trainObj=new Data(data,output);
                dataList.add(trainObj);
            }
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  dataList;
    }
    public  static ArrayList<Data> getDataListCSV(String filePath, boolean ignoreLine1,String spliter,int m,QuantizerType  QT) //m=期望輸出的維度
    {
        Quantizer q=QuantizerFactory.getQuantizer(QT);
        BufferedReader reader;
        ArrayList<Data> dataList=new  ArrayList<>();
        try 
       {
            reader = new BufferedReader(new FileReader(filePath)); //文件名
            String line = null;
            //忽略掉第一行
            if(ignoreLine1)
            {
                reader.readLine();
            }
            //按行讀取直到結束
            while((line=reader.readLine())!=null)
            {
                String[] items = line.split(spliter);
                //System.out.println(Arrays.toString(items));
                double[] []data=new double[1][items.length-m]; 
                double[] []output=new double[1][m];
                int i=0;
                for(i=0;i<data[0].length;i++)
                {
                    data[0][i]=Double.parseDouble(items[i]);//前面的資料為輸入特徵
                   
                }
                for( int j=0;j<output[0].length;j++)
                {
                     output[0][j]=Double.parseDouble(items[i]);//後面的資料為期望輸出
                     i++;
                }
                int classID=q.quantizer(output[0]);
                Data trainObj=new Data(data,output);
                trainObj.classID=classID;
                dataList.add(trainObj);
            }
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  dataList;
    }
}
