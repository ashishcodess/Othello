package Dominio;

import java.util.*;

public class SuccessorFunction { //necessaria para implementar la IA
    private Set<Tablero> succesores;
    private Set<Position> disponibles;

    /**
     * Constructora por defecto
     */

    public SuccessorFunction(){
        this.succesores = new HashSet<Tablero>();
        Set<Position> disponibles= new HashSet<Position>();
    }

    /**
     * Función que genera los estados succesores del tablero que recibe como parámetro
     * @param t es el tablero a partir del cual generamos su lista de estados succesores
     * @param turno es el turno del tablero t
     * @return retorna la lista de estados hijos de ese tablero, resultantes de cada uno de los posibles movimientos de este
     */
    public Set<Tablero> genera_succesores(Tablero t, int turno){

        Tablero aux; Casilla disponible;
        t.calcularCasillasDisponiblesDiagonales(turno);
        t.calcularCasillasDisponiblesHorizontal(turno);
        t.calcularCasillasDisponiblesVertical(turno);
        disponibles = t.getCasillasDisponibles();

        for(Position pos : disponibles){

            aux = t;
            if(turno %2 == 0) aux.setCasilla_tipo(pos.getX(), pos.getY(), 2);
            else aux.setCasilla_tipo(pos.getX(), pos.getY(), 3);
            succesores.add(aux);
        }

        return succesores;
    }
}
