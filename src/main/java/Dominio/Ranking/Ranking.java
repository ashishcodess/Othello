package Dominio.Ranking;

import java.util.ArrayList;
import java.util.Comparator;



public class Ranking {

    public enum tipoGanador {EMPATE, PIERDE, GANA, GANA_J1, GANA_J2}

    /**ArrayList de elementos tipo ElementoRanking*/
    private final ArrayList<ElementoRanking> ranking;

    /**Logros*/
    private final Logros log;


    /**
     * Constructora por defecto (ranking vacio)
     * */
    public Ranking () {
        this.ranking = new ArrayList<>();
        this.log = new Logros();
    }


    /**
     * Este metodo modifica el elemento del Ranking con identificadores iguales a e (en caso de existir en el ranking),
     * caso contrario inserta el ElementoRanking e en la ultima posicion de ArrayList
     * @param e Elemento tipo ElementoRanking a agregar al ranking
     * */
    public void add_al_ranking(ElementoRanking e) {
        int b = existe_en_ranking(e.consultar_ID(),e.consultar_Nickname());
        if (b != -1) {
            modificar_elemento_ranking(b,e);
        }
        else this.ranking.add(e);
    }

    /**
     * Este metodo inserta en la ultima posicion "i" el ElementoRanking "e"
     * @param i posicion dentro del ranking
     * @param e Elemento tipo ElementoRanking a modificar en el ranking */
    public void modificar_elemento_ranking(int i, ElementoRanking e) {
        if (i >= 0 && i < ranking.size()) {
            this.ranking.set(i,e);
        }
    }

    /**
     * Operacion get del ranking.size()
     * @return devuelve el size del ranking
     * */
    public int consultar_tam_ranking() {
        return this.ranking.size();
    }

    /**
     * Operacion existe_en_ranking
     * @param id identificador de Persona a consultar si existe
     * @param nick nickname de Persona a consultar si existe
     * @return devuelve la posicion del elemento del ranking con identificadores (id,nick) en caso de que exista, caso contrario devuelve -1
     * */
    public int existe_en_ranking(int id, String nick) {
        int tam = ranking.size();
        int i ;
        boolean res = false;
        for (i = 0; i < tam && !res; ++i) {
            int idAux = this.ranking.get(i).consultar_ID();
            String sAux = this.ranking.get(i).consultar_Nickname();
            res = (idAux == id) && (sAux.equals(nick));
        }
        if (!res) return -1; //no ha sido encontrado en el RANKING
        return (i-1);
    }

    /**
     * Este metodo inserta en la ultima posicion "i" el ElementoRanking "e"
     * @param id identificador de Persona a borrar
     * @param nick nickname de Persona a borrar */
    public void eliminar_elemento_ranking(int id, String nick) {
        int ires = existe_en_ranking(id,nick);
        if (ires != -1) {
            this.ranking.remove(ires);
        }
    }

    /**
     * Operacion consultar_info_elemento_i(i)
     * @param i posicion dentro del ranking
     * @return devuelve la informacion del  elemento del ranking en la posicion i de ArrayList
     * */
    public String consultar_info_elemento_i(int i) {
        if (i >= 0 && i < ranking.size()) return this.ranking.get(i).consultar_todo();
        else return null;
    }

    /**
     * Operacion consultar_elemento_i(i)
     * @param i posicion dentro del ranking
     * @return devuelve el elemento del ranking en la posicion i de ArrayList
     * */
    public ElementoRanking consultar_elemento_i(int i) {
        if (i >= 0 && i < ranking.size()) return this.ranking.get(i);
        else return null;
    }

