package Dominio.Partida;

import java.util.*;



public class Tablero {

    /*Atributos*/
    /**matrix de casilla*/
    private final Casilla[][] tablero;
    /**matrix para hacer bfs horizontal*/
    private final int[][] graph_h;
    private int[][] graph;
    /**matrix para hacer bfs vertical*/
    private final int[][] graph_v;
    /**matrix para hacer bfs diagonal right */
    private final int[][] graph_dr;
    /**matrix para hacer bfs diagonal left*/
    private final int[][] graph_dl;
    /**numero de casilla vacias*/
    private int num_vacia;
    /**set que contiene fichas negras*/
    private final Set<Position> negras;
    /**set que contiene fichas blancas*/
    private final Set<Position> blancas;
    /**set que contiene fichas disponibles*/
    private final Set<Position> disponibles;
    /** Parámetro necesario para saber si la partida ha finalizado*/
    private boolean disponibles_anterior = true;

    /*Constructora*/
    /**
     * Constructora por defecto (vacia) de Tablero
     * */
    public Tablero(){
        negras = new HashSet<Position>();
        blancas = new HashSet<Position>();
        disponibles= new HashSet<Position>();
        tablero = new Casilla[8][8];    //Problema es que todos serán null.
        inicializeTablero(tablero);
        graph_h = new int[8][8];
        graph_v = new int[8][8];
        graph_dr = new int[8][8];
        graph_dl = new int[8][8];
        num_vacia = 60;

    }

