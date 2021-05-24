package Dominio.Ranking;

/**Coleccion de Logros*/
public class Logros {

    /**Tipo de Logro [Partida mas corta, Mayor diferencia de fichas, Jugador con mas partidas jugadas,
     * Jugador con mas partidas ganadas, Jugador con mas partidas perdidas]*/
    public enum tipoLogro{PARTIDA_CORTA, FICHAS_DIFF, PARTIDAS_TOTALES, PARTIDAS_GANADAS, PARTIDAS_PERDIDAS}

    //partida mas corta (en tema de turnos)
    /**Nickname del jugador 1 (con logro de Partida mas corta)*/
    private String nick1_logro;
    /**Nickname del jugador 2 (con logro de Partida mas corta)*/
    private String nick2_logro;
    /**Identificador del jugador 1 (con logro de Partida mas corta)*/
    private int id1_logro;
    /**Identificador del jugador 2 (con logro de Partida mas corta)*/
    private int id2_logro;
    /**Total de turno de la partida mas corta*/
    private int turnos_logro;

    //Maximas capturas posibles
    /**Nickname del jugador 1 (con logro de Partida con mas diferencia de fichas)*/
    private String nick1_fichas;
    /**Nickname del jugador 2 (con logro de Partida con mas diferencia de fichas)*/
    private String nick2_fichas;
    /**Identificador del jugador 1 (con logro de Partida con mas diferencia de fichas)*/
    private int id1_fichas;
    /**Identificador del jugador 2 (con logro de Partida con mas diferencia de fichas)*/
    private int id2_fichas;
    /**Diferencia de fichas*/
    private int fichas_diff;
    /**Fichas del Jugador 1*/
    private int fichas_j1;
    /**Fichas del Jugador 2*/
    private int fichas_j2;

    //Jugador que mas ha jugado
    /**Nickname del jugador con mayor partidas totales*/
    String j_total;
    /**Identificador del jugador con mayor partidas totales*/
    int id_j_totales;
    /**Total de partidas jugadas por el jugador con dicho logro*/
    int partidas_totales;

    //Jugador que mas partidas ha perdido
    /**Nickname del jugador con mayor partidas perdidas*/
    String j_perdida;
    /**Identificador del jugador con mayor partidas perdidas*/
    int id_j_perdida;
    /**Total de partidas perdidas por el jugador con dicho logro*/
    int partidas_perdidas;

    //Jugador que mas partidas ha ganado
    /**Nickname del jugador con mayor partidas ganadas*/
    String j_ganadas;
    /**Identificador del jugador con mayor partidas ganadas*/
    int id_j_ganadas;
    /**Total de partidas ganadas por el jugador con dicho logro*/
    int partidas_ganadas;


    /**
     * Constructora por defecto (logros vacio)
     * */
    public Logros() {
        //partida mas corta (en tema de turnos)
        nick1_logro = "";
        nick2_logro = "";
        id1_logro = -1;
        id2_logro = -1;
        turnos_logro = 60;

        //Maxima diferencia de fichas
        nick1_fichas = "";
        nick2_fichas = "";
        id1_fichas = -1;
        id2_fichas = -1;
        fichas_diff = 0;

        //PARTIDAS TOTALES
        j_total = "";
        id_j_totales = 0;
        partidas_totales = 0;

        //PARTIDAS GANADAS
        j_ganadas = "";
        id_j_ganadas = 0;
        partidas_ganadas = 0;

        //PARTIDAS PERDIDAS
        j_perdida = "";
        id_j_perdida = 0;
        partidas_perdidas = 0;
    }

    /**
     * Comprobar logro partida mas corta
     * @param turno es el turno a comprobar si es menor al que ya tenemos como logro
     * @return devuelve TRUE en caso de que se de que turno pasado como parametro sea inferior al del logro
     * */
    public boolean comprueba_logro_partida(int turno) {
        return ((turno < turnos_logro) && (turno > 20));
    }


    /**
     * Comprobar logro captura
     * @param cap es el total de capturas realizadas en toda la partida
     * @return devuelve TRUE en caso de que se de que las capturas pasadas como parametro sea superior al del logro
     * */
    public boolean comprueba_logro_capturas(int cap) {
        return (cap > fichas_diff);
    }


    /**
     * Calcular diferencia fichas
     * @param fichas_j1 numero de fichas del jugador 1
     * @param fichas_j2 numero de fichas del jugador 2
     * @return devuelve la diferencia maxima respecto a las fichas de los jugadores
     * */
    private int calcular_diferencia_fichas(int fichas_j1,int fichas_j2) {
        int res_diff = 0;
        if (fichas_j1 > fichas_j2) res_diff = (fichas_j1 - fichas_j2);
        else if (fichas_j1 < fichas_j2) res_diff = (fichas_j2 - fichas_j1);
        return res_diff;
    }

