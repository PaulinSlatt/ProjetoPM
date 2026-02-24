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
public class AtualizacaoUpdateDto {
    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long ocorrenciaId;

    @NotBlank
    private String comentario;
}
