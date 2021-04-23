package Dominio;

import MyException.MyException;

import java.io.IOException;

//Clase de Partida MODO 1 (JugadorPersona vs Maquina)
public class PartidaModo1 extends Partida {

    /*Atributos*/
    private JugadorPersona j1;
    private JugadorMaquina j2;


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
        return res; //Sergio:para que compile correctamente
    }


}