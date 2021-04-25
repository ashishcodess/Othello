package Dominio;

import MyException.MyException;
import java.io.IOException;

//Clase de Partida MODO 1 (JugadorPersona vs Maquina)

/*Sergio: problema no detecta los atributos del padre, no entiendo porque falla */

public class PartidaModo1 extends Partida {

    /*Atributos*/
    private JugadorPersona j1;
    private JugadorMaquina j2;
    private Tablero t;          //Sergi C: necesario para llamar a la IA de jugadorMaquina


    public PartidaModo1(int id, int modoJuego, int[] r, int turn, int idj1, int idj2) throws MyException {
        super(id,modoJuego,r,turn,idj1,idj2);
        this.j1 = new JugadorPersona(idj1);
        this.j2 = new JugadorMaquina(idj2);
    }

    public PartidaModo1(int id, int modoJuego, int[] r, int turn, int idj1, String n1, int idj2, String n2, Tablero t) throws IOException, MyException  {
        super(id,modoJuego,r,turn,idj1,n1,idj2,n2,t);

        this.j1 = new JugadorPersona(idj1,n1);
        this.j2 = new JugadorMaquina(idj2);
    }

    //Similar rondaPartida del padre pero con llamada de colocar ficha y comportamiento de IA's
    @Override
    public int rondaPartida(String[] accion) {
        int res = -1;
        int x = -1; int y = -1;
        switch (accion[0]) {
            case "colocar":
                this.j2.valorMax(this.t, x, y);         //Sergi C: de momento estos son los parametros pero alomejor hay
                //que cambiar algo(esto solo lo implementa j2 se tendria que hacerw la parte de j1)
                //if(x != -1 && y != -1)this.tablero.setCasilla_tipo(x, y, tipo);
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