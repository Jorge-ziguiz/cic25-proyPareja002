package cic25.proyPareja002.grupo8.app.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SecureNoAllowNewID.class)
    public ResponseEntity<String> handleSecureNoAllowNewID(SecureNoAllowNewID e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("error"+e.getMessage());

    }

    @ExceptionHandler(TiendaNoExistia.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
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
