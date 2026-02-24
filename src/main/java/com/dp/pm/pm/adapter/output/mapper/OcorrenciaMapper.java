package com.dp.pm.pm.adapter.output.mapper;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.adapter.output.repository.EnvolvidoRepository;
import com.dp.pm.pm.adapter.output.repository.OcorrenciaRepository;
import com.dp.pm.pm.adapter.output.repository.PolicialRepository;
import com.dp.pm.pm.domain.dto.OcorrenciaCreateDto;
import com.dp.pm.pm.domain.dto.OcorrenciaUpdateDto;
import com.dp.pm.pm.domain.enums.Status;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OcorrenciaMapper {

    private final PolicialRepository policialRepository;
    private final EnvolvidoRepository envolvidoRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public OcorrenciaMapper(PolicialRepository policialRepository,
                            EnvolvidoRepository envolvidoRepository,
                            OcorrenciaRepository ocorrenciaRepository) {
        this.policialRepository = policialRepository;
        this.envolvidoRepository = envolvidoRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public Ocorrencia toEntity(OcorrenciaCreateDto dto) {
        Ocorrencia o = new Ocorrencia();
        o.setTipoCrime(dto.getTipoCrime());
        o.setDescricao(dto.getDescricao());
        o.setDataHora(dto.getDataHora());
        o.setLocal(dto.getLocal());
        o.setStatus(dto.getStatus() == null ? Status.ABERTA : dto.getStatus());

        if (dto.getPolicialResponsavelId() != null) {
            policialRepository.findById(dto.getPolicialResponsavelId()).ifPresent(o::setPolicialResponsavel);
        }

        if (dto.getEnvolvidosIds() != null && !dto.getEnvolvidosIds().isEmpty()) {
            Set<Envolvido> envolvidos = dto.getEnvolvidosIds().stream()
                    .map(id -> envolvidoRepository.findById(id).orElse(null))
                    .filter(e -> e != null)
                    .collect(Collectors.toCollection(HashSet::new));
            o.setEnvolvidos(envolvidos);
        }

        return o;
    }

    public Ocorrencia updateFromDto(OcorrenciaUpdateDto dto) {
        Optional<Ocorrencia> existing = ocorrenciaRepository.findById(dto.getId());
        Ocorrencia o = existing.orElseGet(Ocorrencia::new);

        if (dto.getTipoCrime() != null) o.setTipoCrime(dto.getTipoCrime());
        if (dto.getDescricao() != null) o.setDescricao(dto.getDescricao());
        if (dto.getDataHora() != null) o.setDataHora(dto.getDataHora());
        if (dto.getLocal() != null) o.setLocal(dto.getLocal());
        if (dto.getStatus() != null) o.setStatus(dto.getStatus());

        if (dto.getPolicialResponsavelId() != null) {
            policialRepository.findById(dto.getPolicialResponsavelId()).ifPresent(o::setPolicialResponsavel);
        }

        if (dto.getEnvolvidosIds() != null) {
            Set<Envolvido> envolvidos = dto.getEnvolvidosIds().stream()
                    .map(id -> envolvidoRepository.findById(id).orElse(null))
                    .filter(e -> e != null)
                    .collect(Collectors.toCollection(HashSet::new));
            o.setEnvolvidos(envolvidos);
        }

        return o;
    }
}

