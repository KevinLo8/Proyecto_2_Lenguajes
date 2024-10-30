package com.proyecto_2.Backend.ActionListeners;

import java.awt.event.*;

import com.proyecto_2.Frontend.FramePrincipal;

public class ActionListenerReporteSintactico implements ActionListener {


    private FramePrincipal framePrincipal;

    public ActionListenerReporteSintactico(FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        framePrincipal.GenerarReporteSintactico();
    }

}
