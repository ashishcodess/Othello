package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin {

    // Controlador de presentacion
    private CtrlPresentacion iCtrlPresentacion;

    private int iPanelActivo = 0; //para cambiar entre panel Ranking y estadisticas

    private JFrame frameVista = new JFrame("Login");
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

    //BARRA DE MENU
    private JMenuBar menubarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuitemLogin = new JMenuItem("Login Usuario");
    private JMenuItem menuitemQuit = new JMenuItem("Salir");


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
        frameVista.setMinimumSize(new Dimension(700,400));
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
        //PANEL LOGIN
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

        //PANEL INFO
        panelActivo = panelLoginPrincipal;
        iPanelActivo = 1;
        panelInfo.add(panelActivo);

        //PANEL PRINCIPAL
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelInfo,BorderLayout.NORTH);
        //panelPrincipal.add(panelLoginPrincipal,BorderLayout.NORTH);
        panelPrincipal.add(infoLogin,BorderLayout.SOUTH);
    }


    /////////// LISTENERS (+ su asignacion)

    /**
     * Metodo actionPerfomed del boton de Login
     * */
    public void actionPerformed_buttonLogin (ActionEvent event) {
        int id = -1;
        try {
            id = Integer.parseInt(textoID.getText());
            String nick = textoNickname.getText();
            int res = iCtrlPresentacion.presentacion_login(id,nick);
            if (res == 1) {
                textoLoginFinal.setText("");
                iCtrlPresentacion.hacerVisibleVista(1);
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
        try {
            String nick = textoRegistroNickname.getText();
            int res = iCtrlPresentacion.presentacion_registro_usuario(nick);
            if (res != -1) {
                textoLoginFinal.setText("Registro Correcto se te ha asignado el ID: " + res +" , y nickname: " + nick);
            }
            else textoLoginFinal.setText("Fallo al Registrarse");
        }
        catch (Exception e) {} //no hacer nada
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
                        iCtrlPresentacion.presentacion_exportar_ranking();
                        System.exit(0);
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
                        new VistaLogin(ctrlPresentacion).hacerVisible(true);
                    }});
    }*/

}
