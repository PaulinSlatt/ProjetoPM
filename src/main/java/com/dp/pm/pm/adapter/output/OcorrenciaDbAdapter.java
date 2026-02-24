package com.dp.pm.pm.adapter.output;

import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.config.exception.BadRequestException;
import com.dp.pm.pm.config.exception.DbAdapterException;
import com.dp.pm.pm.config.exception.ResourceNotFoundException;
import com.dp.pm.pm.adapter.output.repository.OcorrenciaRepository;
import com.dp.pm.pm.domain.dto.OcorrenciaCreateDto;
import com.dp.pm.pm.domain.dto.OcorrenciaUpdateDto;
import com.dp.pm.pm.domain.enums.Status;
import com.dp.pm.pm.port.output.OcorrenciaDbAdapterOutputPort;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OcorrenciaDbAdapter implements OcorrenciaDbAdapterOutputPort {

    private final OcorrenciaRepository repository;
    private final com.dp.pm.pm.adapter.output.mapper.OcorrenciaMapper mapper;

    public OcorrenciaDbAdapter(OcorrenciaRepository repository,
                               com.dp.pm.pm.adapter.output.mapper.OcorrenciaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Ocorrencia save(OcorrenciaCreateDto dto) {
        try {
            Ocorrencia o = mapper.toEntity(dto);
            return repository.save(o);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Dados inválidos ou referência inexistente ao salvar Ocorrência", ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao salvar Ocorrencia", ex);
        }
    }

    @Override
    public Ocorrencia update(OcorrenciaUpdateDto dto) {
        try {
            Ocorrencia o = mapper.updateFromDto(dto);
            return repository.save(o);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Dados inválidos ou referência inexistente ao atualizar Ocorrência", ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao atualizar Ocorrencia", ex);
        }
    }

    @Override
    public Optional<Ocorrencia> findById(Long id) {
        try {
            return repository.findById(id);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao buscar Ocorrencia por id=" + id, ex);
        }
    }

    @Override
    public List<Ocorrencia> findAll() {
        try {
            return repository.findAll();
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao listar Ocorrencias", ex);
        }
    }

    @Override
    public List<Ocorrencia> findByStatus(Status status) {
        try {
            return repository.findByStatus(status);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao buscar Ocorrencias por status", ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Ocorrencia não encontrada para exclusão id=" + id, ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao deletar Ocorrencia id=" + id, ex);
        }
    }
}
