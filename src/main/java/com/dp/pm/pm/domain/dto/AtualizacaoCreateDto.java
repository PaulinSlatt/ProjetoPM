package com.dp.pm.pm.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoCreateDto {
    @NotNull(message = "O campo 'ocorrenciaId' é obrigatório.")
    @Positive(message = "O campo 'ocorrenciaId' deve ser um número positivo.")
    private Long ocorrenciaId;

    @NotBlank(message = "O campo 'comentario' é obrigatório.")
    private String comentario;
}
