package ControladorPersistencia;

import Dominio.Casilla;
import Dominio.Position;
import Dominio.Tablero;

import java.io.*;
import java.util.ArrayList;

public class IOTablero {

    /** Ubicacion de directorio de los tableros para CtrlPersitencia*/
    private String path_tableros;

    /** ID generado para la proximo tablero a guardar*/
    private int ID_max_tableros;


    /**
     * Constructora por defecto
     */
    public IOTablero() {
        this.path_tableros = "./src/files/tableros/";
        this.ID_max_tableros = calcularID_MAX_tableros(this.path_tableros);
    }

    /**
     * Constructora 1
     * @param s path_tableros igual a s
     */
    public IOTablero(String s) {
        this.path_tableros = s;
        this.ID_max_tableros = calcularID_MAX_tableros(this.path_tableros);
    }

    /**
     * Operacion calcularID_MAX_tablero
     * @param path path del directorio de tablero a consultar
     * @return devuelve el ID maximo de todos los tableros (ya inicializados en la carpeta de tableros)
     * */
    private int calcularID_MAX_tableros(String path) {
        File f = new File(path);
        String[] s = f.list();
        boolean b = false;
        String s2 = "index.txt";
        int maxID = 0;
        for (int i = 0; i < s.length; ++i) {
            String res[] = s[i].split(".txt");
            if (!s[i].equals(s2)) {
                int i_aux = Integer.parseInt(res[0]);
                if (maxID < i_aux) maxID = i_aux;
            }
        }
        return (maxID+1);
    }

    /**
     * Operacion get_nuevo_ID_tablero
     * @return devuelve el siguente ID disponible para asignarselo a un tablero
     * */
    public int get_nuevo_ID_tablero() {
        return this.ID_max_tableros;
    }


    /**
     * Operacion guardar_tablero
     * @param tab es la informacion del tablero convertida en una matriz de enteros
     */
    public void guardar_tablero(int[][] tab, int idtablero,int turno) {
        try {
            String path = path_tableros + idtablero + ".txt";
            File f = new File(path);
            if (f.exists()) f.delete();
            f.createNewFile();
            PrintWriter fw = new PrintWriter(f);
            for (int i = 0; i < 8; ++i) {
                String sbuff = new String();
                for (int j = 0; j < 8; ++j) {
                    sbuff = sbuff + tab[i][j];
                }
                fw.write( sbuff+ "\n"); //fila i
            }
            String s_aux = String.valueOf(turno);
            fw.write(s_aux +"\n");
            fw.flush();
            fw.close();
            ++this.ID_max_tableros;
        }
        catch (Exception e) {
            System.out.println("Fallo al guardar el tablero en IOTablero");
            System.out.println(e);
        }
    }

    /**
     * Operacion cargar_tablero
     * @param idTablero es el ID de Tablero a cargar
     * @return devuelve la matriz de enteros de un tablero con id igual a idTablero, caso contrario devuelve tablero inicial
     */
    public int[][] cargar_tablero(int idTablero) {
        try {
            String path = path_tableros + idTablero + ".txt";
            File f = new File(path);
            int[][] map = new int[8][8];
            //crear tablero inicial
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    map[i][j] = 0;
                    if ((i == 3 && j==3) || (i == 4 && j==4)) map[i][j] = 3;
                    else if ((i == 3 && j==4) || (i == 4 && j==3)) map[i][j] = 2;
                }
            }
            if (f.exists()) {
                BufferedReader bf =new BufferedReader(new FileReader(f));
                String s1;
                //int[][] map = new int[8][8];
                for (int i = 0; i < 8; ++i) {
                    s1 = bf.readLine();
                    for (int j = 0; j < 8; ++j) {
                        map[i][j] = Integer.parseInt(String.valueOf(s1.charAt(j)));
                    }
                }
                bf.close();
            }
            return map;
        }
        catch (Exception e) {
            System.out.println("Fallo al cargar un tablero(2) en IOTablero");
            System.out.println(e);
        }
        return null;
    }

    /**
     * Operacion cargar_turno_tablero
     * @param idTablero es el ID de Tablero a cargar
     * @return devuelve el turno de un tablero con id igual a idTablero, caso contrario devuelve -1
     */
    public int cargar_turno_tablero(int idTablero) {
        try {
            String path = path_tableros + idTablero + ".txt";
            File f = new File(path);
            if (f.exists()) {
                BufferedReader bf =new BufferedReader(new FileReader(f));
                String s1;
                int res = 0;
                for (int i = 0; i < 8; ++i) {
                    s1 = bf.readLine();
                }
                s1 = bf.readLine();
                res = Integer.parseInt(s1);
                bf.close();
                return res;
            }
            else return -1;
        }
        catch (Exception e) {
            System.out.println("Fallo al cargar un turno de un tablero en IOTablero");
            System.out.println(e);
        }
        return -1;
    }


    /**
     * Operacion borrar_tablero
     * @param idTablero es el identificador de tablero a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito, caso contrario devuelve FALSE
     */
    public boolean borrar_tablero(int idTablero) {
        boolean b = false;
        try {
            String path = path_tableros + idTablero + ".txt";
            File f = new File(path);
            if (b = f.exists()) {
                b = f.delete();
            }
            return b;
        }
        catch (Exception e) {
            System.out.println("Fallo al borrar tablero con ID:" + idTablero + "en IOTablero");
            System.out.println(e);
        }
        return b;
    }

    /**
     * Operacion listar_tableros_disponibles
     * @return devuelve la lista de tableros disponibles, caso contrario devuelve null
     */
    public ArrayList<String> listar_tableros_disponibles() {
        try {
            ArrayList<String> as = new ArrayList<String>();
            File f = new File(path_tableros);
            String[] s = f.list();
            String s2 = "index.txt";
            for (int i = 0; i < s.length; ++i) {
                String res[] = s[i].split(".txt");
                if (!s[i].equals(s2)) {
                    as.add(res[0]);
                }
            }
            return as;
        }
        catch (Exception e) {
            System.out.println("Fallo de listar_tableros en IOTablero");
            System.out.println(e);
        }
        return null;
    }

}
