package Dominio.Jugador;

public class Alphabeta {

    /**
     * parametro alfa para el algoritmo de la IA
     */
    private int alpha;

    /**
     * parametro beta para el algoritmo de la IA
     */
    private int beta;

    /**
     * Constructora por defecto
     */
    public Alphabeta (){
      alpha = -1000;
      beta  = 1000;
    }

    /**
     * @return retorna el parametro alfa del algoritmo de la IA
     */
    public int getAlpha(){return alpha;}

    /**
     * @return retorna el parametro beta
     */
    public int getBeta(){return beta;}

    /**
     * asigna el valor del parametro al parametro alfa del algoritmo de la IA
     * @param a valor que le asignamos al parametro alfa del algoritmo de la IA
     */
    public void setAlpha(int a){alpha=a;}

    /**
     * asigna el valor del parametro al parametro beta del algoritmo de la IA
     * @param b valor que le asignamos al parametro beta del algoritmo de la IA
     */
    public void setBeta(int b){beta=b;}
}
