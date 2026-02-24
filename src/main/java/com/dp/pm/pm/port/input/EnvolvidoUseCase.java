package com.dp.pm.pm.port.input;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import com.dp.pm.pm.domain.dto.EnvolvidoCreateDto;
import com.dp.pm.pm.domain.dto.EnvolvidoUpdateDto;

import java.util.List;
import java.util.Optional;

public interface EnvolvidoUseCase {

    Envolvido criarEnvolvido(EnvolvidoCreateDto dto);

    Optional<Envolvido> buscarPorId(Long id);

    List<Envolvido> listarEnvolvidos(Optional<String> filtroNome);

    Envolvido atualizarEnvolvido(EnvolvidoUpdateDto dto);

    void deletarEnvolvido(Long id);

}
