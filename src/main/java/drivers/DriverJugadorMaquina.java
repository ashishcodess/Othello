package drivers;

import Dominio.*;
import MyException.MyException;

import java.util.Scanner;

public class DriverJugadorMaquina {

    static Scanner scan = new Scanner(System.in);

    /**
     * funcion main (para poder realizar las pruebas)
     * @param  args (argumentos)
     @throws MyException heredado de el resto de funciones
      * */
    public static void main(String[] args) throws MyException {
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

    /** test_crear_jugadorMaquina (clase hijo)
     * @param idMaquina es el identificador de la Maquina a crear
     * @param nick nickname de la Maquina a crear
     * @throws MyException en caso de fallo con identificadores (id pertenece a Persona)
     * */
    public static void test_crear_jugadorPersona(int idMaquina, String nick) throws MyException {
        JugadorPersona res = new JugadorPersona(idMaquina,nick);
        System.out.println("Maquina creada con ID:" + idMaquina);
        System.out.println("Prueba get_MaquinaID : " + res.get_PersonaID());
        System.out.println("Prueba get_Nickname : " + res.get_Nickname());
    }
}
