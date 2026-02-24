package com.dp.pm.pm.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicialUpdateDto {
    @NotNull
    @Positive
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String matricula;
}
