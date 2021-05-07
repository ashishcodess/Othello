package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaMenu {

    // Controlador de presentacion
    private CtrlPresentacion iCtrlPresentacion;

    private int iPanelActivo = 0; //para cambiar entre panel Ranking y estadisticas


    private JFrame frameVista = new JFrame("Vista Menu Principal");
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelInfo = new JPanel();
    private JPanel panelActivo = new JPanel();

    private JTextArea infoLogin = new JTextArea(10,10);

    //login/registro
    private JPanel panelLoginPrincipal = new JPanel();
    JPanel pLogin2 = new JPanel();
    private JPanel panelRegistro = new JPanel();
    private JLabel labelRegistroNickname= new JLabel("Introducir Nickname a registrar:");
    private JTextField textoRegistroNickname = new JTextField(15);
    private JButton buttonRegistro= new JButton("Registrarse");
    private JPanel panelLogin = new JPanel();
    JPanel pLogin1 = new JPanel();
    private JLabel labelID= new JLabel("ID:");
    private JTextField textoID = new JTextField(3);
    private JLabel labelNickname= new JLabel("Nickname:");
    private JTextField textoNickname = new JTextField(15);
    private JButton buttonLogin= new JButton("Login");
    private JButton buttonLimpiarLogin= new JButton("Limpiar");

    private JTextField textoLoginFinal = new JTextField(15);

    //menu principal
    private JPanel panelMenu = new JPanel();

    //BARRA DE MENU
    private JMenuBar menubarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuitemQuit = new JMenuItem("Salir");

    private JMenu menuJugador = new JMenu("Jugador");
    private JMenuItem menuitemLogin = new JMenuItem("Login Usuario");

    private JMenu menuTablero = new JMenu("Tablero");
    private JMenuItem menuitemCrearTablero = new JMenuItem("Crear Tablero");
    private JMenuItem menuitemMostrarTablero = new JMenuItem("Mostrar Tablero");
    private JMenuItem menuitemBorrarTablero = new JMenuItem("Borrar Tablero");

    private JMenu menuPartida = new JMenu("Partida");
    private JMenuItem menuitemCrearPartida = new JMenuItem("Crear Partida");
    private JMenuItem menuitemCargarPartida= new JMenuItem("Cargar Partida");
    private JMenuItem menuitemBorrarPartida = new JMenuItem("Borrar Partida");

    private JMenu menuRanking = new JMenu("Ranking");
    private JMenuItem menuItem_consultar_ranking = new JMenuItem("Consultar Ranking");
    private JMenuItem menuItem_consultar_estadisticas = new JMenuItem("Consultar Estadisticas");



    public VistaMenu (CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializarComponentes();
    }

    public void hacerVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }

    public void activar() {
        frameVista.setEnabled(true);
    }

    public void desactivar() {
        frameVista.setEnabled(false);
    }

    /////////// INICIALIZACION DE COMPONENTES

    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_menubarVista();
        inicializar_panelLogin();
        inicializar_panelInfo();
        inicializar_panelPrincipal();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(700,400)); //cambiar al pasar al menu principal

        //frameVista.setMinimumSize(new Dimension(700,750));// menu principal
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    private void inicializar_menubarVista() {
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        menuJugador.add(menuitemLogin);
        menubarVista.add(menuJugador);
        menuPartida.add(menuitemCrearPartida);
        menuPartida.add(menuitemCargarPartida);
        menuPartida.add(menuitemBorrarPartida);
        menubarVista.add(menuPartida);
        menuTablero.add(menuitemCrearTablero);
        menuTablero.add(menuitemMostrarTablero);
        menuTablero.add(menuitemBorrarTablero);
        menubarVista.add(menuTablero);
        menuRanking.add(menuItem_consultar_ranking);
        menuRanking.add(menuItem_consultar_estadisticas);
        menubarVista.add(menuRanking);
        frameVista.setJMenuBar(menubarVista);
    }


    private void inicializar_panelLogin() {

        infoLogin.setText("\n Informacion de Login:\n");
        infoLogin.append("\n         - Registro: si no estas registrado introducir un Nickname " +
                "y el sistema le asignara un ID para futuros usos del juego \n");
        infoLogin.append("\n         - Login: en caso de saber el ID y Nickname introducir estos " +
                "campos y pulsar boton de login");
        infoLogin.setEditable(false);

        panelLogin.setLayout(new BorderLayout());
        JLabel aa= new JLabel("Login de jugador:");
        panelLogin.add(aa, BorderLayout.NORTH);
        pLogin1.setLayout(new FlowLayout());
        pLogin1.add(labelID);
        pLogin1.add(textoID);
        pLogin1.add(labelNickname);
        pLogin1.add(textoNickname);
        pLogin1.add(buttonLogin);
        pLogin1.add(buttonLimpiarLogin);
        panelLogin.add(pLogin1, BorderLayout.SOUTH);

        panelRegistro.setLayout(new BorderLayout());
        JLabel a2= new JLabel("Registro de jugador:");
        panelRegistro.add(a2,BorderLayout.NORTH);


        pLogin2.setLayout(new FlowLayout());
        pLogin2.add(labelRegistroNickname);
        pLogin2.add(textoRegistroNickname);
        pLogin2.add(buttonRegistro);
        panelRegistro.add(pLogin2, BorderLayout.SOUTH);


        panelLoginPrincipal.setLayout(new BorderLayout());
        panelLoginPrincipal.add(panelLogin, BorderLayout.NORTH);
        panelLoginPrincipal.add(panelRegistro, BorderLayout.CENTER);
        textoLoginFinal.setEditable(false);
        panelLoginPrincipal.add(textoLoginFinal,BorderLayout.SOUTH);
    }



    private void inicializar_panelInfo() { //panel inicial
        panelActivo = panelLoginPrincipal;
        iPanelActivo = 1;
        panelInfo.add(panelActivo);
    }


    private void inicializar_panelPrincipal() {
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelInfo,BorderLayout.NORTH);
        panelPrincipal.add(infoLogin,BorderLayout.SOUTH);
    }

    /////////// LISTENERS (+ su asignacion)

    public void actionPerformed_buttonLogin (ActionEvent event) {
        int id = -1;
        try {
            id = Integer.parseInt(textoID.getText());
            String nick = textoNickname.getText();
            int res = iCtrlPresentacion.presentacion_login(id,nick);
            if (res == 1) textoLoginFinal.setText("Login correcto");
            else textoLoginFinal.setText("Login incorrecto (vuelve a introducir los datos)");
        }
        catch (Exception e) {
            textoLoginFinal.setText("Login incorrecto (vuelve a introducir los datos)");
        }
    }

    public void actionPerformed_buttonLimpiarLogin (ActionEvent event) {
        textoID.setText("");
        textoNickname.setText("");
        textoRegistroNickname.setText("");
    }

    public void actionPerformed_buttonRegistrarse (ActionEvent event) {
        try {
            String nick = textoRegistroNickname.getText();
            //FALTA FUNCIONES CTRLDOMINIO
            int res = iCtrlPresentacion.presentacion_registro_usuario(nick);
            if (res != -1) {
                textoLoginFinal.setText("Registro Correcto se te ha asignado el ID: " + res +" , y nickname: " + nick);
            }
            else textoLoginFinal.setText("Fallo al Registrarse");
        }
        catch (Exception e) {} //no hacer nada
    }


    private void asignar_listenersComponentes() {
        buttonLogin.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonLogin(event);
                    }
                });

        buttonLimpiarLogin.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonLimpiarLogin (event);
                    }
                });

        buttonRegistro.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonRegistrarse (event);
                    }
                });

        menuitemQuit.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        //Poner aqui lo de actualizar ranking (cuando tengamos la interfaz en general)
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
                        new VistaMenu(ctrlPresentacion).hacerVisible();
                    }});
    }

}
