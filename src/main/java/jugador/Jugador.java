package jugador;

import juego.Partida;

public class Jugador {

    /*Atributos*/
    private int id;

    /*Constructora*/
    public Jugador (int idJugador) {
        this.id = idJugador;
    }

    /*Sets y Gets*/
    public void modificar_id(int nuevoID) {
        this.id = nuevoID;
    }

    public int getID() {
        return id;
    }

    /*idPartida tendria que ser objeto juego.Partida, */
    public void Mover_pieza_en_partida(Partida par, int x, int y) {
        //Comprobar que jugador existe en partida y que la casilla esta vacia

        //asegurarse que el jugador esta en la partida
        Boolean b = (par.existeJugador(this.id));
        if (b && par.es_movimiento_posible(x,y)) {
            par.colocar_ficha(this.id,x,y);
        }
    }

}