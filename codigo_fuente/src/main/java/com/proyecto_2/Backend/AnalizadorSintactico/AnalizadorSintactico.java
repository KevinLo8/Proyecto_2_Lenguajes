package com.proyecto_2.Backend.AnalizadorSintactico;

import java.util.*;

import com.proyecto_2.Backend.Exceptions.SyntacticErrorException;
import com.proyecto_2.Backend.Token.Token;

public class AnalizadorSintactico {

    private List<Token> tokens;
    private List<Token> tablas;
    private List<Token> tablasModificadas;
    private int numToken = 0;
    private int numActual = 0;
    private String tipo, palabra;
    private int cantidadCreate = 0;
    private int cantidadAlter = 0;
    private int cantidadInsert = 0;
    private int cantidadSelect = 0;
    private int cantidadUpdate = 0;
    private int cantidadDelete = 0;

    public AnalizadorSintactico(List<Token> tokens) {
        this.tokens = tokens;
        tablas = new ArrayList<>();
        tablasModificadas = new ArrayList<>();
    }

    public void analizar() {
        for (int i = 0; i < tokens.size();) {

            List<Token> tokensComando = new ArrayList<>();
            numActual = 0;
            do {
                tokensComando.add(tokens.get(i));
                i++;
            } while ((!tokens.get(i - 1).getToken().equals(";") || !tokens.get(i - 1).getTipo().equals("Signo"))
                    && i < tokens.size());

            if (tokensComando.get(numActual).getTipo().equals("Palabra Reservada")) {
                try {
                    switch (tokensComando.get(0).getToken()) {
                        case "CREATE":
                            revisarCreate(tokensComando);
                            cantidadCreate++;
                            break;
                        case "ALTER":
                            revisarAlter(tokensComando);
                            cantidadAlter++;
                            break;
                        case "DROP":
                            revisarDrop(tokensComando);
                            cantidadAlter++;
                            break;
                        case "INSERT":
                            revisarInsert(tokensComando);
                            cantidadInsert++;
                            break;
                        case "SELECT":
                            revisarSelect(tokensComando);
                            cantidadSelect++;
                            break;
                        case "UPDATE":
                            revisarUpdate(tokensComando);
                            cantidadUpdate++;
                            break;
                        case "DELETE":
                            revisarDelete(tokensComando);
                            cantidadDelete++;
                            break;
                    }
                } catch (SyntacticErrorException e) {
                    tokens.get(numToken).setDescripcionSintactico(e.getError());
                    numToken = numToken + tokensComando.size() - numActual;
                }
            } else {
                tokens.get(numToken).setDescripcionSintactico("Secuencia de token invalida");
                numToken = numToken + tokensComando.size();
            }
        }
    }

    public List<Token> getTablas() {
        return tablas;
    }

    public List<Token> getTablasModificadas() {
        return tablasModificadas;
    }

    public List<Integer> getCantidades() {
        List<Integer> lista = new ArrayList<>();

        lista.add(cantidadCreate);
        lista.add(cantidadAlter);
        lista.add(cantidadInsert);
        lista.add(cantidadSelect);
        lista.add(cantidadUpdate);
        lista.add(cantidadDelete);

        return lista;
    }

    private void addNum() {
        numToken++;
        numActual++;
    }

    private void revisarCreate(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("DATABASE") && !palabra.equals("TABLE")) {
            throw new SyntacticErrorException("Se esperaba un token \"DATABASE\" o \"TABLE\"");
        }
        addNum();

