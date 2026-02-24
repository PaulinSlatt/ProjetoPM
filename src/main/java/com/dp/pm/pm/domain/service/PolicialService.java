package com.dp.pm.pm.domain.service;

import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.domain.dto.PolicialCreateDto;
import com.dp.pm.pm.domain.dto.PolicialUpdateDto;
import com.dp.pm.pm.port.input.PolicialUseCase;
import com.dp.pm.pm.port.output.PolicialDbAdapterOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicialService implements PolicialUseCase {

    private final PolicialDbAdapterOutputPort outputPort;

    public PolicialService(PolicialDbAdapterOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public Policial criarPolicial(PolicialCreateDto dto) {
        return outputPort.save(dto);
    }

    @Override
    public Optional<Policial> buscarPorId(Long id) {
        return outputPort.findById(id);
    }

    @Override
    public List<Policial> listarPoliciais() {
        return outputPort.findAll();
    }

    @Override
    public Optional<Policial> buscarPorMatricula(String matricula) {
        return outputPort.findByMatricula(matricula);
    }

    @Override
    public Policial atualizarPolicial(PolicialUpdateDto dto) {
        return outputPort.update(dto);
    }

    @Override
    public void deletarPolicial(Long id) {
        outputPort.deleteById(id);
    }
}
