package Dominio;

import ControladorPersistencia.CtrlPersitencia;
import Dominio.Partida.Partida;
import Dominio.Partida.Position;
import Dominio.Partida.Tablero;
import Dominio.Ranking.Logros;
import Dominio.Ranking.Ranking;
import MyException.MyException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


public class CtrlDominio {

    private static CtrlPersitencia cp;
    private static Ranking ranking;

    private static int code; //ID jugador1
    private static String nickname; //nickname jugador1

    private static int id_2; //ID jugador2
    private static String nick_2; //nickname jugador2

    private static int estado_partida; //para conocer el estado de la partida desde la capa de Presentacion
    private static Partida partida_activa;
    private static int idTablero_cargar;

    public void modificar_idTablero_cargar(int id) {
        idTablero_cargar = id;
    }

    public int consultar_idTablero_cargar() {return idTablero_cargar;}


    /**
     * Creadora por defecto de CtrlDominio
     * */
    public CtrlDominio() {
        code = -1;
        nickname = "";
        id_2 = -1;
        nick_2 = "";
        cp = new CtrlPersitencia();
        try {ranking = cp.ctrl_importar_ranking("ranking.txt");}
        catch (Exception e) {ranking = new Ranking();}
    }

    /**
     * metodo para loguearse como usuario
     * @param id identificador de usuario a hacer login
     * @param nick nickname de usuario a hacer login
     * @param b si es falso login sobre Jugador 1, caso contrario sobre Jugador 2
     * @return devuelve 1 en caso de login correcto, 0 caso contrario
     * */
    public boolean login_presentacion(int id, String nick, boolean b) {
        boolean res = false;
        if (cp.ctrl_existe_usuario(id,nick)) {
            if (!b) { //login usuario 1
                code = id;
                nickname = nick;
            }
            else { //login usuario 2
                id_2 = id;
                nick_2 = nick;
            }
            res = true;
        }
        return res;
    }

    /**
     * metodo Get info usuario activo
     * @return devuelve la informacion que esta logueado dentro del juego
     * */
    public String get_info_usuario_activo() {
        return "Usuarios activos:         J1 - (ID:" + code + " , nickname: " + nickname + ")            " + "J2 - (ID2:" + id_2 + " , nickname2: " + nick_2 + ")";
    }


    /**
     * Metodo registro usuario
     * @param nick nickname de usuario a registrar
     * @return devuelve el identificador de usuario asignado a este jugador en caso de registro correcto \
     * caso contrario devuelve -1
     * */
    public int dominio_registro_usuario(String nick) {
        int idRes = -1;
        try {
            idRes = cp.ctrl_get_nuevo_ID_user();
            if (cp.ctrl_crear_usuario(idRes,nick)) {
                return idRes;
            }
        }catch (Exception e) {}
        return idRes;
    }

