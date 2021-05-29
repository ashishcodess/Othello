import Presentacion.CtrlPresentacion;

import javax.swing.*;

/**Funcion Main del proyecto Othello*/
public class Main {
    public static void main (String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        SwingUtilities.invokeLater (
                () -> {
                    CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                    ctrlPresentacion.inicializarPresentacion();
                });
    }
}
