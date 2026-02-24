package com.dp.pm.pm.port.output;

import com.dp.pm.pm.adapter.output.entity.AtualizacaoOcorrencia;
import com.dp.pm.pm.domain.dto.AtualizacaoCreateDto;
import com.dp.pm.pm.domain.dto.AtualizacaoUpdateDto;

import java.util.List;
import java.util.Optional;

public interface AtualizacaoOcorrenciaDbAdapterOutputPort {

    AtualizacaoOcorrencia save(AtualizacaoCreateDto dto);

    AtualizacaoOcorrencia update(AtualizacaoUpdateDto dto);

    Optional<AtualizacaoOcorrencia> findById(Long id);

    List<AtualizacaoOcorrencia> findByOcorrenciaId(Long ocorrenciaId);

    void deleteById(Long id);

}
