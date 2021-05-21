package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**Vista de Login (tanto Jugador1 como Jugador2)*/
public class VistaLogin {

    // Controlador de presentacion
    private final CtrlPresentacion iCtrlPresentacion;

    private JFrame frameVista = new JFrame("Login");
    private final JPanel panelPrincipal = new JPanel();


    //login/registro
    private final JPanel panelLoginPrincipal = new JPanel();
    JPanel pLogin2 = new JPanel();
    private final JPanel panelRegistro = new JPanel();
    private final JLabel labelRegistroNickname= new JLabel("Introducir Nickname a registrar:");
    private final JTextField textoRegistroNickname = new JTextField(15);
    private final JButton buttonRegistro= new JButton("Registrarse");
    private final JPanel panelLogin = new JPanel();
    JPanel pLogin1 = new JPanel();
    private final JLabel labelID= new JLabel("ID:");
    private final JTextField textoID = new JTextField(3);
    private final JLabel labelNickname= new JLabel("Nickname:");
    private final JTextField textoNickname = new JTextField(15);
    private final JButton buttonLogin= new JButton("Login");
    private final JButton buttonLimpiarLogin= new JButton("Limpiar");

    private JLabel labelJ1_J2 = new JLabel();

    private CtrlPresentacion.tipoJugador tJugador;

    private final JTextField textoLoginFinal = new JTextField(15);

    //BARRA DE MENU
    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemLogin = new JMenuItem("Login Usuario");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir del Juego");


    /**
     * Constructora de VistaLogin
     * @param pCtrlPresentacion controlador de presentacion a asignarle a dicha vista
     * @param a tipoJugador a modificar para cambios de labels
     * */
    public VistaLogin(CtrlPresentacion pCtrlPresentacion, CtrlPresentacion.tipoJugador a) {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        tJugador = a;
        inicializarComponentes(tJugador);
    }

