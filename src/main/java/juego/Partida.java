package juego;

public class Partida {
    private int id;
    private String modoDeJuego;
    private String reglas;
    private int turno;
    private String jugador1;
    private String jugador2;
    private Tablero tablero;
    private Resultado resultado;

    public Partida (int id) {this.id = id;}

    public int getIdPartida() {return this.id;}

    public String getModoDeJuegoPartida(int idPartida) {
        if (this.id == idPartida) return this.modoDeJuego;
        return null;
    }

    public String getReglasPartida(int idPartida) {
        if (this.id == idPartida) return this.reglas;
        return null;
    }

    public int getTurnoPartida(int idPartida) {
        if (this.id == idPartida) return this.turno;
        return idPartida;
    }

    public String getJugador1Partida(int idPartida) {
        if (this.id == idPartida) return this.jugador1;
        return null;
    }
    public String getJugador2Partida(int idPartida) {
        if (this.id == idPartida) return this.jugador2;
        return null;
    }
    //Configuración de los parámetros de una partida
    public void setPartida(String modoJuego, String r, int turn, String j1, String j2){
        modoDeJuego = modoJuego;
        reglas = r;
        turno = turn;
        jugador1 = j1;
        jugador2 = j2;
    }
}