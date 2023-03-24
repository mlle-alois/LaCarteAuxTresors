package org.example.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileGenerator implements Generator<File> {

    private static final String OUTPUT_FILE_NAME = "carte-output.txt";

    @Override
    public File generate(String content) {
        try {
            FileWriter writer = new FileWriter(OUTPUT_FILE_NAME);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new File(OUTPUT_FILE_NAME);
    }
}
