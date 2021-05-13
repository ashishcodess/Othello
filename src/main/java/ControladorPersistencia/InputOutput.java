package ControladorPersistencia;


import java.io.*;
import java.util.ArrayList;

enum tipoFichero {USUARIO, PARTIDA,TABLERO, RANKING}

public class InputOutput {


    public InputOutput() {

    }


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
        return maxID;
    }

    public boolean crearFichero (String pathF) throws IOException {
        boolean res = false;
        File f = new File(pathF);
        if (!f.exists()) {
            res = f.createNewFile();
        }
        return res;
    }

    public void guardarInfoFichero(String pathF, ArrayList<String> as, tipoFichero a) throws IOException {
        //PARTIDA, TABLERO (convertir a arraylist de strings), ranking
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

    public boolean existeFichero (String pathF) {
        File f = new File(pathF);
        return f.exists();
    }

    public boolean borrarFichero (String pathF) {
        File f = new File(pathF);
        boolean b;
        if (b = f.exists()) {
            b = f.delete();
        }
        return b;
    }

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

    public boolean agregar_a_fichero(String pathF, String s) throws IOException {
        boolean res = false;
        File f = new File(pathF);
        if (f.exists()) {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(pathF), true));
            String sres = s + "\n";
            pw.append(sres);
            pw.flush();
            pw.close();
            res = true;
        }
        return res;
    }

    public boolean borrar_de_fichero(String pathF, String s) throws IOException {
        boolean res = false;
        if (existe_en_fichero(pathF,s)) {
            ArrayList<String> as = leerFichero(pathF);

            int i = 0;
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
        return res;
    }

    /**
     * Este crea el directorio en caso de no existir
     * @param s_path path del directorio a crear
     * @return devuelve cierto en caso de haberlo creado correctamente, caso contrario (fallo o ya existia) devuelve falso
     * */
    public boolean crearDirectorio(String s_path) {
        boolean b = false;
        File f = new File(s_path);
        if (!f.exists()) {
            b = f.mkdir();
        }
        return b;
    }

    public String[] listarFicherosDeDirectorio(String pathDir) {
        File f = new File(pathDir);
        return f.list();
    }

}
