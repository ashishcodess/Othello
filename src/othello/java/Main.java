import Presentacion.CtrlPresentacion;

import javax.swing.*;

/**Funcion Main del proyecto Othello*/
public class Main {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (
                () -> {
                    CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                    ctrlPresentacion.inicializarPresentacion();
                });
    }
}
