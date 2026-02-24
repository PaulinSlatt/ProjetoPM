package com.dp.pm.pm.adapter.output.mapper;

import com.dp.pm.pm.adapter.output.entity.AtualizacaoOcorrencia;
import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.adapter.output.repository.AtualizacaoOcorrenciaRepository;
import com.dp.pm.pm.adapter.output.repository.OcorrenciaRepository;
import com.dp.pm.pm.domain.dto.AtualizacaoCreateDto;
import com.dp.pm.pm.domain.dto.AtualizacaoUpdateDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AtualizacaoMapper {

    private final AtualizacaoOcorrenciaRepository repository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public AtualizacaoMapper(AtualizacaoOcorrenciaRepository repository, OcorrenciaRepository ocorrenciaRepository) {
        this.repository = repository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public AtualizacaoOcorrencia toEntity(AtualizacaoCreateDto dto) {
        AtualizacaoOcorrencia a = new AtualizacaoOcorrencia();
        Optional<Ocorrencia> o = ocorrenciaRepository.findById(dto.getOcorrenciaId());
        o.ifPresent(a::setOcorrencia);
        a.setComentario(dto.getComentario());
        return a;
    }

    public AtualizacaoOcorrencia updateFromDto(AtualizacaoUpdateDto dto) {
        Optional<AtualizacaoOcorrencia> existing = repository.findById(dto.getId());
        AtualizacaoOcorrencia a = existing.orElseGet(AtualizacaoOcorrencia::new);
        if (dto.getOcorrenciaId() != null) {
            ocorrenciaRepository.findById(dto.getOcorrenciaId()).ifPresent(a::setOcorrencia);
        }
        if (dto.getComentario() != null) a.setComentario(dto.getComentario());
        return a;
    }
}

