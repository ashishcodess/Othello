package Dominio;

import MyException.MyException;

import java.io.IOException;


//Clase de Partida MODO 2 (JugadorPersona vs JugadorPersona)

/*Sergio: problema no detecta los atributos del padre, no entiendo porque falla */

public class PartidaModo2 extends Partida {

    /*Atributos*/
    private JugadorPersona j1;
    private JugadorPersona j2;


    public PartidaModo2(int id, int modoJuego, int[] r, int turn, int idj1, int idj2) throws MyException  {
        super(id,modoJuego,r,turn,idj1,idj2);
        this.j1 = new JugadorPersona(idj1);
        this.j2 = new JugadorPersona(idj2);
    }

    public PartidaModo2(int id, int modoJuego, int[] r, int turn, int idj1, String n1, int idj2, String n2, Tablero t) throws IOException, MyException  {
        super(id,modoJuego,r,turn,idj1,n1,idj2,n2,t);
        this.j1 = new JugadorPersona(idj1,n1);
        this.j2 = new JugadorPersona(idj2,n2);
    }

    //Similar rondaPartida del padre pero con llamada de colocar ficha de cada jugador
    @Override
    public int rondaPartida(String[] accion) {
        int res = -1;
        switch (accion[0]) {
            case "colocar":
                //this.tablero.setCasilla_tipo(x, y, tipo);
                super.incrementar_turno();
                //actualizarTablero();
                break;
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