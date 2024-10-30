package com.proyecto_2.Frontend.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.*;

import com.proyecto_2.Backend.ActionListeners.ActionListenerAnalizar;
import com.proyecto_2.Backend.AnalizadorLexico.AnalizadorLexico;
import com.proyecto_2.Backend.AnalizadorSintactico.AnalizadorSintactico;
import com.proyecto_2.Backend.DocumentListeners.DocumentListenerNumeracion;
import com.proyecto_2.Backend.Token.Token;
import com.proyecto_2.Frontend.FramePrincipal;

public class PanelAreaTexto extends JPanel {

    private List<Token> tokens;
    private List<Token> tablas;
    private List<Token> tablasModificadas;
    private List<Integer> cantidades;

    private int panelHeigt;
    private int panelWidth;
    private int gap;
    private JTextPane textArea;
    private JTextArea lines;
    private JScrollPane jsp;

    public PanelAreaTexto(FramePrincipal framePrincipal, int panelHeigt, int panelWidth) {
        tokens = new ArrayList<>();
        tablas = new ArrayList<>();
        tablasModificadas = new ArrayList<>();
        gap = framePrincipal.GAP / 2;
        this.panelHeigt = panelHeigt - gap * 5;
        this.panelWidth = panelWidth - gap * 2;

        initComponent();
    }

    private void initComponent() {
        jsp = new JScrollPane();
        textArea = new JTextPane();
        lines = new JTextArea("1");

        jsp.setPreferredSize(new Dimension(panelWidth, panelHeigt));

        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);

        DocumentListenerNumeracion docLisNum = new DocumentListenerNumeracion(textArea, lines);
        textArea.getDocument().addDocumentListener(docLisNum);

        JLabel lbl = new JLabel("Fila 1, Columna 1");
        JButton btn = new JButton("Analizar");

        btn.addActionListener(new ActionListenerAnalizar(this));

        jsp.getViewport().add(textArea);
        jsp.setRowHeaderView(lines);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(gap, Short.MAX_VALUE)
                        .addComponent(jsp)
                        .addGap(gap)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(btn)
                                .addComponent(lbl))
                        .addContainerGap(gap, Short.MAX_VALUE));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(gap, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jsp)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn)
                                        .addGap(panelWidth - 200)
                                        .addComponent(lbl)))
                        .addContainerGap(gap, Short.MAX_VALUE));
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<Token> getTablas() {
        return tablas;
    }

    public List<Token> getTablasModificadas() {
        return tablasModificadas;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public void analizarTexto() {
        StringReader reader = new StringReader(textArea.getText());
        AnalizadorLexico aLexico = new AnalizadorLexico(reader);
        try {
            aLexico.yylex();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tokens = aLexico.getLista();

        AnalizadorSintactico aSintactico = new AnalizadorSintactico(tokens);
        aSintactico.analizar();

        tablas = aSintactico.getTablas();
        tablasModificadas = aSintactico.getTablasModificadas();
        cantidades = aSintactico.getCantidades();

        agregarTextoColor();

    }

    private void agregarTextoColor() {
        textArea.setText("");

        DefaultStyledDocument document = new DefaultStyledDocument();
        textArea.setStyledDocument(document);
        StyleContext context = new StyleContext();

        List<Integer> saltos = new ArrayList<>();
        int linea = 1;
        int columna = 1;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            try {
                while (token.getColumna() > columna || token.getFila() > linea) {
                    if (token.getColumna() > columna) {
                        int start = textArea.getText().length();
                        document.insertString(start, " ", null);
                        columna++;
                    }
                    if (token.getFila() > linea) {
                        int start = textArea.getText().length();
                        document.insertString(start, "♠", null);
                        linea++;
                        columna = 1;
                        saltos.add(start);
                    }
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            int start = textArea.getText().length();

            Style style = context.addStyle("Color", null);

            StyleConstants.setForeground(style, token.getColor());

            try {
                document.insertString(start, token.getToken(), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            columna = columna + token.getToken().length();

        }

        for (int i = saltos.size() - 1; i > -1; i--) {
            try {
                document.replace(saltos.get(i), 1, "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }

        DocumentListenerNumeracion docLisNum = new DocumentListenerNumeracion(textArea, lines);
        textArea.getDocument().addDocumentListener(docLisNum);
    }

}
