package com.qa.tc.utill;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.qa.tc.constant.FilePaths.BASE_DIRECTORY_FOR_CONFIG;

public class PropertiesReading {

    Logger log = Logger.getLogger(PropertiesReading.class);

    private Properties properties = new Properties();

    public PropertiesReading(String fileName) {
        try {
            properties.load(new FileInputStream(new File(BASE_DIRECTORY_FOR_CONFIG + fileName)));
        } catch (FileNotFoundException e) {
            log.fatal("File not found at place " + BASE_DIRECTORY_FOR_CONFIG + fileName);
        } catch (IOException e) {
            log.fatal("You don't have access on file " + BASE_DIRECTORY_FOR_CONFIG + fileName);
        }
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
