package juego;

import MyException.MyException;
import jugador.Ranking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Partida {
    private final int id;
    private final int modoDeJuego;
    private final int turno;
    private final int[] reglas; //array de 3 enteros para las reglas
    private String nick1;
    private final int idJugador1;
    private String nick2;
    private final int idJugador2;
    private Tablero tablero;
    private int ganador; //indica una vez finalizada la partida quien es el ganador (para despues hacer modificacion de ranking)

    //Creadora - Configuración de los parámetros de una partida
    public Partida (int id, int modoJuego, int[] r, int turn, int idj1, int idj2) {
        this.id = id;
        this.modoDeJuego = modoJuego;
        this.reglas = r;
        this.turno = turn;
        this.idJugador1 = idj1;
        this.idJugador2 = idj2;
        this.ganador = -1;
    }

    public Partida(int id, int modoJuego, int[] r, int turn, int idj1,String n1, int idj2, String n2, Tablero t) {
        this.id = id;
        this.modoDeJuego = modoJuego;
        this.reglas = r;
        this.turno = turn;
        this.nick1 = n1;
        this.idJugador1 = idj1;
        this.nick2 = n2;
        this.idJugador2 = idj2;
        this.ganador = -1;
        this.tablero = t;
    }

    //Devuelve un boleano indicando si el jugador está en la partida
    public Boolean existeJugador(int id){
        return ((idJugador1 == id) || (idJugador2 == id));
    }

    public int getIdPartida() {return this.id;}

    public int getModoDeJuegoPartida() {
        return this.modoDeJuego;
    }

    public int[] getReglasPartida() {
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



    // ganador -> 0 (gana Jugador1), 1 (gana Jugador2), 2 (empate)
    public void setGanador(int i) {this.ganador = i;}

    public int getGanador() {return this.ganador;}


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
                sbuff = sbuff + this.tablero.getCasilla_tipo(i,j);
                //if ((i==3 && j==4)  || (i==4 && j==3) ) sbuff = sbuff + '1';
                //else sbuff = sbuff + '0';
            }
            fw.write(sbuff + "\n");
        }
        fw.close();
    }

    //Imprime el tablero con las casillas disponibles marcadas
    public void generarCasillesDisponibles(){

    }

    public int rondaPartida() {
        /* mostrar fichas disponibles jugador
            opciones del jugador(colocar ficha, guardar partida, finalizar, pasar turno)
            colocar ficha
            actualizar tablero
            contar fichas
            retornar valor que indique si la partida ha acabado o no (no hay más espacios en el tablero o se ha
            llegado al turno máximo)
         */
        return this.ganador;
    }

    //Actualiza el tablero de la partida
    public void actualizarTablero() {
        //this.tablero = Tablero::getTablero();
    }

    /*
     * Sergio Aguado: esto es como se haria el actualizar Ranking desde partida, pero veo innecesario si esta gestion la puede hacer directamente
     * el controlador de juego (o en nuestro caso el Main)
     *
     * De la manera en como esta en la parte de abajo no compila (no podemos utilizar funciones de una clase cuyo objeto no hemos declarado)
     * */
    public void actualizarRanking(Ranking r) throws MyException {
        r.incrementar_ganadas_perdidas(this.idJugador1, this.nick1,this.idJugador2, this.nick2, this.ganador);
    }


    //Actualiza el ranking con el resultado de la partida
    /*
    public void actualizarRanking(boolean ganadorj1, boolean ganadorj2){
        if (ganadorj1 && !ganadorj2){
            ElementoRanking::incrementar_partida_ganada (String nick1);
            ElementoRanking::incrementar_partida_perdida(String nick2);
        }
        else if (!ganadorj1 && ganadorj2){
            ElementoRanking::incrementar_partida_ganada(String nick2);
            ElementoRanking::incrementar_partida_perdida(String nick1);
        }
        else {
            ElementoRanking::incrementar_partida_empatada(String nick1);
            ElementoRanking::incrementar_partida_empatada(String nick2);
        }
    }*/
}