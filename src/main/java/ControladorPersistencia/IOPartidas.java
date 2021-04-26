package ControladorPersistencia;


import Dominio.PartidaModo0;
import Dominio.PartidaModo1;
import Dominio.PartidaModo2;
import MyException.MyException;
import Dominio.Partida;
import Dominio.Tablero;

import java.io.*;
import java.util.ArrayList;

public class IOPartidas {

    /** Ubicacion de directorio de las partidas para CtrlPersitencia*/
    private String path_partidas;

    /** ID generado para la proxima partida a guardar*/
    private int ID_max_partida;


    /**
     * Constructora por defecto
     */
    public IOPartidas() {
        this.path_partidas = "./src/files/partidas/";
        this.ID_max_partida = calcularID_MAX_partida(this.path_partidas);
    }

    /**
     * Constructora 1
     * @param s path_partida igual a s
     */
    public IOPartidas(String s) {
        this.path_partidas = s;
        this.ID_max_partida = calcularID_MAX_partida(this.path_partidas);
    }

    /**
     * Operacion calcularID_MAX_partida
     * @param path path del directorio de partidas a consultar
     * @return devuelve el ID maximo de todos los partidas (ya inicializados en la carpeta de partidas)
     * */
    private int calcularID_MAX_partida(String path) {
        File f = new File(path);
        String[] s = f.list();
        int maxID = 0;
        for (int i = 0; i < s.length; ++i) {
            String res[] = s[i].split(".txt");
            int i_aux = Integer.parseInt(res[0]);
            if (maxID < i_aux) maxID = i_aux;
        }
        return (maxID+1);
    }

    /**
     * Operacion get_nuevo_ID_Partida
     * @return devuelve el siguente ID disponible para asignarselo a una Partida
     * */
    public int get_nuevo_ID_Partida() {
        return this.ID_max_partida;
    }


    /**
     * Operacion guardar_partida
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion toArrayList() de Partida)
     * @return devuelve TRUE en caso que se haya guardado con exito, caso contrario devuelve FALSE
     * @throws IOException en caso de error con el fichero partida
     */
    public boolean guardar_partida(ArrayList<String> as) throws IOException{
        if (as.size() == 15) { //size correcto (se ha pasado correctamente el ArrayList generadod desde partida
            String idPartida = as.get(0);
            String path = path_partidas + idPartida + ".txt";
            File f = new File(path);
            if (f.exists()) f.delete();
            f.createNewFile();
            PrintWriter fw = new PrintWriter(f);
            fw.write( as.get(1)+ "\n"); //IDjugador1 nick1
            fw.write( as.get(2)+ "\n"); //IDjugador2 nick2
            fw.write(as.get(3)+ "\n"); //modo juego
            fw.write(as.get(4)  + "\n"); //reglas
            fw.write(as.get(5) + "\n"); //turno
            fw.write("\n");
            //PARTE DEL TABLERO
            for (int i = 7; i  < as.size();++i) {
                fw.write(as.get(i) +"\n");
            }
            fw.flush();
            fw.close();
            ++this.ID_max_partida;
            return true;
        }
        else return false;
    }

    /**
     * Operacion borrar_partida
     * @param idPartida es el identificador de partida a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito, caso contrario devuelve FALSE
     * @throws IOException en caso de no existir el fichero de partida a borrar
     */
    public boolean borrar_partida(int idPartida) throws IOException{
        String path = path_partidas + idPartida +".txt";
        File f = new File(path);
        boolean b = false;
        if (b = f.exists()) {
            b = f.delete();
        }
        return b;
    }


