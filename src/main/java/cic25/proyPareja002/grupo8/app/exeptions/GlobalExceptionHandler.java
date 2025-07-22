package cic25.proyPareja002.grupo8.app.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.nI)
    @ExceptionHandler(SecureNoAllowNewID.class)
    public void handleException() {
    }
}
