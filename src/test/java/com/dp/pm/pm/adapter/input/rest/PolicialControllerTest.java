package com.dp.pm.pm.adapter.input.rest;

import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.domain.dto.PolicialCreateDto;
import com.dp.pm.pm.port.input.PolicialUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PolicialControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PolicialUseCase service;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        PolicialController controller = new PolicialController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void criar_deveRetornarCreated() throws Exception {
        PolicialCreateDto dto = new PolicialCreateDto(null, "MAT1");
        Policial p = new Policial();
        p.setId(1L);
        p.setMatricula("MAT1");
        when(service.criarPolicial(dto)).thenReturn(p);

        mockMvc.perform(post("/api/policiais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.containsString("/api/policiais/1")));

        verify(service).criarPolicial(dto);
    }

    @Test
    void getById_retornaOk() throws Exception {
        Policial p = new Policial();
        p.setId(2L);
        p.setMatricula("M2");
        when(service.buscarPorId(2L)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/api/policiais/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));

        verify(service).buscarPorId(2L);
    }
}
