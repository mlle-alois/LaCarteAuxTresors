package org.example.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator implements Generator<File, String, String> {
    @Override
    public File generate(String path, String content) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new File(path);
    }
}
