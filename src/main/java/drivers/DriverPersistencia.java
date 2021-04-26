package drivers;

import ControladorPersistencia.CtrlPersitencia;
import Dominio.ElementoRanking;
import Dominio.Partida;
import Dominio.Ranking;
import MyException.MyException;

import java.io.IOException;
import java.util.*;


public class DriverPersistencia {

    static CtrlPersitencia cp;

    static Scanner scan = new Scanner(System.in);

    public DriverPersistencia() {}

    /** funcion main (para poder realizar las pruebas)
     * @param args (argumentos)
     * @throws IOException heredado de el resto de funciones
     * @throws MyException heredado de el resto de funciones
     * */
    public static void main(String[] args) throws IOException, MyException {
        cp = new CtrlPersitencia(true); //activar para utilizar solo 1 fichero de ranking
        boolean b = true;
        while (b) {
            System.out.println("DriverPersistencia (OPCIONES):");
            System.out.println("0 - SALIR DEL DRIVER");
            System.out.println("1 - CtrlPartidas (Cargar/Guardar partida, toArrayList, listar_partidas_disponibles, borrar_partida)");
            System.out.println("2 - CtrlRanking (Importar/Exportar Ranking, modificando el Ranking)");
            System.out.println("3 - CtrlUsuario (Crear, modificar, borrar usuario)");
            System.out.println("4 - GetNuevoID_User");
            System.out.println("5 - GetNuevoID_Partida");
            System.out.println();
            System.out.print("Introducir opcion:");
            int i_entrada = Integer.parseInt(scan.next());
            System.out.println();
            switch(i_entrada) {
                case 0:
                    b = false;
                    break;
                case 1:
                    System.out.print("seleccionar ID partida(fichero para realizar pruebas):");
                    i_entrada = Integer.parseInt(scan.next());
                    System.out.println("Prueba cargar partida");
                    System.out.println();
                    test_IOPartidas(i_entrada);
                    break;
                case 2:
                    test_IORanking("ranking");
                    break;
                case 3:
                    System.out.print("Introducir ID de usuario a modificar:");
                    i_entrada = Integer.parseInt(scan.next());
                    System.out.println();
                    System.out.print("Introducir Nickname de usuario a modificar:");
                    String s = scan.next();
                    test_IOUsuario(i_entrada,s);
                    break;
                case 4:
                    test_prueba_getID_nuevo_user();
                    break;
                case 5:
                    test_prueba_getID_nueva_partida();
                    break;
                default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

    /** test_IOPartidas
     * @param id es el identificador de la partida para realizar las pruebas
     * @throws IOException fallo al crear fichero partida
     * @throws MyException fallo con modo partida (en este caso no es necesario)
     * */
    public static void test_IOPartidas(int id) throws IOException, MyException {
        System.out.println("Probar metodos: Cargar/Guardar partida, toArrayList, listar_partidas_disponibles, borrar_partida");
        System.out.println();
        Partida p = cp.ctrl_cargar_partida(id);
        p.get_info_partida();
        ArrayList<String> as = p.toArrayList();
        System.out.println();
        System.out.println("sizes: " + as.size() + ", correcto? " + (as.size() == 15));
        as.set(0,"4");
        as.set(1,"9 dd");
        cp.ctrl_guardar_partida(as);
        System.out.println("Se ha creado partida con ID partida: 4");
        System.out.println();
        cp.ctrl_print_partidas_disponibles(7,"as2");
        System.out.println("Prueba: borrar partida creada anteriormente ID(4)");
        if (cp.ctrl_borrar_partida(4)) System.out.println("Partida 4 borrada con exito");
        else System.out.println("Fallo al borrar Partida 4");
        System.out.println("Partida borrada: comprobar partidas disponibles de los jugadores de esa partida");
        cp.ctrl_print_partidas_disponibles(7,"as2");
        cp.ctrl_print_partidas_disponibles(9,"dd");
        System.out.println();
    }



    /** test_IORanking
     * @param rk es el nombre del fichero (sin .txt) del ranking a realizar las pruebas
     * @throws IOException fallo al crear fichero ranking
     * @throws MyException fallo con sizes de ranking
     * */
    public static void test_IORanking(String rk) throws IOException, MyException {
        String path = "./src/files/ranking/" + rk + ".txt";
        Ranking rank= cp.ctrl_importar_ranking2(path);
        System.out.println("Ranking importado correctamente");
        rank.print_ranking();
        ElementoRanking e = new ElementoRanking(8,"aaaa",4, 1,0, 5);
        rank.incrementar_ganadas_perdidas(8,"aaaa",9,"dd", 2);
        rank.add_al_ranking(e);
        e = new ElementoRanking(9,"dd",2, 1,2, 5);
        rank.add_al_ranking(e);
        rank.print_ranking();
        rank.incrementar_ganadas_perdidas(8,"aaaa",9,"dd", 1);
        ArrayList<String> ar = rank.toArrayList();
        cp.ctrl_exportar_ranking(ar);
    }

    /** test_IOUsuario
     * @param id es el identificador de usuario a realizar las pruebas
     * @param nick es el nickname de usuario a realizar las pruebas
     * @throws IOException fallo al crear fichero Usuario
     * @throws MyException fallo con identificador (id es inferior a 6: pertenece a ID maquina)
     * */
    public static void test_IOUsuario(int id, String nick) throws IOException, MyException {
        if (cp.ctrl_existe_usuario(id,nick)) System.out.println("Fichero usuario ya existe, crear uno diferente para probar...");
        else {
            if (cp.ctrl_crear_usuario(id,nick)) {
                System.out.println("fichero creado correctamente");
                System.out.println();
                System.out.println("Agregar/borrar/listar partidas de este Usuario");
                if (!cp.ctrl_agregar_partida_usuario(id,nick,17)) System.out.println("fallo al agregar partida 17");
                cp.ctrl_print_partidas_disponibles(id,nick);
                if (!cp.ctrl_agregar_partida_usuario(id,nick,14)) System.out.println("fallo al agregar partida 14");
                cp.ctrl_print_partidas_disponibles(id,nick);
                System.out.println("Partidas ficticias: 17 y 14 creadas correctamente");
                System.out.println("Prueba eliminar partida 17");
                cp.ctrl_borrar_partida_usuario(id,nick,17);
                cp.ctrl_print_partidas_disponibles(id,nick);
                System.out.println("Borrando Usuario:" + id + " ," + nick);
                if (cp.ctrl_borrar_usuario(id,nick)) System.out.println("Se ha borrado correctamente");
                else System.out.println("Error al borrar el usuario");
            }
            else System.out.println("Problema al crear fichero (ya existia previamente)");
        }
    }

    /**test_prueba_getID_nuevo_usuario*/
    public static void test_prueba_getID_nuevo_user() {
        int id1 = cp.ctrl_get_nuevo_ID_user();
        int id2 = cp.ctrl_get_nuevo_ID_user();
        int id3 = cp.ctrl_get_nuevo_ID_user();
        System.out.println("ID_usuario1: " + String.valueOf(id1));
        System.out.println("ID_usuario2: " + String.valueOf(id2));
        System.out.println("ID_usuario3: " + String.valueOf(id3));
        boolean b = (id1 != id2) && (id2 != id3) && (id1 != id3);
        if (b) System.out.println("Todo correcto (ID's diferentes)");
        else System.out.println("Error hay algun ID igual (no deberia ser asi...)");
    }

    /**test_prueba_getID_nueva_partida*/
    public static void test_prueba_getID_nueva_partida() {
        int id1 = cp.ctrl_get_nuevo_ID_Partida();
        int id2 = cp.ctrl_get_nuevo_ID_Partida();
        int id3 = cp.ctrl_get_nuevo_ID_Partida();
        System.out.println("ID_partida1: " + String.valueOf(id1));
        System.out.println("ID_partida2: " + String.valueOf(id2));
        System.out.println("ID_partida3: " + String.valueOf(id3));
        boolean b = (id1 != id2) && (id2 != id3) && (id1 != id3);
        if (b) System.out.println("Todo correcto (ID's diferentes)");
        else System.out.println("Error hay algun ID igual (no deberia ser asi...)");
    }

}


