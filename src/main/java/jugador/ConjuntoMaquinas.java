package jugador;

import java.util.Vector;

public class ConjuntoMaquinas {
    private final Vector <JugadorMaquina> Maquinas;

    public ConjuntoMaquinas() {
        Vector <JugadorMaquina> temp = new Vector<JugadorMaquina>(6);
        for (int i = 0; i < 6; ++i) {
            JugadorMaquina m = new JugadorMaquina(i,i);
            temp.addElement(m);
        }
        this.Maquinas = temp;
    }

    public int getConjunto_size() {return this.Maquinas.size();}

    public JugadorMaquina getMaquina_i(int i) {
        return (this.Maquinas.elementAt(i));
    }

    public Boolean modificar_Maquina_i(int i, JugadorMaquina maq) {
        Boolean b = true;
        if (i > 5) b = false; //no tiene el ID de una maquina
        else {
            this.Maquinas.set(i,maq);
        }
        return b;
    }
}
