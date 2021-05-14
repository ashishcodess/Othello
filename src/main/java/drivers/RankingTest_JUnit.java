package drivers;

import Dominio.ElementoRanking;
import Dominio.Ranking;
import MyException.MyException;

import org.junit.Test;
import static org.junit.Assert.*;



/*Clase para Test con JUnit sobre Clase Ranking*/
public class RankingTest_JUnit {

    /**
     * Test sobre ElementoRanking (crear objeto 1)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    @Test
    public void test_crearElementoRanking_1() throws MyException {
        ElementoRanking e = new ElementoRanking(6,"aa",2,2,3);
        String s = e.consultar_todo();
        assertEquals("6 aa 2 2 3 7",s);
    }

    /**
     * Test sobre ElementoRanking (crear objeto 2)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    @Test
    public void test_crearElementoRanking_2() throws MyException {
        ElementoRanking e = new ElementoRanking(7,"ba");
        String s = e.consultar_todo();
        assertEquals("7 ba 0 0 0 0",s);
    }

    /**
     * Test sobre ElementoRanking (incrementar contador partidas ganadas)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    @Test
    public void test_incrementarGanadas() throws MyException {
        ElementoRanking e = new ElementoRanking(7,"ba");
        e.incrementar_partida_ganada();
        String s = e.consultar_todo();
        assertEquals("7 ba 1 0 0 1",s);
    }

    /**
     * Test sobre ElementoRanking (incrementar contador partidas perdidas)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    @Test
    public void test_incrementarPerdidas() throws MyException {
        ElementoRanking e = new ElementoRanking(7,"ba");
        e.incrementar_partida_perdida();
        String s = e.consultar_todo();
        assertEquals("7 ba 0 1 0 1",s);
    }

    /**
     * Test sobre ElementoRanking (incrementar contador partidas empatadas)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    @Test
    public void test_incrementarEmpatadas() throws MyException {
        ElementoRanking e = new ElementoRanking(7,"ba");
        e.incrementar_partida_empatada();
        String s = e.consultar_todo();
        assertEquals("7 ba 0 0 1 1",s);
    }


    //Tests sobre Ranking

    @Test
    /**
     * Test sobre Ranking (Agregar elemento al Ranking)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    public void test_agregarAlRanking() throws MyException {
        ElementoRanking e = new ElementoRanking(9,"aa",2,2,3);
        Ranking rank = new Ranking();
        rank.add_al_ranking(e);
        rank.incrementar_partida(9,"aa",1);
        String s = rank.consultar_info_elemento_i(0);
        assertEquals("9 aa 3 2 3 8",s);
    }

    @Test
    /**
     * Test sobre Ranking (Funcion incrementar_ganadas_perdidas, en caso de no existir en el ranking los crea y los agrega)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    public void test_incrementar_ganadas_perdidas() throws MyException {
        Ranking rank = new Ranking();
        rank.incrementar_ganadas_perdidas(6, "a", 7, "b", 1);
        String[] resultados = {"6 a 1 0 0 1", "7 b 0 1 0 1"};
        for (int i = 0; i < 2; ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }

    @Test
    /**
     * Test sobre Ranking (Funcion existe en Ranking)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    public void test_existeEnRanking() throws MyException {
        Ranking rank = new Ranking();
        rank.add_al_ranking(new ElementoRanking(6, "aa"));
        boolean res = (rank.existe_en_ranking(6, "aa") != -1);
        assertTrue(res);
    }

    @Test
    /**
     * Test sobre Ranking (Funcion eliminar del ranking)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    public void test_eliminarDelRanking() throws MyException {
        Ranking rank = new Ranking();
        rank.incrementar_ganadas_perdidas(6, "a", 7, "b", 1);
        rank.add_al_ranking(new ElementoRanking(10, "aa"));
        rank.eliminar_elemento_ranking(6, "a");
        String[] resultados = {"7 b 0 1 0 1", "10 aa 0 0 0 0"};
        for (int i = 0; i < rank.consultar_tam_ranking(); ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }


    @Test
    /**
     * Test sobre Ranking (Funcion ordenar ranking por partidas Ganadas)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    public void test_ordenarGanadas() throws MyException{
        Ranking rank = test_Ranking();
        rank.ordenar_ranking(0);
        String[] resultados = {"7 b 2 1 0 3","6 a 1 0 0 1","8 c 0 1 0 1"};
        for (int i = 0; i < rank.consultar_tam_ranking(); ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }

    @Test
    /**
     * Test sobre Ranking (Funcion ordenar ranking por ID de usuario)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    public void test_ordenarID() throws MyException{
        Ranking rank = test_Ranking();
        rank.ordenar_ranking(1);
        String[] resultados = {"8 c 0 1 0 1", "7 b 2 1 0 3","6 a 1 0 0 1"};
        for (int i = 0; i < rank.consultar_tam_ranking(); ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }

    private Ranking test_Ranking() throws MyException {
        Ranking rank = new Ranking();
        rank.add_al_ranking(new ElementoRanking(7, "b", 2,1,0));
        rank.add_al_ranking(new ElementoRanking(8, "c", 0,1,0));
        rank.add_al_ranking(new ElementoRanking(6, "a", 1,0,0));
        return rank;
    }

    @Test
    /**
     * Test sobre Ranking (Funcion ordenar ranking por Nickname de Usuario)
     * @throws MyException caso de fallo de elementoRanking con id inferior a 6 (tiene que ser una Persona)
     * */
    public void test_ordenarNickname() throws MyException{
        Ranking rank = test_Ranking();
        rank.ordenar_ranking(2);
        String[] resultados = {"6 a 1 0 0 1","7 b 2 1 0 3", "8 c 0 1 0 1"};
        for (int i = 0; i < rank.consultar_tam_ranking(); ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }

}
