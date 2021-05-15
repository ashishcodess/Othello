package Dominio;

public class Logros {

    public enum tipoLogro{PARTIDA_CORTA, CAPTURAS, PARTIDAS_TOTALES, PARTIDAS_GANADAS, PARTIDAS_PERDIDAS}

    //partida mas corta (en tema de turnos)
    private String nick1_logro, nick2_logro;
    private int id1_logro, id2_logro;
    private int turnos_logro;

    //Maximas capturas posibles
    private String nick1_captura, nick2_captura;
    private int id1_captura, id2_captura;
    private int capturas;

    //Jugador que mas ha jugado
    String j_total;
    int id_j_totales;
    int partidas_totales;

    //Jugador que mas partidas ha perdido
    String j_perdida;
    int id_j_perdida;
    int partidas_perdidas;

    //Jugador que mas partidas ha ganado
    String j_ganadas;
    int id_j_ganadas;
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
        //Maximas capturas posibles
        nick1_captura = "";
        nick2_captura = "";
        id1_captura = -1;
        id2_captura = -1;
        capturas = 0;

        j_total = "";
        id_j_totales = 0;
        partidas_totales = 0;

        j_perdida = "";
        id_j_perdida = 0;
        partidas_perdidas = 0;

        j_ganadas = "";
        id_j_ganadas = 0;
        partidas_ganadas = 0;

    }

    /**
     * Comprobar logro partida mas corta
     * @param turno es el turno a comprobar si es menor al que ya tenemos como logro
     * @return devuelve TRUE en caso de que se de que turno pasado como parametro sea inferior al del logro
     * */
    public boolean comprueba_logro_partida(int turno) {
        return (turno < turnos_logro);
    }


    /**
     * Comprobar logro captura
     * @param cap es el total de capturas realizadas en toda la partida
     * @return devuelve TRUE en caso de que se de que las capturas pasadas como parametro sea superior al del logro
     * */
    public boolean comprueba_logro_capturas(int cap) {
        return (cap > capturas);
    }


    /**
     * Cambiar logro partida (menos turnos o mas capturas)
     * @param tipo tipo de logro a realizar el cambio (PARTIDA_CORTA o CAPTURAS)
     * @param nick1 nickname del Jugador1 (en caso de que tenga nickname)
     * @param id1 identificador del Jugador1
     * @param nick2 nickname del Jugador2 (en caso de que tenga nickname)
     * @param id2 identificador del Jugador2
     * @param t es el numero entero a reemplazar dependiendo del tipo seleccionado
     * */
    public void cambiar_logro_partida(tipoLogro tipo, String nick1, int id1, String nick2, int id2, int t) {
        switch (tipo) {
            case PARTIDA_CORTA:
                this.nick1_logro = nick1;
                this.id1_logro = id1;
                this.nick2_logro = nick2;
                this.id2_logro = id2;
                this.turnos_logro = t;
                break;

            case CAPTURAS:
                this.nick1_captura = nick1;
                this.id1_captura = id1;
                this.nick2_captura = nick2;
                this.id2_captura = id2;
                this.capturas = t;
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
    public String consultar_max_capturas() {
        return (capturas+ " " + id1_captura + " " + nick1_captura + " " + id2_captura + " " + nick2_captura);
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

