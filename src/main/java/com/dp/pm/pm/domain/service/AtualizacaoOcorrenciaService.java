package com.dp.pm.pm.domain.service;

import com.dp.pm.pm.adapter.output.entity.AtualizacaoOcorrencia;
import com.dp.pm.pm.domain.dto.AtualizacaoCreateDto;
import com.dp.pm.pm.domain.dto.AtualizacaoUpdateDto;
import com.dp.pm.pm.port.input.AtualizacaoOcorrenciaUseCase;
import com.dp.pm.pm.port.output.AtualizacaoOcorrenciaDbAdapterOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtualizacaoOcorrenciaService implements AtualizacaoOcorrenciaUseCase {

    private final AtualizacaoOcorrenciaDbAdapterOutputPort outputPort;

    public AtualizacaoOcorrenciaService(AtualizacaoOcorrenciaDbAdapterOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public AtualizacaoOcorrencia criarAtualizacao(AtualizacaoCreateDto dto) {
        return outputPort.save(dto);
    }

    @Override
    public Optional<AtualizacaoOcorrencia> buscarPorId(Long id) {
        return outputPort.findById(id);
    }

    @Override
    public List<AtualizacaoOcorrencia> listarPorOcorrenciaId(Long ocorrenciaId) {
        return outputPort.findByOcorrenciaId(ocorrenciaId);
    }

    @Override
    public AtualizacaoOcorrencia atualizarAtualizacao(AtualizacaoUpdateDto dto) {
        return outputPort.update(dto);
    }

    @Override
    public void deletarAtualizacao(Long id) {
        outputPort.deleteById(id);
    }
}
