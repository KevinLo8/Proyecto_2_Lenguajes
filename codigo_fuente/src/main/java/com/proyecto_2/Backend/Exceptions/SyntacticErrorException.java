package com.proyecto_2.Backend.Exceptions;

public class SyntacticErrorException extends Exception{
    private String error;

    public SyntacticErrorException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
