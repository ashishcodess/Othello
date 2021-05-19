package Presentacion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VistaLoginPartida {

    // Controlador de presentacion
    private final CtrlPresentacion iCtrlPresentacion;

    private final JPanel panelPrincipal = new JPanel();

    private JFrame frameVista = new JFrame("Partidas Guardadas");

    //login para mirar su tablas guardadas
    private final JPanel panelLoginPrincipal = new JPanel();
    JPanel pLogin2 = new JPanel();
    private final JPanel panelLogin = new JPanel();
    JPanel pLogin1 = new JPanel();
    JPanel pLogin3 = new JPanel();

    private final JLabel labelID= new JLabel("ID:");
    private final JTextField textoID = new JTextField(3);
    private final JLabel labelNickname= new JLabel("Nickname:");
    private final JTextField textoNickname = new JTextField(15);
    private final JButton buttonBuscar= new JButton("Buscar partidas");
    private final JButton buttonLimpiarLogin= new JButton("Limpiar");
    private final JComboBox<String> selector_tablero = new JComboBox<>();

    private JLabel labelJ1_J2 = new JLabel(); //doubt


    private final JTextField textoBuscarPartida = new JTextField(15);

    //BARRA DE MENU
    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemLogin = new JMenuItem("Login Usuario");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir");


    /**
     * Constructora de VistaLoginPartida
     * */

    public VistaLoginPartida(CtrlPresentacion pCtrlPresentacion) {
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
        if (b) {
            clear_textos();
        }
    }

    private void clear_textos() {
        textoNickname.setText("");
        textoID.setText("");
    }

    /////////// INICIALIZACION DE COMPONENTES

    /**
     * Metodo para inicializar componentes (menuBar, paneles y frame)
     * */
    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_menubarVista();
        inicializar_paneles(); // doubt
        asignar_listenersComponentes();
    }

    /**
     * Metodo para inicializar frame
     * */
    private void inicializar_frameVista() {
        frameVista = iCtrlPresentacion.configuracion_frame(650,250, "Partidas");
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
        labelJ1_J2= new JLabel("");
        panelLogin.add(labelJ1_J2, BorderLayout.NORTH);
        pLogin1.setLayout(new FlowLayout());
        pLogin1.add(labelID);
        pLogin1.add(textoID);
        pLogin1.add(labelNickname);
        pLogin1.add(textoNickname);
        pLogin1.add(buttonBuscar);
        buttonBuscar.setToolTipText("Busca Partida del usuario introducido por el campo de ID y de Nickname");
        pLogin1.add(buttonLimpiarLogin);
        buttonLimpiarLogin.setToolTipText("Hace un clear de los TextArea's");
        panelLogin.add(pLogin1, BorderLayout.SOUTH);

        pLogin2.setLayout(new FlowLayout());

        pLogin1.add(selector_tablero);
        selector_tablero.setVisible(false);

        panelLoginPrincipal.setLayout(new BorderLayout());
        panelLoginPrincipal.add(panelLogin, BorderLayout.NORTH);

        textoBuscarPartida.setEditable(false);
        panelLoginPrincipal.add(textoBuscarPartida,BorderLayout.SOUTH);


        //PANEL PRINCIPAL
        panelPrincipal.setLayout(new FlowLayout());
        panelPrincipal.add(panelLoginPrincipal);

    }


    /////////// LISTENERS (+ su asignacion)

    /**
     * Metodo actionPerfomed del boton de Login
     * */
    public void actionPerformed_buttonBuscar (ActionEvent event) {
        int id;
        try {
            id = Integer.parseInt(textoID.getText());
            String nick = textoNickname.getText();
            ArrayList<String> partidas_guardadas = iCtrlPresentacion.presentacion_buscar_partidas(id,nick);
            if (!partidas_guardadas.isEmpty()){
                textoBuscarPartida.setText("");
                selector_tablero.removeAllItems();
                selector_tablero.setVisible(true);
                for (String partida : partidas_guardadas) {
                    System.out.println(partida);
                    selector_tablero.addItem(partida);
                }
            // // Haremos que el combo box sea visible.
            }
            else textoBuscarPartida.setText("No datas disponibles para ID y Nickamne Introducido");
        }
        catch (Exception e) {
            textoBuscarPartida.setText("No datas disponibles para ID y Nickname Introducido");
        }
    }

    /**
     * Metodo actionPerfomed del boton de Limpiar (borrar contenido de los contenedores de texto)
     * */
    public void actionPerformed_buttonLimpiarLogin (ActionEvent event) {
        textoID.setText("");
        textoNickname.setText("");
        selector_tablero.removeAllItems();
    }


    /**
     * Metodo para asignar los listeners a cada componente
     * */
    private void asignar_listenersComponentes() {
        buttonBuscar.addActionListener
                (this::actionPerformed_buttonBuscar);

        buttonLimpiarLogin.addActionListener
                (this::actionPerformed_buttonLimpiarLogin);


        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());
    }

}
