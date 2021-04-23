import ControladorPersistencia.CtrlPersitencia;

import Dominio.Tablero;
import Dominio.Partida;
import Dominio.PartidaModo0;
import Dominio.PartidaModo1;
import Dominio.PartidaModo2;
import Dominio.Ranking;
import MyException.MyException;

import java.io.IOException;
import java.util.*;



public class Main {
    static int code;
    static String nickname;
    static Scanner scan = new Scanner(System.in);

    private static CtrlPersitencia cp;
    private static Ranking ranking;

    public static void entrar() throws IOException {
        System.out.println("Estas Registrado/a? Si/No ");
        String in = scan.next();
        if(in.toLowerCase().equals("no")){
            System.out.println("Entra tu nombre de usuario");
            nickname = scan.next();
            code = cp.ctrl_get_nuevo_ID_user(); //este metodo devuelve el Nuevo ID assignado a este usuario
            System.out.println("Creado usuario " + nickname + " con ID " + code);
            cp.ctrl_crear_usuario(code,nickname);
        }
        else{
            System.out.println("Entra tu ID");
            code = scan.nextInt();
            System.out.println("Entra tu nombre de usuario");
            nickname = scan.next();
            if (cp.ctrl_existe_usuario(code,nickname)) System.out.println("Login Correcto");
        }
    }

    private static int[] seleccionar_reglas() throws MyException {
        int res[] = new int[3];
        boolean b = false;
        System.out.println("Seleccionar reglas (vertical horizontal diagonal) -> ej:1 1 1");
        System.out.print("Introducir reglas:");
        String s = scan.nextLine(); //evitar bugs (hacerlo 2 veces...)
        s = scan.nextLine();
        String s_aux[] = s.split(" ");
        if (s_aux.length == 3){ //comprobar que este en rango de reglas y valen 0 o 1
            b = true;
            for (int i = 0; i < s_aux.length && b; ++i) {
                int i_aux = Integer.parseInt(s_aux[0]);
                b = ((i_aux == 1) || (i_aux == 0));
                if (b) res[i] = i_aux;
            }
        }
        else throw new MyException("Reglas incorrectas");
        return res;
    }

    //Sergio: inacabado
    public static void iniciarPartida() throws MyException, IOException {
        int res = -1;
        String accion[] = {"aa"};
        int idPartida = cp.ctrl_get_nuevo_ID_Partida();
        System.out.println("0 - Maquina vs Maquina");
        System.out.println("1 - Persona vs Maquina");
        System.out.println("2 - Persona vs Persona");
        System.out.print("Seleccionar modo de juego:");
        int modo = Integer.parseInt(scan.next());
        System.out.println();
        int reglas[] = seleccionar_reglas();

        int id1 = 0;
        int id2 = 0;
        String nick1 = new String();
        String nick2 = new String();
        Tablero t = new Tablero();
        switch (modo) {
            case 0: //Crear PartidaModo0
                //FALTA introducir informacion de ID's de maquina
                PartidaModo0 pa0 = new PartidaModo0(idPartida,modo,reglas,0,id1,nick1,id2,nick2,t);

                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                res = -1;
                while (res < 0) { //continua la partida
                    accion[0] = scan.nextLine(); //para evitar bugs
                    accion = generar_accion_partida();
                    res = pa0.rondaPartida(accion);
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa0.toArrayList());
                }
                else if (res != 3) actualizar_ranking(pa0,res); //igual esto se puede elminiar
                break;
            case 1: //Crear PartidaModo1
                //FALTA introducir informacion de ID de maquina a enfrentarse
                PartidaModo1 pa1 = new PartidaModo1(idPartida,modo,reglas,0,code,nickname,id2,nick2,t);

                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                res = -1;
                while (res < 0) { //continua la partida
                    accion[0] = scan.nextLine(); //para evitar bugs
                    accion = generar_accion_partida();
                    res = pa1.rondaPartida(accion);
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa1.toArrayList());
                }
                else if (res != 3) actualizar_ranking(pa1,res); //tenemos un ganador
                break;

