package cic25.proyPareja002.grupo8.app.exeptions;

public class CreacionSecurityException extends RuntimeException{

    public CreacionSecurityException() {
        super("Intento de creación en el update");
    }

    public CreacionSecurityException(String mensaje) {
        super(mensaje);
    }

    public CreacionSecurityException (String mensaje, Throwable throwable) {
        super(mensaje, throwable);
    }
}