    /**
     * Operacion leer_modo_partida
     * @param idPartida es el ID de partida a cargar
     * @return devuelve el modo de la partida con igual a idPartida, caso contrario (no existe partida) devuelve -1
     * @throws IOException en caso de no existir el fichero de partida
     */
    public int leer_modo_partida(int idPartida) throws IOException {
        String path = path_partidas + idPartida + ".txt";
        File f = new File(path);
        int res = -1;
        if (f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader (f));
            String s1 = bf.readLine(); //id1 user1
            s1 = bf.readLine(); //id2 user2
            s1 = bf.readLine(); //modo
            res = Integer.parseInt(s1);
        }
        return res;
    }

    /**
     * Operacion cargar_partida
     * @param idPartida es el ID de partida a cargar
     * @return devuelve Partida con id igual a idPartida, caso contrario salta excepcion
     * @throws IOException en caso de fallo con fichero de Partida
     */
    public Partida cargar_partida(int idPartida) throws IOException {
        String path = path_partidas + idPartida + ".txt";
        File f = new File(path);
        if (f.exists()) {
            int id1,id2, modo, turno;
            int reglas[] = new int[3];
            String nick1 = new String();
            String nick2 = new String();

            BufferedReader bf =new BufferedReader(new FileReader (f));

            String s1 = bf.readLine(); //buffer por si acaso...
            String s2[] = s1.split(" ");
            id1 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick1 = s2[1];

            s1 = bf.readLine(); //buffer por si acaso...
            s2 = s1.split(" ");
            id2 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick2 = s2[1];

            s1 = bf.readLine();
            modo = Integer.parseInt(s1);

            s1 = bf.readLine();
            s2 = s1.split(" ");
            reglas[0] = Integer.parseInt(s2[0]);
            reglas[1] = Integer.parseInt(s2[1]);
            reglas[2] = Integer.parseInt(s2[2]);

            s1 = bf.readLine();
            turno = Integer.parseInt(s1);

            //por si tenemos que ver la info que hemos extraido del fichero

            /*System.out.println("Extraidos: " +  id1 + " " + nick1);
            System.out.println("Extraidos: " +  id2 + " " + nick2);
            System.out.println("Extraidos: " +  modo);
            System.out.println("Extraidos: " +  reglas[0] + reglas[1] + reglas[2]);
            System.out.println("Extraidos: " +  turno);*/

            s1 = bf.readLine(); //espacio vacio

            int[][] map = new int[8][8];
            for (int i = 0; i < 8; ++i) {
                s1 = bf.readLine();
                for (int j = 0; j < 8; ++j) {
                    map[i][j] = Integer.parseInt(String.valueOf(s1.charAt(j)));
                }
            }
            bf.close();
            Tablero t = new Tablero(map);
            Partida res = new Partida(idPartida,modo,reglas,turno,id1,nick1,id2,nick2,t);
            return res;
        }
        else return null;
    }

    /**
     * Operacion cargar_partida_modo0
     * @param idPartida es el ID de partida a cargar
     * @return devuelve PartidaModo0 con id igual a idPartida,caso contrario (no existe o modo incorrecto) salta excepcion
     * @throws IOException en caso de fallo con fichero Partida
     * @throws MyException en caso de fallo al cargar el modo de juego (modo juego incorrecto)
     */
    public PartidaModo0 cargar_partida_modo0(int idPartida) throws IOException, MyException {
        String path = path_partidas + idPartida + ".txt";
        File f = new File(path);
        if (f.exists()) {
            int id1,id2, modo, turno;
            int reglas[] = new int[3];
            String nick1 = new String();
            String nick2 = new String();
            BufferedReader bf =new BufferedReader(new FileReader (f));
            String s1 = bf.readLine(); //buffer por si acaso...
            String s2[] = s1.split(" ");
            id1 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick1 = s2[1];

            s1 = bf.readLine(); //buffer por si acaso...
            s2 = s1.split(" ");
            id2 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick2 = s2[1];

            s1 = bf.readLine();
            modo = Integer.parseInt(s1);
            if (modo == 0) {
                s1 = bf.readLine();
                s2 = s1.split(" ");
                reglas[0] = Integer.parseInt(s2[0]);
                reglas[1] = Integer.parseInt(s2[1]);
                reglas[2] = Integer.parseInt(s2[2]);
                s1 = bf.readLine();
                turno = Integer.parseInt(s1);
                s1 = bf.readLine(); //espacio vacio
                int[][] map = new int[8][8];
                for (int i = 0; i < 8; ++i) {
                    s1 = bf.readLine();
                    for (int j = 0; j < 8; ++j) {
                        map[i][j] = Integer.parseInt(String.valueOf(s1.charAt(j)));
                    }
                }
                bf.close();
                Tablero t = new Tablero(map);
                PartidaModo0 res = new PartidaModo0(idPartida,modo,reglas,turno,id1,nick1,id2,nick2,t);
                return res;
            }
            else throw new MyException("La partida que se quiere cargar no es del modoPartida incorrecto al solicitado");
        }
        else return null;
    }

    /**
     * Operacion cargar_partida_modo1
     * @param idPartida es el ID de partida a cargar
     * @return devuelve PartidaModo1 con id igual a idPartida, caso contrario (no existe o modo incorrecto) salta excepcion
     * @throws IOException en caso de fallo con fichero Partida
     * @throws MyException en caso de fallo al cargar el modo de juego (modo juego incorrecto)
     */
    public PartidaModo1 cargar_partida_modo1(int idPartida) throws IOException, MyException {
        String path = path_partidas + idPartida + ".txt";
        File f = new File(path);
        if (f.exists()) {
            int id1,id2, modo, turno;
            int reglas[] = new int[3];
            String nick1 = new String();
            String nick2 = new String();
            BufferedReader bf =new BufferedReader(new FileReader (f));
            String s1 = bf.readLine(); //buffer por si acaso...
            String s2[] = s1.split(" ");
            id1 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick1 = s2[1];

            s1 = bf.readLine(); //buffer por si acaso...
            s2 = s1.split(" ");
            id2 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick2 = s2[1];

            s1 = bf.readLine();
            modo = Integer.parseInt(s1);
            if (modo == 1) {
                s1 = bf.readLine();
                s2 = s1.split(" ");
                reglas[0] = Integer.parseInt(s2[0]);
                reglas[1] = Integer.parseInt(s2[1]);
                reglas[2] = Integer.parseInt(s2[2]);
                s1 = bf.readLine();
                turno = Integer.parseInt(s1);
                s1 = bf.readLine(); //espacio vacio
                int[][] map = new int[8][8];
                for (int i = 0; i < 8; ++i) {
                    s1 = bf.readLine();
                    for (int j = 0; j < 8; ++j) {
                        map[i][j] = Integer.parseInt(String.valueOf(s1.charAt(j)));
                    }
                }
                bf.close();
                Tablero t = new Tablero(map);
                PartidaModo1 res = new PartidaModo1(idPartida,modo,reglas,turno,id1,nick1,id2,nick2,t);
                return res;
            }
            else throw new MyException("La partida que se quiere cargar no es del modoPartida incorrecto al solicitado");
        }
        else return null;
    }

    /**
     * Operacion cargar_partida_modo2
     * @param idPartida es el ID de partida a cargar
     * @return devuelve PartidaModo2 con id igual a idPartida, caso contrario (no existe o modo incorrecto) salta excepcion
     * @throws IOException en caso de fallo con fichero Partida
     * @throws MyException en caso de fallo al cargar el modo de juego (modo juego incorrecto)
     */
    public PartidaModo2 cargar_partida_modo2(int idPartida) throws IOException, MyException {
        String path = path_partidas + idPartida + ".txt";
        File f = new File(path);
        if (f.exists()) {
            int id1,id2, modo, turno;
            int reglas[] = new int[3];
            String nick1 = new String();
            String nick2 = new String();
            BufferedReader bf =new BufferedReader(new FileReader (f));
            String s1 = bf.readLine(); //buffer por si acaso...
            String s2[] = s1.split(" ");
            id1 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick1 = s2[1];

            s1 = bf.readLine(); //buffer por si acaso...
            s2 = s1.split(" ");
            id2 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick2 = s2[1];

            s1 = bf.readLine();
            modo = Integer.parseInt(s1);
            if (modo == 2) {
                s1 = bf.readLine();
                s2 = s1.split(" ");
                reglas[0] = Integer.parseInt(s2[0]);
                reglas[1] = Integer.parseInt(s2[1]);
                reglas[2] = Integer.parseInt(s2[2]);
                s1 = bf.readLine();
                turno = Integer.parseInt(s1);
                s1 = bf.readLine(); //espacio vacio
                int[][] map = new int[8][8];
                for (int i = 0; i < 8; ++i) {
                    s1 = bf.readLine();
                    for (int j = 0; j < 8; ++j) {
                        map[i][j] = Integer.parseInt(String.valueOf(s1.charAt(j)));
                    }
                }
                bf.close();
                Tablero t = new Tablero(map);
                PartidaModo2 res = new PartidaModo2(idPartida,modo,reglas,turno,id1,nick1,id2,nick2,t);
                return res;
            }
            else throw new MyException("La partida que se quiere cargar no es del modoPartida incorrecto al solicitado");
        }
        else return null;
    }


}
