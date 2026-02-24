package com.dp.pm.pm.adapter.output.entity;

import com.dp.pm.pm.domain.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "ocorrencia")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_crime", length = 50, nullable = false)
    private String tipoCrime;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "local", length = 150, nullable = false)
    private String local;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ABERTA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policial_responsavel_id")
    private Policial policialResponsavel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ocorrencia_envolvido",
            joinColumns = @JoinColumn(name = "ocorrencia_id"),
            inverseJoinColumns = @JoinColumn(name = "envolvido_id"))
    private Set<Envolvido> envolvidos = new HashSet<>();

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AtualizacaoOcorrencia> atualizacoes = new HashSet<>();

    // O banco define o valor por DEFAULT CURRENT_TIMESTAMP; manter como não-insertable para refletir isso
    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;

}
