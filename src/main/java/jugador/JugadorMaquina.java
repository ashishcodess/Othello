package jugador;

/*
* IDEA: Hacer que las maquinas tengan como ID's valores del 0 al 5 (6 maquinas)
*
* */

public class JugadorMaquina extends Jugador {

    /*Atributos*/
    private String estrategia; //puede eliminarse porque sera al final la profundidad_minMax
    private int profundidad_MinMax;

    /*Constructora*/
    public JugadorMaquina (int idMaquina) {
        super(idMaquina);
    }

    public JugadorMaquina (int idMaquina, int profundidad) {
        super(idMaquina);
        this.profundidad_MinMax = profundidad;
    }

    public JugadorMaquina (int idMaquina, int profundidad, String strategy) {
        super(idMaquina);
        this.estrategia = strategy;
        this.profundidad_MinMax = profundidad;
    }

    /*Sets y Gets*/
    public void modificar_id_maquina(int nuevoID) { super.modificar_id(nuevoID); } //Aunque para las maquinas creo yo que no es necesario modificar su ID

    public void modificar_estrategia(String nuevaEstrategia) {this.estrategia = nuevaEstrategia;}

    public void modificar_profundidad(int nuevaProfundidad) {this.profundidad_MinMax = nuevaProfundidad;}


    public int get_MaquinaID() { return super.getID(); }

    public int get_profundidadMaquina() { return this.profundidad_MinMax; }

    public String get_estrategia() {return this.estrategia;}

}