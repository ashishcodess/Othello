package drivers;

import Dominio.Jugador.JugadorPersona;
import MyException.MyException;

import java.util.*;


public class DriverJugadorPersona {

    static Scanner scan = new Scanner(System.in);



    /** test_crear_jugadorPersona (clase hijo)
     * @param idPersona es el identificador de la Persona a crear
     * @param nick nickname de la Persona a crear
     * @throws MyException en caso de fallo con identificadores (id pertenece a Maquina)
     * */
    public static void test_crear_jugadorPersona(int idPersona, String nick) throws MyException {
        JugadorPersona res = new JugadorPersona(idPersona,nick);
        System.out.println("Maquina creada con ID:" + idPersona);
        System.out.println("Prueba get_MaquinaID : " + res.get_PersonaID());
        System.out.println("Prueba get_Nickname : " + res.get_Nickname());
    }

    /**
     * funcion main (para poder realizar las pruebas)
     * @param  args (argumentos)
     * */
    public static void main(String[] args) {
        try {
            boolean b = true;
            while (b) {
                System.out.println("DriverJugador (OPCIONES):");
                System.out.println("0 - SALIR DEL DRIVER");
                System.out.println("1 - Crear Jugador Persona");
                System.out.println();
                System.out.print("Introducir opcion:");
                int i_entrada = Integer.parseInt(scan.next());
                System.out.println();
                switch(i_entrada) {
                    case 0:
                        b = false;
                        break;
                    case 1:
                        test_crear_jugadorPersona(6,"aaaa");
                        break;
                    default:
                        System.out.println("Introducir una opcion correcta");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println("Error en main de DriverJugadorPersona");
        }

    }
}
