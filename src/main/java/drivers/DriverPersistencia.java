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
            System.out.println("1 - CtrlPartidas");
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
        int idPartida = id;
        Partida p = cp.ctrl_cargar_partida(idPartida);
        p.get_info_partida();
        ArrayList<String> as = p.toArrayList();
        /*for (int i = 0; i < as.size(); ++i) {
            System.out.println(as.get(i));
        }*/
    }

}