        revisarIdentificador(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Signo")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (palabra.equals(";")) {
            if (tokensComando.size() > 4) {
                addNum();
                throw new SyntacticErrorException("Secuencia de token invalida");
            }
        } else if (palabra.equals("(")) {
            revisarTable(tokensComando);
        } else {
            throw new SyntacticErrorException("Se esperaba un token \";\" o \"(\"");
        }
    }

    private void revisarTable(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();
        int cantidad = 0;

        do {

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (tipo.equals("Identificador")) {
                revisarEstructuraDeclaracion(tokensComando);
                cantidad++;
            } else if (tipo.equals("Palabra Reservada")) {
                revisarEstructuraLlaves(tokensComando);
            } else {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }

            revisarDobleSigno(tokensComando, ",", ")");

        } while (!palabra.equals(")"));

        revisarSigno(tokensComando, ";");

        Token token = new Token();
        token.setToken(tokensComando.get(1).getToken());
        token.setFila(tokensComando.get(0).getFila());
        token.setColumna(tokensComando.get(0).getColumna());
        token.setDescripcion(String.valueOf(cantidad));

        tablas.add(token);

    }

    private void revisarEstructuraDeclaracion(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();

        revisarTipoDato(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (tipo.equals("Palabra Reservada")) {
            addNum();

            if (palabra.equals("PRIMARY")) {

                tipo = tokensComando.get(numActual).getTipo();
                palabra = tokensComando.get(numActual).getToken();
                if (!tipo.equals("Palabra Reservada")) {
                    throw new SyntacticErrorException("Secuencia de token invalida");
                }
                if (!palabra.equals("KEY")) {
                    throw new SyntacticErrorException("Se esperaba un token \"KEY\"");
                }
                addNum();

            } else if (!palabra.equals("UNIQUE")) {
                throw new SyntacticErrorException("Se esperaba un token \"PRIMARY\" o \"UNIQUE\"");
            }

        } else if (tipo.equals("Lógico")) {

            if (!palabra.equals("NOT")) {
                throw new SyntacticErrorException("Se esperaba un token \"NOT\"");
            }
            addNum();

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (!tipo.equals("Palabra Reservada")) {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }
            if (!palabra.equals("NULL")) {
                throw new SyntacticErrorException("Se esperaba un token \"NULL\"");
            }
            addNum();

        }
    }

    private void revisarEstructuraLlaves(List<Token> tokensComando) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("CONSTRAINT")) {
            throw new SyntacticErrorException("Se esperaba un token \"CONSTRAINT\"");
        }
        addNum();

        revisarIdentificador(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("FOREIGN")) {
            throw new SyntacticErrorException("Se esperaba un token \"FOREIGN\"");
        }
        addNum();

        revisarForeignKey(tokensComando);

    }

    private void revisarAlter(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("TABLE")) {
            throw new SyntacticErrorException("Se esperaba un token \"TABLE\"");
        }
        addNum();

        revisarIdentificador(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (palabra.equals("ADD")) {
            addNum();

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            String palabra2 = tokensComando.get(numActual).getToken();
            if (!tipo.equals("Palabra Reservada")) {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }
            if (!palabra.equals("COLUMN") && !palabra.equals("CONSTRAINT")) {
                throw new SyntacticErrorException("Se esperaba un token \"COLUMN\" o \"CONSTRAINT\"");
            }
            addNum();

            revisarIdentificador(tokensComando);

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (tipo.equals("Tipo de Dato")) {
                revisarTipoDato(tokensComando);
            } else if (tipo.equals("Palabra Reservada") && palabra2.equals("CONSTRAINT")) {
                if (palabra.equals("UNIQUE")) {
                    addNum();

                    revisarSigno(tokensComando, "(");

                    revisarIdentificador(tokensComando);

                    revisarSigno(tokensComando, ")");

                } else if (palabra.equals("FOREIGN")) {
                    addNum();

                    revisarForeignKey(tokensComando);
                } else {
                    throw new SyntacticErrorException("Se esperaba un token \"UNIQUE\" o \"FOREIGN\"");
                }
            } else {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }

        } else if (palabra.equals("ALTER") || palabra.equals("DROP")) {

            String palabra2 = tokensComando.get(numActual).getToken();
            addNum();

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (!tipo.equals("Palabra Reservada")) {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }
            if (!palabra.equals("COLUMN")) {
                throw new SyntacticErrorException("Se esperaba un token \"COLUMN\"");
            }
            addNum();

            revisarIdentificador(tokensComando);

            if (palabra2.equals("ALTER")) {

                tipo = tokensComando.get(numActual).getTipo();
                palabra = tokensComando.get(numActual).getToken();
                if (!tipo.equals("Palabra Reservada")) {
                    throw new SyntacticErrorException("Secuencia de token invalida");
                }
                if (!palabra.equals("TYPE")) {
                    throw new SyntacticErrorException("Se esperaba un token \"TYPE\"");
                }
                addNum();

                revisarTipoDato(tokensComando);

            }

        } else {
            throw new SyntacticErrorException("Se esperaba un token \"ADD\", \"ALTER\" o \"DROP\"");
        }

        revisarSigno(tokensComando, ";");

        Token token = new Token();

        token.setToken(tokensComando.get(2).getToken());
        token.setDescripcion(tokensComando.get(3).getToken());
        token.setFila(tokensComando.get(0).getFila());
        token.setColumna(tokensComando.get(0).getColumna());

        tablasModificadas.add(token);

    }

    private void revisarDrop(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("TABLE")) {
            throw new SyntacticErrorException("Se esperaba un token \"TABLE\"");
        }
        addNum();

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("IF")) {
            throw new SyntacticErrorException("Se esperaba un token \"IF\"");
        }
        addNum();

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("EXISTS")) {
            throw new SyntacticErrorException("Se esperaba un token \"EXISTS\"");
        }
        addNum();

        revisarIdentificador(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("CASCADE")) {
            throw new SyntacticErrorException("Se esperaba un token \"CASCADE\"");
        }
        addNum();

        revisarSigno(tokensComando, ";");

    }

    private void revisarInsert(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("INTO")) {
            throw new SyntacticErrorException("Se esperaba un token \"INTO\"");
        }
        addNum();

        revisarIdentificador(tokensComando);

        revisarSigno(tokensComando, "(");

        int cantidadDatos = 0;
        do {

            revisarIdentificador(tokensComando);
            cantidadDatos++;

            revisarDobleSigno(tokensComando, ",", ")");

        } while (!palabra.equals(")"));

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("VALUES")) {
            throw new SyntacticErrorException("Se esperaba un token \"VALUES\"");
        }
        addNum();

        do {

            revisarSigno(tokensComando, "(");

            for (int i = 0; i < cantidadDatos - 1; i++) {

                revisarSeccionDato(tokensComando);

                revisarSigno(tokensComando, ",");

            }

            revisarSeccionDato(tokensComando);

            revisarSigno(tokensComando, ")");

            revisarDobleSigno(tokensComando, ",", ";");

        } while (!palabra.equals(";"));

    }

    private void revisarSelect(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();

        revisarSelecionColumna(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("FROM")) {
            throw new SyntacticErrorException("Se esperaba un token \"FROM\"");
        }
        addNum();

        revisarIdentificador(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        if (tipo.equals("Identificador")) {
            revisarIdentificador(tokensComando);
        }

        do {

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (tipo.equals("Signo")) {
                revisarSigno(tokensComando, ";");
            } else if (tipo.equals("Palabra Reservada")) {
                if (palabra.equals("JOIN") || palabra.equals("WHERE") || palabra.equals("GROUP")
                        || palabra.equals("ORDER")
                        || palabra.equals("LIMIT")) {
                    revisarSentencia(tokensComando);
                } else {
                    throw new SyntacticErrorException("Se esperaba un token \";\"");
                }
            } else {
                throw new SyntacticErrorException("Se esperaba un token \";\"");
            }

        } while (!palabra.equals(";"));

    }

    private void revisarUpdate(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();

        revisarIdentificador(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("SET")) {
            throw new SyntacticErrorException("Se esperaba un token \"SET\"");
        }
        addNum();

        do {

            revisarIdentificador(tokensComando);

            revisarSigno(tokensComando, "=");

            revisarDatoUpdate(tokensComando);

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (tipo.equals("Signo") && palabra.equals(",")) {
                addNum();
            }

        } while (tipo.equals("Signo") && palabra.equals(","));

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (tipo.equals("Palabra Reservada") && palabra.equals("WHERE")) {
            addNum();
            revisarWhere(tokensComando);
        }

        revisarSigno(tokensComando, ";");

    }

    private void revisarDatoUpdate(List<Token> tokensComando) throws SyntacticErrorException {

        do {

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            addNum();

            if (!tipo.equals("Entero") && !tipo.equals("Decimal") && !tipo.equals("Fecha")
                    && !tipo.equals("Cadena") && !tipo.equals("Booleano") && !tipo.equals("Identificador")) {
                throw new SyntacticErrorException(
                        "Se esperaba un token <Entero>, <Decimal>, <Fecha>, <Cadena>, <Booleano> o <Identificador>");
            } else if (tipo.equals("Signo") && palabra.equals("(")) {

                revisarDato(tokensComando);

                revisarSigno(tokensComando, ")");
            }

            tipo = tokensComando.get(numActual).getTipo();
            if (tipo.equals("Aritmetico") || tipo.equals("Relacional") || tipo.equals("Lógico")) {
                addNum();
            }

        } while (tipo.equals("Aritmetico") || tipo.equals("Relacional") || tipo.equals("Lógico"));
    }

    private void revisarDelete(List<Token> tokensComando) throws SyntacticErrorException {
        addNum();

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("FROM")) {
            throw new SyntacticErrorException("Se esperaba un token \"FROM\"");
        }
        addNum();

        revisarIdentificador(tokensComando);

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (tipo.equals("Palabra Reservada") && palabra.equals("WHERE")) {
            addNum();
            revisarWhere(tokensComando);
        }

        revisarSigno(tokensComando, ";");

    }

    private void revisarTipoDato(List<Token> tokensComando) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Tipo de Dato")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        addNum();

        if (palabra.equals("VARCHAR")) {

            revisarSigno(tokensComando, "(");

            revisarEntero(tokensComando);

            revisarSigno(tokensComando, ")");

        } else if (palabra.equals("DECIMAL") || palabra.equals("NUMERIC")) {

            revisarSigno(tokensComando, "(");

            revisarEntero(tokensComando);

            revisarSigno(tokensComando, ",");

            revisarEntero(tokensComando);

            revisarSigno(tokensComando, ")");

        }

    }

    private void revisarForeignKey(List<Token> tokensComando) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("KEY")) {
            throw new SyntacticErrorException("Se esperaba un token \"KEY\"");
        }
        addNum();

        revisarSigno(tokensComando, "(");

        revisarIdentificador(tokensComando);

        revisarSigno(tokensComando, ")");

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Palabra Reservada")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals("REFERENCES")) {
            throw new SyntacticErrorException("Se esperaba un token \"REFERENCES\"");
        }
        addNum();

        revisarIdentificador(tokensComando);

        revisarSigno(tokensComando, "(");

        revisarIdentificador(tokensComando);

        revisarSigno(tokensComando, ")");

    }

    private void revisarSeccionDato(List<Token> tokensComando) throws SyntacticErrorException {

        do {

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            addNum();

            if (!tipo.equals("Entero") && !tipo.equals("Decimal") && !tipo.equals("Fecha")
                    && !tipo.equals("Cadena") && !tipo.equals("Booleano")) {
                throw new SyntacticErrorException(
                        "Se esperaba un token <Entero>, <Decimal>, <Fecha>, <Cadena> o <Booleano>");
            } else if (tipo.equals("Signo") && palabra.equals("(")) {

                revisarDato(tokensComando);

                revisarSigno(tokensComando, ")");
            }

            tipo = tokensComando.get(numActual).getTipo();
            if (tipo.equals("Aritmetico") || tipo.equals("Relacional") || tipo.equals("Lógico")) {
                addNum();
            }

        } while (tipo.equals("Aritmetico") || tipo.equals("Relacional") || tipo.equals("Lógico"));

    }

    private void revisarSelecionColumna(List<Token> tokensComando) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (tipo.equals("Aritmetico")) {
            if (!palabra.equals("*")) {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }
            addNum();
        } else {

            do {
                tipo = tokensComando.get(numActual).getTipo();
                palabra = tokensComando.get(numActual).getToken();

                if (tipo.equals("Identificador")) {
                    addNum();

                    tipo = tokensComando.get(numActual).getTipo();
                    palabra = tokensComando.get(numActual).getToken();
                    if (tipo.equals("Signo") && palabra.equals(".")) {
                        addNum();

                        revisarIdentificador(tokensComando);
                    }
                } else if (tipo.equals("Funcion de Agregación")) {
                    addNum();

                    revisarSigno(tokensComando, "(");

                    revisarIdentificador(tokensComando);

                    revisarSigno(tokensComando, ")");
                } else {
                    throw new SyntacticErrorException("Secuencia de token invalida");
                }

                tipo = tokensComando.get(numActual).getTipo();
                palabra = tokensComando.get(numActual).getToken();
                if (tipo.equals("Palabra Reservada") && palabra.equals("AS")) {
                    addNum();

                    revisarIdentificador(tokensComando);

                    tipo = tokensComando.get(numActual).getTipo();
                    palabra = tokensComando.get(numActual).getToken();
                }
                if (tipo.equals("Signo") && palabra.equals(",")) {
                    addNum();
                }

            } while (tipo.equals("Signo") && palabra.equals(","));

        }

    }

    private void revisarSentencia(List<Token> tokensComando) throws SyntacticErrorException {
        String palabra2 = tokensComando.get(numActual).getToken();
        addNum();

        if (palabra.equals("JOIN")) {
            revisarIdentificador(tokensComando);

            revisarIdentificador(tokensComando);

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (!tipo.equals("Palabra Reservada")) {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }
            if (!palabra.equals("ON")) {
                throw new SyntacticErrorException("Se esperaba un token \"ON\"");
            }
            addNum();

            revisarIdentificador(tokensComando);

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (tipo.equals("Signo") && palabra.equals(".")) {
                addNum();

                revisarIdentificador(tokensComando);
            }

            revisarSigno(tokensComando, "=");

            revisarIdentificador(tokensComando);

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (tipo.equals("Signo") && palabra.equals(".")) {
                addNum();

                revisarIdentificador(tokensComando);
            }

        } else if (palabra.equals("WHERE")) {
            revisarWhere(tokensComando);
        } else if (palabra.equals("GROUP") || palabra.equals("ORDER")) {

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (!tipo.equals("Palabra Reservada")) {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }
            if (!palabra.equals("BY")) {
                throw new SyntacticErrorException("Se esperaba un token \"BY\"");
            }
            addNum();

            revisarIdentificador(tokensComando);

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (tipo.equals("Signo") && palabra.equals(".")) {
                addNum();

                revisarIdentificador(tokensComando);
            }

            if (palabra2.equals("ORDER")) {
                tipo = tokensComando.get(numActual).getTipo();
                palabra = tokensComando.get(numActual).getToken();
                if (!tipo.equals("Palabra Reservada")) {
                    throw new SyntacticErrorException("Secuencia de token invalida");
                }
                if (!palabra.equals("DESC") && !palabra.equals("ASC")) {
                    throw new SyntacticErrorException("Se esperaba un token \"DESC\" o \"ASC\"");
                }
                addNum();

            }
        } else if (palabra.equals("LIMIT")) {
            revisarEntero(tokensComando);
        }

    }

    private void revisarWhere(List<Token> tokensComando) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        addNum();

        if (tipo.equals("Signo") && palabra.equals("(")) {

            revisarWhere(tokensComando);

            revisarSigno(tokensComando, ")");
        } else if (tipo.equals("Identificador")) {

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (tipo.equals("Signo") && palabra.equals(".")) {
                addNum();

                revisarIdentificador(tokensComando);
            }

            tipo = tokensComando.get(numActual).getTipo();
            palabra = tokensComando.get(numActual).getToken();
            if (!tipo.equals("Signo") && !tipo.equals("Relacional")) {
                throw new SyntacticErrorException("Secuencia de token invalida");
            }
            if (!palabra.equals("=") && !palabra.equals("<") && !palabra.equals(">")) {
                throw new SyntacticErrorException("Se esperaba un token \"=\", \"<\" o \">\"");
            }
            addNum();

            revisarDato(tokensComando);

        } else {
            throw new SyntacticErrorException("Se esperaba un token <Identificador> o \"(\"");
        }

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (tipo.equals("Lógico")) {
            if (palabra.equals("AND") || palabra.equals("OR")) {
                addNum();

                revisarIdentificador(tokensComando);

                tipo = tokensComando.get(numActual).getTipo();
                palabra = tokensComando.get(numActual).getToken();
                if (tipo.equals("Signo") && palabra.equals(".")) {
                    addNum();

                    revisarIdentificador(tokensComando);
                }

                tipo = tokensComando.get(numActual).getTipo();
                palabra = tokensComando.get(numActual).getToken();
                if (!tipo.equals("Signo") && !tipo.equals("Relacional")) {
                    throw new SyntacticErrorException("Secuencia de token invalida");
                }
                if (!palabra.equals("=") && !palabra.equals("<") && !palabra.equals(">")) {
                    throw new SyntacticErrorException("Se esperaba un token \"=\", \"<\" o \">\"");
                }
                addNum();

                revisarDato(tokensComando);

            } else {
                throw new SyntacticErrorException("Se esperaba un token \"AND\" o \"OR\"");
            }
        }

    }

    private void revisarIdentificador(List<Token> tokensComando) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        if (!tipo.equals("Identificador")) {
            throw new SyntacticErrorException("Se esperaba un token <Identificador>");
        }
        addNum();

    }

    private void revisarSigno(List<Token> tokensComando, String signo) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Signo")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals(signo)) {
            throw new SyntacticErrorException("Se esperaba un token \"" + signo + "\"");
        }
        addNum();

    }

    private void revisarDobleSigno(List<Token> tokensComando, String signo1, String signo2)
            throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        palabra = tokensComando.get(numActual).getToken();
        if (!tipo.equals("Signo")) {
            throw new SyntacticErrorException("Secuencia de token invalida");
        }
        if (!palabra.equals(signo1) && !palabra.equals(signo2)) {
            throw new SyntacticErrorException("Se esperaba un token \"" + signo1 + "\" o \"" + signo2 + "\"");
        }
        addNum();

    }

    private void revisarEntero(List<Token> tokensComando) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        if (!tipo.equals("Entero")) {
            throw new SyntacticErrorException("Se esperaba un token <Entero>");
        }
        addNum();

    }

    private void revisarDato(List<Token> tokensComando) throws SyntacticErrorException {

        tipo = tokensComando.get(numActual).getTipo();
        if (!tipo.equals("Entero") && !tipo.equals("Decimal") && !tipo.equals("Fecha") && !tipo.equals("Cadena")
                && !tipo.equals("Booleano")) {
            throw new SyntacticErrorException(
                    "Se esperaba un token <Entero>, <Decimal>, <Fecha>, <Cadena> o <Booleano>");
        }
        addNum();

    }

}
