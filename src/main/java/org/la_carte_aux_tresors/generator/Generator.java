package org.la_carte_aux_tresors.generator;

public interface Generator<T, U, V> {
    T generate(U location, V content);
}
