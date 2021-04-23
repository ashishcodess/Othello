package Dominio;

//Clase de Partida MODO 0 (Maquina vs Maquina)
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

    //Similar rondaPartida del padre pero con llamada de colocar ficha y comportamiento de IA's
    public int rondaPartida(String[] accion) {
        int res = -1;
        return res; //Sergio:para que compile correctamente
    }
}

