package jugador;


public class ElementoRanking implements Comparable<ElementoRanking> {
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

    public int getID() {
        return this.idJugador;
    }

    public String getNickname() {return this.nickJugador;}

    public String consultar_ganadas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas);
    }

    public String consultar_perdidas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasPerdidas);
    }

    public String consultar_all() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas + " " + this.partidasPerdidas + this.totalPartidasJugadas);
    }

    //FUNCION PARA PODERLO ORDENAR EN FUNCION DE LAS PARTIDAS GANADAS
    @Override
    public int compareTo(ElementoRanking e) {
        if (partidasGanadas < e.partidasGanadas) return -1;
        if (partidasGanadas > e.partidasGanadas) return 1;
        return 0;
    }
}
