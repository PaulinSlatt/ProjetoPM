package com.dp.pm.pm.config.exception;

/**
 * Exceção lançada quando a requisição é inválida (por exemplo, referência a FK inexistente ou dados malformados).
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

