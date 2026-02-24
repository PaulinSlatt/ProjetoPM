package com.dp.pm.pm.domain.service;

import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.domain.dto.PolicialCreateDto;
import com.dp.pm.pm.domain.dto.PolicialUpdateDto;
import com.dp.pm.pm.port.output.PolicialDbAdapterOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PolicialServiceTest {

    private PolicialDbAdapterOutputPort outputPort;
    private PolicialService service;

    @BeforeEach
    void setUp() {
        outputPort = mock(PolicialDbAdapterOutputPort.class);
        service = new PolicialService(outputPort);
    }

    @Test
    void criarPolicial_delegaParaOutputPort() {
        PolicialCreateDto dto = new PolicialCreateDto(null, "MAT123");
        Policial esperado = new Policial();
        esperado.setId(1L);
        esperado.setMatricula("MAT123");

        when(outputPort.save(dto)).thenReturn(esperado);

        Policial created = service.criarPolicial(dto);

        assertNotNull(created);
        assertEquals(esperado.getMatricula(), created.getMatricula());
        verify(outputPort, times(1)).save(dto);
    }

    @Test
    void buscarPorId_retornaOptional() {
        Policial p = new Policial();
        p.setId(2L);
        p.setMatricula("X");
        when(outputPort.findById(2L)).thenReturn(Optional.of(p));

        Optional<Policial> res = service.buscarPorId(2L);
        assertTrue(res.isPresent());
        assertEquals(2L, res.get().getId());
    }

    @Test
    void listarPoliciais_delegaParaFindAll() {
        when(outputPort.findAll()).thenReturn(List.of(new Policial()));
        List<Policial> list = service.listarPoliciais();
        assertFalse(list.isEmpty());
        verify(outputPort, times(1)).findAll();
    }

    @Test
    void atualizarPolicial_delegaUpdate() {
        PolicialUpdateDto dto = new PolicialUpdateDto(3L, "M3");
        Policial p = new Policial();
        p.setId(3L);
        p.setMatricula("M3");
        when(outputPort.update(dto)).thenReturn(p);

        Policial updated = service.atualizarPolicial(dto);
        assertEquals(3L, updated.getId());
        verify(outputPort).update(dto);
    }

    @Test
    void deletarPolicial_chamaDelete() {
        doNothing().when(outputPort).deleteById(5L);
        service.deletarPolicial(5L);
        verify(outputPort).deleteById(5L);
    }
}
