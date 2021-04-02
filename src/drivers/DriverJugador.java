package drivers;

import jugador.Jugador;
import jugador.JugadorMaquina;
import jugador.JugadorPersona;
import jugador.ConjuntoMaquinas;
import main.java.juego.Partida;

import java.io.IOException;
import java.util.*;

/*
 * PARA VER QUE FUNCIONE CORRECTAMENTE
 *
 * */

public class DriverJugador {

    static Scanner scan = new Scanner(System.in);

    public static void test_crear_jugador(int IDJugador) {
        //Boolean b = false; //para control de errores
        Jugador res = new Jugador(IDJugador);
        System.out.println("Jugador creado con ID:" + IDJugador);
        System.out.println("Prueba getID: " + res.getID());
        System.out.println("Probando modificarID (-1)");
        if (IDJugador != -1 ) {
            res.modificar_id(-1);
            System.out.println("Nuevo ID jugador (getID): " + res.getID());
            //b = true;
        } else System.out.println("ID del jugador ya es -1");
        //return b;
    }

    public static void test_crear_jugadorMaquina(int idMaquina, int profundidad){
        //Boolean b = false; //para control de errores
        JugadorMaquina res = new JugadorMaquina(idMaquina,profundidad);
        System.out.println("Maquina creada con ID:" + idMaquina);
        System.out.println("Prueba get_MaquinaID : " + res.get_MaquinaID());
        System.out.println("Prueba get_Profundidad : " + res.get_profundidadMaquina());
        System.out.println("Probando modificarID (-1)");
        if (idMaquina != -1 ) {
            res.modificar_id_maquina(-1);
            System.out.println("NUEVO get_MaquinaID : " + res.get_MaquinaID());
            //b = true;
        } else System.out.println("ID de MAQUINA ya es -1");

        //return b;
    }

    public static void test_crear_jugadorPersona(int idPersona, String nick) throws IOException {
        //Boolean b = true; //para control de errores
        JugadorPersona res = new JugadorPersona(idPersona,nick);
        System.out.println("Maquina creada con ID:" + idPersona);
        System.out.println("Prueba get_MaquinaID : " + res.get_PersonaID());
        System.out.println("Prueba get_Nickname : " + res.get_Nickname());
        //return b;
    }

    public static void test_varias_maquinas() {
        Vector <JugadorMaquina>Maquinas = new Vector<JugadorMaquina>(6);
        for (int i = 0; i < 6; ++i) {
            JugadorMaquina res = new JugadorMaquina(i,i);
            Maquinas.addElement(res);
        }
        System.out.println("Listar elementos");
        for (int i = 0; i < 6; ++i) {
            JugadorMaquina res = Maquinas.elementAt(i);
            System.out.println("Maquina " + res.get_MaquinaID() + " , " + res.get_profundidadMaquina());
        }
    }

    public static void test_guardar_partida() throws IOException {
        JugadorPersona res = new JugadorPersona(6,"as");
        Partida par = new Partida(0,3,"1 1 1" + "",0,6,"as",7,"as2");
        res.Guardar_partida(par);
    }

    public static void test_cargar_partida() throws IOException {
        JugadorPersona res = new JugadorPersona(6,"as");
        String f = "./files/partidas/partida0.txt";
        res.Cargar_partida(f);
    }

    public static void main(String[] args) throws IOException {
        boolean b = true;
        while (b) {
            System.out.println("OPCIONES:");
            System.out.println("-1 - SALIR DEL DRIVER");
            System.out.println("1 - Crear Jugador");
            System.out.println("2 - Crear Jugador Maquina");
            System.out.println("3 - Crear Jugador Persona");
            System.out.println("4 - Crear varias maquinas(6) y jugador ");
            System.out.println("5 - Guardar Partida (fichero) ");
            System.out.println("6 - Cargar Partida (fichero) ");
            System.out.println("");
            int i_entrada = Integer.parseInt(scan.next());
            switch(i_entrada) {
                case -1:
                    b = false;
                    break;
                case 1:
                    test_crear_jugador(2);
                    break;
                case 2:
                    test_crear_jugadorMaquina(0,1);
                    break;
                case 3:
                    test_crear_jugadorPersona(6,"aaaa");
                    break;
                case 4:
                    test_varias_maquinas();
                    break;
                case 5:
                    test_guardar_partida();
                    break;
                case 6:
                    test_cargar_partida();
                    break;
                default:
            }
            System.out.println();
        }
    }
}
