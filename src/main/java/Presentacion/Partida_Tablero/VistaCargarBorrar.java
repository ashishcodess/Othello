package Presentacion.Partida_Tablero;

import ControladorPersistencia.CtrlPersitencia;
import Presentacion.CtrlPresentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta Vista es la encargada de Carga y Borrar tanto los tableros como las partidas
 * */
public class VistaCargarBorrar {

    private CtrlPresentacion.tipoTablero tipoActual;

    private String imagen_vacia = "";
    private String imagen_disponible = "";
    private String imagen_blanca = "";
    private String imagen_negra = "";

    private int id_tablero_seleccionado = -1;
    private int id_partida_seleccionado = -1;

    private final CtrlPresentacion iCtrlPresentacion;
    private final JPanel panelPrincipal = new JPanel();
    private JFrame frameVista = new JFrame("Vista Cargar/Borrar");
    private final JButton[][] botonesMatriz = new JButton[8][8];

    private final JPanel panelBotones = new JPanel();
    private final JButton buttonCargar = new JButton("Cargar Tablero");
    private final JButton buttonBorrar = new JButton("Borrar Tablero");
    private final JButton buttonLimpiar = new JButton("Limpiar");
    private final JButton buttonMenu = new JButton("Menu Principal");
    private final JComboBox<String> selector = new JComboBox<>();



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
     * @param pCtrlPresentacion controlador de presentacion a asignarle a dicha vista
     * */
    public VistaCargarBorrar(CtrlPresentacion pCtrlPresentacion)  {
        iCtrlPresentacion = pCtrlPresentacion;
        tipoActual = CtrlPresentacion.tipoTablero.TABLERO;
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
     * @param t tipoTablero a asignar y modificar (para visualizacion de JLabels)
     * */
    public void hacerVisible(boolean b, CtrlPresentacion.tipoTablero t) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
        if (b) {
            tipoActual = t;
            cambiar_info_labels_botones(tipoActual);
            switch (tipoActual) {
                case TABLERO:
                    buttonCargar.setEnabled(iCtrlPresentacion.consultar_idTablero_cargar() == -1);
                    break;
                case PARTIDA:
                    buttonCargar.setEnabled(true);
                    break;
            }

            limpiar_vista_previa_tablero();
            recargar_comboBox();
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

        selector.insertItemAt("", 0);
        recargar_comboBox();

        JPanel panelAux2 = new JPanel();
        panelAux2.setLayout(new FlowLayout());
        panelAux2.add(selector);
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
     * Este metodo es el encargado de modificar los labels y el nombre de los botones en funcion de tipoTablero
     * @param t tipo de Tablero [PARTIDA o TABLERO]
     * */
    private void cambiar_info_labels_botones(CtrlPresentacion.tipoTablero t) {
        infoJ1.setText(" ");
        infoJ2.setText(" ");
        infoModoJuego.setText(" ");
        infoReglas.setText(" ");
        infoTurno.setText(" ");
        switch (t) {
            case PARTIDA:
                buttonCargar.setText("Cargar Partida");
                buttonBorrar.setText("Borrar Partida");

                break;
            case TABLERO:
                buttonCargar.setText("Cargar Tablero");
                buttonBorrar.setText("Borrar Tablero");
                break;
        }
    }

    /**
     * Metodo recargar combobox tablero: actualiza las opciones disponibles del ComboBox
     * Se usa tanto para tableros como para las partidas del jugador que ha hecho login
     * */
    private void recargar_comboBox() {
        int size;
        switch (tipoActual) {
            case TABLERO:
                size = selector.getItemCount();
                for(int i=size-1; i >= 1;i--){
                    selector.removeItemAt(i);
                }
                ArrayList<String> tableros_disponibles = iCtrlPresentacion.obtener_lista_tableros_disponibles();
                if (tableros_disponibles.size() != 0) {
                    for (String tableros_disponible : tableros_disponibles) {
                        selector.addItem(tableros_disponible);
                    }
                }
                selector.setSelectedIndex(0);
                id_tablero_seleccionado = -1;
                break;

            case PARTIDA:
                id_partida_seleccionado = -1;
                size = selector.getItemCount();
                for(int i=size-1; i >= 1;i--){
                    selector.removeItemAt(i);
                }
                int idAux = iCtrlPresentacion.consultar_id_j1();
                String nickAux = iCtrlPresentacion.consultar_nickname_j1();
                ArrayList<String> partidas_guardadas = iCtrlPresentacion.presentacion_buscar_partidas(idAux,nickAux);
                if (partidas_guardadas.size() != 0) {
                    for (String p : partidas_guardadas) {
                        selector.addItem(p);
                    }
                }
                selector.setSelectedIndex(0);
                break;
        }
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
     * Metodo Obtener info del elemento seleccionado por el selector (para TABLEROS y PARTIDAS)
     * */
    private void obtener_info_selector() {
        String s = Objects.requireNonNull(selector.getSelectedItem()).toString();
        switch (tipoActual) {
            case TABLERO:
                id_tablero_seleccionado = -1;
                try {
                    if (!s.equals("")) id_tablero_seleccionado = Integer.parseInt(s);
                    else limpiar_vista_previa_tablero();
                }
                catch (Exception ignored) {}
                break;

            case PARTIDA:
                id_partida_seleccionado = -1;
                try {
                    if (!s.equals("")) id_partida_seleccionado = Integer.parseInt(s);
                    else limpiar_vista_previa_tablero();
                }
                catch (Exception ignored) {}
                break;
        }

    }


    /**
     * Metodo listener del elemento comboBox "selector"
     * */
    private void listener_selector_tablero() {
        obtener_info_selector();
        int[][] tab = new int[8][8];
        switch (tipoActual) {
            case TABLERO:
                tab = iCtrlPresentacion.presentacion_cargarTablero(id_tablero_seleccionado);
                break;

            case PARTIDA:
                obtener_info_selector();
                if (id_partida_seleccionado >= 0) {
                    ArrayList<String> as = iCtrlPresentacion.consultar_info_partida_ID(id_partida_seleccionado);
                    tab = iCtrlPresentacion.presentacion_cargar_tablero_partida(id_partida_seleccionado);
                    //tenemos info de toda la partida, ahora hace falta mostrarla
                    infoJ1.setText("J1: " + as.get(0));
                    infoJ2.setText("J2: " + as.get(1));
                    infoReglas.setText("Reglas: "+ as.get(3));
                    infoTurno.setText("Turno: " + as.get(4));
                    String sAux = as.get(2); //modo juego
                    String s = "";
                    switch (sAux) {
                        case "0":
                            s = "IA vs IA";
                            break;
                        case "1":
                            s = "Persona vs IA";
                            break;
                        case "2":
                            s = "Persona vs Persona";
                            break;
                    }
                    infoModoJuego.setText(s);

                }

                break;
        }
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
        obtener_info_selector();
        switch (tipoActual) {
            case TABLERO:
                if (id_tablero_seleccionado != -1) {
                    boolean b = iCtrlPresentacion.presentacion_borrar_tablero(id_tablero_seleccionado);
                    if (b) {
                        selector.removeItem(selector.getItemAt(id_tablero_seleccionado));
                        recargar_comboBox();
                        limpiar_vista_previa_tablero();
                    }
                }
                break;

            case PARTIDA:
                if (id_partida_seleccionado >= 0) {
                    //borrar partida
                    boolean b = iCtrlPresentacion.presentacion_borrarPartida(id_partida_seleccionado);
                    if (b) {
                        selector.removeItem(selector.getItemAt(id_partida_seleccionado));
                        recargar_comboBox();
                        limpiar_vista_previa_tablero();
                    }
                }
                break;
        }

    }

    /**
     * Metodo listener del elemento boton "Cargar"
     * */
    private void listener_boton_cargar() {
        obtener_info_selector();
        switch (tipoActual) {
            case TABLERO:
                iCtrlPresentacion.modificar_idTablero_cargar(id_tablero_seleccionado);
                iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.CONFIGPARTIDA);
                break;
            case PARTIDA:
                if (id_partida_seleccionado >= 0) {
                    iCtrlPresentacion.presentacion_cargarPartida(id_partida_seleccionado);
                    iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.TABLERO);
                }
                break;
        }
    }

    /**
     * Metodo para asignar los listeners a cada componente
     * */
    public void asignar_listenersComponentes() {
        selector.addActionListener
                (event -> listener_selector_tablero());

        buttonLimpiar.addActionListener
                (event -> limpiar_vista_previa_tablero());

        buttonCargar.addActionListener
                (event -> listener_boton_cargar());

        buttonBorrar.addActionListener
                (event -> listener_boton_borrar());

        buttonMenu.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.MENU));

        menuitemMenu.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(CtrlPresentacion.vistaActiva.MENU));

        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());
    }

}
