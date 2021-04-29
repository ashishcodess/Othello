package ControladorPersistencia;

import Dominio.*;
import MyException.MyException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CtrlPersitencia {

    /** Objeto IOPartidas*/
    private IOPartidas cPartidas;
    /** Objeto IORanking*/
    private IORanking cRanking;
    /** Objeto IOUsuario*/
    private IOUsuario cUsuario;
    /** Objeto IOtablero*/
    private IOTablero cTablero;

    /** Ubicacion de directorio de ficheros para CtrlPersitencia*/
    private String path;
    /** Ubicacion de directorio de las partidas para CtrlPersitencia*/
    private String path_partidas;
    /** Ubicacion de directorio del ranking para CtrlPersitencia*/
    private String path_ranking;
    /** Ubicacion de directorio de los usuarios para CtrlPersitencia*/
    private String path_users;
    /** Ubicacion de directorio de los tableros para CtrlPersitencia*/
    private String path_tableros;


    /**
     * Constructora por defecto
     */
    public CtrlPersitencia() {
        this.path = "./src/files/";
        this.path_partidas = this.path + "partidas/";
        this.path_ranking =  this.path + "ranking/";
        this.path_users = this.path + "users/";
        this.path_tableros =  this.path + "tableros/";
        this.cPartidas = new IOPartidas(this.path_partidas);
        this.cRanking = new IORanking(this.path_ranking);
        this.cUsuario = new IOUsuario(this.path_users);
        this.cTablero = new IOTablero(this.path_tableros);
        InicializarDirPersitencia();
    }

    /**
     * Constructora con opcion a cambiar el modoRanking)
     * @param bRank [TRUE: solo utiliza fichero ranking.txt; FALSE:genera varios ficheros en funcion del size del ranking (usado en las pruebas DriverRanking)]
     */
    public CtrlPersitencia(boolean bRank) {

        this.path = "./src/files/";
        this.path_partidas = this.path + "partidas/";
        this.path_ranking =  this.path + "ranking/";
        this.path_users = this.path + "users/";
        this.path_tableros =  this.path + "tableros/";
        this.cPartidas = new IOPartidas(this.path_partidas);
        this.cRanking = new IORanking(this.path_ranking,bRank);
        this.cUsuario = new IOUsuario(this.path_users);
        this.cTablero = new IOTablero(this.path_tableros);
        InicializarDirPersitencia();
    }


    /**
     * Constructora con s_path
     * @param s_path path de el directorio de files (para Persistencia)
     */
    public CtrlPersitencia(String s_path) {
        this.path = s_path;
        this.path_partidas =  this.path + "partidas/";
        this.path_ranking =   this.path + "ranking/";
        this.path_users =  this.path + "users/";
        this.path_tableros =   this.path + "tableros/";
        this.cPartidas = new IOPartidas(path_partidas);
        this.cRanking = new IORanking(path_ranking);
        this.cUsuario = new IOUsuario(path_users);
        this.cTablero = new IOTablero(this.path_tableros);
        InicializarDirPersitencia();
    }

    /**
     * Constructora con s_path y modoRanking
     * @param s_path path de el directorio de files (para Persistencia)
     * @param bRank [TRUE: solo utiliza fichero ranking.txt; FALSE:genera varios ficheros en funcion del size del ranking (usado en las pruebas DriverRanking)]
     */
    public CtrlPersitencia(String s_path, boolean bRank) {
        this.path = s_path;
        this.path_partidas =  this.path + "partidas/";
        this.path_ranking =   this.path + "ranking/";
        this.path_users =  this.path + "users/";
        this.cPartidas = new IOPartidas( this.path_partidas);
        this.cRanking = new IORanking( this.path_ranking,bRank);
        this.cUsuario = new IOUsuario( this.path_users);
        this.cTablero = new IOTablero(this.path_tableros);
        InicializarDirPersitencia();
    }

    /**
     * Este metodo inicializa los directorios de la Capa de Persitencia, crea los directorios en caso de no existir
     * */
    private void InicializarDirPersitencia() {
        File f = new File(path);
        if (!f.exists()) {
            if (f.mkdir()) {
                f = new File(path_partidas);
                if (!f.exists()) {f.mkdir();}
                f = new File(path_ranking);
                if (!f.exists()) {f.mkdir();}
                f = new File(path_users);
                if (!f.exists()) {f.mkdir();}
                f = new File(path_tableros);
                if (!f.exists()) {f.mkdir();}
            }
        }
    }

    /**
     * Operacion ctrl_get_nuevo_ID_user
     * @return devuelve el siguente ID disponible para asignarselo a un Usuario
     * */
    public int ctrl_get_nuevo_ID_user() {
        return cUsuario.get_nuevo_ID_user();
    }

    /**
     * Operacion ctrl_get_nuevo_ID_Partida
     * @return devuelve el siguente ID disponible para asignarselo a una Partida
     * */
    public int ctrl_get_nuevo_ID_Partida() {
        return cPartidas.get_nuevo_ID_Partida();
    }

    /**
     * Operacion ctrl_get_nuevo_ID_tablero
     * @return devuelve el siguente ID disponible para asignarselo a un tablero a guardar
     * */
    public int ctrl_get_nuevo_ID_tablero() {
        return cTablero.get_nuevo_ID_tablero();
    }

    //Controlador de Partidas (cPartidas)

    /**
     * Operacion ctrl_leer_modo_partida
     * @param idPartida es el ID de partida a cargar
     * @return devuelve el modo de la partida con igual a idPartida, caso contrario (no existe partida) devuelve -1
     * @throws IOException en caso de no existir el fichero de partida
     */
    public int ctrl_leer_modo_partida(int idPartida) throws IOException {
        return cPartidas.leer_modo_partida(idPartida);
    }

    /**
     * Operacion ctrl_cargar_partida
     * @param idPartida es el ID de partida a cargar
     * @return devuelve Partida con id igual a idPartida, caso contrario devuelve null
     * @throws IOException en caso de no existir el fichero de partida
     * @throws MyException en caso de no existir el fichero de partida con id=idPartida
     */
    public Partida ctrl_cargar_partida(int idPartida) throws IOException, MyException{
        Partida p = cPartidas.cargar_partida(idPartida);
        if (p != null) {
            int id1, id2, id_partida;
            String nick1 = new String();
            String nick2 = new String();
            id1 = p.getID_J1(); nick1 = p.getNickJugador1();
            if (id1 > 5) {
                cUsuario.crear_usuario(id1,nick1);
                if (!cUsuario.existe_partida_usuario(id1,nick1,idPartida)) cUsuario.agregar_partida_usuario(id1,nick1,idPartida);
            }
            id2 = p.getID_J2(); nick2 = p.getNickJugador2();
            if (id2 > 5) {
                cUsuario.crear_usuario(id2,nick2);
                if (!cUsuario.existe_partida_usuario(id2,nick2,idPartida)) cUsuario.agregar_partida_usuario(id2,nick2,idPartida);
            }
            return p;
        }
        else throw new MyException("No existe fichero de partida seleccionada por el ID:" + idPartida);
    }


    /**
     * Operacion ctrl_guardar_partida
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion toArrayList() de Partida)
     * @throws IOException en caso de error con el fichero partida
     */
    public void ctrl_guardar_partida(ArrayList<String> as) throws IOException {
        if (cPartidas.guardar_partida(as)) {
            int id_partida = Integer.parseInt(as.get(0));

            int id1;
            String nick1 = new String();
            String s_aux[] = as.get(1).split(" ");
            id1 = Integer.parseInt(s_aux[0]);
            if (s_aux.length != 1) nick1 = s_aux[1];

            int id2;
            String nick2 = new String();
            s_aux= as.get(2).split(" ");
            id2 = Integer.parseInt(s_aux[0]);
            if (s_aux.length != 1) nick2 = s_aux[1];

            if (id1 > 5) {
                cUsuario.crear_usuario(id1,nick1);
                if (!cUsuario.existe_partida_usuario(id1,nick1,id_partida)) cUsuario.agregar_partida_usuario(id1,nick1,id_partida);
            }
            if (id2 > 5) {
                cUsuario.crear_usuario(id2,nick2);
                if (!cUsuario.existe_partida_usuario(id2,nick2,id_partida)) cUsuario.agregar_partida_usuario(id2,nick2,id_partida);
            }
        }
    }

    /** Operacion ctrl_borrar_partida
     * @param idPartida es el identificador de partida a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito, caso contrario devuelve excepcion
     * @throws IOException en caso de error con el fichero partida
     * @throws MyException en caso de no existir fichero con id=idPartida
     */
    public boolean ctrl_borrar_partida(int idPartida) throws IOException, MyException {
        Partida p = cPartidas.cargar_partida(idPartida);
        boolean b = false;
        if (p != null) {
            int id1, id2;
            String nick1 = new String();
            String nick2 = new String();
            id1 = p.getID_J1(); nick1 = p.getNickJugador1();
            id2 = p.getID_J2(); nick2 = p.getNickJugador2();
            if (b =cPartidas.borrar_partida(idPartida)) {
                if (id1 > 5) {
                    cUsuario.borrar_partida_usuario(id1,nick1,idPartida);
                }
                if (id2 > 5) {
                    cUsuario.borrar_partida_usuario(id2,nick2,idPartida);
                }
            }
            return b;
        }
        else throw new MyException("No existe fichero de partida seleccionada por el ID:" + idPartida);
    }

    //Controlador de Tablero (cTablero)
    /**
     * Operacion ctrl_guardar_tablero
     * @param as es ArrayList con los parametros necesarios para guardar el tablero (utilizando funcion toArrayList() de Tablero)
     * @return devuelve TRUE en caso que se haya guardado con exito, caso contrario devuelve FALSE
     * @throws IOException en caso de error con el fichero partida
     */
    public boolean ctrl_guardar_tablero(ArrayList<String> as) throws IOException {
        return cTablero.guardar_tablero(as,this.ctrl_get_nuevo_ID_tablero());
    }

    /** Operacion ctrl_borrar_tablero
     * @param idTablero el identificador de tablero a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito, caso contrario devuelve excepcion
     * @throws IOException en caso de error con el fichero tablero
     * @throws MyException en caso de no existir fichero con id=idTablero
     */
    public boolean ctrl_borrar_tablero(int idTablero) throws IOException, MyException {
        return cTablero.borrar_tablero(idTablero);
    }

    /**
     * Operacion ctrl_cargar_tablero
     * @param idTablero es el ID de tablero a cargar
     * @return devuelve Tablero con id igual a idTablero, caso contrario devuelve null
     * @throws IOException en caso de no existir el fichero de Tablero
     */
    public Tablero ctrl_cargar_tablero(int idTablero) throws IOException {
        return cTablero.cargar_tablero(idTablero);
    }


    //Controlador de Ranking (cRanking)
    /**
     * Operacion ctrl_importar_ranking
     * @return devuelve el Ranking ubicado en el fichero ranking.txt, caso de no existir devuelve excepcion
     * @throws IOException en caso de fallo con fichero "ranking.txt"
     * @throws MyException en caso de error con sizes de ranking
     */
    public Ranking ctrl_importar_ranking() throws IOException, MyException {
        String path_file = path_ranking + "ranking.txt";
        return cRanking.importar_ranking(path_file);
    }


    /**
     * Operacion ctrl_importar_ranking2
     * @param path_file es el path del fichero de Ranking a importar
     * @return devuelve el Ranking ubicado en path_file, caso de no existir devuelve excepcion
     * @throws IOException en caso de fallo con fichero con "path_file"
     * @throws MyException en caso de error con sizes de ranking
     */
    public Ranking ctrl_importar_ranking2(String path_file) throws IOException, MyException {
        return cRanking.importar_ranking(path_file);
    }



    /**
     * Operacion ctrl_exportar_ranking
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion consultar_all() de cada ElementoRanking)
     * @throws IOException en caso de fallo con fichero de ranking a exportar
     * @throws MyException en caso de error con sizes de ranking
     */
    public void ctrl_exportar_ranking(ArrayList<String> as) throws IOException, MyException {
        cRanking.exportar_ranking(as);
    }

    //Controlador de Usuarios (cUsuario)
    /**
     * Operacion ctrl_crear_usuario
     * @param idJugador identificador de Jugador a crear
     * @param nicknameJugador nickname de Jugador a crear
     * @return devuelve TRUE en caso de haberse creado el fichero Usuario(idJugador,nicknameJugador) con
     * exito, caso contrario (ya existía) devuelve FALSE
     * @throws IOException en caso de fallo al crear fichero Usuario
     */
    public boolean ctrl_crear_usuario(int idJugador,String nicknameJugador) throws IOException {
        return (cUsuario.crear_usuario(idJugador,nicknameJugador));
    }

    /**
     * Operacion ctrl_existe_usuario
     * @param idJugador identificador de Jugador a consultar si existe
     * @param nicknameJugador nickname de Jugador a consultar si existe
     * @return devuelve TRUE en caso de existir el fichero Usuario(idJugador,nicknameJugador) caso contrario devuelve FALSE
     * @throws IOException en caso de fallo con fichero Usuario
     */
    public boolean ctrl_existe_usuario(int idJugador,String nicknameJugador) throws IOException {
        return (cUsuario.existe_usuario(idJugador,nicknameJugador));
    }

    /**
     * Operacion ctrl_borrar_usuario
     * @param idJugador identificador de Jugador a borrar
     * @param nicknameJugador nickname de Jugador a borrar
     * @return b devuelve TRUE en caso de haberse eliminado correctamente el fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     * @throws IOException en caso de fallo con fichero Usuario
     */
    public boolean ctrl_borrar_usuario(int idJugador,String nicknameJugador) throws IOException {
        boolean b = cUsuario.borrar_usuario(idJugador,nicknameJugador);
        return b;
    }

    /**
     * Operacion ctrl_existe_partida_usuario
     * @param idJugador identificador de Jugador a consultar si existe en partida
     * @param nicknameJugador nickname de Jugador a consultar si existe en partida
     * @param idPartida identificador de Partida a consultar si existe el jugador
     * @return devuelve TRUE en caso de existir una partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     * @throws IOException en caso de fallo con fichero Usuario
     */
    public boolean ctrl_existe_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        return (cUsuario.existe_partida_usuario(idJugador,nicknameJugador,idPartida));
    }

    /**
     * Operacion ctrl_agregar_partida_usuario
     * @param idJugador identificador de Jugador a agregar en partida
     * @param nicknameJugador nickname de Jugador a agregar en partida
     * @param idPartida identificador de Partida
     * @return devuelve TRUE en caso de haber agregado correctamente la partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     * @throws IOException en caso de fallo con fichero Usuario
     */
    public boolean ctrl_agregar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        return (cUsuario.agregar_partida_usuario(idJugador,nicknameJugador,idPartida));
    }

    /**
     * Operacion ctrl_borrar_partida_usuario
     * @param idJugador identificador de Jugador a borrar en partida
     * @param nicknameJugador nickname de Jugador a borrar en partida
     * @param idPartida identificador de Partida
     * @return devuelve TRUE en caso de haber eliminado la partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     * @throws IOException en caso de fallo con fichero Usuario
     * @throws MyException en caso de no existir usuario con idJugador y nicknameJugador
     */
    public boolean ctrl_borrar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException, MyException {
        return (cUsuario.borrar_partida_usuario(idJugador,nicknameJugador,idPartida));
    }

    /**
     * Operacion ctrl_listar_partidas_disponibles
     * @param IDjugador identificador de Jugador
     * @param nick nickname de Jugador
     * @return devuelve la lista de partidas disponibles (partidas donde se encuentra el Jugador dentro)
     * @throws IOException en caso de fallo con fichero Usuario
     * @throws MyException en caso de no existir usuario con idJugador y nicknameJugador
     */
    public ArrayList<String> ctrl_listar_partidas_disponibles(int IDjugador, String nick) throws IOException, MyException {
        return cUsuario.listar_partidas_disponibles(IDjugador,nick);
    }

    /**
     * Operacion ctrl_print_partidas_disponibles
     * @param IDjugador identificador de Jugador
     * @param nick nickname de Jugador
     * muestra por salida estandar las partidas disponibles por el Jugador (IDjugador,nick)
     * @throws IOException en caso de fallo con fichero Usuario
     * @throws MyException en caso de no existir usuario con idJugador y nicknameJugador
     */
    public void ctrl_print_partidas_disponibles(int IDjugador, String nick) throws IOException, MyException {
        System.out.println("Partidas disponibles de ID: "+ IDjugador + " ,nickname: " + nick);
        ArrayList<String> partidas = cUsuario.listar_partidas_disponibles(IDjugador,nick);
        if (partidas.size() > 0) {
            System.out.print(partidas.get(0));
            for (int i = 1; i < partidas.size(); ++i) {
                System.out.print(", " + partidas.get(i));
            }
            System.out.println();
        }
        else System.out.print("vacio");
        System.out.println();
    }
}
