package drivers;

import Dominio.Partida;
import Dominio.Tablero;

import MyException.MyException;

import java.io.IOException;
import java.util.*;

import MyException.MyException;

import java.io.IOException;
import java.util.*;
import MyException.MyException;

import java.io.IOException;

public class DriverTablero {
    static Scanner scan = new Scanner(System.in);

    public static void test_tablero(){

    }
    public static void main(String[] args) throws IOException, MyException {
        boolean b = true;
        while (b) {
            System.out.println("DriverJugador (OPCIONES):");
            System.out.println("-1 - SALIR DEL DRIVER");
            System.out.println("1 - Print tablero");
            System.out.println("2 - Print graph_v");
            System.out.println("3 - Print graph_h");
            System.out.println("4 - Print graph_dl ");
            System.out.println("5 - Print graph_dr ");
            System.out.println("6 - Print set disponibles ");
            System.out.println("7 - Print set negras ");
            System.out.println("4 - Print set blancas ");
            System.out.println();
            System.out.print("Introducir opcion:");
            int i_entrada = Integer.parseInt(scan.next());
            System.out.println();
            switch(i_entrada) {
                case -1:
                    b = false;
                    break;
                case 1:
                    test_tablero();
                    break;
                default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

}