    public String dominio_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG t) {
        return cp.consultar_dir_IMG(t);
    }

    /**
    * Metodo exportar ranking (desde Dominio)
    * */
    public void domino_exportar_ranking() {
        try{cp.ctrl_exportar_ranking(ranking.toArrayList(), "ranking.txt");} catch (Exception e) {}
    }

    /**
     * Metodo consultar estadisitcas
     * @param id identificador de usuario a consultar sus estadisitcas
     * @param nick nickname de usuario a consultar sus estadisitcas
     * @return devuelve un arraylist de strings con la informacion de las estadisticas (en caso de existir en el ranking), \
     * caso contrario devuelve string con informacion de ERROR
     * */
    public ArrayList<String> consultar_estadisticas(int id, String nick) {
        ArrayList<String> res = new ArrayList<>();
        int i = ranking.existe_en_ranking(id,nick);
        if (i != -1) {
            res.add(ranking.consultar_info_elemento_i(i));
        } else { //no existe en el ranking
            res.add("ERROR ERROR - - - -");
        }
        return res;
    }

    /**
     * Metodo consultar logros
     * @return devuelve un arraylist de strings con la informacion de los logros
     * */
    public ArrayList<String> consultar_logros() {
        ArrayList<String> as = new ArrayList<>();
        String[] sAux = (ranking.consultar_logro(Logros.tipoLogro.PARTIDA_CORTA)).split(" ");
        String s = "";
        if (sAux.length == 5) s = ("Turnos: " + sAux[0] + " , logrado en Partida con jugadores J1[" + sAux[1] + " , " + sAux[2] + "] - J2[" + sAux[3] + " , " + sAux[4] + "]");
        as.add(s);

        sAux = (ranking.consultar_logro(Logros.tipoLogro.PARTIDAS_TOTALES)).split(" ");
        if (sAux.length == 3) s = ("Partidas totales: " + sAux[0] + " , jugador [" + sAux[1] + " , " + sAux[2] + "]");
        as.add(s);

        sAux = (ranking.consultar_logro(Logros.tipoLogro.PARTIDAS_GANADAS)).split(" ");
        if (sAux.length == 3) s = ("Partidas Ganadas: " + sAux[0] + " , jugador [" + sAux[1] + " , " + sAux[2] + "]");
        as.add(s);

        sAux = (ranking.consultar_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS)).split(" ");
        if (sAux.length == 3) s = ("Partidas Perdidas: " + sAux[0] + " , jugador [" + sAux[1] + " , " + sAux[2] + "]");
        as.add(s);

        return as;
    }

    /**
     * Metodo consultar ranking
     * @return devuelve en un ArrayList de String la informacion el ranking al completo
     * */
    public ArrayList<String> consultar_ranking() {
        ArrayList<String> as = ranking.toArrayList();
        //quitar logros
        as.remove(0);
        as.remove(0);
        as.remove(0);
        as.remove(0);
        as.remove(0);
        as.remove(0);
        return as;
    }

    /**
     * Metodo ordenar ranking
     * @param orden [0 (Ganadas), 1 (ID mayor a menor) , 2 (NICKNAME), 3 (ID menor a mayor), 4 (Perdidas), 5 (empatadas),6 (Totales)]
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


    public void crear_partida(ArrayList<String> as) {
        //partida_activa = new Partida();
    }

    /**
     * Metodo listar partidas disponibles
     * @param id del jugador a mostrar las partidas disponibles
     * @param nick del jugador a mostrar las partidas disponibles
     * @return devuelve un Arraylist de strings con las posibles partidas donde se encuentra el Jugador
     * */
    public ArrayList<String> listar_partidas_disponibles(int id, String nick) throws IOException {
        //enviara al Controlador de Presentacion (para mostrar que partidas puede cargar/borrar el jugador)
        return cp.ctrl_listar_partidas_disponibles(id,nick);
    }



    public void dominio_guardar_partida() throws IOException {
        ArrayList<String> as = partida_activa.toArrayList();
        cp.ctrl_guardar_partida(as);
    }


    /**
     * Metodo cargar Partida (desde domino
     * Carga una partida a partir de un fichero guardado previamente y se actualiza "partida_activa"
     * @param idPartida id de partida a cargar
     * */
    public void dominio_cargar_partida(int idPartida) throws IOException, MyException {
        //Sergio: igual hay que hacer algo mas que solo esto
        partida_activa = cp.ctrl_cargar_partida(idPartida);
    }


    /** Operacion Borrar Partida (desde Dominio)
     * @param idPartida es el identificador de partida a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito
     */
    public boolean dominio_borrar_partida(int idPartida) throws IOException {
        return cp.ctrl_borrar_partida(idPartida);
    }

    /**
     * Metodo listar tableros disponibles
     * @return devuelve un Arraylist de strings con todos los tableros almacenados en el sistema
     * */
    public ArrayList<String> listar_tableros_disponibles() {
        return cp.ctrl_tableros_disponibles();
    }

    /**
     * Operacion Guardar Tablero (desde Dominio)
     */
    public void dominio_guardar_tablero() throws IOException {
        int[][] tab = getTableroPartida();
        int turno = partida_activa.getTurnoPartida();
        cp.ctrl_guardar_tablero(tab,turno);
    }

    /**
     * Operacion Cargar Tablero (desde Dominio)
     * @param idTablero es el ID de tablero a cargar
     * @return devuelve la matriz de enteros de un tablero con id igual a idTablero, caso contrario devuelve tablero vacio
     */
    public int [][] dominio_cargar_tablero(int idTablero) throws IOException {
        return cp.ctrl_cargar_tablero(idTablero);
    }

    /** Operacion borrar_tablero (desde Dominio)
     * @param idTablero el identificador de tablero a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito
     */
    public boolean dominio_borrar_tablero(int idTablero) {
        return cp.ctrl_borrar_tablero(idTablero);
    }



    /**
     * Metodo actualizar_ranking
     * @param p partida de la cual se necesita informacion de los jugadores y del ganador
     * @param ganador incrementar contador de partidas en funcion de [2: empate, 1:gana jugador2, 0:gana jugador1]
     * */
    private static void actualizar_ranking(Partida p, Ranking.tipoGanador ganador){
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
                cp.ctrl_exportar_ranking(ranking.toArrayList(), "ranking.txt");
            }
        }
        catch (Exception e) {
            System.out.println("Fallo al actualizar el ranking");
        }
    }



    private static int ejecutarRondaPartida(Partida p, ArrayList<String> argum) {
        /*ejecutar 1 ronda de la partida:
         * mandarle el estado de como va la partida hacia la capa de presentacion
         * */
        int res = -1;
        try {
            //accion a realizar esta dentro de argum
            String[] accion = argum.toArray(new String[0]);
            res = p.rondaPartida(accion);
            //modificar estadoPartida
            if (res == 2) cp.ctrl_guardar_partida(p.toArrayList());
            else if (res != 3) {
                //0 (gana nick1), 1 (gana nick2), 2 (empate), 3 (guardar partida), 4 (finalizar)
                switch (res) {
                    case 0:
                        actualizar_ranking(p, Ranking.tipoGanador.GANA_J1);
                        break;
                    case 1:
                        actualizar_ranking(p, Ranking.tipoGanador.GANA_J2);
                        break;
                    case 2:
                        actualizar_ranking(p, Ranking.tipoGanador.EMPATE);
                        break;
                    default:
                        break;
                }
            }

        }
        catch (Exception e) {

        }
        return res;
    }


    //para imprimir tablero hacia la capa de presentacion
    public int[][] getTableroPartida() {
        return partida_activa.getTableroPartida().toMatrix();
    }

    public Set<Position> getCasillasDisponibles(){
        Set<Position> casillasDisponibles = partida_activa.getTableroPartida().getCasillasDisponibles();
        return casillasDisponibles;
    }


}
