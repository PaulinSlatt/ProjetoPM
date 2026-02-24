package com.dp.pm.pm.config.exception;

/** Exceção genérica lançada pelos adapters de persistência para encapsular erros de acesso a dados. */
public class DbAdapterException extends RuntimeException {
    public DbAdapterException(String message) {
        super(message);
    }

    public DbAdapterException(String message, Throwable cause) {
        super(message, cause);
    }
}

