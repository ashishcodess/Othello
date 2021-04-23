import ControladorPersistencia.CtrlPersitencia;

import Dominio.Partida;
import Dominio.Ranking;
import MyException.MyException;

import java.io.IOException;
import java.util.*;



public class Main {
    static int code;
    static Scanner scan = new Scanner(System.in);

    private static CtrlPersitencia cp;
    private static Ranking ranking;

    public static void entrar() throws IOException {
        System.out.println("Estas Registrado/a? Si/No ");
        String in = scan.next();
        if(in.toLowerCase().equals("no")){
            System.out.println("Entra tu nombre de usuario");
            String nombre = scan.next();
            code = cp.ctrl_get_nuevo_ID_user(); //este metodo devuelve el Nuevo ID assignado a este usuario
            System.out.println("Creado usuario " + nombre + " con ID " + code);
            cp.ctrl_crear_usuario(code,nombre);
        }
        else{
            System.out.println("Entra tu nombre de usuario");
            String nombre = scan.next();
            System.out.println("Entra tu ID");
            code = scan.nextInt();
            if (cp.ctrl_existe_usuario(code,nombre)) System.out.println("Login Correcto");
        }
    }

    //Sergio: al crear partida utilizar cp.ctrl_get_nuevo_ID_Partida(); para generar ID de partida
    public static void iniciarPartida(){

    }

    public static Partida cargarPartida(int idPartida) throws IOException, MyException {
        Partida res = cp.ctrl_cargar_partida(idPartida);
        return res;
    }

    public static void consultarRanking(){
        ranking.print_ranking();
    }

    public static void consultarEstadístiques(int id, String nick){
        ranking.print_persona_ranking(id,nick);
    }

    public static void runPartida(Partida p) {

    }



    public static void main(String[] args) throws IOException, MyException {
        cp = new CtrlPersitencia(true); //activar para utilizar solo 1 fichero de ranking
        ranking = cp.ctrl_importar_ranking();
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
                    //if(cargarPartida()); //faltan parametros
                    break;
                case 3:
                    consultarRanking();
                    break;
                case 4:
                    //consultarEstadístiques(); //faltan parametros
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Introduce un numero valido");
                    break;
            }
        }
        //exportar ranking antes de salir del programa
        cp.ctrl_exportar_ranking(ranking.toArrayList());
    }
}