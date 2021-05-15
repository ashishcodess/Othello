package Presentacion;

import javax.swing.*;
import java.awt.*;


public class VistaTablero extends JFrame {
    private final CtrlPresentacion iCtrlPresentacion;
    private final JFrame frameVista = new JFrame("VistaTablero");
    private final JPanel panelTablero = new JPanel();
    //Tablero
    ImageIcon fichaBlanca = new ImageIcon("blanca.png");
    ImageIcon fichaNegra = new ImageIcon("negra.png");
    private final JButton button00 = new JButton();
    private final JButton button01 = new JButton();
    private final JButton button02 = new JButton();
    private final JButton button03 = new JButton();
    private final JButton button04 = new JButton();
    private final JButton button05 = new JButton();
    private final JButton button06 = new JButton();
    private final JButton button07 = new JButton();
    private final JButton button10 = new JButton();
    private final JButton button11 = new JButton();
    private final JButton button12 = new JButton();
    private final JButton button13 = new JButton();
    private final JButton button14 = new JButton();
    private final JButton button15 = new JButton();
    private final JButton button16 = new JButton();
    private final JButton button17 = new JButton();
    private final JButton button20 = new JButton();
    private final JButton button21 = new JButton();
    private final JButton button22 = new JButton();
    private final JButton button23 = new JButton();
    private final JButton button24 = new JButton();
    private final JButton button25 = new JButton();
    private final JButton button26 = new JButton();
    private final JButton button27 = new JButton();
    private final JButton button30 = new JButton();
    private final JButton button31 = new JButton();
    private final JButton button32 = new JButton();
    private final JButton button33 = new JButton(fichaBlanca);
    private final JButton button34 = new JButton(fichaNegra);
    private final JButton button35 = new JButton();
    private final JButton button36 = new JButton();
    private final JButton button37 = new JButton();
    private final JButton button40 = new JButton();
    private final JButton button41 = new JButton();
    private final JButton button42 = new JButton();
    private final JButton button43 = new JButton(fichaNegra);
    private final JButton button44 = new JButton(fichaBlanca);
    private final JButton button45 = new JButton();
    private final JButton button46 = new JButton();
    private final JButton button47 = new JButton();
    private final JButton button50 = new JButton();
    private final JButton button51 = new JButton();
    private final JButton button52 = new JButton();
    private final JButton button53 = new JButton();
    private final JButton button54 = new JButton();
    private final JButton button55 = new JButton();
    private final JButton button56 = new JButton();
    private final JButton button57 = new JButton();
    private final JButton button60 = new JButton();
    private final JButton button61 = new JButton();
    private final JButton button62 = new JButton();
    private final JButton button63 = new JButton();
    private final JButton button64 = new JButton();
    private final JButton button65 = new JButton();
    private final JButton button67 = new JButton();
    private final JButton button66 = new JButton();
    private final JButton button70 = new JButton();
    private final JButton button71 = new JButton();
    private final JButton button72 = new JButton();
    private final JButton button73 = new JButton();
    private final JButton button74 = new JButton();
    private final JButton button75 = new JButton();
    private final JButton button76 = new JButton();
    private final JButton button77 = new JButton();


    public VistaTablero(CtrlPresentacion CtrlPresentacion) {
        iCtrlPresentacion = CtrlPresentacion;
        frameVista.setLayout(new BorderLayout());
        inicializarTablero();
        inicializarComponentes();
        //frameVista.setContentPane(new VistaTablero(iCtrlPresentacion).panelTablero);

    }

    private void inicializarComponentes() {
        inicializarFrameVista();
        //asignar_listenersComponentes();
    }

