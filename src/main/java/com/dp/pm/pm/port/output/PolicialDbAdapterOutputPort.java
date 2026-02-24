package com.dp.pm.pm.port.output;

import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.domain.dto.PolicialCreateDto;
import com.dp.pm.pm.domain.dto.PolicialUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PolicialDbAdapterOutputPort {

    Policial save(PolicialCreateDto dto);

    Policial update(PolicialUpdateDto dto);

    Optional<Policial> findById(Long id);

    List<Policial> findAll();

    Optional<Policial> findByMatricula(String matricula);

    void deleteById(Long id);

}
