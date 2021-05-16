package Presentacion;

import Dominio.CtrlDominio;

import java.util.ArrayList;
import java.util.Collections;

enum vistaActiva{LOGIN, MENU, RANKING, CREDITOS, TABLERO, PRUEBA} //agregar en funcion de las necesidades

public class CtrlPresentacion {

    private final CtrlDominio ctrlDominio;

    private final VistaRanking vistaRanking;
    private final VistaLogin vistaLogin;
    private final VistaMenu vistaMenu;
    private final VistaCreditos vistaCreditos;
    private final VistaTablero vistaTablero;

    private final VistaPrueba vistaPrueba;

    /**
     * Creadora por defecto de CtrlPresentacion
     * */
    public CtrlPresentacion() {
        ctrlDominio = new CtrlDominio();
        vistaRanking = new VistaRanking(this);
        vistaLogin = new VistaLogin(this);
        vistaMenu = new VistaMenu(this);
        //vistaConfigPartida = new VistaConfigPartida(this);
        //vistaPartida = new VistaPartida(this);
        vistaCreditos = new VistaCreditos(this);
        vistaTablero  = new VistaTablero(this);

        vistaPrueba = new VistaPrueba(this);
    }

    /**
     * inicializa la vista (con la VistaLogin como ventana inicial)
     * */
    public void inicializarPresentacion() {
        hacerVisibleVista(vistaActiva.LOGIN);
    }


    /**
     * Metodo hacerVisibleVista
     * @param a dependiendo de la enumeracion de vistaActiva hace visible una vista u otra (para gestion de vistas)
     * */
    public void hacerVisibleVista(vistaActiva a) {
        switch (a) {
            case LOGIN:
                vistaLogin.hacerVisible(true);
                vistaMenu.hacerVisible(false);
                //vistaConfigPartida.hacerVisible(false);
                //vistaPartida.hacerVisible(false);
                vistaRanking.hacerVisible(false);
                vistaCreditos.hacerVisible(false);
                break;
            case MENU:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(true);
                //vistaConfigPartida.hacerVisible(false);
                //vistaPartida.hacerVisible(false);
                vistaRanking.hacerVisible(false);
                vistaCreditos.hacerVisible(false);
                break;
            case RANKING:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(false);
                //vistaConfigPartida.hacerVisible(false);
                //vistaPartida.hacerVisible(false);
                vistaRanking.hacerVisible(true);
                vistaCreditos.hacerVisible(false);
                break;
            case CREDITOS:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(false);
                //vistaConfigPartida.hacerVisible(false);
                //vistaPartida.hacerVisible(false);
                vistaRanking.hacerVisible(false);
                vistaCreditos.hacerVisible(true);
                break;
            case TABLERO:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(false);
                //vistaConfigPartida.hacerVisible(false);
                //vistaPartida.hacerVisible(false);
                vistaRanking.hacerVisible(false);
                vistaCreditos.hacerVisible(false);
                vistaTablero.hacerVisible(true);
                break;

            case PRUEBA:
                vistaPrueba.hacerVisible(true);
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(false);
                vistaRanking.hacerVisible(false);
                vistaCreditos.hacerVisible(false);
                vistaTablero.hacerVisible(false);
        }
    }

    /**
     * Metodo login (desde Capa Presentacion)
     * @param id identificador de usuario a hacer login
     * @param nick nickname de usuario a hacer login
     * @return devuelve 1 en caso de login correcto, 0 caso contrario
     * */
    public int presentacion_login(int id, String nick) {
        return ctrlDominio.login_inicial_presentacion(id,nick);
    }


    /**
     * metodo Get info usuario activo (desde Capa Presentacion)
     * @return devuelve la informacion que esta logueado dentro del juego
     * */
    public String presentacion_get_info_usuario_activo() {return ctrlDominio.get_info_usuario_activo();}

    /**
     * Metodo registro usuario (desde Capa Presentacion)
     * @param nick nickname de usuario a registrar
     * @return devuelve el identificador de usuario asignado a este jugador en caso de registro correcto \
     * caso contrario devuelve -1
     * */
    public int presentacion_registro_usuario(String nick) {
        return ctrlDominio.dominio_registro_usuario(nick);
    }

    /**
     * Metodo exportar ranking (desde Capa Presentacion)
     * */
    public void presentacion_exportar_ranking() {
        try {ctrlDominio.domino_exportar_ranking();} catch (Exception e) {}
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
     * @return devuelve en un ArrayList de String la informacion el ranking al completo
     * */
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
     * @param orden [0 (Ganadas), 1 (ID mayor a menor) , 2 (NICKNAME), 3 (ID menor a mayor)]
     * @return devuelve true en caso de que se haya efectuado una ordenacion, caso contrario devuelve falso
     * */
    public boolean presentacion_ordenar_ranking(int orden) {
        return ctrlDominio.ordenar_ranking(orden);
    }

    /**
     * Metodo consultar size del ranking (desde capa Presentacion)
     * @return devuelve el size del ranking
     * */
    public int presentacion_consultar_tam_ranking() {return ctrlDominio.consultar_tam_ranking();}

    /**
     * Metodo consultar size del ranking (desde capa Presentacion)
     * @return devuelve el size del ranking
     * */
    //public int[][] presentacionObtenerTablero() {return ctrlDominio.getTableroPartida();}
}
