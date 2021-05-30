package drivers;

import ControladorPersistencia.CtrlPersitencia;

import Dominio.Ranking.Logros;
import Dominio.Partida.Partida;
import Dominio.Ranking.Ranking;
import Dominio.Partida.Tablero;
import MyException.MyException;

import java.io.IOException;
import java.util.*;

/**Driver de la capa de dominio: Sirve tanto para probar a crear una partida y ejecutarla,
 * como a consultar el Ranking y generar tableros personalizados*/
public class
DriverDominio {

    /**Identificador del Jugador 1*/
    static int id_1;

    /**Nickname del Jugador 1*/
    static String nick_1;

    /**Identificador del Jugador 2*/
    static int id_2;

    /**Nickname del Jugador 2*/
    static String nick_2;

    /**Scanner (leer desde entrada)*/
    static Scanner scan = new Scanner(System.in);

    /**Fichero de ranking*/
    static String f = "ranking.txt"; //nombre del fichero de ranking

    /**Controlador de Persistencia*/
    private static CtrlPersitencia cp;

    /**Ranking*/
    private static Ranking ranking;

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
        int res;
        try {
            if (!b) System.out.print("Introducir idMaquina1 (negro):");
            else System.out.print("Introducir idMaquina2 (blanco):");
            res = Integer.parseInt(scan.next());
            System.out.println();
        }
        catch(Exception e) {
            System.out.println("No se ha introducido un ID valido para una maquina");
            res = seleccionar_id_maquina(b);
        }
        return res;
    }

    /**
     * Metodo seleccionar_reglas
     * @return devuelve un array de enteros con valores entre 0 y 1 (mostrando las reglas validas para la partida)
     * */
    private static int[] seleccionar_reglas() {
        int[] res = new int[3];
        try {
            boolean b;
            System.out.println("Seleccionar reglas (vertical horizontal diagonal) -> ej:1 1 1");
            System.out.print("Introducir reglas:");
            String s;
            scan.nextLine(); //evitar bugs (hacerlo 2 veces...)
            s = scan.nextLine();
            String[] s_aux = s.split(" ");
            if (s_aux.length == 3){ //comprobar que este en rango de reglas y valen 0 o 1
                b = true;
                for (int i = 0; i < s_aux.length && b; ++i) {
                    int i_aux = Integer.parseInt(s_aux[0]);
                    b = ((i_aux == 1) || (i_aux == 0));
                    if (b) res[i] = i_aux;
                }
            }
            else throw new MyException("");
        }
        catch (Exception e) {
            System.out.println("No se ha introducido una opcion valida (si, no)");
            res = seleccionar_reglas();
        }
        return res;
    }


    /**
     * Metodo actualizar_ranking
     * @param p partida de la cual se necesita informacion de los jugadores y del ganador
     * @param ganador incrementar contador de partidas en funcion de [2: empate, 1:gana jugador2, 0:gana jugador1]
     * @param f direccion del fichero de ranking a almacenar la informacion del ranking actualizado
     * */
    private static void actualizar_ranking(Partida p, int ganador, String f){
        int id1, id2;
        try {
            int modo = p.getModoDeJuegoPartida();
            if (modo != 0) { //diferente de maquina vs maquina
                String nick1, nick2;
                id1 = p.getID_J1();
                nick1 = p.getNickJugador1();
                id2 = p.getID_J2();
                nick2 = p.getNickJugador2();

                //logros
                int turnos = p.getTurnoPartida();
                boolean b = ranking.comprobar_logro(Logros.tipoLogro.PARTIDA_CORTA,turnos);
                if (b) ranking.cambiar_logro_partida(Logros.tipoLogro.PARTIDA_CORTA,nick1,id1,nick2,id2,turnos,0);

                int fichas_j1, fichas_j2, fichas_diff;
                fichas_j1 = p.getTableroPartida().getNumCasillasNegras();
                fichas_j2 = p.getTableroPartida().getNumCasillasBlancas();
                if (fichas_j1 > fichas_j2) fichas_diff = fichas_j1 - fichas_j2;
                else fichas_diff = fichas_j2 - fichas_j1;
                b = ranking.comprobar_logro(Logros.tipoLogro.FICHAS_DIFF,fichas_diff);
                if (b) ranking.cambiar_logro_partida(Logros.tipoLogro.FICHAS_DIFF,nick1,id1,nick2,id2,fichas_j1,fichas_j2);

                switch (ganador) {
                    case 0:
                        ranking.incrementar_ganadas_perdidas(id1,nick1,id2,nick2,Ranking.tipoGanador.GANA_J1);
                        break;
                    case 1:
                        ranking.incrementar_ganadas_perdidas(id1,nick1,id2,nick2,Ranking.tipoGanador.GANA_J2);
                        break;
                    case 2:
                        ranking.incrementar_ganadas_perdidas(id1,nick1,id2,nick2,Ranking.tipoGanador.EMPATE);
                        break;
                }
                cp.ctrl_exportar_ranking(ranking.toArrayList(),f);
            }
        }
        catch (Exception e) {
            System.out.println("Fallo al actualizar el ranking");
        }
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
     * @param id identificador del jugador que realiza una accion en la partida
     * @param nick nickname del jugador que realiza una accion en la partida
     * @param turno de la partida que se esta jugando
     * @return devuelve un array de Strings con la accion a realizar en el proximo turno de la partida \
     * dichas acciones pueden ser [colocar x y, paso, info, guardar, y finalizar]
     * */
    private static String[] generar_accion_partida(int id, String nick, int turno) {
        String[] res = new String[3];
        try {
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");
            if (id >= 0 && id < 6) System.out.println("Acciones a realizar para la maquina: (ID: " + id + ")");
            else {
                String s_aux;
                if (turno < 60) {
                    System.out.println("Acciones a realizar para el jugador: (ID: " + id + ", nick: " + nick + ")");
                    System.out.println();
                    System.out.println("    - colocar x y (enteros x,y entre [0...8])");
                    System.out.println("    - paso (pasar el turno)");
                    System.out.println("    - info (get info partida)");
                    System.out.println("    - guardar (guardar partida y finalizar)");
                    System.out.println("    - finalizar (finalizar partida)");
                    System.out.println();
                    System.out.print("Introducir accion a realizar:");
                    while (!scan.hasNextLine()) s_aux = scan.nextLine();
                    s_aux = scan.nextLine();
                    System.out.println();
                    res = s_aux.split(" ");
                    if (res[0].equals("colocar")) { //colocar x y
                        if (res.length == 3) {
                            int x, y;
                            x = Integer.parseInt(res[1]);
                            y = Integer.parseInt(res[2]);
                            boolean b = rango_mapa_correcto(x, y);
                            while (!b) { //solo entra si x y no estan dentro del rango
                                System.out.println("x y fuera de rango");
                                System.out.print("Introducir nueva accion:");
                                s_aux = scan.nextLine();
                                System.out.println();
                                res = s_aux.split(" ");
                                if (res.length == 3) {
                                    x = Integer.parseInt(res[1]);
                                    y = Integer.parseInt(res[2]);
                                    b = rango_mapa_correcto(x, y);
                                } else b = (res[0].equals("guardar")) || (res[0].equals("finalizar")); //ha realizado otra accion -> salir bucle
                            }
                        }
                        else res = generar_accion_partida(id,nick,turno);
                    }
                }
                else {
                    System.out.print("Partida finalizada (pulsar enter para acabar):");
                    while (!scan.hasNextLine()) s_aux = scan.nextLine();
                    s_aux = scan.nextLine();
                    System.out.println();
                    res = s_aux.split(" ");
                }
            }
            System.out.println();
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");
        }
        catch (Exception e) {
            res = generar_accion_partida(id,nick,turno);
            System.out.println("No has introducido una accion valida");
            //System.out.println(e);
        }
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

    //0 (gana jugador 1), 1 (gana jugador 2), 2 (empate)
    private static void print_resultado_partida(Partida p) {
        int ganador = p.getGanador();
        switch (ganador) {
            case 0:
                System.out.println("Partida Ganada por Jugador1 : (" + p.getID_J1() + " , " + p.getNickJugador1() + " )" );
                break;
            case 1:
                System.out.println("Partida Ganada por Jugador2 : (" + p.getID_J2() + " , " + p.getNickJugador2() + " )" );
                break;
            case 2:
                System.out.println("Partida EMPATADA");
                break;
            default:
                System.out.println("Fallo al recoger resultado");
                break;
        }
    }

    /**
     * Metodo ejecutarPartida
     * @param p es la partida ha ejecutar (una vez finalizada dicha partida guardara/actualizara el ranking en caso de \
     * ser necesario)
     * */
    private static void ejecutarPartida(Partida p) {
        try {
            int res = -2;
            int turno;
            int id_aux;
            String s_aux;
            while (res <= -1) { //continua la partida
                turno = p.getTurnoPartida();
                if (turno % 2 == 0) {
                    id_aux = p.getID_J1();s_aux = p.getNickJugador1();
                }
                else {
                    id_aux = p.getID_J2();s_aux = p.getNickJugador2();
                }
                System.out.println("TURNO: " + turno + "  [id:" + id_aux + " ,nick:" + s_aux + "]");
                System.out.println();
                res = p.rondaPartida(generar_accion_partida(id_aux,s_aux,turno));
                System.out.println();
            }
            if (res == 4) { //jugador a selecionado guardar partida
                cp.ctrl_guardar_partida(p.toArrayList());
                System.out.println();
                System.out.println("PARTIDA GUARDADA");
                System.out.println();
            }
            else if (res != 5) actualizar_ranking(p,res,f);

            System.out.println();
            System.out.println("PARTIDA FINALIZADA");
            print_resultado_partida(p);
            System.out.println();
        }
        catch (Exception e) {
            //System.out.println(e);
        }
    }


    /**
     * Metodo cargar_id_Tablero
     * @return devuelve en caso de decidir cargar un tablero el identificador
     * del tablero personalizado a cargar, caso cotnrario devuelve -1
     * */
    private static int cargar_id_tablero() {
        System.out.println("Quieres cargar un tablero personalizado? si/no");
        String in = scan.next();
        System.out.println();
        int idTab = -1;
        if(in.equalsIgnoreCase("si")){
            cp.ctrl_print_tableros();
            System.out.print("Seleccionar tablero:");
            int id_aux = scan.nextInt();
            if (id_aux > 0) idTab = id_aux;
        }
        return idTab;
    }

    /**
     * Metodo cargar_Tablero
     * @param idTablero es el identificador de tablero a cargar
     * @return devuelve el tablero personalizado con id igual a idTablero, caso contrario devuele tablero inicial
     * @throws IOException en caso de no existir el fichero con idTablero
     * */
    private static Tablero cargar_Tablero(int idTablero) throws IOException {
        int[][]tab = cp.ctrl_cargar_tablero(idTablero);
        return new Tablero(tab);
    }

    /**
     * Metodo cargar_turno_Tablero
     * @param idTablero es el identificador de tablero a cargar
     * @return devuelve el turno del tablero personalizado con id igual a idTablero
     * @throws IOException en caso de no existir el fichero con idTablero
     * */
    private static int cargar_turno_Tablero(int idTablero) throws IOException {
        return cp.ctrl_cargar_turno_tablero(idTablero);
    }


    /**
     * Metodo generar_accion_crear_tablero
     * @param turno es el turno de la partida ficticia que se esta jugando para crear un tablero personalizado
     * @return devuelve un array de Strings con la accion a realizar en el proximo turno de la partida ficticia que se \
     * esta jugando para poder crear un tablero personalizado
     * */
    private static String[] generar_accion_crear_tablero(int turno) {
        String[] res;
        int x, y;
        try {
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");
            if (turno % 2 == 0) System.out.println("Acciones a realizar para las fichas Negras");
            else System.out.println("Acciones a realizar para las fichas Blancas");
            System.out.println();
            System.out.println("    - colocar x y (enteros x,y entre [0...8])");
            System.out.println("    - info (get info Crear Tablero)");
            System.out.println("    - guardar (guardar tablero y finalizr)");
            System.out.println("    - finalizar (finalizar tablero sin guardar)\n");
            System.out.print("Introducir accion a realizar:");
            String s_aux;
            while (!scan.hasNextLine()) s_aux = scan.nextLine();
            s_aux = scan.nextLine();
            System.out.println();
            res = s_aux.split(" ");
            if (res.length == 3 && res[0].equals("colocar")) { //colocar x y
                x = Integer.parseInt(res[1]);
                y = Integer.parseInt(res[2]);
                boolean b = rango_mapa_correcto(x, y);
                while (!b) { //solo entra si x y no estan dentro del rango
                    System.out.println("x y fuera de rango");
                    System.out.print("Introducir nueva accion:");
                    s_aux = scan.nextLine();
                    System.out.println();
                    res = s_aux.split(" ");
                    if (res.length == 3) {
                        x = Integer.parseInt(res[1]);
                        y = Integer.parseInt(res[2]);
                        b = rango_mapa_correcto(x, y);
                    } else b = (res[0].equals("guardar")) || (res[0].equals("finalizar")); //ha realizado otra accion -> salir bucle
                }
            }
            System.out.println();
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");
        }
        catch (Exception e) {
            System.out.println("No has introducido una accion valida");
            //System.out.println(e);
            res = generar_accion_crear_tablero(turno);
        }
        return res;
    }


    /**
     * Metodo print_tablero_personalizado
     * @param tab es la matriz del tablero a mostrar por pantalla
     * */
    private static void print_tablero_personalizado(int[][] tab) {
        for (int i = 0; i < 8; ++i) {
            StringBuilder sbuff = new StringBuilder();
            for (int j = 0; j < 8; ++j) {
                sbuff.append(tab[i][j]);
            }
            System.out.println(sbuff);
        }
    }

    /**
     * Metodo ejecutarPartida
     * @param p es la partida ha ejecutar (una vez finalizada dicha partida guardara/actualizara el ranking en caso de \
     * ser necesario)
     * */
    private static void ejecutarPartidaTablero(Partida p) {
        try {
            int res = -1;
            int turno;
            while (res < 0) { //continua la partida
                turno = p.getTurnoPartida();
                res = p.rondaPartida(generar_accion_crear_tablero(turno));
                System.out.println();
            }
            if (res == 2) { //jugador a selecionado guardar tablero
                int [][] tab = p.getTableroPartida().toMatrix();
                int t = p.getTurnoPartida();
                System.out.println("Imprimir tablero personalizado");
                print_tablero_personalizado(tab);
                cp.ctrl_guardar_tablero(tab,t);
                System.out.println();
                System.out.println("TABLERO GUARDADO");
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println("Error al ejecutar partida (crear Tablero)");
        }
    }


    /**
     * Metodo entrar2(funcion de login de usuario)
     * @param modo2 si modo2==2 introducira la informacion para el Jugador2, caso contrario para el Jugador1
     * */
    public static void entrar2(int modo2){
        try {
            if (modo2 == 2) System.out.println("Introducir informacion de contrincante 2 (Persona)");
            System.out.println("Estas Registrado/a? si/no ");
            String in = scan.next();
            if(in.equalsIgnoreCase("no")){
                System.out.println("Entra tu nombre de usuario");
                if (modo2 == 2) {
                    nick_2 = scan.next();
                    id_2 = cp.ctrl_get_nuevo_ID_user(); //este metodo devuelve el Nuevo ID assignado a este usuario
                    if ((nick_2.equals(nick_1)) || (id_1 == id_2)) throw new MyException("Se ha introducido informacion de usuarios repetida");
                    else {
                        System.out.println("Creado usuario " + nick_2 + " con ID " + id_2);
                        cp.ctrl_crear_usuario(id_2,nick_2);
                    }
                }
                else {
                    nick_1 = scan.next();
                    id_1 = cp.ctrl_get_nuevo_ID_user(); //este metodo devuelve el Nuevo ID assignado a este usuario
                    System.out.println("Creado usuario " + nick_1 + " con ID " + id_1);
                    cp.ctrl_crear_usuario(id_1,nick_1);
                }
            }
            else if(in.equalsIgnoreCase("si")){
                System.out.println("Entra tu ID");
                if (modo2 == 2) {
                    id_2 = scan.nextInt();
                    System.out.println("Entra tu nombre de usuario");
                    nick_2 = scan.next();
                    if ((nick_2.equals(nick_1)) || (id_1 == id_2)) throw new MyException("Se ha introducido informacion de usuarios repetida");
                    else {
                        if (cp.ctrl_existe_usuario(id_2,nick_2)) System.out.println("Login Correcto");
                        else throw new MyException("No existe usuario registrado con esa informacion");
                    }
                }
                else {
                    id_1 = scan.nextInt();
                    System.out.println("Entra tu nombre de usuario");
                    nick_1 = scan.next();
                    if (cp.ctrl_existe_usuario(id_1,nick_1)) System.out.println("Login Correcto");
                    else throw new MyException("No existe usuario registrado con esa informacion");
                }
            }
            else throw new MyException("");
        }
        catch (Exception e) {
            System.out.println("No se ha introducido una opcion valida (si, no)");
            entrar2(modo2);
        }
    }


    /**
     * Metodo iniciarPartida
     * Configura lo necesario para crear una partida (idpartida,modo de juego, reglas, contrincantes, tablero) \
     * seguidamente ejecuta dicha partida creada
     * @return devuelve la partida configurada, caso contrario devuelve null
     * */
    public static Partida iniciarPartida() {
        try {
            int idPartida = cp.ctrl_get_nuevo_ID_Partida();
            System.out.println("0 - Maquina vs Maquina");
            System.out.println("1 - Persona vs Maquina");
            System.out.println("2 - Persona vs Persona");
            System.out.print("Seleccionar modo de juego:");
            int modo = Integer.parseInt(scan.next());
            if (modo<0 || modo > 2) throw new MyException("Modo de juego incorrecto");
            System.out.println();
            int[] reglas = seleccionar_reglas();

            int id1 = id_1; //por defecto anfitrion como J1
            int id2 = -1;
            String nick1 = nick_1; //por defecto anfitrion como J1
            String nick2 = "";
            int idTablero = -1;
            int turnoPartida = 0;
            int bando = -1;
            //Selecionar bando de juego
            if (modo == 2) {
                boolean primero = true;
                bando = seleccionar_bando();
                while (bando == -1) {
                    if (!primero) System.out.println("Bando equivocado");
                    bando = seleccionar_bando();
                    primero = false;
                }
                if (bando == 1) {
                    id1 = id_1;
                    nick1 = nick_1;
                }
                else { //ha selecionado bando 2
                    id2 = id_1;
                    nick2 = nick_1;
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
                    idTablero = cargar_id_tablero();
                    print_contrincantes_maquina();
                    while (id2 == -1) {
                        id2 = seleccionar_id_maquina(true);
                        if(id2<0 | id2>5) id2 = -1;
                    }
                    break;

                case 2: //Persona vs Persona
                    idTablero = cargar_id_tablero();
                    entrar2(2);
                    if (bando == 1) {
                        id2 = id_2;
                        nick2 = nick_2;
                    }
                    else {
                        id1 = id_2;
                        nick1 = nick_2;
                    }
                    break;

                default:
                    System.out.println("Modo de juego incorrecto");
                    break;
            }
            Tablero t = new Tablero();
            if (idTablero != -1) {
                t = cargar_Tablero(idTablero);
                turnoPartida = cargar_turno_Tablero(idTablero);
            }
            return new Partida(idPartida,modo,reglas,turnoPartida,id1,nick1,id2,nick2,t);
        }
        catch (Exception ignored) {}
        return null;
    }


    /**
     * Metodo cargarPartida
     * Carga una partida a partir de un fichero guardado previamente y seguidamente ejecuta dicha partida cargada
     * @param idPartida id de partida a cargar
     * @return devuelve la partida cargada, caso contrario devuelve null
     * */
    public static Partida cargarPartida(int idPartida) {
        try {
            return cp.ctrl_cargar_partida(idPartida);
        }
        catch (Exception e) {
            System.out.println("Fallo al cargar la partida con ID:" + idPartida);
        }
        return null;
    }


    /**
     * Metodo TableroPersonalizado
     * Despliega varias opciones disponibles de tablero (crear,borrar o mostrar un tablero personalizado)
     * */
    public static void TableroPersonalizado() {
        try {
            System.out.println();
            int modoT;
            System.out.println("0 - Crear nuevo Tablero Personalizado");
            System.out.println("1 - Borrar Tablero Personalizado");
            System.out.println("2 - Mostrar Tablero Personalizado");
            System.out.print("Seleccionar opcion de Tablero personalizado:");
            modoT = scan.nextInt();
            System.out.println();
            int idTab;
            switch (modoT) {
                case 0:
                    int idPartida = cp.ctrl_get_nuevo_ID_Partida();
                    Tablero t = new Tablero();
                    int[]reg = {1,1,1};
                    Partida p = new Partida (idPartida, 2,reg,0,id_1,nick_1,id_1,nick_1,t);
                    ejecutarPartidaTablero(p);
                    break;
                case 1:
                    System.out.println("Opcion borrar tablero personalizado");
                    cp.ctrl_print_tableros();
                    System.out.print("Seleccionar tablero a realizar la accion:");
                    idTab = scan.nextInt();
                    System.out.println();
                    if (cp.ctrl_borrar_tablero(idTab)) System.out.println("Tablero " + idTab +" borrado con exito");
                    else System.out.println("Fallo al borrar tablero: " + idTab);
                    break;
                case 2:
                    System.out.println("Opcion mostrar tablero personalizado");
                    cp.ctrl_print_tableros();
                    System.out.print("Seleccionar tablero a realizar la accion:");
                    idTab = scan.nextInt();
                    System.out.println();
                    System.out.println("Imprimir tablero personalizado");
                    print_tablero_personalizado(cp.ctrl_cargar_tablero(idTab));
                    break;
                default:
                    throw new MyException("No se ha introducido una opcion valida de las mostradas");
            }
        }
        catch (Exception e) {
            System.out.println("Fallo en TableroPersonalizado de main");
        }
    }


    /**
     * Metodo listar_partidas_disponibles: Muestra opciones de cargar, borrar partida del usuario logueado
     * y ejecuta dicha accion una vez introducida la partida a jugar o borrar
     * @param id identificador de usuario a comprobar sus partidas guardadas
     * @param nick nickname de usuario a comprobar sus partidas guardadas
     * */
    public static void listar_partidas_disponibles(int id, String nick) {
        try {
            System.out.println();
            int modo;
            System.out.print("Opcion (1 - Cargar , 2 - Borrar):");
            modo = scan.nextInt();
            System.out.println();
            System.out.println("modo seleccionado:" + modo);
            if (modo == 1) {
                cp.ctrl_print_partidas_disponibles(id,nick);
                System.out.print("Seleccionar ID de partida a cargar:");
                int idPartida = scan.nextInt();
                Partida p = cargarPartida(idPartida);
                ejecutarPartida(p);
            }
            else if (modo == 2) {
                cp.ctrl_print_partidas_disponibles(id,nick);
                System.out.print("Seleccionar ID de partida a borrar:");
                int idPartida = scan.nextInt();
                if(cp.ctrl_borrar_partida(idPartida)) System.out.println("Partida borrada correctamente");
                else System.out.println("Problemas al borrar la partida seleccionada");
            }
        }
        catch (Exception e) {
            System.out.println("fallo al listar las partidas disponibles del jugador con ID:" + id +" , nickname: "+ nick);
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
     * */
    public static void main(String[] args) {
        try {
            cp = new CtrlPersitencia(); //activar para utilizar solo 1 fichero de ranking (ranking.txt)
            ranking = cp.ctrl_importar_ranking("ranking.txt");
            //ranking.print_ranking();
            entrar2(1);
            boolean salir = false;
            while(!salir){
                System.out.println();
                System.out.println("Elige lo que quieres hacer:");
                System.out.println(" 1: Empezar una partida");
                System.out.println(" 2: Cargar/Borrar una partida");
                System.out.println(" 3: Tablero Personalizado [crear, borrar y mostrar]");
                System.out.println(" 4: Consultar el ranquing");
                System.out.println(" 5: Consultar estadisticas");
                System.out.println(" 6: salir");
                System.out.print("seleccionar opcion:");
                int quit = scan.nextInt();
                System.out.println();
                switch (quit){
                    case 1:
                        Partida p = iniciarPartida();
                        ejecutarPartida(p); //Ejecutando la partida
                        break;
                    case 2:
                        listar_partidas_disponibles(id_1,nick_1);
                        break;
                    case 3:
                        TableroPersonalizado();
                        break;
                    case 4:
                        consultarRanking();
                        break;
                    case 5:
                        consultar_Estadisticas();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Introduce un numero valido");
                        break;
                }
            }
            cp.ctrl_exportar_ranking(ranking.toArrayList(),"ranking.txt"); //exportar ranking antes de salir del programa
        }
        catch (Exception ignored) {}
    }
}