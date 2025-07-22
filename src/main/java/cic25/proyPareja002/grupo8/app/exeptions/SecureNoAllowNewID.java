package cic25.proyPareja002.grupo8.app.exeptions;

public class SecureNoAllowNewID extends RuntimeException {

    public SecureNoAllowNewID(String Message, Throwable Exception) {
        super(Message, Exception);
    }

    public SecureNoAllowNewID(Throwable Exception) {
        super(Exception);
    }

    public SecureNoAllowNewID(String Message) {
        super(Message);
    }

    public SecureNoAllowNewID() {
    }

}
