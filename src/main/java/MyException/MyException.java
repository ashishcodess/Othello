package MyException;



public class MyException extends Exception
{
    public enum tipoExcepcion {ID_NEGATIVO, ID_MAQUINA, ID_PERSONA, MODO_INCORRECTO}

    public MyException(String message)
    {
        super(message);
    }

    public MyException(tipoExcepcion a, int t) {
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
        new MyException(message);
    }
}



