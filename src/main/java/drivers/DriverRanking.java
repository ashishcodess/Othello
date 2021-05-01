package drivers;

import MyException.MyException;
import Dominio.Ranking;
import Dominio.ElementoRanking;


import java.io.IOException;
import java.util.Scanner;

public class DriverRanking {

    static Scanner scan = new Scanner(System.in);

    /** test operaciones ranking (crear, incrementar partidas, imprimir ranking dependiendo del orden)
     * @throws MyException en caso de fallo con ElementosRanking*/
    public static void test_crear_ranking() throws MyException {
        Ranking rank= new Ranking();
        ElementoRanking e = new ElementoRanking(6,"aaa");
        rank.add_al_ranking(e);
        rank.incrementar_partida(6, "aaa",1);
        e = new ElementoRanking(7,"bbb",2, 3,0,5);
        rank.add_al_ranking(e);
        e = new ElementoRanking(8,"c",4, 1,0, 5);
        rank.add_al_ranking(e);
        rank.incrementar_partida(8, "c",0);
        rank.incrementar_partida(8, "c",1);
        rank.incrementar_partida(8, "c",2);
        System.out.println();
        System.out.println("orden normal");
        rank.print_ranking();
        System.out.println("orden partidas ganadas");
        rank.print_ranking_orden(0);
        System.out.println("orden ID");
        rank.print_ranking_orden(1);
    }


    /**
     * funcion main (para poder realizar las pruebas)
     * @param args (argumentos)
     * */
    public static void main(String[] args) {
        try {
            boolean b = true;
            while (b) {
                System.out.println("DriverRanking (OPCIONES):");
                System.out.println("0 - SALIR DEL DRIVER");
                System.out.println("1 - Crear Ranking (Crear, incrementar_partidas, ordenaciones)");
                System.out.println();
                System.out.print("Introducir opcion:");
                int i_entrada = Integer.parseInt(scan.next());
                System.out.println();
                switch(i_entrada) {
                    case 0:
                        b = false;
                        break;
                    case 1:
                        test_crear_ranking();
                        break;
                    default:
                        System.out.println("Introducir una opcion correcta");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println("Fallo en main de DriverRanking");
            System.out.println(e);
        }
    }

}
