package com.dp.pm.pm.adapter.output;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import com.dp.pm.pm.config.exception.BadRequestException;
import com.dp.pm.pm.config.exception.DbAdapterException;
import com.dp.pm.pm.config.exception.ResourceNotFoundException;
import com.dp.pm.pm.adapter.output.repository.EnvolvidoRepository;
import com.dp.pm.pm.domain.dto.EnvolvidoCreateDto;
import com.dp.pm.pm.domain.dto.EnvolvidoUpdateDto;
import com.dp.pm.pm.port.output.EnvolvidoDbAdapterOutputPort;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnvolvidoDbAdapter implements EnvolvidoDbAdapterOutputPort {

    private final EnvolvidoRepository repository;
    private final com.dp.pm.pm.adapter.output.mapper.EnvolvidoMapper mapper;

    public EnvolvidoDbAdapter(EnvolvidoRepository repository,
                              com.dp.pm.pm.adapter.output.mapper.EnvolvidoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Envolvido save(EnvolvidoCreateDto dto) {
        try {
            Envolvido e = mapper.toEntity(dto);
            return repository.save(e);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Dados inválidos ou violação de restrição ao salvar Envolvido", ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao salvar Envolvido", ex);
        }
    }

    @Override
    public Envolvido update(EnvolvidoUpdateDto dto) {
        try {
            Envolvido e = mapper.updateFromDto(dto);
            return repository.save(e);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Dados inválidos ou violação de restrição ao atualizar Envolvido", ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao atualizar Envolvido", ex);
        }
    }

    @Override
    public Optional<Envolvido> findById(Long id) {
        try {
            return repository.findById(id);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao buscar Envolvido por id=" + id, ex);
        }
    }

    @Override
    public List<Envolvido> findAll() {
        try {
            return repository.findAll();
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao listar Envolvidos", ex);
        }
    }

    @Override
    public List<Envolvido> findByNomeContainingIgnoreCase(String nome) {
        try {
            return repository.findByNomeContainingIgnoreCase(nome);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao buscar Envolvidos por nome", ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Envolvido não encontrado para exclusão id=" + id, ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao deletar Envolvido id=" + id, ex);
        }
    }
}
