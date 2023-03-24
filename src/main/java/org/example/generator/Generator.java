package org.example.generator;

public interface Generator<T> {
    T generate(String content);
}
