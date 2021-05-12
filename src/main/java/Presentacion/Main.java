package Presentacion;

public class Main {
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                        ctrlPresentacion.inicializarPresentacion();
                        ctrlPresentacion.hacerVisibleVista(vistaActiva.LOGIN);
                    }});
    }
}
