package com.proyecto_2.Frontend.Panels;

import java.awt.Dimension;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.proyecto_2.Backend.ActionListeners.ActionListenerRegresar;
import com.proyecto_2.Backend.Token.Token;
import com.proyecto_2.Frontend.FramePrincipal;

public class PanelErroresSintacticos extends JPanel {

    private List<Token> errores;
    private FramePrincipal framePrincipal;

    private int panelHeigt;
    private int panelWidth;
    private int gap;

    public PanelErroresSintacticos(FramePrincipal framePrincipal, int panelHeigt, int panelWidth, List<Token> errores) {
        this.framePrincipal = framePrincipal;
        this.errores = errores;

        gap = framePrincipal.GAP / 2;
        this.panelHeigt = panelHeigt - gap * 5;
        this.panelWidth = panelWidth - gap * 2;

        initComponents();
    }

    private void initComponents() {

        JScrollPane scp1 = new JScrollPane();
        scp1.setPreferredSize(new Dimension(panelWidth, panelHeigt));

        String[] titulos = { "Token", "Tipo", "Fila", "Columna", "Descripción" };

        String[][] data = new String[0][5];
        for (int i = 0; i < errores.size(); i++) {
            Token tokenError = errores.get(i);
            data = agregarData(tokenError, data);
        }

        JTable tbl1 = new JTable(data, titulos);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbl1.setDefaultRenderer(String.class, centerRenderer);

        scp1.setViewportView(tbl1);

        JButton btn1 = new JButton("Regresar");

        btn1.addActionListener(new ActionListenerRegresar(framePrincipal));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(gap, Short.MAX_VALUE)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(scp1)
                                        .addComponent(btn1))
                        .addContainerGap(gap, Short.MAX_VALUE));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(gap, Short.MAX_VALUE)
                        .addComponent(scp1)
                        .addGap(gap)
                        .addComponent(btn1)
                        .addContainerGap(gap, Short.MAX_VALUE));

    }

    private String[][] agregarData(Token tokenError, String[][] data) {
        String[][] retorno = new String[data.length + 1][5];

        for (int i = 0; i < data.length; i++) {
            retorno[i][0] = data[i][0];
            retorno[i][1] = data[i][1];
            retorno[i][2] = data[i][2];
            retorno[i][3] = data[i][3];
            retorno[i][4] = data[i][4];
        }

        retorno[data.length][0] = tokenError.getToken();
        retorno[data.length][1] = tokenError.getTipo();
        retorno[data.length][2] = String.valueOf(tokenError.getFila());
        retorno[data.length][3] = String.valueOf(tokenError.getColumna());
        retorno[data.length][4] = tokenError.getDescripcionSintactico();

        return retorno;
    }

}
