package cic25.proyPareja002.grupo8.app.exeptions;

public class ModificacionSecurityException extends RuntimeException{
   
    public ModificacionSecurityException() {
        super("Intento de modificación en el create");
    }

    public ModificacionSecurityException(String mensaje) {
        super(mensaje);
    }

    public ModificacionSecurityException (String mensaje, Throwable throwable) {
        super(mensaje, throwable);
    }
}
