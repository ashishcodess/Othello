package drivers;

import Dominio.Partida;
import Dominio.Jugador;
import Dominio.JugadorMaquina;
import Dominio.JugadorPersona;
import MyException.MyException;

import java.io.IOException;
import java.util.*;

/*
 * PARA VER QUE FUNCIONE CORRECTAMENTE
 *
 * */

public class DriverPartida {

    static Scanner scan = new Scanner(System.in);

    public static void test_crear_partida(int id, int modoJuego, int[] r, int idj1, int idj2){
        Partida res = new Partida(id, modoJuego, r, idj1, idj2);
        System.out.println("Partida creada con ID:" + id);
        System.out.println("Jugador1 con ID:" + idj1);
        System.out.println("Jugador1 con ID:" + idj2);
        System.out.println("Prueba getID: " + res.getIdPartida());
        System.out.println("Prueba getModoDeJuego: " + res.getModoDeJuegoPartida());
        System.out.println("Prueba getTurno: " + res.getTurnoPartida());
        System.out.println("Prueba incrementar turno: ");
        res.incrementar_turno();
        System.out.println("Prueba getTurno: " + res.getTurnoPartida());
        System.out.println("Prueba rondaPartida:");

    }


    public static void main(String[] args) throws IOException, MyException {
        boolean b = true;
        while (b) {
            System.out.println("DriverPartida (OPCIONES):");
            System.out.println("-1 - SALIR DEL DRIVER");
            System.out.println("1 - Crear Partida");
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
                    test_crear_partida(1,0,r,1,2);
                    break;
                 default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }
}
