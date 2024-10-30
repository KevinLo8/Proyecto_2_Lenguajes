package com.proyecto_2.Backend.ActionListeners;

import java.awt.event.*;

import com.proyecto_2.Frontend.FramePrincipal;

public class ActionListenerReporteNumeroOperaciones implements ActionListener {


    private FramePrincipal framePrincipal;

    public ActionListenerReporteNumeroOperaciones(FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        framePrincipal.GenerarReporteNumeroOperaciones();
    }

}
