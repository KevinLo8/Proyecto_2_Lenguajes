package com.proyecto_2.Backend.DocumentListeners;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Element;

public class DocumentListenerNumeracion implements DocumentListener {
    private JTextArea textArea, lines;

    public DocumentListenerNumeracion(JTextArea textArea, JTextArea lines) {
        this.textArea = textArea;
        this.lines = lines;
    }

    private String getText() {
        int caretPosition = textArea.getDocument().getLength();
        Element root = textArea.getDocument().getDefaultRootElement();
        String text = "1" + System.getProperty("line.separator");
        for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
            text += i + System.getProperty("line.separator");
        }
        return text;
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        lines.setText(getText());
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        lines.setText(getText());
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        lines.setText(getText());
    }

}
