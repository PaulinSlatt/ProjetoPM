package com.dp.pm.pm.domain.dto;

import com.dp.pm.pm.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcorrenciaCreateDto {
    @NotBlank
    @Size(max = 50)
    private String tipoCrime;

    @NotBlank
    private String descricao;

    private LocalDateTime dataHora;

    @NotBlank
    @Size(max = 150)
    private String local;

    private Status status;

    private Long policialResponsavelId;

    @NotEmpty
    private List<@Positive Long> envolvidosIds;
}
