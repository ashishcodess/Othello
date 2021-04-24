package Dominio;

import Dominio.Partida;

public class Jugador {

    /*Atributos*/
    private int id;

    /**
     * Constructora por defecto de jugador
     */
    public Jugador() {
        this.id = -1;
    }

    /**
     * Constructora de Jugador
     */
    public Jugador (int idJugador) {
        this.id = idJugador;
    }



    /*Sets y Gets*/
    /**
     * Operacion set del atributo ID
     * @param nuevoID indica el valor que tomara el atributo id
     */
    public void modificar_id(int nuevoID) {
        this.id = nuevoID;
    }

    /*Sets y Gets*/
    /**
     * Operacion get del atributo ID
     */
    public int getID() {
        return id;
    }



    /**
     * Este metodo es el encargado de indicar que ficha colocar dentro de una partida
     */

    public boolean Mover_pieza_en_partida(int tipo, int x, int y, Tablero t) {
        if (t.es_possible(x, y)) {
            t.setCasilla_tipo(x,y,tipo);
            return true;
        }
        else return false;
    }
}