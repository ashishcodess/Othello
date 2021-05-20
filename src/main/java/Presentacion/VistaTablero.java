package Presentacion;

import ControladorPersistencia.CtrlPersitencia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;


public class VistaTablero {

    public enum tipoTablero {PARTIDA, TABLERO}

    private tipoTablero tipoActual;

    private final CtrlPresentacion iCtrlPresentacion;
    private JFrame frameVista = new JFrame("Vista Tablero");
    private final JButton[][] botonesMatriz = new JButton[8][8];
    private final JPanel panelPrincipal = new JPanel();

    private String imagen_vacia = "";
    private String imagen_disponible = "";
    private String imagen_blanca = "";
    private String imagen_negra = "";

    private final JPanel panelBotones = new JPanel();
    private final JButton bottonPasarTurno = new JButton("Pasar turno");
    private final JLabel labelSeparador = new JLabel("         ");
    private final JLabel labelOpciones = new JLabel("Opciones de partida");
    private final JButton bottonGuardar = new JButton("Guardar Partida");
    private final JButton bottonFinalizar = new JButton("Finalizar Partida");


    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir del Juego");
    private final JMenuItem menuitemGuardar= new JMenuItem("Guardar Partida");
    private final JMenuItem menuitemFinalizar = new JMenuItem("Finalizar Partida (Volver al menu Principal)");

    private void cambiar_info_labels_botones(tipoTablero t) {
        menuitemFinalizar.setText("Finalizar (Volver al menu Principal)");
        switch (t) {
            case PARTIDA:
                labelOpciones.setText("Opciones de partida");
                bottonGuardar.setText("Guardar Partida");
                bottonFinalizar.setText("Finalizar Partida");
                menuitemGuardar.setText("Guardar Partida");
                break;
            case TABLERO:
                bottonPasarTurno.setEnabled(false);
                labelOpciones.setText("Opciones de Tablero");
                bottonGuardar.setText("Guardar Tablero");
                bottonFinalizar.setText("Finalizar (Salir sin guardar)");
                menuitemGuardar.setText("Guardar Tablero");
                break;
        }
    }

    public VistaTablero(CtrlPresentacion pCtrlPresentacion)  {
        iCtrlPresentacion = pCtrlPresentacion;
        tipoActual = tipoTablero.PARTIDA;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializar_frameVista();
        obtener_dir_imagenes();
        inicializar_Componentes();
        asignar_listenersComponentes();
        inicializar_menubarVista();
    }

    private void inicializar_menubarVista() {
        menuFile.add(menuitemGuardar);
        menuFile.add(menuitemFinalizar);
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        frameVista.setJMenuBar(menubarVista);
    }

    private void inicializar_frameVista() {
        frameVista = iCtrlPresentacion.configuracion_frame(950,850,"Tablero");
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    private void obtener_dir_imagenes() {
        imagen_vacia = iCtrlPresentacion.presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG.IMG_VACIA);
        imagen_disponible = iCtrlPresentacion.presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG.IMG_DISPONIBLE);
        imagen_blanca = iCtrlPresentacion.presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG.IMG_BLANCA);
        imagen_negra = iCtrlPresentacion.presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG.IMG_NEGRA);
    }

    public void hacerVisible(boolean b,tipoTablero t) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
        if (b) {
            tipoActual = t;
            cambiar_info_labels_botones(tipoActual);
            recargar_tablero();
        }
    }



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
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.PAGE_AXIS));
        panelBotones.add(bottonPasarTurno);
        panelBotones.add(labelSeparador);
        panelBotones.add(labelOpciones);
        panelBotones.add(bottonGuardar);
        panelBotones.add(bottonFinalizar);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(tablero,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.EAST);
        recargar_tablero();
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
     * Metodo recargar tablero (actualiza la pantalla con la informacion del tablero actualizado)*/
    public void recargar_tablero() {
        int[][] tab = iCtrlPresentacion.presentacionObtenerTablero();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                cambiar_imagen_casilla(i,j,tab[i][j]);
            }
        }
    }

    private void listener_guardar_partida() {
        switch (tipoActual) {
            case PARTIDA:
                iCtrlPresentacion.presentacion_guardar_partida();
                break;
            case TABLERO:
                iCtrlPresentacion.presentacion_guardar_tablero();
                break;
        }
        iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU);
    }

    public void actionPerformed_botones(ActionEvent event) {
        int x = 0;
        for (JButton[] jButtons : botonesMatriz) {
            x+=1;
            int y = 0;
            for (JButton jButton : jButtons) {
                y+=1;
                if (event.getSource() == jButton) {
                    x = x-1;
                    y = y-1;
                    System.out.println("posicion "+ x + ", " + y);
                    iCtrlPresentacion.presentacionRondaPartida(x, y);

                }
            }
        }
        recargar_tablero();
    }

    public void asignar_listenersComponentes() {
        //TABLERO
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                botonesMatriz[i][j].addActionListener
                        (this::actionPerformed_botones);
            }
        }


        //LISTENERS DE BOTONES Y BARRA DE MENU SUPERIOS
        bottonGuardar.addActionListener
                (event -> listener_guardar_partida());

        bottonFinalizar.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

        menuitemGuardar.addActionListener
                (event -> listener_guardar_partida());

        menuitemFinalizar.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

        menuitemQuit.addActionListener
                (event -> iCtrlPresentacion.salir_del_juego());

    }
}