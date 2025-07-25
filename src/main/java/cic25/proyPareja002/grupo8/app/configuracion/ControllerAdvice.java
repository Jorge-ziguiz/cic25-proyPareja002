package cic25.proyPareja002.grupo8.app.configuracion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cic25.proyPareja002.grupo8.app.exeptions.CreacionSecurityException;
import cic25.proyPareja002.grupo8.app.exeptions.ModificacionSecurityException;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ModificacionSecurityException.class)
    public void controlModificacion(){
        
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CreacionSecurityException.class)
    public void controlCreacion(){
        
    }
}
