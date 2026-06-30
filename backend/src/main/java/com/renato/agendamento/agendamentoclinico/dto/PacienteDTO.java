package com.renato.agendamento.agendamentoclinico.dto;


import com.renato.agendamento.agendamentoclinico.model.TipoPaciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    private String telefone;

    @NotNull(message = "Tipo de paciente é obrigatório")
    private TipoPaciente tipo;
}
