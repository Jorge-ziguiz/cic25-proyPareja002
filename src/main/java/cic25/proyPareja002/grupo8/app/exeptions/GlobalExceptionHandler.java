package cic25.proyPareja002.grupo8.app.exeptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    ObjectMapper objectMapper;

    @ExceptionHandler(SecureNoAllowNewID.class)
    public ResponseEntity<String> handleSecureNoAllowNewID(SecureNoAllowNewID e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(generateBodyNegativeResponses(e.getMessage()));

    }

    @ExceptionHandler(TiendaNoExistia.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleTiendaNoExistiaException(TiendaNoExistia e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(generateBodyNegativeResponses(e.getMessage()));
    }

    @ExceptionHandler(TiendaNulaException.class)
    public ResponseEntity<String> handleTiendaNulaException(TiendaNulaException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(generateBodyNegativeResponses(e.getMessage()));
    }

    private String generateBodyNegativeResponses(String mesage) {
        try {
            MesaggeException mesaggeException = new MesaggeException(mesage);
            String JsonBody = objectMapper.writeValueAsString(mesaggeException);
            return JsonBody;
        } catch (Exception e) {
            return "";
        }
    }

}
