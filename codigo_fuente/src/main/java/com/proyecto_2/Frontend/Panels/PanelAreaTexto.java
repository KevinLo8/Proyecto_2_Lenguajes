package com.proyecto_2.Frontend.Panels;

import java.awt.*;

import javax.swing.*;

import com.proyecto_2.Backend.DocumentListeners.DocumentListenerNumeracion;
import com.proyecto_2.Frontend.FramePrincipal;

public class PanelAreaTexto extends JPanel {

    private FramePrincipal framePrincipal;
    private int panelHeigt;
    private int panelWidth;
    private int gap;
    private JTextArea textArea;
    private JTextArea lines;
    private JScrollPane jsp;

    public PanelAreaTexto(int panelHeigt, int panelWidth, FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
        gap = framePrincipal.GAP / 2;
        this.panelHeigt = panelHeigt - gap * 4;
        this.panelWidth = panelWidth - gap * 2;

        initComponent();
    }

    private void initComponent() {

        jsp = new JScrollPane();
        textArea = new JTextArea();
        lines = new JTextArea("1");

        jsp.setPreferredSize(new Dimension(panelWidth, panelHeigt));

        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);

        DocumentListenerNumeracion docLisNum = new DocumentListenerNumeracion(textArea, lines);
        textArea.getDocument().addDocumentListener(docLisNum);

        JLabel lbl = new JLabel("Fila 1, Columna 1");
        JButton btn = new JButton("Analizar");

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
}
