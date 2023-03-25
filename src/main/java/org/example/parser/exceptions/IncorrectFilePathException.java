package org.example.parser.exceptions;

public class IncorrectFilePathException extends RuntimeException {

    public IncorrectFilePathException(String filePath) {
        super("The file path " + filePath + " is incorrect, no file found.");
    }
}
