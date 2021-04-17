
package Dominio;

import MyException.MyException;

import java.io.*;

public class JugadorPersona extends Jugador {
    /*Atributos*/
    private String nickname;
    private boolean persona_nueva; //persona_nueva -> 1 si no existia el fichero, 0 -> si previamente se ha utilizado dicho usuario

    /*Constructora*/
    /**
     * Constructora por defecto (vacia) de JugadorPersona
     * */
    public JugadorPersona ()  {
        super();
    }

    /**
     * Constructora JugadorPersona (sin asignarle un nickname y sin crear fichero de la Persona)
     * */
    public JugadorPersona (int idJugador) throws MyException {
        super(idJugador);
        if (idJugador < 6) throw new MyException("El ID:" + idJugador + " pertenece a una maquina o esta fuera de rango(es negativo)");
    }

    /**
     * Constructora JugadorPersona (idJugador,nicknameJugador, además de crear Fichero Usuario en caso de no existir)
     * */
    public JugadorPersona (int idJugador,String nicknameJugador) throws IOException, MyException {
        super(idJugador);
        if (idJugador < 6) throw new MyException("El ID:" + idJugador + " pertenece a una maquina o esta fuera de rango(es negativo)");
        this.nickname = nicknameJugador;

        //Crear fichero "idJugador_nickname"
        this.persona_nueva = crearFicheroUsuario(idJugador,nicknameJugador);
    }

    /*Sets y Gets*/

    /**
     * Operacion set del atributo ID
     * @param nuevoID indica el nuevo valor que tomara el atributo id de Jugador
     */
    public void modificar_IDJugador(int nuevoID) {
        super.modificar_id(nuevoID);
    }

    /**
     * Operacion set del atributo nickName
     * @param nuevoNick indica el nuevo valor que tomara el atributo nickname de JugadorPersona
     */
    public void modificar_nickname(String nuevoNick) {
        this.nickname = nuevoNick;
    }

    /**
     * Operacion get del atributo nickname
     * @return  devuelve el nickname de JugadorPersona
     */
    public String get_Nickname() {
        return this.nickname;
    }

    /**
     * Operacion get del atributo ID
     * @return  devuelve el ID de JugadorPersona
     */
    public int get_PersonaID() {
        return super.getID();
    }

    /**
     * Operacion get del atributo persona_nueva
     * @return  devuelve el atributo bool de JugadorPersona que indica si esta persona existia anteriormente o
     * se ha creado de nuevo (nuevo fichero Persona)
     */
    public boolean get_persona_nueva()  {
        return this.persona_nueva;
    }


    /*Devuelve el conjunto formado por nickname y ID -> nickname#ID */
    /**
     * Operacion get del conjunto nickname#ID
     * @return  devuelve el conjunto identificado como nickname#ID
     */
    public String get_ID_TAG_persona() {
        return (this.nickname + "#" + String.valueOf(super.getID()));
    }


    //Estas cosas tendrian que ir en la capa de persistencia
    /**
     * Operacion crearFicheroUsuario
     * @return  devuelve el conjunto identificado como nickname#ID
     */
    public boolean crearFicheroUsuario(int idJugador,String nicknameJugador) throws IOException{
        String path = "./src/files/users/" + idJugador + "_" + nicknameJugador;
        File f = new File(path);
        boolean res = false;
        if (!f.exists()) {
            f.createNewFile();
            res = true;
        }
        return res;
    }


    /*
     * Borrar para la entrega
     * */
    //Estas cosas tendrian que ir en la capa de persistencia
    public Partida Cargar_partida(String path_partida, int idPartida) throws IOException {
        File f = new File(path_partida);
        if(f.exists()) {
            BufferedReader bf =new BufferedReader(new FileReader (f));

            String s1;
            String s2[] = bf.readLine().split(" ");

            int id2, modo, turno;

            int reglas[] = new int[3];
            String nick1 = new String();
            String nick2 = new String();

            int id1 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick1 = s2[1];

            s1 = bf.readLine();
            s2 = s1.split(" ");
            id2 = Integer.parseInt(s2[0]);
            if (s2.length != 1) nick2 = s2[1];

            s1 = bf.readLine();
            modo = Integer.parseInt(s1);

            s1 = bf.readLine();
            s2 =  bf.readLine().split(" ");
            reglas[0] = Integer.parseInt(s2[0]);
            reglas[1] = Integer.parseInt(s2[1]);
            reglas[2] = Integer.parseInt(s2[2]);

            s1 = bf.readLine();
            turno = Integer.parseInt(s1);

            System.out.println("Extraidos: " +  id1 + " " + nick1);
            System.out.println("Extraidos: " +  id2 + " " + nick2);
            System.out.println("Extraidos: " +  modo);
            System.out.println("Extraidos: " +  reglas[0] + reglas[1] + reglas[2]);
            System.out.println("Extraidos: " +  turno);
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
        return null; //no existe el fichero partida a cargar
    }

    /*
     * Borrar para la entrega
     * */
    //Estas cosas tendrian que ir en la capa de persistencia
    public void Guardar_partida(Partida par_guardar) throws IOException {
        int idPartida = par_guardar.getIdPartida();
        String path = "./src/files/partidas/" + "partida" + String.valueOf(idPartida) + ".txt";
        File f = new File(path);
        if (f.exists()) f.delete();
        f.createNewFile();

        FileWriter fw = new FileWriter(f);
        fw.write(par_guardar.getID_J1() + " " + par_guardar.getNickJugador1() + "\n");
        fw.write(par_guardar.getID_J2() + " " + par_guardar.getNickJugador2() + "\n");
        fw.write(par_guardar.getModoDeJuegoPartida() + "\n");
        fw.write(par_guardar.getReglasPartida()  + "\n");
        fw.write(par_guardar.getTurnoPartida()+ "\n");
        fw.write("\n");

        //ESTO ES UN EJEMPLO HABRIA QUE PASAR EL TABLERO DE LA PARTIDA Y TRADUCIRLO A STRING
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                if ((i==3 && j==4)  || (i==4 && j==3) ) sbuff = sbuff + '1';
                else sbuff = sbuff + '0';
            }
            fw.write(sbuff + "\n");
        }
        fw.close();
    }

}
