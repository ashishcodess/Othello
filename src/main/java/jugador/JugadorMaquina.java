package jugador;
import juego.Tablero;

/*
*
* falta hacer IA maquina
*
* */

public class JugadorMaquina extends Jugador {

    /*Atributos*/
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
     * */
    public JugadorMaquina (int idMaquina) {
        super(idMaquina);
    }

    /**
     * Constructora JugadorMaquina (idMaquina, profundidad)
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
     * Operacion para tirar ficha
     * @return devuelve el identificador de la casilla donde coloca la ficha
     */
    public int elegir_posicion(Tablero t){

    }
}