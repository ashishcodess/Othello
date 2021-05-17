
package Dominio.Jugador;

import Dominio.Partida.Tablero;
import MyException.MyException;

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
        if (idJugador < 6 && idJugador >= 0) throw new MyException(MyException.tipoExcepcion.ID_PERSONA,idJugador);
    }

    /**
     * Constructora JugadorPersona (idJugador,nicknameJugador)
     * @param idJugador (id de Jugador = idJugador)
     * @param nicknameJugador (nickname de Persona = nicknameJugador)
     * @throws MyException en caso de idJugador menor a 6 (pertenece a ID de maquina)
     * */
    public JugadorPersona (int idJugador,String nicknameJugador) throws MyException {
        super(idJugador);
        if (idJugador < 6 && idJugador >= 0) throw new MyException(MyException.tipoExcepcion.ID_PERSONA,idJugador);
        this.nickname = nicknameJugador;
    }

    /*Sets y Gets*/

    /**
     * Operacion get del atributo nickname
     * @return  devuelve el nickname de JugadorPersona
     */
    @Override
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

    /**
     * Operacion posicion (aqui no se usa, es para la IA)*/
    public Tablero posicion(Tablero tablero, int turno){
        return tablero;
    }

}
