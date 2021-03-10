

public class Jugador_Maquina extends Jugador {

    /*Constructora*/
    public Jugador_Maquina (string idMaquina) {
        super(idMaquina);
    }

    /*Sets y Gets*/
    public void modificar_id_maquina(String nuevoID) {
        super.id = nuevoID;
    }


    public String getID_maquina() {
        return super.id;
    }

}
