package com.proyecto_2.Backend.ReportesData;

import java.util.*;

import com.proyecto_2.Backend.Token.Token;

public class ReportesData {

    public List<Token> generarListaErroresLexicos(List<Token> tokens) {
        List<Token> erroresLexicos = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getTipo().equals("Error")) {
                erroresLexicos.add(tokens.get(i));
            }
        }

        return erroresLexicos;
    }

    public List<Token> generarListaErroresSintacticos(List<Token> tokens) {
        List<Token> erroresSintacticos = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getDescripcionSintactico() != null) {
                erroresSintacticos.add(tokens.get(i));
            }
        }

        return erroresSintacticos;
    }

}
