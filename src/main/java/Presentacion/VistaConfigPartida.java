package Presentacion;

import Dominio.Partida.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

public class VistaConfigPartida {

    // Controlador de presentacion
    private final CtrlPresentacion iCtrlPresentacion;

    private final JFrame frameVista = new JFrame("Configurar Partida");
    private final JPanel panelPrincipal = new JPanel();


    //config partida
    private final JPanel panelModoDeJuego = new JPanel();
    private final JPanel panelReglas = new JPanel();
    private final JPanel panelBotones = new JPanel();
    private final JPanel panelCentral = new JPanel();

    private final JLabel labelModoDeJuego= new JLabel("Modo de juego:");
    private final JCheckBox verticalCheckBox = new JCheckBox("Vertical");
    private final JCheckBox horizontalCheckBox = new JCheckBox("Horizontal");
    private final JCheckBox diagonalCheckBox = new JCheckBox("Diagonal");

    private final JLabel labelReglas= new JLabel("Reglas del juego:");
    private final JRadioButton personaVsPersonaRadioButton = new JRadioButton("Jugador vs Jugador");
    private final JRadioButton personaVsIARadioButton = new JRadioButton("Jugador vs IA");
    private final JRadioButton IAVsIARadioButton = new JRadioButton("IA vs IA");

    private final JButton comenzarPartidaButton = new JButton("Comenzar Partida!");
    private final JButton menuButton = new JButton("Volver al menú");

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
        asignarListeners();
    }

    /**
     * Metodo para inicializar frame
     * */
    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(450,250));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameVista.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                iCtrlPresentacion.salir_del_juego();
            }
        });
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

        panelModoDeJuego.setLayout(new BoxLayout(panelModoDeJuego,BoxLayout.PAGE_AXIS));
        panelReglas.setLayout(new BoxLayout(panelReglas,BoxLayout.PAGE_AXIS));
        panelBotones.setLayout(new FlowLayout());
        panelCentral.setLayout(new FlowLayout());

        //PANEL MODO DE JUEGO
        panelModoDeJuego.add(labelModoDeJuego);
        panelModoDeJuego.add(IAVsIARadioButton);
        panelModoDeJuego.add(personaVsIARadioButton);
        panelModoDeJuego.add(personaVsPersonaRadioButton);


        verticalCheckBox.setEnabled(true);
        horizontalCheckBox.setEnabled(true);
        diagonalCheckBox.setEnabled(true);
        //PANEL REGLAS
        panelReglas.add(labelReglas);
        panelReglas.add(verticalCheckBox);
        panelReglas.add(horizontalCheckBox);
        panelReglas.add(diagonalCheckBox);

        //PANEL BOTONES
        panelBotones.add(menuButton);
        panelBotones.add(comenzarPartidaButton);


        //PANEL CENTRAL
        panelCentral.add(panelModoDeJuego,BorderLayout.WEST);
        panelCentral.add(panelReglas,BorderLayout.EAST);

        //PANEL PRINCIPAL
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.SOUTH);
    }

    /*public void actionPerformed_comenzarPartidaButton(ActionEvent event){
        Set<Position> posDisp = iCtrlPresentacion.presentacionObternerCasillasDisponibles();
    }*/
    private void asignarListeners() {
        comenzarPartidaButton.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.TABLERO));

        /*comenzarPartidaButton.addActionListener
                 (this::actionPerformed_comenzarPartidaButton);*/

        menuButton.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

    }
}
