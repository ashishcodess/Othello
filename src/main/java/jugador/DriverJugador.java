
package jugador;

import java.util.*;

/*
* PARA VER QUE FUNCIONE CORRECTAMENTE
*
* */

public class DriverJugador {

    static Scanner scan = new Scanner(System.in);

    public static Boolean test_crear_jugador(int IDJugador) {
        Boolean b = true; //para control de errores
        Jugador res = new Jugador(IDJugador);
        System.out.println("Jugador creado con ID:" + String.valueOf(IDJugador));
        System.out.println("Prueba getID: " + String.valueOf(res.getID()));
        System.out.println("Probando modificarID (-1)");
        if (IDJugador != -1 ) {
            res.modificar_id(-1);
            System.out.println("Nuevo ID jugador (getID): " + String.valueOf(res.getID()));
        } else System.out.println("ID del jugador ya es -1");
        return b;
    }

    public static Boolean test_crear_jugadorMaquina(int idMaquina, int profundidad){
        Boolean b = true; //para control de errores
        JugadorMaquina res = new JugadorMaquina(idMaquina,profundidad);
        System.out.println("Maquina creada con ID:" + String.valueOf(idMaquina));
        System.out.println("Prueba get_MaquinaID : " + String.valueOf(res.get_MaquinaID()));
        System.out.println("Prueba get_Profundidad : " + String.valueOf(res.get_profundidadMaquina()));
        System.out.println("Probando modificarID (-1)");
        if (idMaquina != -1 ) {
            res.modificar_id_maquina(-1);
            System.out.println("NUEVO get_MaquinaID : " + String.valueOf(res.get_MaquinaID()));
        } else System.out.println("ID de MAQUINA ya es -1");

        return b;
    }

    public static Boolean test_crear_jugadorPersona(int idPersona, String nick){
        Boolean b = true; //para control de errores
        JugadorPersona res = new JugadorPersona(idPersona,nick);
        return b;
    }

    public static void main(String[] args) {
        System.out.println("OPCIONES:");
        System.out.println("1 - Crear Jugador");
        System.out.println("2 - Crear Jugador Maquina");
        System.out.println("3 - Crear Jugador Persona");
        System.out.println("4 - Crear varias maquinas(6) y jugador ");
        int i_entrada = Integer.parseInt(scan.next());
        switch(i_entrada) {
            case 1:
                test_crear_jugador(2);
                break;
            case 2:
                test_crear_jugadorMaquina(0,1);
                break;
            case 3:
                test_crear_jugadorPersona(6,"aaaa");
                break;
            case 4:
                Vector<JugadorMaquina> Maquinas = new Vector(6);
                break;
            default:
        }
    }
}
