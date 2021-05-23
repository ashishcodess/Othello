package drivers;

import Dominio.Partida.Position;
import Dominio.Partida.Tablero;
import MyException.MyException;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class DriverTablero {
    static Scanner scan = new Scanner(System.in);

    /** test_tablero para ver que el tablero inicial imprime bien (clase hijo)
     * */
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
    /** print_disponibles para ver cuales son los primeros casillas disponibles cuando empezamos la partida
     * */
    public static void print_disponibles() {

        int turno = 6;
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
    /** print_modificadas es una simulacion pequena hasta 5 turnos(3 de negras y 2 de blancas)
     * Aqui para cada turno primero va a imprimir posiciones de casillas disponible, despues tableo actual donde : disponibles = 1, negras = 2, blancas= 3 y vacios = 0.
     * Cuando ya vemos cuales son los disponibles vamos a escoger un posicion de los disponibles y actualizamos el tablero.
     * Al final para cada turno imprimimos tablero actualizo es decir los disponibles pasan a ser 0 para que cuando toca al siguente persona vuelve a calcular cuales son los disponibles para el.
     * Este proceso se repite para 5 turnos simulados.
     * */
    public static void print_modificadas() {
        int turno = 0;
        Tablero T = new Tablero();

        T.calcularCasillasDisponiblesHorizontal(turno);
        T.calcularCasillasDisponiblesVertical(turno);
        T.calcularCasillasDisponiblesDiagonales(turno);

        Set<Position> disp = T.getCasillasDisponibles();
        Object[] arr = disp.toArray();
        System.out.println("Imprimir todas las pos disponibles:");
        for (int i = 0 ; i < arr.length ; ++i){
            Position P = (Position)arr[i];
            System.out.println("Dx:" + P.getX() + "Dy:" + P.getY());
        }

        System.out.println("Imprimir Tablero para turno 1(negro):");
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();
        int[] reglas = new int[1];
        T.actualizarTablero(2 ,3 , turno, reglas);

        System.out.println("Imprimir tablero actualizado");
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();

        T.calcularCasillasDisponiblesHorizontal(1);
        T.calcularCasillasDisponiblesVertical(1);
        T.calcularCasillasDisponiblesDiagonales(1);
        disp = T.getCasillasDisponibles();
        arr = disp.toArray();

        System.out.println("Imprimir todas las pos disponibles:");
        for (int i = 0 ; i < arr.length ; ++i){
            Position P = (Position)arr[i];
            System.out.println("Dx:" + P.getX() + "Dy:" + P.getY());
        }

        System.out.println("Imprimir Tablero para turno 2(blanco):");
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();

        T.actualizarTablero(2 ,2 , 1, reglas);

        System.out.println("Imprimir tablero actualizado");
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
        disp = T.getCasillasDisponibles();
        arr = disp.toArray();

        System.out.println("Imprimir todas las pos disponibles:");
        for (int i = 0 ; i < arr.length ; ++i){
            Position P = (Position)arr[i];
            System.out.println("Dx:" + P.getX() + "Dy:" + P.getY());
        }
        System.out.println("Imprimir Tablero para turno 3(negro):");
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();

        T.actualizarTablero(3 ,2 , turno, reglas);
        System.out.println("Imprimir tablero actualizado");
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();


        T.calcularCasillasDisponiblesHorizontal(1);
        T.calcularCasillasDisponiblesVertical(1);
        T.calcularCasillasDisponiblesDiagonales(1);
        disp = T.getCasillasDisponibles();
        arr = disp.toArray();
        System.out.println("Imprimir todas las pos disponibles:");
        for (int i = 0 ; i < arr.length ; ++i){
            Position P = (Position)arr[i];
            System.out.println("Dx:" + P.getX() + "Dy:" + P.getY());
        }
        System.out.println("Imprimir Tablero para turno 4(blanco):");
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();

        T.actualizarTablero(4 ,2 , 1,reglas);
        System.out.println("Imprimir tablero actualizado");
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
        disp = T.getCasillasDisponibles();
        arr = disp.toArray();

        System.out.println("Imprimir todas las pos disponibles:");
        for (int i = 0 ; i < arr.length ; ++i){
            Position P = (Position)arr[i];
            System.out.println("Dx:" + P.getX() + "Dy:" + P.getY());
        }
        System.out.println("Imprimir Tablero para turno 5(negro):");
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();
        int x = T.getNumCasillasBlancas();
        System.out.println(x);
        T.actualizarTablero(1 ,1 , turno, reglas);
        System.out.println("Imprimir tablero actualizado");
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + T.getCasilla_tipo(i,j);
            }
            System.out.println(sbuff);
        }
        System.out.println();

          }
      public static void print_blancas(){
        Tablero T = new Tablero();

        System.out.println();
        int x = T.getNumCasillasBlancas();
        System.out.println(x);

    }
    /**
     * funcion main (para poder realizar las pruebas)
     * @param args (argumentos)
     * @throws MyException heredado de el resto de funciones
     * @throws IOException heredado de el resto de funciones
     * */
    public static void main(String[] args) throws IOException, MyException {
        boolean b = true;
        while (b) {
            System.out.println("DriverJugador (OPCIONES):");
            System.out.println("-1 - SALIR DEL DRIVER");
            System.out.println("1 - Print tablero");
            System.out.println("2 - Print set disponibles ");
            System.out.println("3 - Print modificadas ");
            System.out.println("4 - Print blancas ");
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
                    print_disponibles();
                    break;
                case 3:
                    print_modificadas();
                    break;
                case 4:
                    print_blancas();
                default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

}