package jugador;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
    private ArrayList<ElementoRanking> ranking;

    /*Constructora*/
    public Ranking () {
        this.ranking = new ArrayList<ElementoRanking>();
    }

    //IMPORTAR RANKING
    public Ranking (File f) throws IOException {
        this.ranking = new ArrayList<ElementoRanking>();
        FileReader fr = new FileReader (f);
        BufferedReader bf =new BufferedReader(fr);
        String s1 = "aa";
        while (s1 != null) {
            s1 = bf.readLine();
            String s2[] = s1.split(" ");

            int id, total, ganadas, perdidas;
            String nick;

            if (s2.length == 5) {
                id = Integer.parseInt(s2[0]);
                nick = s2[1];
                ganadas = Integer.parseInt(s2[2]);
                perdidas = Integer.parseInt(s2[3]);
                total = Integer.parseInt(s2[4]);

                ElementoRanking e = new ElementoRanking(id,nick,ganadas,perdidas,total);
                this.ranking.add(e);
;            }
        }
    }

    public void Exportar_ranking() throws IOException {
        String path = "./files/ranking/" + "ranking" + this.ranking.size() + ".txt";
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            int tam = this.ranking.size();
            for  (int i = 0; i < tam; ++i) {
                fw.write(this.ranking.get(i).consultar_all() + "\n");
            }
            fw.close();
        }
    }

    public void add_al_ranking(ElementoRanking e) {
        this.ranking.add(e);
        if (this.ranking.size() >= 2) Collections.sort(this.ranking);
    }

    public void modificar_elemento_ranking(int i, ElementoRanking e) {
        this.ranking.set(i,e);
    }

    public int consultar_tam_ranking() {
        return this.ranking.size();
    }

    public Boolean existe_en_ranking(int id, String nick) {
        int tam = ranking.size();
        Boolean res = false;
        for (int i = 0; i < tam && !res; ++i) {
            res = (this.ranking.get(i).getID() == id) && (this.ranking.get(i).getNickname() == nick);
        }
        return res;
    }

    public ElementoRanking consultar_ranking(int id, String nick) {
        int tam = ranking.size();
        Boolean res = false;
        int i = 0;
        while (i < tam && !res) {
            res = (this.ranking.get(i).getID() == id) && (this.ranking.get(i).getNickname() == nick);
        }
        if (res) return this.ranking.get(i);
        else return null;
    }

    public ElementoRanking consultar_elemento_i(int i) {
        if (i < ranking.size()) return this.ranking.get(i);
        else {
            return null;
        }
    }

    //modo: 1 -> Ganadas, 0 -> perdidas
    public Boolean incrementar_ganada_perdida(int id, String nick, Boolean modo) {
        int tam = ranking.size();
        Boolean res = false;
        int i = 0;
        while (i < tam && !res) {
            res = (this.ranking.get(i).getID() == id) && (this.ranking.get(i).getNickname() == nick);
        }
        if (res) { //lo ha encontrado en el ranking (proceder)
            if (modo) this.ranking.get(i).incrementar_partida_ganada();
            else this.ranking.get(i).incrementar_partida_perdida();
        }
        return res;
    }


}
