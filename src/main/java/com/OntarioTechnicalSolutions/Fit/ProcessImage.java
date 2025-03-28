package com.OntarioTechnicalSolutions.Fit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProcessImage {
    private static ProcessImage instance;
    private String pathToImage;

    public ProcessImage() {
        pathToImage = null;
    }

    public static ProcessImage getInstance() {
        if (instance == null) {
            instance = new ProcessImage();
        }
        return instance;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public byte[] getImage() throws IOException {
        File file = new File(pathToImage);
        FileInputStream fis = new FileInputStream(file);

        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        return bytes;
    }


}