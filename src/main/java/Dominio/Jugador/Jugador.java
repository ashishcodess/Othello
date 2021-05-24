package Dominio.Jugador;


import Dominio.Partida.Tablero;
import MyException.MyException;

public abstract class Jugador {

    /*Atributos*/

    /**identificador de Jugador*/
    private final int id;

    /**
     * Constructora por defecto de jugador
     */
    public Jugador() {
        this.id = -1;
    }

    /**
     * Constructora de Jugador
     * @param idJugador (id igual a idJugador)
     * @throws Exception lanza excepcion en caso de ID de jugador negativo
     */
    public Jugador (int idJugador) throws Exception{
        if (idJugador < 0) throw new MyException(MyException.tipoExcepcion.ID_NEGATIVO,idJugador);
        this.id = idJugador;
    }


    /*Sets y Gets*/
    /**
     * Operacion get del atributo ID
     * @return devuelve el identificador de Jugador
     */
    public int getID() {
        return id;
    }



    /**
     * Este metodo es el encargado de indicar que ficha colocar dentro de una partida
     * @param turno turno de la partida
     * @param x posicionX valor entre 0 y 8
     * @param y posicionY valor entre 0 y 8
     * @param t Tablero donde se realiza la accion de colocar la ficha
     */
    public void colocar_ficha_en_partida(int turno, int x, int y, Tablero t, int[] reglas) {
        if (t.es_possible(x, y)) {
            t.actualizarTablero(x,y,turno, reglas);
        }
    }

    public abstract String get_Nickname();

    public abstract Tablero posicion(Tablero tablero, int turno);


    public abstract Tablero posicionMaquina(Tablero t, int turno, int[] reglas);
}