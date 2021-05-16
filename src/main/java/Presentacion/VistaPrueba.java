package Presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

//PUEDE SERVIR DE AYUDA PARA QUE QUEDE MEJOR EL TABLERO -> SERGIO

public class VistaPrueba {

    private final CtrlPresentacion iCtrlPresentacion;
    private final JPanel panelPrincipal = new JPanel();

    private String imagen_vacia = "./src/files/fichas/vacia.png";
    private String imagen_disponible = "./src/files/fichas/disponible.png";
    private String imagen_blanca = "./src/files/fichas/blanca.png";
    private String imagen_negra = "./src/files/fichas/negra.png";

    private JButton bPrueba1 = new JButton("Pasar turno");
    private JButton bPrueba2 = new JButton("prueba2");
    private JPanel panelBotones = new JPanel();
    private JTextArea textPrueba = new JTextArea();

    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir");

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

    private final JFrame frameVista = new JFrame("Prueba Tablero");
    private JButton[][] botonesMatriz = new JButton[8][8];
    private JPanel tablero;

    private static final String columnas = "ABCDEFGH";

    public VistaPrueba(CtrlPresentacion pCtrlPresentacion)  {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializar_frameVista();
        inicializar_Componentes();
        asignar_listenersComponentes();
        inicializar_menubarVista();
    }

    private void inicializar_menubarVista() {
        menubarVista.add(menuFile);
        menuFile.add(menuitemQuit);
        frameVista.setJMenuBar(menubarVista);
    }

    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(950,850));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(true);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    private void inicializar_Componentes() {
        tablero = new JPanel(new GridLayout(0,8));
        tablero.setBorder((new LineBorder(Color.BLACK)));

        Insets margenesBotones = new Insets(0,0,0,0);
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < botonesMatriz[i].length; ++j) {
                JButton b = new JButton();
                b.setMargin(margenesBotones);
                ImageIcon icono = new ImageIcon(imagen_vacia);
                b.setIcon(icono);
                botonesMatriz[i][j] = b;
                tablero.add(botonesMatriz[i][j]);
            }
        }

        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(bPrueba1);
        panelBotones.add(bPrueba2);

        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(tablero,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones,BorderLayout.EAST);
    }

    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                        ctrlPresentacion.inicializarPresentacion();
                        ctrlPresentacion.hacerVisibleVista(vistaActiva.PRUEBA);
                    }});
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