    /**
     * Operacion incrementar_ganadas_perdidas()
     * Este metodo es el encargado de incrementar las partidas de cada jugador (en caso de que no exista creara los Elementos del Ranking de cada jugador respectivamente)
     * Ademas hace la comprobacion de los logros en caso de necesitarse actualizarlos
     * @param id1 identificador del Jugador1
     * @param nick1 nickname del Jugador1 (en caso de que tenga nickname)
     * @param id2 identificador del Jugador2
     * @param nick2 nickname del Jugador2 (en caso de que tenga nickname)
     * @param ganador incrementar contador en funcion del tipoGanador de ganador
     * */
    public void incrementar_ganadas_perdidas(int id1, String nick1,int id2, String nick2, tipoGanador ganador) throws Exception {
        switch (ganador) {
            case EMPATE:
                if (id1 > 5) incrementar_partida(id1,nick1,tipoGanador.EMPATE);
                if (id2 > 5) incrementar_partida(id2,nick2,tipoGanador.EMPATE);
                break;
            case GANA_J1:
                if (id1 > 5) incrementar_partida(id1,nick1,tipoGanador.GANA);
                if (id2 > 5) incrementar_partida(id2,nick2,tipoGanador.PIERDE);
                break;
            case GANA_J2:
                if (id1 > 5) incrementar_partida(id1,nick1,tipoGanador.PIERDE);
                if (id2 > 5) incrementar_partida(id2,nick2,tipoGanador.GANA);
                break;
        }
        compruebaModifica_logros_jugadores(nick1,id1,nick2,id2);
    }

    /**
     * Operacion incrementar_partida()
     *
     * Este metodo crea el ElementoRanking asociado a un jugador (en caso de que no exista)
     * e incrementa los contadores de partidas respetivamente en funcion del entero "ganador"
     * @param id identificador del Jugador2
     * @param nick nickname del Jugador2 (en caso de que tenga nickname)
     * @param ganador incrementar contador en funcion del tipo de ganador
     * */
    public void incrementar_partida(int id, String nick, tipoGanador ganador) throws Exception {
        int i = existe_en_ranking(id,nick);
        if (i == -1) {
            ElementoRanking e = new ElementoRanking(id,nick);
            this.add_al_ranking(e);
            i = ranking.size()-1;
        }
        switch(ganador) {
            case PIERDE:
                this.ranking.get(i).incrementar_partida_perdida();
                break;
            case GANA:
                this.ranking.get(i).incrementar_partida_ganada();
                break;
            case EMPATE:
                this.ranking.get(i).incrementar_partida_empatada();
                break;
            default:
                break;
        }
    }


    /**
     * Operacion ordenar_ranking(orden) en funcion de un orden concreto
     * @param orden [0 (Ganadas), 1 (ID mayor a menor) , 2 (NICKNAME), 3 (ID menor a mayor), 4 (Perdidas), 5 (empatadas),6 (Totales)]
     * */
    public void ordenar_ranking(int orden) {
        if (orden >= 0 && orden < 7) {
            switch(orden) {
                case 0:
                    this.ranking.sort(new SortbyGanadas());
                    break;
                case 1:
                    this.ranking.sort(new SortbyID());
                    break;
                case 2:
                    this.ranking.sort(new SortbyNICKNAME());
                    break;
                case 3:
                    this.ranking.sort(new SortbyIDMenor());
                    break;
                case 4:
                    this.ranking.sort(new SortbyPerdidas());
                    break;
                case 5:
                    this.ranking.sort(new SortbyEmpatadas());
                    break;
                case 6:
                    this.ranking.sort(new SortbyTotales());
                    break;
            }
        }
    }

