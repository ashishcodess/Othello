package Dominio;

import MyException.MyException;

public class ElementoRanking {
    /*Atributos*/

    private final int idJugador;
    private String nickJugador;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int partidasEmpatadas;

    /** total de partidas jugadas por el jugador: Ganadas+Perdidas+Empatadas*/
    private int totalPartidasJugadas;


    /*Constructora*/
    /**
     * Constructora de ElementoRanking(id,nick) con contadores de partidas a 0
     * */

    /**
     * Constructora de ElementoRanking(id,nick) con contadores de partidas a 0
     * @param id identificador de Usuario
     * @param nick nickname de Usuario
     * @throws MyException en caso de fallo con el identificador [pertenece a un ID de maquina: id menor a 6]
     * */
    public ElementoRanking (int id, String nick) throws MyException {
        if (id < 6) throw new MyException("El ID:" + id + " pertenece a una maquina o esta fuera de rango(es negativo)");
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
     * Constructora de ElementoRanking(id,nick) con asignacion de contadores de partidas (pasados como parametros)
     * */

    /**
     * Constructora de ElementoRanking(id,nick) con contadores inicializados
     * @param id identificador de Usuario
     * @param nick nickname de Usuario
     * @param ganadas contador de Partidas Ganadas
     * @param perdidas contador de Partidas perdidas
     * @param empatadas contador de Partidas empatadas
     * @param total contador de Partidas totales
     * @throws MyException en caso de fallo con el identificador [pertenece a un ID de maquina: id menor 6]
     * */
    public ElementoRanking (int id, String nick,  int ganadas, int perdidas, int empatadas,int total) throws MyException {
        if (id < 6) throw new MyException("El ID:" + id + " pertenece a una maquina o esta fuera de rango(es negativo)");
        /*Se puede eliminar esta excepcion haciendo eliminando parametro totales*/
        else if (total != (ganadas+perdidas+empatadas)) throw new MyException("Error al introducir partidas totales");
        else {
            this.idJugador = id;
            this.nickJugador = nick;
            this.totalPartidasJugadas = total;
            this.partidasEmpatadas = empatadas;
            this.partidasGanadas = ganadas;
            this.partidasPerdidas = perdidas;
        }
    }


    /**
     * Operacion set del atributo nickJugador
     * @param nick indica el valor que tomara el atributo nickJugador
     */
    public void modificar_nickname(String nick){ this.nickJugador=nick; }

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
    public int getID() {
        return this.idJugador;
    }

    /**
     * Operacion get del atributo nickJugador
     * @return devuelve el nickname del Jugador
     */
    public String getNickname() {return this.nickJugador;}

    /**
     * Operacion get del atributo partidas ganadas
     * @return devuelve el contador de partidas ganadas del Jugador
     */
    public int getGanadas() {return this.partidasGanadas;}

    /**
     * Operacion get del atributo partidas perdidas
     * @return devuelve el contador de partidas perdidas del Jugador
     */
    public int getPerdidas() {return this.partidasPerdidas;}

    /**
     * Operacion get del atributo partidas empatadas
     * @return devuelve el contador de partidas empatadas del Jugador
     */
    public int getEmpatadas() {return this.partidasEmpatadas;}

    /**
     * Operacion get del atributo partidas totales jugadas
     * @return devuelve el contador de partidas totales del Jugador
     */
    public int getTotales() {return this.totalPartidasJugadas;}

    /**
     * Este metodo genera una String con la informacion del Jugador y las partidas ganadas
     * @return devuelve la String con la informacion de idJugador, nickJugador y partidasGanadas
     */
    public String consultar_ganadas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas);
    }


    /**
     * Este metodo genera una String con la informacion del Jugador y las partidas perdidas
     * @return devuelve la String con la informacion de idJugador, nickJugador y partidasPerdidas
     */
    public String consultar_perdidas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasPerdidas);
    }

    /**
     * Este metodo genera una String con la informacion del Jugador y las partidas empatadas
     * @return devuelve la String con la informacion de idJugador, nickJugador y partidasEmpatadas
     */
    public String consultar_empatadas() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasEmpatadas);
    }

    /**
     * Este metodo genera una String con la informacion del Jugador y toda la informacion sobre las partidas de esta misma
     * @return devuelve la String con la informacion de idJugador, nickJugador y todos los contadores de partidas
     */
    public String consultar_all() {
        return (this.idJugador + " " + this.nickJugador + " " + this.partidasGanadas + " " + this.partidasPerdidas + " " + this.partidasEmpatadas + " " + this.totalPartidasJugadas);
    }
}
