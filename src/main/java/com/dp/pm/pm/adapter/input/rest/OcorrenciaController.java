package com.dp.pm.pm.adapter.input.rest;

import com.dp.pm.pm.adapter.output.entity.Ocorrencia;
import com.dp.pm.pm.domain.dto.OcorrenciaCreateDto;
import com.dp.pm.pm.domain.dto.OcorrenciaUpdateDto;
import com.dp.pm.pm.domain.enums.Status;
import com.dp.pm.pm.port.input.OcorrenciaUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaUseCase service;

    public OcorrenciaController(OcorrenciaUseCase service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ocorrencia> criar(@Valid @RequestBody OcorrenciaCreateDto dto) {
        Ocorrencia created = service.criarOcorrencia(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> getById(@PathVariable Long id) {
        Optional<Ocorrencia> o = service.buscarPorId(id);
        return ResponseEntity.of(o);
    }

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> list(@RequestParam(required = false) Status status) {
        List<Ocorrencia> list = service.listarOcorrencias(Optional.ofNullable(status));
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ocorrencia> atualizar(@PathVariable Long id, @Valid @RequestBody OcorrenciaUpdateDto dto) {
        if (dto.getId() == null) dto.setId(id);
        Ocorrencia updated = service.atualizarOcorrencia(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletarOcorrencia(id);
    }
}