    /**
     * Operacion toArrayList()
     * @return devuelve toda la información del Ranking en un ArrayList de Strings
     * */
    public ArrayList<String> toArrayList() {
        ArrayList<String> as = new ArrayList<>();
        //logros
        as.add(consultar_logro(Logros.tipoLogro.PARTIDA_CORTA));
        as.add(consultar_logro(Logros.tipoLogro.FICHAS_DIFF));
        as.add(consultar_logro(Logros.tipoLogro.PARTIDAS_TOTALES));
        as.add(consultar_logro(Logros.tipoLogro.PARTIDAS_GANADAS));
        as.add(consultar_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS));
        as.add("***");
        //RANKING
        for (ElementoRanking elementoRanking : this.ranking) {
            as.add(elementoRanking.consultar_todo());
        }
        return as;
    }


    /**
     * Operacion print_persona_ranking(id,nick): muestra por salida estandar la informacion disponible de un Jugador(id,nick) determinado dentro del Ranking
     * @param id identificador de Persona
     * @param nick nickname de Persona
     * */
    public void print_persona_ranking(int id ,String nick) {
        int i = existe_en_ranking(id,nick);
        if (i != -1) {
            System.out.println("(ID, nickname, Ganadas, Perdidas, Empatadas, Totales)");
            System.out.println(this.ranking.get(i).consultar_todo());
        }
        else System.out.println("Error: no existe persona con ID:" + id + " y nick:" + nick + " dentro del Ranking");
    }

    /**
     * Este metodo muestra por salida estandar la informacion disponible de todos los jugadores dentro del Ranking
     * */
    public void print_ranking() {
        System.out.println("(ID, nickname, Ganadas, Perdidas,Empatadas, Totales)");
        for (ElementoRanking elementoRanking : this.ranking) {
            System.out.println(elementoRanking.consultar_todo());
        }
        System.out.println();
    }

    /**
     * Este metodo ordena el Ranking y muestra por salida estandar la informacion disponible de todos los jugadores dentro del Ranking
     * @param orden es un numero entero entre 0 y 2 (caso contrario no ordenara nada)
     * */
    public void print_ranking_orden(int orden) {
        ordenar_ranking(orden);
        System.out.println("(ID, nickname, Ganadas, Perdidas,Empatadas, Totales)");
        for (ElementoRanking elementoRanking : this.ranking) {
            System.out.println(elementoRanking.consultar_todo());
        }
        System.out.println();
    }


    //FUNCIONES DE LOGROS

    /**
     * Cambiar logro partida (menos turnos o mas capturas)
     * @param tipo tipo de logro a realizar el cambio (PARTIDA_CORTA o FICHAS_DIFF)
     * @param nick1 nickname del Jugador1 (en caso de que tenga nickname)
     * @param id1 identificador del Jugador1
     * @param nick2 nickname del Jugador2 (en caso de que tenga nickname)
     * @param id2 identificador del Jugador2
     * @param t1 es el numero entero a reemplazar dependiendo del tipo seleccionado en caso de FICHAS DIFF es el numero de fichas de del jugador 1
     * @param t2 es el numero de fichas de del jugador 2
     * */
    public void cambiar_logro_partida(Logros.tipoLogro tipo, String nick1, int id1, String nick2, int id2, int t1, int t2) {
        log.cambiar_logro_partida(tipo,nick1,id1,nick2,id2,t1,t2);
    }

    /**
     * Comprobar logro jugadores
     * @param tipo tipo de Logro de jugadores a comprobar (PARTIDAS_TOTALES, PARTIDAS_GANADAS, PARTIDAS_PERDIDAS)
     * @param t entero a comprobar la condicion
     * */
    public void cambiar_logro_jugador(Logros.tipoLogro tipo, String nick1, int id1, int t) {
        log.cambiar_logro_jugador(tipo,nick1,id1,t);
    }

    /**
     * Comprobar logro
     * @param tipo tipo de Logro
     * @param i entero a comprobar la condicion3
     * @return devuelve TRUE en caso de que se cumpla la condicion del logro a probar
     * */
    public boolean comprobar_logro(Logros.tipoLogro tipo, int i) {
        boolean b = false;
        switch (tipo) {
            case PARTIDA_CORTA:
                b = log.comprueba_logro_partida(i);
                break;

            case FICHAS_DIFF:
                b = log.comprueba_logro_capturas(i);
                break;

            case PARTIDAS_TOTALES:
                b = log.comprueba_logro_jugadores(tipo,i);
                break;

            case PARTIDAS_GANADAS:
                b = log.comprueba_logro_jugadores(tipo,i);
                break;

            case PARTIDAS_PERDIDAS:
                b = log.comprueba_logro_jugadores(tipo,i);
                break;
        }
        return b;
    }

    /**
     * Consultar logro
     * @param tipo es el tipo de logro a consultar [PARTIDA_CORTA, PARTIDAS_TOTALES, PARTIDAS_GANADAS, PARTIDAS_PERDIDAS, CAPTURAS]
     * @return devuelve la informacion del logro dependiendo del tipoLogro pasado como parametro
     * */
    public String consultar_logro(Logros.tipoLogro tipo) {
        String res = "";
        switch (tipo) {
            case PARTIDA_CORTA:
                res = log.consultar_partida_corta();
                break;

            case FICHAS_DIFF:
                res = log.consultar_max_fichas_diff();
                break;

            case PARTIDAS_TOTALES:
                res = log.consultar_jugador_Totales();
                break;

            case PARTIDAS_GANADAS:
                res = log.consultar_jugador_Ganadas();
                break;

            case PARTIDAS_PERDIDAS:
                res = log.consultar_jugador_perdidas();
                break;
        }
        return res;
    }


    /**
     * Comprueba logro : comprueba el logro y se modifica el logro si es necesario
     * @param tipo es el tipo de logro a consultar para jugador[PARTIDAS_TOTALES, PARTIDAS_GANADAS, PARTIDAS_PERDIDAS]
     * @param e ElementoRanking a comprobar dependiendo del tipo de logro
     * */
    private void comprueba_logro(Logros.tipoLogro tipo, ElementoRanking e) {
        int partidas_aux = 0;
        switch (tipo) {
            case PARTIDAS_TOTALES:
                partidas_aux = e.consultar_Totales();
                break;
            case PARTIDAS_GANADAS:
                partidas_aux = e.consultar_Ganadas();
                break;
            case PARTIDAS_PERDIDAS:
                partidas_aux = e.consultar_Perdidas();
                break;
        }
        if (log.comprueba_logro_jugadores(tipo,partidas_aux)) {
            log.cambiar_logro_jugador(tipo,e.consultar_Nickname(),e.consultar_ID(),partidas_aux);
        }
    }

    /**
     * Comprueba y modifica (en caso de ser necesario) los logros de partidas totales, ganadas y perdidas
     * @param nick1 nickname del Jugador1 (en caso de que tenga nickname)
     * @param id1 identificador del Jugador1
     * @param nick2 nickname del Jugador2 (en caso de que tenga nickname)
     * @param id2 identificador del Jugador2
     * */
    private void compruebaModifica_logros_jugadores(String nick1, int id1, String nick2, int id2) {
        int i = existe_en_ranking(id1,nick1);
        if (i != -1) { //Jugador1
            ElementoRanking e_aux = consultar_elemento_i(i);
            comprueba_logro(Logros.tipoLogro.PARTIDAS_TOTALES,e_aux);
            comprueba_logro(Logros.tipoLogro.PARTIDAS_GANADAS,e_aux);
            comprueba_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS,e_aux);
        }
        i = existe_en_ranking(id2,nick2);
        if (i != -1) { //Jugador2
            ElementoRanking e_aux = consultar_elemento_i(i);
            comprueba_logro(Logros.tipoLogro.PARTIDAS_TOTALES,e_aux);
            comprueba_logro(Logros.tipoLogro.PARTIDAS_GANADAS,e_aux);
            comprueba_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS,e_aux);
        }
    }


    /**
     * funcion compare en funcion de PartidasGanadas
     * */
    static class SortbyGanadas implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.consultar_Ganadas() - e1.consultar_Ganadas());
        }
    }

    /**
     * funcion compare en funcion de PartidasPerdidas
     * */
    static class SortbyPerdidas implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.consultar_Perdidas() - e1.consultar_Perdidas());
        }
    }

    /**
     * funcion compare en funcion de PartidasEmpatadas
     * */
    static class SortbyEmpatadas implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.consultar_Empatadas() - e1.consultar_Empatadas());
        }
    }

    /**
     * funcion compare en funcion de PartidasTotales
     * */
    static class SortbyTotales implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.consultar_Totales() - e1.consultar_Totales());
        }
    }

    /**
     * funcion compare en funcion de ID de los Jugadores
     * */
    static class SortbyID implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e2.consultar_ID() - e1.consultar_ID());
        }
    }

    /**
     * funcion compare en funcion de ID de los Jugadores
     * */
    static class SortbyIDMenor implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return (e1.consultar_ID() - e2.consultar_ID());
        }
    }

    /**
     * funcion compare en funcion de los nickname's de los Jugadores
     * */
    static class SortbyNICKNAME implements Comparator<ElementoRanking> {
        public int compare(ElementoRanking e1, ElementoRanking e2) {
            return e1.consultar_Nickname().compareTo(e2.consultar_Nickname());
        }
    }
}
