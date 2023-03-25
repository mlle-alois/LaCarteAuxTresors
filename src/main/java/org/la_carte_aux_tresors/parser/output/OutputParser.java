package org.la_carte_aux_tresors.parser.output;

public interface OutputParser<T, U> {
    T parse(U entity);
}
