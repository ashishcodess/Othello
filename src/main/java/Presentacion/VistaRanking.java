package Presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//FALTAN BOTONES DE ORDENACION DEL RANKING


public class VistaRanking {

    // Controlador de presentacion
    private CtrlPresentacion iCtrlPresentacion;

    private int iPanelActivo = 0; //para cambiar entre panel Ranking y estadisticas

    // Componentes de la interficie grafica
    private JFrame frameVista = new JFrame("Vista Ranking");
    private JPanel panelPrincipal = new JPanel();
    private JPanel panelInfo = new JPanel();
    private JPanel panelActivo = new JPanel();
    private JPanel panelBotonesGeneral = new JPanel();
    private JButton buttonConsultarRanking = new JButton("Consultar todo el Ranking");
    private JButton buttonConsultarEstadisticas = new JButton("Consultar estadisticas de un Jugador");
    private JLabel labelInfoRanking = new JLabel("Informacion del ranking (ID, nickname, Ganadas, Perdidas,Empatadas, Totales)"); //borrar al final
    private JLabel labelInfoRanking2 = new JLabel("Informacion del ranking (ID, nickname, Ganadas, Perdidas,Empatadas, Totales)"); //borrar al final

    //COMPONENTES RANKING
    private JPanel panelRanking = new JPanel();
    private JTextArea textareaRanking = new JTextArea(30,45);
    private JTable tablaRanking = new JTable();
    private JPanel panelBotonesRanking = new JPanel();
    private JButton buttonCargarRanking = new JButton("Cargar Ranking");
    private JButton buttonLimpiarRanking= new JButton("Limpiar Ranking");
    //Opcion desplegable con3 opciones de ordenar: ID, Partidas Ganadas y Nickname
    private JComboBox comboBoxOrdenar = new JComboBox();
    private JButton buttonOrdenar = new JButton("Ordenar");


