package juego;

import java.util.*;

public class Tablero {

    int[][] Marcar = new int[8][8];
    Map<Integer, Casilla> map_fichas_blanca = new HashMap<Integer, Casilla>();
    Map<Integer, Casilla> map_fichas_negra = new HashMap<Integer, Casilla>();


    public int colocada_blancas() {
        return map_fichas_blanca.size();

    }

    public int colocada_negras() {
        return map_fichas_negra.size();

    }

    public boolean es_posssible(int x, int y) {// cuando quiere hacer un movimeinto pasamos pos para chequear
        if (Marcar[x][y] == 1) return true;
        else return false;
    }

    public void marcar_pos(Casilla obj) {  // Cuando queremos marcar posiciones como valido, ya atravesado etc.
        //Moviendo por este ficha vamos haciendo bfs etc.
    }

    public void añadir_fichas(Casilla cas) { // ficha fic no existe ya .
        if (cas.consultar_color() == 1) map_fichas_negra.put(cas.getId(), cas);
        else map_fichas_blanca.put(cas.getId(), cas);

        // añadir ficha nueva a mapa de fichas donde su id es el key y el objeto es el cuerpo de mapa
    }

    public void modificar_fichas(Vector<Integer> id_fichas_afectadas) {

        for (int i = 0; i < id_fichas_afectadas.size(); ++i) {
            //if()

            //cuando nos llega ids de todas las fichas afectadas vamos a hacer find de ids desde el map y si
            //estan en negras borramos de ahí y añadimos a blancas ya que fichas afectadas cambian de color.
        }
    }
}