package Presentacion;

import ControladorPersistencia.CtrlPersitencia;
import Dominio.CtrlDominio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

/**Todos las vistas disponibles que se pueden visualizar*/
enum vistaActiva{LOGIN, LOGIN_USER2, MENU, RANKING, CREDITOS, TABLERO, CONFIGPARTIDA,CREARTABLERO,CARGARTABLERO, BORRARTABLERO , CARGARPARTIDA}


public class CtrlPresentacion {


    /**enumeración utilizada en la Vista Cargar/Borrar para diferenciar que tipo de Vista estamos tratando*/
    public enum tipoTablero {PARTIDA,TABLERO}

    /**enumeración utilizada en la Vista Login para diferenciar a que usuario va destinado el login*/
    public enum tipoJugador {JUGADOR1,JUGADOR2}

    /**Controlador de Dominio*/
    private final CtrlDominio ctrlDominio;

    /**Vista de Login de un Jugador*/
    private final VistaLogin vistaLogin;

    /**Vista de Menu Principal del Juego*/
    private final VistaMenu vistaMenu;

    /**Vista del Ranking*/
    private final VistaRanking vistaRanking;

    /**Vista de Cargar/Borrar (sirve para Partidas o para Tableros)*/
    private final VistaCargarBorrar vistaCargarBorrar;

    /**Vista de los creditos*/
    private final VistaCreditos vistaCreditos;

    /**Vista de tablero (partida en juego)*/
    private final VistaTablero vistaTablero;

    /**Vista de Configuracion de Partida*/
    private final VistaConfigPartida vistaConfigPartida;


    /**
     * Creadora por defecto de CtrlPresentacion
     * */
    public CtrlPresentacion() {
        ctrlDominio = new CtrlDominio();
        vistaRanking = new VistaRanking(this);
        vistaLogin = new VistaLogin(this,tipoJugador.JUGADOR1);
        vistaMenu = new VistaMenu(this);
        vistaConfigPartida = new VistaConfigPartida(this);
        vistaCreditos = new VistaCreditos(this);
        vistaTablero  = new VistaTablero(this);
        vistaCargarBorrar = new VistaCargarBorrar(this);
    }

    /**
     * inicializa la vista (con la VistaLogin como ventana inicial)
     * */
    public void inicializarPresentacion() {
        hacerVisibleVista(vistaActiva.LOGIN);
    }


    /**
     * Metodo para cerrar la ventana
     * */
    public void salir_del_juego() {
        presentacion_exportar_ranking();
        System.exit(0);
    }


