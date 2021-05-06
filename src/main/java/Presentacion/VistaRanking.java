package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaRanking {

    // Controlador de presentacion
    private CtrlPresentacion iCtrlPresentacion;

    private int iPanelActivo = 0; //para cambiar entre panel Ranking y estadisticas

    // Componentes de la interficie grafica
    private JFrame frameVista = new JFrame("Vista Ranking");
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelInfo = new JPanel();
    private JPanel panelActivo = new JPanel();

    //COMPONENTES RANKING
    private JPanel panelRanking = new JPanel();
    private JLabel labelPanelRanking = new JLabel("Panel Ranking"); //borrar al final
    //private JTextArea textareaRanking = new JTextArea(15,25);
    private JTextArea textareaRanking = new JTextArea(40,55);
    private JPanel panelBotonesRanking = new JPanel();
    private JButton buttonCargarRanking = new JButton("Cargar Ranking");
    private JButton buttonLimpiarTexto= new JButton("Limpiar Texto");

    //COMPONENTES ESTADISTICAS
    private JPanel panelEstadisticas = new JPanel();
    private JLabel labelPanelEstadisticas = new JLabel("Panel Estadisticas"); //borrar al final


    //BARRA DE MENU
    private JMenuBar menubarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuitemQuit = new JMenuItem("Salir");
    private JMenu menuRanking = new JMenu("Ranking");
    private JMenuItem menuItem_consultar_ranking = new JMenuItem("Consultar Ranking");
    private JMenuItem menuItem_consultar_estadisticas = new JMenuItem("Consultar Estadisticas");



    public VistaRanking (CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializarComponentes();
    }

    public void hacerVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }

    public void activar() {
        frameVista.setEnabled(true);
    }

    public void desactivar() {
        frameVista.setEnabled(false);
    }

    /////////// INICIALIZACION DE COMPONENTES

    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_menubarVista();
        inicializar_panelPrincipal();
        inicializar_panelInfo();
        inicializar_panelBotonesRanking();
        inicializar_panelRanking();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(1400,800));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        // Posicion y operaciones por defecto
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Se agrega panelContenidos al contentPane (el panelContenidos se
        // podria ahorrar y trabajar directamente sobre el contentPane)
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    private void inicializar_menubarVista() {
        menuFile.add(menuitemQuit);
        menuRanking.add(menuItem_consultar_ranking);
        menuRanking.add(menuItem_consultar_estadisticas);
        menubarVista.add(menuFile);
        menubarVista.add(menuRanking);
        frameVista.setJMenuBar(menubarVista);
    }

    private void inicializar_panelPrincipal() {
        // Layout
        panelPrincipal.setLayout(new BorderLayout());
        // Paneles
        panelPrincipal.add(panelInfo,BorderLayout.CENTER);
    }

    private void inicializar_panelInfo() { //panel inicial
        panelActivo = panelRanking;
        iPanelActivo = 1;
        panelInfo.add(panelActivo);
    }

    private void inicializar_panelRanking() {
        panelRanking.setLayout(new BorderLayout()); //5 zonas
        panelRanking.add(labelPanelRanking,BorderLayout.NORTH);
        panelRanking.add(panelBotonesRanking,BorderLayout.EAST);
        textareaRanking.setText("Prueba text area Ranking");
        panelRanking.add(new JScrollPane(textareaRanking),BorderLayout.SOUTH);

    }


    private void inicializar_panelBotonesRanking() {
        // Layout
        panelBotonesRanking.setLayout(new FlowLayout());
        panelBotonesRanking.add(buttonCargarRanking);
        panelBotonesRanking.add(buttonLimpiarTexto);
        // Tooltips
        buttonCargarRanking.setToolTipText("Carga la informacion del ranking en el TextArea");
        buttonLimpiarTexto.setToolTipText("Hace un clear del TextArea");
    }

    /////////// LISTENERS (+ su asignacion)

    public void actionPerformed_buttonCargarRanking (ActionEvent event) {
        ArrayList<String> res = iCtrlPresentacion.presentacion_consultar_ranking();
        textareaRanking.setText("");
        for (int i = 0; i < res.size(); i++) {
            textareaRanking.append("\n" + res.get(i));
        }
    }

    public void actionPerformed_buttonLimpiarTexto (ActionEvent event) {
        textareaRanking.setText("");
    }

    private void asignar_listenersComponentes() {

        buttonCargarRanking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonCargarRanking(event);
                    }
                });

        buttonLimpiarTexto.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonLimpiarTexto(event);
                    }
                });

        menuItem_consultar_ranking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonCargarRanking(event);
                    }
                });

    }



    /////////// MAIN (para poder probar)

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                        ctrlPresentacion.inicializarPresentacion();
                        new VistaRanking(ctrlPresentacion).hacerVisible();
                    }});
    }
}
