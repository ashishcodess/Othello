import java.util.*;


public class Main {
    static int code;
    public static void main(String[] args) {
        System.out.println("Quieres crear un usuario? Si/No");
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if(in.equals("Si")){
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
}