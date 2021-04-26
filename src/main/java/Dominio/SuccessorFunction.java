package Dominio;

import java.util.*;

public class SuccessorFunction { //necessaria para implementar la IA
    Tablero[] succesores;
    private Set<Position> disponibles;

    /**
     * Constructora por defecto
     */

    public SuccessorFunction(){
        this.succesores = new Tablero[0];
        disponibles= new HashSet<Position>();
    }

    /**
     *
     * @param t es el tablero a partir del cual generamos su lista de estados succesores
     * @return retorna la lista de estados hijos de ese tablero, resultantes de cada uno de los posibles movimientos de este
     */
    public Tablero[] genera_succesores(Tablero t){

        return succesores;
    }
}
