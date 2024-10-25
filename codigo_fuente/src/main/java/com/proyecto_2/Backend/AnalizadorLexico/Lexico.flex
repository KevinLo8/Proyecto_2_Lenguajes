package com.proyecto_2.Backend.AnalizadorLexico;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.proyecto_2.Backend.Token.Token;

%%
%{

// Codigo Java

    private List<Token> lista = new ArrayList<>();
    private List<Token> listaErrores = new ArrayList<>();

    private void addList(String palabra, String tipo, int fila, int columna, Color color) {
        Token token = new Token();
       
        token.setToken(palabra);
        token.setTipo(tipo);
        token.setFila(fila + 1);
        token.setColumna(columna + 1);
        token.setColor(color);

        lista.add(token);
    }

    private void addListaErrores(String palabra, int fila, int columna) {
        Token token = new Token();
        
        token.setToken(palabra);
        token.setTipo("Error");
        token.setFila(fila + 1);
        token.setColumna(columna + 1);

        listaErrores.add(token);
    }

    public List<Token> getLista(){
        return lista;
    }
    
    public List<Token> getListaErrores(){
        return listaErrores;
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
CREATE = CREATE|DATABASE|TABLE|KEY|NULL|PRIMARY|UNIQUE|FOREIGN|REFERENCES|ALTER|ADD|COLUMN|TYPE|DROP|CONSTRAINT|IF|EXIST|CASCADE|ON|DELETE|SET|UPDATE|INSERT|INTO|VALUES|SELECT|FROM|WHERE|AS|GROUP|ORDER|BY|ASC|DESC|LIMIT|JOIN
DATO = INTEGER|BIGINT|VARCHAR|DECIMAL|DATE|TEXT|BOOLEAN|SERIAL
ENTERO = [0-9]+
FECHA = "'"[0-9]{4}"-"[0-9]{2}"-"[0-9][2]"'"
IDENTIFICADOR = [a-z]+("_"[a-z]+)*
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
{CREATE}                                    { addList(yytext(), "Create", yyline, yycolumn, Color.ORANGE); }
{DATO}                                      { addList(yytext(), "Tipo de Date", yyline, yycolumn, Color.MAGENTA); }
{ENTERO}                                    { addList(yytext(), "Entero", yyline, yycolumn, Color.BLUE); }
{ENTERO}"."{ENTERO}                         { addList(yytext(), "Decimal", yyline, yycolumn, Color.BLUE); }
{FECHA}                                     { addList(yytext(), "Fecha", yyline, yycolumn, Color.YELLOW); }
(\'[^\']*\')                                { addList(yytext(), "Cadena", yyline, yycolumn, Color.GREEN); }
{IDENTIFICADOR}                             { addList(yytext(), "Identificador", yyline, yycolumn, Color.PINK); }
{BOOLEANO}                                  { addList(yytext(), "Booleano", yyline, yycolumn, Color.BLUE); }
{AGREGACION}                                { addList(yytext(), "Funcion de Agregacion", yyline, yycolumn, Color.BLUE); }
{SIGNO}                                     { addList(yytext(), "Signo", yyline, yycolumn, Color.BLACK); }
{ARITMETICOS}                               { addList(yytext(), "Aritmetico", yyline, yycolumn, Color.BLACK); }
{RELACIONALES}                              { addList(yytext(), "Relacional", yyline, yycolumn, Color.BLACK); }
{LOGICOS}                                   { addList(yytext(), "Logico", yyline, yycolumn, Color.ORANGE); }
(- - [^{SALTO}])                            { addList(yytext(), "Comentario", yyline, yycolumn, Color.GRAY); }

{ESPACIOS}                                  { /*Ignore*/ }
{SALTO}                                     { /*Ignore*/ }

.                                           { addListaErrores(yytext(), yyline, yycolumn); }