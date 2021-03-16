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
    }

    public String getReglasPartida(int idPartida) {
        if (this.id == idPartida) return this.reglas;
    }

    public int getTurnoPartida(int idPartida) {
        if (this.id == idPartida) return this.turno;
    }

    public String getJugador1Partida(int idPartida) {
        if (this.id == idPartida) return this.jugador1;
    }
    public String getJugador2Partida(int idPartida) {
        if (this.id == idPartida) return this.jugador2;
    }
}