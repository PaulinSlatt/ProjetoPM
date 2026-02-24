package com.dp.pm.pm.config.exception;

/** Exceção lançada quando uma operação de remoção/consulta espera que um recurso exista e não o encontra. */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

