package com.dp.pm.pm.adapter.input.rest;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import com.dp.pm.pm.domain.dto.EnvolvidoCreateDto;
import com.dp.pm.pm.domain.dto.EnvolvidoUpdateDto;
import com.dp.pm.pm.port.input.EnvolvidoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/envolvidos")
public class EnvolvidoController {

    private final EnvolvidoUseCase service;

    public EnvolvidoController(EnvolvidoUseCase service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Envolvido> criar(@Valid @RequestBody EnvolvidoCreateDto dto) {
        Envolvido created = service.criarEnvolvido(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envolvido> getById(@PathVariable Long id) {
        Optional<Envolvido> e = service.buscarPorId(id);
        return ResponseEntity.of(e);
    }

    @GetMapping
    public ResponseEntity<List<Envolvido>> list(@RequestParam(required = false) String nome) {
        List<Envolvido> list = service.listarEnvolvidos(Optional.ofNullable(nome));
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envolvido> atualizar(@PathVariable Long id, @Valid @RequestBody EnvolvidoUpdateDto dto) {
        if (dto.getId() == null) dto.setId(id);
        Envolvido updated = service.atualizarEnvolvido(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletarEnvolvido(id);
    }
}
