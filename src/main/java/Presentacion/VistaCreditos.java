package Presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Vista simple con los nombres de los integrantes de este proyecto
 * */
public class VistaCreditos {

    private final CtrlPresentacion iCtrlPresentacion;

    String strTexto = "\n  Creditos: \n" + "\n    - Sergio Aguado Cobos \n"
            +"\n    - Ashish Kshetri \n" + "\n    - Sergi Cassanmagnago Somoza \n"
            + "\n    - Sergi Bosquet Reyes \n";

    private final JFrame frameVista;
    private final JButton buttonOK = new JButton("Volver al menu principal");

    private final JMenuItem menuitemQuit = new JMenuItem("Salir del Juego");
    private final JMenuItem menuitemVolver = new JMenuItem("Volver al menu principal");

    /**
     * Constructora de VistaCreditos (inicializa panel y botones)
     * @param pCtrlPresentacion controlador de presentacion a asignarle a dicha vista
     * */
    public VistaCreditos(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        JTextArea infoCreditos = new JTextArea(4, 10);
        infoCreditos.setText(strTexto);
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.PAGE_AXIS));
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(infoCreditos,BorderLayout.CENTER);
        panelPrincipal.add(buttonOK,BorderLayout.SOUTH);
        buttonOK.setToolTipText("Vuelva al menu principal");


        frameVista = iCtrlPresentacion.configuracion_frame(300,280, "Creditos");
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);

        asignar_listenersComponentes();

        JMenu menuFile = new JMenu("File");
        menuFile.add(menuitemVolver);
        menuFile.add(menuitemQuit);
        JMenuBar menubarVista = new JMenuBar();
        menubarVista.add(menuFile);
        frameVista.setJMenuBar(menubarVista);
    }


    /**
     *Metodo hacerVisible
     * @param b si TRUE entonces el frame sera visible, caso contrario estara desactivado
     * */
    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }

    /**
     * Metodo para asignar los listeners a cada componente
     * */
    private void asignar_listenersComponentes() {
        buttonOK.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

        menuitemVolver.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());
    }



}
