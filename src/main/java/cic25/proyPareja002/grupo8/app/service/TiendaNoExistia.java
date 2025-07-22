package cic25.proyPareja002.grupo8.app.service;

public class TiendaNoExistia extends RuntimeException {
    TiendaNoExistia() {
    }

    TiendaNoExistia(String mensaje) {
        super(mensaje);
    }

    TiendaNoExistia(String mensaje, Throwable error) {
        super(mensaje, error);
    }
}
