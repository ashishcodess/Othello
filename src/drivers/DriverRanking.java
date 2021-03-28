package drivers;

import jugador.Ranking;
import jugador.ElementoRanking;


import java.util.Scanner;

public class DriverRanking {

    static Scanner scan = new Scanner(System.in);

    public static void test_crear_ranking() {
        Ranking rank= new Ranking();
        ElementoRanking e = new ElementoRanking(6,"aaa");
        rank.add_al_ranking(e);
        rank.incrementar_ganada_perdida(6, "aaa",true);
        e = new ElementoRanking(7,"bbb",2, 3, 5);
        rank.add_al_ranking(e);
        System.out.println("orden normal");
        rank.print_Ranking();
        System.out.println("orden partidas ganadas");
        rank.print_Ranking(0);
        System.out.println("orden ID");
        rank.print_Ranking(1);
    }

    public static void main(String[] args) {
        boolean b = true;
        System.out.println("DriverRanking");
        while (b) {
            System.out.println("OPCIONES:");
            System.out.println("-1 - SALIR DEL DRIVER");
            System.out.println("1 - Crear Ranking (prueba1)");
            int i_entrada = Integer.parseInt(scan.next());
            switch(i_entrada) {
                case -1:
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

}
