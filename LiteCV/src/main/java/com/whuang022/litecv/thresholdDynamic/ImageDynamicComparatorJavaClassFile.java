
package com.whuang022.litecv.thresholdDynamic;


import java.io.IOException;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

public class ImageDynamicComparatorJavaClassFile extends ForwardingJavaFileManager 
{
    private ImageDynamicComparatorJavaObjectFile classObject;
    
    protected ImageDynamicComparatorJavaClassFile(JavaFileManager fileManager) 
    {
        super(fileManager);
    }
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling)
    throws IOException 
    {
        this.classObject = new ImageDynamicComparatorJavaObjectFile(className, kind);
        return classObject;
    }
    
    @Override

    public ClassLoader getClassLoader(Location location) 
    {
        return new ClassLoader() 
       {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException 
            {
                byte[] classBytes = classObject.getClassBytes();
                return super.defineClass(name, classBytes, 0, classBytes.length);
            }
       };
    }
}