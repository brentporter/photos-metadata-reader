package org.utexas.magic.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*******
 * Author Brent Porter 2023
 * <a href="https://stackoverflow.com/questions/8285595/reading-properties-file-in-java">...</a>
 ********/

public class ReadInProperties {

    public String[] getProperties(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("conf/txcap-photos.properties"));
            String basereaddir = (String) prop.get("BASEREADDIR");
            String baseurl = (String) prop.get("BASEURL");
            String date = (String) prop.get("DATE");
            String basesortie = (String) prop.get("BASESORTIE");
            String thumbnails = (String) prop.get("THUMBNAILS");
            String extension = (String) prop.get("IMAGEEXTENSION");
            return new String[]{basereaddir, baseurl, date, basesortie, thumbnails, extension};
        } catch (IOException e) {
            System.out.println("Could not find the properties file");
            return new String[]{"Could not find the properties file"};
        }
    }
}
