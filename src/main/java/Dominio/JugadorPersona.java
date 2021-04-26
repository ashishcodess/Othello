
package Dominio;

import MyException.MyException;

import java.io.*;

public class JugadorPersona extends Jugador {
    /*Atributos*/
    /** Atributo nickname de JugadorPersona*/
    private String nickname;

    /*Constructora*/
    /**
     * Constructora por defecto (vacia) de JugadorPersona
     * */
    public JugadorPersona ()  {
        super();
    }

    /**
     * Constructora JugadorPersona (sin asignarle un nickname y sin crear fichero de la Persona)
     * @param idJugador (id de Jugador = idJugador)
     * @throws MyException en caso de idJugador menor a 6 (pertenece a ID de maquina)
     * */
    public JugadorPersona (int idJugador) throws MyException {
        super(idJugador);
        if (idJugador < 6) throw new MyException("El ID:" + idJugador + " pertenece a una maquina o esta fuera de rango(es negativo)");
    }

    /**
     * Constructora JugadorPersona (idJugador,nicknameJugador)
     * @param idJugador (id de Jugador = idJugador)
     * @param nicknameJugador (nickname de Persona = nicknameJugador)
     * @throws MyException en caso de idJugador menor a 6 (pertenece a ID de maquina)
     * */
    public JugadorPersona (int idJugador,String nicknameJugador) throws MyException {
        super(idJugador);
        if (idJugador < 6) throw new MyException("El ID:" + idJugador + " pertenece a una maquina o esta fuera de rango(es negativo)");
        this.nickname = nicknameJugador;
    }

    /*Sets y Gets*/

    /**
     * Operacion set del atributo ID
     * @param nuevoID indica el nuevo valor que tomara el atributo id de Jugador
     */
    public void modificar_IDJugador(int nuevoID) {
        super.modificar_id(nuevoID);
    }

    /**
     * Operacion set del atributo nickName
     * @param nuevoNick indica el nuevo valor que tomara el atributo nickname de JugadorPersona
     */
    public void modificar_nickname(String nuevoNick) {
        this.nickname = nuevoNick;
    }

    /**
     * Operacion get del atributo nickname
     * @return  devuelve el nickname de JugadorPersona
     */
    public String get_Nickname() {
        return this.nickname;
    }

    /**
     * Operacion get del atributo ID
     * @return  devuelve el ID de JugadorPersona
     */
    public int get_PersonaID() {
        return super.getID();
    }



}
