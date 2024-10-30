package com.proyecto_2.Frontend.Panels;

import java.awt.Dimension;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.proyecto_2.Backend.ActionListeners.ActionListenerRegresar;
import com.proyecto_2.Frontend.FramePrincipal;

public class PanelNumeroOperaciones extends JPanel {

    private List<Integer> cantidades;
    private FramePrincipal framePrincipal;

    private int panelHeigt;
    private int panelWidth;
    private int gap;

    public PanelNumeroOperaciones(FramePrincipal framePrincipal, int panelHeigt, int panelWidth, List<Integer> cantidades) {
        this.framePrincipal = framePrincipal;
        this.cantidades = cantidades;

        gap = framePrincipal.GAP / 2;
        this.panelHeigt = panelHeigt - gap * 5;
        this.panelWidth = panelWidth - gap * 2;

        initComponents();
    }

    private void initComponents() {

        JScrollPane scp1 = new JScrollPane();
        scp1.setPreferredSize(new Dimension(panelWidth, panelHeigt));

        String[] titulos = { "Tipo de Operación", "Cantidad" };

        String[][] data = agregarData();

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

    private String[][] agregarData() {
        String[][] retorno = new String[6][2];

        retorno[0][0] = "Create";
        retorno[1][0] = "Alter";
        retorno[2][0] = "Insert";
        retorno[3][0] = "Select";
        retorno[4][0] = "Update";
        retorno[5][0] = "Delete";

        for (int i = 0; i < 6; i++) {
            try {
                retorno[i][1] = String.valueOf(cantidades.get(i));
            } catch (Exception e) {
                retorno[i][1] = String.valueOf(0);
            }
        }

        return retorno;
    }

}
