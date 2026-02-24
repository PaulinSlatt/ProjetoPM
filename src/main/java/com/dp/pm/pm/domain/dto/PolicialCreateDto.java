package com.dp.pm.pm.domain.dto;

import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicialCreateDto {
    @Null
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String matricula;
}
