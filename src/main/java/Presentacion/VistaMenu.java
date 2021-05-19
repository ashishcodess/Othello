package Presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;


public class VistaMenu {
    // Controlador de presentacion
    private final CtrlPresentacion iCtrlPresentacion;


    private JFrame frameVista = new JFrame("Menu Principal");

    private final JPanel panelPrincipal = new JPanel();
    private final JPanel panelInfo = new JPanel();

    private final JTextField textoInfoUsuario = new JTextField(15);

    //BARRA DE MENU
    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir");
    private final JMenuItem menuitemCreditos = new JMenuItem("Creditos");
    private final JMenuItem menuitemLogin = new JMenuItem("Login Usuario");
    private final JMenu menuTablero = new JMenu("Tablero");
    private final JMenuItem menuItem_crearTablero = new JMenuItem("Crear Tablero");
    private final JMenuItem menuItem_BorrarTablero = new JMenuItem("Borrar Tablero");
    private final JMenu menuPartida = new JMenu("Partida");
    private final JMenuItem menuItem_crearPartida = new JMenuItem("Crear Paritda");
    private final JMenuItem menuItem_CargarBorrarPartida = new JMenuItem("Cargar/Borrar Partida");
    private final JMenu menuRanking = new JMenu("Ranking");
    private final JMenuItem menuItem_consultar_ranking = new JMenuItem("Consultar Ranking");

    //Botones MENU
    private final JPanel panelBotonesMenu= new JPanel();

    private final JPanel panelMenuPartida= new JPanel();
    private final JLabel labelPartida = new JLabel("Partida");
    private final JButton buttonCrearPartida = new JButton("Crear");
    private final JButton buttonCargarBorrarPartida = new JButton("Cargar/Borrar");

    private final JPanel panelMenuTablero= new JPanel();
    private final JLabel labelTablero = new JLabel("Tablero");
    private final JButton buttonCrearTablero = new JButton("  Crear");
    private final JButton buttonBorrarTablero = new JButton("Borrar");

    private final JPanel panelMenuRanking= new JPanel();
    private final JLabel labelRanking = new JLabel("Ranking / Logros");
    private final JButton buttonConsultarRanking = new JButton("Consultar Ranking / Logros");

    private final JPanel panelOtrasOpciones= new JPanel();
    private final JLabel labelOtros = new JLabel("Otras opciones");
    private final JButton buttonLogin = new JButton("Login(otro usuario)");
    private final JButton buttonCreditos = new JButton("Creditos");
    private final JButton buttonSalir = new JButton("Salir del juego");


