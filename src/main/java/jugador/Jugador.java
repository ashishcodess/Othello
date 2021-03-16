package jugador;
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
    public void Mover_pieza_en_partida(String idPartida, int x, int y) {
        //Comprobar que jugador existe en partida y que la casilla esta vacia

    }

    /*idPartida tendria que ser objeto juego.Partida, */
    public void Pasar_turno_en_partida(String idPartida) {

    }

}