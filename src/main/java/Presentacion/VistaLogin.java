package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VistaLogin {

    // Controlador de presentacion
    private final CtrlPresentacion iCtrlPresentacion;

    private final JFrame frameVista = new JFrame("Login");
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

    private final JTextField textoLoginFinal = new JTextField(15);

    //BARRA DE MENU
    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemLogin = new JMenuItem("Login Usuario");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir");


    /**
     * Constructora de VistaLogin
     * */
    public VistaLogin(CtrlPresentacion pCtrlPresentacion) {
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
        frameVista.setMinimumSize(new Dimension(650,250));
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
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        frameVista.setJMenuBar(menubarVista);
    }

    /**
     * Metodo para inicializar todos los paneles
     * */
    private void inicializar_paneles() {
        panelLogin.setLayout(new BorderLayout());
        JLabel aa= new JLabel("Login de jugador:");
        panelLogin.add(aa, BorderLayout.NORTH);
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
     * */
    public void actionPerformed_buttonLogin (ActionEvent event) {
        int id;
        try {
            id = Integer.parseInt(textoID.getText());
            String nick = textoNickname.getText();
            int res = iCtrlPresentacion.presentacion_login(id,nick);
            if (res == 1) {
                textoLoginFinal.setText("");
                iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU);
            }
            else textoLoginFinal.setText("Login incorrecto (vuelve a introducir los datos)");
        }
        catch (Exception e) {
            textoLoginFinal.setText("Login incorrecto (vuelve a introducir los datos)");
        }
    }

    /**
     * Metodo actionPerfomed del boton de Limpiar (borrar contenido de los contenedores de texto)
     * */
    public void actionPerformed_buttonLimpiarLogin (ActionEvent event) {
        textoID.setText("");
        textoNickname.setText("");
        textoRegistroNickname.setText("");
    }

    /**
     * Metodo actionPerfomed del boton de Registrarse
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
                (event -> {
                    iCtrlPresentacion.presentacion_exportar_ranking();
                    System.exit(0);
                });
    }


}
