package Dominio;

/*
*
* falta hacer IA maquina
*
* */

import MyException.MyException;

import java.util.Set;

public class JugadorMaquina extends Jugador {

    /*Atributos*/

    /** atributo profundidad (para IA) */
    private int profundidad_MinMax;

    /*Constructora*/
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
        if (idMaquina > 5)throw new MyException("El ID:" + idMaquina + " pertenece a una persona o esta fuera de rango(es negativo)");
    }

    @Override
    public String get_Nickname() {
        return "";
    }

    /**
     * Constructora JugadorMaquina (idMaquina, profundidad)
     * @param idMaquina (id de Jugador = idMaquina)
     * @param profundidad (profundidad_IA = profundidad)
     * */
    public JugadorMaquina (int idMaquina, int profundidad) {
        super(idMaquina);
        this.profundidad_MinMax = profundidad;
    }

    /*Sets y Gets*/
    /**
     * Operacion set del atributo ID
     * @param nuevoID indica el nuevo valor que tomara el atributo id
     */
    public void modificar_id_maquina(int nuevoID) { super.modificar_id(nuevoID); } //Aunque para las maquinas creo yo que no es necesario modificar su ID

    /**
     * Operacion set del atributo profundidad_MinMax
     * @param nuevaProfundidad indica el nuevo valor que tomara el atributo profundidad_MinMax
     */
    public void modificar_profundidad(int nuevaProfundidad) {this.profundidad_MinMax = nuevaProfundidad;}

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
     * Operacion para tirar ficha ejecutando el algoritmo minMax
     * @param t el tablero de la partida
     * @param turno turno de la partida en ese momento en concreto
     * @param alpha parámetro alfa del algoritmo
     * @param beta parámetro beta del algoritmo
     * @param depth profundidad que nos falta por recorrer
     * devuelve el tablero(estado hijo) resultante de haber ejecutado el turno que nos es más conveniente
     */
    public Tablero valorMax(Tablero t, int turno, int alpha, int beta, int depth){

        Tablero mejorHijo = t;
        SuccessorFunction succesores = new SuccessorFunction();
        Set<Tablero> estados_hijos = succesores.genera_succesores(t, turno);

        /*if(turno%2 == 0){
            int maxeval = -1000;
            for(Tablero aux : estados_hijos){

            }
        }

        else {
            int mineval = 1000;
            for(Tablero aux : estados_hijos){

            }
        }*/

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

        //t = valorMax(t,turno,-100, 100, this.get_profundidadMaquina());
        //return t;
        Set<Position> disponibles = t.getCasillasDisponibles();
        int x = 0;
        int y = 0;
        for(Position aux : disponibles){
             x = aux.getX();
             y = aux.getY();
             break;
        }
        System.out.println("Máquina mueve a " + x + ", " + y);
        t.actualizarTablero(x, y, turno);
        return t;
    }
}