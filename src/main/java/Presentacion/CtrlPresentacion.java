package Presentacion;

import Dominio.CtrlDominio;

import java.util.ArrayList;

public class CtrlPresentacion {

    private CtrlDominio ctrlDominio;

    private VistaPrincipal vistaPrincipal = null;
    private VistaRanking vistaRanking = null;
    private VistaLogin vistaLogin= null;
    private VistaMenu vistaMenu = null;


    public CtrlPresentacion() {
        ctrlDominio = new CtrlDominio();
        //vistaPrincipal = new VistaPrincipal(this);
        vistaRanking = new VistaRanking(this);
        vistaLogin = new VistaLogin(this);
        vistaMenu = new VistaMenu(this);
    }


    public void inicializarPresentacion() {
        //ctrlDominio.inicializarCtrlDominio();
        //vistaPrincipal.hacerVisible();
    }

    public int presentacion_login(int id, String nick) {
        return ctrlDominio.login_inicial_presentacion(id,nick);
    }

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
