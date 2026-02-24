package com.dp.pm.pm.adapter.output;

import com.dp.pm.pm.adapter.output.entity.AtualizacaoOcorrencia;
import com.dp.pm.pm.config.exception.BadRequestException;
import com.dp.pm.pm.config.exception.DbAdapterException;
import com.dp.pm.pm.config.exception.ResourceNotFoundException;
import com.dp.pm.pm.adapter.output.repository.AtualizacaoOcorrenciaRepository;
import com.dp.pm.pm.domain.dto.AtualizacaoCreateDto;
import com.dp.pm.pm.domain.dto.AtualizacaoUpdateDto;
import com.dp.pm.pm.port.output.AtualizacaoOcorrenciaDbAdapterOutputPort;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AtualizacaoOcorrenciaDbAdapter implements AtualizacaoOcorrenciaDbAdapterOutputPort {

    private final AtualizacaoOcorrenciaRepository repository;
    private final com.dp.pm.pm.adapter.output.mapper.AtualizacaoMapper mapper;

    public AtualizacaoOcorrenciaDbAdapter(AtualizacaoOcorrenciaRepository repository,
                                          com.dp.pm.pm.adapter.output.mapper.AtualizacaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AtualizacaoOcorrencia save(AtualizacaoCreateDto dto) {
        try {
            AtualizacaoOcorrencia a = mapper.toEntity(dto);
            return repository.save(a);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Dados inválidos ou referência inexistente ao salvar Atualização", ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao salvar AtualizacaoOcorrencia", ex);
        }
    }

    @Override
    public AtualizacaoOcorrencia update(AtualizacaoUpdateDto dto) {
        try {
            AtualizacaoOcorrencia a = mapper.updateFromDto(dto);
            return repository.save(a);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Dados inválidos ou referência inexistente ao atualizar Atualização", ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao atualizar AtualizacaoOcorrencia", ex);
        }
    }

    @Override
    public Optional<AtualizacaoOcorrencia> findById(Long id) {
        try {
            return repository.findById(id);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao buscar AtualizacaoOcorrencia por id=" + id, ex);
        }
    }

    @Override
    public List<AtualizacaoOcorrencia> findByOcorrenciaId(Long ocorrenciaId) {
        try {
            return repository.findByOcorrencia_Id(ocorrenciaId);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao buscar atualizações por ocorrenciaId", ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("AtualizacaoOcorrencia não encontrada para exclusão id=" + id, ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao deletar AtualizacaoOcorrencia id=" + id, ex);
        }
    }
}
