package Dominio;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import MyException.MyException;

public class Ranking {
    private final ArrayList<ElementoRanking> ranking;

    /**
     * Constructora por defecto (ranking vacio)
     * */
    public Ranking () {
        this.ranking = new ArrayList<ElementoRanking>();
    }

    //IMPORTAR RANKING
    /**
     * Constructora a partir de un fichero Ranking (en caso de que este exista)
     * @param path_fichero es la ubicacion del fichero a importar
     * */
    public Ranking (String path_fichero) throws IOException, MyException {
        this.ranking = new ArrayList<ElementoRanking>();
        File f = new File(path_fichero);
        if (f.exists()) {
            FileReader fr = new FileReader (f);
            BufferedReader bf =new BufferedReader(fr);
            String s1;
            while ((s1 = bf.readLine()) != null) {
                String[] s2 = s1.split(" ");
                int id, total, ganadas, perdidas,empatadas;
                String nick;
                if (s2.length == 6) {
                    id = Integer.parseInt(s2[0]);
                    nick = s2[1];
                    ganadas = Integer.parseInt(s2[2]);
                    perdidas = Integer.parseInt(s2[3]);
                    empatadas = Integer.parseInt(s2[4]);
                    total = Integer.parseInt(s2[5]);
                    ElementoRanking e = new ElementoRanking(id,nick,ganadas,perdidas,empatadas,total);
                    this.ranking.add(e);
                }
            }
        }
    }

    /**
     * Este metodo exporta toda la informacion del Ranking en un fichero
     * */
    public void Exportar_ranking() throws IOException {
        String path = "./src/files/ranking/" + "ranking" + this.ranking.size() + ".txt";
        File f = new File(path);
        if (f.exists()) f.delete();
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        int tam = this.ranking.size();
        for  (int i = 0; i < tam; ++i) {
            fw.write(this.ranking.get(i).consultar_all() + "\n");
        }
        fw.close();
    }

    /**
     * Este metodo inserta en la ultima posicion de ArrayList el ElementoRanking e
     * */
    public void add_al_ranking(ElementoRanking e) {
        this.ranking.add(e);
    }

    /**
     * Este metodo inserta en la ultima posicion "i" el ElementoRanking "e"
     * @return devuelve falso si el entero "i" no pertenece al rango permitido dentro del ArrayList
     * */
    public Boolean modificar_elemento_ranking(int i, ElementoRanking e) {
        if (i < 0 || i > this.ranking.size()) return false;
        else {
            this.ranking.set(i,e);
            return true;
        }
    }

    /**
     * Operacion get del ranking.size()
     * */
    public int consultar_tam_ranking() {
        return this.ranking.size();
    }

    /**
     * Este metodo inserta en la ultima posicion "i" el ElementoRanking "e"
     * @return devuelve falso si el entero "i" no pertenece al rango permitido dentro del ArrayList
     * */
    public Boolean eliminar_elemento_ranking(int id, String nick) {
        int ires = existe_en_ranking(id,nick);
        Boolean res = false;
        if (ires != -1) {
            this.ranking.remove(ires);
            res = true;
        }
        return res;
    }

    /**
     * @return devuelve la posicion del elemento del ranking con identificadores (id,nick) en caso de que exista, caso contrario devuelve -1
     * */
    public int existe_en_ranking(int id, String nick) {
        if (id < 0 || id > this.ranking.size()) return -1;
        int tam = ranking.size();
        int i = -1;
        Boolean res = false;
        for (i = 0; i < tam && !res; ++i) {
            res = (this.ranking.get(i).getID() == id) && (this.ranking.get(i).getNickname() == nick);
        }
        if (!res) return -1; //no ha sido encontrado en el RANKING
        return (i-1);
    }

    /**
     * @return devuelve el elemento del ranking con identificadores (id,nick) en caso de que exista, caso contrario devuelve -1
     * */
    public ElementoRanking consultar_ranking(int id, String nick) {
        int i = existe_en_ranking(id,nick);
        if (i != -1) return this.ranking.get(i); //existe en el ranking
        else return null;
    }

    /**
     * @return devuelve el elemento del ranking en la posicion i de ArrayList
     * */
    public ElementoRanking consultar_elemento_i(int i) {
        if (i > 0 && i < ranking.size()) return this.ranking.get(i);
        else return null;
    }

