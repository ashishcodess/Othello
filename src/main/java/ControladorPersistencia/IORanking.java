package ControladorPersistencia;

import MyException.MyException;
import Dominio.ElementoRanking;
import Dominio.Ranking;

import java.io.*;
import java.util.ArrayList;

public class IORanking {

    /** Ubicacion de directorio del ranking para CtrlPersitencia*/
    private String path_ranking;
    /**modoRanking:true -> solo utiliza fichero ranking.txt, false -> genera varios ficheros
     * en funcion del size del ranking [usado en las pruebas DriverRanking]*/
    private boolean modoRanking; //TRUE: guardara en ranking.txt , FALSE -> guardara en ranking_size.txt

    /**
     * Constructora por defecto
     */
    public IORanking() {
        this.path_ranking = "./src/files/ranking/";
        this.modoRanking = false;
    }

    /**
     * Constructora con path_ranking=s
     */
    public IORanking(String s) {
        this.path_ranking = s;
        this.modoRanking = false;
    }

    /**
     * Constructora con path_ranking=s y modoRanking=b
     */
    public IORanking(String s, boolean b) {
        this.path_ranking = s;
        this.modoRanking = b;
    }

    /**
     * Operacion importar_ranking
     * @param path_fichero es el path del fichero de Ranking a importar
     * @return devuelve el Ranking ubicado en path_file, caso de no existir devuelve excepcion
     */
    public Ranking importar_ranking (String path_fichero) throws IOException, MyException {
        Ranking rank = new Ranking();
        File f = new File(path_fichero);
        if (f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader (f));
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
            bf.close();
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
            if(this.modoRanking) path = path_ranking + "ranking.txt";
            File f = new File(path);
            if (f.exists()) f.delete();
            f.createNewFile();
            PrintWriter fw = new PrintWriter(f);
            for (int i = 0; i < tam; ++i) {
                fw.write(as.get(i) + "\n");
            }
            fw.close();
        }
        else throw new MyException("Tamaño de ranking a exportar incompatible (menor que 0)");
    }


}
