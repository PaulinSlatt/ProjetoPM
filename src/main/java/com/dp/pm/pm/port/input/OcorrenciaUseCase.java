package com.dp.pm.pm.port.input;

import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.domain.dto.OcorrenciaCreateDto;
import com.dp.pm.pm.domain.dto.OcorrenciaUpdateDto;
import com.dp.pm.pm.domain.enums.Status;

import java.util.List;
import java.util.Optional;

/**
 * Porta de entrada (use case) para operações de Ocorrência.
 * Define casos de uso que os serviços (application layer) expõem.
 */
public interface OcorrenciaUseCase {

    /** Cria uma nova ocorrência a partir do DTO de criação. */
    Ocorrencia criarOcorrencia(OcorrenciaCreateDto dto);

    /** Busca uma ocorrência por id. */
    Optional<Ocorrencia> buscarPorId(Long id);

    /** Lista todas ocorrências, com opção de filtrar por status. */
    List<Ocorrencia> listarOcorrencias(Optional<Status> status);

    /** Atualiza parcialmente uma ocorrência a partir do DTO de atualização. */
    Ocorrencia atualizarOcorrencia(OcorrenciaUpdateDto dto);

    /** Remove uma ocorrência por id. */
    void deletarOcorrencia(Long id);

}
