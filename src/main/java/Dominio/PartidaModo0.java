package Dominio;

//Clase de Partida MODO 0 (Maquina vs Maquina)
public class PartidaModo0 extends Partida {

    /*Atributos*/
    private JugadorMaquina j1;
    private JugadorMaquina j2;


    public PartidaModo0(int id, int modoJuego, int[] r, int turn, int idj1, int idj2) {
        super(id,modoJuego,r,turn,idj1,idj2);
        this.j1 = new JugadorMaquina(idj1);
        this.j2 = new JugadorMaquina(idj2);
    }

    public PartidaModo0(int id, int modoJuego, int[] r, int turn, int idj1, String n1, int idj2, String n2, Tablero t) {
        super(id,modoJuego,r,turn,idj1,n1,idj2,n2,t);
        this.j1 = new JugadorMaquina(idj1);
        this.j2 = new JugadorMaquina(idj2);
    }

    //Similar rondaPartida del padre pero con llamada de colocar ficha y comportamiento de IA's
    @Override
    public int rondaPartida(String[] accion) {
        int res = -1;
        return res; //Sergio:para que compile correctamente
    }




    //guarda toda la info en un ArrayList de String para tratamiento con CtrlPersitencia
    /*public ArrayList<String> toArrayList0() {
        ArrayList<String> as = new ArrayList<String>();
        as.add(String.valueOf(this.id));
        String s =super.idJugador1 + " " + this.nick1;
        as.add(s);
        s =this.idJugador2 + " " + this.nick2;
        as.add(s);
        s = String.valueOf(this.modoDeJuego);
        as.add(s);
        s = (this.reglas[0] + " " + this.reglas[1] + " " +this.reglas[2]);
        as.add(s);
        s = String.valueOf(this.turno);
        as.add(s);
        as.add("");
        //parte del tablero
        for (int i = 0; i < 8; ++i) {
            String sbuff = new String();
            for (int j = 0; j < 8; ++j) {
                sbuff = sbuff + this.tablero.getCasilla_tipo(i,j);
            }
            as.add(sbuff);
        }
        return as;
    }*/
}

