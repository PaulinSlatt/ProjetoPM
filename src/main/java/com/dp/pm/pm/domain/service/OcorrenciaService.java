package com.dp.pm.pm.domain.service;

import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.domain.dto.OcorrenciaCreateDto;
import com.dp.pm.pm.domain.dto.OcorrenciaUpdateDto;
import com.dp.pm.pm.domain.enums.Status;
import com.dp.pm.pm.port.input.OcorrenciaUseCase;
import com.dp.pm.pm.port.output.OcorrenciaDbAdapterOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaService implements OcorrenciaUseCase {

    private final OcorrenciaDbAdapterOutputPort ocorrenciaPort;

    public OcorrenciaService(OcorrenciaDbAdapterOutputPort ocorrenciaPort) {
        this.ocorrenciaPort = ocorrenciaPort;
    }

    @Override
    public Ocorrencia criarOcorrencia(OcorrenciaCreateDto dto) {
        return ocorrenciaPort.save(dto);
    }

    @Override
    public Optional<Ocorrencia> buscarPorId(Long id) {
        return ocorrenciaPort.findById(id);
    }

    @Override
    public List<Ocorrencia> listarOcorrencias(Optional<Status> status) {
        if (status != null && status.isPresent()) {
            return ocorrenciaPort.findByStatus(status.get());
        }
        return ocorrenciaPort.findAll();
    }

    @Override
    public Ocorrencia atualizarOcorrencia(OcorrenciaUpdateDto dto) {
        return ocorrenciaPort.update(dto);
    }

    @Override
    public void deletarOcorrencia(Long id) {
        ocorrenciaPort.deleteById(id);
    }
}
