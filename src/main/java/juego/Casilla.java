package juego;

import java.util.*;

public class Casilla {
    int id , color ,x , y;     //1 = negra , 0 = blanca.
    public int consultar_color(){
        return color;
    }
    public int getId(){
        return id;
    }
    public int consultar_pos_x(){
        return x;
    }
    public int consultar_pos_y(){
        return y;
    }

    public void modificar_color(int color_modificar){
        color = color_modificar;
    }
}
