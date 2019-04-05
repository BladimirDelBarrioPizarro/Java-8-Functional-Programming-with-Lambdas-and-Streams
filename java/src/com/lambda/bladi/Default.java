package com.lambda.bladi;

public interface Default {
    void showName(String name);
    default String nameDefault(String nombre){
        return  nombre+" Default ";
    }
}
