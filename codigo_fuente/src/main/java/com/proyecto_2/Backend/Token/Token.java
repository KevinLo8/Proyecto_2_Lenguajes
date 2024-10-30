package com.proyecto_2.Backend.Token;

import java.awt.Color;

public class Token {

    private String token;
    private String tipo;
    private int fila;
    private int columna;
    private String descripcion;
    private String descripcionSintactico;
    private Color color;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionSintactico() {
        return descripcionSintactico;
    }

    public void setDescripcionSintactico(String descripcionSintactico) {
        this.descripcionSintactico = descripcionSintactico;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
