package Dominio;

public class Position {
    private int x , y;
    public Position () {
        this.x = -1;
        this.y = -1;    // At first all of them are empty.
    }
    public Position (int x , int y) {
        this.x = x;
        this.y = y;    // At first all of them are empty.
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
