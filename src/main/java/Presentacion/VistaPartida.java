package Presentacion;

import javax.swing.*;
import java.awt.*;

//IDEA INTENTAR HACER UN EXTEND DE CLASE VistaTablero (si no hacerlo en vistas diferentes: para partidas y para Cargar Tableros)

public class VistaPartida extends VistaTablero {

    //private final CtrlPresentacion iCtrlPresentacion;
    private final JPanel panelPrincipal = new JPanel();
    private JFrame frameVista = new JFrame("Partida");

    public VistaPartida(CtrlPresentacion pCtrlPresentacion) {
        super(pCtrlPresentacion);
    }

    /*Sergio: quitar los botones de la parte de vistaTablero y ponerlo aqui, de esa manera
    podemos reutilizar la vistaTablero para mostrar los tableros cuando tengamos que cargar un tablero
    * */





}
