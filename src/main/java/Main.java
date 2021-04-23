import ControladorPersistencia.CtrlPersitencia;

import Dominio.*;
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
        //Crear la partida (con lo necesario)
        System.out.println("0 - Maquina vs Maquina");
        System.out.println("1 - Persona vs Maquina");
        System.out.println("2 - Persona vs Persona");
        System.out.print("Seleccionar modo de juego:");
        int modo = Integer.parseInt(scan.next());
        switch (modo) {
            case 0:
                //Crear PartidaModo0
                break;
            case 1:
                //Crear PartidaModo1
                break;
            case 2:
                //Crear PartidaModo2
                break;
            default:
                System.out.println("No se ha seleccionado un modo de juego correcto");
                break;
        }

        //Luego ejecutarla (descomentar lo de abajo)
        /*int res = pa.rondaPartida();
        while (res < 0) { //continua la partida
            res = pa.rondaPartida();
        }
        if (res == -2) { //jugador a selecionado guardar partida
            cp.ctrl_guardar_partida(pa.toArrayList());
        }
        else actualizar_ranking(pa,res); //tenemos un ganador*/

    }

    private static void actualizar_ranking(Partida p, int ganador) throws MyException {
        int modo = p.getModoDeJuegoPartida();
        if (modo != 0) { //maquina vs maquina
            int id1, id2;
            String nick1, nick2;
            id1 = p.getID_J1();
            nick1 = p.getNickJugador1();
            id2 = p.getID_J2();
            nick2 = p.getNickJugador2();
            ranking.incrementar_ganadas_perdidas(id1,nick1,id2,nick2,ganador);
        }
    }


    private static String[] generar_accion_partida() {
        String[] res = {"prueba"};
        return res;
    }

    //sergio: este es un ejemplo de implementacion de como cargar una partida dependiendo del modo de partida
    public static void cargarPartida() throws IOException, MyException {
        /*En esta parte faltaria igual hacer la funcion de cargarPartida en funcion del
        usuario que hayamos hecho login previamente
         */
        System.out.print("Introducir ID de partida cargar:");
        int idPartida = Integer.parseInt(scan.next());
        System.out.println();
        int modo = cp.ctrl_leer_modo_partida(idPartida);
        int res;
        String[] accion;
        switch (modo) {
            case 0: //Maquina vs Maquina
                PartidaModo0 pa0 = cp.ctrl_cargar_partida_modo0(idPartida);
                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                accion = generar_accion_partida();
                res = pa0.rondaPartida(accion);
                while (res < 0) { //continua la partida
                    accion = generar_accion_partida();
                    res = pa0.rondaPartida(accion);
                }
                if (res == -2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa0.toArrayList());
                }
                else actualizar_ranking(pa0,res); //igual esto se puede elminiar

                break;

            case 1: //Persona vs Maquina
                PartidaModo1 pa1 = cp.ctrl_cargar_partida_modo1(idPartida);
                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                accion = generar_accion_partida();
                res = pa1.rondaPartida(accion);
                while (res < 0) { //continua la partida
                    accion = generar_accion_partida();
                    res = pa1.rondaPartida(accion);
                }
                if (res == -2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa1.toArrayList());
                }
                else actualizar_ranking(pa1,res); //tenemos un ganador
                break;

            case 2: //Persona vs Persona
                PartidaModo2 pa2 = cp.ctrl_cargar_partida_modo2(idPartida);
                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                accion = generar_accion_partida();
                res = pa2.rondaPartida(accion);
                while (res < 0) { //continua la partida
                    accion = generar_accion_partida();
                    res = pa2.rondaPartida(accion);
                }
                if (res == -2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa2.toArrayList());
                }
                else actualizar_ranking(pa2,res); //tenemos un ganador
                break;

            default:
                System.out.println("La partida no existe o el modo de partida es incorrecto");
                break;
        }
    }



    public static void consultarRanking(){
        ranking.print_ranking();
    }

    public static void consultarEstadístiques(){
        System.out.print("Introducir ID de jugador a consultar:");
        int id = Integer.parseInt(scan.next());
        System.out.print("\n Introducir nickname de jugador a consultar:");
        String nick = scan.next();
        System.out.println();
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
        //exportar ranking antes de salir del programa
        cp.ctrl_exportar_ranking(ranking.toArrayList());
    }
}