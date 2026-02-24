package com.dp.pm.pm.domain.enums;

/**
 * Enum que representa o status de uma Ocorrência no domínio.
 * Por padrão não precisa de anotações JPA. Use anotações (ex: @JsonValue) apenas se precisar customizar serialização/DB.
 */
public enum Status {
    ABERTA,
    EM_INVESTIGACAO,
    ENCERRADA
}
