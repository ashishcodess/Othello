package Presentacion;

import javax.swing.*;
import java.awt.*;

public class VistaConfigPartida {

    // Controlador de presentacion
    private final CtrlPresentacion iCtrlPresentacion;

    private final JFrame frameVista = new JFrame("Configurar Partida");
    private final JPanel panelPrincipal = new JPanel();


    //config partida
    private final JPanel panelModoDeJuego = new JPanel();
    private final JPanel panelReglas = new JPanel();
    private final JLabel labelModoDeJuego= new JLabel("Modo de juego:");
    private final JLabel labelReglas= new JLabel("Reglas del juego:");
    private final JCheckBox verticalCheckBox = new JCheckBox();
    private final JCheckBox horizontalCheckBox = new JCheckBox();
    private final JCheckBox diagonalCheckBox = new JCheckBox();
    private final JRadioButton personaVsPersonaRadioButton = new JRadioButton();
    private final JRadioButton personaVsIARadioButton = new JRadioButton();
    private final JRadioButton IAVsIARadioButton = new JRadioButton();
    private final JButton comenzarPartidaButton = new JButton();

    //BARRA DE MENU
    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir");


    /**
     * Constructora de VistaConfigPartida
     * */
    public VistaConfigPartida(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializarComponentes();
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


    /////////// INICIALIZACION DE COMPONENTES

    /**
     * Metodo para inicializar componentes (menuBar, paneles y frame)
     * */
    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_menubarVista();
        inicializar_paneles();
        asignar_listenersComponentes();
    }

    /**
     * Metodo para inicializar frame
     * */
    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(650,450));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    /**
     * Metodo para inicializar menuBar (barra de menu superior)
     * */
    private void inicializar_menubarVista() {
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        frameVista.setJMenuBar(menubarVista);
    }

    /**
     * Metodo para inicializar todos los paneles
     * */
    private void inicializar_paneles() {
        panelPrincipal.setLayout(new BorderLayout());

        panelModoDeJuego.setLayout(new FlowLayout());
        panelReglas.setLayout(new FlowLayout());
        panelPrincipal.add(panelModoDeJuego);
        panelPrincipal.add(panelReglas);

        IAVsIARadioButton.setText("IA vs IA");
        IAVsIARadioButton.setEnabled(true);
        personaVsIARadioButton.setText("Persona vs IA");
        personaVsIARadioButton.setEnabled(true);
        personaVsPersonaRadioButton.setText("Persona vs Persona");
        personaVsPersonaRadioButton.setEnabled(true);
        panelModoDeJuego.add(labelModoDeJuego);
        panelModoDeJuego.add(IAVsIARadioButton);
        panelModoDeJuego.add(personaVsIARadioButton);
        panelModoDeJuego.add(personaVsPersonaRadioButton);

        panelReglas.add(labelReglas);
        panelReglas.add(verticalCheckBox);
        panelReglas.add(horizontalCheckBox);
        panelReglas.add(diagonalCheckBox);

        comenzarPartidaButton.setText("Comenzar Partida!");
        panelReglas.add(comenzarPartidaButton);


    }
    private void asignar_listenersComponentes() {
        comenzarPartidaButton.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.TABLERO));

    }
}
