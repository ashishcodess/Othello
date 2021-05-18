package Presentacion;

import ControladorPersistencia.CtrlPersitencia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


//IDEA HACER CLASE EXTENDIDA DE ESTO (SIN LOS BOTONES

public class VistaTablero {

    private final CtrlPresentacion iCtrlPresentacion;
    private final JPanel panelPrincipal = new JPanel();

    private String imagen_vacia = "";
    private String imagen_disponible = "";
    private String imagen_blanca = "";
    private String imagen_negra = "";


    private final JPanel panelBotones = new JPanel();
    private final JButton bottonPasarTurno = new JButton("Pasar turno");
    private final JLabel labelSeparador = new JLabel("         ");
    private final JLabel labelOpcionesPartida = new JLabel("Opciones de partida");
    private final JButton bottonGuardarPartida = new JButton("Guardar Partida");
    private final JButton bottonFinalizarPartida = new JButton("Finalizar Partida");



    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir");

    private final JMenu menuPartida = new JMenu("Partida");
    private final JMenuItem menuitemGuardarPartida = new JMenuItem("Guardar Partida");
    private final JMenuItem menuitemFinalizarPartida = new JMenuItem("Finalizar Partida");



    private int prueba_switch_imagen = 0;

    private String getImagen() {
        ++prueba_switch_imagen;
        prueba_switch_imagen = prueba_switch_imagen%4;
        String img = imagen_vacia;
        switch (prueba_switch_imagen) {
            case 0:
                img = imagen_disponible;
                break;
            case 1:
                img = imagen_blanca;
                break;
            case 2:
                img = imagen_negra;
                break;
            case 3:
                img = imagen_vacia;
                break;
        }
        return img;
    }

    private JFrame frameVista = new JFrame("Vista Tablero");
    private final JButton[][] botonesMatriz = new JButton[8][8];


    public VistaTablero(CtrlPresentacion pCtrlPresentacion)  {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializar_frameVista();
        obtener_dir_imagenes();
        inicializar_Componentes();
        asignar_listenersComponentes();
        inicializar_menubarVista();
    }

    private void inicializar_menubarVista() {
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        menuPartida.add(menuitemGuardarPartida);
        menuPartida.add(menuitemFinalizarPartida);
        menubarVista.add(menuPartida);
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

    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }

    private void inicializar_Componentes() {
        JPanel tablero = new JPanel(new GridLayout(0, 8));
        tablero.setBorder((new LineBorder(Color.BLACK)));

        //int[][] tableroPartida = iCtrlPresentacion.presentacionObtenerTablero();

        Insets margenesBotones = new Insets(0,0,0,0);
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < botonesMatriz[i].length; ++j) {
                JButton b = new JButton();
                b.setMargin(margenesBotones);
                b.setBackground(Color.gray);
                if (i == 3 && j == 3){
                    b.setIcon(new ImageIcon(imagen_blanca));
                }
                else if (i == 4 && j == 4){
                    b.setIcon(new ImageIcon(imagen_blanca));
                }
                else if (i == 3 && j == 4){
                    b.setIcon(new ImageIcon(imagen_negra));
                }
                else if (i == 4 && j == 3){
                    b.setIcon(new ImageIcon(imagen_negra));
                }
                else {
                    b.setIcon(new ImageIcon(imagen_vacia));
                }
                botonesMatriz[i][j] = b;
                tablero.add(botonesMatriz[i][j]);
            }
        }
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.PAGE_AXIS));
        panelBotones.add(bottonPasarTurno);
        panelBotones.add(labelSeparador);
        panelBotones.add(labelOpcionesPartida);
        panelBotones.add(bottonGuardarPartida);
        panelBotones.add(bottonFinalizarPartida);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(tablero,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.EAST);
    }


    /*
    private void inicializar_Componentes() {
        JPanel tablero = new JPanel(new GridLayout(0, 8));
        tablero.setBorder((new LineBorder(Color.BLACK)));

        int[][] tableroPartida = iCtrlPresentacion.presentacionObtenerTablero();

        Insets margenesBotones = new Insets(0,0,0,0);
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < botonesMatriz[i].length; ++j) {
                JButton b = new JButton();
                b.setMargin(margenesBotones);
                b.setBackground(Color.gray);
                if (tableroPartida[i][j] == 0 ) b.setIcon(new ImageIcon(imagen_vacia));
                else if (tableroPartida[i][j] == 3 )b.setIcon(new ImageIcon(imagen_blanca));
                else if (tableroPartida[i][j] == 2 )b.setIcon(new ImageIcon(imagen_negra));
                else b.setIcon(new ImageIcon(imagen_disponible));

                botonesMatriz[i][j] = b;
                tablero.add(botonesMatriz[i][j]);
            }
        }

        panelBotones.setLayout(new FlowLayout());
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.PAGE_AXIS));
        panelBotones.add(bottonPasarTurno);
        panelBotones.add(labelSeparador);
        panelBotones.add(labelOpcionesPartida);
        panelBotones.add(bottonGuardarPartida);
        panelBotones.add(bottonFinalizarPartida);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(tablero,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.EAST);
    }

   */


    public void actionPerformed_botones(ActionEvent event) {
        for (JButton[] jButtons : botonesMatriz) {
            for (JButton jButton : jButtons) {
                if (event.getSource() == jButton) {
                    String s = getImagen();
                    jButton.setIcon(new ImageIcon(s));
                }
            }
        }
    }
/*
 Set<Position> posDisp = iCtrlPresentacion.presentacionObternerCasillasDisponibles();
 for (int i = 0; i < posDisp.size() ; ++i){
                JButton b = new JButton();
                b.setMargin(margenesBotones);
                b.setBackground(Color.gray);
                b.setIcon(new ImageIcon(imagen_disponible));
                botonesMatriz[i][j] = b;
                tablero.add(botonesMatriz[i][j]);
 }
*/

    public void asignar_listenersComponentes() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                botonesMatriz[i][j].addActionListener
                        (this::actionPerformed_botones);
            }
        }

    }
}