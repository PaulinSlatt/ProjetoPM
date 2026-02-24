package com.dp.pm.pm.adapter.output.repository;

import com.dp.pm.pm.adapter.output.entity.AtualizacaoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtualizacaoOcorrenciaRepository extends JpaRepository<AtualizacaoOcorrencia, Long> {

    List<AtualizacaoOcorrencia> findByOcorrencia_Id(Long ocorrenciaId);

}

