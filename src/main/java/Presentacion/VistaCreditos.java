package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaCreditos {

    private final CtrlPresentacion iCtrlPresentacion;

    String strTexto = "\n  Creditos: \n" + "\n    - Sergio Aguado Cobos \n"
            +"\n    - Ashish Kshetri \n" + "\n    - Sergi Cassanmagnago Somoza \n"
            + "\n    - Sergi Bosquet Reyes \n";

    private final JFrame frameVista = new JFrame("Creditos");
    private final JButton buttonOK = new JButton("Volver al menu principal");

    private final JMenuItem menuitemQuit = new JMenuItem("Salir");
    private final JMenuItem menuitemVolver = new JMenuItem("Volver al menu principal");

    /**
     * Constructora de VistaCreditos (inicializa panel y botones)
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

        frameVista.setMinimumSize(new Dimension(300,280));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameVista.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                salir_del_juego();
            }
        });
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
     * Metodo WindowPerfomed para cerrar la ventana
     * */
    private void salir_del_juego() {
        iCtrlPresentacion.presentacion_exportar_ranking();
        System.exit(0);
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
                (event -> {
                    iCtrlPresentacion.presentacion_exportar_ranking();
                    System.exit(0);
                });
    }



}
