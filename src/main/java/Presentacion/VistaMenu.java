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

    private JTextField textoInfoUsuario = new JTextField(15);


    //BARRA DE MENU
    private JMenuBar menubarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuitemQuit = new JMenuItem("Salir");
    private JMenuItem menuitemCreditos = new JMenuItem("Creditos");
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
    private JButton buttonLogin = new JButton("Login(otro usuario)");
    private JButton buttonCreditos = new JButton("Creditos");
    private JButton buttonSalir = new JButton("Salir del juego");


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
        inicializar_menubarVista();
        inicializar_frameVista();
        inicializar_paneles();
        asignar_listenersComponentes();
    }


    /**
     * Metodo para inicializar frame
     * */
    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(600,300));
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
        menuFile.add(menuitemLogin);
        menuFile.add(menuitemCreditos);
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        menuTablero.add(menuItem_crearTablero);
        menuTablero.add(menuItem_MostrarTablero);
        menuTablero.add(menuItem_BorrarTablero);
        menubarVista.add(menuTablero);
        menuPartida.add(menuItem_crearPartida);
        menuPartida.add(menuItem_CargarPartida);
        menuPartida.add(menuItem_BorrarPartida);
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
        panelMenuPartida.add(buttonBorrarPartida);
        panelMenuPartida.add(buttonCargarPartida);

        //PANEL MENU TABLERO
        panelMenuTablero.setLayout(new BoxLayout(panelMenuTablero,BoxLayout.PAGE_AXIS));
        panelMenuTablero.add(labelTablero);
        panelMenuTablero.add(buttonCrearTablero);
        panelMenuTablero.add(buttonBorrarTablero);
        panelMenuTablero.add(buttonMostrarTablero);

        //PANEL MENU RANKING
        panelMenuRanking.setLayout(new BoxLayout(panelMenuRanking,BoxLayout.PAGE_AXIS));
        panelMenuRanking.add(labelRanking);
        panelMenuRanking.add(buttonConsultarRanking);

        //PANEL MENU OTRAS OPCIONES
        panelOtrasOpciones.setLayout(new FlowLayout());
        panelOtrasOpciones.add(labelOtros);
        panelOtrasOpciones.add(buttonLogin);
        panelOtrasOpciones.add(buttonCreditos);
        panelOtrasOpciones.add(buttonSalir);

        //PANEL BOTONES GENERAL
        panelBotonesMenu.setLayout(new BorderLayout());
        panelBotonesMenu.add(panelMenuTablero,BorderLayout.WEST);
        panelBotonesMenu.add(panelMenuPartida,BorderLayout.CENTER);
        panelBotonesMenu.add(panelMenuRanking,BorderLayout.EAST);
        panelBotonesMenu.add(panelOtrasOpciones,BorderLayout.SOUTH);

        //PANEL INFO
        panelActivo = panelBotonesMenu;
        iPanelActivo = 1;
        panelInfo.add(panelActivo);


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
        iCtrlPresentacion.hacerVisibleVista(0);
    }


    /**
     * Metodo para asignar los listeners a cada componente
     * */
    private void asignar_listenersComponentes() {
        buttonLogin.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonLogin(event);
                    }
                });

        buttonCreditos.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.hacerVisibleVista(3);
                    }
                });

        buttonSalir.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.presentacion_exportar_ranking();
                        System.exit(0);
                    }
                });

        menuitemQuit.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.presentacion_exportar_ranking();
                        System.exit(0);
                    }
                });



        menuitemLogin.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonLogin(event);
                    }
                });

        menuitemCreditos.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.hacerVisibleVista(3);
                    }
                });

        menuItem_consultar_ranking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.hacerVisibleVista(2);
                    }
                });

        buttonConsultarRanking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        iCtrlPresentacion.hacerVisibleVista(2);
                    }
                });

    }

    /////////// MAIN (para poder probar)
    /*public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                        ctrlPresentacion.inicializarPresentacion();
                        new VistaMenu(ctrlPresentacion).hacerVisible(true);
                    }});
    }*/
}
