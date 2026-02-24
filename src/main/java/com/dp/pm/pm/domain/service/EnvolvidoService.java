package com.dp.pm.pm.domain.service;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import com.dp.pm.pm.domain.dto.EnvolvidoCreateDto;
import com.dp.pm.pm.domain.dto.EnvolvidoUpdateDto;
import com.dp.pm.pm.port.input.EnvolvidoUseCase;
import com.dp.pm.pm.port.output.EnvolvidoDbAdapterOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvolvidoService implements EnvolvidoUseCase {

    private final EnvolvidoDbAdapterOutputPort outputPort;

    public EnvolvidoService(EnvolvidoDbAdapterOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public Envolvido criarEnvolvido(EnvolvidoCreateDto dto) {
        return outputPort.save(dto);
    }

    @Override
    public Optional<Envolvido> buscarPorId(Long id) {
        return outputPort.findById(id);
    }

    @Override
    public List<Envolvido> listarEnvolvidos(Optional<String> filtroNome) {
        if (filtroNome != null && filtroNome.isPresent()) {
            return outputPort.findByNomeContainingIgnoreCase(filtroNome.get());
        }
        return outputPort.findAll();
    }

    @Override
    public Envolvido atualizarEnvolvido(EnvolvidoUpdateDto dto) {
        return outputPort.update(dto);
    }

    @Override
    public void deletarEnvolvido(Long id) {
        outputPort.deleteById(id);
    }
}
