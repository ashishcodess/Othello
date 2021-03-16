package jugador;

public class JugadorPersona extends Jugador {
    /*Atributos*/
    private int TAG;

    /*Constructora*/
    public JugadorPersona (String idJugador,int TAG_jugador) {
        super(idJugador);
        this.TAG = TAG_jugador;
    }

    /*Sets y Gets*/
    public void modificar_id_persona(String nuevoID) {
        super.modificar_id(nuevoID);
    }

    public int getTAG_persona() {
        return this.TAG;
    }

    public String getID_persona() {
        return super.getID();
    }

    /*Devuelve el conjunto formado por ID y TAG -> id#TAG */
    public String get_ID_TAG_persona() {
        return (super.getID() + "#" + String.valueOf(this.TAG));
    }


    /*crea un objeto juego.Partida con las reglas y el contricante establecidos
    Englobaria a los casos de uso: Iniciar juego.Partida y Configurar juego.Partida
    */
    public void Crear_partida(String idContrincante, String reglas) {
        /*Comprobar si contrincante es maquina o jugador (en caso de ser jugador crear Objecto jugador,...)*/


        //CONFIGURAR PARTIDA


        //INICIAR PARTIDA
    }

    public void consultar_ranking() {

    }

    public void Guardar_partida() {

    }

    public void Finalizar_partida() {

    }

    public void Finalizar_sinGuardar_partida() {

    }


}
