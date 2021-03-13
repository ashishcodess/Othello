
import java.util.*;

public class Casilla {

    int [][] Marcar = new int [8][8];
    Map<Integer, Ficha> map_fichas_blanca = new HashMap<Integer, Ficha>();
    Map<Integer, Ficha> map_fichas_negra = new HashMap<Integer, Ficha>();


    public int colocada_blancas(){
            return map_fichas_blanca.size();
        }
    public int colocada_negras(){
        return map_fichas_negra.size();
    }

    public bool es_posssible (int x , int y){// cuando quiere hacer un movimeinto pasamos pos para chequear
        if(Marcar[x][y] = 1) return true;
        else return false;
    }

    public void marcar_pos(Fichas obj){  // Cuando queremos marcar posiciones como valido, ya atravesado etc.
              //Moviendo por este ficha vamos haciendo bfs etc.
    }

    public void añadir_fichas(const Fichas &fic){ // ficha fic no existe ya .
        if (fic.consultar_color()== "negra")map_fichas_negra.put(fic.getId(), fic);
        else map_fichas_blanca.put(fic.getId(), fic);

           // añadir ficha nueva a mapa de fichas donde su id es el key y el objeto es el cuerpo de mapa
    }

    public void modificar_fichas(int[] id_fichas_afectadas){
        //cuando nos llega ids de todas las fichas afectadas vamos a hacer find de ids desde el map y si
        //estan en negras borramos de ahí y añadimos a blancas ya que fichas afectadas cambian de color.
    }
}