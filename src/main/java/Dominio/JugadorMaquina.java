package Dominio;

/*
*
* falta hacer IA maquina
*
* */

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
    public JugadorMaquina (int idMaquina) {
        super(idMaquina);
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
        Tablero[] estados_hijos = succesores.genera_succesores(t, turno);

        if(turno%2 == 0){

        }

        else {

        }

        return mejorHijo;
    }
}