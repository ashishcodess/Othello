package drivers;

import Dominio.Partida;
import Dominio.Jugador;
import Dominio.JugadorMaquina;
import Dominio.JugadorPersona;
import Dominio.ConjuntoMaquinas;
import MyException.MyException;

import java.io.IOException;
import java.util.*;

/*
 * PARA VER QUE FUNCIONE CORRECTAMENTE
 *
 * */

public class DriverPartida {

    static Scanner scan = new Scanner(System.in);




    public static void main(String[] args) throws IOException, MyException {
        boolean b = true;
        while (b) {
            System.out.println("DriverPartida (OPCIONES):");

            System.out.println();
            System.out.print("Introducir opcion:");
            int i_entrada = Integer.parseInt(scan.next());
            System.out.println();
            switch(i_entrada) {
                case -1:
                    b = false;
                    break;

                default:
            }
            System.out.println();
        }
    }
}
