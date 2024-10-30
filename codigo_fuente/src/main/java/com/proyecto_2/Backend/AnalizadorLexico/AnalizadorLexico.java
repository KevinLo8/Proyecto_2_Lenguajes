// DO NOT EDIT
// Generated by JFlex 1.9.1 http://jflex.de/
// source: D:/Kevin/Documentos/Development/Lenguajes 1/Proyecto_2_Lenguajes/codigo_fuente/src/main/java/com/proyecto_2/Backend/AnalizadorLexico/Lexico.flex

package com.proyecto_2.Backend.AnalizadorLexico;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.proyecto_2.Backend.Token.Token;


@SuppressWarnings("fallthrough")
public class AnalizadorLexico {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\37\u0100\1\u0200\267\u0100\10\u0300\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\10\0\3\1\2\2\1\1\22\0\1\3\6\0\1\4"+
    "\2\5\2\6\1\5\1\7\1\10\1\6\12\11\1\0"+
    "\1\5\1\12\1\13\1\12\2\0\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\15\1\23\2\15\1\24"+
    "\1\25\1\26\1\27\2\15\1\30\1\31\1\32\1\33"+
    "\1\34\1\15\1\35\2\15\4\0\1\36\1\0\32\37"+
    "\12\0\1\2\u01a2\0\2\2\326\0\u0100\2";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\1\1\4\2\5\1\6"+
    "\1\7\11\10\1\11\1\0\1\12\3\0\1\7\7\10"+
    "\1\13\2\10\2\0\1\14\1\15\1\16\3\10\1\0"+
    "\1\10\1\17\7\0\1\20";

  private static int [] zzUnpackAction() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\40\0\40\0\40\0\100\0\40\0\40\0\140"+
    "\0\200\0\240\0\300\0\340\0\u0100\0\u0120\0\u0140\0\u0160"+
    "\0\u0180\0\u01a0\0\u01c0\0\u01e0\0\u0200\0\40\0\u0220\0\u0240"+
    "\0\u0260\0\40\0\u0280\0\u02a0\0\u02c0\0\u02e0\0\u0300\0\u0320"+
    "\0\u0340\0\340\0\u0360\0\u0380\0\u03a0\0\u03c0\0\40\0\u0260"+
    "\0\340\0\u03e0\0\u0400\0\u0420\0\u0440\0\u0460\0\340\0\u0480"+
    "\0\u04a0\0\u04c0\0\u04e0\0\u0500\0\u0520\0\u0540\0\40";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\3\1\5\1\6\1\7\1\10"+
    "\1\6\1\11\1\12\1\6\1\13\1\14\1\15\2\14"+
    "\1\16\3\14\1\17\1\20\1\21\1\14\1\22\1\23"+
    "\3\14\1\2\1\24\40\0\4\25\1\26\4\25\1\27"+
    "\26\25\7\0\1\30\40\0\1\31\1\11\41\0\1\32"+
    "\40\0\12\14\1\33\5\14\1\34\1\14\16\0\22\14"+
    "\16\0\13\14\1\35\6\14\16\0\1\36\21\14\16\0"+
    "\1\37\6\14\1\40\12\14\16\0\13\14\1\41\6\14"+
    "\16\0\14\14\1\42\5\14\16\0\17\14\1\43\2\14"+
    "\16\0\14\14\1\44\5\14\13\0\1\24\24\0\1\45"+
    "\1\24\4\25\1\26\37\25\1\26\4\25\1\46\26\25"+
    "\1\47\1\0\36\47\11\0\1\50\42\0\3\14\1\42"+
    "\16\14\16\0\6\14\1\51\13\14\16\0\17\14\1\52"+
    "\2\14\16\0\10\14\1\53\11\14\16\0\21\14\1\51"+
    "\16\0\12\14\1\51\7\14\16\0\16\14\1\42\3\14"+
    "\16\0\11\14\1\51\10\14\16\0\17\14\1\54\2\14"+
    "\13\0\1\24\25\0\1\24\4\25\1\26\4\25\1\55"+
    "\26\25\14\0\12\14\1\56\7\14\16\0\15\14\1\54"+
    "\4\14\16\0\4\14\1\57\15\14\2\0\4\25\1\26"+
    "\4\25\1\60\26\25\14\0\16\14\1\51\3\14\2\0"+
    "\4\25\1\26\2\25\1\61\34\25\1\26\4\25\1\62"+
    "\32\25\1\26\4\25\1\63\32\25\1\26\2\25\1\64"+
    "\34\25\1\26\4\25\1\65\32\25\1\26\4\25\1\66"+
    "\32\25\1\67\33\25";

