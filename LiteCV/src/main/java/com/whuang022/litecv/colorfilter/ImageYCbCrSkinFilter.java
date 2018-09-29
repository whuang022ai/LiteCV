/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.colorfilter;

import com.whuang022.litecv.colorspace.ImageMatrix;
import com.whuang022.litecv.colorspace.ImageYCbCr;

/**
 *
 * @author user
 */
public class ImageYCbCrSkinFilter 
{
    public static ImageMatrix filter(ImageMatrix src)
    {
        ImageYCbCr srcMat=(ImageYCbCr)src;
        // # define variables for skin rules  
        double Wcb = 46.97 ; 
        double Wcr = 38.76 ;
        double WHCb = 14  ;
        double WHCr = 10  ;
        double WLCb = 23  ; 
        double WLCr = 20  ;
        double Ymin = 16  ;
        double Ymax = 235 ;
        double Kl = 125   ;
        double Kh = 188   ;
        double WCb = 0    ;
        double WCr = 0   ; 
        double CbCenter = 0 ;
        double CrCenter = 0  ;
        for(int r=0;r<srcMat.Y.length;r++)
        {
           for(int c=0;c<srcMat.Y[0].length;c++)
           {
               // # non-skin area if skin equals 0, skin area otherwise          
               int skin = 0  ; 
               //  # color space transformation  
               // # get values from ycbcr color space       
               double Y =srcMat.Y[r][c];
               double Cr = srcMat.Cr[r][c]; 
               double Cb = srcMat.Cb[r][c];                                 
                if( Y < Kl)
                {
                    WCr = WLCr + (Y - Ymin) * (Wcr - WLCr) / (Kl - Ymin)  ;
                    WCb = WLCb + (Y - Ymin) * (Wcb - WLCb) / (Kl - Ymin)  ;
                    CrCenter = 154 - (Kl - Y) * (154 - 144) / (Kl - Ymin)  ;
                    CbCenter = 108 + (Kl - Y) * (118 - 108) / (Kl - Ymin)  ;            
                }
                else if( Y > Kh)
                {
                    WCr = WHCr + (Y - Ymax) * (Wcr - WHCr) / (Ymax - Kh) ; 
                    WCb = WHCb + (Y - Ymax) * (Wcb - WHCb) / (Ymax - Kh)  ;
                    CrCenter = 154 + (Y - Kh) * (154 - 132) / (Ymax - Kh)  ;
                    CbCenter = 108 + (Y - Kh) * (118 - 108) / (Ymax - Kh)   ;
                 }

                if (Y < Kl || Y > Kh)
                { 
                    Cr = (Cr - CrCenter) * Wcr / WCr + 154   ;           
                    Cb = (Cb - CbCenter) * Wcb / WCb + 108  ;
                }
                //# skin color detection  
                if( Cb > 77 && Cb < 127 && Cr > 133 && Cr < 173)
                {  
                    skin = 1  ;
                }
                if (0 == skin)//非膚色
                {
                   srcMat.mask[r][c]=true;//設定遮罩掉
                }
            }
        }
        return srcMat;
    }
}
