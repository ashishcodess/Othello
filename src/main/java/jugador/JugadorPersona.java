

package jugador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class JugadorPersona extends Jugador {
    /*Atributos*/
    private String nickname;

    /*Constructora*/
    public JugadorPersona (int idJugador,String nicknameJugador) {
        super(idJugador);
        this.nickname = nicknameJugador;
    }

    /*Sets y Gets*/


    public void modificar_IDJugador(int nuevoID) {
        super.modificar_id(nuevoID);
    }

    public void modificar_nickname(String nuevoNick) {
        this.nickname = nuevoNick;
    }

    public String get_Nickname() {
        return this.nickname;
    }

    public int get_PersonaID() {
        return super.getID();
    }

    /*Devuelve el conjunto formado por nickname y ID -> nickname#ID */
    public String get_ID_TAG_persona() {
        return (this.nickname + "#" + String.valueOf(super.getID()));
    }


    /*crea un objeto juego.Partida con las reglas y el contricante establecidos
    Englobaria a los casos de uso: Iniciar juego.Partida y Configurar juego.Partida
    */
    public void Crear_partida(int idPartida, int idContrincante, String reglas) {
        /*Comprobar si contrincante es maquina o jugador (en caso de ser jugador crear Objecto jugador,...)*/

        //Partida(idPartida,idContrincante, reglas, );


        //CONFIGURAR PARTIDA


        //INICIAR PARTIDA
    }

    public void consultar_ranking() {

    }


    public void Cargar_partida(String fichero) throws FileNotFoundException {
        //FileReader fr = new FileReader (fichero);
        //BufferedReader contenido=new BufferedReader(fr);

    }

    public void Guardar_partida(int idPartida) {
        //String path = "./" + "partida" + String.valueOf(idPartida);
        //FileWriter fw = new FileWriter(path);
    }

    public void Finalizar_partida() {

    }



}
