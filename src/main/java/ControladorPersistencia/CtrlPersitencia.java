package ControladorPersistencia;

import Dominio.Partida.Partida;
import Dominio.Partida.Tablero;
import Dominio.Ranking.*;
import MyException.MyException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CtrlPersitencia {

    private final InputOutput io = new InputOutput();

    private final String dirIni;
    private final String dir_usuarios;
    private final String dir_partidas;
    private final String dir_ranking;
    private final String dir_tablero;

    private int idMax_usuario;
    private int idMax_partida;
    private int idMax_tablero;

    //imagenes
    public enum tipoIMG {IMG_VACIA,IMG_DISPONIBLE,IMG_NEGRA,IMG_BLANCA}
    private final String dirImagenes;

    /**
     * Constructora por defecto de CtrlPersistencia
     * */
    public CtrlPersitencia() {
        this.dirIni = "./src/files/";
        this.dir_partidas = this.dirIni + "partidas/";
        this.dir_usuarios = this.dirIni + "users/";
        this.dir_ranking =  this.dirIni + "ranking/";
        this.dir_tablero =  this.dirIni + "tableros/";
        this.dirImagenes = this.dirIni + "fichas/";
        InicializarDirPersitencia();
        calcular_IDs_maximos();
    }

    /**
     * Constructora de CtrlPersistencia
     * @param s_path es el path personalizado a cargar
     * */
    public CtrlPersitencia(String s_path) {
        this.dirIni = s_path;
        this.dir_partidas = this.dirIni + "partidas/";
        this.dir_usuarios = this.dirIni + "users/";
        this.dir_ranking =  this.dirIni + "ranking/";
        this.dir_tablero =  this.dirIni + "tableros/";
        this.dirImagenes = this.dirIni + "fichas/";
        InicializarDirPersitencia();
        calcular_IDs_maximos();
    }

    /**
     * Metodo calcular ID's maximos asignado de Usuario, Partida y Tablero
     * */
    private void calcular_IDs_maximos() {
        this.idMax_usuario = io.calcularID_Ficheros(dir_usuarios,tipoFichero.USUARIO);
        this.idMax_partida = io.calcularID_Ficheros(dir_partidas,tipoFichero.PARTIDA);
        this.idMax_tablero = io.calcularID_Ficheros(dir_tablero,tipoFichero.TABLERO);
        ctrl_incr_nuevo_ID_partida();
    }


    /**
     * Metodo Inicializar Dir. Persistencia (inicializa los directorios en caso de no existir)
     * */
    private void InicializarDirPersitencia() {
        File f = new File(this.dirIni);
        if (!f.exists()) {
            io.crearDirectorio(dirIni);
            io.crearDirectorio(dir_partidas);
            io.crearDirectorio(dir_ranking);
            io.crearDirectorio(dir_usuarios);
            io.crearDirectorio(dir_tablero);
        }
    }

    /**
     * Metodo consultar direccion imagen
     * @param t es el tipo de imagen a cargar [vacia, disponible, blanca, negra]
     * @return devuelve la direccion del icono seleccionado
     * */
    public String consultar_dir_IMG(tipoIMG t) {
        String s = dirImagenes;
        switch (t) {
            case IMG_VACIA:
                s = s + "vacia.png";
                break;
            case IMG_DISPONIBLE:
                s = s + "disponible.png";
                break;
            case IMG_BLANCA:
                s = s + "blanca.png";
                break;
            case IMG_NEGRA:
                s = s + "negra.png";
                break;
        }
        return s;
    }

    /**
     * Operacion ctrl_get_nuevo_ID_user
     * @return devuelve el siguente ID disponible para asignarselo a un Usuario
     * */
    public int ctrl_get_nuevo_ID_user() {
        return idMax_usuario;
    }

    /**
     * Operacion ctrl_get_nuevo_ID_Partida
     * @return devuelve el siguente ID disponible para asignarselo a una Partida
     * */
    public int ctrl_get_nuevo_ID_Partida() {
        return idMax_partida;
    }

    /**
     * Operacion ctrl_get_nuevo_ID_tablero
     * @return devuelve el siguente ID disponible para asignarselo a un tablero a guardar
     * */
    public int ctrl_get_nuevo_ID_tablero() {
        return idMax_tablero;
    }


    /**
     * Operacion ctrl_incr_nuevo_ID_user (incrementa contador ID user)
     * */
    private void ctrl_incr_nuevo_ID_user() {
        ++idMax_usuario;
    }

    /**
     * Operacion ctrl_incr_nuevo_ID_partida (incrementa contador ID partida)
     * */
    private void ctrl_incr_nuevo_ID_partida() {
        ++idMax_partida;
    }

    /**
     * Operacion ctrl_incr_nuevo_ID_tablero (incrementa contador ID tablero)
     * */
    private void ctrl_incr_nuevo_ID_tablero() {
        ++idMax_tablero;
    }


    //Controlador de Partidas (cPartidas)

    /**
     * Operacion ctrl_leer_modo_partida
     * @param idPartida es el ID de partida a cargar
     * @return devuelve el modo de la partida con igual a idPartida, caso contrario (no existe partida) devuelve -1
     */
    public int ctrl_leer_modo_partida(int idPartida) throws IOException {
        int modo = -1;
        String s_fichero = dir_partidas +idPartida + ".txt";
        ArrayList<String> as = io.leerFichero(s_fichero);
        if (as.size() > 3) {
            modo = Integer.parseInt(as.get(2));
        }
        return modo;
    }

    /**
     * Operacion ctrl_cargar_partida
     * @param idPartida es el ID de partida a cargar
     * @return devuelve Partida con id igual a idPartida, caso contrario devuelve null
     */
    public Partida ctrl_cargar_partida(int idPartida) throws IOException, MyException{
        String pathF = dir_partidas + idPartida + ".txt";
        ArrayList<String> as = io.leerFichero(pathF);
        int id1, id2, modo, turno;
        int[] reglas = new int[3];
        String nick1 = "";
        String nick2 = "";
        String s1 = as.get(0);
        String[] s2 = s1.split(" ");
        id1 = Integer.parseInt(s2[0]);
        if (s2.length != 1) nick1 = s2[1];
        s1 = as.get(1);
        s2 = s1.split(" ");

        id2 = Integer.parseInt(s2[0]);
        if (s2.length != 1) nick2 = s2[1];
        modo = Integer.parseInt(as.get(2));
        s1 = as.get(3);
        s2 = s1.split(" ");
        reglas[0] = Integer.parseInt(s2[0]);
        reglas[1] = Integer.parseInt(s2[1]);
        reglas[2] = Integer.parseInt(s2[2]);
        turno = Integer.parseInt(as.get(4));
        int[][] map = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            s1 = as.get(i+6);
            for (int j = 0; j < 8; ++j) {
                map[i][j] = Integer.parseInt(String.valueOf(s1.charAt(j)));
            }
        }
        Tablero t = new Tablero(map);
        Partida res = new Partida(idPartida,modo,reglas,turno,id1,nick1,id2,nick2,t);

        if (id1 > 5) {
            ctrl_crear_usuario(id1,nick1);
            if (ctrl_no_existe_partida_usuario(id1,nick1,idPartida)) ctrl_agregar_partida_usuario(id1,nick1,idPartida);
        }
        if (id2 > 5) {
            ctrl_crear_usuario(id2,nick2);
            if (ctrl_no_existe_partida_usuario(id2,nick2,idPartida)) ctrl_agregar_partida_usuario(id2,nick2,idPartida);
        }
        return res;

    }


    /**
     * Operacion ctrl_guardar_partida
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion toArrayList() de Partida)
     */
        public void ctrl_guardar_partida(ArrayList<String> as) throws IOException {
            int id_partida  = Integer.parseInt(as.get(0));
            String pathF = dir_partidas + id_partida + ".txt";
            io.guardarInfoFichero(pathF,as,tipoFichero.PARTIDA);
            ctrl_incr_nuevo_ID_partida();
            int id1, id2;
            String nick1= "";
            String nick2 = "";
            String s1 = as.get(1);
            String[] s2 = s1.split(" ");
            id1 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick1 = s2[1];
            s1 = as.get(3);
            s2 = s1.split(" ");
            id2 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick2 = s2[1];
            if (id1 > 5) {
                if (ctrl_no_existe_partida_usuario(id1,nick1,id_partida)) ctrl_agregar_partida_usuario(id1,nick1,id_partida);
            }
            if (id2 > 5) {
                if (ctrl_no_existe_partida_usuario(id2,nick2,id_partida)) ctrl_agregar_partida_usuario(id2,nick2,id_partida);
            }
    }

    /** Operacion ctrl_borrar_partida
     * @param idPartida es el identificador de partida a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito, caso contrario devuelve excepcion
     */
    public boolean ctrl_borrar_partida(int idPartida) throws IOException {
        boolean b;
        int id1, id2;
        String nick1= "";
        String nick2= "";
        String pathF = dir_partidas + idPartida + ".txt";
        ArrayList<String> as = io.leerFichero(pathF);

        String[] s2 = as.get(0).split(" ");
        id1 = Integer.parseInt(s2[0]);
        if (s2.length != 1) nick1 = s2[1];

        s2 = as.get(1).split(" ");
        id2 = Integer.parseInt(s2[0]);
        if (s2.length != 1) nick2 = s2[1];

        if (id1 > 5) {
            io.borrar_de_fichero(dir_usuarios + id1 + "_"+ nick1, String.valueOf(idPartida));
        }
        if (id2 > 5) {
            io.borrar_de_fichero(dir_usuarios + id2 + "_"+ nick2, String.valueOf(idPartida));
        }

        //borrar fichero de partida
        b = io.borrarFichero(pathF);
        return b;
    }

    //Controlador de Tablero (cTablero)
    /**
     * Operacion ctrl_guardar_tablero
     * @param tab es ArrayList con los parametros necesarios para guardar el tablero (utilizando funcion toArrayList() de Tablero)
     */
    public void ctrl_guardar_tablero(int[][] tab, int turno) throws IOException {
        int idTablero = ctrl_get_nuevo_ID_tablero();

        //limpiar casillas disponibles (1)
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (tab[i][j] == 1) tab[i][j] = 0;
            }
        }
        ArrayList<String> as = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            StringBuilder sbuff = new StringBuilder();
            for (int j = 0; j < 8; ++j) {
                sbuff.append(tab[i][j]);
            }
            as.add(sbuff.toString()); //fila i
        }
        String s_aux = String.valueOf(turno);
        as.add(s_aux);

        String pathF = dir_tablero + idTablero + ".txt";
        ctrl_incr_nuevo_ID_tablero();
        io.guardarInfoFichero(pathF,as,tipoFichero.TABLERO);
    }

    /** Operacion ctrl_borrar_tablero
     * @param idTablero el identificador de tablero a borrar
     * @return devuelve TRUE en caso que se haya borrado con exito, caso contrario devuelve excepcion
     */
    public boolean ctrl_borrar_tablero(int idTablero) {
        if (idTablero > 0) {
            String pathF = dir_tablero + idTablero + ".txt";
            return io.borrarFichero(pathF);
        }
        else return false;
    }

    /**
     * Operacion ctrl_cargar_tablero
     * @param idTablero es el ID de tablero a cargar
     * @return devuelve la matriz de enteros de un tablero con id igual a idTablero, caso contrario devuelve tablero vacio
     */
    public int[][] ctrl_cargar_tablero(int idTablero) throws IOException {
        int[][] map = new int[8][8];
        //crear tablero inicial
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                map[i][j] = 0;
                //if ((i == 3 && j==3) || (i == 4 && j==4)) map[i][j] = 3;
                //else if ((i == 3 && j==4) || (i == 4 && j==3)) map[i][j] = 2;
            }
        }
        String pathF = dir_tablero + idTablero + ".txt";
        File f = new File(pathF);
        if (f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader(f));
            String s1;
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

    /**
     * Operacion ctrl_cargar_turno_tablero
     * @param idTablero es el ID de tablero a cargar
     * @return devuelve la matriz de enteros de un tablero con id igual a idTablero, caso contrario devuelve -1 */
    public int ctrl_cargar_turno_tablero(int idTablero) throws IOException {
        int turno;
        String pathF = dir_tablero + idTablero + ".txt";
        ArrayList<String> as = io.leerFichero(pathF);
        turno = Integer.parseInt(as.get(as.size()-1));
        return turno;
    }

    /**
     * Operacion ctrl_tableros_disponibles
     * @return devuelve el Arraylist de tableros disponibles (ficheros de tipo tablero)
     */
    public ArrayList<String> ctrl_tableros_disponibles() {
        ArrayList<String> as = io.listarFicherosDeDirectorio(dir_tablero);
        boolean b = false;
        String s = "index.txt";
        for (int i = 0; ((i < as.size()) && (!b)); ++i) {
            b = (as.get(i)).equals(s);
            if (b) as.remove(i);
        }
        for (int i = 0; i < as.size(); ++i) { //borrar parte de .txt
            s = as.get(i);
            String sAux[] = s.split(".txt");
            as.set(i, sAux[0]);
        }
        return as;
    }

    /**
     * Operacion ctrl_print_tableros_disponibles
     * muestra por salida estandar los tableros disponibles
     */
    public void ctrl_print_tableros_disponibles() {
        System.out.println("Tableros disponibles");
        ArrayList<String> as= io.listarFicherosDeDirectorio(dir_tablero);
        if (as != null) {
            System.out.print(as.get(0));
            for (int i = 1; i < as.size(); ++i) {
                String s_aux = as.get(i);
                System.out.print(", " + s_aux);
            }
            System.out.println();
        }
        else System.out.print("vacio");
        System.out.println();
    }



    //Controlador de Ranking (cRanking)
    /**
     * Operacion ctrl_importar_ranking
     * @return devuelve el Ranking ubicado en el fichero apuntado por s,caso de no existir devuelve excepcion
     */
    public Ranking ctrl_importar_ranking(String s) throws MyException, IOException {
        String pathF = dir_ranking + s;
        ArrayList<String> as = io.leerFichero(pathF);
        Ranking rank = new Ranking();
        int tam = as.size();
        int i;
        //logros
        for (i = 0; i < 5; ++i) {
            String sAux = as.get(i);
            String[] s2 = sAux.split(" ");
            int t, id1, id2;
            String nick1 = "";
            String nick2 = "";
            switch (i) {
                case 0: //partida mas corta
                    t = Integer.parseInt(s2[0]);
                    id1 = Integer.parseInt(s2[1]);
                    if (id1 < 6) { //es una maquina //s2.length==4
                        id2 = Integer.parseInt(s2[2]);
                        nick2 = s2[3];
                    }
                    else {
                        nick1 = s2[2];
                        id2 = Integer.parseInt(s2[3]);
                        if (id2 > 6) nick2 = s2[4];
                    }
                    rank.cambiar_logro_partida(Logros.tipoLogro.PARTIDA_CORTA,nick1,id1,nick2,id2,t,0);
                    break;

                case 1: //Diferencia de fichas mas grande
                    int t2 = 0;
                    if (s2.length == 7) {
                        id1 = Integer.parseInt(s2[1]);
                        nick1 = s2[2];
                        t = Integer.parseInt(s2[3]);
                        id2 = Integer.parseInt(s2[4]);
                        nick2 = s2[5];
                        t2 = Integer.parseInt(s2[6]);
                        rank.cambiar_logro_partida(Logros.tipoLogro.FICHAS_DIFF,nick1,id1,nick2,id2,t,t2);
                    }
                    else if (s2.length == 6) {
                        id1 = Integer.parseInt(s2[1]);
                        if (id1 < 6) {
                            t = Integer.parseInt(s2[2]);
                            id2 = Integer.parseInt(s2[3]);
                            nick2 = s2[4];
                            t2 = Integer.parseInt(s2[5]);
                        }
                        else {
                            nick1 = s2[2];
                            t = Integer.parseInt(s2[3]);
                            id2 = Integer.parseInt(s2[4]);
                            t2 = Integer.parseInt(s2[5]);
                        }
                        rank.cambiar_logro_partida(Logros.tipoLogro.FICHAS_DIFF,nick1,id1,nick2,id2,t,t2);
                    }
                    break;

                case 2: //PARTIDAS TOTALES
                    if (s2.length == 3) {
                        t = Integer.parseInt(s2[0]);
                        id1 = Integer.parseInt(s2[1]);
                        nick1 = s2[2];
                        rank.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_TOTALES,nick1,id1,t);
                    }
                    break;

                case 3: //PARTIDAS GANADAS
                    if (s2.length == 3) {
                        t = Integer.parseInt(s2[0]);
                        id1 = Integer.parseInt(s2[1]);
                        nick1 = s2[2];
                        rank.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_GANADAS,nick1,id1,t);
                    }
                    break;

                case 4: //PARTIDAS PERDIDAS
                    if (s2.length == 3) {
                        t = Integer.parseInt(s2[0]);
                        id1 = Integer.parseInt(s2[1]);
                        nick1 = s2[2];
                        rank.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_PERDIDAS,nick1,id1,t);
                    }
                    break;
            }

        }
        //ranking
        for (i=5; i<tam; ++i) {
            String[] s2 = as.get(i).split(" ");
            int id, ganadas, perdidas,empatadas;
            String nick;
            if (s2.length == 6) {
                id = Integer.parseInt(s2[0]);
                nick = s2[1];
                ganadas = Integer.parseInt(s2[2]);
                perdidas = Integer.parseInt(s2[3]);
                empatadas = Integer.parseInt(s2[4]);
                ElementoRanking e = new ElementoRanking(id,nick,ganadas,perdidas,empatadas);
                rank.add_al_ranking(e);
            }
        }
        return rank;
    }


    /**
     * Operacion ctrl_exportar_ranking
     * @param as es ArrayList con los parametros necesarios para guardar la partida (utilizando funcion consultar_all() de cada ElementoRanking)
     */
    public void ctrl_exportar_ranking(ArrayList<String> as, String f) throws IOException {
        String pathf = dir_ranking + f;
        io.guardarInfoFichero(pathf,as,tipoFichero.RANKING);
    }


    //Controlador de Usuarios (cUsuario)
    /**
     * Operacion ctrl_crear_usuario
     * @param idJugador identificador de Jugador a crear
     * @param nicknameJugador nickname de Jugador a crear
     * @return devuelve TRUE en caso de haberse creado el fichero Usuario(idJugador,nicknameJugador) con
     * exito, caso contrario (ya existía) devuelve FALSE
     */
    public boolean ctrl_crear_usuario(int idJugador,String nicknameJugador) throws IOException {
        String pathF = dir_usuarios + idJugador + "_" + nicknameJugador;
        boolean b = io.crearFichero(pathF);
        if (b) ctrl_incr_nuevo_ID_user();
        return b;
    }

    /**
     * Operacion ctrl_existe_usuario
     * @param idJugador identificador de Jugador a consultar si existe
     * @param nicknameJugador nickname de Jugador a consultar si existe
     * @return devuelve TRUE en caso de existir el fichero Usuario(idJugador,nicknameJugador) caso contrario devuelve FALSE
     */
    public boolean ctrl_existe_usuario(int idJugador,String nicknameJugador) {
        String pathF = dir_usuarios + idJugador + "_" + nicknameJugador;
        return io.existeFichero(pathF);
    }

    /**
     * Operacion ctrl_borrar_usuario
     * @param idJugador identificador de Jugador a borrar
     * @param nicknameJugador nickname de Jugador a borrar
     * @return b devuelve TRUE en caso de haberse eliminado correctamente el fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
    public boolean ctrl_borrar_usuario(int idJugador,String nicknameJugador) {
        String pathF = dir_usuarios + idJugador + "_" + nicknameJugador;
        return io.borrarFichero(pathF);
    }

    /**
     * Operacion ctrl_no_existe_partida_usuario
     * @param idJugador identificador de Jugador a consultar si existe en partida
     * @param nicknameJugador nickname de Jugador a consultar si existe en partida
     * @param idPartida identificador de Partida a consultar si existe el jugador
     * @return devuelve TRUE en caso de existir una partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
    private boolean ctrl_no_existe_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        String pathF = dir_usuarios + idJugador + "_" + nicknameJugador;
        String s = String.valueOf(idPartida);
        return !io.existe_en_fichero(pathF,s);
    }

    /**
     * Operacion ctrl_agregar_partida_usuario
     * @param idJugador identificador de Jugador a agregar en partida
     * @param nicknameJugador nickname de Jugador a agregar en partida
     * @param idPartida identificador de Partida
     * @return devuelve TRUE en caso de haber agregado correctamente la partida con id igual a idPartida dentro del fichero Usuario(idJugador,nicknameJugador),
     * caso contrario devuelve FALSE
     */
    public boolean ctrl_agregar_partida_usuario(int idJugador,String nicknameJugador, int idPartida) throws IOException {
        boolean res = false;
        String pathF = dir_usuarios + idJugador + "_" + nicknameJugador;
        String s = String.valueOf(idPartida);
        if (!io.existe_en_fichero(pathF,s)) {
            res = io.agregar_a_fichero(pathF,s);
        }

        return res;
    }

    /**
     * Operacion ctrl_borrar_partida_usuario
     * @param idJugador identificador de Jugador a borrar en partida
     * @param nicknameJugador nickname de Jugador a borrar en partida
     * @param idPartida identificador de Partida
     */
    public void ctrl_borrar_partida_usuario(int idJugador, String nicknameJugador, int idPartida) throws IOException {
        String pathF = dir_usuarios + idJugador + "_" + nicknameJugador;
        String s = String.valueOf(idPartida);
        io.borrar_de_fichero(pathF,s);
    }

    /**
     * Operacion ctrl_listar_partidas_disponibles
     * @param idJugador identificador de Jugador
     * @param nicknameJugador nickname de Jugador
     * @return devuelve la lista de partidas disponibles (partidas donde se encuentra el Jugador dentro)

     */
    public ArrayList<String> ctrl_listar_partidas_disponibles(int idJugador, String nicknameJugador) throws IOException {
        ArrayList<String> as;
        String pathF = dir_usuarios + idJugador + "_" + nicknameJugador;
        as = io.leerFichero(pathF);
        return as;
    }

    /**
     * Operacion ctrl_print_partidas_disponibles
     * @param IDjugador identificador de Jugador
     * @param nick nickname de Jugador
     * muestra por salida estandar las partidas disponibles por el Jugador (IDjugador,nick)
     */
    public void ctrl_print_partidas_disponibles(int IDjugador, String nick) throws IOException {
        System.out.println("Partidas disponibles de ID: "+ IDjugador + " ,nickname: " + nick);
        ArrayList<String> partidas = ctrl_listar_partidas_disponibles(IDjugador,nick);
        if (partidas.size() > 0) {
            System.out.print(partidas.get(0));
            for (int i = 1; i < partidas.size(); ++i) {
                System.out.print(", " + partidas.get(i));
            }
            System.out.println();
        }
        else System.out.print("vacio");
        System.out.println();
    }
}
