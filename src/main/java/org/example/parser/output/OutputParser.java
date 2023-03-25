package org.example.parser.output;

public interface OutputParser<T, U> {
    T parse(U entity);
}
