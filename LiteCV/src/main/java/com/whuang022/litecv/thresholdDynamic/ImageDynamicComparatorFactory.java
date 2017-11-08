
package com.whuang022.litecv.thresholdDynamic;

import java.util.ArrayList;
import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * 動態比較子生成工廠
 * @author whuang022
 */
public class ImageDynamicComparatorFactory 
{

    public static ImageDynamicComparator getComparator(String condition,String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        String packageName=ImageDynamicComparatorFactory.class.getPackage().getName();
        String  ImageDynamicComparatorClass=className;
        ArrayList<Token> tokens=parse(condition);//剖析運算式
        //生成取值代碼
        String code="";
        ArrayList<String> isUsed=new  ArrayList<>();//已經生成過的
        for(Token tocken:tokens)
        {
            boolean used=false;
            for(String tokenUsed:isUsed)
            {
                if(tocken.text.equals(tokenUsed))
                {
                    used=true;
                    break;
                }
            }
            if(tocken.state.equals(State.Identifier)&&!used)
            {
                code+="Double "+tocken.text+"=Double.parseDouble(config.setting.get(\""+tocken.text+"\"));";
            }
            isUsed.add(tocken.text);
            
        }
        String str = "import "+packageName+".ImageDynamicComparator; "
                   +"import com.whuang022.litecv.filter.ImageFilterConfig;" 
        + "public class "+ImageDynamicComparatorClass+" implements ImageDynamicComparator "
        + "{" 
            + "public boolean  threshold(ImageFilterConfig config) "
            + "{"
               + code
               +"if("+condition+")" 
               +"{" 
                    +"return true;" 
               +"}" 
                    +"return false;"
            + "}"
        + "}";
        SimpleJavaFileObject fileObject = new ImageDynamicComparatorJavaSourceFile(ImageDynamicComparatorClass, str);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager fileManager = new ImageDynamicComparatorJavaClassFile(compiler.getStandardFileManager(null, null, null));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Arrays.asList(fileObject));
        task.call();
        ClassLoader classLoader = fileManager.getClassLoader(null);
        Class printerClass = classLoader.loadClass(ImageDynamicComparatorClass);
        return (ImageDynamicComparator) printerClass.newInstance();
    }
    private static ArrayList<Token> parse(String condition)
    {
        String str=condition;
        ArrayList<Token> tokens=new ArrayList<>();
        for(int i=0;i<str.length();i++)
        {
            char c=str.charAt(i);
              if(c=='/'&&str.charAt(i+1)=='/')//標示註解
            {
                Token t =new Token();
                t.state=State.Annotation;
                String tmpS="";
                for(int k=i;k<str.length();k++)
                {
                    tmpS+=str.charAt(k);
                }
                t.text=tmpS;
                tokens.add(t);
                break;
            }
            else  if(c==' ')////標示空白
            {
                Token t =new Token();
                t.state=State.Space;
                t.text=""+c;
                tokens.add(t);
            }
            else  if(c=='R'||c=='G'||c=='B'||c=='H'||c=='S'||c=='V')////標示 關鍵字
            {
               Token t =new Token();
               t.state=State.Identifier;
               t.text=""+c;
               tokens.add(t);

            }
            else  if(c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9')////標示 數字
            {
                Token t =new Token();
                t.state=State.Number;
                String tmpS="";
                tmpS+=str.charAt(i);
                while(true)
                {
                    if(i==str.length()-1){break;}
                    i++;
                    c=str.charAt(i);
                    if(c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9'||c=='.')
                    {
                        tmpS+=str.charAt(i);
                    }
                    else
                    {
                       i--;
                       break; 
                    }
                }
                t.text=tmpS;
                double n=Double.parseDouble(tmpS);
                t.value=n;
                tokens.add(t);
            }
            else
            {
               Token t =new Token();
               t.state=State.Normal;
               t.text=""+c;
               tokens.add(t);
            }
        }
        return tokens;
    }
}

class Token
{
    State  state;
    String text="";
    double value=0;
    Token(){}
    Token(State s,double value)
    {
        this.state=s;
        this.value=value;
    }
}
enum State 
{
    Normal,Annotation,Space,Number,Identifier;
}