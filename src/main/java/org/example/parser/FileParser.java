package org.example.parser;

public interface FileParser<T> {
    T parse(String filePath);
}
