
/*Una persona se representa como: id#TAG
* TAG es un identificador unico, por lo que no se puede cambiar de TAG pero si de id
*
* El TAG inicial sera inicial de 1 (a medida que vayamos creando personas habra que incremetar dicho TAG)
*/

public class Jugador_Persona extends Jugador {
    /*Atributos*/
    private int TAG;

    /*Constructora*/
    public Jugador_Persona (string idJugador,int TAG_jugador) {
        super(idJugador);
        this.TAG = TAG_jugador;
    }

    /*Sets y Gets*/
    public void modificar_id_persona(String nuevoID) {
        super.id = nuevoID;
    }

    public String getTAG_persona() {
        return this.TAG;
    }

    public String getID_persona() {
        return super.id;
    }

    /*Devuelve el conjunto formado por ID y TAG -> id#TAG */
    public String get_ID_TAG_persona() {
        return (super.id + "#" + String.valueOf(this.TAG));
    }

}
