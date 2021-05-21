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

    /**
     * Constructora por defecto (vacia) de JugadorMaquina
     * */
    public JugadorMaquina () {
        super();
    }

    /**
     * Constructora JugadorMaquina (si tener en cuenta la profundidad del arbol de MinMax)
     * @param idMaquina (id de Jugador = idMaquina)
     * */
    public JugadorMaquina (int idMaquina) throws MyException{
        super(idMaquina);
        if (idMaquina > 5)throw new MyException(MyException.tipoExcepcion.ID_PERSONA,idMaquina);
        this.profundidad_MinMax = (idMaquina+1)*2;
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
    public Tablero valorMaxNegras(Tablero t, int turno, int alpha, int beta, int depth, int[] reglas){

        if(depth == 0 || t.finalizada())return t;

        Tablero mejorHijo = new Tablero(t.getTablero());
        mejorHijo.setDisponiblesAnterior(t.getDisponiblesAnterior());
        Set<Tablero> estados_hijos = this.genera_estados(t, turno, reglas);
        int evaluacion;
        int mineval = 1000;
        int maxeval = -1000;

        for(Tablero aux : estados_hijos){
            Tablero it = new Tablero(valorMaxNegras(aux,turno+1,alpha, beta, depth-1, reglas).getTablero());
            evaluacion = it.getHeuristicValueNegras();
            if(turno%2 == 0){
                if(maxeval < evaluacion){
                    maxeval = evaluacion;
                    mejorHijo = new Tablero(aux.getTablero());
                    mejorHijo.setDisponiblesAnterior(aux.getDisponiblesAnterior());
                }
            }
            else {
                if(mineval > evaluacion){
                    mineval = evaluacion;
                    mejorHijo = new Tablero(aux.getTablero());
                    mejorHijo.setDisponiblesAnterior(aux.getDisponiblesAnterior());
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
    public Tablero valorMaxBlancas(Tablero t, int turno, int alpha, int beta, int depth, int[] reglas){

        if(depth == 0 || t.finalizada()){
            return t;
        }

        Tablero mejorHijo = new Tablero(t.getTablero());
        mejorHijo.setDisponiblesAnterior(t.getDisponiblesAnterior());

        Set<Tablero> estados_hijos = this.genera_estados(t, turno, reglas);
        int evaluacion;
        int maxeval = -1000;
        int mineval = 1000;

        for(Tablero aux : estados_hijos){
            Tablero it = new Tablero(valorMaxNegras(aux,turno+1,alpha, beta, depth-1, reglas).getTablero());
            evaluacion = it.getHeuristicValueBlancas();
            if(turno%2 != 0){
                if(maxeval < evaluacion){
                    maxeval = evaluacion;
                    mejorHijo = new Tablero(aux.getTablero());
                    mejorHijo.setDisponiblesAnterior(aux.getDisponiblesAnterior());
                }
            }
            else{
                if(mineval > evaluacion){
                    mineval = evaluacion;
                    mejorHijo = new Tablero(aux.getTablero());
                    mejorHijo.setDisponiblesAnterior(aux.getDisponiblesAnterior());
                }
            }

             if(beta<=alpha)break;
        }
        return mejorHijo;
    }

    public Tablero posicion(Tablero tablero, int turno){
        return tablero;
    }

    /**
     * Operacion posicion (funcion temporal para colocar ficha de la IA)
     * @param t tablero a colocar la ficha
     * @param turno heredado de otra funcion (parametro para actualizarTablero)
     * @param reglas reglas del juego
     * @return devuelve el tablero con la ficha colocada por la IA
     * */
    public Tablero posicionMaquina(Tablero t, int turno, int[] reglas){

        //Comentad esta parte y descomentad lo otro para poder ejecutar con maquina

        if(turno%2 == 0)t = valorMaxBlancas(t,turno,-100, 100, this.get_profundidadMaquina(), reglas);
        else t = valorMaxNegras(t,turno,-100, 100, this.get_profundidadMaquina(), reglas);
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
    public Set<Tablero> genera_estados(Tablero t, int turno, int[] reglas){

        Tablero aux;
        Set<Tablero> succesores = new HashSet<>();

        Set<Position> disponibles = t.getCasillasDisponibles();

        for(Position pos : disponibles){

            aux = new Tablero(t.getTablero());
            aux.setDisponiblesAnterior(t.getDisponiblesAnterior());
            aux.printTablero();

            aux.actualizarTablero(pos.getX(), pos.getY(), turno);
            if(reglas[0] == 1)aux.calcularCasillasDisponiblesVertical(turno+1);
            if(reglas[1] == 1)aux.calcularCasillasDisponiblesHorizontal(turno+1);
            if(reglas[2] == 1)aux.calcularCasillasDisponiblesDiagonales(turno+1);

            aux.printTablero();
            succesores.add(aux);
        }

        return succesores;
    }
}