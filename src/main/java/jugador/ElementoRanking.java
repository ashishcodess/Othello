package jugador;

import java.io.IOException;

public class ElementoRanking {
    /*Atributos*/
    private int idJugador;
    private String nickJugador;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int totalPartidasJugadas;
    //Se le puede agregar mas como porcentajes y esas cosas


    /*Constructora*/
    public ElementoRanking (int id, String nick) {
        this.idJugador = id;
        this.nickJugador = nick;
        this.totalPartidasJugadas = 0;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }

    public void incrementar_partida_ganada() {
        this.partidasGanadas = this.partidasGanadas + 1;
        this.totalPartidasJugadas = this.totalPartidasJugadas + 1;
    }

    public void incrementar_partida_perdida() {
        this.partidasPerdidas = this.partidasPerdidas + 1;
        this.totalPartidasJugadas = this.totalPartidasJugadas + 1;
    }

    public String consultar_ganadas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas);
    }

    public String consultar_perdidas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasPerdidas);
    }

    public String consultar_all() {
        String s = this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas + \
                " " + this.partidasPerdidas + this.totalPartidasJugadas;
        return (s);
    }
}
