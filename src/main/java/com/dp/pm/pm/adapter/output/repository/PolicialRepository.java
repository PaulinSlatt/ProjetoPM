package com.dp.pm.pm.adapter.output.repository;

import com.dp.pm.pm.adapter.output.entity.Policial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicialRepository extends JpaRepository<Policial, Long> {

    Optional<Policial> findByMatricula(String matricula);

}
