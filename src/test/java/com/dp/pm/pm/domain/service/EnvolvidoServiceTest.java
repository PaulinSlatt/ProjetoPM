package com.dp.pm.pm.domain.service;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import com.dp.pm.pm.domain.dto.EnvolvidoCreateDto;
import com.dp.pm.pm.domain.dto.EnvolvidoUpdateDto;
import com.dp.pm.pm.port.output.EnvolvidoDbAdapterOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnvolvidoServiceTest {

    private EnvolvidoDbAdapterOutputPort outputPort;
    private EnvolvidoService service;

    @BeforeEach
    void setUp() {
        outputPort = mock(EnvolvidoDbAdapterOutputPort.class);
        service = new EnvolvidoService(outputPort);
    }

    @Test
    void criarEnvolvido_delegaSave() {
        EnvolvidoCreateDto dto = new EnvolvidoCreateDto("Nome", null);
        Envolvido e = new Envolvido();
        e.setId(1L);
        e.setNome("Nome");
        when(outputPort.save(dto)).thenReturn(e);

        Envolvido created = service.criarEnvolvido(dto);
        assertEquals("Nome", created.getNome());
        verify(outputPort).save(dto);
    }

    @Test
    void listarEnvolvidos_semFiltro_chamaFindAll() {
        when(outputPort.findAll()).thenReturn(List.of(new Envolvido()));
        List<Envolvido> list = service.listarEnvolvidos(Optional.empty());
        assertFalse(list.isEmpty());
        verify(outputPort).findAll();
    }

    @Test
    void buscarPorId_delegaFindById() {
        Envolvido e = new Envolvido();
        e.setId(2L);
        when(outputPort.findById(2L)).thenReturn(Optional.of(e));
        Optional<Envolvido> res = service.buscarPorId(2L);
        assertTrue(res.isPresent());
    }

    @Test
    void atualizar_delegaUpdate() {
        EnvolvidoUpdateDto dto = new EnvolvidoUpdateDto(2L, "Novo", null);
        Envolvido e = new Envolvido();
        e.setId(2L);
        e.setNome("Novo");
        when(outputPort.update(dto)).thenReturn(e);
        Envolvido updated = service.atualizarEnvolvido(dto);
        assertEquals("Novo", updated.getNome());
    }

    @Test
    void deletar_chamaDelete() {
        doNothing().when(outputPort).deleteById(9L);
        service.deletarEnvolvido(9L);
        verify(outputPort).deleteById(9L);
    }
}

