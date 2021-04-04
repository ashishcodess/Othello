package jugador;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking {
    private ArrayList<ElementoRanking> ranking;

    /*Constructora*/
    public Ranking () {
        this.ranking = new ArrayList<ElementoRanking>();
    }

    //IMPORTAR RANKING
    public Ranking (String path_fichero) throws IOException {
        this.ranking = new ArrayList<ElementoRanking>();
        File f = new File(path_fichero);
        if (f.exists()) {
            FileReader fr = new FileReader (f);
            BufferedReader bf =new BufferedReader(fr);
            String s1;
            while ((s1 = bf.readLine()) != null) {
                String s2[] = s1.split(" ");
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

    public void Exportar_ranking() throws IOException {
        String path = "./files/ranking/" + "ranking" + this.ranking.size() + ".txt";
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

    public void add_al_ranking(ElementoRanking e) {
        this.ranking.add(e);
    }

    public void modificar_elemento_ranking(int i, ElementoRanking e) {
        this.ranking.set(i,e);
    }

    public int consultar_tam_ranking() {
        return this.ranking.size();
    }

    //devuelve posicion dentro del Arraylist si existe, si no devuelve -1
    public int existe_en_ranking(int id, String nick) {
        int tam = ranking.size();
        int i = -1;
        Boolean res = false;
        for (i = 0; i < tam && !res; ++i) {
            res = (this.ranking.get(i).getID() == id) && (this.ranking.get(i).getNickname() == nick);
        }
        System.out.println("existeRank: " + i );
        if (!res) return -1; //no ha sido encontrado en el RANKING
        return (i-1);
    }

    public ElementoRanking consultar_ranking(int id, String nick) {
        int i = existe_en_ranking(id,nick);
        if (i != -1) return this.ranking.get(i); //existe en el ranking
        else return null;
    }

    public ElementoRanking consultar_elemento_i(int i) {
        if (i > 0 && i < ranking.size()) return this.ranking.get(i);
        else return null;
    }

    //ganador -> 0 (gana nick1), 1 (gana nick2), 2 (empate)
    public void incrementar_ganadas_perdidas(int id1, String nick1,int id2, String nick2, int ganador) {
        if (id1 > 5) incrementar_ganada_perdida(id1,nick1,ganador);
        if (id2 > 5) incrementar_ganada_perdida(id2,nick2,ganador);
    }

    //modo: 2 -> empate, 1 -> Ganadas, 0 -> perdidas
    public void incrementar_ganada_perdida(int id, String nick, int modo) {
        int i = existe_en_ranking(id,nick);
        System.out.println("existe: " + i + ", " +id + " "+ nick);
        if (i == -1) {
            ElementoRanking e = new ElementoRanking(id,nick);
            this.add_al_ranking(e);
            i = ranking.size()-1;
        }
        if (modo == 2) this.ranking.get(i).incrementar_partida_empatada();
        if (modo == 1) this.ranking.get(i).incrementar_partida_ganada();
        else this.ranking.get(i).incrementar_partida_perdida();
    }



    //orden -> 0 (Ganadas), 1 (ID) , 2 (NICKNAME)
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

    public Boolean print_persona_ranking(int id ,String nick) {
        Boolean res = false;
        int tam = ranking.size();
        int i = 0;
        while (i < tam && !res) {
            res = (this.ranking.get(i).getID() == id) && (this.ranking.get(i).getNickname() == nick);
        }
        if (res) {
            System.out.println("(ID, nickname, Ganadas, Perdidas,Empatadas, Totales)");
            System.out.println(this.ranking.get(i).consultar_all());
        }
        else System.out.println("Error: no existe persona con ID:" + id + " y nick:" + nick + " dentro del Ranking");
        return res;
    }

    public void print_ranking() {
        int tam = this.ranking.size();
        System.out.println("(ID, nickname, Ganadas, Perdidas,Empatadas, Totales)");
        for (int i = 0; i < tam; ++i) {
            String s = this.ranking.get(i).consultar_all();
            System.out.println(s);
        }
    }

    public void print_ranking_orden(int orden) {
        ordenar_ranking(orden);
        int tam = this.ranking.size();
        System.out.println("(ID, nickname, Ganadas, Perdidas,Empatadas, Totales)");
        for (int i = 0; i < tam; ++i) {
            String s = this.ranking.get(i).consultar_all();
            System.out.println(s);
        }
    }

    static class SortbyGanadas implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.getGanadas() - e1.getGanadas());
        }
    }

    static class SortbyID implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.getID() - e1.getID());
        }
    }

    static class SortbyNICKNAME implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return e2.getNickname().compareTo(e1.getNickname());
        }
    }

}
