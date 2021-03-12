

public class Jugador {

    /*Atributos*/
    private String id;

    /*Constructora*/
    public Jugador (String idJugador) {
        this.id = idJugador;
    }

    /*Sets y Gets*/
    public void modificar_id(String nuevoID) {
        this.id = nuevoID;
    }

    public String getID() {
        return id;
    }

    /*idPartida tendria que ser objeto Partida, */
    public void Mover_pieza_en_partida(String idPartida, int x, int y) {
        //Comprobar que jugador existe en partida y que la casilla esta vacia

    }

    /*idPartida tendria que ser objeto Partida, */
    public void Pasar_turno_en_partida(String idPartida) {

    }

}


