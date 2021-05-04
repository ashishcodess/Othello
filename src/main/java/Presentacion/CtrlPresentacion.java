package Presentacion;

import Dominio.CtrlDominio;

public class CtrlPresentacion {

    private CtrlDominio ctrlDominio;

    private VistaPrincipal vistaPrincipal = null;


    public CtrlPresentacion() {
        ctrlDominio = new CtrlDominio();
        vistaPrincipal = new VistaPrincipal(this);
    }


    /*public void inicializarPresentacion() {
        ctrlDominio.inicializarCtrlDominio();
        vistaPrincipal.hacerVisible();
    }*/


}
