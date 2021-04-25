package Dominio;

//Clase de Partida MODO 0 (Maquina vs Maquina)

/*Sergio: problema no detecta los atributos del padre, no entiendo porque falla */

public class PartidaModo0 extends Partida {

    /*Atributos*/
    private JugadorMaquina j1;
    private JugadorMaquina j2;


    public PartidaModo0(int id, int modoJuego, int[] r, int turn, int idj1, int idj2) {
        super(id,modoJuego,r,turn,idj1,idj2);
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
        switch (accion[0]) {
            case "colocar":
                //this.tablero.setCasilla_tipo(x, y, tipo);
                super.incrementar_turno();
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
                super.incrementar_turno();
                break;
        }
        return res; //Sergio:para que compile correctamente
    }



}

