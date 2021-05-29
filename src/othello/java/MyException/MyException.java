package MyException;

/**Clase para generar Excepciones personalizadas*/
public class MyException extends Exception
{
    /**Tipos de excepcion a generar: ID negativo, ID pertenece a maquina, ID Pertenece a Persona, Modo de juego incorrecto*/
    public enum tipoExcepcion {ID_NEGATIVO, ID_MAQUINA, ID_PERSONA, MODO_INCORRECTO}

    /**Creadora de la clase MyException
     * @param message es el mensaje a informar sobre la excepcion ocurrida*/
    public MyException(String message)
    {
        super(message);
    }

    /**Creadora de la clase MyException
     * @param a es el tipo de Excepcion a generar
     * @param t es el numero entero para generar el mensaje (puede ser un ID o el modo de juego)
     * @throws Exception excepcion personalizada a partir del tipoExcepcion*/
    public MyException(tipoExcepcion a, int t) throws Exception {
        String message = "";
        switch (a) {
            case ID_NEGATIVO:
                message = ("Error: El ID:" + t + " es negativo");
                break;

            case ID_MAQUINA:
                message = ("Error: El ID:" + t + " pertenece a una maquina [rango 0..5]");
                break;

            case ID_PERSONA:
                message = ("Error: El ID:" + t + " pertenece a una Persona [id > 5]");
                break;

            case MODO_INCORRECTO:
                message = ("El modo:" + t + " es incorrecto, seleccionar [0: maquina vs maquina, 1: Persona vs maquina, 2:Persona vs Persona]");
                break;
        }
        throw new Exception(message);
    }
}



