package Dominio.Ranking;

import MyException.MyException;

/** Clase para representar toda la informacion para el Ranking referente a un Jugador*/
public class ElementoRanking {

    /** Identificador del Jugador*/
    private final int idJugador;

    /** Nickname del Jugador*/
    private final String nickJugador;

    /** Numero de partidas ganadas por el Jugador*/
    private int partidasGanadas;

    /** Numero de partidas perdidas por el Jugador*/
    private int partidasPerdidas;

    /** Numero de partidas empatadas por el Jugador*/
    private int partidasEmpatadas;

    /** total de partidas jugadas por el jugador: Ganadas+Perdidas+Empatadas*/
    private int totalPartidasJugadas;


    /*Constructora*/
    /**
     * Constructora de ElementoRanking(id,nick) con contadores de partidas a 0
     * @param id identificador de Usuario
     * @param nick nickname de Usuario
     * @throws MyException en caso de fallo con el identificador [pertenece a un ID de maquina: id menor a 6]
     * */
    public ElementoRanking (int id, String nick) throws Exception {
        if (id < 0) throw new MyException(MyException.tipoExcepcion.ID_NEGATIVO,id);
        else if (id < 6) throw new MyException(MyException.tipoExcepcion.ID_MAQUINA,id);
        else {
            this.idJugador = id;
            this.nickJugador = nick;
            this.totalPartidasJugadas = 0;
            this.partidasEmpatadas = 0;
            this.partidasGanadas = 0;
            this.partidasPerdidas = 0;
        }
    }


    /**
     * Constructora de ElementoRanking(id,nick) con contadores inicializados
     * @param id identificador de Usuario
     * @param nick nickname de Usuario
     * @param ganadas contador de Partidas Ganadas
     * @param perdidas contador de Partidas perdidas
     * @param empatadas contador de Partidas empatadas
     * @throws MyException en caso de fallo con el identificador [pertenece a un ID de maquina: id menor 6]
     * */
    public ElementoRanking (int id, String nick,  int ganadas, int perdidas, int empatadas) throws Exception {
        if (id < 0) throw new MyException(MyException.tipoExcepcion.ID_NEGATIVO,id);
        else if (id < 6) throw new MyException(MyException.tipoExcepcion.ID_MAQUINA,id);
        else {
            this.idJugador = id;
            this.nickJugador = nick;
            this.totalPartidasJugadas = ganadas + empatadas + perdidas;
            this.partidasEmpatadas = empatadas;
            this.partidasGanadas = ganadas;
            this.partidasPerdidas = perdidas;
        }
    }


    /**
     * Este metodo incrementa el contador de partidas ganadas y partidas jugadas
     */
    public void incrementar_partida_ganada() {
        this.partidasGanadas = this.partidasGanadas + 1;
        this.totalPartidasJugadas = this.totalPartidasJugadas + 1;
    }

    /**
     * Este metodo incrementa el contador de partidas perdidas y partidas jugadas
     */
    public void incrementar_partida_perdida() {
        this.partidasPerdidas = this.partidasPerdidas + 1;
        this.totalPartidasJugadas = this.totalPartidasJugadas + 1;
    }

    /**
     * Este metodo incrementa el contador de partidas empatadas y partidas jugadas
     */
    public void incrementar_partida_empatada() {
        this.partidasEmpatadas = this.partidasEmpatadas + 1;
        this.totalPartidasJugadas = this.totalPartidasJugadas + 1;
    }

    /**
     * Operacion get del atributo idJugador
     * @return devuelve el id del Jugador
     */
    public int consultar_ID() {
        return this.idJugador;
    }

    /**
     * Operacion get del atributo nickJugador
     * @return devuelve el nickname del Jugador
     */
    public String consultar_Nickname() {return this.nickJugador;}

    /**
     * Operacion get del atributo partidas ganadas
     * @return devuelve el contador de partidas ganadas del Jugador
     */
    public int consultar_Ganadas() {return this.partidasGanadas;}

    /**
     * Operacion get del atributo partidas perdidas
     * @return devuelve el contador de partidas perdidas del Jugador
     */
    public int consultar_Perdidas() {return this.partidasPerdidas;}

    /**
     * Operacion get del atributo partidas empatadas
     * @return devuelve el contador de partidas empatadas del Jugador
     */
    public int consultar_Empatadas() {return this.partidasEmpatadas;}

    /**
     * Operacion get del atributo partidas totales jugadas
     * @return devuelve el contador de partidas totales del Jugador
     */
    public int consultar_Totales() {return this.totalPartidasJugadas;}

    /**
     * Este metodo genera una String con la informacion del Jugador y toda la informacion sobre las partidas de esta misma
     * @return devuelve la String con la informacion de idJugador, nickJugador y todos los contadores de partidas
     */
    public String consultar_todo() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas + " " + this.partidasPerdidas + " " + this.partidasEmpatadas + " " + this.totalPartidasJugadas);
    }
}
