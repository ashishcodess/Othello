package drivers;

import Dominio.Partida.Partida;
import MyException.MyException;

import java.io.IOException;
import java.util.*;

/*
 * PARA VER QUE FUNCIONE CORRECTAMENTE
 *
 * */

public class DriverPartida {

    static Scanner scan = new Scanner(System.in);


    /**
     * Test de la creadora de una partida nueva que muestra lo creado
     * @param id es el ID de la Partida
     * @param modoJuego es el modo de juego de la Partida
     * @param r son las reglas de la Partida
     * @param idj1 es el ID del jugador1 de la Partida
     * @param idj2 es el ID del jugador2 de la Partida
     * @throws MyException caso de fallar con el modo de juego
     * @return devuelve la partida creada
     * */
    public static Partida test_crear_partida_mostrar(int id, int modoJuego, int[] r, int idj1, int idj2) throws Exception {
        Partida res = new Partida(id,modoJuego,r,idj1,"",idj2,"");
        System.out.println("Partida creada con ID:" + id);
        System.out.println("Jugador1 con ID:" + idj1);
        System.out.println("Jugador1 con ID:" + idj2);
        System.out.println("Prueba getID: " + res.getIdPartida());
        System.out.println("Prueba getModoDeJuego: " + res.getModoDeJuegoPartida());
        System.out.println("Prueba getTurno: " + res.getTurnoPartida());
        System.out.println("Prueba getTablero: ");
        res.print_Tablero();
        System.out.println("Prueba getTurno: " + res.getTurnoPartida());
        return res;
    }

    /**
     * Test de la creadora de una partida nueva
     * @param id es el ID de la Partida
     * @param modoJuego es el modo de juego de la Partida
     * @param r son las reglas de la Partida
     * @param idj1 es el ID del jugador1 de la Partida
     * @param idj2 es el ID del jugador2 de la Partida
     * @throws MyException caso de fallar con el modo de juego
     * @return devuelve la partida creada con este test
     * */
    public static Partida test_crear_partida(int id, int modoJuego, int[] r, int idj1, int idj2) throws Exception {
        Partida res = new Partida(id,modoJuego,r,idj1,"",idj2,"");
        return res;
    }


    public static void main(String[] args) throws Exception {
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
                    Partida p = test_crear_partida_mostrar(1,2,r,12,6);
                    break;
                case 2:
                    int[]reg = {1,1,1};

                    Partida par = test_crear_partida(1,2,reg,12,6);
                    String[] accion = {"colocar 3 2"};
                    test_ronda_partida(accion, par);
                    par.incrementarTurnoPartida();
                    accion = new String[]{"colocar 2 2"};
                    test_ronda_partida(accion, par);
                    break;
                 default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

    private static void test_ronda_partida(String[] accion, Partida p) {
        System.out.println("Prueba ronda partida:");
        int res = -1;
        int id_aux = -1;
        String s_aux = "";
        while (res < 0) { //continua la partida
            int turno = p.getTurnoPartida();
            if (turno % 2 == 0) {
                id_aux = p.getID_J1();s_aux = p.getNickJugador1();
            }
            else {
                id_aux = p.getID_J2();s_aux = p.getNickJugador2();
            }
            if((res!= 2) || (res!= 3)) {
                System.out.println("TURNO: " + turno + "  [id:" + id_aux + " ,nick:" + s_aux + "]");
                System.out.println();
                //p.print_casillas_disponibles();
                //p.print_Tablero();
            }
            res = p.rondaPartida(test_generar_accion_partida(id_aux,s_aux));
            System.out.println();
        }
    }
    private static String[] test_generar_accion_partida(int id, String nick) {
        String[] res;
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");
        if (id >= 0 && id < 6) System.out.println("Acciones a realizar para la maquina: (ID: " + id + ")");
        else System.out.println("Acciones a realizar para el jugador: (ID: " + id + ", nick: " + nick + ")");
        System.out.println();
        System.out.println("    - colocar x y (enteros x,y entre [0...8])");
        System.out.println("    - paso (pasar el turno)");
        System.out.println("    - info (get info partida)");
        System.out.println("    - finalizar (finalizar la prueba)");
        System.out.println();
        System.out.print("Introducir accion a realizar:");
        String s_aux;
        while (!scan.hasNextLine()) s_aux = scan.nextLine();
        s_aux = scan.nextLine();
        System.out.println();
        res = s_aux.split(" ");
        if (res.length == 3) { //colocar x y
            int x, y;
            x = Integer.parseInt(res[1]);
            y = Integer.parseInt(res[2]);
        }
        System.out.println();
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");
        return res;
    }
}
