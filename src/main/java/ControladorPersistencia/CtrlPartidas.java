package ControladorPersistencia;


import MyException.MyException;
import Dominio.Partida;
import Dominio.Tablero;

import java.io.*;
import java.util.ArrayList;

public class CtrlPartidas {

    private String path_partidas;

    /**
     * Constructora por defecto
     */
    public CtrlPartidas() {this.path_partidas = "./src/files/partidas/";}

    /**
     * Constructora path_partida igual a s
     */
    public CtrlPartidas(String s) {this.path_partidas = s;}

    /**
     * Operacion cargar_partida
     * @param idPartida es el ID de partida a cargar
     * @return devuelve Partida con id igual a idPartida, caso contrario salta excepcion
     */
    public Partida cargar_partida(int idPartida) throws IOException, MyException {
        String path = path_partidas + idPartida + ".txt";
        File f = new File(path);
        if (f.exists()) {
            int id1,id2, modo, turno;
            int reglas[] = new int[3];
            String nick1 = new String();
            String nick2 = new String();

            FileReader fr = new FileReader (f);
            BufferedReader bf =new BufferedReader(fr);

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
            /*
            System.out.println("Extraidos: " +  id1 + " " + nick1);
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
            Tablero t = new Tablero(map);
            Partida res = new Partida(idPartida,modo,reglas,turno,id1,nick1,id2,nick2,t);
            return res;
        }
        else throw new MyException("No existe fichero de partida seleccionada por el ID:" + idPartida);
    }

    /**
     * Operacion guardar_partida
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion toArrayList() de Partida)
     * @return devuelve TRUE en caso que se haya guardado con exito, caso contrario devuelve FALSE
     */
    public boolean guardar_partida(ArrayList<String> as) throws IOException, MyException {
        if (as.size() == 15) { //size correcto (se ha pasado correctamente el ArrayList generadod desde partida
            String idPartida = as.get(0);
            String path = path_partidas + idPartida + ".txt";
            File f = new File(path);
            if (f.exists()) f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
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
            fw.close();
            return true;
        }
        else return false;
    }

}
