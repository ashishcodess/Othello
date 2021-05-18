package Presentacion;

import javax.swing.*;
import java.awt.*;

public class VistaPartida {

    /*Sergio: quitar los botones de la parte de vistaTablero y ponerlo aqui, de esa manera
    podemos reutilizar la vistaTablero para mostrar los tableros cuando tengamos que cargar un tablero
    * */

    private final CtrlPresentacion iCtrlPresentacion;
    private final JPanel panelPrincipal = new JPanel();
    private final JFrame frameVista = new JFrame("Vista Tablero");

    //private VistaTablero vistaTablero = new VistaTablero();

    public VistaPartida(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout());
        inicializarComponentes();
    }

    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }

    private void inicializarComponentes() {

    }
}
