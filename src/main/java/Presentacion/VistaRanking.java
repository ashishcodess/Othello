package Presentacion;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class VistaRanking {

    // Controlador de presentacion
    private final CtrlPresentacion iCtrlPresentacion;

    private int iPanelActivo = 0; //para cambiar entre panel Ranking y estadisticas

    // Componentes de la interficie grafica
    private final JFrame frameVista = new JFrame("Vista Ranking");
    private final  JPanel panelPrincipal = new JPanel();
    private final  JPanel panelInfo = new JPanel();
    private JPanel panelActivo = new JPanel();
    private final JPanel panelBotonesGeneral = new JPanel();
    private final JButton buttonConsultarRanking = new JButton("Consultar todo el Ranking");
    private final JButton buttonConsultarEstadisticas = new JButton("Consultar estadisticas (1 jugador)");
    private final JButton buttonConsultarLogros = new JButton("Consultar logros");
    private final JLabel labelInfoRanking = new JLabel("Informacion del ranking (ID, nickname, Ganadas, Perdidas,Empatadas, Totales)"); //borrar al final
    private final JLabel labelInfoRanking2 = new JLabel("Informacion del ranking (ID, nickname, Ganadas, Perdidas,Empatadas, Totales)"); //borrar al final

    private final JButton buttonVolverMenu = new JButton("Volver al menu");

    //COMPONENTES RANKING
    private final JPanel panelRanking = new JPanel();
    private JTable tablaRanking = new JTable();
    private final JPanel panelBotonesRanking = new JPanel();
    private final JButton buttonCargarRanking = new JButton("Cargar Ranking");
    private final JButton buttonLimpiarRanking= new JButton("Limpiar Ranking");
    private final JComboBox<String> comboBoxOrdenar = new JComboBox<>();
    private final JButton buttonOrdenar = new JButton("Ordenar");


    //COMPONENTES ESTADISTICAS / LOGROS
    private final JPanel panelEstadisticas = new JPanel();
    private final JPanel panelBotonesEstadisticas = new JPanel();
    private final JLabel labelID= new JLabel("ID:");
    private final JTextField textoID = new JTextField(3);
    private final JLabel labelNickname= new JLabel("Nickname:");
    private final JTextField textoNickname = new JTextField(15);
    private final JButton buttonBuscarEstadisticas= new JButton("Buscar");
    private final JButton buttonLimpiarEstadisticas= new JButton("Limpiar");
    private JTable tablaEstadisticas = new JTable();

    private final JPanel panelLogros = new JPanel();
    private final JLabel labelPartidaCorta= new JLabel("Partida mas corta(turnos):");
    private final JTextField textoPartidaCorta = new JTextField(35);
    private final JLabel labelPartidasTotales= new JLabel("Jugador con mas partidas (total):");
    private final JTextField textoPartidaTotal= new JTextField(25);
    private final JLabel labelPartidasGanadas= new JLabel("Jugador con mas partidas (ganadas):");
    private final JTextField textoPartidaGanadas = new JTextField(25);
    private final JLabel labelPartidasPerdidas= new JLabel("Jugador con mas partidas (perdidas:");
    private final JTextField textoPartidaPerdidas = new JTextField(25);

    //BARRA DE MENU
    private final JMenuBar menubarVista = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuitemVolverMenu= new JMenuItem("Volver al menu principal");
    private final JMenuItem menuitemQuit = new JMenuItem("Salir");
    private final JMenu menuRanking = new JMenu("Ranking");
    private final JMenuItem menuItem_consultar_ranking = new JMenuItem("Consultar Ranking");
    private final JMenuItem menuItem_consultar_estadisticas = new JMenuItem("Consultar Estadisticas");
    private final JMenuItem menuItem_consultar_logros = new JMenuItem("Consultar Logros");


    /**
     * Constructora de VistaRanking
     * */
    public VistaRanking (CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        frameVista.setLayout(new BorderLayout()); // 5 zonas (North, South, East, West, Center)
        inicializarComponentes();
    }

     /**
     *Metodo hacerVisible
     * @param b si TRUE entonces el frame sera visible, caso contrario estara desactivado
     * */
    public void hacerVisible(boolean b) {
        frameVista.pack();
        frameVista.setVisible(b);
        frameVista.setEnabled(b);
    }


    /////////// INICIALIZACION DE COMPONENTES

    /**
     * Metodo para inicializar componentes (menuBar, paneles y frame)
     * */
    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_menubarVista();
        inicializar_paneles();
        asignar_listenersComponentes();
    }

    /**
     * Metodo para inicializar frame
     * */
    private void inicializar_frameVista() {
        frameVista.setMinimumSize(new Dimension(700,750));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameVista.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                salir_del_juego();
            }
        });
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelPrincipal);
    }

    /**
     * Metodo para inicializar menuBar (barra de menu superior)
     * */
    private void inicializar_menubarVista() {
        menuFile.add(menuitemVolverMenu);
        menuFile.add(menuitemQuit);
        menuRanking.add(menuItem_consultar_ranking);
        menuRanking.add(menuItem_consultar_estadisticas);
        menuRanking.add(menuItem_consultar_logros);
        menubarVista.add(menuFile);
        menubarVista.add(menuRanking);
        frameVista.setJMenuBar(menubarVista);
    }


    /**
     * Metodo para inicializar todos los panelesº
     * */
    private void inicializar_paneles() {
        //PANEL BOTONES_GENERAL
        panelBotonesGeneral.setLayout(new FlowLayout());
        panelBotonesGeneral.add(buttonConsultarRanking);
        panelBotonesGeneral.add(buttonConsultarEstadisticas);
        panelBotonesGeneral.add(buttonConsultarLogros);
        panelBotonesGeneral.add(buttonVolverMenu);
        buttonConsultarRanking.setToolTipText("Consulta todo el ranking");
        buttonConsultarEstadisticas.setToolTipText("Consulta las estadisticas de un Jugador en concreto");
        buttonConsultarLogros.setToolTipText("Consultar los logros");
        buttonVolverMenu.setToolTipText("Volver al menu principal");

        //PANEL RANKING
        panelRanking.setLayout(new BorderLayout()); //5 zonas
        panelRanking.add(labelInfoRanking,BorderLayout.NORTH);
        panelRanking.add(panelBotonesRanking,BorderLayout.EAST);
        tablaRanking.setFillsViewportHeight(true);
        limpiar_ranking();
        tablaRanking.repaint();
        panelRanking.revalidate();
        panelRanking.repaint();
        panelRanking.add(tablaRanking,BorderLayout.SOUTH);

        //PANEL BOTONES RANKING
        panelBotonesRanking.setLayout(new FlowLayout());
        panelBotonesRanking.add(buttonCargarRanking);
        panelBotonesRanking.add(buttonLimpiarRanking);
        for (String s : Arrays.asList("ID (mayor a menor)", "ID(menor a mayor)", "Partidas Ganadas", "Nickname","Perdidas","Empatadas","Totales")) {
            comboBoxOrdenar.addItem(s);
        }
        panelBotonesRanking.add(comboBoxOrdenar);
        panelBotonesRanking.add(buttonOrdenar);
        buttonCargarRanking.setToolTipText("Carga la informacion del ranking en el TextArea");
        buttonLimpiarRanking.setToolTipText("Hace un clear del TextArea");
        buttonOrdenar.setToolTipText("Ordena la salida en funcion de: ID, partidas ganadas o Nickname");


        //PANEL ESTADISTICAS
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
        tablaEstadisticas.setFillsViewportHeight(true);
        limpiar_estadisticas();
        tablaEstadisticas.repaint();
        panelEstadisticas.add(tablaEstadisticas,BorderLayout.SOUTH);

        //logros
        panelLogros.setLayout(new BoxLayout(panelLogros,BoxLayout.PAGE_AXIS));
        JPanel panelAux = new JPanel();
        panelAux.setLayout(new FlowLayout());
        panelAux.add(labelPartidaCorta);
        panelAux.add(textoPartidaCorta);
        panelLogros.add(panelAux);
        panelAux = new JPanel();
        panelAux.setLayout(new FlowLayout());
        panelAux.add(labelPartidasTotales);
        panelAux.add(textoPartidaTotal);
        panelLogros.add(panelAux);
        panelAux = new JPanel();
        panelAux.setLayout(new FlowLayout());
        panelAux.add(labelPartidasGanadas);
        panelAux.add(textoPartidaGanadas);
        panelLogros.add(panelAux);
        panelAux = new JPanel();
        panelAux.setLayout(new FlowLayout());
        panelAux.add(labelPartidasPerdidas);
        panelAux.add(textoPartidaPerdidas);
        panelLogros.add(panelAux);

        //PANEL INFO
        panelActivo = panelRanking;
        iPanelActivo = 1;
        panelInfo.add(panelActivo);

        //PANEL PRINCIPAL
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelBotonesGeneral,BorderLayout.NORTH);
        panelPrincipal.add(panelInfo,BorderLayout.CENTER);
    }



    /////////// LISTENERS (+ su asignacion)

    /**
     * Metodo WindowPerfomed para cerrar la ventana
     * */
    private void salir_del_juego() {
        iCtrlPresentacion.presentacion_exportar_ranking();
        System.exit(0);
    }

    /**
     * Metodo cargar_logros (guarda la informacion de los logros en los TextFields assignados por estos)
     * * */
    private void cargar_logros() {
        ArrayList<String> as = iCtrlPresentacion.presentacion_consultar_logros();
        if (as.size() == 4) {
            textoPartidaCorta.setText(as.get(0));
            textoPartidaCorta.setEditable(false);

            textoPartidaTotal.setText(as.get(1));
            textoPartidaTotal.setEditable(false);

            textoPartidaGanadas.setText(as.get(2));
            textoPartidaGanadas.setEditable(false);

            textoPartidaPerdidas.setText(as.get(1));
            textoPartidaPerdidas.setEditable(false);

        }

    }

    //CONSULTAR RANKING

    /**
     * Metodo actionPerfomed del boton de Consultar Ranking
     * * */
    public void actionPerformed_buttonConsultarRanking() {
        panelInfo.remove(panelActivo);
        if (iPanelActivo != 1) {
            iPanelActivo = 1;
            panelActivo = panelRanking;
        }
        panelInfo.add(panelActivo);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Metodo actionPerfomed del boton de Cargar Ranking
     * * */
    public void actionPerformed_buttonCargarRanking (ActionEvent event) {
        ArrayList<String> res = iCtrlPresentacion.presentacion_consultar_ranking();
        int tam = res.size()/6;
        String[] column ={"ID","nickname","ganadas","perdidas","empatadas","totales"};
        String[][] data = new String[tam+1][6];
        System.arraycopy(column, 0, data[0], 0, column.length);
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

    /**
     * Metodo actionPerfomed del boton de Limpiar Ranking
     * * */
    public void actionPerformed_buttonLimpiarRanking (ActionEvent event) {
        panelRanking.remove(tablaRanking);
        limpiar_ranking();
        panelRanking.add(tablaRanking,BorderLayout.SOUTH);
        panelRanking.revalidate();
        panelRanking.repaint();
    }

    /**
     * Metodo actionPerfomed del boton de Ordenar (Para ordenar el ranking en funcion de ID, ganadas o Nickname)
     * */
    public void actionPerformed_buttonOrdenar (ActionEvent event) {
        String s = Objects.requireNonNull(comboBoxOrdenar.getSelectedItem()).toString();
        int orden = -1;
        switch (s) {
            case "ID (mayor a menor)":
                orden = 1;
                break;
            case "Partidas Ganadas":
                orden = 0;
                break;
            case "Nickname":
                orden = 2;
                break;
            case "ID(menor a mayor)":
                orden = 3;
                break;
            case "Perdidas":
                orden = 4;
                break;
            case "Empatadas":
                orden = 5;
                break;
            case "Totales":
                orden = 6;
                break;
        }
        if (iCtrlPresentacion.presentacion_ordenar_ranking(orden)) actionPerformed_buttonCargarRanking(event);
    }

    /**
     * Metodo limpiar_ranking (vacia la tabla del ranking)
     * * */
    private void limpiar_ranking() {
        int tam = iCtrlPresentacion.presentacion_consultar_tam_ranking();
        String[] column ={"ID","nickname","ganadas","perdidas","empatadas","totales"};
        String[][] data = new String[tam+1][6];
        System.arraycopy(column, 0, data[0], 0, column.length);
        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < 6; ++j) {
                data[i+1][j] = "";
            }
        }
        tablaRanking=new JTable(data,column);
    }

    //CONSULTAR ESTADISTICAS

    /**
     * Metodo actionPerfomed del boton de Consultar Estadisticas
     * * */
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

    /**
     * Metodo actionPerfomed del boton de Consultar Logros
     * * */
    public void actionPerformed_buttonConsultarLogros(ActionEvent event) {
        panelInfo.remove(panelActivo);
        if (iPanelActivo != 3) {
            iPanelActivo = 3;
            panelActivo = panelLogros;
            cargar_logros();
        }
        panelInfo.add(panelActivo);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Metodo actionPerfomed del boton de Buscar Estadisticas
     * * */
    public void actionPerformed_buttonBuscarEstadisticas (ActionEvent event) {
        int id = -1;
        try {
            id = Integer.parseInt(textoID.getText());
        } catch (Exception ignored) {
        } //no hacer nada
        String nick = textoNickname.getText();
        ArrayList<String> res = iCtrlPresentacion.presentacion_consultar_estadisticas(id, nick);
        String[] sRes = res.get(0).split(" ");
        String[] column = {"ID", "nickname", "ganadas", "perdidas", "empatadas", "totales"};
        String[][] data = new String[2][6];
        System.arraycopy(column, 0, data[0], 0, column.length);
        System.arraycopy(sRes, 0, data[1], 0, 6);
        panelEstadisticas.remove(tablaEstadisticas);
        tablaEstadisticas=new JTable(data,column);
        panelEstadisticas.add(tablaEstadisticas,BorderLayout.SOUTH);
        panelEstadisticas.revalidate();
        panelEstadisticas.repaint();
    }

    /**
     * Metodo actionPerfomed del boton de Limpiar Estadisticas
     * * */
    public void actionPerformed_buttonLimpiarEstadisticas (ActionEvent event) {
        textoID.setText("");
        textoNickname.setText("");
        panelEstadisticas.remove(tablaEstadisticas);
        limpiar_estadisticas();
        panelEstadisticas.add(tablaEstadisticas,BorderLayout.SOUTH);
        panelEstadisticas.revalidate();
        panelEstadisticas.repaint();
    }

    /**
     * Metodo limpiar_estadisticas (vacia la tabla de estadisticas)
     * * */
    private void limpiar_estadisticas() {
        int tam = 1;
        String[] column ={"ID","nickname","ganadas","perdidas","empatadas","totales"};
        String[][] data = new String[tam+1][6];
        System.arraycopy(column, 0, data[0], 0, column.length);
        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < 6; ++j) {
                data[i+1][j] = "-";
            }
        }
        panelEstadisticas.remove(tablaEstadisticas);
        tablaEstadisticas=new JTable(data,column);
        panelEstadisticas.add(tablaEstadisticas,BorderLayout.SOUTH);
        panelEstadisticas.revalidate();
        panelEstadisticas.repaint();
    }

    /**
     * Metodo para asignar los listeners a cada componente
     * */
    private void asignar_listenersComponentes() {

        buttonCargarRanking.addActionListener
                (this::actionPerformed_buttonCargarRanking);

        buttonLimpiarRanking.addActionListener
                (this::actionPerformed_buttonLimpiarRanking);

        menuItem_consultar_ranking.addActionListener
                (this::actionPerformed_buttonCargarRanking);

        menuitemVolverMenu.addActionListener
                (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

        menuitemQuit.addActionListener
                (event -> {
                    iCtrlPresentacion.presentacion_exportar_ranking();
                    System.exit(0);
                });

        buttonConsultarRanking.addActionListener
                (event -> actionPerformed_buttonConsultarRanking());

        buttonConsultarEstadisticas.addActionListener
                (this::actionPerformed_buttonConsultarEstadisticas);

        buttonConsultarLogros.addActionListener
                (this::actionPerformed_buttonConsultarLogros);

        buttonLimpiarEstadisticas.addActionListener
                (this::actionPerformed_buttonLimpiarEstadisticas);

        buttonBuscarEstadisticas.addActionListener
                (this::actionPerformed_buttonBuscarEstadisticas);

        menuItem_consultar_ranking.addActionListener
                (event -> actionPerformed_buttonConsultarRanking());

        menuItem_consultar_estadisticas.addActionListener
                (this::actionPerformed_buttonConsultarEstadisticas);

        menuItem_consultar_logros.addActionListener
                (this::actionPerformed_buttonConsultarLogros);

        buttonVolverMenu.addActionListener (event -> iCtrlPresentacion.hacerVisibleVista(vistaActiva.MENU));

        buttonOrdenar.addActionListener
                (this::actionPerformed_buttonOrdenar);

    }

}
