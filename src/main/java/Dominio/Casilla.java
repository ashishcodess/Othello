package Dominio;

public class Casilla {

    private int tipo ;     //0 - vacía, 1 - disponible, 2 - negra, 3 - blanca
    //Constructor of  Casilla
    public Casilla () {
        this.tipo = 0;    // At first all of them are empty.
    }

    public Casilla (int id , int t) {
        this.tipo = t;
    }

    public Casilla (int t) {
        this.tipo = t;
    }

    public int getTipoCasilla() {
        return tipo;
    }

    public void cambiar_tipo(int tipo){

        this.tipo = tipo;
    }
}

