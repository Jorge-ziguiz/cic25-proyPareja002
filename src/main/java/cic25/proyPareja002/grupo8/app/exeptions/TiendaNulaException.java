package cic25.proyPareja002.grupo8.app.exeptions;

public class TiendaNulaException extends RuntimeException{
    public TiendaNulaException(){
        super();
    }

    public TiendaNulaException(String mensaje){
        super(mensaje);
    }

    public TiendaNulaException(String mensaje, Throwable error){
        super(mensaje, error);
    }
}
