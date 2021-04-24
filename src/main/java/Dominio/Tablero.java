package Dominio;

import java.util.*;

public class Tablero {

    private Casilla[][] tablero;
    private int[][] graph_h;
    private int[][] graph_v;
    private int[][] graph_d;
    private int num_vacia;
    private Set<Position> negras;
    private Set<Position> blancas;
    private Set<Position> disponibles;

    //constructor
    public Tablero(){
        tablero = new Casilla[8][8];
        graph_h = new int[8][8];
        graph_v = new int[8][8];
        graph_d = new int[8][8];
        num_vacia = 60;
        negras = new HashSet<Position>();
        blancas = new HashSet<Position>();
        disponibles= new HashSet<Position>();
    }

    //Jugador sends the tablero to load in case of game resumes from the earlier saved state.

    //Sergio: necesito constructora de la clase a partir de una matriz de enteros :)
    public Tablero(int[][] tab) {
        tablero = new Casilla[8][8];
        negras = new HashSet<Position>();
        blancas = new HashSet<Position>();
        disponibles= new HashSet<Position>();
        num_vacia = 60;
        Position pos;
        for(int i = 0 ; i < 8 ; ++i){
            for(int j = 0 ; j < 8 ; ++j){
                tablero[i][j] = new Casilla(tab[i][j]);  // put the exact value in exact position

                if(tab[i][j]== 2 ) {   // If they are black increase the number of black tokens and decrease the empty ones.
                    num_vacia --;
                    negras.add(new Position(i , j));
                }
                else if (tab[i][j]== 3 ) { // If they are white increase the number of white tokens and decrease the empty ones.
                    num_vacia --;
                    blancas.add(new Position(i , j));
                }
                else{  // If they are available ones increase the number of availables and decrease the empty ones.
                    num_vacia --;
                    //disponibles.add(new Position(i , j));
                }
            }
        }
    }

    //Jugador sends the tablero to load in case of game resumes from the earlier saved state.
    public Tablero(Casilla[][] tab) {
        tablero = new Casilla[8][8];
        negras = new HashSet<Position>();
        blancas = new HashSet<Position>();
        disponibles= new HashSet<Position>();
        num_vacia = 60;
        Position pos;
        for(int i = 0 ; i < 8 ; ++i){
            for(int j = 0 ; j < 8 ; ++j){
                tablero[i][j] = tab[i][j];  // put the exact value in exact position

                if(tab[i][j].getTipoCasilla() == 2 ) {   // If they are black increase the number of black tokens and decrease the empty ones.
                    num_vacia --;
                    negras.add(new Position(i , j));
                }
                else if (tab[i][j].getTipoCasilla() == 3 ) { // If they are white increase the number of white tokens and decrease the empty ones.
                    num_vacia --;
                    blancas.add(new Position(i , j));
                }
                else{  // If they are available ones increase the number of availables and decrease the empty ones.
                    num_vacia --;
                    disponibles.add(new Position(i , j));
                }
            }
        }
    }

    // Returns the tablero in case the other classes want the current state of tablero.
    public Casilla[][] getTablero() {

        return tablero;
    }

    //Returns if the casilla in the pos x , y is vacía, disponible, negra, blanca.
    public Casilla getCasilla(int x, int y){

        return tablero[x][y];
    }

    public int getCasilla_tipo(int x, int y){

        return tablero[x][y].getTipoCasilla();
    }

    public void setCasilla_tipo(int x, int y, int tipo){

        tablero[x][y].cambiar_tipo(tipo);
    }

    public int getNumCasillasBlancas(){

        return blancas.size();
    }

    public int getNumCasillasNegras(){

        return negras.size();
    }

