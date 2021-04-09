import jugador.Ranking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class Main {
    static int code;
    static Scanner scan = new Scanner(System.in);
    private static Ranking ranking;

    public static void entrar() {
        System.out.println("Estas Registrado/a? Si/No ");
        String in = scan.next();
        if(in.toLowerCase().equals("no")){
            System.out.println("Entra tu nombre de usuario");
            String nombre = scan.next();
            code = 1;                           // de momento
            System.out.println("Creado usiario " + nombre + " con codigo " + code);
        }
        else{
            System.out.println("Entra tu código");
            code = scan.nextInt();
        }
    }

    public static void iniciarPartida(){

    }

    public static Boolean cargarPartida() throws IOException {
        Boolean res = false;
        String path_partida = ""; //igual esta bien pasarlo como parametro o algo
        File f = new File(path_partida);
        if(f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader (f));

            String s1;
            String s2[] = bf.readLine().split(" ");

            int id2, modo, turno;

            int reglas[] = new int[3];
            String nick1 = new String();
            String nick2 = new String();

            int id1 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick1 = s2[1];

            s1 = bf.readLine();
            s2 = s1.split(" ");
            id2 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick2 = s2[1];

            s1 = bf.readLine();
            modo = Integer.parseInt(s1);

            s1 = bf.readLine();
            s2 = s1.split(" ");
            reglas[0] = Integer.parseInt(s2[0]);
            reglas[1] = Integer.parseInt(s2[1]);
            reglas[2] = Integer.parseInt(s2[2]);

            s1 = bf.readLine();
            turno = Integer.parseInt(s1);

            System.out.println("Extraidos: " +  id1 + " " + nick1);
            System.out.println("Extraidos: " +  id2 + " " + nick2);
            System.out.println("Extraidos: " +  modo);
            System.out.println("Extraidos: " +  reglas[0] + reglas[1] + reglas[2]);
            System.out.println("Extraidos: " +  turno);
            s1 = bf.readLine(); //espacio vacio

            char[][] mapa = new char[8][8];
            for (int i = 0; i < 8; ++i) {
                s1 = bf.readLine();
                for (int j = 0; j < 8; ++j) {
                    mapa[i][j] = s1.charAt(j);
                }
            }

        /*
        Ahora tenemos toda la informacion para crear una partida, falta creadora de
        Tablero a partir de la matriz de mapa y creadora de Partida con toda la info
        * */


        /* PRINT DEL MAPA
        System.out.println("Imprimir mapa");
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }*/
            res = true; //se ha cargado correctamente
        }
        return res;
    }

    public static void consultarRanking(){
        //SERGIO: Duda donde guardamos el objeto Ranking???
        ranking.print_ranking();
    }

    public static void consultarEstadístiques(int id, String nick){
        //SERGIO: Duda donde guardamos el objeto Ranking???
        ranking.print_persona_ranking(id,nick);
    }



    public static void main(String[] args) {
        entrar();
        boolean salir = false;
        while(!salir){
            System.out.println("Elige lo que quieres hacer: \n 1: Empezar una partida \n 2: Cargar una partida \n" +
                    "3: Consultar el ranquing \n 4: Consultar estadísticas \n 5: salir");
            int quit = scan.nextInt();
            switch (quit){
                case 1:
                    iniciarPartida();
                    break;
                case 2:
                    if(cargarPartida());
                    break;
                case 3:
                    consultarRanking();
                    break;
                case 4:
                    consultarEstadístiques();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Introduce un numero valido");
                    break;
            }
        }
    }
}