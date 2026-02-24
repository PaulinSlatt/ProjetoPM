package com.dp.pm.pm.adapter.output;

import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.config.exception.ConflictException;
import com.dp.pm.pm.config.exception.DbAdapterException;
import com.dp.pm.pm.config.exception.ResourceNotFoundException;
import com.dp.pm.pm.adapter.output.repository.PolicialRepository;
import com.dp.pm.pm.domain.dto.PolicialCreateDto;
import com.dp.pm.pm.domain.dto.PolicialUpdateDto;
import com.dp.pm.pm.port.output.PolicialDbAdapterOutputPort;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PolicialDbAdapter implements PolicialDbAdapterOutputPort {

    private final PolicialRepository repository;
    private final com.dp.pm.pm.adapter.output.mapper.PolicialMapper mapper;

    public PolicialDbAdapter(PolicialRepository repository,
                             com.dp.pm.pm.adapter.output.mapper.PolicialMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Policial save(PolicialCreateDto dto) {
        try {
            Policial p = mapper.toEntity(dto);
            return repository.save(p);
        } catch (DataIntegrityViolationException ex) {
            // Tentativa de salvar com matrícula já existente
            throw new ConflictException("Já existe um policial com essa matrícula", ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao salvar Policial", ex);
        }
    }

    @Override
    public Policial update(PolicialUpdateDto dto) {
        try {
            Policial p = mapper.updateFromDto(dto);
            return repository.save(p);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("Já existe um policial com essa matrícula", ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao atualizar Policial", ex);
        }
    }

    @Override
    public Optional<Policial> findById(Long id) {
        try {
            return repository.findById(id);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao buscar Policial por id=" + id, ex);
        }
    }

    @Override
    public List<Policial> findAll() {
        try {
            return repository.findAll();
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao listar Policiais", ex);
        }
    }

    @Override
    public Optional<Policial> findByMatricula(String matricula) {
        try {
            return repository.findByMatricula(matricula);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao buscar Policial por matrícula", ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Policial não encontrado para exclusão id=" + id, ex);
        } catch (DataAccessException ex) {
            throw new DbAdapterException("Erro ao deletar Policial id=" + id, ex);
        }
    }
}
