package com.fnb.utils.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static Properties properties;
    private static FileInputStream fileIn;

    //path
    private static String propertiesFilePathRoot = "src/test/resources/environment/";

    //private static String fileName = "src/test/resources/dev.properties";
    public PropertiesReader(String fileName) throws IOException {
        properties = new Properties();
        //Create object of class FileInputStream
        fileIn = new FileInputStream(propertiesFilePathRoot + fileName + ".properties");
        Log.info(propertiesFilePathRoot + fileName + ".properties");
        //Load properties file
        properties.load(fileIn);
    }

    //get value from .properties file
    public static String getPropValue(String KeyProp) {
        String value = null;
        //get values from properties file
        value = properties.getProperty(KeyProp);
        Log.info(value);
        return value;

    }

    //set value to .properties file
//    public static void setPropValue(String KeyProp, String Value) throws IOException {
//            fileOut = new FileOutputStream(projectPath + propertiesFilePathRoot +fileName);
//            properties.setProperty(KeyProp, Value);
//            properties.store(fileOut, "Set new value in properties file");
//            System.out.println("Set new value in file properties success.");
//    }

}
