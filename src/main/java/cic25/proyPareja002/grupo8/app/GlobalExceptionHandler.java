package cic25.proyPareja002.grupo8.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cic25.proyPareja002.grupo8.app.service.TiendaNoExistia;
import cic25.proyPareja002.grupo8.app.service.TiendaNulaException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TiendaNoExistia.class)
    public ResponseEntity<String> handleTiendaNoExistiaException(TiendaNoExistia e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Error: " + e.getMessage());
    }

    @ExceptionHandler(TiendaNulaException.class)
    public ResponseEntity<String> handleTiendaNulaException(TiendaNulaException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Error: " + e.getMessage());
    }
}
