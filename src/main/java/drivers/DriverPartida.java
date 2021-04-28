package drivers;

import Dominio.Partida;
import MyException.MyException;

import java.io.IOException;
import java.util.*;

/*
 * PARA VER QUE FUNCIONE CORRECTAMENTE
 *
 * */

public class DriverPartida {

    static Scanner scan = new Scanner(System.in);

    public static Partida test_crear_partida(int id, int modoJuego, int[] r, int idj1, int idj2) throws MyException {
        Partida res = new Partida(id,modoJuego,r,idj1,"",idj2,"");
        System.out.println("Partida creada con ID:" + id);
        System.out.println("Jugador1 con ID:" + idj1);
        System.out.println("Jugador1 con ID:" + idj2);
        System.out.println("Prueba getID: " + res.getIdPartida());
        System.out.println("Prueba getModoDeJuego: " + res.getModoDeJuegoPartida());
        System.out.println("Prueba getTurno: " + res.getTurnoPartida());
        System.out.println("Prueba getTablero: ");
        res.print_Tablero();
        System.out.println("Prueba incrementar turno: ");
        res.incrementarTurnoPartida();
        System.out.println("Prueba getTurno: " + res.getTurnoPartida());
        return res;
    }


    public static void main(String[] args) throws IOException, MyException {
        boolean b = true;
        while (b) {
            System.out.println("DriverPartida (OPCIONES):");
            System.out.println("-1 - SALIR DEL DRIVER");
            System.out.println("1 - Crear Partida");
            System.out.println("2 - Simular ronda Partida");
            System.out.println();
            System.out.print("Introducir opcion:");
            int i_entrada = Integer.parseInt(scan.next());
            System.out.println();
            switch(i_entrada) {
                case -1:
                    b = false;
                    break;
                case 1:
                    int[]r = {1,1,1};
                    Partida p = test_crear_partida(1,0,r,1,2);
                    break;
                case 2:
                    int[]reg = {1,1,1};

                    Partida par = test_crear_partida(1,0,reg,1,2);
                    String[] accion = {"colocar"};
                    test_ronda_partida(accion, par);
                    break;
                 default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

    private static void test_ronda_partida(String[] accion, Partida p) {
        System.out.println("Prueba ronda partida: ");
        p.rondaPartida(new String[]{"colocar 3 2"});
        p.print_Tablero();
    }
}
