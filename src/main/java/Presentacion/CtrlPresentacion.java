package Presentacion;

import Dominio.CtrlDominio;

import java.util.ArrayList;

public class CtrlPresentacion {

    private CtrlDominio ctrlDominio;

    private VistaRanking vistaRanking = null;
    private VistaLogin vistaLogin= null;
    private VistaMenu vistaMenu = null;

    int vistaActiva = 0;


    public CtrlPresentacion() {
        ctrlDominio = new CtrlDominio();
        vistaRanking = new VistaRanking(this);
        vistaLogin = new VistaLogin(this);
        vistaMenu = new VistaMenu(this);
    }



    public void inicializarPresentacion() {
        hacerVisibleVista(0);
    }


    //en caso de que esta idea falle y haya bugs visuales utilizar creadoras de vistas para resetear estados
    public void hacerVisibleVista(int t) {
        switch (t) {
            case 0:
                vistaLogin.hacerVisible(true);
                vistaMenu.hacerVisible(false);
                vistaRanking.hacerVisible(false);
                break;
            case 1:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(true);
                vistaRanking.hacerVisible(false);
                break;
            case 2:
                vistaLogin.hacerVisible(false);
                vistaMenu.hacerVisible(false);
                vistaRanking.hacerVisible(true);
                break;
        }
    }

    public int presentacion_login(int id, String nick) {
        return ctrlDominio.login_inicial_presentacion(id,nick);
    }

    public String presentacion_get_info_usuario_activo() {return ctrlDominio.get_info_usuario_activo();}

    public int presentacion_registro_usuario(String nick) {
        return ctrlDominio.dominio_registro_usuario(nick);
    }

    public ArrayList<String> presentacion_consultar_ranking() {
        ArrayList<String> as = ctrlDominio.consultar_ranking();
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < as.size(); ++i) {
            String[] rs= as.get(i).split(" ");
            for (int j = 0; j < rs.length; ++j) { //length tendria que ser de 6 elementos
                res.add(rs[j]);
            }
        }
        return res;
    }

    public ArrayList<String> presentacion_consultar_estadisticas(int id, String nick) {
        return ctrlDominio.consultar_estadisticas(id,nick);
    }

    public boolean presentacion_ordenar_ranking(int orden) {
        return ctrlDominio.ordenar_ranking(orden);
    }

    public int presentacion_consultar_tam_ranking() {return ctrlDominio.consultar_tam_ranking();}

    public void presentacion_exportar_ranking() {ctrlDominio.domino_exportar_ranking();}


}
