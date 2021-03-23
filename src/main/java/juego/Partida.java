package juego;

public class Partida {
    private int id;
    private String modoDeJuego;
    private String reglas;
    private int turno;
    private int idJugador1;
    private int idJugador2;
    private Casilla[][] tablero;
    //private Resultado resultado;

    //Creadora - Configuración de los parámetros de una partida
    public Partida (int id, String modoJuego, String r, int turn, int idj1, int idj2) {
        this.id = id;
        this.modoDeJuego = modoJuego;
        this.reglas = r;
        this.turno = turn;
        this.idJugador1 = idj1;
        this.idJugador2 = idj2;
    }
    
    //Devuelve un boleano indicando si el jugador está en la partida
    public Boolean existeJugador(int id){
        return idJugador1 == id or idJugador2 == id;
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

    public int getIDJugador1Partida(int idPartida) {
        if (this.id == idPartida) return this.idJugador1;
        return null;
    }
    public int getIDJugador2Partida(int idPartida) {
        if (this.id == idPartida) return this.idJugador2;
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