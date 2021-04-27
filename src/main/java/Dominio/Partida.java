package Dominio;

import ControladorPersistencia.CtrlPersitencia;
import MyException.MyException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Partida {
    /**identificador de Partida*/
    private final int id;
    /**Modo de juego de la Partida*/
    private final int modoDeJuego;
    /**Turno de la Partida*/
    private int turno;
    /**Reglas de la Partida*/
    private final int[] reglas; //array de 2 enteros para las reglas; {1,0} vertical/horizontal; {0,1} diagonales; {1,1} normal
    /**Nick del Jugador1 de la Partida*/
    private String nick1;
    /**ID del Jugador1 de la Partida*/
    private final int idJugador1;
    /**Nick del Jugador2 de la Partida*/
    private String nick2;
    /**ID del Jugador2 de la Partida*/
    private final int idJugador2;
    /**Tablero de la Partida*/
    private Tablero tablero;
    /**Ganador de la Partida*/
    private int ganador; //indica una vez finalizada la partida quien es el ganador (para despues hacer modificacion de ranking)
    //ganador -> -1 (partida sigue en curso),0 (gana nick1), 1 (gana nick2), 2 (empate), 3 (guardar partida), 4 (finalizar)
    private int finalizada;

    /**
     * Creadora - Configuración de los parámetros de una partida nueva
     * @param id es el ID de la Partida
     * @param modoJuego es el modo de juego de la Partida
     * @param r son las reglas de la Partida
     * @param idj1 es el ID del jugador1 de la Partida
     * @param idj2 es el ID del jugador2 de la Partida
     * */
    public Partida (int id, int modoJuego, int[] r, int idj1, int idj2) {
        this.id = id;
        this.modoDeJuego = modoJuego;
        this.reglas = r;
        this.turno = 0;
        this.idJugador1 = idj1;
        this.idJugador2 = idj2;
        this.ganador = -1;
        this.tablero = new Tablero();
        this.finalizada = 0;
    }

    /**
     * Creadora - Configuración de los parámetros de una partida ya empezada
     * @param id es el ID de la Partida
     * @param modoJuego es el modo de juego de la Partida
     * @param r son las reglas de la Partida
     * @param turn es el turno de la Partida
     * @param idj1 es el ID del jugador1 de la Partida
     * @param n1 es el nick del jugador1 de la Partida
     * @param idj2 es el ID del jugador2 de la Partida
     * @param n2 es el nick del jugador2 de la Partida
     * @param t es el tablero de la Partida
     * */
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
        this.finalizada = 0;
    }

    /**
     * Devuelve un boleano indicando si el jugador está en la partida
     * @return retorna un bool indicando si el jugador con ID id participa en la Partida
     * */
    public Boolean existeJugador(int id){
        return ((idJugador1 == id) || (idJugador2 == id));
    }

    /**
     * Operacion get del atributo ID de Partida
     * @return retorna un int con el ID de la Partida
     */
    public int getIdPartida() {return this.id;}

    /**
     * Operacion get del atributo modoDeJuego de Partida
     * @return retorna un int con el modo de juego de la Partida
     */
    public int getModoDeJuegoPartida() {
        return this.modoDeJuego;
    }

    /**
     * Operacion get del atributo reglas de Partida
     * @return retorna un array de int con las reglas de la Partida
     */
    public int[] getReglasPartida() {
        return this.reglas;
    }

    /**
     * Operacion get del atributo turno de Partida
     * @return retorna un int con el turno de la Partida
     */
    public int getTurnoPartida() {
        return this.turno;
    }

    /**
     * Operacion get del atributo ID del Jugador1 de Partida
     * @return retorna un int con el ID del Jugador1 de la Partida
     */
    public int getID_J1() {
        return this.idJugador1;
    }

    /**
     * Operacion get del atributo ID del Jugador2 de Partida
     * @return retorna un int con el ID del Jugador2 de la Partida
     */
    public int getID_J2() {
        return this.idJugador2;
    }

    /**
     * Operacion get del atributo nick del Jugador1 de Partida
     * @return retorna un String con el nick del Jugador1 de la Partida
     */
    public String getNickJugador1() {
        return this.nick1;
    }

    /**
     * Operacion get del atributo nick del Jugador2 de Partida
     * @return retorna un String con el nick del Jugador2 de la Partida
     */
    public String getNickJugador2() {
        return this.nick2;
    }

    /**
     * Operacion get del atributo tablero de Partida
     * @return retorna un tablero de la Partida
     */
    public Tablero getTableroPartida() {
        return this.tablero;
    }

    /**
     * Operacion set del atributo tablero de Partida
     * @param t es el tablero a actualizar en Partida
     */
    public void setTableroPartida(Tablero t) { this.tablero = t; }

    /**
     * Operacion get del atributo ganador de Partida
     * @return retorna un int que define el ganador
     */
    public int getGanador() {return this.ganador;}

    /**
     * Operacion get del atributo finalizada de Partida
     * @return retorna un int que define si la partida ha finalizado
     */
    protected int getFinalizada() { return this.finalizada; }

    /**
     * Operacion que incrementa el atributo turno de Partida
     */
    public void incrementarTurnoPartida() {this.turno = this.turno + 1;}

    /**
     * Operacion set del atributo ganador de Partida
     * @param i indica el ganador de la partida de la siguiente manera:
     *          ganador : 0 (gana Jugador1), 1 (gana Jugador2), 2 (empate)
     */
    public void setGanador(int i) {this.ganador = i;}


    /**
     * Operacion que gestiona toda una ronda de la Partida
     * @param accion indica la acción que el jugador quiere realizar en su turno:
     * @return retorna un int con el ganador de la partida o -1 si la partida no ha acabado todavia
     */
    public int rondaPartida(String[] accion) {
        if (finalizada == 2) {
            comprobarPartidaFinalizada();
            return 3; //si hay dos turnos sin poder mover ningun jugador, la partida se acaba.
        }
        else {
            reglasCasillasDisponibles();
            Set<Position> disponibles = this.tablero.getCasillasDisponibles();
            Object[] arr = disponibles.toArray();
            System.out.println("Movimientos disponibles:");
            for (int i = 0 ; i < arr.length ; ++i){
                Position P = (Position)arr[i];
                System.out.println("x:" + P.getX() + "y:" + P.getY());
            }
            int disp = disponibles.size();
            if (this.turno % 2 == 0) {
                switch (accion[0]){
                    case "colocar":
                        int x = Integer.parseInt(accion[1]);
                        int y = Integer.parseInt(accion[2]);
                        //como se actualizan las fichas que se convierten con tu movimiento?
                        //como se actualizan las fichas que antes se han puesto a disponible para que vuelvan a estar simplemente vacias?
                        this.tablero.actualizarTablero(x, y, this.turno);
                        incrementarTurnoPartida();
                        this.finalizada = 0;
                        //Esto habría que hacerlo una vez llegado al ultimo turno/final de la partida
                        /*
                        else { this.ganador = -1;}*/
                        break;
                    case "guardar": //guardarPartida
                        return 2;
                    case "finalizar": //finalizarPartida
                        return 3;
                    case "paso":
                        if (disp == 0) ++this.finalizada;
                        incrementarTurnoPartida();
                        break;
                }
            }
            else if (this.turno % 2 != 0) {
                switch (accion[0]) {
                    case "colocar":
                        int x = Integer.parseInt(accion[1]);
                        int y = Integer.parseInt(accion[2]);
                        //como se actualizan las fichas que se convierten con tu movimiento?
                        //como se actualizan las fichas que antes se han puesto a disponible para que vuelvan a estar simplemente vacias?
                        this.tablero.actualizarTablero(x, y, this.turno);
                        incrementarTurnoPartida();
                        actualizarTablero();
                        this.finalizada = 0;
                        //Esto habría que hacerlo una vez llegado al ultimo turno/final de la partida
                        /*
                        else { this.ganador = -1;}*/
                        break;
                    case "guardar": //guardarPartida
                        return 2;
                    case "finalizar": //finalizarPartida
                        return 3;
                    case "paso":
                        if (disp == 0) ++this.finalizada;
                        incrementarTurnoPartida();
                        break;
                }
            }
        }
        return this.ganador;
    }

    /**
     * Operacion que utiliza las reglas de Partida para generar las casillas disponibles
     */
    private void reglasCasillasDisponibles() {
        if (this.reglas[0] == 1) {
            this.tablero.calcularCasillasDisponiblesVertical(this.turno);
        }
        if (this.reglas[1] == 1) {
            this.tablero.calcularCasillasDisponiblesHorizontal(this.turno);
        }
        if (this.reglas[2] == 1) {
            this.tablero.calcularCasillasDisponiblesDiagonales(this.turno);
        }
    }

    /**
     * Operacion que comprueba si la Partida ha finalizado y genera el ganador
     */
    private void comprobarPartidaFinalizada() {
        if (this.tablero.getNumCasillasBlancas() > this.tablero.getNumCasillasNegras() || this.tablero.getNumCasillasNegras() == 0) {
            setGanador(1);
        } else if ((this.tablero.getNumCasillasBlancas() < this.tablero.getNumCasillasNegras()) || this.tablero.getNumCasillasBlancas() == 0) {
            setGanador(0);
        } else if (this.tablero.getNumCasillasBlancas() == this.tablero.getNumCasillasNegras()) {
            setGanador(2);
        }
    }

    /**
     * Operacion que actualiza el tablero de la partida
     */
    public void actualizarTablero() {
        this.tablero.getTablero();
    }


    /*
    public void actualizarRanking(Ranking r) throws MyException {
        r.incrementar_ganadas_perdidas(this.idJugador1, this.nick1,this.idJugador2, this.nick2, this.ganador);
    }
    */

    /**
     * Operacion que imprime por pantalla el tablero de la partida
     */
    public void print_Tablero() {
        if (this.tablero != null) {
            for (int i = 0; i < 8; ++i) {
                String sbuff = new String();
                for (int j = 0; j < 8; ++j) {
                    sbuff = sbuff + this.tablero.getCasilla_tipo(i,j);
                }
                System.out.println(sbuff);
            }
            System.out.println();
        }
    }

    /**
     * Operacion que imprime por pantalla toda la informacion de la partida
     */
    public void get_info_partida() {
        System.out.println();
        System.out.println("IDpartida:" + this.id);
        System.out.println("Jugador1 (ID,nick): " +  this.idJugador1 + " " + this.nick1);
        System.out.println("Jugador2 (ID,nick): " +  this.idJugador2 + " " + this.nick2);
        System.out.println("Modo de juego: " +  this.modoDeJuego);
        System.out.println("Reglas(v,h,d): " +  this.reglas[0] + this.reglas[1] + this.reglas[2]);
        System.out.println("Turno: " +  this.turno);
        System.out.println();
        System.out.println("Tablero:");
        print_Tablero();
        System.out.println();
    }

    /**
     * Operacion que guarda toda la info en un ArrayList de String para tratamiento con CtrlPersitencia
     */
    public ArrayList<String> toArrayList() {
        ArrayList<String> as = new ArrayList<String>();
        as.add(String.valueOf(this.id));
        String s =this.idJugador1 + " " + this.nick1;
        as.add(s);
        s =this.idJugador2 + " " + this.nick2;
        as.add(s);
        s = String.valueOf(this.modoDeJuego);
        as.add(s);
        s = (this.reglas[0] + " " + this.reglas[1] + " " +this.reglas[2]);
        as.add(s);
        s = String.valueOf(this.turno);
        as.add(s);
        as.add("");
        //parte del tablero
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + this.tablero.getCasilla_tipo(i,j);
            }
            as.add(sbuff);
        }
        return as;
    }
}