    //ganador -> 0 (gana nick1), 1 (gana nick2), 2 (empate)
    /**
     * Este metodo es el encargado de incrementar las partidas de cada jugador (en caso de que no exista creara los Elementos del Ranking
     * de cada jugador respectivamente)
     * */
    public void incrementar_ganadas_perdidas(int id1, String nick1,int id2, String nick2, int ganador) throws MyException {
        if (ganador >= 0 && ganador < 3) {
            if (id1 > 5) incrementar_partida(id1,nick1,ganador);
            if (id2 > 5) incrementar_partida(id2,nick2,ganador);
        }
    }

    //modo: 2 -> empate, 1 -> Ganadas, 0 -> perdidas
    /**
     * Este metodo crea el ElementoRanking asociado a un jugador (en caso de que no exista)
     * e incrementa los contadores de partidas respetivamente en funcion del entero "ganador"
     * */
    public void incrementar_partida(int id, String nick, int modo) throws MyException {
        int i = existe_en_ranking(id,nick);
        if (i == -1) {
            ElementoRanking e = new ElementoRanking(id,nick);
            this.add_al_ranking(e);
            i = ranking.size()-1;
        }
        switch(modo) {
            case 0:
                this.ranking.get(i).incrementar_partida_perdida();
                break;
            case 1:
                this.ranking.get(i).incrementar_partida_ganada();
                break;
            case 2:
                this.ranking.get(i).incrementar_partida_empatada();
                break;
            default:
                break;
        }
    }


    //orden -> 0 (Ganadas), 1 (ID) , 2 (NICKNAME)

    /**
     * Este metodo es el encargado de ordenar el ranking (en funcion de PartidasGanadas, ID o NICKNAME)
     * @return devuelve true en caso de que se haya efectuado una ordenacion, caso contrario devuelve falso
     * */
    public Boolean ordenar_ranking(int orden) {
        if (orden > 2 || orden < 0) return false;
        else {
            switch(orden) {
                case 0:
                    Collections.sort(this.ranking, new SortbyGanadas());
                    break;
                case 1:
                    Collections.sort(this.ranking, new SortbyID());
                    break;
                case 2:
                    Collections.sort(this.ranking, new SortbyNICKNAME());
                    break;
            }
            return true;
        }
    }


    /**
     * Este metodo muestra por salida estandar la informacion disponible de un Jugador(id,nick) determinado dentro del Ranking
     * */
    public void print_persona_ranking(int id ,String nick) {
        int i = existe_en_ranking(id,nick);
        if (i != -1) {
            System.out.println("(ID, nickname, Ganadas, Perdidas,Empatadas, Totales)");
            System.out.println(this.ranking.get(i).consultar_all());
        }
        else System.out.println("Error: no existe persona con ID:" + id + " y nick:" + nick + " dentro del Ranking");
    }

    /**
     * Este metodo muestra por salida estandar la informacion disponible de todos los jugadores dentro del Ranking
     * */
    public void print_ranking() {
        int tam = this.ranking.size();
        System.out.println("(ID, nickname, Ganadas, Perdidas,Empatadas, Totales)");
        for (int i = 0; i < tam; ++i) {
            System.out.println(this.ranking.get(i).consultar_all());
        }
        System.out.println();
    }

    /**
     * Este metodo ordena el Ranking y muestra por salida estandar la informacion disponible de todos los jugadores dentro del Ranking
     * @param orden es un numero entero entre 0 y 2 (caso contrario no ordenara nada)
     * */
    public void print_ranking_orden(int orden) {
        ordenar_ranking(orden);
        int tam = this.ranking.size();
        System.out.println("(ID, nickname, Ganadas, Perdidas,Empatadas, Totales)");
        for (int i = 0; i < tam; ++i) {
            System.out.println(this.ranking.get(i).consultar_all());
        }
        System.out.println();
    }

    /**
     * funcion compare en funcion de PartidasGanadas
     * */
    static class SortbyGanadas implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.getGanadas() - e1.getGanadas());
        }
    }

    /**
     * funcion compare en funcion de ID de los Jugadores
     * */
    static class SortbyID implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.getID() - e1.getID());
        }
    }

    /**
     * funcion compare en funcion de los nickname's de los Jugadores
     * */
    static class SortbyNICKNAME implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return e2.getNickname().compareTo(e1.getNickname());
        }
    }
}
