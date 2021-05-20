package Presentacion;

import ControladorPersistencia.CtrlPersitencia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class VistaCargarTablero {

    public enum tipoTablero {TABLERO, PARTIDA}
    private tipoTablero tipoActual;

    private String imagen_vacia = "";
    private String imagen_disponible = "";
    private String imagen_blanca = "";
    private String imagen_negra = "";

    private int id_tablero_seleccionado = -1;

    private final CtrlPresentacion iCtrlPresentacion;
    private final JPanel panelPrincipal = new JPanel();
    private JFrame frameVista = new JFrame("Vista Cargar/Borrar");
    private final JButton[][] botonesMatriz = new JButton[8][8];

    private final JPanel panelBotones = new JPanel();
    private final JButton buttonCargar = new JButton("Cargar Tablero");
    private final JButton buttonBorrar = new JButton("Borrar Tablero");
    private final JButton buttonLimpiar = new JButton("Limpiar");
    private final JButton buttonMenu = new JButton("Menu Principal");
    private final JComboBox<String> selector_tablero = new JComboBox<>();

    //Info de partida
    private final JLabel infoJ1 = new JLabel("Info J1: ");
    private final JLabel infoJ2 = new JLabel("Info J2: ");
    private final JLabel infoModoJuego = new JLabel("Modo juego: ");
    private final JLabel infoReglas = new JLabel("Reglas: 1 1 1");
    private final JLabel infoTurno = new JLabel("Turno: 0");


    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemMenu = new JMenuItem("Volver al Menu Principal");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir del Juego");

    /**
     * Constructora de Vista Cargar/Borrar Tablero
     * */
    public VistaCargarTablero(CtrlPresentacion pCtrlPresentacion)  {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializar_frameVista();
        obtener_dir_imagenes();
        inicializar_Componentes();
        asignar_listenersComponentes();
        inicializar_menubarVista();
    }

    /**
     *Metodo hacerVisible
     * @param b si TRUE entonces el frame sera visible, caso contrario estara desactivado
     * */
    public void hacerVisible(boolean b, tipoTablero t) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
        if (b) {
            cambiar_info_labels_botones(t);

            //FALTA COMBOBOX PARA PARTIDAS + DIFERENCIAR LISTENERS
            buttonCargar.setEnabled(iCtrlPresentacion.consultar_idTablero_cargar() == -1);
            limpiar_vista_previa_tablero();
            recargar_comboBox_tableros();
        }
    }

    private void cambiar_info_labels_botones(VistaCargarTablero.tipoTablero t) {
        switch (t) {
            case PARTIDA:
                buttonCargar.setText("Cargar Partida");
                buttonBorrar.setText("Borrar Partida");
                infoJ1.setText("J1:");
                infoJ2.setText("J2:");
                infoModoJuego.setText("Modo de juego: ");
                infoReglas.setText("Reglas: ");
                infoTurno.setText("Turno: ");
                break;
            case TABLERO:
                buttonCargar.setText("Cargar Tablero");
                buttonBorrar.setText("Borrar Tablero");
                infoJ1.setText(" ");
                infoJ2.setText(" ");
                infoModoJuego.setText(" ");
                infoReglas.setText(" ");
                infoTurno.setText(" ");
                break;
        }
    }

    /**
     * Metodo para inicializar frame
     * */
    private void inicializar_frameVista() {
        frameVista = iCtrlPresentacion.configuracion_frame(950,850,"Vista Cargar/Borrar");
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
        selector_tablero.insertItemAt("", 0);
        recargar_comboBox_tableros();

        JPanel panelAux2 = new JPanel();
        panelAux2.setLayout(new FlowLayout());
        panelAux2.add(selector_tablero);
        panelAux2.add(buttonLimpiar);
        panelAux2.add(buttonMenu);

        panelAux.add(panelAux2,BorderLayout.NORTH);
        panelAux.add(buttonCargar,BorderLayout.CENTER);
        panelAux.add(buttonBorrar,BorderLayout.SOUTH);

        JPanel panelAux3 = new JPanel();
        panelAux3.setLayout(new BoxLayout(panelAux3,BoxLayout.Y_AXIS));
        panelAux3.add(infoJ1);
        panelAux3.add(infoJ2);
        panelAux3.add(infoModoJuego);
        panelAux3.add(infoReglas);
        panelAux3.add(infoTurno);

        JPanel panelAux4 = new JPanel();
        panelAux4.setLayout(new BoxLayout(panelAux4,BoxLayout.Y_AXIS));
        panelAux4.add(panelAux);
        panelAux4.add(panelAux3);
        panelBotones.add(panelAux4);
        panelPrincipal.add(panelBotones);

        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(tablero,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.EAST);
    }

    /**
     * Metodo recargar combobox tablero: actualiza las opciones disponibles del ComboBox
     * */
    private void recargar_comboBox_tableros() {
        int size = selector_tablero.getItemCount();
        for(int i=size-1; i >= 1;i--){
            selector_tablero.removeItemAt(i);
        }
        ArrayList<String> tableros_disponibles = iCtrlPresentacion.obtener_lista_tableros_disponibles();
        for (String tableros_disponible : tableros_disponibles) {
            selector_tablero.addItem(tableros_disponible);
        }
        selector_tablero.setSelectedIndex(0);
        id_tablero_seleccionado = -1;
    }

    /**
     * Metodo cambiar imagen casilla
     * @param x posicion x dentro del tablero
     * @param y posicion y dentro del tablero
     * @param tipo (tipo de imagen a cargar [0:vacia, 1:disponible, 2:Negra, 3:Blanca]
     * */
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


    /**
     * Metodo limpiar vista previa tablero: hace un clear de la pantalla de vista previa
     * */
    private void limpiar_vista_previa_tablero() {
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < 8; ++j) {
                cambiar_imagen_casilla(i,j,0);
            }
        }
    }

    /**
     * Metodo Obtener info del elemento seleccionado por el selector_tablero
     * */
    private void obtener_info_selector_tablero() {
        String s = Objects.requireNonNull(selector_tablero.getSelectedItem()).toString();
        id_tablero_seleccionado = -1;
        try {
            if (!s.equals("")) id_tablero_seleccionado = Integer.parseInt(s);
            else limpiar_vista_previa_tablero();
        }
        catch (Exception ignored) {}
    }


    /**
     * Metodo listener del elemento comboBox "selector_tablero"
     * */
    private void listener_selector_tablero() {
        obtener_info_selector_tablero();
        //limpiar_vista_previa_tablero();
        int[][] tab = iCtrlPresentacion.cargarTablero(id_tablero_seleccionado);
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < 8; ++j) {
                cambiar_imagen_casilla(i,j,tab[i][j]);
            }
        }
    }

    /**
     * Metodo listener del elemento boton "Borrar"
     * */
    private void listener_boton_borrar() {
        switch (tipoActual) {
            case TABLERO:
                if (id_tablero_seleccionado != -1) {
                    boolean b = iCtrlPresentacion.borrar_tablero(id_tablero_seleccionado);
                    if (b) {
                        selector_tablero.removeItem(selector_tablero.getItemAt(id_tablero_seleccionado));
                        recargar_comboBox_tableros();
                        limpiar_vista_previa_tablero();
                    }
                }
                break;

            case PARTIDA:
                //falta esto + combobox
                break;

        }
        obtener_info_selector_tablero();

    }

    /**
     * Metodo listener del elemento boton "Cargar"
     * */
    private void listener_boton_cargar() {
        switch (tipoActual) {
            case TABLERO:
                obtener_info_selector_tablero();
                iCtrlPresentacion.modificar_idTablero_cargar(id_tablero_seleccionado);
                int[][] mapa_tablero = iCtrlPresentacion.cargarTablero(id_tablero_seleccionado);
                iCtrlPresentacion.hacerVisibleVista(vistaActiva.CONFIGPARTIDA);
                break;
            case PARTIDA:
                //falta esto + combobox
                break;
        }


    }

    /**
     * Metodo para asignar los listeners a cada componente
     * */
    public void asignar_listenersComponentes() {
        selector_tablero.addActionListener
                (event -> listener_selector_tablero());

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
