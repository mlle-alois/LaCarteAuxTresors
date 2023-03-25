package org.example.generator;

public interface Generator<T, U, V> {
    T generate(U location, V content);
}
