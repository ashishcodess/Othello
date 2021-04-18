package drivers;

import ControladorPersistencia.CtrlPersitencia;
import Dominio.Partida;
import Dominio.Tablero;
import MyException.MyException;

import java.io.IOException;
import java.util.*;

/*
 * PARA VER QUE FUNCIONE CORRECTAMENTE
 *
 * */

public class DriverPersistencia {

    static CtrlPersitencia cp;

    static Scanner scan = new Scanner(System.in);

    public DriverPersistencia() {}

    public static void main(String[] args) throws IOException, MyException {
        cp = new CtrlPersitencia();
        boolean b = true;
        while (b) {
            System.out.println("DriverPersistencia (OPCIONES):");
            System.out.println("0 - SALIR DEL DRIVER");
            System.out.println("1 - CtrlPartidas (Cargar/Guardar partida, toArrayList, listar_partidas_disponibles, borrar_partida)");
            System.out.println();
            System.out.print("Introducir opcion:");
            int i_entrada = Integer.parseInt(scan.next());
            System.out.println();
            switch(i_entrada) {
                case 0:
                    b = false;
                    break;
                case 1:
                    System.out.print("seleccionar ID partida(fichero para realizar pruebas):");
                    i_entrada = Integer.parseInt(scan.next());
                    System.out.println("Prueba cargar partida");
                    System.out.println();
                    test_CtrlPartidas(i_entrada);
                    break;

            }
            System.out.println();
        }
    }

    public static void test_CtrlPartidas(int id) throws IOException, MyException {
        System.out.println("Probar metodos: Cargar/Guardar partida, toArrayList, listar_partidas_disponibles, borrar_partida");
        System.out.println();
        Partida p = cp.ctrl_cargar_partida(id);
        p.get_info_partida();
        ArrayList<String> as = p.toArrayList();
        System.out.println();
        System.out.println("sizes: " + as.size() + ", correcto? " + (as.size() == 15));
        as.set(0,"4");
        as.set(1,"9 dd");
        cp.ctrl_guardar_partida(as);
        System.out.println("Se ha creado partida con ID partida: 4");
        System.out.println();
        cp.ctrl_print_partidas_disponibles(7,"as2");
        System.out.println("Prueba: borrar partida creada anteriormente ID(4)");
        if (cp.ctrl_borrar_partida(4)) System.out.println("Partida 4 borrada con exito");
        else System.out.println("Fallo al borrar Partida 4");
        System.out.println("Partida borrada: comprobar partidas disponibles de los jugadores de esa partida");
        cp.ctrl_print_partidas_disponibles(7,"as2");
        cp.ctrl_print_partidas_disponibles(9,"dd");
        System.out.println();
    }

}


