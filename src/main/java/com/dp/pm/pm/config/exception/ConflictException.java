package com.dp.pm.pm.config.exception;

/**
 * Exceção lançada quando uma operação viola restrição de unicidade ou representa um conflito de recurso.
 */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}

