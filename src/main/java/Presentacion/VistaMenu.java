package Presentacion;

import javax.swing.*;

public class VistaMenu {

    // Controlador de presentacion
    private CtrlPresentacion iCtrlPresentacion;

    private int iPanelActivo = 0; //para cambiar entre panel Ranking y estadisticas


    private JFrame frameVista = new JFrame("Vista Menu Principal");
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelInfo = new JPanel();
    private JPanel panelActivo = new JPanel();

    //login
    private JPanel panelLoginPrincipal = new JPanel();
    private JPanel panelRegistro = new JPanel();
    private JLabel labelRegistroNickname= new JLabel("Introducir Nickname a registrar:");
    private JTextField textoRegistroNickname = new JTextField(15);
    private JButton buttonRegistro= new JButton("Registrarse");

    private JPanel panelLogin = new JPanel();
    private JTextField textoID = new JTextField(3);
    private JLabel labelNickname= new JLabel("Nickname:");
    private JTextField textoNickname = new JTextField(15);
    private JButton buttonLogin= new JButton("Login");
    private JButton buttonLimpiarLogin= new JButton("Limpiar");

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

}
