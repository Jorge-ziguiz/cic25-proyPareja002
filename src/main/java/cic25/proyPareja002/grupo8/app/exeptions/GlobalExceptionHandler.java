package cic25.proyPareja002.grupo8.app.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            SecureNoAllowNewID.class,
            NoAllowedDeleteIfRegisterNoExist.class
    })

    public ResponseEntity<Object> handleSecureNoAllowNewID(SecureNoAllowNewID e) {
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> handleNoAllowedDeleteIfRegisterNoExist(NoAllowedDeleteIfRegisterNoExist e) {
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
