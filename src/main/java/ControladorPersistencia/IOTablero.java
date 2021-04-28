package ControladorPersistencia;

import Dominio.Partida;
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
     * @param as es ArrayList con los parametros necesarios para guardar un tablero
     * @return devuelve TRUE en caso que se haya guardado con exito, caso contrario devuelve FALSE
     * @throws IOException en caso de error con el fichero tablero
     */
    public boolean guardar_tablero(ArrayList<String> as, int idtablero) throws IOException {
        if (as.size() == 8) {
            String path = path_tableros + idtablero + ".txt";
            File f = new File(path);
            if (f.exists()) f.delete();
            f.createNewFile();
            PrintWriter fw = new PrintWriter(f);
            for (int i = 0; i < 8; ++i) {
                fw.write( as.get(i)+ "\n"); //fila i
            }
            fw.write("\n");
            fw.flush();
            fw.close();
            ++this.ID_max_tableros;
            return true;
        }
        else return false;
    }

    /**
     * Operacion cargar_tablero
     * @param idTablero es el ID de Tablero a cargar
     * @return devuelve Tablero con id igual a idTablero, caso contrario salta excepcion
     * @throws IOException en caso de fallo con fichero de Tablero
     */
    public Tablero cargar_tablero(int idTablero) throws IOException {
        String path = path_tableros + idTablero + ".txt";
        File f = new File(path);
        if (f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader(f));
            String s1;
            int[][] map = new int[8][8];
            for (int i = 0; i < 8; ++i) {
                s1 = bf.readLine();
                for (int j = 0; j < 8; ++j) {
                    map[i][j] = Integer.parseInt(String.valueOf(s1.charAt(j)));
                }
            }
            bf.close();
            Tablero ta = new Tablero(map);
            return ta;
        }
        else return null;
    }


    /**
     * Operacion borrar_tablero
     * @param idTablero es el identificador de tablero a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito, caso contrario devuelve FALSE
     * @throws IOException en caso de no existir el fichero de tablero a borrar
     */
    public boolean borrar_tablero(int idTablero) throws IOException{
        String path = path_tableros + idTablero + ".txt";
        File f = new File(path);
        boolean b = false;
        if (b = f.exists()) {
            b = f.delete();
        }
        return b;
    }

}
