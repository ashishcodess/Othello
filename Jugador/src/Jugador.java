

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

}


