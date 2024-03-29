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
     * @throws Exception en caso de que idMaquina pertenezca a una Persona (id mayor que 5)
     * */
    public JugadorMaquina (int idMaquina) throws Exception{
        super(idMaquina);
        if (idMaquina > 5)throw new MyException(MyException.tipoExcepcion.ID_PERSONA,idMaquina);
        this.profundidad_MinMax = (idMaquina)*2-1;
    }

    @Override
    public String get_Nickname() {
        return "";
    }

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
     * @param reglas reglas de la partida
     * @return devuelve el tablero(estado hijo) resultante de haber ejecutado el turno que nos es más conveniente
     */
    public Tablero valorMaxNegras(Tablero t, int turno, int alpha, int beta, int depth, int[] reglas){

        if(depth == 0 || t.finalizada())return t;

        Tablero mejorHijo = new Tablero(t.getTablero());
        mejorHijo.setDisponiblesAnterior(t.getDisponiblesAnterior());
        Set<Tablero> estados_hijos = this.genera_estados(t, turno, reglas, depth-1);
        int evaluacion;
        int mineval = 1000;
        int maxeval = -1000;

        for(Tablero aux : estados_hijos){
            Tablero it = new Tablero(valorMaxNegras(aux,turno+1, alpha, beta, depth-1, reglas).getTablero());
            evaluacion = it.getHeuristicValueNegras();

            if(turno%2 == 0){   //Jugador Negro
                if(maxeval < evaluacion){
                    maxeval = evaluacion;
                    mejorHijo = new Tablero(aux.getTablero());
                    mejorHijo.setDisponiblesAnterior(aux.getDisponiblesAnterior());
                    if(alpha < evaluacion)alpha = evaluacion;
                }
            }
            else {      //Jugador Blanco
                if(mineval > evaluacion){
                    mineval = evaluacion;
                    mejorHijo = new Tablero(aux.getTablero());
                    mejorHijo.setDisponiblesAnterior(aux.getDisponiblesAnterior());
                    if(beta > evaluacion)beta = evaluacion;
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
     * @param reglas reglas de la partida
     * @return devuelve el tablero(estado hijo) resultante de haber ejecutado el turno que nos es más conveniente
     */
    public Tablero valorMaxBlancas(Tablero t, int turno, int alpha, int beta, int depth, int[] reglas){

        if(depth == 0 || t.finalizada())return t;

        Tablero mejorHijo = new Tablero(t.getTablero());
        mejorHijo.setDisponiblesAnterior(t.getDisponiblesAnterior());

        Set<Tablero> estados_hijos = this.genera_estados(t, turno, reglas, depth-1);
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
                    if(alpha < evaluacion)alpha = evaluacion;
                }
            }
            else{
                if(mineval > evaluacion){
                    mineval = evaluacion;
                    mejorHijo = new Tablero(aux.getTablero());
                    mejorHijo.setDisponiblesAnterior(aux.getDisponiblesAnterior());
                    if(beta > evaluacion)beta = evaluacion;
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
    @Override
    public Tablero posicionMaquina(Tablero t, int turno, int[] reglas){

        if(this.getID()==0){
            Set<Position> disponibles = t.getCasillasDisponibles();
            int x = 0;
            int y = 0;
            for(Position aux : disponibles){
                x = aux.getX();
                y = aux.getY();
                break;
            }
            //System.out.println("Máquina mueve a " + x + ", " + y);
            t.actualizarTablero(x, y, turno, reglas);
        }
        else{
            if(turno%2 == 0)t = valorMaxBlancas(t,turno, -1000, 1000, this.get_profundidadMaquina(), reglas);
            else t = valorMaxNegras(t,turno,-100, 100, this.get_profundidadMaquina(), reglas);
        }
        return t;

    }

    /**
     * Función que genera los estados sucesores del tablero que recibe como parámetro
     * @param t es el tablero a partir del cual generamos su lista de estados sucesores
     * @param turno es el turno del tablero t
     * @param reglas reglas de la partida
     * @param depth profundidad de la IA
     * @return retorna la lista de estados sucesores de ese tablero, resultantes de cada uno de los posibles movimientos de este
     */
    public Set<Tablero> genera_estados(Tablero t, int turno, int[] reglas, int depth){

        Tablero aux;
        Set<Tablero> sucesores = new HashSet<>();

        Set<Position> disponibles = t.getCasillasDisponibles();

        for(Position pos : disponibles){

            aux = new Tablero(t.getTablero());
            aux.setDisponiblesAnterior(t.getDisponiblesAnterior());

            aux.actualizarTablero(pos.getX(), pos.getY(), turno, reglas);

            if(depth > 0){
                if(reglas[0] == 1)aux.calcularCasillasDisponiblesVertical(turno+1);
                if(reglas[1] == 1)aux.calcularCasillasDisponiblesHorizontal(turno+1);
                if(reglas[2] == 1)aux.calcularCasillasDisponiblesDiagonales(turno+1);
            }

            sucesores.add(aux);
        }

        return sucesores;
    }
}