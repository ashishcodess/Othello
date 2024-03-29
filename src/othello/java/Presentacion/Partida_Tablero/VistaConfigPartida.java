package Presentacion.Partida_Tablero;

import Presentacion.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class VistaConfigPartida {

    // Controlador de presentacion
    private final CtrlPresentacion iCtrlPresentacion;

    private JFrame frameVista = new JFrame("Configurar Partida");
    private final JPanel panelPrincipal = new JPanel();

    private boolean primera_vez = true;//en caso de cierto, al iniciar la partida saltaria al menu de seleccion de tablero

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

    private JComboBox<String> selectorIA_0 = new JComboBox<>();
    private JComboBox<String> selectorIA_1 = new JComboBox<>();
    private final JButton buttonLoginUser2 = new JButton("Login Jugador 2");
    private JComboBox<String> selectorIA_2 = new JComboBox<>();

    private final JCheckBox tableroCheckBox = new JCheckBox("Tablero Personalizado?");
    private final JLabel labelInfoTablero = new JLabel("tablero: 0");

    private final JButton comenzarPartidaButton = new JButton("Comenzar Partida!");
    private final JButton menuButton = new JButton("Volver al menú");

    //BARRA DE MENU
    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemMenuPrincipal = new JMenuItem("Volver al menu principal");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir del juego");


    /**
     * Constructora de VistaConfigPartida
     * @param pCtrlPresentacion controlador de presentacion a asignarle a dicha vista
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
        cargar_label_info_tablero();
    }
    /**
     * Metodo para cargar la informacion de un tablero
     * */
    private void cargar_label_info_tablero() {
        int id = iCtrlPresentacion.consultar_idTablero_cargar();
        if (id == 0) labelInfoTablero.setText("tablero: inicial");
        else labelInfoTablero.setText("tablero: "+ id);
    }

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
        frameVista = iCtrlPresentacion.configuracion_frame(550,450, "Configuracion de Partida");
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    /**
     * Metodo para inicializar menuBar (barra de menu superior)
     * */
    private void inicializar_menubarVista() {
        menuFile.add(menuitemMenuPrincipal);
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        frameVista.setJMenuBar(menubarVista);
    }
    /**
     * Metodo para inicializar el comboBox
     * @return devuelve el combobox del selector de dificultad de la IA
     * */
    private JComboBox<String> inicializar_comboBox() {
        JComboBox<String> combo= new JComboBox<>();
        for (String s : Arrays.asList("facil_1", "facil_2", "normal_1", "normal_2", "dificil")) {
            combo.addItem(s);
        }
        return combo;
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
        selectorIA_0 = inicializar_comboBox();
        selectorIA_1 = inicializar_comboBox();
        selectorIA_2 = inicializar_comboBox();

        //FALTARIA DESDE EL LISTENER QUE AL PULSAR UNA EL RESTO SE DESMARQUEN
        IAVsIARadioButton.setSelected(false);
        selectorIA_0.setVisible(false);
        selectorIA_1.setVisible(false);
        personaVsIARadioButton.setSelected(false);
        selectorIA_2.setVisible(false);
        personaVsPersonaRadioButton.setSelected(false);
        buttonLoginUser2.setVisible(false);


        panelModoDeJuego.add(labelModoDeJuego);
        panelModoDeJuego.add(IAVsIARadioButton);
        panelModoDeJuego.add(selectorIA_0);
        panelModoDeJuego.add(selectorIA_1);
        panelModoDeJuego.add(personaVsIARadioButton);
        panelModoDeJuego.add(selectorIA_2);
        panelModoDeJuego.add(personaVsPersonaRadioButton);
        panelModoDeJuego.add(buttonLoginUser2);

        verticalCheckBox.setEnabled(true);
        horizontalCheckBox.setEnabled(true);
        diagonalCheckBox.setEnabled(true);
        verticalCheckBox.setSelected(true);
        horizontalCheckBox.setSelected(true);
        diagonalCheckBox.setSelected(true);
        //PANEL REGLAS
        panelReglas.add(labelReglas);
        panelReglas.add(verticalCheckBox);
        panelReglas.add(horizontalCheckBox);
        panelReglas.add(diagonalCheckBox);

        //PANEL BOTONES
        cargar_label_info_tablero();
        panelBotones.add(labelInfoTablero);
        panelBotones.add(tableroCheckBox);
        panelBotones.add(menuButton);
        panelBotones.add(comenzarPartidaButton);

        //PANEL CENTRAL
        panelCentral.add(panelModoDeJuego,BorderLayout.WEST);
        panelCentral.add(panelReglas,BorderLayout.EAST);

        //PANEL PRINCIPAL
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.SOUTH);

        gestion_ComboBox_uno_ON(1); //poner modo por defecto
    }

    /**
     * Metodo que obtiene la informacion de los comboBox al configurar una Partida
     * @param e comboBox a consultar la informacion
     * @return devuelve el identificador de la maquina*/
    private int consultar_info_comboBox(JComboBox<String> e) {
        String s = Objects.requireNonNull(e.getSelectedItem()).toString();
        int id = -1;
        switch (s) {
            case "facil_1":
                id = 0;
                break;
            case "facil_2":
                id = 1;
                break;
            case "normal_1":
                id = 2;
                break;
            case "normal_2":
                id = 3;
                break;
            case "dificil":
                id = 4;
                break;
        }
        return id;
    }

    /**
     * Metodo para recoger la informacion seleccionada para crear una Partida
     * @return devuelve un arraylist de enteros con la informacion respecto al configurador de partidas*/
    private ArrayList<Integer> recoger_info_modo_juego() {
        ArrayList<Integer> cosas = new ArrayList<>();
        cosas.add(((verticalCheckBox.isSelected()) ? 1 : 0));
        cosas.add(((horizontalCheckBox.isSelected()) ? 1 : 0));
        cosas.add(((diagonalCheckBox.isSelected()) ? 1 : 0));
        //0->modo , 1 -> ID-maquina1, 2 -> ID-maquina2
        int i_aux;
        if (IAVsIARadioButton.isSelected()) {
            cosas.add(0);
            i_aux = consultar_info_comboBox(selectorIA_0);
            cosas.add(i_aux);
            cosas.add(consultar_info_comboBox(selectorIA_1));
        }
        else if (personaVsIARadioButton.isSelected()) {
            cosas.add(1);
            i_aux = consultar_info_comboBox(selectorIA_2);
            cosas.add(i_aux);
        }
        else if (personaVsPersonaRadioButton.isSelected()) {
            cosas.add(2);
        }
        return cosas;
    }

    /**
     * Metodo que gestiona el inicio de una Partida*/
    private void gestionar_inicio_de_juego() {
        if (tableroCheckBox.isSelected()) {
            //SALTAR AL MENU DE CARGA DE TABLERO Y LUEGO CREAR PARTIDA CON LA CONFIGURACION NECESARIA
            if (!primera_vez) {
                primera_vez = true;
                recoger_info_partida(); //llama a la funcion que crea la partida
            }
            else {
                primera_vez = false;
                iCtrlPresentacion.modificar_idTablero_cargar(-1);
                iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.CARGARTABLERO);
            }
        }
        else { //CREAR PARTIDA CON LA CONFIGURACION
            if (personaVsPersonaRadioButton.isSelected()) {
                int idj2 = iCtrlPresentacion.consultar_id_j2();
                if (idj2 < 6) {
                    JOptionPane.showMessageDialog(null, "Primero inicia sesión con el J2");
                }
                else recoger_info_partida(); //llama a la funcion que crea la partida
            }
            else recoger_info_partida(); //llama a la funcion que crea la partida
        }
    }

    /**
     * Metodo para recoger la informacion de una Partida*/
    private void recoger_info_partida() {
        if (IAVsIARadioButton.isSelected()) {
            int i1 = consultar_info_comboBox(selectorIA_0);
            int i2 = consultar_info_comboBox(selectorIA_1);
            if ((i1 != i2)) {
                ArrayList<Integer> as_int = recoger_info_modo_juego();
                primera_vez = true;
                iCtrlPresentacion.presentacion_crearPartida(as_int);
                iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.TABLERO);
            }
        }
        else {
            ArrayList<Integer> as_int = recoger_info_modo_juego();
            primera_vez = true;
            iCtrlPresentacion.presentacion_crearPartida(as_int);
            iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.TABLERO);
        }
    }
    /**
     * Metodo que gestiona los comboBox al configurar una Partida
     * @param i modo de juego seleccionado en el comboBox*/
    private void gestion_ComboBox_uno_ON(int i) {
        IAVsIARadioButton.setSelected(false);
        personaVsIARadioButton.setSelected(false);
        personaVsPersonaRadioButton.setSelected(false);
        buttonLoginUser2.setVisible(false);
        selectorIA_0.setVisible(false);
        selectorIA_1.setVisible(false);
        selectorIA_2.setVisible(false);
        switch (i) {
            case 0:
                primera_vez = false;
                iCtrlPresentacion.modificar_idTablero_cargar(0);
                cargar_label_info_tablero();
                tableroCheckBox.setSelected(false);
                tableroCheckBox.setEnabled(false);
                IAVsIARadioButton.setSelected(true);
                selectorIA_0.setVisible(true);
                selectorIA_1.setVisible(true);
                break;
            case 1:
                primera_vez = true;
                iCtrlPresentacion.modificar_idTablero_cargar(0);
                cargar_label_info_tablero();
                tableroCheckBox.setEnabled(true);
                personaVsIARadioButton.setSelected(true);
                selectorIA_2.setVisible(true);
                break;
            case 2:
                primera_vez = true;
                iCtrlPresentacion.modificar_idTablero_cargar(0);
                cargar_label_info_tablero();
                tableroCheckBox.setEnabled(true);
                personaVsPersonaRadioButton.setSelected(true);
                buttonLoginUser2.setVisible(true);
                break;
        }
    }
    /**
     * Metodo que define los listeners en la configuracion de una Partida*/
    private void asignarListeners() {
        comenzarPartidaButton.addActionListener
                (event -> gestionar_inicio_de_juego());

        menuButton.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.MENU));

        buttonLoginUser2.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.LOGIN_USER2));

        IAVsIARadioButton.addActionListener
                (event -> gestion_ComboBox_uno_ON(0));

        personaVsIARadioButton.addActionListener
                (event -> gestion_ComboBox_uno_ON(1));

        personaVsPersonaRadioButton.addActionListener
                (event -> gestion_ComboBox_uno_ON(2));

        menuitemMenuPrincipal.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.MENU));

        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());
    }
}
