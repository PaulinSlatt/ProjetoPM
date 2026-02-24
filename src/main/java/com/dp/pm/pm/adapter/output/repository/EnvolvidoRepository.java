package com.dp.pm.pm.adapter.output.repository;

import com.dp.pm.pm.adapter.output.entity.Envolvido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvolvidoRepository extends JpaRepository<Envolvido, Long> {

    List<Envolvido> findByNomeContainingIgnoreCase(String nome);

}

