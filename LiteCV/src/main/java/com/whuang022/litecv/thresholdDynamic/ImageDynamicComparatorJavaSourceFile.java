
package com.whuang022.litecv.thresholdDynamic;


import java.net.URI;
import javax.tools.SimpleJavaFileObject;
/**
 * 存放Java原始碼的字串物件 繼承 SimpleJavaFileObject
*/
public class ImageDynamicComparatorJavaSourceFile extends SimpleJavaFileObject 
{
    final String code;
    
    ImageDynamicComparatorJavaSourceFile(String name, String code) 
    {
        super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) 
    {
        return code;
    }
}