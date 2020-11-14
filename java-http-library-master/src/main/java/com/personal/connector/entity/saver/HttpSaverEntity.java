package com.personal.connector.entity.saver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpSaverEntity {
    private String path;
    private String fileName;

    public HttpSaverEntity(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
        this.makeDirs();
    }

    public String getFilePath() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.path).append(this.fileName);
        return builder.toString();
    }

    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(this.getFilePath(), true);
    }

    public void deleteFile() {
        File file = new File(this.getFilePath());
        file.delete();
    }

    private void makeDirs() {
        File file = new File(this.path);

        if (!(file.exists() && file.isDirectory())) {
            file.mkdirs();
        }
    }
}
