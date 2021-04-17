package ControladorPersistencia;

import Dominio.Partida;
import Dominio.Ranking;
import MyException.MyException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CtrlPersitencia {

    private CtrlPartidas cPartidas;
    private CtrlRanking cRanking;
    private CtrlUsuario cUsuario;

    private String path;
    private String path_partidas;
    private String path_ranking;
    private String path_users;

    //Faltaria agregarle el Controlador de Domino
    public CtrlPersitencia() {
        path = "./src/files/";
        path_partidas = path + "partidas/";
        path_ranking =  path + "ranking/";
        path_users = path + "users/";
        cPartidas = new CtrlPartidas(path_partidas);
        cRanking = new CtrlRanking(path_ranking);
        cUsuario = new CtrlUsuario(path_users);
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
            }
        }
    }


    //Controlador de Partidas (cPartidas)
    /**
     * Operacion ctrl_cargar_partida
     * @param idPartida es el ID de partida a cargar
     * @return devuelve Partida con id igual a idPartida, caso contrario devuelve null
     */
    public Partida ctrl_cargar_partida(int idPartida) throws IOException, MyException {
        return cPartidas.cargar_partida(idPartida);
    }
    /**
     * Operacion ctrl_guardar_partida
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion toArrayList() de Partida)
     */
    public void ctrl_guardar_partida(ArrayList<String> as) throws IOException, MyException {
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

            cUsuario.crear_usuario(id1,nick1);
            cUsuario.agregar_partida_usuario(id1,nick1,id_partida);
            cUsuario.crear_usuario(id2,nick2);
            cUsuario.agregar_partida_usuario(id2,nick2,id_partida);
        }
    }

    //Controlador de Ranking (cRanking)
    /**
     * Operacion ctrl_importar_ranking
     * @param path_file es el path del fichero de Ranking a importar
     * @return devuelve el Ranking ubicado en path_file, caso de no existir devuelve excepcion
     */
    public Ranking ctrl_importar_ranking(String path_file) throws IOException, MyException {
        return cRanking.importar_ranking(path_file);
    }
    /**
     * Operacion ctrl_exportar_ranking
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion consultar_all() de cada ElementoRanking)
     */
    public void ctrl_exportar_ranking(ArrayList<String> as) throws IOException, MyException {
        cRanking.exportar_ranking(as);
    }

    //Controlador de Usuarios (cUsuario)
    /**
     * Operacion ctrl_crear_usuario
     * @return devuelve TRUE en caso de haberse creado el fichero Usuario(idJugador,nicknameJugador) con
     * exito, caso contrario (ya existía) devuelve FALSE
     */
    public boolean ctrl_crear_usuario(int idJugador,String nicknameJugador) throws IOException {
        return (cUsuario.crear_usuario(idJugador,nicknameJugador));
    }

    /**
     * Operacion ctrl_existe_usuario
     * @return devuelve TRUE en caso de existir el fichero Usuario(idJugador,nicknameJugador) caso contrario devuelve FALSE
     */
    public boolean ctrl_existe_usuario(int idJugador,String nicknameJugador) throws IOException {
        return (cUsuario.existe_usuario(idJugador,nicknameJugador));
    }

    /**
     * Operacion ctrl_borrar_usuario
     */
    public void ctrl_borrar_usuario(int idJugador,String nicknameJugador) throws IOException {
        cUsuario.borrar_usuario(idJugador,nicknameJugador);
    }

    /**
     * Operacion ctrl_existe_partida_usuario
     * @return devuelve TRUE en caso de existir una partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
    public boolean ctrl_existe_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        return (cUsuario.existe_partida_usuario(idJugador,nicknameJugador,idPartida));
    }

    /**
     * Operacion ctrl_agregar_partida_usuario
     * @return devuelve TRUE en caso de haber agregado correctamente la partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
    public boolean ctrl_agregar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        return (cUsuario.agregar_partida_usuario(idJugador,nicknameJugador,idPartida));
    }

    /**
     * Operacion ctrl_borrar_partida_usuario
     * @return devuelve TRUE en caso de haber eliminado la partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
    public boolean ctrl_borrar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        return (cUsuario.borrar_partida_usuario(idJugador,nicknameJugador,idPartida));
    }

    /**
     * Operacion ctrl_listar_partidas_disponibles
     * @return devuelve la lista de partidas disponibles (partidas donde se encuentra el Jugador dentro)
     */
    public ArrayList<String> ctrl_listar_partidas_disponibles(int IDjugador, String nick) throws IOException, MyException {
        return cUsuario.listar_partidas_disponibles(IDjugador,nick);
    }

}