    //COMPONENTES ESTADISTICAS
    private JPanel panelEstadisticas = new JPanel();
    private JTextArea textareaEstadisticas = new JTextArea(3,8);
    private JPanel panelBotonesEstadisticas = new JPanel();
    private JLabel labelID= new JLabel("ID:");
    private JTextField textoID = new JTextField(3);
    private JLabel labelNickname= new JLabel("Nickname:");
    private JTextField textoNickname = new JTextField(15);
    private JButton buttonBuscarEstadisticas= new JButton("Buscar");
    private JButton buttonLimpiarEstadisticas= new JButton("Limpiar");

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
        inicializar_panelBotonesGeneral();
        inicializar_panelPrincipal();
        inicializar_panelInfo();
        inicializar_panelBotonesRanking();
        inicializar_panelRanking();
        inicializar_panelEstadisticas();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(700,750));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelBotonesGeneral,BorderLayout.NORTH);
        panelPrincipal.add(panelInfo,BorderLayout.CENTER);
    }


    private void inicializar_panelBotonesGeneral() { //panel inicial
        panelBotonesGeneral.setLayout(new FlowLayout());
        panelBotonesGeneral.add(buttonConsultarRanking);
        panelBotonesGeneral.add(buttonConsultarEstadisticas);
        buttonConsultarRanking.setToolTipText("Consulta todo el ranking");
        buttonConsultarEstadisticas.setToolTipText("Consulta las estadisticas de un Jugador en concreto");
    }


    private void inicializar_panelInfo() { //panel inicial
        panelActivo = panelRanking;
        iPanelActivo = 1;
        panelInfo.add(panelActivo);
    }

    private void limpiar_ranking() {
        int tam = iCtrlPresentacion.presentacion_consultar_tam_ranking();
        String column[]={"ID","nickname","ganadas","perdidas","empatadas","totales"};
        String data[][] = new String[tam+1][6];
        for (int i = 0; i < column.length; ++i) data[0][i] = column[i];
        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < 6; ++j) {
                data[i+1][j] = "";
            }
        }
        tablaRanking=new JTable(data,column);
    }

    private void inicializar_panelRanking() {
        panelRanking.setLayout(new BorderLayout()); //5 zonas
        panelRanking.add(labelInfoRanking,BorderLayout.NORTH);
        panelRanking.add(panelBotonesRanking,BorderLayout.EAST);
        tablaRanking.setFillsViewportHeight(true);
        limpiar_ranking();
        tablaRanking.repaint();
        panelRanking.revalidate();
        panelRanking.repaint();
        panelRanking.add(tablaRanking,BorderLayout.SOUTH);
    }

    private void inicializar_panelEstadisticas() {
        panelBotonesEstadisticas.setLayout(new FlowLayout());
        panelBotonesEstadisticas.add(labelID);
        panelBotonesEstadisticas.add(textoID);
        panelBotonesEstadisticas.add(labelNickname);
        panelBotonesEstadisticas.add(textoNickname);
        panelBotonesEstadisticas.add(buttonBuscarEstadisticas);
        panelBotonesEstadisticas.add(buttonLimpiarEstadisticas);
        panelEstadisticas.setLayout(new BorderLayout());
        panelEstadisticas.add(labelInfoRanking2,BorderLayout.NORTH);
        panelEstadisticas.add(panelBotonesEstadisticas,BorderLayout.EAST);
        panelEstadisticas.add(textareaEstadisticas,BorderLayout.SOUTH);
    }

    private void inicializar_panelBotonesRanking() {
        panelBotonesRanking.setLayout(new FlowLayout());
        panelBotonesRanking.add(buttonCargarRanking);
        panelBotonesRanking.add(buttonLimpiarRanking);
        comboBoxOrdenar.addItem("ID (mayor a menor)");
        comboBoxOrdenar.addItem("ID(menor a mayor)");
        comboBoxOrdenar.addItem("Partidas Ganadas");
        comboBoxOrdenar.addItem("Nickname");
        panelBotonesRanking.add(comboBoxOrdenar);
        panelBotonesRanking.add(buttonOrdenar);
        buttonCargarRanking.setToolTipText("Carga la informacion del ranking en el TextArea");
        buttonLimpiarRanking.setToolTipText("Hace un clear del TextArea");
        buttonOrdenar.setToolTipText("Ordena la salida en funcion de: ID, partidas ganadas o Nickname");
    }

    /////////// LISTENERS (+ su asignacion)

    public void actionPerformed_buttonOrdenar (ActionEvent event) {
        String s = comboBoxOrdenar.getSelectedItem().toString();
        int orden = -1;
        if (s.equals("ID (mayor a menor)")) orden = 1;
        else if (s.equals("Partidas Ganadas")) orden = 0;
        else if (s.equals("Nickname")) orden = 2;
        else if (s.equals("ID(menor a mayor)")) orden = 3;
        if (iCtrlPresentacion.presentacion_ordenar_ranking(orden)) actionPerformed_buttonCargarRanking(event);
    }

    public void actionPerformed_buttonConsultarRanking (ActionEvent event) {
        panelInfo.remove(panelActivo);
        if (iPanelActivo != 1) {
            iPanelActivo = 1;
            panelActivo = panelRanking;
        }
        panelInfo.add(panelActivo);
        frameVista.pack();
        frameVista.repaint();
    }

    public void actionPerformed_buttonCargarRanking (ActionEvent event) {
        ArrayList<String> res = iCtrlPresentacion.presentacion_consultar_ranking();
        int tam = res.size()/6;
        String column[]={"ID","nickname","ganadas","perdidas","empatadas","totales"};
        String data[][] = new String[tam+1][6];
        for (int i = 0; i < column.length; ++i) data[0][i] = column[i];
        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < 6; ++j) {
                data[i+1][j] = res.get(i*6+j);
            }
        }
        panelRanking.remove(tablaRanking);
        tablaRanking=new JTable(data,column);
        panelRanking.add(tablaRanking,BorderLayout.SOUTH);
        panelRanking.revalidate();
        panelRanking.repaint();
    }

    public void actionPerformed_buttonLimpiarRanking (ActionEvent event) {
        panelRanking.remove(tablaRanking);
        limpiar_ranking();
        panelRanking.add(tablaRanking,BorderLayout.SOUTH);
        panelRanking.revalidate();
        panelRanking.repaint();
    }

    public void actionPerformed_buttonConsultarEstadisticas(ActionEvent event) {
        panelInfo.remove(panelActivo);
        if (iPanelActivo != 2) {
            iPanelActivo = 2;
            panelActivo = panelEstadisticas;
        }
        panelInfo.add(panelActivo);
        frameVista.pack();
        frameVista.repaint();
    }

    public void actionPerformed_buttonBuscarEstadisticas (ActionEvent event) {
        int id = -1;
        try {
            id = Integer.parseInt(textoID.getText());
        }
        catch (Exception e) {} //no hacer nada
        String nick = textoNickname.getText();
        ArrayList<String> res = iCtrlPresentacion.presentacion_consultar_estadisticas(id,nick);
        textareaEstadisticas.setText("");
        for (int i = 0; i < res.size(); i++) {
            textareaEstadisticas.append("\n     " + res.get(i));
        }
    }

    public void actionPerformed_buttonLimpiarTextoEstadisticas (ActionEvent event) {
        textareaEstadisticas.setText("");
        textoID.setText("");
        textoNickname.setText("");
    }



    private void asignar_listenersComponentes() {

        buttonCargarRanking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonCargarRanking(event);
                    }
                });

        buttonLimpiarRanking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonLimpiarRanking(event);
                    }
                });

        menuItem_consultar_ranking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonCargarRanking(event);
                    }
                });

        menuitemQuit.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        //Poner aqui lo de actualizar ranking (cuando tengamos la interfaz en general)
                        System.exit(0);
                    }
                });

        buttonConsultarRanking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonConsultarRanking(event);
                    }
                });

        buttonConsultarEstadisticas.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonConsultarEstadisticas(event);
                    }
                });

        buttonLimpiarEstadisticas.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonLimpiarTextoEstadisticas(event);
                    }
                });

        buttonBuscarEstadisticas.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonBuscarEstadisticas(event);
                    }
                });

        menuItem_consultar_ranking.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonConsultarRanking(event);
                    }
                });

        menuItem_consultar_estadisticas.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonConsultarEstadisticas(event);
                    }
                });

        buttonOrdenar.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        actionPerformed_buttonOrdenar(event);
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
