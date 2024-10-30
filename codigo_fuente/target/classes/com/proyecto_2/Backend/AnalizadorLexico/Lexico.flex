package com.proyecto_2.Backend.AnalizadorLexico;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.proyecto_2.Backend.Token.Token;

%%
%{

// Codigo Java

    private final String[] RESERVADA_WORD = {"CREATE", "DATABASE", "TABLE", "KEY", "NULL", "PRIMARY", "UNIQUE", "FOREIGN", "REFERENCES", "ALTER", "ADD",
            "COLUMN", "TYPE", "DROP", "CONSTRAINT", "IF", "EXIST", "EXISTS", "CASCADE", "ON", "DELETE", "SET", "UPDATE", "INSERT", "INTO", "VALUES",
            "SELECT", "FROM", "WHERE", "AS", "GROUP", "ORDER", "BY", "ASC", "DESC", "LIMIT", "JOIN"};

    private final String[] TIPO_WORD = {"INTEGER", "BIGINT", "VARCHAR", "DECIMAL", "DATE", "TEXT", "BOOLEAN", "SERIAL", "BIT", "CHAR", "DATETIME",
            "FLOAT", "BINARY", "BLOB", "ENUM", "INT", "DOUBLE", "TIMESTAMP", "TIME", "NUMERIC"};

    private List<Token> lista = new ArrayList<>();

    private void compararPalabra(String palabra, int fila, int columna){
        int match = 0;

        for (int i = 0; i < RESERVADA_WORD.length; i++) {
            if (palabra.equals(RESERVADA_WORD[i])) {
                match = 1;
            }
        }

        for (int i = 0; i < TIPO_WORD.length; i++) {
            if (palabra.equals(TIPO_WORD[i])) {
                match = 2;
            }
        }

        if (match == 1) {
            addList(palabra, "Palabra Reservada", fila, columna, Color.ORANGE);
        } else if (match == 2) {
            addList(palabra, "Tipo de Dato", fila, columna, Color.MAGENTA);
        } else {
            addList(palabra, "Error", fila, columna, Color.RED);
        }
    }

    private void addList(String palabra, String tipo, int fila, int columna, Color color) {
        Token token = new Token();
       
        token.setToken(palabra);
        token.setTipo(tipo);
        token.setFila(fila + 1);
        token.setColumna(columna + 1);
        token.setColor(color);

        lista.add(token);
    }

    public List<Token> getLista(){
        return lista;
    }
    
%}

// Configuracion
%public
%class AnalizadorLexico
%unicode
%line
%column
%standalone

// Expresiones Regulares
PALABRA = [A-Z]+
ENTERO = [0-9]+
FECHA = "'"[0-9]{4}"-"[0-9]{2}"-"[0-9]{2}"'"
IDENTIFICADOR = [a-z]([a-z]|[0-9])+("_"([a-z]|[0-9])+)*
BOOLEANO = TRUE|FALSE
AGREGACION = SUM|AVG|COUNT|MAX|MIN
SIGNO = "("|")"|","|";"|"."|"="
ARITMETICOS = "+"|"-"|"/"|"*"
RELACIONALES = "<"|">"|"<="|">="
LOGICOS = AND|OR|NOT
ESPACIOS = [" "]
SALTO = [\r\t\b\n]


%%
// Reglas de Escaneo de Expresiones
{ENTERO}                                    { addList(yytext(), "Entero", yyline, yycolumn, Color.BLUE); }
{ENTERO}"."{ENTERO}                         { addList(yytext(), "Decimal", yyline, yycolumn, Color.BLUE); }
{FECHA}                                     { addList(yytext(), "Fecha", yyline, yycolumn, Color.YELLOW); }
(\'[^\']*\')                                { addList(yytext(), "Cadena", yyline, yycolumn, Color.GREEN); }
{IDENTIFICADOR}                             { addList(yytext(), "Identificador", yyline, yycolumn, Color.PINK); }
{BOOLEANO}                                  { addList(yytext(), "Booleano", yyline, yycolumn, Color.BLUE); }
{AGREGACION}                                { addList(yytext(), "Funcion de Agregación", yyline, yycolumn, Color.BLUE); }
{SIGNO}                                     { addList(yytext(), "Signo", yyline, yycolumn, Color.BLACK); }
{ARITMETICOS}                               { addList(yytext(), "Aritmetico", yyline, yycolumn, Color.BLACK); }
{RELACIONALES}                              { addList(yytext(), "Relacional", yyline, yycolumn, Color.BLACK); }
{LOGICOS}                                   { addList(yytext(), "Lógico", yyline, yycolumn, Color.ORANGE); }
(- - [^{SALTO}])                            { addList(yytext(), "Comentario", yyline, yycolumn, Color.GRAY); }
{PALABRA}                                   { compararPalabra(yytext(), yyline, yycolumn); }

{ESPACIOS}                                  { /*Ignore*/ }
{SALTO}                                     { /*Ignore*/ }

.                                           { addList(yytext(), "Error", yyline, yycolumn, Color.RED); }
