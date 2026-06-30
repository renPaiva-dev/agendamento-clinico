package com.renato.agendamento.agendamentoclinico.repository;

import com.renato.agendamento.agendamentoclinico.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByCpf(String cpf);
}