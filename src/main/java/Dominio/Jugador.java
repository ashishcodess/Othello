package Dominio;


public abstract class Jugador {

    /*Atributos*/

    /**identificador de Jugador*/
    private int id;

    /**
     * Constructora por defecto de jugador
     */
    public Jugador() {
        this.id = -1;
    }

    /**
     * Constructora de Jugador
     * @param idJugador (id igual a idJugador)
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
     * @return devuelve el identificador de Jugador
     */
    public int getID() {
        return id;
    }



    /**
     * Este metodo es el encargado de indicar que ficha colocar dentro de una partida
     * @param turno turno de la partida
     * @param x posicionX valor entre 0 y 8
     * @param y posicionY valor entre 0 y 8
     * @param t Tablero donde se realiza la accion de colocar la ficha
     * @return True en caso haber colocado correctamente la ficha, caso contrario devuelve FALSO
     */
    public boolean colocar_ficha_en_partida(int turno, int x, int y, Tablero t) {
        if (t.es_possible(x, y)) {
            t.actualizarTablero(x,y,turno);
            return true;
        }
        else return false;
    }

    public abstract String get_Nickname();

    public abstract Tablero posicion(Tablero tablero, int turno);
}