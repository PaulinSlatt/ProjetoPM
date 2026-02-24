package com.dp.pm.pm.port.output;

import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.domain.dto.OcorrenciaCreateDto;
import com.dp.pm.pm.domain.dto.OcorrenciaUpdateDto;
import com.dp.pm.pm.domain.enums.Status;

import java.util.List;
import java.util.Optional;

/**
 * Porta de saída para o adaptador de banco de dados (DB Adapter).
 * Define operações que o adapter deve implementar para persistência de Ocorrência.
 */
public interface OcorrenciaDbAdapterOutputPort {

    Ocorrencia save(OcorrenciaCreateDto dto);

    Ocorrencia update(OcorrenciaUpdateDto dto);

    Optional<Ocorrencia> findById(Long id);

    List<Ocorrencia> findAll();

    List<Ocorrencia> findByStatus(Status status);

    void deleteById(Long id);

}
