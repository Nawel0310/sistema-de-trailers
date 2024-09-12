package com.sistema.trailers.com.sistema.trailers.excepciones;

public class ImagenNoGuardadaException extends RuntimeException{
    public ImagenNoGuardadaException(String message) {
        super(message);
    }

    public ImagenNoGuardadaException(String message, Throwable cause) {
        super(message, cause);
    }
}
