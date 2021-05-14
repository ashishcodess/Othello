package Dominio;

public class Logros {

    public enum tipoLogro{PARTIDA_CORTA, CAPTURAS}

    //partida mas corta (en tema de turnos)
    private String nick1_logro, nick2_logro;
    private int id1_logro, id2_logro;
    private int turnos_logro;

    //Maximas capturas posibles
    private String nick1_captura, nick2_captura;
    private int id1_captura, id2_captura;
    private int capturas;

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

        public boolean comprueba_logro_partida(int turno) {
            return (turno < turnos_logro);
        }

        public boolean comprueba_logro_capturas(int cap) {
            return (cap > capturas);
        }

        public String consultar_partida_corta() {
            return ("Turnos: " + turnos_logro + " , logrado en Partida con jugadores J1[" + id1_logro + " , " + nick1_logro + "] - J2[" + id2_logro + " , " + nick2_logro + "]");
        }

        public String consultar_max_capturas() {
            return ("Capturas: " + capturas + " , logrado en Partida con jugadores J1[" + id1_captura + " , " + nick1_captura + "] - J2[" + id2_captura + " , " + nick2_captura + "]");
        }
}

