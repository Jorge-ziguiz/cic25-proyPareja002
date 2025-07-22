package cic25.proyPareja002.grupo8.app.exeptions;

public class NoAllowedDeleteIfRegisterNoExist extends RuntimeException {

    public NoAllowedDeleteIfRegisterNoExist(String Message, Throwable Exception) {
        super(Message, Exception);
    }

    public NoAllowedDeleteIfRegisterNoExist(Throwable Exception) {
        super(Exception);
    }

    public NoAllowedDeleteIfRegisterNoExist(String Message) {
        super(Message);
    }

    public NoAllowedDeleteIfRegisterNoExist() {
    }

}