            case 2: //Crear PartidaModo2
                //FALTA introducir informacion de id's y nicknames
                PartidaModo2 pa2 = new PartidaModo2(idPartida,modo,reglas,0,code,nickname,id2,nick2,t);

                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                res = -1;
                while (res < 0) { //continua la partida
                    accion[0] = scan.nextLine(); //para evitar bugs
                    accion = generar_accion_partida();
                    res = pa2.rondaPartida(accion);
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa2.toArrayList());
                }
                else if (res != 3) actualizar_ranking(pa2,res); //tenemos un ganador

                break;
            default:
                System.out.println("No se ha seleccionado un modo de juego correcto");
                break;
        }

    }

    //esta funcion se puede comprobar desde Partida si no
    private static boolean rango_mapa_correcto(int x, int y) {
        return (x >= 0 && x < 9) && (y >= 0 && y < 9);
    }

    private static String[] generar_accion_partida() {
        String[] res;
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println("Acciones a realizar");
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println("-  colocar x y (colocar ficha en posicion x, y)");
        System.out.println("-  paso (pasar el turno)");
        System.out.println("-  guardar (guardar partida y finalizar)");
        System.out.println("-  finalizar (finalizar partida)");
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println();
        System.out.print("Introducir accion a realizar:");
        String s_aux = scan.nextLine();
        System.out.println();
        res = s_aux.split(" ");
        if (res.length == 3) { //colocar x y
            int x, y;
            x = Integer.parseInt(res[1]);
            y = Integer.parseInt(res[2]);
            boolean b = rango_mapa_correcto(x,y);
            while (!b) { //solo entra si x y no estan dentro del rango
                System.out.println("x y fuera de rango");
                System.out.print("Introducir nueva accion:");
                s_aux = scan.nextLine();
                System.out.println();
                res = s_aux.split(" ");
                if (res.length == 3) {
                    x = Integer.parseInt(res[1]);
                    y = Integer.parseInt(res[2]);
                    b = rango_mapa_correcto(x,y);
                }
                else if ((res[0].equals("guardar")) || (res[0].equals("finalizar"))) b = true; //ha realizado otra accion -> salir bucle
                else b = false;
            }
        }
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
        String[] accion = {"prueba"};
        switch (modo) {
            case 0: //Maquina vs Maquina
                PartidaModo0 pa0 = cp.ctrl_cargar_partida_modo0(idPartida);
                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                res = -1;
                while (res < 0) { //continua la partida
                    accion[0] = scan.nextLine(); //para evitar bugs
                    accion = generar_accion_partida();
                    res = pa0.rondaPartida(accion);
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa0.toArrayList());
                }
                else if (res != 3) actualizar_ranking(pa0,res); //igual esto se puede elminiar

                break;

            case 1: //Persona vs Maquina
                PartidaModo1 pa1 = cp.ctrl_cargar_partida_modo1(idPartida);
                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                res = -1;
                while (res < 0) { //continua la partida
                    accion[0] = scan.nextLine(); //para evitar bugs
                    accion = generar_accion_partida();
                    res = pa1.rondaPartida(accion);
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa1.toArrayList());
                }
                else if (res != 3) actualizar_ranking(pa1,res); //tenemos un ganador
                break;

            case 2: //Persona vs Persona
                PartidaModo2 pa2 = cp.ctrl_cargar_partida_modo2(idPartida);
                //aqui se podria ejecutar la partida (pongo un ejemplo de como implementarlo)
                res = -1;
                while (res < 0) { //continua la partida
                    accion[0] = scan.nextLine(); //para evitar bugs
                    accion = generar_accion_partida();
                    res = pa2.rondaPartida(accion);
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa2.toArrayList());
                }
                else if (res != 3) actualizar_ranking(pa2,res); //tenemos un ganador
                break;

            default:
                System.out.println("La partida no existe o el modo de partida es incorrecto");
                break;
        }
    }


    private static void actualizar_ranking(Partida p, int ganador) throws MyException {
        int modo = p.getModoDeJuegoPartida();
        if (modo != 0) { //diferente de maquina vs maquina
            int id1, id2;
            String nick1, nick2;
            id1 = p.getID_J1();
            nick1 = p.getNickJugador1();
            id2 = p.getID_J2();
            nick2 = p.getNickJugador2();
            ranking.incrementar_ganadas_perdidas(id1,nick1,id2,nick2,ganador);
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

    //Sergio: creo que la podemos eliminar
    public static void runPartida(Partida p) {

    }


    public static void main(String[] args) throws IOException, MyException {
        cp = new CtrlPersitencia(true); //activar para utilizar solo 1 fichero de ranking
        ranking = cp.ctrl_importar_ranking();
        entrar();
        boolean salir = false;
        while(!salir){
            System.out.println("Elige lo que quieres hacer: \n 1: Empezar una partida \n 2: Cargar una partida \n" +
                    " 3: Consultar el ranquing \n 4: Consultar estadísticas \n 5: salir");
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
        cp.ctrl_exportar_ranking(ranking.toArrayList()); //exportar ranking antes de salir del programa
    }
}