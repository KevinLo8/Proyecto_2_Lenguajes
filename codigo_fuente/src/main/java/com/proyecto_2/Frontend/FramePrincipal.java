package com.proyecto_2.Frontend;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import com.proyecto_2.Backend.ActionListeners.*;
import com.proyecto_2.Backend.ReportesData.ReportesData;
import com.proyecto_2.Backend.Token.Token;
import com.proyecto_2.Frontend.Panels.*;

public class FramePrincipal extends JFrame {

    private JPanel panel;
    private PanelAreaTexto pAT;

    private final Dimension DIM = Toolkit.getDefaultToolkit().getScreenSize();
    private final int SIZE_HEIGHT = 900;
    private final int SIZE_WIDTH = (SIZE_HEIGHT * 16) / 9;
    public final int GAP = 25;
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
        setResizable(false);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jM1 = new JMenu("Archivo");
        JMenu jM2 = new JMenu("Acciones");

        jMenuBar.add(jM1);
        jMenuBar.add(jM2);

        JMenuItem jMI1 = new JMenuItem("Cargar Archivo");
        JMenuItem jMI2 = new JMenuItem("Salir");
        JMenuItem jMI3 = new JMenuItem("Reporte De Errores Léxicos");
        JMenuItem jMI4 = new JMenuItem("Reporte De Errores Sintacticos");

        jMI2.addActionListener(new ActionListenerSalir());
        jMI3.addActionListener(new ActionListenerReporteLexico(this));
        jMI4.addActionListener(new ActionListenerReporteSintactico(this));

        jM1.add(jMI1);
        jM1.add(jMI2);
        jM2.add(jMI3);
        jM2.add(jMI4);

        setJMenuBar(jMenuBar);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT - jMenuBar.getHeight()));

        pAT = new PanelAreaTexto(this, PANEL_HEIGHT, PANEL_WIDTH);
        panel.add(pAT);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(GAP / 2, Short.MAX_VALUE)
                        .addComponent(panel)
                        .addContainerGap(GAP, Short.MAX_VALUE));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(GAP, Short.MAX_VALUE)
                        .addComponent(panel)
                        .addContainerGap(GAP, Short.MAX_VALUE));

        pack();
    }

    public void GenerarReporteLexico() {
        panel.removeAll();

        List<Token> tokens = pAT.getTokens();
        List<Token> errores;

        ReportesData rD = new ReportesData();
        errores = rD.generarListaErroresLexicos(tokens);

        PanelErroresLexicos pEL = new PanelErroresLexicos(this, PANEL_HEIGHT, PANEL_WIDTH, errores);
        panel.add(pEL);

        repaint();
        validate();
    }

    public void GenerarReporteSintactico() {
        panel.removeAll();

        List<Token> tokens = pAT.getTokens();
        List<Token> errores;

        ReportesData rD = new ReportesData();
        errores = rD.generarListaErroresSintacticos(tokens);

        PanelErroresSintacticos pES = new PanelErroresSintacticos(this, PANEL_HEIGHT, PANEL_WIDTH, errores);
        panel.add(pES);

        repaint();
        validate();
    }

    public void Regresar() {
        panel.removeAll();
        panel.add(pAT);

        repaint();
        validate();
    }

}
