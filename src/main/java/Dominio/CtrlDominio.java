package Dominio;

import ControladorPersistencia.CtrlPersitencia;
import Dominio.Partida.Partida;
import Dominio.Partida.Tablero;
import Dominio.Ranking.Logros;
import Dominio.Ranking.Ranking;
import MyException.MyException;

import java.io.IOException;
import java.util.ArrayList;

/**Controlador de la capa de Dominio*/
public class CtrlDominio {

    /**Controlador de Persistencia*/
    private static CtrlPersitencia cp;

    /**Ranking*/
    private static Ranking ranking;

    /**Identificador del Jugador 1*/
    private static int id_1;

    /**Nickname del Jugador 1*/
    private static String nickname;

    /**Identificador del Jugador 2*/
    private static int id_2;

    /**Nickname del Jugador 2*/
    private static String nick_2;

    /**Es la partida que ejecutaremos siempre desde la capa de Dominio*/
    private static Partida partida_activa;

    /**Es identificador de tablero a cargar (se modifica si queremos cargar un tablero personalizado)*/
    private static int idTablero_cargar;


    /**
     * Creadora por defecto de CtrlDominio
     * */
    public CtrlDominio() {
        id_1 = -1;
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
                id_1 = id;
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
     * Metodo consultar info usuario activo
     * @return devuelve la informacion que esta logueado dentro del juego
     * */
    public String consultar_info_usuario_activo() {
        return "J1 - (ID:" + id_1 + " , nickname: " + nickname + ")       " + "J2 - (ID2:" + id_2 + " , nickname2: " + nick_2 + ")";
    }


    /**
     * Metodo consultar Identificador J1
     * @return devuelve el identificador del jugador 1
     * */
    public int consultar_id_j1() {return id_1;}


    /**
     * Metodo consultar nickname J1
     * @return devuelve el nickname del jugador 1
     * */
    public String consultar_nickname_j1() {return nickname;}

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
        }catch (Exception ignored) {}
        return idRes;
    }

    /** Metodo consultar direccion imagen (desde Dominio)
     * @param t es el tipo de imagen a cargar [vacia, disponible, blanca, negra]
     * @return devuelve la direccion del icono seleccionado*/
    public String dominio_consultar_dir_imagen_fichas(CtrlPersitencia.tipoIMG t) {
        return cp.consultar_dir_imagenes(t);
    }

    //FUNCIONES DE RANKING

    /**
    * Metodo exportar ranking (desde Dominio)
    * */
    public void domino_exportar_ranking() {
        try{cp.ctrl_exportar_ranking(ranking.toArrayList(), "ranking.txt");} catch (Exception ignored) {}
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
        if (sAux.length == 5) s = ("Turnos: " + sAux[0] + " , logrado con jugadores J1[" + sAux[1] + " , " + sAux[2] + "] - J2[" + sAux[3] + " , " + sAux[4] + "]");
        else s = ("Turnos: " + sAux[0] + " , logrado con jugadores J1[" + sAux[1] + " , " + sAux[2] + "] - J2[ " + sAux[3] + " ]");
        as.add(s);

        sAux = (ranking.consultar_logro(Logros.tipoLogro.FICHAS_DIFF)).split(" ");
        if (sAux.length ==7) s = ("Diferencia: " + sAux[0] + " , J1[" + sAux[1] + " , " + sAux[2] + " , fichas:" + sAux[3] + "] - J2[" + sAux[4] + " , " + sAux[5] + " , fichas:" + sAux[6] +"]");
        else s = ("Diferencia: " + sAux[0] + " , J1[" + sAux[1] + " , " + sAux[2] + " , fichas:" + sAux[3] + "] - J2[" + sAux[4] + " , fichas:" + sAux[5] +"]");
        as.add(s);

        sAux = (ranking.consultar_logro(Logros.tipoLogro.PARTIDAS_TOTALES)).split(" ");
        s = ("Partidas totales: " + sAux[0] + " , jugador [" + sAux[1] + " , " + sAux[2] + "]");
        as.add(s);

        sAux = (ranking.consultar_logro(Logros.tipoLogro.PARTIDAS_GANADAS)).split(" ");
        s = ("Partidas Ganadas: " + sAux[0] + " , jugador [" + sAux[1] + " , " + sAux[2] + "]");
        as.add(s);

        sAux = (ranking.consultar_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS)).split(" ");
        s = ("Partidas Perdidas: " + sAux[0] + " , jugador [" + sAux[1] + " , " + sAux[2] + "]");
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
     * */
    public void ordenar_ranking(int orden) {
       ranking.ordenar_ranking(orden);
    }

    /**
     * Metodo consultar size del ranking
     * @return devuelve el size del ranking
     * */
    public int consultar_tam_ranking() {return ranking.consultar_tam_ranking();}


    /**
     * Metodo actualizar_ranking (a partir de partida_activa)
     * @param res incrementar contador de partidas en funcion de [2: empate, 1:gana jugador2, 0:gana jugador1]
     * */
    private static void actualizar_ranking(int res){
        try {
            int modo = partida_activa.getModoDeJuegoPartida();
            if (modo != 0) {
                Ranking.tipoGanador ganador = Ranking.tipoGanador.GANA_J1;
                switch (res) {
                    case 0:
                        ganador = Ranking.tipoGanador.GANA_J1;
                        break;
                    case 1:
                        ganador = Ranking.tipoGanador.GANA_J2;
                        break;
                    case 2:
                        ganador = Ranking.tipoGanador.EMPATE;
                        break;
                }
                int id1, id2;
                String nick1, nick2;
                id1 = partida_activa.getID_J1();
                if (id1 < 6) nick1 = "**";
                else nick1 = partida_activa.getNickJugador1();
                id2 = partida_activa.getID_J2();
                if (id2 < 6) nick2 = "**";
                else nick2 = partida_activa.getNickJugador2();

                //logros
                int turnos = partida_activa.getTurnoPartida();
                boolean b = ranking.comprobar_logro(Logros.tipoLogro.PARTIDA_CORTA,turnos);
                if (b) ranking.cambiar_logro_partida(Logros.tipoLogro.PARTIDA_CORTA,nick1,id1,nick2,id2,turnos,0);

                int fichas_j1, fichas_j2, fichas_diff;
                fichas_j1 = partida_activa.getTableroPartida().getNumCasillasNegras();
                fichas_j2 = partida_activa.getTableroPartida().getNumCasillasBlancas();
                if (fichas_j1 > fichas_j2) fichas_diff = fichas_j1 - fichas_j2;
                else fichas_diff = fichas_j2 - fichas_j1;
                b = ranking.comprobar_logro(Logros.tipoLogro.FICHAS_DIFF,fichas_diff);
                if (b) ranking.cambiar_logro_partida(Logros.tipoLogro.FICHAS_DIFF,nick1,id1,nick2,id2,fichas_j1,fichas_j2);
                //ACTUALIZAR RANKING
                ranking.incrementar_ganadas_perdidas(id1,nick1,id2,nick2,ganador);
                cp.ctrl_exportar_ranking(ranking.toArrayList(), "ranking.txt");
            }
        }
        catch (Exception ignored) {}
    }


    //FUNCIONES DE TABLERO

    /**
     * Metodo modificar idTablero_cargar
     * @param id identificador de tablero personalizado a modificar
     * */
    public void modificar_idTablero_cargar(int id) {
        idTablero_cargar = id;
    }


    /**
     * Metodo consultar idTablero_cargar
     * @return devuelve el identificador del tablero a cargar
     * */
    public int consultar_idTablero_cargar() {return idTablero_cargar;}


    /**
     * Metodo listar tableros disponibles
     * @return devuelve un Arraylist de strings con todos los tableros almacenados en el sistema
     * */
    public ArrayList<String> listar_tableros_disponibles() {
        return cp.ctrl_tableros_disponibles();
    }


    /**
     * Metodo crear tablero (desde dominio): crea una partida ficticia para modificar el tablero personalizado
     * */
    public void dominio_crear_tablero() {
        try {
            Tablero t = new Tablero(dominio_cargar_tablero(0));
            int[] reglas = {1,1,1};
            //Crear partida ficticia
            partida_activa = new Partida(-1,2, reglas,0,id_1,nickname,id_1,nickname,t);
        }
        catch (Exception ignored) {}
    }

    /**
     * Metodo consultar tablero partida (partida_activa)
     * @return True en caso haberse guardado con exito el tablero, caso contrario devuelve falso
     * */
    public boolean dominio_guardar_tablero(){
        try {
            int[][] tab = consultar_TableroPartida();
            int turno = partida_activa.getTurnoPartida();
            cp.ctrl_guardar_tablero(tab,turno);
            return true;
        }
        catch (Exception ignored) {}
        return false;
    }


    /**
     * Metodo consultar tablero partida (partida_activa)
     * @return devuelve el tablero de la partida, en caso de ser "null" devuelve un tablero vacio
     * */
    public int[][] consultar_TableroPartida() {
        if (partida_activa != null) return partida_activa.getTableroPartida().toMatrix();
        else {
            int[][] tab = new int[8][8];
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) tab[i][j] = 0;
            }
            return tab;
        }
    }

    /**
     * Metodo cargar tablero de una partida
     * @param idPartida identificador de partida a cargar el tablero
     * @return devuelve el tablero de la partida
     * */
    public int[][] dominio_cargar_tablero_partida(int idPartida) {
        int[][] tab = new int[8][8];
        try {
            tab = cp.ctrl_cargar_tableroPartida(idPartida);
        } catch (Exception ignored) {}
        return tab;
    }


    /**
     * Metodo Cargar Tablero (desde Dominio)
     * @param idTablero es el ID de tablero a cargar
     * @return devuelve la matriz de enteros de un tablero con id igual a idTablero, caso contrario devuelve tablero vacio*/
    public int [][] dominio_cargar_tablero(int idTablero){
        int [][]tab = new int[8][8];
        try {
            tab = cp.ctrl_cargar_tablero(idTablero);
        }
        catch (Exception ignored) {}
        return tab;
    }


    /** Metodo borrar_tablero (desde Dominio)
     * @param idTablero el identificador de tablero a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito */
    public boolean dominio_borrar_tablero(int idTablero) {
        return cp.ctrl_borrar_tablero(idTablero);
    }


    //FUNCIONES DE PARTIDA

    /**
     * Metodo listar partidas disponibles
     * @param id del jugador a mostrar las partidas disponibles
     * @param nick del jugador a mostrar las partidas disponibles
     * @return devuelve un Arraylist de strings con las posibles partidas donde se encuentra el Jugador
     * */
    public ArrayList<String> listar_partidas_disponibles(int id, String nick){
        ArrayList<String> as = new ArrayList<>();
        try {
            as =  cp.ctrl_listar_partidas_disponibles(id,nick);
        } catch (Exception ignored) {}
        return as;

    }

    /**
     * Metodo consultar info de partida a partir de un id (desde domino)
     * @param id es el identificador de partida a consultar la informacion
     * @return devuelve un Arraylist de Strings con toda la informacion del fichero de partida identificado por su id
     * */
    public ArrayList<String> consultar_info_partida_ID(int id) {
        ArrayList<String> as = new ArrayList<>();
        try {
            as = cp.consultar_info_partida_id(id);
        }
        catch (Exception ignored) {}
        return as;
    }


    /**
     * Metodo crear Partida (desde domino)
     * @param a_int es la informacion recogida de la vista de Configuracin de Partida
     * */
    public void domino_crearPartida(ArrayList<Integer> a_int) {
        try {
            int[] reglas = new int[3];
            reglas[0] = a_int.get(0);
            reglas[1] = a_int.get(1);
            reglas[2] = a_int.get(2);
            int modo = a_int.get(3);
            int id_aux1;
            int id_aux2;
            int idPartida = cp.ctrl_get_nuevo_ID_Partida();
            int turno = cp.ctrl_cargar_turno_tablero(idTablero_cargar);
            int[][] mapa = cp.ctrl_cargar_tablero(idTablero_cargar);
            Tablero t = new Tablero(mapa);
            if (a_int.size() == 4) { //Persona vs Persona
                partida_activa = new Partida(idPartida,modo,reglas,turno,id_1,nickname,id_2,nick_2,t);
            }
            else if (a_int.size() == 5) { // Persona vs IA
                id_aux1 = a_int.get(4);
                partida_activa = new Partida(idPartida,modo,reglas,turno,id_1,nickname,id_aux1,"",t);
            }
            else if (a_int.size() == 6) { // IA vs IA
                id_aux1 = a_int.get(4);
                id_aux2 = a_int.get(5);
                partida_activa = new Partida(idPartida,modo,reglas,turno,id_aux1,"",id_aux2,"",t);
            }
        }
        catch (Exception ignored) {}
    }

    /**
     * Metodo guardar partida (desde domino)
     * guarda la informacion de partida_activa en un fichero
     * @return devuelve cierto en caso de haberse guardado con exito, caso contrario devuelve falso */
    public boolean dominio_guardar_partida() {
        try {
            ArrayList<String> as = partida_activa.toArrayList();
            cp.ctrl_guardar_partida(as);
            return true;
        }
        catch (Exception ignored) {}
        return false;
    }


    /**
     * Metodo cargar Partida (desde domino)
     * Carga una partida a partir de un fichero guardado previamente y se actualiza "partida_activa"
     * @param idPartida id de partida a cargar
     * */
    public void dominio_cargar_partida(int idPartida) {
        try {
            partida_activa = cp.ctrl_cargar_partida(idPartida);
        }
        catch (Exception ignored) {}

    }

    /** Metodo Borrar Partida (desde Dominio)
     * @param idPartida es el identificador de partida a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito
     */
    public boolean dominio_borrar_partida(int idPartida) {
        boolean b = false;
        try {
            b = cp.ctrl_borrar_partida(idPartida);
        }
        catch (Exception ignored) {}
        return b;
    }

    public void iniciarPartida(int modo, int[] r, int idj1, int idj2, String nickj1, String nickj2) {
        try {
            int idPartida = cp.ctrl_get_nuevo_ID_Partida();
            if (modo < 0 || modo > 2) throw new MyException("Modo de juego incorrecto");
            int id1 = id_1; //por defecto anfitrion como J1
            int id2 = -1;
            String nick1 = nickname; //por defecto anfitrion como J1
            String nick2 = "";
            int turnoPartida = 0;

            //Seleccionar informacion del contrincante
            switch (modo) {
                case 0: //Maquina vs Maquina
                    id1 = idj1; nick1 = "";
                    id2 = idj2; nick2 = "";
                    break;

                case 1: //Persona(siempre negras) vs Maquina
                    id1 = idj1; nick1 = nickj1;
                    id2 = idj2; nick2 = "";
                    break;

                case 2: //Persona vs Persona
                    id1 = idj1; nick1 = nickj1;
                    id2 = idj2; nick2 = nickj2;
                    break;
            }
            Tablero t = new Tablero();
            partida_activa = new Partida(idPartida,modo,r,turnoPartida,id1,nick1,id2,nick2,t);
        }
        catch (Exception ignored) { }
    }

    public int dominioRondaPartida(int x, int y) {
        int modo = partida_activa.getModoDeJuegoPartida();
        int res = -1;
        switch (modo) {
            case 0: //IA vs IA
                res = partida_activa.rondaPartidaIAvIA();
                break;

            case 1: //Persona vs IA
                res = partida_activa.rondaPartidaPvIA(x, y);
                res = partida_activa.rondaPartidaPvIA(x, y);
                break;

            case 2: //Persona vs Persona
                res = partida_activa.rondaPartidaPvP(x, y);
                break;
        }
        if (res >= 0 && res < 3) { //tenemos un ganador
            int idPartida = partida_activa.getIdPartida();
            if (idPartida > 0) actualizar_ranking(res);
        }
        return res;
    }

    /*
    public int dominioRondaPartidaPvP(int x, int y) {
        int res = partida_activa.rondaPartidaPvP(x, y);
        if (res >= 0 && res < 3) { //tenemos un ganador
            int idPartida = partida_activa.getIdPartida();
            if (idPartida > 0) actualizar_ranking(res);
        }
        return res;
    }

    public int dominioRondaPartidaPvIA(int x, int y) {
        int res = partida_activa.rondaPartidaPvIA(x, y);
        if (res >= 0 && res < 3) { //tenemos un ganador
            int idPartida = partida_activa.getIdPartida();
            if (idPartida > 0) actualizar_ranking(res);
        }
        return res;
    }
    */

    public int dominioRondaPartidaIAvIA() {
        int res = partida_activa.rondaPartidaIAvIA();
        if (res >= 0 && res < 3) { //tenemos un ganador
            int idPartida = partida_activa.getIdPartida();
            if (idPartida > 0) actualizar_ranking(res);
        }
        return res;
    }

    public int dominioObtenerModoDeJuegoPartida() {
        return partida_activa.getModoDeJuegoPartida();
    }
    /**
     * Metodo consultar total numero de las fichas negras
     * @return devuelve el numero de fichas negras
     * */
     public int dominio_get_negras() {
         if (partida_activa != null) return partida_activa.getNumNegras();
         else return 2;
     }

    /**
     * Metodo consultar total numero de las fichas blancas
     * @return devuelve el numero de fichas blancas
     * */
     public int dominio_get_blancas() {
       if (partida_activa != null) return partida_activa.getNumBlancas();
        else return 2;
     }
    /**
     * Metodo consultar el turno de juego
     * @return devuelve la ronda de la partida
     * */
    public int dominio_get_turno() {
        if (partida_activa != null) return partida_activa.getTurnoPartida();
        else return 2;
    }
    /**
     * Metodo para pasar el turno de juego en una Partida
     * */
    public void dominioPasarTurnoPartida() {
        partida_activa.pasarTurnoPartida();
    }

    public int dominioObtenerTurnoPartida() { return partida_activa.getTurnoPartida(); }
}

