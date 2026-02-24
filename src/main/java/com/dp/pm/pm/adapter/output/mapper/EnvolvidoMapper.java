package com.dp.pm.pm.adapter.output.mapper;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import com.dp.pm.pm.adapter.output.repository.EnvolvidoRepository;
import com.dp.pm.pm.domain.dto.EnvolvidoCreateDto;
import com.dp.pm.pm.domain.dto.EnvolvidoUpdateDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EnvolvidoMapper {

    private final EnvolvidoRepository repository;

    public EnvolvidoMapper(EnvolvidoRepository repository) {
        this.repository = repository;
    }

    public Envolvido toEntity(EnvolvidoCreateDto dto) {
        Envolvido e = new Envolvido();
        e.setNome(dto.getNome());
        e.setTipo(dto.getTipo());
        return e;
    }

    public Envolvido updateFromDto(EnvolvidoUpdateDto dto) {
        Optional<Envolvido> existing = repository.findById(dto.getId());
        Envolvido e = existing.orElseGet(Envolvido::new);
        if (dto.getNome() != null) e.setNome(dto.getNome());
        if (dto.getTipo() != null) e.setTipo(dto.getTipo());
        return e;
    }
}

