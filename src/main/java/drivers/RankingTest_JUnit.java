package drivers;

import Dominio.ElementoRanking;
import Dominio.Ranking;
import MyException.MyException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class RankingTest_JUnit {

    public RankingTest_JUnit() {}

    ElementoRanking e1;
    ElementoRanking e2;
    Ranking rank;
    Ranking rank2;

    @Before
    public void inicializarElementoRanking() throws MyException {
        e1 = new ElementoRanking(6,"aa",2,2,3);
        e2 = new ElementoRanking(7,"ba");
        rank = new Ranking();
        rank2 = new Ranking();
        rank2.add_al_ranking(new ElementoRanking(7, "b", 2,1,0));
        rank2.add_al_ranking(new ElementoRanking(8, "c", 0,1,0));
        rank2.add_al_ranking(new ElementoRanking(6, "a", 1,0,0));
    }

    @Test
    public void test_creaElementoRanking_y_consulta_elemento() {
        String s = e1.consultar_todo();
        assertEquals("6 aa 2 2 3 7",s);
    }


    @Test
    public void test_crearElementoRanking_conCreadora2_contadores_zero() {
        String s = e2.consultar_todo();
        assertEquals("7 ba 0 0 0 0",s);
    }


    @Test
    public void test_incrementarGanadas() {
        e2.incrementar_partida_ganada();
        String s = e2.consultar_todo();
        assertEquals("7 ba 1 0 0 1",s);
    }


    @Test
    public void test_incrementarPerdidas() {
        e2.incrementar_partida_perdida();
        String s = e2.consultar_todo();
        assertEquals("7 ba 0 1 0 1",s);
    }


    @Test
    public void test_incrementarEmpatadas() throws MyException {
        ElementoRanking e = new ElementoRanking(7,"ba");
        e.incrementar_partida_empatada();
        String s = e.consultar_todo();
        assertEquals("7 ba 0 0 1 1",s);
    }


    //Tests sobre Ranking



    @Test
    public void test_agregarAlRanking_e_incrementar_partida() throws MyException {
        rank.add_al_ranking(e1);
        rank.incrementar_partida(6,"aa",1);
        String s = rank.consultar_info_elemento_i(0);
        assertEquals("6 aa 3 2 3 8",s);
    }



    @Test
    public void test_incrementar_ganadas_perdidas() throws MyException {
        rank.incrementar_ganadas_perdidas(6, "a", 7, "b", 1);
        String[] resultados = {"6 a 1 0 0 1", "7 b 0 1 0 1"};
        for (int i = 0; i < 2; ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }


    @Test
    public void test_existeEnRanking() throws MyException {
        rank.add_al_ranking(new ElementoRanking(6, "aa"));
        boolean res = (rank.existe_en_ranking(6, "aa") != -1);
        assertTrue(res);
    }



    @Test
    public void test_eliminarDelRanking() throws MyException {
        rank.incrementar_ganadas_perdidas(6, "a", 7, "b", 1);
        rank.add_al_ranking(new ElementoRanking(10, "aa"));
        rank.eliminar_elemento_ranking(6, "a");
        String[] resultados = {"7 b 0 1 0 1", "10 aa 0 0 0 0"};
        for (int i = 0; i < rank.consultar_tam_ranking(); ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }

    @Test
    public void test_ordenarGanadas() {
        rank.ordenar_ranking(0);
        String[] resultados = {"7 b 2 1 0 3","6 a 1 0 0 1","8 c 0 1 0 1"};
        for (int i = 0; i < rank.consultar_tam_ranking(); ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }


    @Test
    public void test_ordenarID() {
        rank2.ordenar_ranking(1);
        String[] resultados = {"8 c 0 1 0 1", "7 b 2 1 0 3","6 a 1 0 0 1"};
        for (int i = 0; i < rank2.consultar_tam_ranking(); ++i) {
            assertEquals(resultados[i],rank2.consultar_info_elemento_i(i));
        }
    }



    @Test
    public void test_ordenarNickname() {
        rank2.ordenar_ranking(2);
        String[] resultados = {"6 a 1 0 0 1","7 b 2 1 0 3", "8 c 0 1 0 1"};
        for (int i = 0; i < rank.consultar_tam_ranking(); ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }

}
