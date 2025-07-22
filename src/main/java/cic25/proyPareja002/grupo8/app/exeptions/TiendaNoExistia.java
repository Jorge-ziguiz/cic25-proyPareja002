package cic25.proyPareja002.grupo8.app.exeptions;

public class TiendaNoExistia extends RuntimeException {
    public TiendaNoExistia() {
    }

    public TiendaNoExistia(String mensaje) {
        super(mensaje);
    }

    public TiendaNoExistia(String mensaje, Throwable error) {
        super(mensaje, error);
    }
}
