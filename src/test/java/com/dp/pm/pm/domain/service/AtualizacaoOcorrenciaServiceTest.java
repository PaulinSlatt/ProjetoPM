package com.dp.pm.pm.domain.service;

import com.dp.pm.pm.adapter.output.entity.AtualizacaoOcorrencia;
import com.dp.pm.pm.domain.dto.AtualizacaoCreateDto;
import com.dp.pm.pm.domain.dto.AtualizacaoUpdateDto;
import com.dp.pm.pm.port.output.AtualizacaoOcorrenciaDbAdapterOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizacaoOcorrenciaServiceTest {

    private AtualizacaoOcorrenciaDbAdapterOutputPort outputPort;
    private AtualizacaoOcorrenciaService service;

    @BeforeEach
    void setUp() {
        outputPort = mock(AtualizacaoOcorrenciaDbAdapterOutputPort.class);
        service = new AtualizacaoOcorrenciaService(outputPort);
    }

    @Test
    void criar_delegaSave() {
        AtualizacaoCreateDto dto = new AtualizacaoCreateDto(1L, "c");
        AtualizacaoOcorrencia a = new AtualizacaoOcorrencia();
        a.setId(1L);
        when(outputPort.save(dto)).thenReturn(a);
        AtualizacaoOcorrencia created = service.criarAtualizacao(dto);
        assertEquals(1L, created.getId());
    }

    @Test
    void listarPorOcorrencia_delegaFind() {
        when(outputPort.findByOcorrenciaId(3L)).thenReturn(List.of(new AtualizacaoOcorrencia()));
        List<AtualizacaoOcorrencia> list = service.listarPorOcorrenciaId(3L);
        assertFalse(list.isEmpty());
        verify(outputPort).findByOcorrenciaId(3L);
    }

    @Test
    void buscarPorId_delegaFindById() {
        AtualizacaoOcorrencia a = new AtualizacaoOcorrencia();
        a.setId(2L);
        when(outputPort.findById(2L)).thenReturn(Optional.of(a));
        Optional<AtualizacaoOcorrencia> res = service.buscarPorId(2L);
        assertTrue(res.isPresent());
    }

    @Test
    void atualizar_delegaUpdate() {
        AtualizacaoUpdateDto dto = new AtualizacaoUpdateDto(2L, 1L, "c");
        AtualizacaoOcorrencia a = new AtualizacaoOcorrencia();
        a.setId(2L);
        when(outputPort.update(dto)).thenReturn(a);
        AtualizacaoOcorrencia updated = service.atualizarAtualizacao(dto);
        assertEquals(2L, updated.getId());
    }

    @Test
    void deletar_chamaDelete() {
        doNothing().when(outputPort).deleteById(8L);
        service.deletarAtualizacao(8L);
        verify(outputPort).deleteById(8L);
    }
}

