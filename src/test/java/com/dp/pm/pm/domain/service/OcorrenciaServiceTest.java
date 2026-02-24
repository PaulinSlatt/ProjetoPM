package com.dp.pm.pm.domain.service;

import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.domain.dto.OcorrenciaCreateDto;
import com.dp.pm.pm.domain.dto.OcorrenciaUpdateDto;
import com.dp.pm.pm.domain.enums.Status;
import com.dp.pm.pm.port.output.OcorrenciaDbAdapterOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OcorrenciaServiceTest {

    private OcorrenciaDbAdapterOutputPort outputPort;
    private OcorrenciaService service;

    @BeforeEach
    void setUp() {
        outputPort = mock(OcorrenciaDbAdapterOutputPort.class);
        service = new OcorrenciaService(outputPort);
    }

    @Test
    void criar_delegaSave() {
        OcorrenciaCreateDto dto = new OcorrenciaCreateDto();
        Ocorrencia o = new Ocorrencia();
        o.setId(1L);
        when(outputPort.save(dto)).thenReturn(o);
        Ocorrencia created = service.criarOcorrencia(dto);
        assertEquals(1L, created.getId());
        verify(outputPort).save(dto);
    }

    @Test
    void listarComStatus_chamaFindByStatus() {
        when(outputPort.findByStatus(Status.ABERTA)).thenReturn(List.of(new Ocorrencia()));
        List<Ocorrencia> list = service.listarOcorrencias(Optional.of(Status.ABERTA));
        assertFalse(list.isEmpty());
        verify(outputPort).findByStatus(Status.ABERTA);
    }

    @Test
    void listarSemStatus_chamaFindAll() {
        when(outputPort.findAll()).thenReturn(List.of(new Ocorrencia()));
        List<Ocorrencia> list = service.listarOcorrencias(Optional.empty());
        assertFalse(list.isEmpty());
        verify(outputPort).findAll();
    }

    @Test
    void atualizar_delegaUpdate() {
        OcorrenciaUpdateDto dto = new OcorrenciaUpdateDto();
        Ocorrencia o = new Ocorrencia();
        o.setId(2L);
        when(outputPort.update(dto)).thenReturn(o);
        Ocorrencia updated = service.atualizarOcorrencia(dto);
        assertEquals(2L, updated.getId());
    }

    @Test
    void deletar_chamaDelete() {
        doNothing().when(outputPort).deleteById(7L);
        service.deletarOcorrencia(7L);
        verify(outputPort).deleteById(7L);
    }
}

