
package Dominio;

import MyException.MyException;

import java.io.*;

public class JugadorPersona extends Jugador {
    /*Atributos*/
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
     * */
    public JugadorPersona (int idJugador) throws MyException {
        super(idJugador);
        if (idJugador < 6) throw new MyException("El ID:" + idJugador + " pertenece a una maquina o esta fuera de rango(es negativo)");
    }

    /**
     * Constructora JugadorPersona (idJugador,nicknameJugador, además de crear Fichero Usuario en caso de no existir)
     * */
    public JugadorPersona (int idJugador,String nicknameJugador) throws IOException, MyException {
        super(idJugador);
        if (idJugador < 6) throw new MyException("El ID:" + idJugador + " pertenece a una maquina o esta fuera de rango(es negativo)");
        this.nickname = nicknameJugador;

        /*//Crear fichero "idJugador_nickname"
        this.persona_nueva = crearFicheroUsuario(idJugador,nicknameJugador);*/
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


    /*Devuelve el conjunto formado por nickname y ID -> nickname#ID */
    /**
     * Operacion get del conjunto nickname#ID
     * @return  devuelve el conjunto identificado como nickname#ID
     */
    public String get_ID_TAG_persona() {
        return (this.nickname + "#" + String.valueOf(super.getID()));
    }

}
