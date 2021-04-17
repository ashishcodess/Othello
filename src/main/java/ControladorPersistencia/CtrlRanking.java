package ControladorPersistencia;

import MyException.MyException;
import Dominio.ElementoRanking;
import Dominio.Ranking;

import java.io.*;
import java.util.ArrayList;

public class CtrlRanking {

    private String path_ranking;

    /**
     * Constructora por defecto
     */
    public CtrlRanking() {this.path_ranking = "./src/files/ranking/";}

    /**
     * Constructora path_ranking igual a s
     */
    public CtrlRanking(String s) {this.path_ranking = s;}

    /**
     * Operacion importar_ranking
     * @param path_fichero es el path del fichero de Ranking a importar
     * @return devuelve el Ranking ubicado en path_file, caso de no existir devuelve excepcion
     */
    public Ranking importar_ranking (String path_fichero) throws IOException, MyException {
        Ranking rank = new Ranking();
        File f = new File(path_fichero);
        if (f.exists()) {
            FileReader fr = new FileReader (f);
            BufferedReader bf =new BufferedReader(fr);
            String s1;
            while ((s1 = bf.readLine()) != null) {
                String[] s2 = s1.split(" ");
                int id, total, ganadas, perdidas,empatadas;
                String nick;
                if (s2.length == 6) {
                    id = Integer.parseInt(s2[0]);
                    nick = s2[1];
                    ganadas = Integer.parseInt(s2[2]);
                    perdidas = Integer.parseInt(s2[3]);
                    empatadas = Integer.parseInt(s2[4]);
                    total = Integer.parseInt(s2[5]);
                    ElementoRanking e = new ElementoRanking(id,nick,ganadas,perdidas,empatadas,total);
                    rank.add_al_ranking(e);
                }
            }
        }
        else throw new MyException("Fichero de ranking a importar no existe");
        return rank;
    }

    /**
     * Operacion ctrl_exportar_ranking
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion consultar_all() de cada ElementoRanking)
     */
    public void exportar_ranking(ArrayList<String> as) throws IOException, MyException {
        int tam = as.size();
        if (tam > 0) {
            String path = path_ranking + "ranking" + as.size() + ".txt";
            File f = new File(path);
            if (f.exists()) f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tam; ++i) {
                fw.write(as.get(i) + "\n");
            }
            fw.close();
        }
        else throw new MyException("Tamaño de ranking a exportar incompatible (menor que 0)");

    }


}
