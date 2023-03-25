package org.la_carte_aux_tresors.parser.exceptions;

public class InvalidInputFileFormatException extends RuntimeException {

        public InvalidInputFileFormatException() {
                super("Invalid file format, no dimensions found");
        }

        public InvalidInputFileFormatException(String section) {
                super(String.format("Invalid file format, for the section '%s'", section));
        }

        public InvalidInputFileFormatException(String lineType, String line) {
                super(String.format("Invalid line type '%s' for line '%s'", lineType, line));
        }

        public InvalidInputFileFormatException(int numberOfArguments, String lineParts) {
                super(String.format("Invalid number of arguments '%s' for line '%s'", numberOfArguments, lineParts));
        }
}
