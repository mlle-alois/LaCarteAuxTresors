package org.example.parser.exceptions;

public class InvalidFileFormatException extends RuntimeException {

        public InvalidFileFormatException(String lineType, String line) {
                super(String.format("Invalid line type '%s' for line '%s'", lineType, line));
        }

        public InvalidFileFormatException() {
                super("Invalid file format, no map dimensions found");
        }
}