    /**
     * Constructora Tablero (cargando un tablero pasado por parametro)
     * */
    public Tablero(int[][] tab) {
        negras = new HashSet<Position>();
        blancas = new HashSet<Position>();
        disponibles= new HashSet<Position>();
        tablero = new Casilla[8][8];
        graph_h = new int[8][8];
        graph_v = new int[8][8];
        graph_dr = new int[8][8];
        graph_dl = new int[8][8];
        num_vacia = 60;
        Position pos;
        for(int i = 0 ; i < 8 ; ++i){
            for(int j = 0 ; j < 8 ; ++j){
                tablero[i][j] = new Casilla(tab[i][j]);  // put the exact value in exact position
                if(tab[i][j] == 2 ) {   // If they are black increase the number of black tokens and decrease the empty ones.
                    num_vacia --;
                    negras.add(new Position(i , j));
                }
                else if (tab[i][j] == 3) { // If they are white increase the number of white tokens and decrease the empty ones.
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

    /**
     * Constructora Tablero (para caragar de estado ya guardado )
     * */
    public Tablero(Casilla[][] tab) {
        negras = new HashSet<Position>();
        blancas = new HashSet<Position>();
        disponibles= new HashSet<Position>();
        tablero = new Casilla[8][8];
        graph_h = new int[8][8];
        graph_v = new int[8][8];
        graph_dr = new int[8][8];
        graph_dl = new int[8][8];
        num_vacia = 60;
        Position pos;
        for(int i = 0 ; i < 8 ; ++i){
            for(int j = 0 ; j < 8 ; ++j){
                tablero[i][j] = new Casilla(tab[i][j].getTipoCasilla());   // put the exact value in exact position
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
    /**
     * Inicializar tablero con dos negras y dos blancas ya colocadas
     * */
    public void inicializeTablero(Casilla[][] tablero){
        for(int i = 0 ; i < 8 ; ++i){
            for (int j = 0 ; j <8 ; ++j){
                tablero[i][j] = new Casilla();
                if ((i == 3 && j==3) || (i == 4 && j==4)) {
                    tablero[i][j] = new Casilla(3);
                    blancas.add(new Position(i , j));
                }
                else if ((i == 3 && j==4) || (i == 4 && j==3)) {
                    tablero[i][j] = new Casilla(2);
                    negras.add(new Position(i , j));
                }
            }
        }
    }
    /*Sets y Gets*/
    /**
     * Operacion get del atributo tablero
     */
    public Casilla[][] getTablero() {
        return tablero;
    }


    /**
     * Operacion que deveulve la casilla
     * @param x  la fila del tablero
     * @param y la columna del tablero
     */
    public Casilla getCasilla(int x, int y){
        return tablero[x][y];
    }

    /**
     * Operacion que dice si la pos es valido
     * @param x  la fila del tablero
     * @param y la columna del tablero
     */
    public boolean isValido(int x, int y){
        if (tablero[x][y].getTipoCasilla() == 1) return true;
        return false;
    }

    /**
     * Operacion que deveulve el tipo de casilla (0 - vacía, 1 - disponible, 2 - negra, 3 - blanca)
     * @param x  la fila del tablero
     * @param y la columna del tablero
     */
    public int getCasilla_tipo(int x, int y){
        return tablero[x][y].getTipoCasilla();
    }

    /**
     * Operacion set del atributo tipo
     * @param tipo indica el nuevo valor que tomara el atributo tipo de una casilla que esta en pos x , y
     * @param x  la fila del tablero
     * @param y la columna del tablero
     */
    public void setCasilla_tipo(int x, int y, int tipo){
        tablero[x][y].cambiar_tipo(tipo);
        if(tipo == 1)  disponibles.add(new Position(x , y));
        else if(tipo == 2)negras.add(new Position(x , y));
        else if (tipo == 3)blancas.add(new Position(x , y));
    }

    /**
     * Operacion get que devuelva total numero de fichas blancas en el momento
     */
    public int getNumCasillasBlancas(){
        return blancas.size();
    }

    /**
     * Operacion get que devuelva total numero de fichas negras en el momento
     */
    public int getNumCasillasNegras(){
        return negras.size();
    }

    public int getNumCasillasDisponibles(){
        return disponibles.size();
    }
    /**
     * Operacion get que devuelva si la posicion esta dentro de la posicion valida de un matriz
     * @param x  la fila del matriz
     * @param y la columna del matriz
     */
    public boolean isOk(int x , int y){
        if (x < 0 || x > 7 || y < 0 || y > 7) return false;
        return true;
    }
    /**
     * Funcion que hace bfs horizontalmente para saber culaes son las casillas disponibles o validas(disponible = 1) para colocar fichas.
     * @param pos la posicion de una ficha de donde empezamos a hacer bfs
     * @param color_own el color de una ficha que llama el funcion(2 = negras , 3 = blancas)
     * @param color_opp el color de una ficha contrario(2 = negras , 3 = blancas)
     */
    public void bfsCalcularCasillasDisponiblesVertical(Position pos , int color_own ,int color_opp) {
        int row = 8, columns = 8;
        Queue<Position> q = new LinkedList<>();
        q.add(pos);
        while (!q.isEmpty()) {
            Position current_pos = q.element();
            int x = current_pos.getX();
            int y = current_pos.getY();
            q.remove();
            //if (isOk(x+1 , y) && graph_v[x+1][y] == 0 && graph_v[x][y] == color_opp) {
            if (isOk(x+1 , y)) {
                if (graph_v[x+1][y] == 0 && graph_v[x][y] == color_opp) { // the next is the vacio and the current pos is opposite color to me then disponible.
                    graph_v[x+1][y] = 1;
                    tablero[x+1][y] = new Casilla(1);
                    disponibles.add(new Position(x+1 , y));
                    graph_v[x][y] = -1 ; // this one is already traversed.
                }
            }

            //if (isOk(x-1 , y) &&graph_v[x-1][y] == 0 && graph_v[x][y] == color_opp) {
            if (isOk(x-1 , y)) {
                if (graph_v[x-1][y] == 0 && graph_v[x][y] == color_opp) {
                    graph_v[x -1][y] = 1;
                    tablero[x-1][y] = new Casilla(1);
                    disponibles.add(new Position(x-1 , y));
                    graph_v[x][y] = -1 ; // this one is already traversed.
                }
            }
            graph_v[x][y] = -1;
            //if(isOk(x+1, y)  &&  ((graph_v[x+1][y] == color_own) || (graph_v[x+1][y] == color_opp))) q.add(new Position(x+1 , y)); // same or different color
            if(isOk(x+1, y)) {
                if ((graph_v[x+1][y] == color_own) || (graph_v[x+1][y] == color_opp)) q.add(new Position(x+1 , y)); // same or different color
            }
            //if(isOk(x-1, y) &&  ((graph_v[x-1][y] == color_own) || (graph_v[x-1 ][y] == color_opp)))q.add(new Position(x-1 , y)); // same color
            if(isOk(x-1, y)) {
                if ((graph_v[x-1][y] == color_own) || (graph_v[x-1 ][y] == color_opp)) q.add(new Position(x-1 , y)); // same color
            }

        }

    }

    /**
     * Funcion que hace bfs verticalmente para saber culaes son las casillas disponibles o validas(disponible = 1) para colocar fichas.
     * @param pos la posicion de una ficha de donde empezamos a hacer bfs
     * @param color_own el color de una ficha que llama el funcion(2 = negras , 3 = blancas)
     * @param color_opp el color de una ficha contrario(2 = negras , 3 = blancas)
     */

    public void bfsCalcularCasillasDisponiblesHorizontal(Position pos , int color_own ,int color_opp){
        int row = 8, columns = 8;
        Queue<Position> q = new LinkedList<>();
        q.add(pos);
        while (!q.isEmpty()) {
            Position current_pos = q.element();
            int x = current_pos.getX();
            int y = current_pos.getY();
            q.remove();
            //if (isOk(x , y+1) && graph_h[x ][y+1] == 0 && graph_h[x][y] == color_opp) { // the next is the vacio and the current pos is opposite color to me then disponible.
            if (isOk(x , y+1)) {
                if (graph_h[x ][y+1] == 0 && graph_h[x][y] == color_opp) {
                    graph_h[x ][y+1] = 1;
                    tablero[x][y+1] = new Casilla(1);
                    disponibles.add(new Position(x , y+1));
                    graph_h[x][y] = -1 ; // this one is already traversed.
                }
            }
            //if (isOk(x , y-1) && graph_h[x][y-1] == 0 && graph_h[x][y] == color_opp) {
            if (isOk(x , y-1)) {
                if (graph_h[x][y-1] == 0 && graph_h[x][y] == color_opp) {
                    graph_h[x ][y-1] = 1;
                    tablero[x][y-1] = new Casilla(1);
                    disponibles.add(new Position(x, y-1));
                    graph_h[x][y] = -1 ; // this one is already traversed.
                }
            }
            graph_h[x][y] = -1;
            //if(isOk(x , y+1) && graph_h[x][y+1] != -1 && (graph_h[x ][y+1] == color_own || graph_h[x ][y+1] == color_opp)) q.add(new Position(x , y+1)); // same or different color
            if(isOk(x , y+1)) {
                if (graph_h[x][y+1] != -1 && (graph_h[x ][y+1] == color_own || graph_h[x ][y+1] == color_opp)) q.add(new Position(x , y+1)); // same or different color
            }
            //if(isOk(x, y-1) && graph_h[x][y-1] != -1 && (graph_h[x ][y-1] == color_own || graph_h[x ][y-1] == color_opp))q.add(new Position(x , y-1)); // same color
            if(isOk(x, y-1)) {
                if (graph_h[x][y-1] != -1 && (graph_h[x ][y-1] == color_own || graph_h[x ][y-1] == color_opp)) q.add(new Position(x , y-1)); // same color
            }
        }
    }

    /**
     * Funcion que hace bfs diagonalmente (izquierda a derecha)para saber culaes son las casillas disponibles o validas(disponible = 1) para colocar fichas.
     * @param pos la posicion de una ficha de donde empezamos a hacer bfs
     * @param color_own el color de una ficha que llama el funcion(2 = negras , 3 = blancas)
     * @param color_opp el color de una ficha contrario(2 = negras , 3 = blancas)
     */
    public void bfsCalcularCasillasDisponiblesDiagonalesLeft(Position pos , int color_own ,int color_opp){
        int row = 8, columns = 8;
        Queue<Position> q = new LinkedList<>();
        q.add(pos);
        while (!q.isEmpty()) {
            Position current_pos = q.element();
            int x = current_pos.getX();
            int y = current_pos.getY();
            q.remove();
            //if (isOk(x+1 , y+1) && graph_dl[x+1][y+1] == 0 && graph_dl[x][y] == color_opp) { // the next is the vacio and the current pos is opposite color to me then disponible.
            if (isOk(x+1 , y+1)) {
                if (graph_dl[x+1][y+1] == 0 && graph_dl[x][y] == color_opp) {
                    graph_dl[x+1][y+1] = 1;
                    tablero[x+1][y+1] = new Casilla(1);
                    disponibles.add(new Position(x+1 , y+1));
                    graph_dl[x][y] = -1 ; // this one is already traversed.
                }
            }
            //if (isOk(x-1 , y-1) && graph_dl[x-1][y-1] == 0 && graph_dl[x][y] == color_opp) {
            if (isOk(x-1 , y-1)) {
                if (graph_dl[x-1][y-1] == 0 && graph_dl[x][y] == color_opp) {
                    graph_dl[x-1][y-1] = 1;
                    tablero[x-1][y-1] = new Casilla(1);
                    disponibles.add(new Position(x-1 , y-1));
                    graph_dl[x][y] = -1 ; // this one is already traversed.
                }
            }
            graph_dl[x][y] = -1;
            //if(isOk(x+1 , y+1) && graph_dl[x+1][y+1] != -1 && (graph_dl[x+1][y+1] == color_own || graph_dl[x+1][y+1] == color_opp))q.add(new Position(x+1 , y+1)); // same or different color
            if(isOk(x+1 , y+1)) {
                if (graph_dl[x+1][y+1] != -1 && (graph_dl[x+1][y+1] == color_own || graph_dl[x+1][y+1] == color_opp))q.add(new Position(x+1 , y+1)); // same or different color
            }
            //if(isOk(x-1 , y-1) && graph_dl[x-1][y-1] != -1 && (graph_dl[x-1][y-1] == color_own || graph_dl[x-1][y-1] == color_opp))q.add(new Position(x-1 , y-1));
            if(isOk(x-1 , y-1)) {
                if (graph_dl[x-1][y-1] != -1 && (graph_dl[x-1][y-1] == color_own || graph_dl[x-1][y-1] == color_opp))q.add(new Position(x-1 , y-1));
            }
        }
    }
    /**
     * Funcion que hace bfs diagonalmente (derecha a izquierda )para saber culaes son las casillas disponibles o validas(disponible = 1) para colocar fichas.
     * @param pos la posicion de una ficha de donde empezamos a hacer bfs
     * @param color_own el color de una ficha que llama el funcion(2 = negras , 3 = blancas)
     * @param color_opp el color de una ficha contrario(2 = negras , 3 = blancas)
     */
    public void bfsCalcularCasillasDisponiblesDiagonalesRight(Position pos , int color_own ,int color_opp){
        int row = 8, columns = 8;
        Queue<Position> q = new LinkedList<>();
        q.add(pos);
        while (!q.isEmpty()) {
            Position current_pos = q.element();
            int x = current_pos.getX();
            int y = current_pos.getY();
            q.remove();
            //if (isOk(x+1 , y-1) && graph_dr[x+1][y-1] == 0 && graph_dr[x][y] == color_opp) {
            if (isOk(x+1 , y-1)) {
                if (graph_dr[x+1][y-1] == 0 && graph_dr[x][y] == color_opp) {
                    graph_dr[x+1][y-1] = 1;
                    tablero[x+1][y-1] = new Casilla(1);
                    disponibles.add(new Position(x+1 , y-1));
                    graph_dr[x][y] = -1 ; // this one is already traversed.
                }
            }
            //if (isOk(x-1 , y+1) && graph_dr[x-1][y+1] == 0 && graph_dr[x][y] == color_opp) {
            if (isOk(x-1 , y+1)) {
                if (graph_dr[x-1][y+1] == 0 && graph_dr[x][y] == color_opp) {
                    graph_dr[x-1][y+1] = 1;
                    tablero[x-1][y+1] = new Casilla(1);
                    disponibles.add(new Position(x-1 , y+1));
                    graph_dr[x][y] = -1 ; // this one is already traversed.
                }
            }
            graph_dr[x][y] = -1;
            //if(isOk(x+1, y-1) && graph_dr[x+1][y-1] != -1 && (graph_dr[x+1][y-1] == color_own || graph_dr[x+1][y-1] == color_opp))q.add(new Position(x+1 , y-1)); // same color
            if(isOk(x+1, y-1)) {
                if (graph_dr[x+1][y-1] != -1 && (graph_dr[x+1][y-1] == color_own || graph_dr[x+1][y-1] == color_opp))q.add(new Position(x+1 , y-1)); // same color
            }
            //if(isOk(x-1 , y+1) && graph_dr[x-1][y+1] != -1 && (graph_dr[x-1][y+1] == color_own || graph_dr[x-1][y+1] == color_opp))q.add(new Position(x-1 , y+1));
            if(isOk(x-1 , y+1)) {
                if (graph_dr[x-1][y+1] != -1 && (graph_dr[x-1][y+1] == color_own || graph_dr[x-1][y+1] == color_opp))q.add(new Position(x-1 , y+1));
            }
        }
    }
    /**
     * Funcion para llamar bfs Vertical
     * @param turno es el turno donde estamos (turno par = negras , turno impar = blancas)
     */
    public void calcularCasillasDisponiblesVertical(int turno) {
        for(int i = 0 ; i < 8 ; ++i){
            for (int j = 0 ; j < 8 ; ++j){
                graph_v[i][j]= tablero[i][j].getTipoCasilla();
            }
        }
        Position pos;
        if (turno % 2 == 0) {
            Object[] arr = negras.toArray();
            for (int i = 0 ; i < negras.size() ; ++i){
                pos = (Position) arr[i];
                int x = pos.getX();
                int y = pos.getY();
                if (graph_v[x][y] != -1) bfsCalcularCasillasDisponiblesVertical(pos , 2,3);
            }
        }
        else {
            Object[] arr = blancas.toArray();
            for (int i = 0 ; i < blancas.size() ; ++i){
                pos = (Position) arr[i];
                int x = pos.getX();
                int y = pos.getY();
                if (graph_v[x][y] != -1) bfsCalcularCasillasDisponiblesVertical(pos , 3 , 2);
            }
        }
    }

    /**
     * Funcion para llamar bfs Horizontal
     * @param turno es el turno donde estamos (turno par = negras , turno impar = blancas)
     */
    public void calcularCasillasDisponiblesHorizontal(int turno){
        for(int i = 0 ; i < 8 ; ++i){
            for (int j = 0 ; j < 8 ; ++j){
                graph_h[i][j]= tablero[i][j].getTipoCasilla();
            }
        }
        Position pos;
        if (turno % 2 == 0) {
            Object[] arr = negras.toArray();
            for (int i = 0 ; i < negras.size() ; ++i){
                pos = (Position) arr[i];
                int x = pos.getX();
                int y = pos.getY();
                if (graph_h[x][y] != -1) bfsCalcularCasillasDisponiblesHorizontal(pos , 2 , 3);
            }
        }
        else {
            Object[] arr = blancas.toArray();
            for (int i = 0 ; i < blancas.size() ; ++i){
                pos = (Position) arr[i];
                int x = pos.getX();
                int y = pos.getY();
                if (graph_h[x][y] != -1) bfsCalcularCasillasDisponiblesHorizontal(pos , 3 , 2);
            }
        }
    }
    /**
     * Funcion para llamar bfs Diagonal
     * @param turno es el turno donde estamos (turno par = negras , turno impar = blancas)
     */
    public void calcularCasillasDisponiblesDiagonales(int turno){
        for(int i = 0 ; i < 8 ; ++i){
            for (int j = 0 ; j < 8 ; ++j){
                graph_dl[i][j]= tablero[i][j].getTipoCasilla();
                graph_dr[i][j]= tablero[i][j].getTipoCasilla();
            }
        }
        Position pos;
        if (turno % 2 == 0) {
            Object[] arr = negras.toArray();
            for (int i = 0 ; i < negras.size() ; ++i){
                pos = (Position) arr[i];
                int x = pos.getX();
                int y = pos.getY();
                if (graph_dl[x][y] != -1) bfsCalcularCasillasDisponiblesDiagonalesLeft(pos , 2, 3 );
                if (graph_dr[x][y] != -1) bfsCalcularCasillasDisponiblesDiagonalesRight(pos , 2, 3 );
            }
        }
        else {
            Object[] arr = blancas.toArray();
            for (int i = 0 ; i < blancas.size() ; ++i){
                pos = (Position) arr[i];
                int x = pos.getX();
                int y = pos.getY();
                if (graph_dl[x][y] != -1) bfsCalcularCasillasDisponiblesDiagonalesLeft(pos , 3, 2 );
                if (graph_dr[x][y] != -1) bfsCalcularCasillasDisponiblesDiagonalesRight(pos , 3, 2);
            }
        }
    }
    /**
     * Funcion para llamar saber si es posible colocar ficha en posicion x , y.
     * @param x  la fila del tablero
     * @param y la columna del tablero
     */
    public boolean es_possible(int x, int y) {
        if (tablero[x][y].getTipoCasilla() == 1) return true;
        else return false;
    }
    /**
     * Funcion para modificar casillas verticales afectadas
     * @param turno es el turno donde estamos (turno par = negras , turno impar = blancas)
     * @param x la fila del tablero de donde queremos empezar a modificar
     * @param y la columna del tablero de donde queremos empezar a modificar
     */
    public void modificarCasillasVertical(int x , int y, int turno) {
        for(int i = 0 ; i < 8 ; ++i){
            for (int j = 0 ; j < 8 ; ++j){
                graph_v[i][j]= tablero[i][j].getTipoCasilla();
            }
        }
        int own_color, opp_color;
        boolean found1 = false, found2 = false, stop1 = false, stop2 = false;
        Vector<Position> vec1 = new Vector<Position>();
        Vector<Position> vec2 = new Vector<Position>();
        if (turno % 2 == 0) { own_color = 2 ;opp_color = 3;
        }
        else { own_color = 3; opp_color = 2;
        }
        //if (isOk(x - 1, y) && tablero[x-1][y].getTipoCasilla() == opp_color) {
        if (isOk(x - 1, y)) {
            if (tablero[x-1][y].getTipoCasilla() == opp_color) {
                for (int i = x - 1; i >= 0 && !found1 && !stop1; --i) {
                    if (tablero[i][y].getTipoCasilla() == own_color) found1 = true;    //Case of finding the same colour
                    else if (tablero[i][y].getTipoCasilla() == 0 || tablero[i][y].getTipoCasilla() == 1)
                        stop1 = true;   // case of being unable to find any color
                    else {                           //Same color(i.e opposite )
                        vec1.addElement(new Position(i, y));
                    }
                }
                if (found1) {
                    Object[] arr1 = vec1.toArray();
                    int a, b;
                    for (int i = 0; i < vec1.size(); ++i) {
                        Position pos = (Position) arr1[i];
                        a = pos.getX();
                        b = pos.getY();
                        tablero[a][b].cambiar_tipo(own_color);    // In tablero keep the own color
                    }
                }
            }
        }
        //if (isOk(x + 1, y) && tablero[x+1][y].getTipoCasilla() == opp_color) {
        if (isOk(x + 1, y)) {
            if (tablero[x+1][y].getTipoCasilla() == opp_color) {
                for (int i = x + 1; i <= 7 && !found2 && !stop2; ++i) {
                    if (tablero[i][y].getTipoCasilla() == own_color) found2 = true;    //Case of finding the same colour
                    else if (tablero[i][y].getTipoCasilla() == 0 || tablero[i][y].getTipoCasilla() == 1) stop2 = true;   // case of being unable to find any color
                    else {                           //Same color(i.e opposite )
                        Position ps = new Position(i, y);
                        vec2.addElement(ps);
                    }
                }
                if (found2) {
                    Object[] arr2 = vec2.toArray();
                    int a, b;
                    for (int i = 0; i < vec2.size(); ++i) {
                        Position pos = (Position) arr2[i];
                        a = pos.getX();
                        b = pos.getY();
                        tablero[a][b].cambiar_tipo(own_color);    // In tablero keep the own color
                    }
                }
            }
        }
    }
    /**
     * Funcion para modificar casillas horizontales afectadas
     * @param turno es el turno donde estamos (turno par = negras , turno impar = blancas)
     * @param x la fila del tablero de donde queremos empezar a modificar
     * @param y la columna del tablero de donde queremos empezar a modificar
     */
    public void modificarCasillasHorizontal(int x , int y , int turno){
        int own_color, opp_color;
        boolean found1 = false, found2 = false, stop1 = false, stop2 = false;
        Vector<Position> vec1 = new Vector<Position>();
        Vector<Position> vec2 = new Vector<Position>();
        if (turno % 2 == 0) { own_color = 2 ;opp_color = 3;
        }
        else { own_color = 3; opp_color = 2;
        }
        //if (isOk(x, y-1) && tablero[x][y-1].getTipoCasilla() == opp_color) {
        if (isOk(x, y-1)) {
            if (tablero[x][y-1].getTipoCasilla() == opp_color) {
                for (int i = y - 1; i >= 0 && !found1 && !stop1 ; --i) {
                    if (tablero[x][i].getTipoCasilla() == own_color) found1 = true;    //Case of finding the same colour
                    else if (tablero[x][i].getTipoCasilla() == 0 || tablero[x][i].getTipoCasilla() == 1)
                        stop1 = true;   // case of being unable to find any color
                    else {                           //Same color(i.e opposite )
                        vec1.addElement(new Position(x, i));
                    }
                }
                if (found1) {
                    Object[] arr1 = vec1.toArray();
                    int a, b;
                    for (int i = 0; i < vec1.size(); ++i) {
                        Position pos = (Position) arr1[i];
                        a = pos.getX();
                        b = pos.getY();
                        tablero[a][b].cambiar_tipo(own_color);    // In tablero keep the own color
                    }
                }
            }
        }
        //if (isOk(x , y+1) && tablero[x][y+1].getTipoCasilla() == opp_color) {
        if (isOk(x , y+1)) {
            if (tablero[x][y+1].getTipoCasilla() == opp_color) {
                for (int i = y + 1; i <= 7 && !found2 && !stop2; ++i) {
                    if (tablero[x][i].getTipoCasilla() == own_color) found2 = true;    //Case of finding the same colour
                    else if (tablero[x][i].getTipoCasilla() == 0 || tablero[x][i].getTipoCasilla() == 1) stop2 = true;   // case of being unable to find any color
                    else {//Same color(i.e opposite )
                        Position ps = new Position(x, i);
                        vec2.addElement(ps);

                    }
                }
                if (found2) {
                    Object[] arr2 = vec2.toArray();
                    int a, b;
                    for (int i = 0; i < vec2.size(); ++i) {
                        Position pos = (Position) arr2[i];
                        a = pos.getX();
                        b = pos.getY();
                        tablero[a][b].cambiar_tipo(own_color);    // In tablero keep the own color
                    }
                }
            }
        }
    }
    /**
     * Funcion para modificar casillas diagonales afectadas
     * @param turno es el turno donde estamos (turno par = negras , turno impar = blancas)
     * @param x la fila del tablero de donde queremos empezar a modificar
     * @param y la columna del tablero de donde queremos empezar a modificar
     */
    public void modificarCasillasDiagonales(int x , int y , int turno){
        int own_color, opp_color;
        boolean found1 = false, found2 = false, found3 = false, found4 = false,  stop1 = false, stop2 = false , stop3 = false, stop4 = false;
        Vector<Position> vec1 = new Vector<Position>();
        Vector<Position> vec2 = new Vector<Position>();
        Vector<Position> vec3 = new Vector<Position>();
        Vector<Position> vec4 = new Vector<Position>();
        if (turno % 2 == 0) { own_color = 2 ;opp_color = 3;
        }
        else { own_color = 3; opp_color = 2;
        }
        //if (isOk(x-1, y-1) && tablero[x-1][y-1].getTipoCasilla() == opp_color) {
        if (isOk(x-1, y-1)) {
            if (tablero[x-1][y-1].getTipoCasilla() == opp_color) {
                int i = x-1 , j = y-1 ;
                while ( i >= 0 && j >= 0 && !found1 && !stop1) {
                    if (tablero[i][j].getTipoCasilla() == own_color) found1 = true;    //Case of finding the same colour
                    else if (tablero[i][j].getTipoCasilla() == 0 || tablero[i][j].getTipoCasilla() == 1)
                        stop1 = true;   // case of being unable to find any color
                    else {                           //Same color(i.e opposite )
                        vec1.addElement(new Position(i, j));
                    }
                    --i; --j;
                    if (i<0 || j < 0) stop1 = true; //pos si acaso (evitar fallos)
                }
                if (found1) {
                    Object[] arr1 = vec1.toArray();
                    int a, b;
                    for (int k = 0; k < vec1.size(); ++k) {
                        Position pos = (Position) arr1[k];
                        a = pos.getX();
                        b = pos.getY();
                        tablero[a][b].cambiar_tipo(own_color);    // In tablero keep the own color
                   /* if(own_color == 2) {
                        negras.add(pos);
                        blancas.remove(pos);
                    }
                    else  if(own_color == 3){
                        negras.remove(pos);
                        blancas.add(pos);
                    }*/
                    }
                }
            }
        }
        //if (isOk(x+1, y+1) && tablero[x+1][y+1].getTipoCasilla() == opp_color) {
        if (isOk(x+1, y+1)) {
            if (tablero[x+1][y+1].getTipoCasilla() == opp_color) {
                int i = x+1 , j = y+1 ;
                while ( i <= 7 && j <= 7 && !found2 && !stop2) {
                    if (tablero[i][j].getTipoCasilla() == own_color) found2 = true;    //Case of finding the same colour
                    else if (tablero[i][j].getTipoCasilla() == 0 || tablero[i][j].getTipoCasilla() == 1)
                        stop2 = true;   // case of being unable to find any color
                    else {                           //Same color(i.e opposite )
                        vec2.addElement(new Position(i, j));
                    }
                    ++i; ++j;
                }
                if (found2) {
                    Object[] arr2 = vec2.toArray();
                    int a, b;
                    for (int k = 0; k < vec2.size(); ++k) {
                        Position pos = (Position) arr2[k];
                        a = pos.getX();
                        b = pos.getY();
                        tablero[a][b].cambiar_tipo(own_color);    // In tablero keep the own color
                    }
                }
            }
        }
        //if (isOk(x-1, y+1) && tablero[x-1][y+1].getTipoCasilla() == opp_color) {
        if (isOk(x-1, y+1)) {
            if (tablero[x-1][y+1].getTipoCasilla() == opp_color) {
                int i = x-1 , j = y+1 ;
                while ( i >= 0 && j <= 7 && !found3 && !stop3) {
                    if (tablero[i][j].getTipoCasilla() == own_color) found3 = true;    //Case of finding the same colour
                    else if (tablero[i][j].getTipoCasilla() == 0 || tablero[i][j].getTipoCasilla() == 1)
                        stop3 = true;   // case of being unable to find any color
                    else {                           //Same color(i.e opposite )
                        vec3.addElement(new Position(i, j));
                    }
                    --i; ++j;
                }
                if (found3) {
                    Object[] arr3 = vec3.toArray();
                    int a, b;
                    for (int k = 0; k < vec3.size(); ++k) {
                        Position pos = (Position) arr3[k];
                        a = pos.getX();
                        b = pos.getY();
                        tablero[a][b].cambiar_tipo(own_color);    // In tablero keep the own color
                    }
                }
            }
        }

        //if (isOk(x+1, y-1) && tablero[x+1][y-1].getTipoCasilla() == opp_color) {
        if (isOk(x+1, y-1)) {
            if (tablero[x+1][y-1].getTipoCasilla() == opp_color) {
                int i = x+1 , j = y-1 ;
                while ( i <= 7 && j >= 0 && !found4 && !stop4) {
                    if (tablero[i][j].getTipoCasilla() == own_color) found4 = true;    //Case of finding the same colour
                    else if (tablero[i][j].getTipoCasilla() == 0 || tablero[i][j].getTipoCasilla() == 1)
                        stop4 = true;   // case of being unable to find any color
                    else {                           //Same color(i.e opposite )
                        vec4.addElement(new Position(i, j));
                    }
                    ++i; --j;
                }
                if (found4) {
                    Object[] arr4 = vec4.toArray();
                    int a, b;
                    for (int k = 0; k < vec4.size(); ++k) {
                        Position pos = (Position) arr4[k];
                        a = pos.getX();
                        b = pos.getY();
                        tablero[a][b].cambiar_tipo(own_color);    // In tablero keep the own color
                    }
                }
            }
        }
    }
    /**
     * Funcion encarada de modificar todas las fichas afectadas por un movimiento
     * @param turno es el turno donde estamos (turno par = negras , turno impar = blancas)
     * @param x la fila del tablero de donde queremos colocar la ficha
     * @param y la columna del tablero de donde queremos colocar la ficha
     */
    public void actualizarTablero(int x , int y , int turno){
        Position[] arr =  new Position[disponibles.size()];
        disponibles.toArray(arr);
        disponibles.clear();
        modificarCasillasHorizontal(x , y , turno);
        modificarCasillasVertical(x , y , turno);
        modificarCasillasDiagonales(x , y , turno);

        int a, b;

        for (int i = 0; i < arr.length; ++i) {
            Position pos = (Position) arr[i];
            a = pos.getX();
            b = pos.getY();
            tablero[a][b].cambiar_tipo(0);  // cambiar todos los disponibles a vacio.
        }
        int color ;
        if (turno %2 == 0) {color = 2;  negras.add(new Position(x , y));}
        else { color = 3; blancas.add(new Position(x , y));}
        tablero[x][y].cambiar_tipo(color);
        arr =  new Position[0];
        negras.clear();
        blancas.clear();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (tablero[i][j].getTipoCasilla() == 2 )negras.add(new Position(i , j));
                else if  (tablero[i][j].getTipoCasilla() == 3 )blancas.add(new Position(i , j));
            }
        }
    }
    /**
     * Operaciones get de casillas disponibles
     */
    public Set<Position> getCasillasDisponibles(){
        // Object[] arr = disponibles.toArray();
        return disponibles;
    }
    /**
     * Operaciones get de casillas Negras
     */
    public Set<Position> getCasillasNegras(){
        // Object[] arr = disponibles.toArray();
        return negras;
    }
    /**
     * Operaciones get de casillas blancas
     */
    public Set<Position> getCasillasBlancas(){
        // Object[] arr = disponibles.toArray();
        return blancas;
    }

    public boolean finalizada(){

        return (this.num_vacia == 0 || this.blancas.size() == 0 ||
                this.negras.size() == 0 || (this.disponibles.size() == 0 && !this.disponibles_anterior));
    }

    public boolean getDisponiblesAnterior(){return this.disponibles_anterior;}

    public void SetDisponiblesAnterior(boolean b){
        this.disponibles_anterior = b;
    }

    /**
     * Operacion toMatrix
     * @return devuelve la informacion del tablero en una matriz de enteros
     */
    //Para Controlador de Persitencia
    public int[][] toMatrix() {
        int[][] res = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0;j < 8; ++j) {
                res[i][j]= getCasilla_tipo(i,j);
            }
        }
        return res;
    }

    /**
     * Método de la máquina para poder evaluar la calidad de cada estado,
     * en caso de que la máquina este jugando con las fichas blancas
     * @return retorna la diferencia entre el número de fichas blancas y el número de fichas negras
     */
    public int getHeuristicValueBlancas(){
        return this.blancas.size()-this.negras.size();
    }

    /**
     * Método de la máquina para poder evaluar la calidad de cada estado,
     * en caso de que la máquina este jugando con las fichas negras
     * @return retorna la diferencia entre el número de fichas negras y el número de fichas blancas
     */
    public int getHeuristicValueNegras(){
        return this.negras.size()-this.blancas.size();
    }

}