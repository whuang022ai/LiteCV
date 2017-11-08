/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whuang022.litecv.thresholdDynamic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import javax.tools.SimpleJavaFileObject;

/**
 *
 * @author user
 */
public class ImageDynamicComparatorJavaObjectFile extends SimpleJavaFileObject {
    //用于存储class字节
    ByteArrayOutputStream outputStream;

    public ImageDynamicComparatorJavaObjectFile(String className, Kind kind) {
        super(URI.create("string:///" + className.replace('.', '/') + kind.extension), kind);
        outputStream = new ByteArrayOutputStream();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return outputStream;
    }

    public byte[] getClassBytes() {
        return outputStream.toByteArray();
    }
}