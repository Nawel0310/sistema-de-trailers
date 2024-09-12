package com.sistema.trailers.com.sistema.trailers.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;//Nos permite asegurarnos la
    // serialización de la misma clase, aunque se realicen cambios posteriores

    public FileNotFoundException(String mensaje){
        super(mensaje);
    }

    public FileNotFoundException(String mensaje,Throwable excepcion){
        super(mensaje,excepcion);
    }

}
