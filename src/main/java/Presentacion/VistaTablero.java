package Presentacion;

import javax.swing.*;
import java.awt.*;


public class VistaTablero extends JFrame {
    private static CtrlPresentacion iCtrlPresentacion;
    private JPanel panelTablero;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    JFrame frameVista = new JFrame("VistaTablero");

    public VistaTablero(CtrlPresentacion CtrlPresentacion) {
        iCtrlPresentacion = CtrlPresentacion;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("VistaTablero");
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setContentPane(new VistaTablero(iCtrlPresentacion).panelTablero);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodo hacerVisible
     *
     * @param b si TRUE entonces el frame sera visible, caso contrario estara desactivado
     */
    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }

}


