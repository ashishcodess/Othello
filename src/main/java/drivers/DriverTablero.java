package drivers;

import Dominio.*;

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
        Tablero T = new Tablero();
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();

    }
    public static void test_graph_v(){

        Tablero T = new Tablero();

        T.setCasilla_tipo(0,0 , 2);
        T.setCasilla_tipo(5,5 , 1);
        int[][] graph_v = new int[8][8];
        for(int i = 0 ; i < 8 ; ++i){
            for (int j = 0 ; j < 8 ; ++j){
                graph_v[i][j]= T.getCasilla_tipo(i,j);
            }
        }
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + graph_v[i][j];
            }
            System.out.println(sbuff);
        }
        System.out.println();


    }
    public static void print_disponibles() {
        /*Position pos = new Position(1 , 1);
        Queue<Position> q = new LinkedList<>();
        q.add(pos);
        Position current_pos = q.element();
        int x = current_pos.getX();
        int y = current_pos.getY();
        System.out.println(x);*/

       /*Tablero T = new Tablero();


        T.setCasilla_tipo(0,0 , 1);
        T.setCasilla_tipo(5,5 , 1);
        T.setCasilla_tipo(5,5 , 1); // esto no debería ser añadido
        Set<Position> disp = T.getCasillasDisponibles();
        Position p1 = new Position(3 , 4);
        Position p2 =new Position(4,4);
        disp.add(p1);
        disp.add(p2);
        disp.remove(p1);
        Object[] arr = disp.toArray();
        for (int i = 0 ; i < arr.length ; ++i){
            Position P = (Position)arr[i];
            System.out.println(P.getX());
            System.out.println(P.getY());
        }
*/      int turno = 6;
        Tablero T = new Tablero();


        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();

        T.calcularCasillasDisponiblesHorizontal(turno);
        T.calcularCasillasDisponiblesVertical(turno);
        T.calcularCasillasDisponiblesDiagonales(turno);
        //T.printHorizontal();

        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();

        //Para imprimir disponibles en la pantalla.
        Set<Position> disp = T.getCasillasDisponibles();
        Object[] arr = disp.toArray();
        for (int i = 0 ; i < arr.length ; ++i){
            Position P = (Position)arr[i];
            System.out.println("Dx:" + P.getX() + "Dy:" + P.getY());
        }

    }
    public static void print_modificadas() {
        int turno = 6;
        Tablero T = new Tablero();

        T.calcularCasillasDisponiblesHorizontal(turno);
        T.calcularCasillasDisponiblesVertical(turno);
        T.calcularCasillasDisponiblesDiagonales(turno);
        Set<Position> disp = T.getCasillasDisponibles();
        Object[] arr = disp.toArray();
        for (int i = 0 ; i < arr.length ; ++i){
            Position P = (Position)arr[i];
            System.out.println("Dx:" + P.getX() + "Dy:" + P.getY());
        }

        T.actualizarTablero(2 ,3 , turno);
        /*T.actualizarTablero(2 ,2 , 1);
        T.actualizarTablero(3 ,2 , turno);
        T.actualizarTablero(4 ,2 , 1);*/

        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();
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
            System.out.println("8 - Print set blancas ");
            System.out.println("9 - Print modificadas ");
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
                case 2:
                    test_graph_v();
                    break;
                case 6:
                    print_disponibles();
                    break;
                case 9:
                    print_modificadas();
                    break;
                default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

}



    /*Set<Position> blancas = T.getCasillasBlancas();
    Object[] arr1 = blancas.toArray();
        for (int i = 0 ; i < arr1.length ; ++i){
        Position P = (Position)arr1[i];
        System.out.println("Bx:" + P.getX() + "By:" + P.getY());
        }
        Set<Position> negras = T.getCasillasNegras();
        Object[] arr2 = negras.toArray();
        for (int i = 0 ; i < arr2.length ; ++i){
        Position P = (Position)arr2[i];
        System.out.println("Nx:" + P.getX() + "Ny:" + P.getY());
        }*/
