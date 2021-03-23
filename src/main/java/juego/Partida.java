package juego;

public class Partida {
    private int id;
    private String modoDeJuego;
    private String reglas;
    private int turno;
    private String jugador1;
    private String jugador2;
    private Casilla[][] tablero;
    //private Resultado resultado;

    //Creadora - Configuración de los parámetros de una partida
    public Partida (int id, String modoJuego, String r, int turn, String j1, String j2) {
        this.id = id;
        this.modoDeJuego = modoJuego;
        this.reglas = r;
        this.turno = turn;
        this.jugador1 = j1;
        this.jugador2 = j2;
    }

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

    //Guarda la partida actual y todos sus datos en un fichero
    public void guardarPartida(){

    }

    //Imprime el tablero con las casillas disponibles marcadas
    public void generarCasillesDisponibles(){

    }

    //Actualiza el tablero de la partida
    public void actualizarTablero() {
        this.tablero = Tablero::getTablero();
    }
}