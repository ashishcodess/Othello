package Presentacion;

import Dominio.CtrlDominio;

import java.util.ArrayList;

public class CtrlPresentacion {

    private CtrlDominio ctrlDominio;

    private VistaPrincipal vistaPrincipal = null;
    private VistaRanking vistaRanking = null;


    public CtrlPresentacion() {
        ctrlDominio = new CtrlDominio();
        //vistaPrincipal = new VistaPrincipal(this);
        vistaRanking = new VistaRanking(this);
    }


    public void inicializarPresentacion() {
        //ctrlDominio.inicializarCtrlDominio();
        //vistaPrincipal.hacerVisible();
    }


    public ArrayList<String> presentacion_consultar_ranking() {
        return ctrlDominio.consultar_ranking();
    }

    public ArrayList<String> presentacion_consultar_estadisticas(int id, String nick) {
        return ctrlDominio.consultar_estadisticas(id,nick);
    }



}
