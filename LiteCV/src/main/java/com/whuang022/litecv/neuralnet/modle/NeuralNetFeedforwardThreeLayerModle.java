/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.neuralnet.modle;

import com.whuang022.litecv.neuralnet.active.ActivationFunctionType;
import com.whuang022.litecv.neuralnet.net.MatrixDirect;
import com.whuang022.litecv.neuralnet.quantizer.Quantizer;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerFactory;
import com.whuang022.litecv.neuralnet.quantizer.QuantizerType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * NeuralNetFeedforwardThreeLayer模型類別
 * @author whuang022
 */

public class NeuralNetFeedforwardThreeLayerModle implements Modle 
{
    //
    private boolean inputLayerNormal=false;//是否壓縮輸入層總和=1(輸入層維度高時使用)
    private boolean hiddenLayerNormal=false;
    private int I;
    private int H;
    private int O;
    //權重矩陣
    private double [][]WeightsIH;
    private double[][] WeightsHO;
    //偏權矩陣
    private double [][]BiasHiddenLayer;
    private double [][]BiasOutputLayer;
    private ActivationFunctionType actType;
    private QuantizerType QLayerType;
    public int getI()
    {
        return I;
    }
    
    public int getO()
    {
        return O;
    }
    
    public int getH()
    {
        return H;
    }
    
    public double [][] getWeightsIH()
    {
        return WeightsIH;
    }
    
    public double [][]  getWeightsHO()
    {
        return  WeightsHO;
    }
    
    public double [][]  getBiasHiddenLayer()
    {
        return  BiasHiddenLayer;
    }
    
    public double [][]  getBiasOutputLayer()
    {
        return  BiasOutputLayer;
    }
    
    public ActivationFunctionType getActivationFunctionType()
    {
        return  this.actType;
    }
    
    public boolean getInputLayerNormal()
    {
        return  inputLayerNormal;
    }
    public boolean getHiddenLayerNormal()
    {
        return hiddenLayerNormal;
    }
    public QuantizerType getQuantizerType()
    {
        return  QLayerType;
    }
    public  NeuralNetFeedforwardThreeLayerModle(int I,int H,int O,ActivationFunctionType act,double [][]WeightsIH,double[][] WeightsHO,double [][]BiasHiddenLayer,double [][]BiasOutputLayer,boolean inputLayerNormal,boolean  hiddenLayerNormal,QuantizerType qLayer)
    {
        this.I=I;
        this.O=O;
        this.H=H;
        this.WeightsIH=WeightsIH;
        this.WeightsHO=WeightsHO;
        this.BiasHiddenLayer=BiasHiddenLayer;
        this.BiasOutputLayer=BiasOutputLayer;
        this.actType=act;
        this.inputLayerNormal=inputLayerNormal;
        this.QLayerType=qLayer;
        this.hiddenLayerNormal= hiddenLayerNormal;
    }
    public  NeuralNetFeedforwardThreeLayerModle(String filePath)
    {
        loadXML(filePath);
    }
    @Override
    public void saveXML(String filePath) 
    {
        Document document = new Document();
        Element root = new Element("litecv_storage");
        Element ANN = new Element("ANN");
        ANN.setAttribute("type_id", "litecv-ml-ann-mlp-treelayer");
        
            Element  Lz= new Element("layer_sizes");
            Lz.setAttribute("type_id", "litecv-matrix");
                Element  r= new Element("rows");
                r.addContent("1");
            Lz.addContent(r);

                Element  c= new Element("cols");
                c.addContent("3");
            Lz.addContent(c);
                Element d= new Element("data");
                    String dtmp="";
                    dtmp=I+" "+H+" "+O;
                d.addContent(dtmp);
            Lz.addContent(d);
        ANN.addContent(Lz);
            Element  activationFunction= new Element("activation_function");
            activationFunction.addContent(actType.toString());
        ANN.addContent(activationFunction);
            //
            Element  qLayer= new Element("QLayerType");
            qLayer.addContent(QLayerType.toString());
        ANN.addContent(qLayer);
            //
        Element  inputNormal= new Element("inputLayerNormal");
            inputNormal.addContent(inputLayerNormal+"");
        ANN.addContent(inputNormal);
            //
        Element  hiddenNormal= new Element("hiddenLayerNormal");
            hiddenNormal.addContent(hiddenLayerNormal+"");
        ANN.addContent(hiddenNormal);
       //
            Element  weigths= new Element("weigths");
                Element  WIH= new Element("WeightsIH");
                WIH.setAttribute("type_id", "litecv-matrix");
                    Element  rw1= new Element("rows");
                    rw1.addContent(WeightsIH.length+"");
                WIH.addContent(rw1);
                    Element  cw1= new Element("cols");
                    cw1.addContent(WeightsIH[0].length+"");
                WIH.addContent(cw1);
                    Element  dw1=new Element("data");
                    dw1.addContent( getArraytoString(getMatrixToArray(WeightsIH, MatrixDirect.row)," "));
                WIH.addContent(dw1);
           weigths.addContent(WIH);
                Element  WHO= new Element("WeightsHO");
                WHO.setAttribute("type_id", "litecv-matrix");
                    Element  rw2= new Element("rows");
                    rw2.addContent(WeightsHO.length+"");
                WHO.addContent(rw2);
                    Element  cw2= new Element("cols");
                    cw2.addContent(WeightsHO[0].length+"");
                WHO.addContent(cw2);
                    Element  dw2=new Element("data");
                    dw2.addContent( getArraytoString(getMatrixToArray(WeightsHO, MatrixDirect.row)," "));
                WHO.addContent(dw2);
          weigths.addContent(WHO); 
                Element  BiasHiddenLayer= new Element("BiasHiddenLayer");
                BiasHiddenLayer.setAttribute("type_id", "litecv-matrix");
                    Element  rw3= new Element("rows");
                    rw3.addContent(this.BiasHiddenLayer.length+"");
                BiasHiddenLayer.addContent(rw3);
                    Element  cw3= new Element("cols");
                    cw3.addContent(this.BiasHiddenLayer[0].length+"");
                BiasHiddenLayer.addContent(cw3);   
                    Element  dw3=new Element("data");
                    dw3.addContent( getArraytoString(getMatrixToArray(this.BiasHiddenLayer, MatrixDirect.row)," "));
                BiasHiddenLayer.addContent(dw3);
          weigths.addContent(BiasHiddenLayer);
                Element  BiasOutputLayer= new Element("BiasOutputLayer");
                BiasOutputLayer.setAttribute("type_id", "litecv-matrix");
                    Element  rw4= new Element("rows");
                    rw4.addContent(this.BiasOutputLayer.length+"");
                BiasOutputLayer.addContent(rw4);
                    Element  cw4= new Element("cols");
                    cw4.addContent(this.BiasOutputLayer[0].length+"");
                BiasOutputLayer.addContent(cw4);
                    Element  dw4=new Element("data");
                    dw4.addContent( getArraytoString(getMatrixToArray(this.BiasOutputLayer, MatrixDirect.row)," "));
                BiasOutputLayer.addContent(dw4);
           weigths.addContent(BiasOutputLayer);
       ANN.addContent(weigths);
       root.addContent(ANN);
       document.addContent(root);
        XMLOutputter XMLOut = new XMLOutputter();  
        try 
        {  
            Format f = Format.getPrettyFormat();  
            f.setEncoding("UTF-8");//default=UTF-8  
            XMLOut.setFormat(f);  
            XMLOut.output(document, new FileOutputStream(filePath));  
        }
        catch (IOException e) 
        {  
            
        }  
    }

