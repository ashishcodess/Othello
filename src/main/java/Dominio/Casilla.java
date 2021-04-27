package Dominio;

public class Casilla {

    /**
     * tipo de la cassilla donde 0: vacía, 1: disponible, 2: negra, 3: blanca
     */
    private int tipo ;

    /**
     * Constructora por defecto
     */
    public Casilla () {
        this.tipo = 0;    // At first all of them are empty.
    }
    /**
     * Constructora a partir de un tipo t
     * @param t tipo de la casilla
     */
    public Casilla (int t) {
        this.tipo = t;
    }

    /**
     * @return retorna el tipo de la casilla
     */
    public int getTipoCasilla() {
        return tipo;
    }

    /**
     * cambia el tipo de la casilla
     * @param tipo tipo al cual se cambia la casilla
     */
    public void cambiar_tipo(int tipo){
        this.tipo = tipo;
    }
}

