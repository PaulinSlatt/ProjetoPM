package com.dp.pm.pm.port.input;

import com.dp.pm.pm.adapter.output.entity.AtualizacaoOcorrencia;
import com.dp.pm.pm.domain.dto.AtualizacaoCreateDto;
import com.dp.pm.pm.domain.dto.AtualizacaoUpdateDto;

import java.util.List;
import java.util.Optional;

public interface AtualizacaoOcorrenciaUseCase {

    AtualizacaoOcorrencia criarAtualizacao(AtualizacaoCreateDto dto);

    Optional<AtualizacaoOcorrencia> buscarPorId(Long id);

    List<AtualizacaoOcorrencia> listarPorOcorrenciaId(Long ocorrenciaId);

    AtualizacaoOcorrencia atualizarAtualizacao(AtualizacaoUpdateDto dto);

    void deletarAtualizacao(Long id);

}
