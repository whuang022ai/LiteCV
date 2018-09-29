package com.whuang022.litecv.colorspace;

import java.util.Vector;

/**
 *
 * @author user
 */
public class ImageColorSpaceConverter 
{
    public static Vector<Double> RGBtoYCbCr(int R,int G,int B)
    {
        Vector<Double> YCbCr=new Vector<>();
        double Y=0,Cr=0,Cb=0;
        Y=0.257*R + 0.504*G + 0.098*B + 16;
        Cb=-0.148*R - 0.291*G + 0.439*B + 128;
        Cr=0.439*R - 0.368*G - 0.071*B + 128;
        YCbCr.add(Y);
        YCbCr.add(Cb);
        YCbCr.add(Cr);
        return YCbCr;
    }
    public static int RGBtoGray(int R,int G,int B)
    {
       return  (int) Math.round(R*ImageColorVector.grayR+ G*ImageColorVector.grayG+  B*ImageColorVector.grayB);
    }
    public static Vector<Double> RGBtoHSV(int R,int G,int B)
    {
        Vector<Double> hsv=new Vector<>();
        double H=0,S=0,V=0;
        double max = R;
        double min = R;
        if (G > max) {
            max = G;
        }
        if (B> max) {
            max = B;
        }

        if (G < min) {
            min = G;
        }
        if (B < min) {
            min = B;
        }
        //
        V=max;
        double D=max-min;
        if(R==G&&G==B)
        {
            H=0;
        }
        else
        {
            double theta=( (0.5*((R-G)+(R-B)))/Math.sqrt( (R-G)*(R-G)+(R-B)*(G-B)));
            H=Math.acos(theta);
        }
        if(B>G)
        {
            H=2*Math.PI-H;
        }
        if(max>0)
        {
            S=D/max;
        }
        else
        {
            S=0;
        }
        hsv.add(H);
        hsv.add(S);
        hsv.add(V);
        return hsv;
    }
    public static Vector<Integer> HSVtoRGB(  double h,  double s,  double v )
    {
        Vector<Integer> rgb=new Vector<>();
        double r=0; double g=0; double b=0;
        int i;
        double f, p, q, t;
        if( s == 0 ) 
        {
          r = g = b = v;
          rgb.add(0);
          rgb.add(0);
          rgb.add(0);
          return  rgb;
        }
        h /= 60;			// sector 0 to 5
        i = (int) Math.floor(h);
        f = h - i;			// factorial part of h
        p = v * ( 1 - s );
        q = v * ( 1 - s * f );
        t = v * ( 1 - s * ( 1 - f ) );
        switch( i ) 
        {
            case 0:
                    r = v;
                    g = t;
                    b = p;
                    break;
            case 1:
                    r = q;
                    g = v;
                    b = p;
                    break;
            case 2:
                    r = p;
                    g = v;
                    b = t;
                    break;
            case 3:
                    r = p;
                    g = q;
                    b = v;
                    break;
            case 4:
                    r = t;
                    g = p;
                    b = v;
                    break;
            default:	
                    r = v;
                    g = p;
                    b = q;
                    break;
        }
        rgb.add((int)Math.round(r));
        rgb.add((int)Math.round(g));
        rgb.add((int)Math.round(b));
        return rgb;
    }
}