    /**
     * Constructora de VistaMenu
     * */
    public VistaMenu(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout());
        inicializarComponentes();
    }

    /**
     *Metodo hacerVisible
     * @param b si TRUE entonces el frame sera visible, caso contrario estara desactivado
     * */
    public void hacerVisible(boolean b) {
        if (b) {
            textoInfoUsuario.setText(iCtrlPresentacion.presentacion_get_info_usuario_activo());
            textoInfoUsuario.setEditable(false);
        }
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
        frameVista = iCtrlPresentacion.configuracion_frame(600,300,"Menu");
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    /**
     * Metodo para inicializar menuBar (barra de menu superior)
     * */
    private void inicializar_menubarVista() {
        menuFile.add(menuitemLogin);
        menuFile.add(menuitemCreditos);
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        menuTablero.add(menuItem_crearTablero);
        menuTablero.add(menuItem_BorrarTablero);
        menubarVista.add(menuTablero);
        menuPartida.add(menuItem_crearPartida);
        menuPartida.add(menuItem_CargarBorrarPartida);
        menubarVista.add(menuPartida);
        menuRanking.add(menuItem_consultar_ranking);
        menubarVista.add(menuRanking);
        frameVista.setJMenuBar(menubarVista);
    }


    /**
     * Metodo para inicializar todos los paneles
     * */
    private void inicializar_paneles() {
        //PANEL MENU PARTIDA
        panelMenuPartida.setLayout(new BoxLayout(panelMenuPartida,BoxLayout.PAGE_AXIS));
        panelMenuPartida.add(labelPartida);
        panelMenuPartida.add(buttonCrearPartida);
        buttonCrearPartida.setToolTipText("Entra en el menu de config. de una Partida");
        panelMenuPartida.add(buttonCargarBorrarPartida);
        buttonCrearPartida.setToolTipText("Entra en el menu para Cargar/Borrar una partida guardada por el usuario");

        //PANEL MENU TABLERO
        panelMenuTablero.setLayout(new BoxLayout(panelMenuTablero,BoxLayout.PAGE_AXIS));
        panelMenuTablero.add(labelTablero);
        panelMenuTablero.add(buttonCrearTablero);
        buttonCrearTablero.setToolTipText("Entra en el menu de config. de una Tablero");
        panelMenuTablero.add(buttonBorrarTablero);
        buttonBorrarTablero.setToolTipText("Entra en el menu para Borrar un tablero guardado en el sistema");
        panelMenuTablero.setBorder((new LineBorder(Color.BLACK)));

        //PANEL MENU RANKING
        panelMenuRanking.setLayout(new BoxLayout(panelMenuRanking,BoxLayout.PAGE_AXIS));
        panelMenuRanking.add(labelRanking);
        panelMenuRanking.add(buttonConsultarRanking);
        buttonConsultarRanking.setToolTipText("Consulta el ranking, estadisticas de un jugador o los logros");

        //PANEL MENU OTRAS OPCIONES
        panelOtrasOpciones.setLayout(new FlowLayout());
        panelOtrasOpciones.add(labelOtros);
        panelOtrasOpciones.add(buttonLogin);
        buttonLogin.setToolTipText("Volver al menu de Login (login con otro usuario)");
        panelOtrasOpciones.add(buttonCreditos);
        buttonCreditos.setToolTipText("Muestra los creditos");
        panelOtrasOpciones.add(buttonSalir);
        buttonSalir.setToolTipText("Salir del juego");

        //PANEL BOTONES GENERAL
        panelBotonesMenu.setLayout(new BorderLayout());
        panelBotonesMenu.add(panelMenuTablero,BorderLayout.WEST);
        panelBotonesMenu.add(panelMenuPartida,BorderLayout.CENTER);
        panelBotonesMenu.add(panelMenuRanking,BorderLayout.EAST);
        panelBotonesMenu.add(panelOtrasOpciones,BorderLayout.SOUTH);

        //PANEL INFO
        panelInfo.add(panelBotonesMenu);

        //PANEL PRINCIPAL
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(textoInfoUsuario,BorderLayout.NORTH);
        panelPrincipal.add(panelInfo,BorderLayout.CENTER);

    }



    /////////// LISTENERS (+ su asignacion)

    /**
     * Metodo actionPerfomed del boton de Login (Para cargar otro usuario diferente al actual)
     * */
    public void actionPerformed_buttonLogin (ActionEvent event) {
        iCtrlPresentacion.hacerVisibleVista(vistaActiva.LOGIN);
    }


    /**
     * Metodo para asignar los listeners a cada componente
     * */
    private void asignar_listenersComponentes() {
        buttonLogin.addActionListener
                (this::actionPerformed_buttonLogin);

        buttonCreditos.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.CREDITOS));


        buttonSalir.addActionListener
                (event -> {
                    iCtrlPresentacion.presentacion_exportar_ranking();
                    System.exit(0);
                });

        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());

        buttonCrearPartida.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.CONFIGPARTIDA));

        buttonBorrarTablero.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.CARGARTABLERO));


        menuitemLogin.addActionListener
                (this::actionPerformed_buttonLogin);

        menuitemCreditos.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.CREDITOS));

        menuItem_consultar_ranking.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.RANKING));

        buttonConsultarRanking.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.RANKING));

    }
}
