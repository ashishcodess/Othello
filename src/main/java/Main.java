import java.util.*;


public class Main {
    static int code;
    static Scanner scan = new Scanner(System.in);

    public static void entrar() {
        System.out.println("Quieres crear un usuario? Si/No ");
        String in = scan.next();
        if(in.toLowerCase().equals("si")){
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

    public void iniciarPartida(){

    }

    public void cargarPartida(){

    }

    public void consultarRanking(){

    }

    public void consultarEstadístiques(int id){

    }



    public static void main(String[] args) {
        entrar();
        boolean salir = false;
        while(!salir){
            partida();
            System.out.println("Quieres sailr? Si/No");
            String quit = scan.next();
            if(quit.toLowerCase().equals("si")){
                salir = true;
            }
        }
    }
}