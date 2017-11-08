
package com.whuang022.litecv.thresholdDynamic;


import com.whuang022.litecv.filter.ImageFilterConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ImageDynamicComparatorFactoryTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {

        ImageFilterConfig config=new ImageFilterConfig();
        config.setting.put("H", "336");
        try 
        {
            ImageDynamicComparator compare=ImageDynamicComparatorFactory.getComparator("((H >= 0)&& (H <= 25 )) || ((H >= 335 ) && (H <= 360 ))", "test");
            boolean out=compare.threshold(config);
            System.out.println(out);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ImageDynamicComparatorFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