  private static int [] zzUnpacktrans() {
    int [] result = new int[1376];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\3\11\1\1\2\11\15\1\1\0\1\11\3\0"+
    "\1\11\12\1\2\0\1\11\5\1\1\0\2\1\7\0"+
    "\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen())];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */

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
            addError(palabra, fila, columna);
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

    private void addError(String palabra, int fila, int columna) {
        Token token = new Token();
       
        token.setToken(palabra);
        token.setTipo("Error");
        token.setFila(fila + 1);
        token.setColumna(columna + 1);
        token.setColor(Color.RED);
        if (palabra.length() == 1) {
            token.setDescripcion("Carácter no reconocido");
        } else {
            token.setDescripcion("Token no reconocido");
        }

        lista.add(token);
    }

    public List<Token> getLista(){
        return lista;
    }
    


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public AnalizadorLexico(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate && zzCanGrow()) {
      /* if not, and it can grow: blow it up */
      char newBuffer[] = new char[Math.min(zzBuffer.length * 2, zzMaxBufferLen())];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      if (requested == 0) {
        throw new java.io.EOFException("Scan buffer limit reached ["+zzBuffer.length+"]");
      }
      else {
        throw new java.io.IOException(
            "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
      }
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    int initBufferSize = Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen());
    if (zzBuffer.length > initBufferSize) {
      zzBuffer = new char[initBufferSize];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public int yylex() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return YYEOF;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { addError(yytext(), yyline, yycolumn);
            }
          // fall through
          case 17: break;
          case 2:
            { /*Ignore*/
            }
          // fall through
          case 18: break;
          case 3:
            { System.out.print(yytext());
            }
          // fall through
          case 19: break;
          case 4:
            { addList(yytext(), "Signo", yyline, yycolumn, Color.BLACK);
            }
          // fall through
          case 20: break;
          case 5:
            { addList(yytext(), "Aritmetico", yyline, yycolumn, Color.BLACK);
            }
          // fall through
          case 21: break;
          case 6:
            { addList(yytext(), "Entero", yyline, yycolumn, Color.BLUE);
            }
          // fall through
          case 22: break;
          case 7:
            { addList(yytext(), "Relacional", yyline, yycolumn, Color.BLACK);
            }
          // fall through
          case 23: break;
          case 8:
            { compararPalabra(yytext(), yyline, yycolumn);
            }
          // fall through
          case 24: break;
          case 9:
            { addList(yytext(), "Identificador", yyline, yycolumn, Color.PINK);
            }
          // fall through
          case 25: break;
          case 10:
            { addList(yytext(), "Cadena", yyline, yycolumn, Color.GREEN);
            }
          // fall through
          case 26: break;
          case 11:
            { addList(yytext(), "Lógico", yyline, yycolumn, Color.ORANGE);
            }
          // fall through
          case 27: break;
          case 12:
            { addList(yytext(), "Comentario", yyline, yycolumn, Color.GRAY);
            }
          // fall through
          case 28: break;
          case 13:
            { addList(yytext(), "Decimal", yyline, yycolumn, Color.BLUE);
            }
          // fall through
          case 29: break;
          case 14:
            { addList(yytext(), "Funcion de Agregación", yyline, yycolumn, Color.BLUE);
            }
          // fall through
          case 30: break;
          case 15:
            { addList(yytext(), "Booleano", yyline, yycolumn, Color.BLUE);
            }
          // fall through
          case 31: break;
          case 16:
            { addList(yytext(), "Fecha", yyline, yycolumn, Color.YELLOW);
            }
          // fall through
          case 32: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java AnalizadorLexico [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        AnalizadorLexico scanner = null;
        java.io.FileInputStream stream = null;
        java.io.Reader reader = null;
        try {
          stream = new java.io.FileInputStream(argv[i]);
          reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new AnalizadorLexico(reader);
          while ( !scanner.zzAtEOF ) scanner.yylex();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
        finally {
          if (reader != null) {
            try {
              reader.close();
            }
            catch (java.io.IOException e) {
              System.out.println("IO error closing file \""+argv[i]+"\"");
              System.out.println(e);
            }
          }
          if (stream != null) {
            try {
              stream.close();
            }
            catch (java.io.IOException e) {
              System.out.println("IO error closing file \""+argv[i]+"\"");
              System.out.println(e);
            }
          }
        }
      }
    }
  }


}
