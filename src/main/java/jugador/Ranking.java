package jugador;

import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
    private ArrayList<ElementoRanking> ranking;

    /*Constructora*/
    public Ranking () {
        this.ranking = new ArrayList<ElementoRanking>();
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
