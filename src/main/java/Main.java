import Presentacion.CtrlPresentacion;

/**Funcion Main del proyecto Othello*/
public class Main {
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                () -> {
                    CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                    ctrlPresentacion.inicializarPresentacion();
                });
    }
}
