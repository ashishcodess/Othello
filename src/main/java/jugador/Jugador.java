package jugador;

import juego.Partida;

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

    /*idPartida tendria que ser objeto main.java.juego.Partida, */

    /**
     * Este metodo es el encargado de indicar que ficha colocar dentro de una partida
     */
    public void Mover_pieza_en_partida(Partida par, int x, int y) {
        //Comprobar que jugador existe en partida y que la casilla esta vacia

        //asegurarse que el jugador esta en la partida
        Boolean b = (par.existeJugador(this.id));
        /*if (b && par.es_movimiento_posible(x,y)) {
            par.colocar_ficha(this.id,x,y);
        }*/
    }

}