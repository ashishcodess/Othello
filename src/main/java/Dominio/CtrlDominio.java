package Dominio;

import ControladorPersistencia.CtrlPersitencia;
import MyException.MyException;

import java.util.ArrayList;
import java.util.Scanner;


public class CtrlDominio {

    private static CtrlPersitencia cp;
    private static Ranking ranking;

    static int code; //ID jugador1
    static String nickname; //nickname jugador1

    static int id_2; //ID jugador2
    static String nick_2; //nickname jugador2

    static Scanner scan = new Scanner(System.in); //temporal por ahora

    private static int estado_partida; //para conocer el estado de la partida desde la capa de Presentacion
    private static Partida partida_activa;


    /**
     * Creadora por defecto de CtrlDominio
     * */
    public CtrlDominio() {
        this.code = -1;
        this.nickname = "";
        cp = new CtrlPersitencia(true);
        ranking = cp.ctrl_importar_ranking();
    }

    /**
     * metodo para loguearse como usuario
     * @param id identificador de usuario a hacer login
     * @param nick nickname de usuario a hacer login
     * @return devuelve 1 en caso de login correcto, 0 caso contrario
     * */
    public int login_inicial_presentacion(int id, String nick) {
        int res = 0;
        if (cp.ctrl_existe_usuario(id,nick)) {
            code = id;
            nickname = nick;
            res = 1;
        }
        return res;
    }

    /**
     * metodo Get info usuario activo
     * @return devuelve la informacion que esta logueado dentro del juego
     * */
    public String get_info_usuario_activo() {
        String s = "Usuario activo (ID:" + code + " , nickname: " + nickname + ")";
        return s;
    }

    /**
     * Metodo registro usuario
     * @param nick nickname de usuario a registrar
     * @return devuelve el identificador de usuario asignado a este jugador en caso de registro correcto \
     * caso contrario devuelve -1
     * */
    public int dominio_registro_usuario(String nick) {
        int idRes = cp.ctrl_get_nuevo_ID_user();
        if (cp.ctrl_crear_usuario(idRes,nick)) {
            return idRes;
        }else return -1;
    }

    /**
    * Metodo exportar ranking (desde Dominio)
    * */
    public void domino_exportar_ranking() {
        cp.ctrl_exportar_ranking(ranking.toArrayList());  //exportar ranking antes de salir del programa
    }

    /**
     * Metodo consultar estadisitcas
     * @param id identificador de usuario a consultar sus estadisitcas
     * @param nick nickname de usuario a consultar sus estadisitcas
     * @return devuelve un arraylist de strings con la informacion de las estadisticas (en caso de existir en el ranking), \
     * caso contrario devuelve string con informacion de ERROR
     * */
    public ArrayList<String> consultar_estadisticas(int id, String nick) {
        ArrayList<String> res = new ArrayList<String>();
        int i = ranking.existe_en_ranking(id,nick);
        if (i != -1) {
            res.add(ranking.consultar_info_elemento_i(i));
        } else { //no existe en el ranking
            res.add("ERROR ERROR - - - -");
        }
        return res;
    }

    /**
     * Metodo consultar ranking
     * @return devuelve en un ArrayList de String la informacion el ranking al completo
     * */
    public ArrayList<String> consultar_ranking() {
        return ranking.toArrayList();
    }

    /**
     * Metodo ordenar ranking
     * @param orden [0 (Ganadas), 1 (ID mayor a menor) , 2 (NICKNAME), 3 (ID menor a mayor)]
     * @return devuelve true en caso de que se haya efectuado una ordenacion, caso contrario devuelve falso
     * */
    public boolean ordenar_ranking(int orden) {
        return ranking.ordenar_ranking(orden);
    }


    /**
     * Metodo consultar size del ranking
     * @return devuelve el size del ranking
     * */
    public int consultar_tam_ranking() {return ranking.consultar_tam_ranking();}


    //FUNCIONES NO TESTEADAS....