    private void inicializarFrameVista() {
        frameVista.setMinimumSize(new Dimension(800,800));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelTablero);
    }

    private void inicializarTablero() {
        panelTablero.add(button00);
        panelTablero.add(button01);
        panelTablero.add(button02);
        panelTablero.add(button03);
        panelTablero.add(button04);
        panelTablero.add(button05);
        panelTablero.add(button06);
        panelTablero.add(button07);

        panelTablero.add(button10);
        panelTablero.add(button11);
        panelTablero.add(button12);
        panelTablero.add(button13);
        panelTablero.add(button14);
        panelTablero.add(button15);
        panelTablero.add(button16);
        panelTablero.add(button17);

        panelTablero.add(button20);
        panelTablero.add(button21);
        panelTablero.add(button22);
        panelTablero.add(button23);
        panelTablero.add(button24);
        panelTablero.add(button25);
        panelTablero.add(button26);
        panelTablero.add(button27);

        panelTablero.add(button30);
        panelTablero.add(button31);
        panelTablero.add(button32);
        panelTablero.add(button33);
        panelTablero.add(button34);
        panelTablero.add(button35);
        panelTablero.add(button36);
        panelTablero.add(button37);

        panelTablero.add(button40);
        panelTablero.add(button41);
        panelTablero.add(button42);
        panelTablero.add(button43);
        panelTablero.add(button44);
        panelTablero.add(button45);
        panelTablero.add(button46);
        panelTablero.add(button47);

        panelTablero.add(button50);
        panelTablero.add(button51);
        panelTablero.add(button52);
        panelTablero.add(button53);
        panelTablero.add(button54);
        panelTablero.add(button55);
        panelTablero.add(button56);
        panelTablero.add(button57);

        panelTablero.add(button60);
        panelTablero.add(button61);
        panelTablero.add(button62);
        panelTablero.add(button63);
        panelTablero.add(button64);
        panelTablero.add(button65);
        panelTablero.add(button66);
        panelTablero.add(button67);

        panelTablero.add(button70);
        panelTablero.add(button71);
        panelTablero.add(button72);
        panelTablero.add(button73);
        panelTablero.add(button74);
        panelTablero.add(button75);
        panelTablero.add(button76);
        panelTablero.add(button77);

        /*
        ImageIcon fichaBlanca = new ImageIcon("blanca.png");
        ImageIcon fichaNegra = new ImageIcon("negra.png");
        inicializarFichasIni(fichaBlanca, fichaNegra);

        JButton button35 = new JButton();
        JButton button36 = new JButton();
        JButton button37 = new JButton();
        JButton button40 = new JButton();
        JButton button41 = new JButton();
        JButton button42 = new JButton();
        JButton button45 = new JButton();
        JButton button46 = new JButton();
        JButton button47 = new JButton();
        JButton button50 = new JButton();
        JButton button51 = new JButton();
        JButton button52 = new JButton();
        JButton button53 = new JButton();
        JButton button54 = new JButton();
        JButton button55 = new JButton();
        JButton button56 = new JButton();
        JButton button57 = new JButton();
        JButton button60 = new JButton();
        JButton button61 = new JButton();
        JButton button62 = new JButton();
        JButton button63 = new JButton();
        JButton button64 = new JButton();
        JButton button65 = new JButton();
        JButton button67 = new JButton();
        JButton button66 = new JButton();
        JButton button70 = new JButton();
        JButton button71 = new JButton();
        JButton button72 = new JButton();
        JButton button73 = new JButton();
        JButton button74 = new JButton();
        JButton button75 = new JButton();
        JButton button76 = new JButton();
        JButton button77 = new JButton();
        */

    }

    private void inicializarFichasIni(ImageIcon fichaBlanca, ImageIcon fichaNegra) {
        JButton button33 = new JButton(fichaBlanca);
        JButton button34 = new JButton(fichaNegra);

    }
    /*
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = VistaTablero.class.getResource(path);
    //error handling omitted for clarity...
        return new ImageIcon(imgURL);
    }*/

    /**
     * Metodo hacerVisible
     *
     * @param b si TRUE entonces el frame sera visible, caso contrario estara desactivado
     */
    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


