package Presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;



public class VistaTablero {

    private final CtrlPresentacion iCtrlPresentacion;
    private final JPanel panelPrincipal = new JPanel();

    private final String imagen_vacia = "./src/files/fichas/vacia.png";
    private final String imagen_disponible = "./src/files/fichas/disponible.png";
    private final String imagen_blanca = "./src/files/fichas/blanca.png";
    private final String imagen_negra = "./src/files/fichas/negra.png";


    private JPanel panelBotones = new JPanel();
    private JButton bottonPasarTurno = new JButton("Pasar turno");
    private JLabel labelSeparador = new JLabel("         ");
    private JLabel labelOpcionesPartida = new JLabel("Opciones de partida");
    private JButton bottonGuardarPartida = new JButton("Guardar Partida");
    private JButton bottonFinalizarPartida = new JButton("Finalizar Partida");



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

    private final JFrame frameVista = new JFrame("Vista Tablero");
    private JButton[][] botonesMatriz = new JButton[8][8];
    private JPanel tablero;


    public VistaTablero(CtrlPresentacion pCtrlPresentacion)  {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializar_frameVista();
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
        frameVista.setMinimumSize(new Dimension(950,850));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    private void inicializar_Componentes() {
        tablero = new JPanel(new GridLayout(0,8));
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

    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }


    public void actionPerformed_botones(ActionEvent event) {
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < botonesMatriz[i].length; ++j) {
                if (event.getSource() == botonesMatriz[i][j]) {
                    String s = getImagen();
                    botonesMatriz[i][j].setIcon(new ImageIcon(s));
                }
            }
        }
    }


    private void asignar_listenersComponentes() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                botonesMatriz[i][j].addActionListener
                        (this::actionPerformed_botones);
            }
        }

    }
}