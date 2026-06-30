package com.renato.agendamento.agendamentoclinico.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private String tipoConsulta; // ex: "Rotina", "Puericultura", "Retorno"

    @Enumerated(EnumType.STRING)
    private StatusConsulta status; // AGENDADA, REALIZADA, CANCELADA

    private String observacoes;
}
