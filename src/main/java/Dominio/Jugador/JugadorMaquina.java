package Dominio.Jugador;

import Dominio.Partida.Position;
import Dominio.Partida.Tablero;
import MyException.MyException;

import java.util.HashSet;
import java.util.Set;

public class JugadorMaquina extends Jugador {

    /*Atributos*/

    /** atributo profundidad (para IA) */
    private int profundidad_MinMax;

    /**atributo estados succesores para la implementación del algoritmo de la IA*/
    private final Set<Tablero> succesores;



    /**
     * Constructora por defecto (vacia) de JugadorMaquina
     * */
    public JugadorMaquina () {
        super();
        this.succesores = new HashSet<>();
    }

    /**
     * Constructora JugadorMaquina (si tener en cuenta la profundidad del arbol de MinMax)
     * @param idMaquina (id de Jugador = idMaquina)
     * */
    public JugadorMaquina (int idMaquina) throws MyException{
        super(idMaquina);
        if (idMaquina > 5)throw new MyException(MyException.tipoExcepcion.ID_PERSONA,idMaquina);
        this.succesores = new HashSet<>();
        this.profundidad_MinMax = (idMaquina+1)*4;
    }

    @Override
    public String get_Nickname() {
        return "";
    }

    /*Sets y Gets*/

    /**
     * Operacion get del atributo ID
     * @return  devuelve el ID de Jugador
     */
    public int get_MaquinaID() { return super.getID(); }

    /**
     * Operacion get del atributo profundidad
     * @return  devuelve el atributo profundidad de JugadorMaquina
     */
    public int get_profundidadMaquina() { return this.profundidad_MinMax; }

    /**
     * Operacion para tirar ficha ejecutando el algoritmo minMax en caso que la maquina sea las fichas negras
     * @param t el tablero de la partida
     * @param turno turno de la partida en ese momento en concreto
     * @param alpha parámetro alfa del algoritmo
     * @param beta parámetro beta del algoritmo
     * @param depth profundidad que nos falta por recorrer
     * devuelve el tablero(estado hijo) resultante de haber ejecutado el turno que nos es más conveniente
     */
    public Tablero valorMaxNegras(Tablero t, int turno, int alpha, int beta, int depth){

        if(depth == 0 || t.finalizada())return t;

        Tablero mejorHijo = t;
        Set<Tablero> estados_hijos = this.genera_estados(t, turno);
        int evaluacion;

        for(Tablero aux : estados_hijos){
            aux = valorMaxNegras(aux,turno+1,alpha, beta, this.get_profundidadMaquina()-1);
            evaluacion = aux.getHeuristicValueNegras();
            if(turno%2 == 0){
                int maxeval = -1000;
                if(maxeval < evaluacion){
                    maxeval = evaluacion;
                    mejorHijo = aux;
                }
            }
            else {
                int mineval = 1000;
                if(mineval > evaluacion){
                    mineval = evaluacion;
                    mejorHijo = aux;
                }
            }

            if(beta<=alpha)break;
        }
        return mejorHijo;
    }

    /**
     * Operacion para tirar ficha ejecutando el algoritmo minMax en caso que la maquina sea las fichas de color blanco
     * @param t el tablero de la partida
     * @param turno turno de la partida en ese momento en concreto
     * @param alpha parámetro alfa del algoritmo
     * @param beta parámetro beta del algoritmo
     * @param depth profundidad que nos falta por recorrer
     * devuelve el tablero(estado hijo) resultante de haber ejecutado el turno que nos es más conveniente
     */
    public Tablero valorMaxBlancas(Tablero t, int turno, int alpha, int beta, int depth){

        if(depth == 0 || t.finalizada()){
            return t;
        }

        Tablero mejorHijo = t;
        Set<Tablero> estados_hijos = this.genera_estados(t, turno);
        int evaluacion;

        for(Tablero aux : estados_hijos){
            aux = valorMaxBlancas(aux,turno+1,alpha, beta, this.get_profundidadMaquina()-1);
             evaluacion = aux.getHeuristicValueBlancas();
            if(turno%2 != 0){
                int maxeval = -1000;
                if(maxeval < evaluacion){
                    maxeval = evaluacion;
                    mejorHijo = aux;
                }
            }
            else{
                int mineval = 1000;
                if(mineval > evaluacion){
                    mineval = evaluacion;
                    mejorHijo = aux;
                }
            }

             if(beta<=alpha)break;
        }
        return mejorHijo;
    }

    @Override
    /**
     * Operacion posicion (funcion temporal para colocar ficha de la IA)
     * @param t tablero a colocar la ficha
     * @param turno heredado de otra funcion (parametro para actualizarTablero)
     * @return devuelve el tablero con la ficha colocada por la IA
     * */
    public Tablero posicion(Tablero t, int turno){

        //Comentad esta parte y descomentad lo otro para poder ejecutar con maquina

        if(turno%2 == 0)t = valorMaxBlancas(t,turno,-100, 100, this.get_profundidadMaquina());
        else t = valorMaxNegras(t,turno,-100, 100, this.get_profundidadMaquina());
        return t;


        /*Set<Position> disponibles = t.getCasillasDisponibles();
        int x = 0;
        int y = 0;
        for(Position aux : disponibles){
             x = aux.getX();
             y = aux.getY();
             break;
        }
        System.out.println("Máquina mueve a " + x + ", " + y);
        t.actualizarTablero(x, y, turno);
        return t;*/
    }

    /**
     * Función que genera los estados succesores del tablero que recibe como parámetro
     * @param t es el tablero a partir del cual generamos su lista de estados succesores
     * @param turno es el turno del tablero t
     * @return retorna la lista de estados succesores de ese tablero, resultantes de cada uno de los posibles movimientos de este
     */
    public Set<Tablero> genera_estados(Tablero t, int turno){

        Tablero aux;

        Set<Position> disponibles = t.getCasillasDisponibles();

        for(Position pos : disponibles){

            aux = t;
            if(turno %2 == 0) aux.setCasilla_tipo(pos.getX(), pos.getY(), 2);
            else aux.setCasilla_tipo(pos.getX(), pos.getY(), 3);
            succesores.add(aux);
        }

        return succesores;
    }
}