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

    public static void entrar() throws IOException, MyException {
        System.out.println("Estas Registrado/a? si/no ");
        String in = scan.next();
        if(in.toLowerCase().equals("no")){
            System.out.println("Entra tu nombre de usuario");
            nickname = scan.next();
            code = cp.ctrl_get_nuevo_ID_user(); //este metodo devuelve el Nuevo ID assignado a este usuario
            System.out.println("Creado usuario " + nickname + " con ID " + code);
            cp.ctrl_crear_usuario(code,nickname);
        }
        else if(in.toLowerCase().equals("si")){
            System.out.println("Entra tu ID");
            code = scan.nextInt();
            System.out.println("Entra tu nombre de usuario");
            nickname = scan.next();
            if (cp.ctrl_existe_usuario(code,nickname)) System.out.println("Login Correcto");
        }
        else throw new MyException("No se ha introducido una opcion valida (si, no)");
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
        String s_aux;
        while (!scan.hasNextLine()) s_aux = scan.nextLine();
        s_aux = scan.nextLine();
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

    private static int seleccionar_bando() {
        System.out.print("Seleccionar bando de juego (1 -> negro, 2 -> blanco):");
        int res = Integer.parseInt(scan.next());
        System.out.println();
        if ((res== 1) || (res== 2)) return res;
        return -1;
    }

    private static int seleccionar_id_maquina(boolean b) {
        if (!b) System.out.print("Introducir idMaquina1 (negro):");
        else System.out.print("Introducir idMaquina2 (blanco):");
        int res = Integer.parseInt(scan.next());
        System.out.println();
        return res;
    }

    private static void print_contrincantes_maquina() {
        System.out.println();
        System.out.println("Mostrando contrincantes maquina");
        System.out.println("ID's: 0 , 1 (nivel facil)");
        System.out.println("ID's: 2 , 3 (nivel medio)");
        System.out.println("ID's: 4 , 5 (nivel dificil)");
        System.out.println();
    }

    public static void iniciarPartida() throws MyException, IOException {
        int res = -1;
        int idPartida = cp.ctrl_get_nuevo_ID_Partida();
        System.out.println("0 - Maquina vs Maquina");
        System.out.println("1 - Persona vs Maquina");
        System.out.println("2 - Persona vs Persona");
        System.out.print("Seleccionar modo de juego:");
        int modo = Integer.parseInt(scan.next());
        if (modo<0 || modo > 2) throw new MyException("Modo de juego incorrecto");
        System.out.println();
        int reglas[] = seleccionar_reglas();

        int id1 = code; //por defecto anfitrion como J1
        int id2 = -1;
        String nick1 = nickname; //por defecto anfitrion como J1
        String nick2 = "";

        //inicializar tablero
        int [][] tab = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                tab[i][j] = 0;
                if ((i == 3 & j==3) || (i == 4 & j==4)) tab[i][j] = 3;
                else if ((i == 3 & j==4) || (i == 4 & j==3)) tab[i][j] = 2;
            }
        }
        Tablero t = new Tablero(tab);
        int bando = -1;
        //Selecionar bando de juego
        if (modo == 2) {
            boolean primero = true;
            while (bando == -1) {
                if (!primero) System.out.println("Bando equivocado");
                bando = seleccionar_bando();
                primero = false;
            }
            if (bando == 1) {
                id1 = code;
                nick1 = nickname;
            }
            else { //ha selecionado bando 2
                id2 = code;
                nick2 = nickname;
            }

        }
        switch (modo) {
            case 0: //Crear PartidaModo0 (Maquina vs Maquina)
                //INFO CONTRICANTES
                print_contrincantes_maquina();
                while (id1 == -1) {
                    id1 = seleccionar_id_maquina(false);
                    if(id1<0 | id1>5) id1 = -1;
                }
                while (id2 == -1) {
                    id2 = seleccionar_id_maquina(true);
                    if(id2<0 | id2>5) id2 = -1;
                }
                PartidaModo0 pa0 = new PartidaModo0(idPartida,modo,reglas,0,id1,nick1,id2,nick2,t);
                //Ejecutando partida
                res = -1;
                while (res < 0) { //continua la partida
                    res = pa0.rondaPartida(generar_accion_partida());
                    System.out.println();
                    if((res!= 2) || (res!= 3)) pa0.print_Tablero();
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa0.toArrayList());
                    System.out.println();
                    System.out.println("PARTIDA GUARDADA");
                    System.out.println();
                }
                else if (res != 3) actualizar_ranking(pa0,res); //Igual esto se puede eliminar (no actualiza ranking con las Maquinas)
                System.out.println();
                System.out.println("PARTIDA FINALIZADA");
                System.out.println();
                break;


            case 1: //Crear PartidaModo1 (Persona(siempre negras) vs Maquina)
                //INFO CONTRICANTES
                print_contrincantes_maquina();
                while (id2 == -1) {
                    id2 = seleccionar_id_maquina(true);
                    if(id2<0 | id2>5) id2 = -1;
                }
                PartidaModo1 pa1 = new PartidaModo1(idPartida,modo,reglas,0,id1,nick1,id2,nick2,t);
                //Ejecutando partida
                res = -1;
                while (res < 0) { //continua la partida
                    res = pa1.rondaPartida(generar_accion_partida());
                    System.out.println();
                    if((res!= 2) || (res!= 3)) pa1.print_Tablero();
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa1.toArrayList());
                    System.out.println();
                    System.out.println("PARTIDA GUARDADA");
                    System.out.println();
                }
                else if (res != 3) actualizar_ranking(pa1,res); //tenemos un ganador
                System.out.println();
                System.out.println("PARTIDA FINALIZADA");
                System.out.println();
                break;

            case 2: //Crear PartidaModo2 (Persona vs Persona)
                System.out.println("entra en crear partidamodo2");
                //INFO CONTRICANTES
                System.out.println("Introducir informacion de contrincante 2 (Persona)");
                String in;
                if (bando == 1) {
                    System.out.println("Estas Registrado/a? si/no ");
                    in = scan.next();
                    if (in.toLowerCase().equals("no")) {
                        System.out.println("Entra tu nombre de usuario");
                        nick2 = scan.next();
                        id2 = cp.ctrl_get_nuevo_ID_user(); //este metodo devuelve el Nuevo ID assignado a este usuario
                        System.out.println("Creado usuario " + nick2 + " con ID " + id2);
                        cp.ctrl_crear_usuario(id2, nick2);
                    } else if (in.toLowerCase().equals("si")) {
                        System.out.println("Entra tu ID");
                        id2 = scan.nextInt();
                        System.out.println("Entra tu nombre de usuario");
                        nick2 = scan.next();
                        if (cp.ctrl_existe_usuario(id2, nick2)) System.out.println("Login Correcto");
                    }
                }
                else {
                    System.out.println("Estas Registrado/a? si/no ");
                    in = scan.next();
                    if(in.toLowerCase().equals("no")){
                        System.out.println("Entra tu nombre de usuario");
                        nick1 = scan.next();
                        id1 = cp.ctrl_get_nuevo_ID_user(); //este metodo devuelve el Nuevo ID assignado a este usuario
                        System.out.println("Creado usuario " + nick1 + " con ID " + id1);
                        cp.ctrl_crear_usuario(id1,nick1);
                    }
                    else if(in.toLowerCase().equals("si")){
                        System.out.println("Entra tu ID");
                        id1 = scan.nextInt();
                        System.out.println("Entra tu nombre de usuario");
                        nick1 = scan.next();
                        if (cp.ctrl_existe_usuario(id1,nick1)) System.out.println("Login Correcto");
                    }
                }

                PartidaModo2 pa2 = new PartidaModo2(idPartida,modo,reglas,0,id1,nick1,id2,nick2,t);
                //Ejecutando partida
                res = -1;
                while (res < 0) { //continua la partida
                    res = pa2.rondaPartida(generar_accion_partida());
                    System.out.println();
                    if((res!= 2) || (res!= 3)) pa2.print_Tablero();
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa2.toArrayList());
                    System.out.println();
                    System.out.println("PARTIDA GUARDADA");
                    System.out.println();
                }
                else if (res != 3) actualizar_ranking(pa2,res); //tenemos un ganador
                System.out.println();
                System.out.println("PARTIDA FINALIZADA");
                System.out.println();
                break;


            default:
                System.out.println("No se ha seleccionado un modo de juego correcto");
                break;
        }
    }

    public static void cargarPartida(int idPartida) throws IOException, MyException {
        int modo = cp.ctrl_leer_modo_partida(idPartida);
        int res;
        switch (modo) {
            case 0: //Maquina vs Maquina
                PartidaModo0 pa0 = cp.ctrl_cargar_partida_modo0(idPartida);
                //Ejecutando partida
                res = -1;
                while (res < 0) { //continua la partida
                    res = pa0.rondaPartida(generar_accion_partida());
                    System.out.println();
                    if((res!= 2) || (res!= 3)) pa0.print_Tablero();
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa0.toArrayList());
                    System.out.println();
                    System.out.println("PARTIDA GUARDADA");
                    System.out.println();
                }
                else if (res != 3) actualizar_ranking(pa0,res); //Igual esto se puede eliminar (no actualiza ranking con las Maquinas)
                System.out.println();
                System.out.println("PARTIDA FINALIZADA");
                System.out.println();
                break;

            case 1: //Persona vs Maquina
                PartidaModo1 pa1 = cp.ctrl_cargar_partida_modo1(idPartida);
                //Ejecutando partida
                res = -1;
                while (res < 0) { //continua la partida
                    res = pa1.rondaPartida(generar_accion_partida());
                    System.out.println();
                    if((res!= 2) || (res!= 3)) pa1.print_Tablero();
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa1.toArrayList());
                    System.out.println();
                    System.out.println("PARTIDA GUARDADA");
                    System.out.println();
                }
                else if (res != 3) actualizar_ranking(pa1,res); //tenemos un ganador
                System.out.println();
                System.out.println("PARTIDA FINALIZADA");
                System.out.println();
                break;

            case 2: //Persona vs Persona
                PartidaModo2 pa2 = cp.ctrl_cargar_partida_modo2(idPartida);
                //Ejecutando partida
                res = -1;
                while (res < 0) { //continua la partida  //por ahora falla
                    res = pa2.rondaPartida(generar_accion_partida());
                    System.out.println();
                    if((res!= 2) || (res!= 3)) pa2.print_Tablero();
                }
                if (res == 2) { //jugador a selecionado guardar partida
                    cp.ctrl_guardar_partida(pa2.toArrayList());
                    System.out.println();
                    System.out.println("PARTIDA GUARDADA");
                    System.out.println();
                }
                else if (res != 3) actualizar_ranking(pa2,res); //tenemos un ganador
                System.out.println();
                System.out.println("PARTIDA FINALIZADA");
                System.out.println();
                break;

            default:
                System.out.println("La partida no existe o el modo de partida es incorrecto");
                break;
        }

    }

    public static void listar_partidas_disponibles(int id, String nick) throws IOException, MyException {
        System.out.println();
        cp.ctrl_print_partidas_disponibles(id,nick);
        System.out.print("Seleccionar ID de partida a cargar:");
        int idPartida = Integer.parseInt(scan.next());
        cargarPartida(idPartida);
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


    public static void main(String[] args) throws IOException, MyException {
        cp = new CtrlPersitencia(true); //activar para utilizar solo 1 fichero de ranking (ranking.txt)
        ranking = cp.ctrl_importar_ranking();
        entrar();
        boolean salir = false;
        while(!salir){
            System.out.println("\nElige lo que quieres hacer: \n 1: Empezar una partida \n 2: Cargar una partida \n" +
                    " 3: Consultar el ranquing \n 4: Consultar estadísticas \n 5: salir");
            int quit = scan.nextInt();
            switch (quit){
                case 1:
                    iniciarPartida();
                    break;
                case 2:
                    listar_partidas_disponibles(code,nickname);
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