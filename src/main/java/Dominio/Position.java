package Dominio;

public class Position {

    /**
     * Coordenadas de la posición
     */
    private int x , y;

    /**
     * Creadora por defecto
     */
    public Position () {
        this.x = -1;
        this.y = -1;    // At first all of them are empty.
    }

    /**
     * Creadora a partir de una coordenada x e y
     * @param x coordenada x
     * @param y coordenada y
     */
    public Position (int x , int y) {
        this.x = x;
        this.y = y;    // At first all of them are empty.
    }

    /**
     * @return retorna la coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * @return retorna la coordenada y
     */
    public int getY() {
        return y;
    }
}
