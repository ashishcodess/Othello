package ControladorPersistencia;

import MyException.MyException;

import java.io.*;
import java.util.ArrayList;


public class IOUsuario {

    /** Ubicacion de directorio de los usuarios para CtrlPersitencia*/
    private final String path_users;

    /** ID generado para el proximo Usuario a crear*/
    private int ID_max;

    /**
     * Constructora por defecto
     */
    public IOUsuario() {
        this.path_users = "./src/files/users/";
        this.ID_max = calcularID_MAX();
    }

    /**
     * Constructora path_users igual a s
     */
    public IOUsuario(String s) {
        this.path_users = s;
        this.ID_max = calcularID_MAX();
    }

    /**
     * Este metodo devuelve el ID maximo de todos los usuarios (ya inicializados en la carpeta de users)
     * */
    private int calcularID_MAX() {
        File f = new File(path_users);
        String[] s = f.list();
        int maxID = 0;
        for (int i = 0; i < s.length; ++i) {
            String res[] = s[i].split("_");
            int i_aux = Integer.parseInt(res[0]);
            if (maxID < i_aux) maxID = i_aux;
        }
        return maxID;
    }

    /**
     * Este metodo devuelve el siguente ID disponible para asignarselo a un Usuario
     * */
    public int get_nuevo_ID_user() {
        return (++this.ID_max);
    }

    /**
     * Operacion crear_usuario
     * @return devuelve TRUE en caso de haberse creado el fichero Usuario(idJugador,nicknameJugador) con
     * exito, caso contrario (ya existía) devuelve FALSE
     */
    public boolean crear_usuario(int idJugador,String nicknameJugador) throws IOException {
        String path = path_users + idJugador + "_" + nicknameJugador;
        File f = new File(path);
        boolean res = false;
        if (!f.exists()) {
            f.createNewFile();
            res = true;
        }
        return res;
    }

    /**
     * Operacion existe_usuario
     * @return devuelve TRUE en caso de existir el fichero Usuario(idJugador,nicknameJugador) caso contrario devuelve FALSE
     */
    public boolean existe_usuario(int idJugador, String nick) {
        String path = path_users + idJugador + "_" + nick;
        File f = new File(path);
        return (f.exists());
    }

    /**
     * Operacion borrar_usuario a partir de su idJugador y nickname
     */
    public boolean borrar_usuario(int idJugador, String nick) throws IOException {
        String path = path_users + idJugador + "_" + nick;
        File f = new File(path);
        boolean b = false;
        if (b = f.exists()) {
            b = f.delete();
        }
        return b;
    }


    /**
     * Operacion existe_partida_usuario
     * @return  devuelve TRUE si existe el idPartida dentro del fichero de Usuario, caso contrario (no exista fichero o no encontrado dentro del mismo)
     * devuelve FALSE
     */
    public boolean existe_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        String path = path_users + idJugador + "_" + nicknameJugador;
        boolean res = false;
        File f = new File(path);
        if (f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader (f));
            String s1;
            while (((s1 = bf.readLine()) != null) && !res) {
                res = (Integer.parseInt(s1) == idPartida);
            }
            bf.close();
        }
        return res;
    }

    //GESTION DE PARTIDAS
    /**
     * Operacion agregar_partida_usuario
     * @return devuelve TRUE en caso de haber agregado correctamente la partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
    public boolean agregar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        String path = path_users + idJugador + "_" + nicknameJugador;
        boolean res = false;
        File f = new File(path);
        if (f.exists() && (!existe_partida_usuario(idJugador,nicknameJugador,idPartida))) {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(path), true));
            String s_res = String.valueOf(idPartida) +"\n";
            pw.append(s_res);
            pw.flush();
            pw.close();
            res = true;
        }
        return res;
    }


    /**
     * Operacion borrar_partida_usuario
     * @return devuelve TRUE en caso de haber eliminado la partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
        public boolean borrar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException, MyException {
        String path = path_users + idJugador + "_" + nicknameJugador;
        Boolean res = false;
        if (existe_partida_usuario(idJugador,nicknameJugador,idPartida)) {
            ArrayList<String> partidas = listar_partidas_disponibles(idJugador,nicknameJugador);
            int i = 0;
            boolean b = false;
            for (i = 0; (i < partidas.size()) && !b; ++i) { //buscar indice a borrar
                b = (Integer.parseInt(partidas.get(i)) == idPartida);
                if (b) partidas.remove(i);
            }
            //partidas ahora contiene el nuevo contenido (con la partida seleccionada ya borrada)
            PrintWriter pw = new PrintWriter(path);
            for (i = 0; i < partidas.size(); ++i) pw.println(partidas.get(i));
            pw.flush();
            pw.close();
        }
        return res;
    }


    /**
     * Operacion ctrl_listar_partidas_disponibles
     * @return devuelve la lista de partidas disponibles (partidas donde se encuentra el Jugador dentro), caso contrario
     * devuelve excepcion (no existe el usuario)
     */
    public ArrayList<String> listar_partidas_disponibles(int IDjugador, String nick) throws IOException, MyException {
        String path = path_users + IDjugador + "_" + nick;
        File f = new File(path);
        if (f.exists()) {
            ArrayList<String> res = new ArrayList<String>();
            BufferedReader bf = new BufferedReader(new FileReader (f));
            String s1;
            while ((s1 = bf.readLine()) != null) {
                res.add(s1);
            }
            bf.close();
            return res;
        }
        else throw new MyException("No existe usuario con ID:" + IDjugador + ", nickname: "+ nick);
    }
}
