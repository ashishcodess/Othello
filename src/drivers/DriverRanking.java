package drivers;

import jugador.Ranking;
import jugador.ElementoRanking;


import java.io.IOException;
import java.util.Scanner;

public class DriverRanking {

    static Scanner scan = new Scanner(System.in);

    public static void test_crear_ranking() {
        Ranking rank= new Ranking();
        ElementoRanking e = new ElementoRanking(6,"aaa");
        rank.add_al_ranking(e);
        rank.incrementar_ganada_perdida(6, "aaa",1);
        e = new ElementoRanking(7,"bbb",2, 3,0,5);
        rank.add_al_ranking(e);
        System.out.println("orden normal");
        rank.print_ranking();
        System.out.println("orden partidas ganadas");
        rank.print_ranking_orden(0);
        System.out.println("orden ID");
        rank.print_ranking_orden(1);
    }

    public static void test_crear_ranking2() throws IOException {
        Ranking rank= new Ranking();
        ElementoRanking e = new ElementoRanking(6,"a");
        rank.add_al_ranking(e);
        rank.incrementar_ganada_perdida(6, "a",1);
        rank.incrementar_ganada_perdida(6, "a",0);
        e = new ElementoRanking(7,"b",2, 3,0, 5);
        rank.add_al_ranking(e);
        System.out.println("Exportando ranking a fichero");
        rank.Exportar_ranking();
        e = new ElementoRanking(8,"c",1, 4,0, 5);
        rank.add_al_ranking(e);
        rank.print_ranking();
        System.out.println("Cargar otra vez ranking anterior");
        Ranking rank2 = new Ranking("./files/ranking/ranking2.txt");
        System.out.println("Ranking importado");
        rank2.print_ranking();
    }

    public static void main(String[] args) throws IOException {
        boolean b = true;
        System.out.println("DriverRanking");
        while (b) {
            System.out.println("OPCIONES:");
            System.out.println("-1 - SALIR DEL DRIVER");
            System.out.println("1 - Crear Ranking (prueba1)");
            System.out.println("2 - Crear Ranking (exportar, importar)");
            int i_entrada = Integer.parseInt(scan.next());
            switch(i_entrada) {
                case -1:
                    b = false;
                    break;
                case 1:
                    test_crear_ranking();
                    break;
                case 2:
                    test_crear_ranking2();
                    break;
                default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

}
