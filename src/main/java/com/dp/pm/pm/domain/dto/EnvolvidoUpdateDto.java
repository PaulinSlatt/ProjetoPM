package com.dp.pm.pm.domain.dto;

import com.dp.pm.pm.domain.enums.EnvolvidoTipo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvolvidoUpdateDto {
    @NotNull
    @Positive
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotNull
    private EnvolvidoTipo tipo;
}
