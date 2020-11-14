package com.personal.suppliers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by angel on 7/10/17.
 */
public class BaseHandler {
    protected String getResponseFromFile(String filePath)  {
        // TODO : put this code into lib.generic.util
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(filePath).getFile());
            String path = file.getAbsolutePath();
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }
}
