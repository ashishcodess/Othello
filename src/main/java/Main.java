import java.util.*;



public class Main {
    static int code;
    static Scanner scan = new Scanner(System.in);

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

    public static void cargarPartida(){

    }

    public static void consultarRanking(){

    }

    public static void consultarEstadístiques(int id){

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
                    cargarPartida();
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