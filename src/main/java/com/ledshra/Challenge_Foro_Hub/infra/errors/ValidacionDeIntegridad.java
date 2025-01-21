package com.ledshra.Challenge_Foro_Hub.infra.errors;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String s) {

        super(s);
    }
}