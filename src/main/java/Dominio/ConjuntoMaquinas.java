package Dominio;

import java.util.Vector;

public class ConjuntoMaquinas {
    private final Vector <JugadorMaquina> Maquinas;

    /**
     * Constructora por defecto (genera 6 maquinas diferentes)
     * */
    public ConjuntoMaquinas() {
        Vector <JugadorMaquina> temp = new Vector<JugadorMaquina>(6);
        for (int i = 0; i < 6; ++i) {
            JugadorMaquina m = new JugadorMaquina(i,i);
            temp.addElement(m);
        }
        this.Maquinas = temp;
    }

    /**
     * Operacion get del size() del vector de Maquinas
     * */
    public int getConjunto_size() {return this.Maquinas.size();}

    /**
     * Operacion get del size() del vector de Maquinas
     * @return En caso de i={0...5} devuelve la Maquina seleccionada en la posicion i
     * del vector, caso contrario devuelve null
     * */
    public JugadorMaquina getMaquina_i(int i) {
        if (i < 0 || i > getConjunto_size()) return null;
        return (this.Maquinas.elementAt(i));
    }

    /**
     * Operacion set modificar-maquina-i
     * @param i (posicion del vector de Maquinas), maq (en caso de poderse reemplazar dicha maquina será la introducida en el vector)
     * @return devuelve true en caso de que se haya modificado correctamente, caso contrario devuelve falso
     * */
    public Boolean modificar_Maquina_i(int i, JugadorMaquina maq) {
        Boolean b;
        if (i < 0 || i > getConjunto_size()) b = false; //no tiene el ID de una maquina o i negativa
        else {
            this.Maquinas.set(i,maq);
            b = true;
        }
        return b;
    }
}
