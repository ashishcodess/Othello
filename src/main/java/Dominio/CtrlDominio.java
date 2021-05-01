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

    private static int estado_partida;
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


    private static void ejecutarPartida(Partida p) {

    }

    //Argum: argumentos/info necesaria para crear la partida
    private static Partida iniciarPartida(ArrayList<String> argum) {
        return null; //por ahora para pruebas
    }

    private static void listar_partidas_disponibles(int id, String nick) {
        //enviara al Controlador de Presentacion
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
                listar_partidas_disponibles(code,nickname);
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
