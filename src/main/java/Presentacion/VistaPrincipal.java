package Presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class VistaPrincipal {

    // Controlador de presentacion
    private CtrlPresentacion iCtrlPresentacion;

    // Componentes de la interficie grafica
    private JFrame frameVista = new JFrame("Vista Principal"); //ejemplo


    public VistaPrincipal (CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        //inicializarComponentes();
    }

    public void hacerVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }

    public void activar() {
        frameVista.setEnabled(true);
    }

    public void desactivar() {
        frameVista.setEnabled(false);
    }
}