    private double[][]parseMatrixXML(Element mat)
    {
        String strRow=mat.getChild("rows").getText();
        String strCol=mat.getChild("cols").getText();
        String strData=mat.getChild("data").getText();
        int rows=Integer.parseInt(strRow);
        int cols=Integer.parseInt(strCol);
        double[]dataArray=parseStringtoArray(strData," ");
        double[][]matOut= getArrayToMatrix(dataArray, MatrixDirect.row, rows, cols);
        return matOut;
    }
    private double[] parseStringtoArray(String str,String spilter)
    {
        String[]strArray=str.split(spilter);
        double[]out=new double[strArray.length];
        for(int i=0;i<out.length;i++)
        {
            out[i]=Double.parseDouble(strArray[i]);
        }
        return out;
    }
    @Override
    public void loadXML(String filePath) 
    {
        try 
        {
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(new File(filePath));
            Element root = doc.getRootElement();
            List<Element> elements = root.getChildren();
            Element ann=elements.get(0);
            String annRoot = ann.getName();
            String annRootTypeID=ann.getAttributeValue("type_id");
            if(root.getName().equals("litecv_storage")&&annRoot.equals("ANN")&&annRootTypeID.equals("litecv-ml-ann-mlp-treelayer"))
            {
                Element layerSize=ann.getChild("layer_sizes");
                double[][] layerSizeSetting=parseMatrixXML( layerSize);//網路結構
                Element actF=ann.getChild("activation_function");
                String actFSetting=actF.getText();//激活函數
                Element QL=ann.getChild("QLayerType");
                String QLayerSetting=QL.getText();//量化層
                
                Element inputLayerE=ann.getChild("inputLayerNormal");
                Element hiddenLayerNormalE=ann.getChild("hiddenLayerNormal");
                
                String inputLayerN=inputLayerE.getText();//是否壓縮輸入
                String hiddenLayerN=hiddenLayerNormalE.getText();
                Element weigths=ann.getChild("weigths");//權值模型
                
                Element WIHe=weigths.getChild("WeightsIH");
                Element WHOe=weigths.getChild("WeightsHO");
                Element B0e=weigths.getChild("BiasHiddenLayer");
                Element B1e=weigths.getChild("BiasOutputLayer");
                
                double[][] WIH=parseMatrixXML(WIHe);
                double[][] WHO=parseMatrixXML(WHOe);
                double[][] B0=parseMatrixXML(B0e);
                double[][] B1=parseMatrixXML(B1e);
                
                boolean inputLayerNormalSet=Boolean.parseBoolean(inputLayerN);
                boolean hiddenLayerNormalSet=Boolean.parseBoolean(hiddenLayerN);
                ActivationFunctionType act = null;
                if(actFSetting.equals(ActivationFunctionType.SigmodFunction+""))
                {
                    act=ActivationFunctionType.SigmodFunction;
                    
                }
                else if(actFSetting.equals(ActivationFunctionType.HyperbolicTangentFuction+""))
                {
                    act=ActivationFunctionType.HyperbolicTangentFuction;
                }
                //QLayerSetting
                Quantizer QLayer=QuantizerFactory.getQuantizer(QLayerSetting);
                this.I=(int)layerSizeSetting[0][0];
                 
                this.H=(int)layerSizeSetting[0][1];
                this.O=(int)layerSizeSetting[0][2];
                this.WeightsIH=WIH;
                this.WeightsHO=WHO;
                this.BiasHiddenLayer=B0;
                this.BiasOutputLayer=B1;
                this.inputLayerNormal=inputLayerNormalSet;
                this.hiddenLayerNormal=hiddenLayerNormalSet;
                this.actType=act;
                this.QLayerType=QLayer.getType();
            }
            else
            {
                System.out.println("Modle Typed Error");
                return ;
            }
            
        } catch (JDOMException ex) {
            Logger.getLogger(NeuralNetFeedforwardThreeLayerModle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NeuralNetFeedforwardThreeLayerModle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getArraytoString(double[] input,String spilt)
    {
        String output="";
        for(int i=0;i<input.length;i++)
        {
            output+=input[i];
            if(i!=input.length-1)
            {
                output+=spilt;
            }
        }
        return output;
    }
    public double[] getMatrixToArray(double[][] dataMat, MatrixDirect dirFirst) {
        switch (dirFirst) 
        {
            case column:
                return getMatrixToArrayColumnMajor(dataMat);
            case row:
                return getMatrixToArrayRowMajor (dataMat);
            default:
                return getMatrixToArrayRowMajor(dataMat);
        }
    }
    private double[]getMatrixToArrayRowMajor(double [][] dataMat)
    {
        double[] array=new double[dataMat.length*dataMat[0].length];
        int k=0;
        for(int i=0;i< dataMat.length;i++)
        {
            for(int j=0;j< dataMat[0].length;j++)
            {
                array[k]=dataMat[i][j];
                k++;
            }
        }
        return array;
    }
    private double[]getMatrixToArrayColumnMajor(double [][] dataMat)
    {
        double[] array=new double[dataMat.length*dataMat[0].length];
        int k=0;
        for(int i=0;i< dataMat[0].length;i++)
        {
            for(int j=0;j< dataMat.length;j++)
            {
                array[k]=dataMat[j][i];
                k++;
            }
        }
        return array;
    }

    public double[][] getArrayToMatrix(double[] dataArray, MatrixDirect dirFirst, int row, int column)
    {
       switch (dirFirst) 
        {
            case column:
             return getArrayToMatrixColumnMajor(dataArray, row, column);
            case row:
                return getArrayToMatrixRowMajor(dataArray, row, column);
            default:
                return getArrayToMatrixRowMajor(dataArray, row, column);
        }
    }
    public double[][]getArrayToMatrixRowMajor(double [] dataArray, int row, int column)
    {
        double[][]dataMat=new double[row][column];
        int j=0;
        for(int i=0;i<dataArray.length;i++)
        {
            dataMat[j][i%column]=dataArray[i];
           if(i%column==column-1)
           {
               j++;
           }
        }
        return dataMat;
    }
    public double[][]getArrayToMatrixColumnMajor(double [] dataArray, int row, int column)
    {
        double[][]dataMat=new double[row][column];
        int j=0;
        for(int i=0;i<dataArray.length;i++)
        {
            dataMat[i%row][j]=dataArray[i];
           if(i%row==row-1)
           {
               j++;
           }
        }
        return dataMat;
    }
    public void printM(double[][] x) //印出矩陣
    {
        for(int i=0;i<x.length;i++)
        {
            for(int j=0;j<x[0].length;j++)
            {
                System.out.print(x[i][j]+" ");
            }
            System.out.println();
        }     
    }
    public void printM(double[] x) //印出陣列
    {
        for(int i=0;i<x.length;i++)
        {
           System.out.println(x[i]);
        }     
    }
}
