package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaMenu {
    // Controlador de presentacion
    private CtrlPresentacion iCtrlPresentacion;

    private int iPanelActivo = 0; //para cambiar entre panel Ranking y estadisticas

    private JFrame frameVista = new JFrame("Menu Principal");

    private JPanel panelPrincipal = new JPanel();
    private JPanel panelInfo = new JPanel();
    private JPanel panelActivo = new JPanel();

    //BARRA DE MENU
    private JMenuBar menubarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuitemQuit = new JMenuItem("Salir");
    private JMenu menuJugador = new JMenu("Jugador");
    private JMenuItem menuitemLogin = new JMenuItem("Login Usuario");
    private JMenu menuTablero = new JMenu("Tablero");
    private JMenuItem menuItem_crearTablero = new JMenuItem("Crear Tablero");
    private JMenuItem menuItem_MostrarTablero = new JMenuItem("Mostrar Tablero");
    private JMenuItem menuItem_BorrarTablero = new JMenuItem("Borrar Tablero");
    private JMenu menuPartida = new JMenu("Partida");
    private JMenuItem menuItem_crearPartida = new JMenuItem("Crear Paritda");
    private JMenuItem menuItem_CargarPartida = new JMenuItem("Cargar Partida");
    private JMenuItem menuItem_BorrarPartida = new JMenuItem("Borrar Partida");
    private JMenu menuRanking = new JMenu("Ranking");
    private JMenuItem menuItem_consultar_ranking = new JMenuItem("Consultar Ranking");
    private JMenuItem menuItem_consultar_estadisticas = new JMenuItem("Consultar Estadisticas");

    //Botones MENU
    private JPanel panelBotonesMenu= new JPanel();

    private JPanel panelMenuPartida= new JPanel();
    private JLabel labelPartida = new JLabel("Partida");
    private JButton buttonCrearPartida = new JButton("Crear");
    private JButton buttonCargarPartida = new JButton("Cargar");
    private JButton buttonBorrarPartida = new JButton("Borrar");

    private JPanel panelMenuTablero= new JPanel();
    private JLabel labelTablero = new JLabel("Tablero");
    private JButton buttonCrearTablero = new JButton("  Crear");
    private JButton buttonMostrarTablero = new JButton("Mostrar");
    private JButton buttonBorrarTablero = new JButton("Borrar");

    private JPanel panelMenuRanking= new JPanel();
    private JLabel labelRanking = new JLabel("Ranking");
    private JButton buttonConsultarRanking = new JButton("Consultar Ranking");

    private JPanel panelOtrasOpciones= new JPanel();
    private JLabel labelOtros = new JLabel("Otras opciones");
    private JButton buttonLogin = new JButton("Login");
    private JButton buttonSalir = new JButton("Salir del juego");


    public VistaMenu(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout());
        inicializarComponentes();
    }

    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }

    /////////// INICIALIZACION DE COMPONENTES

    private void inicializarComponentes() {
        inicializar_menubarVista();
        inicializar_frameVista();
        inicializar_panelPrincipal();
        inicializar_panelInfo();
        inicializar_panelMenuPartida();
        inicializar_panelMenuTablero();
        inicializar_panelMenuRanking();
        inicializar_panelMenuOtrasOpciones();
        inicializar_panelBotonesMenu();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(700,400));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    private void inicializar_panelPrincipal() {
        panelPrincipal.setLayout(new BorderLayout());
        //Parte central/abajo informacion del usuario logeado
        panelPrincipal.add(panelInfo,BorderLayout.CENTER);
    }

    private void inicializar_menubarVista() {
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        menuJugador.add(menuitemLogin);
        menubarVista.add(menuJugador);
        menuTablero.add(menuItem_crearTablero);
        menuTablero.add(menuItem_MostrarTablero);
        menuTablero.add(menuItem_BorrarTablero);
        menubarVista.add(menuTablero);
        menuPartida.add(menuItem_crearPartida);
        menuPartida.add(menuItem_CargarPartida);
        menuPartida.add(menuItem_BorrarPartida);
        menubarVista.add(menuPartida);
        menuRanking.add(menuItem_consultar_ranking);
        menuRanking.add(menuItem_consultar_estadisticas);
        menubarVista.add(menuRanking);
        frameVista.setJMenuBar(menubarVista);
    }

    private void inicializar_panelInfo() { //panel inicial
        panelActivo = panelBotonesMenu;
        iPanelActivo = 1;
        panelInfo.add(panelActivo);
    }

    private void inicializar_panelMenuPartida() {
        panelMenuPartida.setLayout(new BoxLayout(panelMenuPartida,BoxLayout.PAGE_AXIS));
        panelMenuPartida.add(labelPartida);
        //buttonCrearPartida.setPreferredSize(new Dimension(100,20));
        panelMenuPartida.add(buttonCrearPartida);
        panelMenuPartida.add(buttonBorrarPartida);
        panelMenuPartida.add(buttonCargarPartida);
    }

    private void inicializar_panelMenuTablero() {
        panelMenuTablero.setLayout(new BoxLayout(panelMenuTablero,BoxLayout.PAGE_AXIS));
        panelMenuTablero.add(labelTablero);
        //buttonCrearPartida.setPreferredSize(new Dimension(100,20));
        panelMenuTablero.add(buttonCrearTablero);
        panelMenuTablero.add(buttonBorrarTablero);
        panelMenuTablero.add(buttonMostrarTablero);
    }

    private void inicializar_panelMenuRanking() {
        panelMenuRanking.setLayout(new BoxLayout(panelMenuRanking,BoxLayout.PAGE_AXIS));
        panelMenuRanking.add(labelRanking);
        panelMenuRanking.add(buttonConsultarRanking);
    }

    private void inicializar_panelMenuOtrasOpciones() {
        panelOtrasOpciones.setLayout(new FlowLayout());
        panelOtrasOpciones.add(labelOtros);
        panelOtrasOpciones.add(buttonLogin);
        panelOtrasOpciones.add(buttonSalir);
    }

    private void inicializar_panelBotonesMenu() {
        /*panelBotonesMenu.setLayout(new FlowLayout());
        panelBotonesMenu.add(panelMenuTablero);
        panelBotonesMenu.add(panelMenuPartida);
        panelBotonesMenu.add(panelMenuRanking);
        panelBotonesMenu.add(panelOtrasOpciones);*/

        panelBotonesMenu.setLayout(new BorderLayout());
        panelBotonesMenu.add(panelMenuTablero,BorderLayout.WEST);
        panelBotonesMenu.add(panelMenuPartida,BorderLayout.CENTER);
        panelBotonesMenu.add(panelMenuRanking,BorderLayout.EAST);
        panelBotonesMenu.add(panelOtrasOpciones,BorderLayout.SOUTH);

    }


    /////////// LISTENERS (+ su asignacion)

    private void asignar_listenersComponentes() {

        menuitemQuit.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.presentacion_exportar_ranking();
                        System.exit(0);
                    }
                });

    }

    /////////// MAIN (para poder probar)
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                        ctrlPresentacion.inicializarPresentacion();
                        new VistaMenu(ctrlPresentacion).hacerVisible(true);
                    }});
    }
}
