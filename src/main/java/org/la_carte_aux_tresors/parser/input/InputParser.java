package org.la_carte_aux_tresors.parser.input;

public interface InputParser<T, U> {
    T parse(U input);
}
