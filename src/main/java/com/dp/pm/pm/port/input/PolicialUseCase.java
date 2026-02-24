package com.dp.pm.pm.port.input;

import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.domain.dto.PolicialCreateDto;
import com.dp.pm.pm.domain.dto.PolicialUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PolicialUseCase {

    Policial criarPolicial(PolicialCreateDto dto);

    Optional<Policial> buscarPorId(Long id);

    List<Policial> listarPoliciais();

    Optional<Policial> buscarPorMatricula(String matricula);

    Policial atualizarPolicial(PolicialUpdateDto dto);

    void deletarPolicial(Long id);

}
