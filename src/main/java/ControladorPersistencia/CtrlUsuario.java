package ControladorPersistencia;

import MyException.MyException;

import java.io.*;
import java.util.ArrayList;


public class CtrlUsuario {

    private final String path_users;

    /**
     * Constructora por defecto
     */
    public CtrlUsuario() {this.path_users = "./src/files/users/";}

    /**
     * Constructora path_users igual a s
     */
    public CtrlUsuario(String s) {this.path_users = s;}

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
     * Operacion borrar_usuario
     */
    public void borrar_usuario(int idJugador, String nick) throws IOException {
        String path = path_users + idJugador + "_" + nick;
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
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
            FileReader fr = new FileReader (f);
            BufferedReader bf =new BufferedReader(fr);
            String s1;
            String s_res = idPartida + ".txt";
            while (((s1 = bf.readLine()) != null) && !res) {
                res = (s1 == s_res);
            }
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
            FileWriter fw = new FileWriter(f,true); //opcion append
            String s_res = idPartida + ".txt";
            fw.write(s_res + "\n");
            fw.close();
            fw.close();
            res = true;
        }
        return res;
    }


    /**
     * Operacion borrar_partida_usuario
     * @return devuelve TRUE en caso de haber eliminado la partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
    public boolean borrar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        String path = path_users + idJugador + "_" + nicknameJugador;
        Boolean res = false;
        if (existe_partida_usuario(idJugador,nicknameJugador,idPartida)) {
            File f = new File(path);
            FileReader fr = new FileReader (f);
            BufferedReader bf = new BufferedReader(fr);
            String s1 = path_users + idJugador + "_" + nicknameJugador;
            String s_res = idPartida + ".txt";


            File f_temp = new File(s1);
            if (f_temp.exists()) f_temp.delete();
            f_temp.createNewFile();
            FileWriter fw_temp = new FileWriter(f_temp);
            while ((s1 = bf.readLine()) != null) {
                if (s1 != s_res) fw_temp.write(s1 + "\n");
            }
            fw_temp.close();
            res = f.delete(); //borrar fichero original
            if (res) {
                File f2 = new File(path);
                f_temp.renameTo(f2);
            }
        }
        return res;
    }

    /**
     * Operacion ctrl_listar_partidas_disponibles
     * @return devuelve la lista de partidas disponibles (partidas donde se encuentra el Jugador dentro), caso contrario
     * devuelve excepcion (no existe el usuario)
     */
    public ArrayList<String> listar_partidas_disponibles(int IDjugador, String nick) throws IOException, MyException {
        if (existe_usuario(IDjugador,nick)) {
            ArrayList<String> res = new ArrayList<String>();
            String path = path_users + IDjugador + "_" + nick;
            File f = new File(path);
            FileReader fr = new FileReader (f);
            BufferedReader bf = new BufferedReader(fr);
            String s1;
            while ((s1 = bf.readLine()) != null) {
                //System.out.println(s1);
                res.add(s1);
            }
            return res;
        }
        else throw new MyException("No existe usuario con ID:" + IDjugador + ", nickname: "+ nick);
    }


}
