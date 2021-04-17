package Dominio;

public class Tablero {

    private Casilla[][] tablero ;
    private int num_negra , num_blanca , num_disponible , num_vacia;

    //constructor
    public Tablero(){
        tablero = new Casilla[8][8];
        num_blanca = 0;
        num_negra = 0;
        num_disponible = 0;
        num_vacia = 60;
    }

    //Jugador sends the tablero to load in case of game resumes from the earlier saved state.
    public Tablero(int[][] tab) {
        tablero = new Casilla[8][8];
        num_blanca = 0;
        num_negra = 0;
        num_disponible = 0;
        num_vacia = 60;
        for(int i = 0 ; i < 8 ; ++i){
            for(int j = 0 ; j < 8 ; ++j){
                tablero[i][j].cambiar_tipo(tab[i][j]);  // change the type to the one passed as parameter
                if(tab[i][j] == 2 ) {   // If they are black increase the number of black tokens and decrease the empty ones.
                    num_negra ++;
                    num_vacia --;
                }
                else if (tab[i][j] == 3 ) { // If they are white increase the number of white tokens and decrease the empty ones.
                    num_blanca ++ ;
                    num_vacia --;
                }
                else{  // If they are available ones increase the number of availables and decrease the empty ones.
                    num_disponible ++;
                    num_vacia --;
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
        return num_blanca;
    }

    public int getNumCasillasNegras(){
        return num_negra;
    }

    //To calculate the Casillas that are available to be put the tokens ; mark these casillas as 2 i.e disponible.
    public void calcularCasillasDisponiblesVertical(){

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

    public void modificarCasillasVertical(){

    }

    public void modificarCasillasHorizontal(){

    }

    public void modificarCasillasDiagonales(){

    }

}