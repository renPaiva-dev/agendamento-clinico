package com.renato.agendamento.agendamentoclinico.repository;


import com.renato.agendamento.agendamentoclinico.model.Consulta;
import com.renato.agendamento.agendamentoclinico.model.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByStatus(StatusConsulta status);

    List<Consulta> findByPacienteId(Long pacienteId);

    boolean existsByPacienteIdAndDataHora(Long pacienteId, LocalDateTime dataHora);
}
