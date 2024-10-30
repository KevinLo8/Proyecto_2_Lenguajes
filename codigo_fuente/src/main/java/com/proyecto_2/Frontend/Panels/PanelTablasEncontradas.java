package com.proyecto_2.Frontend.Panels;

import java.awt.Dimension;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.proyecto_2.Backend.ActionListeners.ActionListenerRegresar;
import com.proyecto_2.Backend.Token.Token;
import com.proyecto_2.Frontend.FramePrincipal;

public class PanelTablasEncontradas extends JPanel {

    private List<Token> tablas;
    private FramePrincipal framePrincipal;

    private int panelHeigt;
    private int panelWidth;
    private int gap;

    public PanelTablasEncontradas(FramePrincipal framePrincipal, int panelHeigt, int panelWidth, List<Token> tablas) {
        this.framePrincipal = framePrincipal;
        this.tablas = tablas;

        gap = framePrincipal.GAP / 2;
        this.panelHeigt = panelHeigt - gap * 5;
        this.panelWidth = panelWidth - gap * 2;

        initComponents();
    }

    private void initComponents() {

        JScrollPane scp1 = new JScrollPane();
        scp1.setPreferredSize(new Dimension(panelWidth, panelHeigt));

        String[] titulos = { "Nombre de Tabla", "Fila", "Columna", "Cantida de Datos" };

        String[][] data = new String[0][4];
        for (int i = 0; i < tablas.size(); i++) {
            Token tokentabla = tablas.get(i);
            data = agregarData(tokentabla, data);
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

    private String[][] agregarData(Token tokentabla, String[][] data) {
        String[][] retorno = new String[data.length + 1][4];

        for (int i = 0; i < data.length; i++) {
            retorno[i][0] = data[i][0];
            retorno[i][1] = data[i][1];
            retorno[i][2] = data[i][2];
            retorno[i][3] = data[i][3];
        }

        retorno[data.length][0] = tokentabla.getToken();
        retorno[data.length][2] = String.valueOf(tokentabla.getFila());
        retorno[data.length][3] = String.valueOf(tokentabla.getColumna());
        retorno[data.length][1] = tokentabla.getDescripcion();

        return retorno;
    }

}
