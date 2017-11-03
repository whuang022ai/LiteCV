package com.whuang022.litecv.kernel;

import java.util.Arrays;

/**
 *
 * @author user
 */
public class ImageMedianFilterKernel implements ImageSortKernalInterface {

    @Override
    public int getCenter(int []data) 
    {  
        Arrays.sort(data);
        return data[data.length/2];
    }
    
}
