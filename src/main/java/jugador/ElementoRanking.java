package jugador;

import MyException.MyException;


public class ElementoRanking {
    /*Atributos*/
    private int idJugador;
    private String nickJugador;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int partidasEmpatadas;
    private int totalPartidasJugadas;
    //Se le puede agregar mas como porcentajes y esas cosas


    /*Constructora*/
    public ElementoRanking (int id, String nick) throws MyException {
        if (id < 6 && id >= 0) {throw new MyException("El ID:" + id + " pertenece a una maquina");}
        else {
            this.idJugador = id;
            this.nickJugador = nick;
            this.totalPartidasJugadas = 0;
            this.partidasEmpatadas = 0;
            this.partidasGanadas = 0;
            this.partidasPerdidas = 0;
        }
    }

    public ElementoRanking (int id, String nick,  int ganadas, int perdidas, int empatadas,int total) throws MyException {
        if (id < 6 && id >= 0) {
            throw new MyException("El ID:" + id + " pertenece a una maquina");
        }
        else {
            this.idJugador = id;
            this.nickJugador = nick;
            this.totalPartidasJugadas = total;
            this.partidasEmpatadas = empatadas;
            this.partidasGanadas = ganadas;
            this.partidasPerdidas = perdidas;
        }
    }

    public void modificar_nickname(String nick){ this.nickJugador=nick; }

    public void incrementar_partida_ganada() {
        this.partidasGanadas = this.partidasGanadas + 1;
        this.totalPartidasJugadas = this.totalPartidasJugadas + 1;
    }

    public void incrementar_partida_perdida() {
        this.partidasPerdidas = this.partidasPerdidas + 1;
        this.totalPartidasJugadas = this.totalPartidasJugadas + 1;
    }

    public void incrementar_partida_empatada() {
        this.partidasEmpatadas = this.partidasEmpatadas + 1;
        this.totalPartidasJugadas = this.totalPartidasJugadas + 1;
    }

    public int getID() {
        return this.idJugador;
    }

    public String getNickname() {return this.nickJugador;}

    public int getGanadas() {return this.partidasGanadas;}

    public int getPerdidas() {return this.partidasPerdidas;}

    public int getEmpatadas() {return this.partidasEmpatadas;}

    public int getTotales() {return this.totalPartidasJugadas;}

    public String consultar_ganadas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas);
    }

    public String consultar_perdidas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasPerdidas);
    }

    public String consultar_empatadas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasEmpatadas);
    }

    public String consultar_all() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas + " " + this.partidasPerdidas + " " + this.partidasEmpatadas + " " + this.totalPartidasJugadas);
    }
}
