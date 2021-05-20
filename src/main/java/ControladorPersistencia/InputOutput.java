package ControladorPersistencia;


import java.io.*;
import java.util.ArrayList;

enum tipoFichero {USUARIO, PARTIDA,TABLERO, RANKING}

public class InputOutput {

    /**
     * Constructora por defecto de InputOutput
     * */
    public InputOutput() {}


    /**
     * Metodo calcular ID de ficheros
     * @param pathFichero directorio del fichero a procesar
     * @param a tipo de fichero a procesar
     * @return devuelve el maximo numero entero que se encuentra dentro del fichero apuntado por "pathFichero"
     * */
    public int calcularID_Ficheros (String pathFichero, tipoFichero a) {
        File f = new File(pathFichero);
        String[] s = f.list();
        String s2 = "index.txt";
        int maxID = 0;
        String[] res;
        for (String value : s) {
            res = value.split(".txt");
            if (a.equals(tipoFichero.USUARIO)) res = value.split("_");
            if (!value.equals(s2)) {
                int i_aux = Integer.parseInt(res[0]);
                if (maxID < i_aux) maxID = i_aux;
            }
        }
        return (maxID+1);
    }

    /**
     * Metodo crearFichero
     * @param pathF directorio + nombre de fichero a crear
     * @return devuelve TRUE en caso de haberse creado correctamente, caso contrario devuelve FALSE */
    public boolean crearFichero (String pathF) throws IOException {
        boolean res = false;
        File f = new File(pathF);
        if (!f.exists()) {
            res = f.createNewFile();
        }
        return res;
    }

    /**
     * Metodo Guardar Info Fichero
     * @param pathF directorio + nombre de fichero a guardar informacion
     * @param as informacion que queremos guardar
     * @param a tipo de fichero a guardar informacion*/
    public void guardarInfoFichero(String pathF, ArrayList<String> as, tipoFichero a) throws IOException {
        File f = new File(pathF);
        if (f.exists()) f.delete();
        f.createNewFile();
        PrintWriter fw = new PrintWriter(f);
        int tam = as.size();
        int i = 0;
        if (a.equals(tipoFichero.PARTIDA)) {
            fw.write( as.get(1)+ "\n"); //IDjugador1 nick1
            fw.write( as.get(2)+ "\n"); //IDjugador2 nick2
            fw.write(as.get(3)+ "\n"); //modo juego
            fw.write(as.get(4)  + "\n"); //reglas
            fw.write(as.get(5) + "\n"); //turno
            fw.write("\n");
            i = 7;
        }
        for (; i < tam; ++i) {
            fw.write(as.get(i) + "\n");
        }
        fw.flush();
        fw.close();

    }

    /**
     * Metodo leer Fichero
     * @param pathF directorio + nombre de fichero a leer
     * @return devuelve un ArrayList de Strings con toda la informacion que contiene dicho fichero*/
    public ArrayList<String> leerFichero(String pathF) throws IOException {
        ArrayList<String> as = new ArrayList<>();
        File f = new File(pathF);
        if (f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader(f));
            String s1;
            while ((s1 = bf.readLine()) != null) {
                as.add(s1);
            }
            bf.close();
        }
        return as;
    }

    /**
     * Metodo Existe Fichero
     * @param pathF directorio + nombre de fichero a comprobar si existe
     * @return devuelve TRUE en caso de existir el fichero, caso contrario devuelve FALSE */
    public boolean existeFichero (String pathF) {
        File f = new File(pathF);
        return f.exists();
    }

    /**
     * Metodo Borrar Fichero
     * @param pathF directorio + nombre de fichero a borrar
     * @return devuelve TRUE en caso de haberse borrado con exito el fichero, caso contrario devuelve FALSE */
    public boolean borrarFichero (String pathF) {
        File f = new File(pathF);
        boolean b;
        if (b = f.exists()) {
            b = f.delete();
        }
        return b;
    }
    /**
     * Metodo Existe EN Fichero (buscar si una string esta contenida dentro del fichero)
     * @param pathF directorio + nombre de fichero a comprobar si existe
     * @param s informacion que queremos comprobar que exista
     * @return devuelve TRUE en caso de existir la string "s" dentro de el fichero, caso contrario devuelve FALSE */
    public boolean existe_en_fichero (String pathF, String s) throws IOException {
        boolean res = false;
        File f = new File(pathF);
        if (f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader (f));
            String s1;
            while (((s1 = bf.readLine()) != null) && !res) {
                res = (s1.equals(s));
            }
            bf.close();
        }
        return res;
    }

    /**
     * Metodo Agregar EN Fichero (agrega la informacion al fichero)
     * @param pathF directorio + nombre de fichero a realizar la accion
     * @param s informacion que queremos agregar al fichero
     * @return devuelve TRUE en caso de agregar correctamente la string "s" dentro de el fichero, caso contrario devuelve FALSE */
    public boolean agregar_a_fichero(String pathF, String s) throws IOException {
        boolean res = false;
        File f = new File(pathF);
        if (f.exists()) {
            PrintWriter pw = new PrintWriter(new FileOutputStream(pathF, true));
            String sres = s + "\n";
            pw.append(sres);
            pw.flush();
            pw.close();
            res = true;
        }
        return res;
    }

    /**
     * Metodo Borrar EN Fichero (Borrar la informacion de un fichero)
     * @param pathF directorio + nombre de fichero a realizar la accion
     * @param s informacion que queramos borrar de el fichero
     * @return devuelve TRUE en caso de borrar correctamente la string "s" dentro de el fichero, caso contrario devuelve FALSE */
    public void borrar_de_fichero(String pathF, String s) throws IOException {
        if (existe_en_fichero(pathF,s)) {
            ArrayList<String> as = leerFichero(pathF);
            int i;
            boolean b = false;
            for (i = 0; ((i < as.size()) && (!b)); ++i) {
                b = (as.get(i)).equals(s);
                if (b) as.remove(i);
            }
            //volver a escribir fichero con el elemento ya borrado
            PrintWriter pw = new PrintWriter(pathF);
            for (i = 0; i < as.size(); ++i) pw.println(as.get(i));
            pw.flush();
            pw.close();
        }
    }

    /**
     * Este crea el directorio en caso de no existir
     * @param s_path path del directorio a crear */
    public void crearDirectorio(String s_path) {
        File f = new File(s_path);
        if (!f.exists()) {
            f.mkdir();
        }
    }

    /**
     * Metodo "listar ficheros de Directorio"
     * @param pathDir directorio donde queramos realizar la accion
     * @return devuelve un ArrayList de Strings con todos los ficheros que contiene el directorio pasado como parametro */
    public ArrayList<String> listarFicherosDeDirectorio(String pathDir) {
        File f = new File(pathDir);
        String[] s = f.list();
        ArrayList<String> res = new ArrayList<>();
        for (String sAux : s) {
            if (!sAux.equals("index.txt")) res.add(sAux);
        }
        return res;
    }

}
