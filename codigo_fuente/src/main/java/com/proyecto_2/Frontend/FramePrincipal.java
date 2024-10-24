package com.proyecto_2.Frontend;

import java.awt.*;

import javax.swing.*;

import com.proyecto_2.Backend.ActionListeners.ActionListenerSalir;
import com.proyecto_2.Frontend.Panels.PanelAreaTexto;

public class FramePrincipal extends JFrame {

    private JPanel panel;

    private final Dimension DIM = Toolkit.getDefaultToolkit().getScreenSize();
    private final int SIZE_HEIGHT = 900;
    private final int SIZE_WIDTH = (SIZE_HEIGHT * 16) / 9;
    public final int GAP = 50;
    private final int PANEL_HEIGHT = SIZE_HEIGHT - GAP * 4;
    private final int PANEL_WIDTH = SIZE_WIDTH - GAP * 4;

    public FramePrincipal() {

        initComponentes();

    }

    private void initComponentes() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds((int) ((DIM.getWidth() - SIZE_WIDTH) / 2), (int) ((DIM.getHeight() - SIZE_HEIGHT) / 2),
                SIZE_WIDTH, SIZE_HEIGHT);
        setTitle("Analizador Léxico");

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jM1 = new JMenu("Archivo");
        JMenu jM2 = new JMenu("Acciones");

        jMenuBar.add(jM1);
        jMenuBar.add(jM2);

        JMenuItem jMI1 = new JMenuItem("Cargar Archivo");
        JMenuItem jMI2 = new JMenuItem("Salir");
        JMenuItem jMI3 = new JMenuItem("Reporte De Tokens");
        JMenuItem jMI4 = new JMenuItem("Reporte De Errores");
        JMenuItem jMI5 = new JMenuItem("Reporte De Optimización");

        jMI2.addActionListener(new ActionListenerSalir());

        jM1.add(jMI1);
        jM1.add(jMI2);
        jM2.add(jMI3);
        jM2.add(jMI4);
        jM2.add(jMI5);

        setJMenuBar(jMenuBar);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT - jMenuBar.getHeight()));

        PanelAreaTexto pAT = new PanelAreaTexto(PANEL_HEIGHT, PANEL_WIDTH, this);
        panel.add(pAT);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(GAP, Short.MAX_VALUE)
                        .addComponent(panel)
                        .addContainerGap(GAP, Short.MAX_VALUE));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(GAP, Short.MAX_VALUE)
                        .addComponent(panel)
                        .addContainerGap(GAP, Short.MAX_VALUE));

        pack();
    }

}
