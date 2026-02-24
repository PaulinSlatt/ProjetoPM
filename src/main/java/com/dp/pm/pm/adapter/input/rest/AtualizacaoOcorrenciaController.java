package com.dp.pm.pm.adapter.input.rest;

import com.dp.pm.pm.adapter.output.entity.AtualizacaoOcorrencia;
import com.dp.pm.pm.domain.dto.AtualizacaoCreateDto;
import com.dp.pm.pm.domain.dto.AtualizacaoUpdateDto;
import com.dp.pm.pm.port.input.AtualizacaoOcorrenciaUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atualizacoes")
public class AtualizacaoOcorrenciaController {

    private final AtualizacaoOcorrenciaUseCase service;

    public AtualizacaoOcorrenciaController(AtualizacaoOcorrenciaUseCase service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AtualizacaoOcorrencia> criar(@Valid @RequestBody AtualizacaoCreateDto dto) {
        AtualizacaoOcorrencia created = service.criarAtualizacao(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtualizacaoOcorrencia> getById(@PathVariable Long id) {
        Optional<AtualizacaoOcorrencia> a = service.buscarPorId(id);
        return ResponseEntity.of(a);
    }

    @GetMapping
    public ResponseEntity<List<AtualizacaoOcorrencia>> list(@RequestParam Long ocorrenciaId) {
        List<AtualizacaoOcorrencia> list = service.listarPorOcorrenciaId(ocorrenciaId);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizacaoOcorrencia> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizacaoUpdateDto dto) {
        if (dto.getId() == null) dto.setId(id);
        AtualizacaoOcorrencia updated = service.atualizarAtualizacao(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletarAtualizacao(id);
    }
}
