package drivers;

import Dominio.Jugador.JugadorMaquina;
import Dominio.Partida.Tablero;
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
            System.out.println("1 - Crear Jugador Maquina");
            System.out.println("2 - Ejecutar Turno");
            System.out.println();
            System.out.print("Introducir opcion:");
            int i_entrada = Integer.parseInt(scan.next());
            System.out.println();
            switch(i_entrada) {
                case 0:
                    b = false;
                    break;
                case 1:
                    test_crear_jugadorMaquina(5);
                    break;
                case 2:
                    JugadorMaquina prueba = new JugadorMaquina(1);
                    Tablero t = new Tablero();
                    break;
                default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

    /** test_crear_jugadorMaquina (clase hijo)
     * @param idMaquina es el identificador de la Maquina a crear
     * @throws MyException en caso de fallo con identificadores (id pertenece a Persona)
     * */
    public static void test_crear_jugadorMaquina(int idMaquina) throws MyException {
        JugadorMaquina res = new JugadorMaquina(idMaquina);
        System.out.println("Maquina creada con ID:" + idMaquina);
        System.out.println("Prueba get_MaquinaID : " + res.get_MaquinaID());
        System.out.println("Prueba get_Profundidad : " + res.get_profundidadMaquina());
    }
}
