package org.example.parser.input;

public interface InputParser<T, U> {
    T parse(U input);
}
