package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCreditos {

    private CtrlPresentacion iCtrlPresentacion;

    String strTexto = "\n  Creditos: \n" + "\n    - Sergio Aguado Cobos \n"
            +"\n    - Ashish Kshetri \n" + "\n    - Sergi Cassanmagnago Somoza \n"
            + "\n    - Sergi Bosquet Reyes \n";

    private JTextArea infoCreditos = new JTextArea(4,10);
    private JFrame frameVista = new JFrame("Creditos");
    private JButton buttonOK = new JButton("OK");
    private  JPanel panelPrincipal = new JPanel();

    private JMenuBar menubarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuitemQuit = new JMenuItem("Salir");
    private JMenuItem menuitemVolver = new JMenuItem("Volver al menu principal");

    /**
     * Constructora de VistaCreditos (inicializa panel y botones)
     * */
    public VistaCreditos(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        infoCreditos.setText(strTexto);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.PAGE_AXIS));
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(infoCreditos,BorderLayout.CENTER);
        panelPrincipal.add(buttonOK,BorderLayout.SOUTH);

        frameVista.setMinimumSize(new Dimension(300,250));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);

        asignar_listenersComponentes();

        menuFile.add(menuitemVolver);
        menuFile.add(menuitemQuit);
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
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.hacerVisibleVista(1);
                    }
                });

        menuitemVolver.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.hacerVisibleVista(1);
                    }
                });

        menuitemQuit.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.presentacion_exportar_ranking();
                        System.exit(0);
                    }
                });
    }



}