    /**
     * Cambiar logro partida (menos turnos o mas capturas)
     * @param tipo tipo de logro a realizar el cambio (PARTIDA_CORTA o CAPTURAS)
     * @param nick1 nickname del Jugador1 (en caso de que tenga nickname)
     * @param id1 identificador del Jugador1
     * @param nick2 nickname del Jugador2 (en caso de que tenga nickname)
     * @param id2 identificador del Jugador2
     * @param t1 es el numero entero a reemplazar dependiendo del tipo seleccionado en caso de FICHAS DIFF es el numero de fichas de del jugador 1
     * @param t2 es el numero de fichas de del jugador 2
     * */
    public void cambiar_logro_partida(tipoLogro tipo, String nick1, int id1, String nick2, int id2, int t1, int t2) {
        switch (tipo) {
            case PARTIDA_CORTA:
                this.nick1_logro = nick1;
                this.id1_logro = id1;
                this.nick2_logro = nick2;
                this.id2_logro = id2;
                this.turnos_logro = t1;
                break;

            case FICHAS_DIFF: //FALTA AR
                this.nick1_fichas= nick1;
                this.id1_fichas = id1;
                this.nick2_fichas = nick2;
                this.id2_fichas = id2;
                this.fichas_diff = calcular_diferencia_fichas(t1,t2);
                this.fichas_j1 = t1;
                this.fichas_j2 = t2;
                break;
        }
    }


    /**
     * Comprobar logro jugadores
     * @param tipo tipo de Logro de jugadores a comprobar (PARTIDAS_TOTALES, PARTIDAS_GANADAS, PARTIDAS_PERDIDAS)
     * @param t entero a comprobar la condicion
     * @return devuelve TRUE en caso de que se cumpla la condicion de que el entero pasado por parametro sea mayor
     * al que hay almacenado
     * */
    public boolean comprueba_logro_jugadores(tipoLogro tipo, int t) {
        boolean b = false;
        switch (tipo) {
            case PARTIDAS_TOTALES:
                b = (t > partidas_totales);
                break;
            case PARTIDAS_GANADAS:
                b = (t > partidas_ganadas);
                break;
            case PARTIDAS_PERDIDAS:
                b = (t > partidas_perdidas);
                break;
        }
        return b;
    }

    /**
     * Cambiar logro jugador
     * @param tipo tipo de logro a realizar el cambio (PARTIDAS_TOTALES, PARTIDAS_GANADAS, PARTIDAS_PERDIDAS)
     * @param nick nickname del Jugador (en caso de que tenga nickname)
     * @param id identificador del Jugador1
     * @param t es el numero entero a reemplazar dependiendo del tipo seleccionado
     * */
    public void cambiar_logro_jugador(tipoLogro tipo, String nick, int id, int t) {
        switch (tipo) {
            case PARTIDAS_TOTALES:
                j_total = nick;
                id_j_totales = id;
                partidas_totales = t;
                break;

            case PARTIDAS_GANADAS:
                j_ganadas = nick;
                id_j_ganadas = id;
                partidas_ganadas = t;
                break;

            case PARTIDAS_PERDIDAS:
                j_perdida = nick;
                id_j_perdida = id;
                partidas_perdidas = t;
                break;
        }
    }

    /**
     * Consultar logro: partida mas corta
     * @return devuelve la informacion del logro de Partida mas corta
     * */
    public String consultar_partida_corta() {
        return (turnos_logro + " " + id1_logro + " " + nick1_logro + " " + id2_logro + " " + nick2_logro);
    }
    /**
     * Consultar logro: Maximo de capturas en una partida
     * @return devuelve la informacion del logro de maximo de capturas dentro de una partida
     * */
    public String consultar_max_fichas_diff() {
        return (fichas_diff+ " " + id1_fichas + " " + nick1_fichas + " " + fichas_j1 + " " + id2_fichas + " " + nick2_fichas + " " + fichas_j2);
    }

    /**
     * Consultar logro: Partidas Totales
     * @return devuelve la informacion del logro de el jugador con mas partidas jugadas
     * */
    public String consultar_jugador_Totales() {
        return (partidas_totales + " " + id_j_totales + " " + j_total);
    }

    /**
     * Consultar logro: Partidas Ganadas
     * @return devuelve la informacion del logro de el jugador con mas Partidas Ganadas
     * */
    public String consultar_jugador_Ganadas() {
        return (partidas_ganadas + " " + id_j_ganadas + " " + j_ganadas);
    }

    /**
     * Consultar logro: Partidas Perdidas
     * @return devuelve la informacion del logro de el jugador con mas Partidas Perdidas
     * */
    public String consultar_jugador_perdidas() {
        return (partidas_perdidas + " " + id_j_perdida + " " + j_perdida);
    }

}

