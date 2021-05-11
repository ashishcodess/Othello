package Presentacion;

import Dominio.CtrlDominio;

import java.util.ArrayList;

public class CtrlPresentacion {

    private CtrlDominio ctrlDominio;

    private VistaRanking vistaRanking = null;
    private VistaLogin vistaLogin= null;
    private VistaMenu vistaMenu = null;
    private VistaCreditos vistaCreditos = null;

    int vistaActiva = 0;


    /**
     * Creadora por defecto de CtrlPresentacion
     * */
    public CtrlPresentacion() {
        ctrlDominio = new CtrlDominio();
        vistaRanking = new VistaRanking(this);
        vistaLogin = new VistaLogin(this);
        vistaMenu = new VistaMenu(this);
        vistaCreditos = new VistaCreditos(this);
    }

    /**
     * inicializa la vista (con la VistaLogin como ventana inicial)
     * */
    public void inicializarPresentacion() {
        hacerVisibleVista(0);
    }


    /**
     * Metodo hacerVisibleVista
     * @param t dependiendo del entero hace visible una vista u otra (para gestion de vistas)
     * */
    public void hacerVisibleVista(int t) {
        switch (t) {
            case 0:
                vistaLogin.hacerVisible(true);
                vistaMenu.hacerVisible(false);
                vistaRanking.hacerVisible(false);
                vistaCreditos.hacerVisible(false);
                break;
            case 1:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(true);
                vistaRanking.hacerVisible(false);
                vistaCreditos.hacerVisible(false);
                break;
            case 2:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(false);
                vistaRanking.hacerVisible(true);
                vistaCreditos.hacerVisible(false);
                break;
            case 3:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(false);
                vistaRanking.hacerVisible(false);
                vistaCreditos.hacerVisible(true);
                break;
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
    public void presentacion_exportar_ranking() {ctrlDominio.domino_exportar_ranking();}

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
     * Metodo consultar ranking (desde Capa Presentacion)
     * @return devuelve en un ArrayList de String la informacion el ranking al completo
     * */
    public ArrayList<String> presentacion_consultar_ranking() {
        ArrayList<String> as = ctrlDominio.consultar_ranking();
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < as.size(); ++i) {
            String[] rs= as.get(i).split(" ");
            for (int j = 0; j < rs.length; ++j) { //length tendria que ser siempre de 6 elementos
                res.add(rs[j]);
            }
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

}