    /*CONFIGURACION COMUN PARA TODOS LOS FRAMES, CAMBIA UNICAMENTE EL SIZE DE LA VENTANA*/
    public JFrame configuracion_frame(int size_x, int size_y, String s) {
        JFrame frameAux = new JFrame(s);
        frameAux.setMinimumSize(new Dimension(size_x,size_y));
        frameAux.setPreferredSize(frameAux.getMinimumSize());
        frameAux.setResizable(false);
        frameAux.setLocationRelativeTo(null);
        frameAux.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameAux.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                salir_del_juego();
            }
        });
        return frameAux;
    }

    //agregar en funcion de las necesidades
    private void apagar_vistas() {
        vistaLogin.hacerVisible(false,tipoJugador.JUGADOR1);
        vistaMenu.hacerVisible(false);
        vistaConfigPartida.hacerVisible(false);
        vistaRanking.hacerVisible(false);
        vistaCreditos.hacerVisible(false);
        vistaTablero.hacerVisible(false, tipoTablero.PARTIDA);
        vistaConfigPartida.hacerVisible(false);
        vistaCargarBorrar.hacerVisible(false, tipoTablero.PARTIDA);
    }

    /**
     * Metodo hacerVisibleVista
     * @param a dependiendo de la enumeracion de vistaActiva hace visible una vista u otra (para gestion de vistas)
     * */
    public void hacerVisibleVista(vistaActiva a) {
        apagar_vistas();
        switch (a) {
            case LOGIN:
                vistaLogin.hacerVisible(true,tipoJugador.JUGADOR1);
                break;
            case LOGIN_USER2:
                vistaLogin.hacerVisible(true,tipoJugador.JUGADOR2);
                break;
            case MENU:
                vistaMenu.hacerVisible(true);
                break;
            case RANKING:
                vistaRanking.hacerVisible(true);
                break;
            case CREDITOS:
                vistaCreditos.hacerVisible(true);
                break;
            case TABLERO:
                vistaTablero.recargar_tablero();
                vistaTablero.hacerVisible(true, tipoTablero.PARTIDA);
                break;
            case CONFIGPARTIDA:
                vistaConfigPartida.hacerVisible(true);
                break;
            case CREARTABLERO:
                crearTablero();
                vistaTablero.hacerVisible(true, tipoTablero.TABLERO);
                break;
            case CARGARTABLERO:
                vistaCargarBorrar.hacerVisible(true, tipoTablero.TABLERO);
                break;
            case BORRARTABLERO:
                modificar_idTablero_cargar(-2);
                vistaCargarBorrar.hacerVisible(true, tipoTablero.TABLERO);
                break;
            case CARGARPARTIDA:
                vistaCargarBorrar.hacerVisible(true, tipoTablero.PARTIDA);
        }
    }

    /**
     * Metodo login (desde Capa Presentacion)
     * @param id identificador de usuario a hacer login
     * @param nick nickname de usuario a hacer login
     * @param a tipoJugador a hacer el login (JUGADOR1 o JUGADOR2)
     * @return devuelve 1 en caso de login correcto, 0 caso contrario
     * */
    public boolean presentacion_login(int id, String nick, tipoJugador a) {
        boolean b = false;
        switch (a) {
            case JUGADOR1:
                b = false;
                break;
            case JUGADOR2:
                b = true;
                break;
        }
        return ctrlDominio.login_presentacion(id,nick,b);
    }

    /**
     * Metodo login (desde Capa Presentacion)
     * @param id identificador de usuario a mostrar las partidas disponibles de dicho Jugador
     * @param nick nickname de usuario a mostrar las partidas disponibles de dicho Jugador
     * @return devuelve un ArrayList de Strings con las partidas disponibles de este Jugador
     * */
    public ArrayList<String> presentacion_buscar_partidas(int id , String nick ) {
        return ctrlDominio.listar_partidas_disponibles(id , nick);
    }

    /**
     * Metodo consultar info de partida a partir de un id (desde Presentacion)
     * @param id es el identificador de partida a consultar la informacion
     * @return devuelve un Arraylist de Strings con toda la informacion del fichero de partida identificado por su id
     * */
    public ArrayList<String> consultar_info_partida_ID(int id) {
        return ctrlDominio.consultar_info_partida_ID(id);
    }

    /**
     * Metodo cargar tablero de una partida (desde Presentacion)
     * @param idPartida identificador de partida a cargar el tablero
     * @return devuelve el tablero de la partida
     * */
    public int[][] presentacion_cargar_tablero_partida(int idPartida) {
        return ctrlDominio.dominio_cargar_tablero_partida(idPartida);
    }

    /**
     * Metodo consultar Identificador J1 (desde Presentacion)
     * @return devuelve el identificador del jugador 1*/
    public int consultar_id_j1() {return ctrlDominio.consultar_id_j1();}

    /**
     * Metodo consultar nickname J1 (desde Presentacion)
     * @return devuelve el nickname del jugador 1 */
    public String consultar_nickname_j1() {return ctrlDominio.consultar_nickname_j1();}

    /**
     * metodo Get info usuario activo (desde Capa Presentacion)
     * @return devuelve la informacion que esta logueado dentro del juego
     * */
    public String consultar_info_usuario_activo() {return ctrlDominio.consultar_info_usuario_activo();}


    /**
     * Metodo crear Partida (desde Presentacion)
     * @param a_int es la informacion recogida de la vista de Configuracin de Partida */
    public void presentacion_crearPartida(ArrayList<Integer> a_int) {
        ctrlDominio.domino_crearPartida(a_int);
    }

    /** Metodo consultar direccion imagen (desde Presentacion)
     * @param t es el tipo de imagen a cargar [vacia, disponible, blanca, negra]
     * @return devuelve la direccion del icono seleccionado*/
    public String presentacion_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG t) {
        return ctrlDominio.dominio_consultar_dir_imagen_fichas(t);
    }

    /**
     * Metodo registro usuario (desde Capa Presentacion)
     * @param nick nickname de usuario a registrar
     * @return devuelve el identificador de usuario asignado a este jugador en caso de registro correcto \
     * caso contrario devuelve -1
     * */
    public int presentacion_registro_usuario(String nick) {
        return ctrlDominio.dominio_registro_usuario(nick);
    }

    //FUNCIONES DE RANKING
    /**
     * Metodo exportar ranking (desde Capa Presentacion)
     * */
    public void presentacion_exportar_ranking() {
        ctrlDominio.domino_exportar_ranking();
    }

    /**
     * Metodo consultar estadisitcas (desde Capa Presentacion)
     * @param id identificador de usuario a consultar sus estadisitcas
     * @param nick nickname de usuario a consultar sus estadisitcas
     * @return devuelve un arraylist de strings con la informacion de las estadisticas (en caso de existir en el ranking), \
     * caso contrario devuelve string con informacion de ERROR
     * */
    public ArrayList<String> presentacion_consultar_estadisticas(int id, String nick) {
        return ctrlDominio.consultar_estadisticas(id,nick);
    }

    /**
     * Metodo presentacion_consultar_logros
     * @return devuelve en un ArrayList de String la informacion de los logros al completo
     * */
    public ArrayList<String> presentacion_consultar_logros() {
        return ctrlDominio.consultar_logros();
    }

    /**
     * Metodo consultar ranking (desde Capa Presentacion)
     * @return devuelve en un ArrayList de String la informacion el ranking al completo*/
    public ArrayList<String> presentacion_consultar_ranking() {
        ArrayList<String> as = ctrlDominio.consultar_ranking();
        ArrayList<String> res = new ArrayList<>();
        for (String a : as) {
            String[] rs = a.split(" ");
            Collections.addAll(res, rs);
        }
        return res;
    }

    /**
     * Metodo ordenar ranking (desde Capa Presentacion)
     * @param orden [0 (Ganadas), 1 (ID mayor a menor) , 2 (NICKNAME), 3 (ID menor a mayor)]*/
    public void presentacion_ordenar_ranking(int orden) {
        ctrlDominio.ordenar_ranking(orden);
    }

    /**
     * Metodo consultar size del ranking (desde capa Presentacion)
     * @return devuelve el size del ranking*/
    public int presentacion_consultar_tam_ranking() {return ctrlDominio.consultar_tam_ranking();}


    //FUNCIONES DE TABLERO

    /**
     * Metodo listar tableros disponibles (desde Presentacion)
     * @return devuelve un Arraylist de strings con todos los tableros almacenados en el sistema
     * */
    public ArrayList<String> obtener_lista_tableros_disponibles() {return ctrlDominio.listar_tableros_disponibles();}

    /**
     * Metodo crear tablero (desde Presentacion):
     * Crea una partida ficticia para modificar el tablero personalizado
     * */
    public void crearTablero() {
        ctrlDominio.dominio_crear_tablero();
    }

    /** Metodo modificar idTablero_cargar
     * @param id identificador de tablero personalizado a modificar*/
    public void modificar_idTablero_cargar(int id) {
        ctrlDominio.modificar_idTablero_cargar(id);
    }

    /**Metodo consultar idTablero_cargar (desde presentacion)
     * @return devuelve el identificador del tablero a cargar*/
    public int consultar_idTablero_cargar() {return ctrlDominio.consultar_idTablero_cargar();}

    /** Metodo consultar tablero partida (partida_activa)
     * @return True en caso haberse guardado con exito el tablero, caso contrario devuelve falso*/
    public boolean presentacion_guardar_tablero() {
        return ctrlDominio.dominio_guardar_tablero();
    }

    /** Metodo borrar_tablero (desde Presentacion)
     * @param id el identificador de tablero a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito */
    public boolean presentacion_borrar_tablero(int id) {return ctrlDominio.dominio_borrar_tablero(id);}

    /**
     * Metodo Cargar Tablero (desde Presentacion)
     * @param id es el ID de tablero a cargar
     * @return devuelve la matriz de enteros de un tablero con id igual a idTablero, caso contrario devuelve tablero vacio*/
    public int[][] presentacion_cargarTablero(int id) {
        return ctrlDominio.dominio_cargar_tablero(id);
    }


    //FUNCIONES DE PARTIDA

    /** Metodo guardar partida (desde presentacion)
     * guarda la informacion de partida_activa en un fichero
     * @return devuelve cierto en caso de haberse guardado con exito, caso contrario devuelve falso */
    public boolean presentacion_guardar_partida() { return ctrlDominio.dominio_guardar_partida();}

    /** Metodo cargar Partida (desde presentacion)
     * Carga una partida a partir de un fichero guardado previamente y se actualiza "partida_activa"
     * @param id id de partida a cargar*/
    public void presentacion_cargarPartida(int id) {
        if (id >= 0) {
            ctrlDominio.dominio_cargar_partida(id);
        }
    }

     public int presentacion_get_negras() {
        return ctrlDominio.dominio_get_negras();
     }

     public int presentacion_get_blancas() {
        return ctrlDominio.dominio_get_blancas();
     }

     public int presentacion_get_turno() {
        return ctrlDominio.dominio_get_turno();
     }

    /** Metodo Borrar Partida (desde Presentacion)
     * @param id es el identificador de partida a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito */
    public boolean presentacion_borrarPartida(int id) {
        boolean b = false;
        if (id >= 0) {
            b = ctrlDominio.dominio_borrar_partida(id);
        }
        return b;
    }


    /**
     * Metodo obtener tablero
     * @return devuelve el tablero de la partida como una matriz de enteros (desde la Capa de Dominio)*/
    public int[][] presentacion_consultar_Tablero() {return ctrlDominio.consultar_TableroPartida();}



    public int presentacionRondaPartida(int x, int y) {
        return ctrlDominio.dominioRondaPartida(x, y);
    }

    /*public void presentacionActualizarTablero() {
        ctrlDominio.dominioActualizarTablero();
    }*/

}
