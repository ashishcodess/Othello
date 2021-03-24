

package jugador;

import juego.Partida;
import juego.Tablero;

import java.io.*;

public class JugadorPersona extends Jugador {
    /*Atributos*/
    private String nickname;
    private boolean persona_nueva; //persona_nueva -> 1 si no existia el fichero, 0 -> si previamente se ha utilizado dicho usuario

    /*Constructora*/
    public JugadorPersona (int idJugador,String nicknameJugador) throws IOException {
        super(idJugador);
        this.nickname = nicknameJugador;

        //Crear fichero "idJugador_nickname"
        //persona
        this.persona_nueva = crearFicheroUsuario(idJugador,nicknameJugador);
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

    public boolean get_persona_nueva()  {
        return this.persona_nueva;
    }

    /*Devuelve el conjunto formado por nickname y ID -> nickname#ID */
    public String get_ID_TAG_persona() {
        return (this.nickname + "#" + String.valueOf(super.getID()));
    }

    
    /*public Partida Crear_partida(int idPartida, String reglas, int idContrincante, String nickContricante, Tablero tab) {
        //Comprobar si contrincante es maquina o jugador (en caso de ser jugador crear Objecto jugador,...)


        Partida par = new Partida(idPartida);
        if (idContrincante > 5) { //es una persona

            //if (Ranking.existe_Persona(idContrincante,nickContricante)) {}else { //Crear nuevo contrincante}

        } else { //es una maquina
            //JugadorMaquina contrincante= conjuntoMaquinas.getMaquina_i(idContrincante);
        }

        //Partida(idPartida,idContrincante, reglas, );


        //CONFIGURAR PARTIDA


        //INICIAR PARTIDA
        return par;
    }*/


    public void consultar_ranking() {

    }

    //Estas cosas tendrian que ir en la capa de persistencia
    public boolean crearFicheroUsuario(int idJugador,String nicknameJugador) throws IOException{
        String path = "./users/" + idJugador + "_" + nicknameJugador;
        File f = new File(path);
        boolean res = false;
        if (!f.exists()) {
            f.createNewFile();
            res = true;
        }
        return res;
    }

    //Estas cosas tendrian que ir en la capa de persistencia
    public void Cargar_partida(String path_fichero) throws FileNotFoundException {
        FileReader fr = new FileReader (path_fichero);
        BufferedReader contenido=new BufferedReader(fr);


    }

    //Estas cosas tendrian que ir en la capa de persistencia
    public void Guardar_partida(Partida par_guardar) throws IOException {
        int idPartida = par_guardar.getIdPartida();
        String path = "./" + "partida" + String.valueOf(idPartida);
        FileWriter fw = new FileWriter(path);

    }

    public void Finalizar_partida(Partida par) {

    }

}
