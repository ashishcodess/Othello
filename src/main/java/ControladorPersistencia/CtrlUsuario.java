package ControladorPersistencia;

import java.io.*;


//SERGIO: CLASE AUN SIN TESTEAR

public class CtrlUsuario {

    private String path_users;

    /**
     * Constructora por defecto
     */
    public CtrlUsuario() {this.path_users = "./src/files/ranking/";}

    public CtrlUsuario(String s) {this.path_users = s;}

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

    public Boolean existe_usuario(int idJugador, String nick) {
        String path = path_users + idJugador + "_" + nick;
        File f = new File(path);
        return (f.exists());
    }

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
    public Boolean existe_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        String path = path_users + idJugador + "_" + nicknameJugador;
        Boolean res = false;
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

    //PARTIDAS


    public boolean agregar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        String path = path_users + idJugador + "_" + nicknameJugador;
        boolean res = false;
        File f = new File(path);
        if (f.exists() && (!existe_partida_usuario(idJugador,nicknameJugador,idPartida))) {
            FileWriter fw = new FileWriter(f,true); //opcion append
            String s_res = idPartida + ".txt";
            fw.write(s_res + "\n");
            fw.close();
            res = true;
        }
        return res;
    }



    public Boolean borrar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
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



}
