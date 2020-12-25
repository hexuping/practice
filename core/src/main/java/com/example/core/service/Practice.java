package com.example.core.service;

public interface Practice {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
