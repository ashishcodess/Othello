package drivers;

import Dominio.Ranking.ElementoRanking;
import Dominio.Ranking.Logros;
import Dominio.Ranking.Ranking;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/** Test Unitario utilizando JUnit de la clase de Ranking*/
public class RankingTest_JUnit {

    public RankingTest_JUnit() {}

    ElementoRanking e1;
    ElementoRanking e2;
    Ranking rank;
    Ranking rank2;

    @Before
    public void inicializarElementoRanking() throws Exception {
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
    public void test_incrementarEmpatadas() throws Exception {
        ElementoRanking e = new ElementoRanking(7,"ba");
        e.incrementar_partida_empatada();
        String s = e.consultar_todo();
        assertEquals("7 ba 0 0 1 1",s);
    }


    //Tests sobre Ranking

    @Test
    public void test_agregarAlRanking_e_incrementar_partida() throws Exception {
        rank.add_al_ranking(e1);
        rank.incrementar_partida(6,"aa", Ranking.tipoGanador.GANA);
        String s = rank.consultar_info_elemento_i(0);
        assertEquals("6 aa 3 2 3 8",s);
    }



    @Test
    public void test_incrementar_ganadas_perdidas() throws Exception {
        rank.incrementar_ganadas_perdidas(6, "a", 7, "b", Ranking.tipoGanador.GANA_J1);
        String[] resultados = {"6 a 1 0 0 1", "7 b 0 1 0 1"};
        for (int i = 0; i < 2; ++i) {
            assertEquals(resultados[i],rank.consultar_info_elemento_i(i));
        }
    }


    @Test
    public void test_existeEnRanking() throws Exception {
        rank.add_al_ranking(new ElementoRanking(6, "aa"));
        boolean res = (rank.existe_en_ranking(6, "aa") != -1);
        assertTrue(res);
    }



    @Test
    public void test_eliminarDelRanking() throws Exception {
        rank.incrementar_ganadas_perdidas(6, "a", 7, "b", Ranking.tipoGanador.GANA_J1);
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

    //Test de los logros

    @Test
    public void test_cambiar_logro_partidaMasCorta_y_consultar_logro() {
        rank2.cambiar_logro_partida(Logros.tipoLogro.PARTIDA_CORTA,"a",6,"b",7,40,0);
        String s = rank2.consultar_logro(Logros.tipoLogro.PARTIDA_CORTA);
        assertEquals("40 6 a 7 b",s);
    }

    @Test
    public void test_cambiar_logro_partidaMasDiferencia_y_consultar_logro() {
        rank2.cambiar_logro_partida(Logros.tipoLogro.FICHAS_DIFF,"a",6,"b",7,10,30);
        String s = rank2.consultar_logro(Logros.tipoLogro.FICHAS_DIFF);
        assertEquals("20 6 a 10 7 b 30",s);
    }

    @Test
    public void test_cambiar_logro_partidasTOTALES_y_consultar_logro() {
        rank2.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_TOTALES,"a",6,8);
        String s = rank2.consultar_logro(Logros.tipoLogro.PARTIDAS_TOTALES);
        assertEquals("8 6 a",s);
    }

    @Test
    public void test_cambiar_logro_partidasGanadas_y_consultar_logro() {
        rank2.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_GANADAS,"b",7,12);
        String s = rank2.consultar_logro(Logros.tipoLogro.PARTIDAS_GANADAS);
        assertEquals("12 7 b",s);
    }
    @Test
    public void test_cambiar_logro_partidasPerdidas_y_consultar_logro() {
        rank2.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_PERDIDAS,"b",7,4);
        String s = rank2.consultar_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS);
        assertEquals("4 7 b",s);
    }

    @Test
    public void test_comprobarCondicionLogro_PARTIDAS() {
        rank2.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_GANADAS,"b",7,12);
        boolean b1 = rank2.comprobar_logro(Logros.tipoLogro.PARTIDAS_GANADAS,13); //deberia dar cierto (13 > 12)
        boolean b2 = rank2.comprobar_logro(Logros.tipoLogro.PARTIDAS_GANADAS,10); //deberia dar falso (10 < 12)
        boolean res = b1 && !b2;
        assertTrue(res);

        rank2.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_TOTALES, "b", 7, 6);
        b1 = rank2.comprobar_logro(Logros.tipoLogro.PARTIDAS_TOTALES,13); //deberia dar cierto (13 > 6)
        b2 = rank2.comprobar_logro(Logros.tipoLogro.PARTIDAS_TOTALES,3); //deberia dar falso (3 < 6)
        res = b1 && !b2;
        assertTrue(res);

        rank2.cambiar_logro_jugador(Logros.tipoLogro.PARTIDAS_PERDIDAS, "b", 7, 15);
        b1 = rank2.comprobar_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS,16); //deberia dar cierto (16 > 15)
        b2 = rank2.comprobar_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS,14); //deberia dar falso (14 < 15)
        res = b1 && !b2;
        assertTrue(res);


        rank2.cambiar_logro_partida(Logros.tipoLogro.PARTIDA_CORTA,"a",6,"b",7,45,0);
        b1 = rank2.comprobar_logro(Logros.tipoLogro.PARTIDA_CORTA,23); //deberia dar cierto (23 < 45)
        b2 = rank2.comprobar_logro(Logros.tipoLogro.PARTIDA_CORTA,45); //deberia dar falso (53 > 45)
        res = b1 && !b2;
        assertTrue(res);

        rank2.cambiar_logro_partida(Logros.tipoLogro.FICHAS_DIFF,"a",6,"b",7,45,30);
        b1 = rank2.comprobar_logro(Logros.tipoLogro.FICHAS_DIFF,16); //deberia dar cierto (16 < 45-30)
        b2 = rank2.comprobar_logro(Logros.tipoLogro.FICHAS_DIFF,10); //deberia dar falso (53 > (45-30))
        res = b1 && !b2;
        assertTrue(res);

    }

    @Test
    public void test_Incrementar_partidas_Y_comprobar_logros_actualizados() throws Exception {
        rank2.incrementar_ganadas_perdidas(6,"a",7,"b", Ranking.tipoGanador.GANA_J2);
        String s = rank2.consultar_logro(Logros.tipoLogro.PARTIDAS_GANADAS);
        assertEquals("3 7 b",s);
        s = rank2.consultar_logro(Logros.tipoLogro.PARTIDAS_TOTALES);
        assertEquals("4 7 b",s);
        s = rank2.consultar_logro(Logros.tipoLogro.PARTIDAS_PERDIDAS);
        assertEquals("1 6 a",s);
    }


}