    /**
     * Metodo cargarPartida
     * Carga una partida a partir de un fichero guardado previamente y seguidamente ejecuta dicha partida cargada
     * @param idPartida id de partida a cargar
     * @return devuelve la partida cargada, caso contrario devuelve null
     * */
    private static Partida cargarPartida(int idPartida) {
        try {
            Partida p = cp.ctrl_cargar_partida(idPartida);
            return p;
        }
        catch (Exception e) {
            String s = ("Fallo al cargar la partida con ID:" + idPartida);
            //llamar a CtrlPresentacion con el mensaje de s
        }
        return null;
    }


    /**
     * Metodo actualizar_ranking
     * @param p partida de la cual se necesita informacion de los jugadores y del ganador
     * @param ganador incrementar contador de partidas en funcion de [2: empate, 1:gana jugador2, 0:gana jugador1]
     * */
    private static void actualizar_ranking(Partida p, int ganador){
        try {
            int modo = p.getModoDeJuegoPartida();
            if (modo != 0) { //diferente de maquina vs maquina
                int id1, id2;
                String nick1, nick2;
                id1 = p.getID_J1();
                nick1 = p.getNickJugador1();
                id2 = p.getID_J2();
                nick2 = p.getNickJugador2();
                ranking.incrementar_ganadas_perdidas(id1,nick1,id2,nick2,ganador);
                cp.ctrl_exportar_ranking(ranking.toArrayList());
            }
        }
        catch (Exception e) {
            System.out.println("Fallo al actualizar el ranking");
        }
    }

    /*ejecutar 1 ronda de la partida:
    * mandarle el estado de como va la partida hacia la capa de presentacion
    * */
    private static int ejecutarRondaPartida(Partida p, ArrayList<String> argum) {
        //accion a realizar esta dentro de argum
        int res = -1;
        String[] accion = argum.toArray(new String[0]);
        res = p.rondaPartida(accion);
        //modificar estadoPartida
        if (res == 2) cp.ctrl_guardar_partida(p.toArrayList());
        else if (res != 3) actualizar_ranking(p,res);
        return res;
    }

    //Argum: argumentos/info necesaria para crear la partida
    private static Partida iniciarPartida(ArrayList<String> argum) {
        try {
            int idPartida = cp.ctrl_get_nuevo_ID_Partida();
            int id1, id2;
            String nick1 = new String();
            String nick2 = new String();
            int modo = Integer.parseInt(argum.get(0));
            if (modo<0 || modo > 2) throw new MyException("Modo de juego incorrecto");
            int reglas[] = new int[3];
            reglas[0] = Integer.parseInt(argum.get(1));
            reglas[1] = Integer.parseInt(argum.get(2));
            reglas[2] = Integer.parseInt(argum.get(3));
            /*Para CapaPresentacion: hay que hacer funcion de seleccionar
            el bando cuando estamos en modo2*/

            //seleccion de id's y de nicknames's (caso de no tener que sea "")
            id1 = Integer.parseInt(argum.get(4));
            nick1 = argum.get(5);
            id2 = Integer.parseInt(argum.get(6));
            nick2 = argum.get(7);
            Tablero t = new Tablero();//por ahora, diferenciar si se quiere cargar un tablero nuevo
            Partida p = new Partida(idPartida,modo,reglas,0,id1,nick1,id2,nick2,t);
            return p;
        }
        catch (Exception e) {
            //que hacer cuando salta alguna excepcion
            System.out.println(e); //por ahora para pruebas
        }
        return null; //por ahora para pruebas
    }

    private static ArrayList<String> listar_partidas_disponibles(int id, String nick) {
        //enviara al Controlador de Presentacion (para mostrar que partidas puede cargar/borrar el jugador)
        return cp.ctrl_listar_partidas_disponibles(id,nick);
    }

    //para imprimir tablero hacia la capa de presentacion
    public int[][] getTableroPartida() {
        return partida_activa.getTableroPartida().toMatrix();
    }

}
