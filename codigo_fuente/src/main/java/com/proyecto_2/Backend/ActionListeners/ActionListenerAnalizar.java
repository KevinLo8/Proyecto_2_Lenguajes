package com.proyecto_2.Backend.ActionListeners;

import java.awt.event.*;

import com.proyecto_2.Frontend.Panels.PanelAreaTexto;

public class ActionListenerAnalizar implements ActionListener {

    private PanelAreaTexto panel;

    public ActionListenerAnalizar(PanelAreaTexto panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.analizarTexto();
    }

}
