package Presentacion;

import ControladorPersistencia.CtrlPersitencia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class VistaCargarPartida {
    private String imagen_vacia = "";
    private String imagen_disponible = "";
    private String imagen_blanca = "";
    private String imagen_negra = "";
 

    private final CtrlPresentacion iCtrlPresentacion;
    private final JPanel panelPrincipal = new JPanel();
    private JFrame frameVista = new JFrame("Vista Partida");


    private final JButton[][] botonesMatriz = new JButton[8][8];


    private final JPanel panelBotones = new JPanel();
    private final JButton buttonCargar = new JButton("Cargar Tablero");
    private final JButton buttonBorrar = new JButton("Borrar Tablero");
    private final JButton buttonLimpiar = new JButton("Limpiar");
    private final JButton buttonMenu = new JButton("Menu Principal");
    private JComboBox<String> selector_partida = new JComboBox<>();

    private final JPanel panelInfo = new JPanel();
    private final JLabel Info_partida = new JLabel("Jugadores");
    private final JTextField textoInfo1 = new JTextField(15);
    private final JTextField textoInfo2 = new JTextField(15);

    private int id_partida_seleccionado = -1;


    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemMenu = new JMenuItem("Menu Principal");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir");


    public VistaCargarPartida(CtrlPresentacion pCtrlPresentacion)  {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializar_frameVista();
        obtener_dir_imagenes();
        inicializar_Componentes();
        asignar_listenersComponentes();
        inicializar_menubarVista();
    }

    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
        if (b) {
            buttonCargar.setEnabled(iCtrlPresentacion.consultar_idTablero_cargar() == -1);
            limpiar_vista_previa_tablero();
            recargar_comboBox_tableros();
        }
    }
    /**
     * Metodo para inicializar frame
     * */
    private void inicializar_frameVista() {
        frameVista = iCtrlPresentacion.configuracion_frame(950,850,"Cargar/Borrar Partida");
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    /**
     * Metodo para inicializar menuBar (barra de menu superior)
     * */
    private void inicializar_menubarVista() {
        menuFile.add(menuitemMenu);
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        frameVista.setJMenuBar(menubarVista);
    }
    /**
     * Metodo obtener direccion de imagenes (para iconos de fichas)
     * */
    private void obtener_dir_imagenes() {
        imagen_vacia = iCtrlPresentacion.presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG.IMG_VACIA);
        imagen_disponible = iCtrlPresentacion.presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG.IMG_DISPONIBLE);
        imagen_blanca = iCtrlPresentacion.presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG.IMG_BLANCA);
        imagen_negra = iCtrlPresentacion.presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG.IMG_NEGRA);
    }

    /**
     * Metodo de inicicacion de los componentes (tablero y botones)
     * */
    private void inicializar_Componentes() {
        JPanel tablero = new JPanel(new GridLayout(0, 8));
        tablero.setBorder((new LineBorder(Color.BLACK)));
        Insets margenesBotones = new Insets(0,0,0,0);
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < botonesMatriz[i].length; ++j) {
                JButton b = new JButton();
                b.setMargin(margenesBotones);
                b.setBackground(Color.gray);
                botonesMatriz[i][j] = b;
                tablero.add(botonesMatriz[i][j]);
            }
        }

        JPanel panelAux = new JPanel();
        panelAux.setLayout(new BorderLayout());


        panelBotones.setLayout(new FlowLayout());
        selector_partida.insertItemAt("", 0);
        recargar_comboBox_tableros();

        JPanel panelAux2 = new JPanel();
        panelAux2.setLayout(new FlowLayout());
        panelAux2.add(selector_partida);
        panelAux2.add(buttonLimpiar);
        panelAux2.add(buttonMenu);

        panelAux.add(panelAux2,BorderLayout.NORTH);


        JPanel panelAux3 = new JPanel();
        panelAux3.setLayout(new BorderLayout());
        panelAux3.add(buttonCargar,BorderLayout.NORTH);
        panelAux3.add(buttonBorrar,BorderLayout.SOUTH);

        panelAux.add(panelAux3,BorderLayout.CENTER);

        panelInfo.setLayout(new BorderLayout());
        panelInfo.add(Info_partida, BorderLayout.NORTH);
        panelInfo.add(textoInfo1 , BorderLayout.CENTER);
        panelInfo.add(textoInfo2 , BorderLayout.SOUTH);
        textoInfo1.setEditable(false);
        textoInfo2.setEditable(false);
        panelAux.add(panelInfo , BorderLayout.SOUTH);

        panelBotones.add(panelAux);
        panelPrincipal.add(panelBotones);

        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(tablero,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.EAST);
    }

    private void recargar_comboBox_tableros() {
        int size = selector_partida.getItemCount();
        for(int i=size-1; i >= 1;i--){
            selector_partida.removeItemAt(i);
        }
        int id = iCtrlPresentacion.presentacion_get_id_usuario();
        String nick = iCtrlPresentacion.presentacion_get_nickname_usuario();
        ArrayList<String>partidas_disponibles = iCtrlPresentacion.presentacion_buscar_partidas(id , nick);
        for (String partidas_disponible :partidas_disponibles) {
            selector_partida.addItem(partidas_disponible);
        }

        selector_partida.setSelectedIndex(0);
        id_partida_seleccionado = -1;
    }

    private void cambiar_imagen_casilla(int x, int y, int tipo) {
        String s = "";
        switch (tipo){
            case 0:
                s = imagen_vacia;
                break;
            case 1:
                s = imagen_disponible;
                break;
            case 2:
                s = imagen_negra;
                break;
            case 3:
                s = imagen_blanca;
                break;
        }
        botonesMatriz[x][y].setIcon(new ImageIcon(s));
    }


    private void limpiar_vista_previa_tablero() {
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < 8; ++j) {
                cambiar_imagen_casilla(i,j,0);
            }
        }
    }

    private void obtener_info_selector_partida() {
        String s = Objects.requireNonNull(selector_partida.getSelectedItem()).toString();
        id_partida_seleccionado = -1;
        try {
            if (!s.equals("")) id_partida_seleccionado = Integer.parseInt(s);
            else limpiar_vista_previa_tablero();
        }
        catch (Exception ignored) {}
    }

    private void listener_selector_partida() {
        obtener_info_selector_partida();
        //limpiar_vista_previa_tablero();

        int[][] tab = iCtrlPresentacion.cargarTablero(id_partida_seleccionado);
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < 8; ++j) {
                cambiar_imagen_casilla(i,j,tab[i][j]);
            }
        }
        ArrayList<String> info = iCtrlPresentacion.getInfoPartida(id_partida_seleccionado);
       // System.out.println(info.size());
        textoInfo1.setText(info.get(0));
        textoInfo2.setText(info.get(1));

    }
    /**
     * Metodo listener del elemento boton "Borrar"
     * */
    private void listener_boton_borrar() {
        obtener_info_selector_partida();
        if (id_partida_seleccionado != -1) {
            boolean b = iCtrlPresentacion.borrar_tablero(id_partida_seleccionado);
            if (b) {
                selector_partida.removeItem(selector_partida.getItemAt(id_partida_seleccionado));
                recargar_comboBox_tableros();
                limpiar_vista_previa_tablero();
            }
        }
    }

    private void listener_boton_cargar() {
        System.out.println("cargando");
        obtener_info_selector_partida();
        iCtrlPresentacion.modificar_idTablero_cargar(id_partida_seleccionado);
        int[][] mapa_tablero = iCtrlPresentacion.cargarTablero(id_partida_seleccionado);
        iCtrlPresentacion.hacerVisibleVista(vistaActiva.CONFIGPARTIDA);
    }

    public void asignar_listenersComponentes() {
        selector_partida.addActionListener
                (event -> listener_selector_partida());

        buttonLimpiar.addActionListener
                (event -> limpiar_vista_previa_tablero());

        buttonCargar.addActionListener
                (event -> listener_boton_cargar());

        buttonBorrar.addActionListener
                (event -> listener_boton_borrar());

        buttonMenu.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

        menuitemMenu.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());
    }

}
