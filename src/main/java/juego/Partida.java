package juego;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Partida {
    private int id;
    private int modoDeJuego;
    private String reglas;
    private int turno;
    private String nick1;
    private int idJugador1;
    private String nick2;
    private int idJugador2;
    private Casilla[][] tablero;

    //Creadora - Configuración de los parámetros de una partida
    public Partida (int id, int modoJuego, String r, int turn, int idj1, int idj2) {
        this.id = id;
        this.modoDeJuego = modoJuego;
        this.reglas = r;
        this.turno = turn;
        this.idJugador1 = idj1;
        this.idJugador2 = idj2;
    }

    public Partida(int id, int modoJuego, String r, int turn, int idj1,String n1, int idj2, String n2) {
        this.id = id;
        this.modoDeJuego = modoJuego;
        this.reglas = r;
        this.turno = turn;
        this.nick1 = n1;
        this.idJugador1 = idj1;
        this.nick2 = n2;
        this.idJugador2 = idj2;
    }

    //Devuelve un boleano indicando si el jugador está en la partida
    public Boolean existeJugador(int id){
        return ((idJugador1 == id) || (idJugador2 == id));
    }

    public int getIdPartida() {return this.id;}

    public int getModoDeJuegoPartida() {
        return this.modoDeJuego;
    }

    public String getReglasPartida() {
        return this.reglas;
    }

    public int getTurnoPartida() {
        return this.turno;
    }

    public int getID_J1() {
        return this.idJugador1;
    }

    public int getID_J2() {
        return this.idJugador2;
    }

    public String getNickJugador1() {
        return this.nick1;
    }

    public String getNickJugador2() {
        return this.nick2;
    }

    //Guarda la partida actual y todos sus datos en un fichero
    public void guardarPartida() throws IOException { //Parte Sergio
        int idPartida = this.getIdPartida();
        String path = "./src/files/partidas/" + "partida" + String.valueOf(idPartida) + ".txt";
        File f = new File(path);
        if (f.exists()) f.delete();
        f.createNewFile();

        FileWriter fw = new FileWriter(f);
        fw.write(this.getID_J1() + " " + this.getNickJugador1() + "\n");
        fw.write(this.getID_J2() + " " + this.getNickJugador2() + "\n");
        fw.write(this.getModoDeJuegoPartida() + "\n");
        fw.write(this.getReglasPartida()  + "\n");
        fw.write(this.getTurnoPartida()+ "\n");
        fw.write("\n");

        //ESTO ES UN EJEMPLO HABRIA QUE PASAR EL TABLERO DE LA PARTIDA Y TRADUCIRLO A STRING
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                if ((i==3 && j==4)  || (i==4 && j==3) ) sbuff = sbuff + '1';
                else sbuff = sbuff + '0';
            }
            fw.write(sbuff + "\n");
        }
        fw.close();
    }

    //Imprime el tablero con las casillas disponibles marcadas
    public void generarCasillesDisponibles(){

    }

    //Actualiza el tablero de la partida
    public void actualizarTablero() {
        //this.tablero = Tablero::getTablero();
    }
}