package com.personal.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private Properties properties;
    private String filePath;

    public static Configuration getInstance(String filePath) {
        return new Configuration(filePath);
    }

    public Configuration(String filePath) {
        this.filePath = filePath;
        this.properties = new Properties();
        this.init();
    }

    public String get(String key) {
        return this.properties.getProperty(key);
    }

    private void init() {
        InputStream inputStream = null;

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream( this.filePath + ".properties");
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
