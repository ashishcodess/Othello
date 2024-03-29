package drivers;

import Dominio.Jugador.JugadorMaquina;
import Dominio.Partida.Tablero;
import MyException.MyException;

import java.util.Scanner;

public class DriverJugadorMaquina {

    static Scanner scan = new Scanner(System.in);

    /**
     * funcion main (para poder realizar las pruebas)
     * @param  args (argumentos)
     @throws MyException heredado de el resto de funciones
      * */
    public static void main(String[] args) throws Exception {
        boolean b = true;
        while (b) {
            System.out.println("DriverJugador (OPCIONES):");
            System.out.println("0 - SALIR DEL DRIVER");
            System.out.println("1 - Crear Jugador Maquina");
            System.out.println("2 - Ejecutar Turno");
            System.out.println();
            System.out.print("Introducir opcion:");
            int i_entrada = Integer.parseInt(scan.next());
            System.out.println();
            Tablero t = new Tablero();

            t.calcularCasillasDisponiblesDiagonales(0);
            t.calcularCasillasDisponiblesHorizontal(0);
            t.calcularCasillasDisponiblesVertical(0);
            switch(i_entrada) {
                case 0:
                    b = false;
                    break;
                case 1:
                    test_crear_jugadorMaquina(0);
                    break;
                case 2:
                    JugadorMaquina prueba = new JugadorMaquina(2);
                    System.out.println(prueba.get_profundidadMaquina());
                    for (int i = 0; i < 8; ++i) {
                        String sbuff = new String();
                        for (int j = 0; j < 8; ++j) {
                            sbuff = sbuff + t.getCasilla_tipo(i,j);
                        }
                        System.out.println(sbuff);
                    }
                    int[] reglas = {1,1,1};
                    System.out.println("");
                    t = prueba.valorMaxNegras(t,0, -1000, 1000, prueba.get_profundidadMaquina(), reglas);
                    System.out.println("///////////////////Despues de MINIMAX////////////////////////");
                    System.out.println("");
                    for (int i = 0; i < 8; ++i) {
                        StringBuilder sbuff = new StringBuilder();
                        for (int j = 0; j < 8; ++j) {
                            sbuff.append(t.getTablero()[i][j].getTipoCasilla());
                        }
                        System.out.println(sbuff);
                    }
                    System.out.println("Valor =" + t.getHeuristicValueNegras());
                    break;
                default:
                    System.out.println("Introducir una opcion correcta");
            }
            System.out.println();
        }
    }

    /** test_crear_jugadorMaquina (clase hijo)
     * @param idMaquina es el identificador de la Maquina a crear
     * @throws MyException en caso de fallo con identificadores (id pertenece a Persona)
     * */
    public static void test_crear_jugadorMaquina(int idMaquina) throws Exception{
        JugadorMaquina res = new JugadorMaquina(idMaquina);
        System.out.println("Maquina creada con ID:" + idMaquina);
        System.out.println("Prueba get_MaquinaID : " + res.get_MaquinaID());
        System.out.println("Prueba get_Profundidad : " + res.get_profundidadMaquina());
    }
}
