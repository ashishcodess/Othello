

public class Jugador_Maquina extends Jugador {

    /*Atributos*/
    private String estrategia; //tendria que ser la estrategia/algoritmo (algun tipo objeto o algo, esto es temporal por ahora)

    /*Constructora*/
    public Jugador_Maquina (String idMaquina) {
        super(idMaquina);
    }

    /*Sets y Gets*/
    public void modificar_id_maquina(String nuevoID) {
        super.modificar_id(nuevoID);
    }


    public String getID_maquina() {
        return super.getID();
    }

}
