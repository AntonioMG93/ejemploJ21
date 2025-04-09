package edifactwsradiopolis.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author coordinador
 */
public class Propiedades {
    
    public String getPropValues(String key) throws IOException {
        String result = "";
        Properties prop = new Properties(); 
        
        String propFileName = "D://edifactmx_radiopolis//radiopolis.properties"; //windows
//        String propFileName = "/edifactmx_radiopolis/radiopolis.properties"; //linux .151

       
        //InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        InputStream inputStream = new FileInputStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        //Date time = new Date(System.currentTimeMillis());

        // get the property value and print it out
        result = prop.getProperty(key);

        return result;
    }       
}