    /**
     *Metodo hacerVisible
     * @param b si TRUE entonces el frame sera visible, caso contrario estara desactivado
     * @param a tipoJugador a comprobar y asignar para modificacion de labels
     * */
    public void hacerVisible(boolean b, CtrlPresentacion.tipoJugador a) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
        if (b) {
            tJugador = a;
            clear_textos();
        }
    }

    /**
     * clear_texto (limpia los campos de texto e inicializa correctamente
     * el label de la informacion del jugador a loguearse)"
     * */
    private void clear_textos() {
        switch (tJugador) {
            case JUGADOR1:
                labelJ1_J2.setText("Login de jugador: JUGADOR 1");
                break;
            case JUGADOR2:
                labelJ1_J2.setText("Login de jugador: JUGADOR 2");
                break;
        }
        textoNickname.setText("");
        textoID.setText("");
        textoRegistroNickname.setText("");
    }

    /////////// INICIALIZACION DE COMPONENTES

    /**
     * Metodo para inicializar componentes (menuBar, paneles y frame)
     * @param a tipo de Jugador (para inicializacion de paneles: JUGADOR1 o JUGADOR2)
     * */
    private void inicializarComponentes(CtrlPresentacion.tipoJugador a) {
        inicializar_frameVista();
        inicializar_menubarVista();
        inicializar_paneles(a);
        asignar_listenersComponentes();
    }

    /**
     * Metodo para inicializar frame
     * */
    private void inicializar_frameVista() {
        frameVista = iCtrlPresentacion.configuracion_frame(650,250, "Login");
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    /**
     * Metodo para inicializar menuBar (barra de menu superior)
     * */
    private void inicializar_menubarVista() {
        menuFile.add(menuitemLogin);
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        frameVista.setJMenuBar(menubarVista);
    }


    /**
     * Metodo para inicializar todos los paneles
     * @param a tipoJuador a comprobar (para modificacion de labels)
     * */
    private void inicializar_paneles(CtrlPresentacion.tipoJugador a) {
        panelLogin.setLayout(new BorderLayout());
        labelJ1_J2= new JLabel("");
        switch (a) {
            case JUGADOR1:
                labelJ1_J2.setText("Login de jugador: JUGADOR 1");
                break;
            case JUGADOR2:
                labelJ1_J2.setText("Login de jugador: JUGADOR 2");
                break;
        }
        panelLogin.add(labelJ1_J2, BorderLayout.NORTH);
        pLogin1.setLayout(new FlowLayout());
        pLogin1.add(labelID);
        pLogin1.add(textoID);
        pLogin1.add(labelNickname);
        pLogin1.add(textoNickname);
        pLogin1.add(buttonLogin);
        buttonLogin.setToolTipText("Hace login con el usuario introducido por el campo de ID y de Nickname");
        pLogin1.add(buttonLimpiarLogin);
        buttonLimpiarLogin.setToolTipText("Hace un clear de los TextArea's");
        panelLogin.add(pLogin1, BorderLayout.SOUTH);
        panelRegistro.setLayout(new BorderLayout());
        JLabel a2= new JLabel("Registro de jugador:");
        panelRegistro.add(a2,BorderLayout.NORTH);

        pLogin2.setLayout(new FlowLayout());
        pLogin2.add(labelRegistroNickname);
        pLogin2.add(textoRegistroNickname);
        pLogin2.add(buttonRegistro);
        buttonRegistro.setToolTipText("Introducir nickname a registrar, el sistema le asignara un ID para futuros usos del juego");
        panelRegistro.add(pLogin2, BorderLayout.SOUTH);

        panelLoginPrincipal.setLayout(new BorderLayout());
        panelLoginPrincipal.add(panelLogin, BorderLayout.NORTH);
        panelLoginPrincipal.add(panelRegistro, BorderLayout.CENTER);

        textoLoginFinal.setEditable(false);
        panelLoginPrincipal.add(textoLoginFinal,BorderLayout.SOUTH);


        //PANEL PRINCIPAL
        panelPrincipal.setLayout(new FlowLayout());
        panelPrincipal.add(panelLoginPrincipal);

    }


    /////////// LISTENERS (+ su asignacion)

    /**
     * Metodo actionPerfomed del boton de Login
     * @param event evento del boton de Login
     * */
    public void actionPerformed_buttonLogin (ActionEvent event) {
        int id;
        try {
            id = Integer.parseInt(textoID.getText());
            String nick = textoNickname.getText();
            boolean res = iCtrlPresentacion.presentacion_login(id,nick,tJugador);
            if (res) {
                textoLoginFinal.setText("");
                switch (tJugador) {
                    case JUGADOR1:
                        iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU);
                        break;
                    case JUGADOR2:
                        iCtrlPresentacion.hacerVisibleVista(vistaActiva.CONFIGPARTIDA);
                        break;
                }
            }
            else textoLoginFinal.setText("Login incorrecto (vuelve a introducir los datos)");
        }
        catch (Exception e) {
            textoLoginFinal.setText("Login incorrecto (vuelve a introducir los datos)");
        }
    }

    /**
     * Metodo actionPerfomed del boton de Limpiar (borrar contenido de los contenedores de texto)
     * @param event evento del boton de Limpiar los campos de login
     * */
    public void actionPerformed_buttonLimpiarLogin (ActionEvent event) {
        textoID.setText("");
        textoNickname.setText("");
        textoRegistroNickname.setText("");
    }


    /**
     * Metodo actionPerfomed del boton de Registrarse
     * @param event evento del boton de registro de usuario
     * */
    public void actionPerformed_buttonRegistrarse (ActionEvent event) {
        String nick = textoRegistroNickname.getText();
        int res = iCtrlPresentacion.presentacion_registro_usuario(nick);
        if (res != -1) {
            textoLoginFinal.setText("Registro Correcto se te ha asignado el ID: " + res +" , y nickname: " + nick);
        }
        else textoLoginFinal.setText("Fallo al Registrarse");
    }

    /**
     * Metodo para asignar los listeners a cada componente
     * */
    private void asignar_listenersComponentes() {
        buttonLogin.addActionListener
                (this::actionPerformed_buttonLogin);

        buttonLimpiarLogin.addActionListener
                (this::actionPerformed_buttonLimpiarLogin);

        buttonRegistro.addActionListener
                (this::actionPerformed_buttonRegistrarse);

        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());
    }


}
