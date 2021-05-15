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



    public Logros() {
        //partida mas corta (en tema de turnos)
        nick1_logro = new String();
        nick2_logro = new String();
        id1_logro = -1;
        id2_logro = -1;
        turnos_logro = 60;
        //Maximas capturas posibles
        nick1_captura = new String();
        nick2_captura = new String();
        id1_captura = -1;
        id2_captura = -1;
        capturas = 0;

        j_total = new String();
        id_j_totales = 0;
        partidas_totales = 0;

        j_perdida = new String();
        id_j_perdida = 0;
        partidas_perdidas = 0;

        j_ganadas = new String();
        id_j_ganadas = 0;
        partidas_ganadas = 0;

    }

    public boolean comprueba_logro_partida(int turno) {
        return (turno < turnos_logro);
    }

    public boolean comprueba_logro_capturas(int cap) {
        return (cap > capturas);
    }

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
                this.id2_captura = id1;
                this.nick2_captura = nick2;
                this.id2_captura = id2;
                this.capturas = t;
                break;
        }
    }


    public String consultar_partida_corta() {
        return (turnos_logro + " " + id1_logro + " " + nick1_logro + " " + id2_logro + " " + nick2_logro);
    }

    public String consultar_max_capturas() {
        return (capturas+ " " + id1_captura + " " + nick1_captura + " " + id2_captura + " " + nick2_captura);
    }


    public boolean comprueba_logro_partidas(tipoLogro tipo, int t) {
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


    public String consultar_jugador_Totales() {
        return (partidas_totales + " " + id_j_totales + " " + j_total);
    }

    public String consultar_jugador_Ganadas() {
        return (partidas_ganadas + " " + id_j_ganadas + " " + j_ganadas);
    }

    public String consultar_jugador_perdidas() {
        return (partidas_perdidas + " " + id_j_perdida + " " + j_perdida);
    }

}

