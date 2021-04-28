import ControladorPersistencia.CtrlPersitencia;

import Dominio.Tablero;
import Dominio.Partida;
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

    /**
     * Metodo entrar(funcion de login de usuario)
     * @throws IOException en caso de fallo al crear fichero Usuario
     * @throws MyException en caso de no haber introducido una opcion correcta en la pregunta de si esta registrado (si/no)
     * */
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

    /**
     * Metodo seleccionar_reglas
     * @return devuelve un array de enteros con valores entre 0 y 1 (mostrando las reglas validas para la partida)
     * @throws MyException en caso de no haber introducido las reglas de manera incorrecta
     * */
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


    /**
     * Metodo rango_mapa_correcto
     * @param x posicion X de la casilla a comprobar el rango del mapa
     * @param y posicion y de la casilla a comprobar el rango del mapa
     * @return devuelve TRUE si x,y esta comprendido en el tablero (tablero de 8x8), FALSE en caso contrario
     * */
    private static boolean rango_mapa_correcto(int x, int y) {
        return (x >= 0 && x < 9) && (y >= 0 && y < 9);
    }

    /**
     * Metodo generar_accion_partida()
     * @return devuelve un array de Strings con la accion a realizar en el proximo turno de la partida \
     * dichas acciones pueden ser [colocar x y, paso, info, guardar, y finalizar]
     * */
    private static String[] generar_accion_partida(int id, String nick) {
        String[] res;
        System.out.println("//////////////////////////////////////////////////////");
        if (id >= 0 && id < 6) System.out.println("Acciones a realizar para la maquina: (ID: " + id + ")");
        else System.out.println("Acciones a realizar para el jugador: (ID: " + id + ", nick: " + nick + ")");
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println("-  colocar x y (colocar ficha en posicion x, y)");
        System.out.println("-  paso (pasar el turno)");
        System.out.println("-  info (get info partida)");
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

    /**
     * Metodo seleccionar_bando
     * @return devuelve el entero en caso de haber introducido correctamente el bando (1 o 2), caso contrario devuelve -1
     * */
    private static int seleccionar_bando() {
        System.out.print("Seleccionar bando de juego (1 -> negro, 2 -> blanco):");
        int res = Integer.parseInt(scan.next());
        System.out.println();
        if ((res== 1) || (res== 2)) return res;
        return -1;
    }

    /**
     * Metodo seleccionar_id_maquina
     * @param b booleano para imprimir que maquina hay que introducir en ese momento
     * @return devuelve el entero en caso de haber introducido por la consola de comandos
     * */
    private static int seleccionar_id_maquina(boolean b) {
        if (!b) System.out.print("Introducir idMaquina1 (negro):");
        else System.out.print("Introducir idMaquina2 (blanco):");
        int res = Integer.parseInt(scan.next());
        System.out.println();
        return res;
    }

    /**
     * Metodo print_contrincantes_maquina()
     * Esta funcion unicamente imprime los identificadores disponibles de las maquinas con la dificultad del juego
     * */
    private static void print_contrincantes_maquina() {
        System.out.println();
        System.out.println("Mostrando contrincantes maquina");
        System.out.println("ID's: 0 , 1 (nivel facil)");
        System.out.println("ID's: 2 , 3 (nivel medio)");
        System.out.println("ID's: 4 , 5 (nivel dificil)");
        System.out.println();
    }

    /**
     * Metodo ejecutarPartida
     * @param p es la partida ha ejecutar (una vez finalizada dicha partida guardara/actualizara el ranking en caso de \
     * ser necesario)
     * @throws IOException heredado de otra funcion (fallo al guardar la Partida)
     * @throws MyException heredado de otra funcion (fallo en la ronda o al actualizar el ranking)
     * */
    /*
    private static void ejecutarPartida(Partida p) throws IOException, MyException {
        int res = -1;
        while (res < 0) { //continua la partida
            res = p.rondaPartida(generar_accion_partida());
            System.out.println();
            if((res!= 2) || (res!= 3)) p.print_Tablero();
        }
        if (res == 2) { //jugador a selecionado guardar partida
            cp.ctrl_guardar_partida(p.toArrayList());
            System.out.println();
            System.out.println("PARTIDA GUARDADA");
            System.out.println();
        }
        else if (res != 3) actualizar_ranking(p,res);
        System.out.println();
        System.out.println("PARTIDA FINALIZADA");
        System.out.println();
    }*/

    private static void ejecutarPartida(Partida p) throws IOException, MyException {
        int res = -1;
        int turno = 0;
        int id_aux = -1;
        String s_aux = "";
        while (res < 0) { //continua la partida
            turno = p.getTurnoPartida();
            if (turno % 2 == 0) {
                id_aux = p.getID_J1();s_aux = p.getNickJugador1();
            }
            else {
                id_aux = p.getID_J2();s_aux = p.getNickJugador2();
            }
            res = p.rondaPartida(generar_accion_partida(id_aux,s_aux));
            System.out.println();
            if((res!= 2) || (res!= 3)) p.print_Tablero();
        }
        if (res == 2) { //jugador a selecionado guardar partida
            cp.ctrl_guardar_partida(p.toArrayList());
            System.out.println();
            System.out.println("PARTIDA GUARDADA");
            System.out.println();
        }
        else if (res != 3) actualizar_ranking(p,res);
        System.out.println();
        System.out.println("PARTIDA FINALIZADA");
        System.out.println();
    }

    /**
     * Metodo iniciarPartida
     * Configura lo necesario para crear una partida (idpartida,modo de juego, reglas, contrincantes, tablero) \
     * seguidamente ejecuta dicha partida creada
     * @throws MyException heredado por otro metodo (en este caso ejecutarPartida())
     * @throws IOException error al crear el fichero Partida (en caso de querer guardar la partida)
     * */
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
        //Seleccionar informacion del contrincante
        switch (modo) {
            case 0: //Maquina vs Maquina
                id1 = -1; nick1 = "";
                id2 = -1; nick2 = "";
                print_contrincantes_maquina();
                //INFO CONTRICANTES (Jugador1)
                while (id1 == -1) {
                    id1 = seleccionar_id_maquina(false);
                    if(id1<0 | id1>5) id1 = -1;
                }
                //INFO CONTRICANTES (Jugador2)
                while (id2 == -1) {
                    id2 = seleccionar_id_maquina(true);
                    if(id2<0 | id2>5) id2 = -1;
                }
                break;

            case 1: //Persona(siempre negras) vs Maquina
                print_contrincantes_maquina();
                while (id2 == -1) {
                    id2 = seleccionar_id_maquina(true);
                    if(id2<0 | id2>5) id2 = -1;
                }
                break;

            case 2: //Persona vs Persona
                System.out.println("Introducir informacion de contrincante 2 (Persona)");
                String in;

                int id_temp = -1;
                String nick_temp = "";
                System.out.println("Estas Registrado/a? si/no ");
                in = scan.next();
                if (in.toLowerCase().equals("no")) {
                    System.out.println("Entra tu nombre de usuario");
                    nick_temp = scan.next();
                    id_temp = cp.ctrl_get_nuevo_ID_user(); //este metodo devuelve el Nuevo ID assignado a este usuario
                    System.out.println("Creado usuario " + nick_temp + " con ID " + id_temp);
                    cp.ctrl_crear_usuario(id_temp, nick_temp);
                } else if (in.toLowerCase().equals("si")) {

                    System.out.println("Entra tu ID");
                    id_temp = scan.nextInt();
                    System.out.println("Entra tu nombre de usuario");
                    nick_temp = scan.next();
                    boolean b = ((nick_temp.equals(nickname)) && (id_temp == code));
                    if (b) {
                        System.out.println("Error se ha introducido la informacion del usuario logueado");
                        while (b) {
                            System.out.println("Entra tu ID");
                            id_temp = scan.nextInt();
                            System.out.println("Entra tu nombre de usuario");
                            nick_temp = scan.next();
                            b = ((nick_temp.equals(nickname)) && (id_temp == code));
                        }
                    }
                    else if (cp.ctrl_existe_usuario(id_temp, nick_temp)) System.out.println("Login Correcto");
                }
                if (bando == 1) {
                    id2 = id_temp;
                    nick2 = nick_temp;
                }
                else {
                    id1 = id_temp;
                    nick1 = nick_temp;
                }
                break;

            default:
                System.out.println("Modo de juego incorrecto");
                break;
        }
        Partida p = new Partida(idPartida,modo,reglas,0,id1,nick1,id2,nick2,t);
        ejecutarPartida(p); //Ejecutando la partida
    }


    /**
     * Metodo cargarPartida
     * Carga una partida a partir de un fichero guardado previamente y seguidamente ejecuta dicha partida cargada
     * @throws IOException en caso de fallo con el fichero Partida
     * @throws MyException heredado por otro metodo (en este caso ejecutarPartida())
     * */
    public static void cargarPartida(int idPartida) throws IOException, MyException {
        int modo = cp.ctrl_leer_modo_partida(idPartida);
        int res;
        Partida p = cp.ctrl_cargar_partida(idPartida);
        ejecutarPartida(p); //Ejecutando la partida
    }

    /**
     * Metodo listar_partidas_disponibles: Muestra opciones de cargar, borrar partida del usuario logueado
     * y ejecuta dicha accion una vez introducida la partida a jugar o borrar
     * @param id identificador de usuario a comprobar sus partidas guardadas
     * @param nick nickname de usuario a comprobar sus partidas guardadas
     * @throws IOException en caso de fallo con el fichero de Usuario
     * @throws MyException heredado por otro metodo
     * */
    public static void listar_partidas_disponibles(int id, String nick) throws IOException, MyException {
        System.out.println();
        int modo = -1;
        System.out.print("Opcion (1 - Cargar , 2 - Borrar):");
        modo = scan.nextInt();
        System.out.println();
        System.out.println("modo seleccionado:" + modo);
        if (modo == 1) {
            cp.ctrl_print_partidas_disponibles(id,nick);
            System.out.print("Seleccionar ID de partida a cargar:");
            int idPartida = scan.nextInt();
            cargarPartida(idPartida);
        }
        else if (modo == 2) {
            cp.ctrl_print_partidas_disponibles(id,nick);
            System.out.print("Seleccionar ID de partida a borrar:");
            int idPartida = scan.nextInt();
            if(cp.ctrl_borrar_partida(idPartida)) System.out.println("Partida borrada correctamente");
            else System.out.println("Problemas al borrar la partida seleccionada");
        }
    }

    /**
     * Metodo actualizar_ranking
     * @param p partida de la cual se necesita informacion de los jugadores y del ganador
     * @param ganador incrementar contador de partidas en funcion de [2: empate, 1:gana jugador2, 0:gana jugador1]
     * @throws MyException heredado por otro metodo
     * */
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

    /**
     * Metodo consultarRanking (Muestra el ranking al completo)
     * */
    public static void consultarRanking(){
        ranking.print_ranking();
    }

    /**
     * Metodo consultar_Estadisticas (Muestra la info del ranking de un jugador en concreto)
     * */
    public static void consultar_Estadisticas(){
        System.out.print("Introducir ID de jugador a consultar:");
        int id = Integer.parseInt(scan.next());
        System.out.print("\n Introducir nickname de jugador a consultar:");
        String nick = scan.next();
        System.out.println();
        ranking.print_persona_ranking(id,nick);
    }

    /**
     * Funcion main
     * @param args (argumentos del main)
     * @throws IOException heredado de otras clases
     * @throws MyException heredado de otras clases
     * */
    public static void main(String[] args) throws IOException, MyException {
        cp = new CtrlPersitencia(true); //activar para utilizar solo 1 fichero de ranking (ranking.txt)
        ranking = cp.ctrl_importar_ranking();
        entrar();
        boolean salir = false;
        while(!salir){
            System.out.println("\nElige lo que quieres hacer: \n 1: Empezar una partida \n 2: Cargar/Borrar una partida \n" +
                    " 3: Consultar el ranquing \n 4: Consultar estadisticas \n 5: salir \n");
            System.out.print("seleccionar opcion:");
            int quit = scan.nextInt();
            System.out.println("");
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
                    consultar_Estadisticas();
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