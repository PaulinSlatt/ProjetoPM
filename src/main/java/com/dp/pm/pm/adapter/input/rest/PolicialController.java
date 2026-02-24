package com.dp.pm.pm.adapter.input.rest;

import com.dp.pm.pm.adapter.output.entity.Policial;
import com.dp.pm.pm.domain.dto.PolicialCreateDto;
import com.dp.pm.pm.domain.dto.PolicialUpdateDto;
import com.dp.pm.pm.port.input.PolicialUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/policiais")
public class PolicialController {

    private final PolicialUseCase service;

    public PolicialController(PolicialUseCase service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Policial> criar(@Valid @RequestBody PolicialCreateDto dto) {
        Policial created = service.criarPolicial(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policial> getById(@PathVariable Long id) {
        Optional<Policial> p = service.buscarPorId(id);
        return ResponseEntity.of(p);
    }

    @GetMapping
    public ResponseEntity<List<Policial>> list() {
        List<Policial> list = service.listarPoliciais();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policial> atualizar(@PathVariable Long id, @Valid @RequestBody PolicialUpdateDto dto) {
        if (dto.getId() == null) dto.setId(id);
        Policial updated = service.atualizarPolicial(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletarPolicial(id);
    }
}
