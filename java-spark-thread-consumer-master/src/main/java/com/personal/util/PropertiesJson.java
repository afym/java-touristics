package com.personal.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PropertiesJson {

    public String getService(String serviceName) throws PropertiesException {
        // TODO : Use Consul or other variable database
        //String path = this.getFile(serviceName.concat(".services.json"));

        if (serviceName == "supplier") {
            return "{\"services\":{\"HTP\":{\"host\":\"providerB-service\",\"port\":\"30000\"},\"EZL\":{\"host\":\"providerA-service\",\"port\":\"30001\"},\"FTV\":{\"host\":\"providerC-service\",\"port\":\"30002\"}}}";
        } else if (serviceName == "supplier.test") {
            return  "{\"services\":{\"HTP\":{\"host\":\"localhost\",\"port\":\"30000\"},\"EZL\":{\"host\":\"localhost\",\"port\":\"30001\"},\"FTV\":{\"host\":\"localhost\",\"port\":\"30002\"}}}";
        }

        return null;
        /*try {
            return this.getFileContents(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PropertiesException("Get the file's content fail");
        }*/
    }

    private String getFile(String configFile) throws PropertiesException {
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            File file = new File(classLoader.getResource("config/" + configFile).getFile());
            return file.getAbsolutePath();
        } catch (Exception e) {
            throw new PropertiesException("The properties files is not found");
        }
    }

    private String getFileContents(String path, Charset encoding) throws IOException{
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
