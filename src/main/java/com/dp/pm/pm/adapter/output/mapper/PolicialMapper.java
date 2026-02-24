package com.dp.pm.pm.adapter.output.mapper;

import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.adapter.output.repository.PolicialRepository;
import com.dp.pm.pm.domain.dto.PolicialCreateDto;
import com.dp.pm.pm.domain.dto.PolicialUpdateDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PolicialMapper {

    private final PolicialRepository repository;

    public PolicialMapper(PolicialRepository repository) {
        this.repository = repository;
    }

    public Policial toEntity(PolicialCreateDto dto) {
        Policial p = new Policial();
        p.setId(dto.getId());
        p.setMatricula(dto.getMatricula());
        return p;
    }

    public Policial updateFromDto(PolicialUpdateDto dto) {
        Optional<Policial> existing = repository.findById(dto.getId());
        Policial p = existing.orElseGet(Policial::new);
        if (dto.getMatricula() != null) p.setMatricula(dto.getMatricula());
        return p;
    }
}

