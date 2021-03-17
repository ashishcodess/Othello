
package jugador;

import java.util.*;

/*
* PARA VER QUE FUNCIONE CORRECTAMENTE
*
* */

public class DriverJugador {

    static Scanner scan = new Scanner(System.in);


    public static Jugador test_crear_jugador(int iDJugador) {
        Jugador res = new Jugador(iDJugador);
        return res;
    }

    public static JugadorMaquina test_crear_jugadorMaquina(int idMaquina, int profundidad){
        JugadorMaquina res = new JugadorMaquina(idMaquina,profundidad);
        return res;
    }

    public static JugadorPersona test_crear_jugadorPersona(int idPersona, String nick){

        JugadorPersona res = new JugadorPersona(idPersona,nick);
        return res;
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
                Jugador prueba = test_crear_jugador(2);
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
