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
    }

    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(1000,1000));
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

        Insets margenesBotones = new Insets(0,0,0,0);
        for (int i = 0; i < botonesMatriz.length; ++i) {
            for (int j = 0; j < botonesMatriz[i].length; ++j) {
                JButton b = new JButton();
                b.setMargin(margenesBotones);
                ImageIcon icono = new ImageIcon(imagen_disponible);
                b.setIcon(icono);
                botonesMatriz[i][j] = b;
                tablero.add(botonesMatriz[i][j]);
            }
        }

        panelPrincipal.setLayout(new FlowLayout());
        panelPrincipal.add(tablero);

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

    boolean pulsado = true;

    //NO funciona pero entiendo que esa es la idea
    public void actionPerformed_button0(ActionEvent event) {
        /*JButton b = new JButton();
        Insets margenesBotones = new Insets(0,0,0,0);
        b.setMargin(margenesBotones);
        ImageIcon icono = new ImageIcon(getImagen());
        b.setIcon(icono);
        botonesMatriz[0][0] = b;*/



    }


    private void asignar_listenersComponentes() {
        botonesMatriz[0][0].addActionListener
                (this::actionPerformed_button0);
    }
}
