package cic25.proyPareja002.grupo8.app.exeptions;

public class MesaggeException {

    private String Message;

    public MesaggeException(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
