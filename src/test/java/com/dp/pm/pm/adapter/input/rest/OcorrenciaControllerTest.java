package com.dp.pm.pm.adapter.input.rest;

import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.domain.dto.OcorrenciaCreateDto;
import com.dp.pm.pm.domain.enums.Status;
import com.dp.pm.pm.port.input.OcorrenciaUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OcorrenciaController.class)
class OcorrenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OcorrenciaUseCase service;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    void postInvalid_shouldReturnBadRequest() throws Exception {
//        // Missing required fields in DTO
//        OcorrenciaCreateDto dto = new OcorrenciaCreateDto();
//        mockMvc.perform(post("/api/ocorrencias")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isBadRequest());
//    }

    @Test
    void listWithStatus_callsService() throws Exception {
        when(service.listarOcorrencias(Optional.of(Status.ABERTA))).thenReturn(List.of(new Ocorrencia()));
        mockMvc.perform(get("/api/ocorrencias").param("status", "ABERTA"))
                .andExpect(status().isOk());
        verify(service).listarOcorrencias(Optional.of(Status.ABERTA));
    }
}

