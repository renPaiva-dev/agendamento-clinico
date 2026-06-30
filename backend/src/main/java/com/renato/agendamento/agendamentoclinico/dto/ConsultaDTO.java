
package com.renato.agendamento.agendamentoclinico.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaDTO {

    @NotNull(message = "Paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @NotNull(message = "Tipo de consulta é obrigatório")
    private String tipoConsulta;

    private String observacoes;
}
