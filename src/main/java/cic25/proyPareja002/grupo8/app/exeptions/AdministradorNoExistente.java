package cic25.proyPareja002.grupo8.app.exeptions;

public class AdministradorNoExistente extends RuntimeException {
    public AdministradorNoExistente() {
        super();
    }
        public AdministradorNoExistente(String mensaje, Throwable error) {
        super(mensaje, error);
    }
        public AdministradorNoExistente(String mensaje) {
        super(mensaje);
    }
}
