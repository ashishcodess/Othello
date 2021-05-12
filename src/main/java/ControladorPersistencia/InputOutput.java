package ControladorPersistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

enum tipoFichero {USUARIO, PARTIDA,TABLERO, RANKING}

public class InputOutput {

    private final String dir_usuarios;
    private final String dir_partidas;
    private final String dir_ranking;
    private final String dir_tablero;

    /*atributos ID maximos desde CtrlPersitencia*/

    public InputOutput() {
        String dir = "./src/files/";
        this.dir_partidas = dir + "partidas/";
        this.dir_usuarios = dir + "users/";
        this.dir_ranking =  dir + "ranking/";
        this.dir_tablero =  dir + "tableros/";
    }


    private int calcularID_Ficheros (String pathFichero, tipoFichero a) {
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


    public boolean crearFichero (String pathF) {
        boolean res = false;
        try {
            File f = new File(pathF);
            if (!f.exists()) {
                res = f.createNewFile();
            }
        }
        catch (Exception e) {
            System.out.println("Error al crear el fichero con pathF: \n" + pathF);
        }
        return res;
    }

    public void guardarInfoFichero(String pathF, ArrayList<String> as, tipoFichero a) {
        //PARTIDA, TABLERO (convertir a arraylist de strings), ranking
        try {
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
        catch (Exception e) {
            System.out.println("Error al guardar info en fichero con pathF: \n" + pathF);
        }
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

}
