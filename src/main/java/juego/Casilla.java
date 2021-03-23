package juego;

import java.util.*;

public class Casilla {
    //faltaria un typedef para señalar el tipo de casilla (0 - vacía, 1 - disponible, 2 - negra, 3 - blanca)
    private int id , tipo ,x , y;     //1 = negra , 0 = blanca.

    //getters
    public int getId(){
        return id;
    }
    public int getTipoCasilla(){ return tipo }
    public int getPosicionX(){ return x; }
    public int getPosicionY(){ return y; }


    public void modificar_color(int color_modificar){
        color = color_modificar;
    }

}
