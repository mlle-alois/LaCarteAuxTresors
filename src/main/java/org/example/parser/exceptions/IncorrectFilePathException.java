package org.example.parser.exceptions;

import org.example.model.enums.CaseType;

public class IncorrectFilePathException extends RuntimeException {

    public IncorrectFilePathException(String filePath) {
        super("The file path " + filePath + " is incorrect, no file found.");
    }
}
