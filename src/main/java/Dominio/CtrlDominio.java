package Dominio;

import ControladorPersistencia.CtrlPersitencia;
import MyException.MyException;

import java.util.ArrayList;
import java.util.Scanner;

/*Sergio: no hay que poner nada de print's desde esta clase,
habria que ir pasandole mensajes/peticiones al de Presentacion con las cosas
a imprimir

*/
public class CtrlDominio {

    static int code; //ID jugador1
    static String nickname; //nickname jugador1

    static int id_2; //ID jugador2
    static String nick_2; //nickname jugador2

    private static CtrlPersitencia cp;

    private static Ranking ranking;

    static Scanner scan = new Scanner(System.in); //temporal por ahora

    private static int estado_partida; //para conocer el estado de la partida desde la capa de Presentacion
    private static Partida partida_activa;

    public CtrlDominio() {
        this.code = -1;
        this.nickname = "";
        login_inicial_presentacion();
        cp = new CtrlPersitencia(true);
        ranking = cp.ctrl_importar_ranking();
    }


    private static void login_inicial_presentacion() {
        //funcion de login desde CtrlPresentacion
        //para los atributos de code y nickname
    }


    /*ejecutar 1 ronda de la partida:
    * mandarle el estado de como va la partida hacia la capa de presentacion
    * */
    private static int ejecutarPartida(Partida p) {
        return -1; //por ahora para evitar fallos
    }

    //Argum: argumentos/info necesaria para crear la partida
    private static Partida iniciarPartida(ArrayList<String> argum) {
        try {
            int idPartida = cp.ctrl_get_nuevo_ID_Partida();
            int id1, id2;
            String nick1 = new String();
            String nick2 = new String();
            int modo = Integer.parseInt(argum.get(0));
            if (modo<0 || modo > 2) throw new MyException("Modo de juego incorrecto");
            int reglas[] = new int[3];
            reglas[0] = Integer.parseInt(argum.get(1));
            reglas[1] = Integer.parseInt(argum.get(2));
            reglas[2] = Integer.parseInt(argum.get(3));
            /*Para CapaPresentacion: hay que hacer funcion de seleccionar
            el bando cuando estamos en modo2*/

            //seleccion de id's y de nicknames's (caso de no tener que sea "")
            id1 = Integer.parseInt(argum.get(4));
            nick1 = argum.get(5);
            id2 = Integer.parseInt(argum.get(6));
            nick2 = argum.get(7);
            Tablero t = new Tablero();//por ahora, diferenciar si se quiere cargar un tablero nuevo
            Partida p = new Partida(idPartida,modo,reglas,0,id1,nick1,id2,nick2,t);
            return p;
        }
        catch (Exception e) {
            //que hacer cuando salta alguna excepcion
            System.out.println(e); //por ahora para pruebas
        }
        return null; //por ahora para pruebas
    }

    private static ArrayList<String> listar_partidas_disponibles(int id, String nick) {
        //enviara al Controlador de Presentacion (para mostrar que partidas puede cargar/borrar el jugador)
        return cp.ctrl_listar_partidas_disponibles(id,nick);
    }

    //para imprimir tablero hacia la capa de presentacion
    public int[][] getTableroPartida() {
        return partida_activa.getTableroPartida().toMatrix();
    }

    /**
     * Metodo cargarPartida
     * Carga una partida a partir de un fichero guardado previamente y seguidamente ejecuta dicha partida cargada
     * @param idPartida id de partida a cargar
     * @return devuelve la partida cargada, caso contrario devuelve null
     * */
    private static Partida cargarPartida(int idPartida) {
        try {
            Partida p = cp.ctrl_cargar_partida(idPartida);
            return p;
        }
        catch (Exception e) {
            String s = ("Fallo al cargar la partida con ID:" + idPartida);
            //llamar a CtrlPresentacion con el mensaje de s
        }
        return null;
    }


    //devolvera los mensajes que tendra que imprimir en pantalla
    public static ArrayList<String> peticion_menu(int peticion, ArrayList<String> argum) {
        ArrayList<String> as = new ArrayList<String>();
        int idPartida = -1;
        switch (peticion){
            case 1:
                partida_activa = iniciarPartida(argum);
                ejecutarPartida(partida_activa);
                break;
            case 2:
                as = listar_partidas_disponibles(code,nickname);
                break;
            case 3:
                idPartida = Integer.parseInt(argum.get(0));
                partida_activa = cargarPartida(idPartida);
                ejecutarPartida(partida_activa);
                break;
            case 4:
                idPartida = Integer.parseInt(argum.get(0));
                if(cp.ctrl_borrar_partida(idPartida)) {
                    as.add("Partida Borrada correctamente");
                }
                else as.add("No se ha borrado correctamente");
                break;
            case 5:
                //TableroPersonalizado();
                break;
            case 6:
                //as = consultarRanking();
                break;
            case 7:
                //as = consultar_Estadisticas();
                break;
            default:
                System.out.println("Introduce un numero valido");
                break;
        }
        return as;
    }

}
