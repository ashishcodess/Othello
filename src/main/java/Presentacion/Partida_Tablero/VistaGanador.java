package Presentacion.Partida_Tablero;

import Presentacion.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;

public class VistaGanador {

    private final CtrlPresentacion iCtrlPresentacion;


    private final JFrame frameVista;
    private final JButton buttonOK = new JButton("Volver al menu principal");

    private final JMenuItem menuitemQuit = new JMenuItem("Salir del Juego");
    private final JMenuItem menuitemVolver = new JMenuItem("Volver al menu principal");

    //Info de fichas
    JPanel panelInfo = new JPanel();
    private final JTextField textoInfoUsuario = new JTextField(10);
    private final JTextField textoNegras = new JTextField(2);
    private final JTextField textoBlancas = new JTextField(2);

    private final JLabel labelINFOGANADOR = new JLabel("");


    private void obtener_info_labels() {
        textoInfoUsuario.setText(iCtrlPresentacion.consultar_info_usuario_activo());
        textoInfoUsuario.setEditable(false);

        int fichas_negras = iCtrlPresentacion.presentacion_get_negras();
        int fichas_blancas = iCtrlPresentacion.presentacion_get_blancas();
        textoNegras.setText(String.valueOf(fichas_blancas));
        textoNegras.setEditable(false);
        textoBlancas.setText(String.valueOf(fichas_negras));
        textoBlancas.setEditable(false);

        //falta calculo de labelINFOGANADOR
        labelINFOGANADOR.setText("");
        if (fichas_negras > fichas_blancas) {
            labelINFOGANADOR.setText("El ganador de la partida es J2!");
        }
        else if (fichas_negras < fichas_blancas) {
            labelINFOGANADOR.setText("El ganador de la partida es J1!");
        }
        else labelINFOGANADOR.setText("La partida termina en empate!");
    }

    /**
     * Constructora de VistaCreditos (inicializa panel y botones)
     * @param pCtrlPresentacion controlador de presentacion a asignarle a dicha vista
     * */
    public VistaGanador(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        panelInfo.setLayout(new BoxLayout(panelInfo,BoxLayout.PAGE_AXIS));

        obtener_info_labels();

        panelInfo.add(textoInfoUsuario);
        JPanel aux = new JPanel();
        aux.setLayout(new FlowLayout());
        JLabel labelFichasNegras = new JLabel("(J1) Fichas Negras: ");
        aux.add(labelFichasNegras);
        aux.add(textoNegras);

        JPanel aux2 = new JPanel();
        aux2.setLayout(new FlowLayout());
        JLabel labelFichasBlancas = new JLabel("(J2) Blancas: ");
        aux2.add(labelFichasBlancas);
        aux2.add(textoBlancas);

        panelInfo.add(aux);
        panelInfo.add(aux2);
        panelInfo.add(new JLabel(" "));

        panelInfo.add(labelINFOGANADOR);
        panelInfo.add(new JLabel(" "));

        panelPrincipal.add(panelInfo,BorderLayout.CENTER);
        panelPrincipal.add(buttonOK,BorderLayout.SOUTH);
        buttonOK.setToolTipText("Vuelve al menu principal");

        frameVista = iCtrlPresentacion.configuracion_frame(370,280, "Ganador");
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
        if (b) obtener_info_labels();
    }

    /**
     * Metodo para asignar los listeners a cada componente
     * */
    private void asignar_listenersComponentes() {
        buttonOK.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.MENU));

        menuitemVolver.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.MENU));

        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());
    }



}
