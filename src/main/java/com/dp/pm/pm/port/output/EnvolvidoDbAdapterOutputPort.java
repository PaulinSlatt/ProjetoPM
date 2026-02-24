package com.dp.pm.pm.port.output;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import com.dp.pm.pm.domain.dto.EnvolvidoCreateDto;
import com.dp.pm.pm.domain.dto.EnvolvidoUpdateDto;

import java.util.List;
import java.util.Optional;

public interface EnvolvidoDbAdapterOutputPort {

    Envolvido save(EnvolvidoCreateDto dto);

    Envolvido update(EnvolvidoUpdateDto dto);

    Optional<Envolvido> findById(Long id);

    List<Envolvido> findAll();

    List<Envolvido> findByNomeContainingIgnoreCase(String nome);

    void deleteById(Long id);

}