    //To calculate the Casillas that are available to be put the tokens ; mark these casillas as 2 i.e disponible.
    public void bfs_calcularCasillasDisponiblesHorizontal(Position pos) {
        int row = 8, columns = 8;
        Queue<Position> q = new LinkedList<>();
        q.add(pos);
        while (!q.isEmpty()) {
            Position current_pos = q.element();
            int x = current_pos.getX();
            int y = current_pos.getY();
            q.remove();
                if (graph_v[x ][y+1] == 0 && graph_v[x][y] == 3) { // the next is the vacio and the current pos is opposite color to me then disponible.
                    graph_v[x ][y+1] = 1;
                    graph_v[x][y] = -1 ; // this one is already trevessed.
                }
                if (graph_v[x][y-1] == 0 && graph_v[x][y] == 3) {
                    graph_v[x ][y-1] = 1;
                    graph_v[x][y] = -1 ; // this one is already trevessed.
                }
                if(graph_v[x][y] != -1 && graph_v[x ][y+1] == 2); q.add(new Position(x , y+1)); // same color
                if(graph_v[x][y] != -1 && graph_v[x ][y+1] == 3); q.add(new Position(x , y+1)); // different color
                if(graph_v[x][y] != -1 && graph_v[x ][y-1] == 2); q.add(new Position(x , y-1)); // same color
                if(graph_v[x][y] != -1 && graph_v[x ][y-1] == 3); q.add(new Position(x , y-1)); // different color


        }

        }
    public void bfs_calcularCasillasDisponiblesVertical(Position pos){
        int row = 8, columns = 8;
        Queue<Position> q = new LinkedList<>();
        q.add(pos);
        while (!q.isEmpty()) {
            Position current_pos = q.element();
            int x = current_pos.getX();
            int y = current_pos.getY();
            q.remove();
            if (graph_v[x][y+1] == 0 && graph_v[x][y] == 3) { // the next is the vacio and the current pos is opposite color to me then disponible.
                graph_v[x + 1][y] = 1;
                graph_v[x][y] = -1 ; // this one is already trevessed.
            }
            if (graph_v[x ][y+1] == 0 && graph_v[x][y] == 3) {
                graph_v[x + 1][y] = 1;
                graph_v[x][y] = -1 ; // this one is already trevessed.
            }
            if(graph_v[x][y] != -1 && graph_v[x + 1][y] == 2); q.add(new Position(x+1 , y)); // same color
            if(graph_v[x][y] != -1 && graph_v[x + 1][y] == 3); q.add(new Position(x+1 , y)); // different color
            if(graph_v[x][y] != -1 && graph_v[x -1 ][y] == 2); q.add(new Position(x-1 , y)); // same color
            if(graph_v[x][y] != -1 && graph_v[x -1][y] == 3); q.add(new Position(x-1 , y)); // different color
        }
    }

    public void bfs_calcularCasillasDisponiblesDiagonales(){

    }
    public void calcularCasillasDisponiblesVertical() {
        int turno = 0; //sergio, temporal para que pueda compilar :)
        if (turno % 2 == 0) {
            Iterator iter = negras.iterator();
            while (iter.hasNext()) {
                Position pos = (Position) iter.next();
                int x = pos.getX();
                int y = pos.getY();
                if (graph_v[x][y] != -1) bfs_calcularCasillasDisponiblesVertical(pos);

            }
        }
        else {
            Iterator iter = negras.iterator();
            while (iter.hasNext()) {
                Position pos = (Position) iter.next();
                int x = pos.getX();
                int y = pos.getY();
                if (graph_v[x][y] != -1) bfs_calcularCasillasDisponiblesVertical(pos);

            }
        }
    }
    public void calcularCasillasDisponiblesHorizontal(){

    }

    public void calcularCasillasDisponiblesDiagonales(){

    }
    // The jugador wants to move to this position but is it possible? We can show the position disponibles but still the jugador wants
//to move in this position then we should not allow it to happen.
    public boolean es_possible(int x, int y) {
        if (tablero[x][y].getTipoCasilla() == 2) return true;
        else return false;
    }

    public void marcar_pos(Casilla obj) {  // Cuando queremos marcar posiciones como valido, ya atravesado etc.
        //Moviendo por este ficha vamos haciendo bfs etc.
    }

    /*public void modificar_casilla(Vector<pair<int, int>>casillas_afectadas) { // casillas afectadas have to change thier color.

        for (int i = 0; i < casillas_afectadas.size(); ++i) {
            //if(the position was black change tablero[][]) to white etc.

            //cuando nos llega ids de todas las fichas afectadas vamos a hacer find de ids desde el map y si
            //estan en negras borramos de ahí y añadimos a blancas ya que fichas afectadas cambian de color.
        }
    }*/

    public void modificarCasillasVertical(int x , int y){  // debería llamar jugador.

    }

    public void modificarCasillasHorizontal(int x , int y){

    }

    public void modificarCasillasDiagonales(int x , int y){

    }

    public Set<Position> getCasillasDisponibles(){
        return disponibles;
    }
    public boolean finalizada(){
        return true; //para poderlo compilar y hacer pruebas
    }

}