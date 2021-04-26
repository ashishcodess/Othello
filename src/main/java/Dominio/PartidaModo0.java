package Dominio;

//Clase de Partida MODO 0 (Maquina vs Maquina)

/*Sergio: problema no detecta los atributos del padre, no entiendo porque falla */

public class PartidaModo0 extends Partida {

    /*Atributos*/
    private JugadorMaquina j1;
    private JugadorMaquina j2;
    private Tablero t;              //Sergi C: necesario para llamar a la IA de jugadorMaquina


    public PartidaModo0(int id, int modoJuego, int[] r, int idj1, int idj2) {
        super(id,modoJuego,r,idj1,idj2);
        this.j1 = new JugadorMaquina(idj1);
        this.j2 = new JugadorMaquina(idj2);
    }

    public PartidaModo0(int id, int modoJuego, int[] r, int turn, int idj1, String n1, int idj2, String n2, Tablero t) {
        super(id,modoJuego,r,turn,idj1,n1,idj2,n2,t);
        this.j1 = new JugadorMaquina(idj1);
        this.j2 = new JugadorMaquina(idj2);
    }


    @Override
    public int rondaPartida(String[] accion) {
        int res = -1;
        int x = -1; int y = -1;
        if (super.getFinalizada() == 2) {
            if (this.t.getNumCasillasBlancas() > this.t.getNumCasillasNegras()) {
                super.setGanador(1);
            } else if (this.t.getNumCasillasBlancas() < this.t.getNumCasillasNegras()) {
                super.setGanador(0);
            } else if (this.t.getNumCasillasBlancas() == this.t.getNumCasillasNegras()) {
                super.setGanador(2);
            }
            return 3; //si hay dos turnos sin poder mover ningun jugador, la partida se acaba.
        }
        switch (accion[0]) {
            case "colocar":
                this.t = this.j1.valorMax(this.t, super.getTurnoPartida(), -1000 , 1000, this.j1.get_profundidadMaquina());    //Sergi C: de momento estos son los parametros pero alomejor hay
                //que cambiar algo(tambien hay que implementaresto para las 2 maquinas)
                setTableroPartida(t);
                super.incrementarTurnoPartida();
                /*
                if(x != -1 && y != -1)
                this.tablero.setCasilla_tipo(x, y, tipo);
                 */
                this.t = this.j2.valorMax(this.t, super.getTurnoPartida(), -1000 , 1000, this.j2.get_profundidadMaquina());
                setTableroPartida(t);
                super.incrementarTurnoPartida();
                //actualizarTablero();
                break;
            case "info": //imprimir info de partida
                this.get_info_partida();
                return -1;
            case "guardar": //guardarPartida
                return 2;
            case "finalizar": //finalizarPartida
                return 3;
            case "paso":
                super.incrementarTurnoPartida();
                break;
        }
        return res; //Sergio:para que compile correctamente
    }



}

