package com.renato.agendamento.agendamentoclinico.service;


import com.renato.agendamento.agendamentoclinico.dto.ConsultaDTO;
import com.renato.agendamento.agendamentoclinico.exception.RegraDeNegocioException;
import com.renato.agendamento.agendamentoclinico.model.Consulta;
import com.renato.agendamento.agendamentoclinico.model.Paciente;
import com.renato.agendamento.agendamentoclinico.model.StatusConsulta;
import com.renato.agendamento.agendamentoclinico.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteService pacienteService;

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public List<Consulta> listarPorStatus(StatusConsulta status) {
        return consultaRepository.findByStatus(status);
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Consulta não encontrada"));
    }

    public Consulta agendar(ConsultaDTO dto) {
        Paciente paciente = pacienteService.buscarPorId(dto.getPacienteId());

        if (consultaRepository.existsByPacienteIdAndDataHora(dto.getPacienteId(), dto.getDataHora())) {
            throw new RegraDeNegocioException("Paciente já possui consulta marcada nesse horário");
        }

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDataHora(dto.getDataHora());
        consulta.setTipoConsulta(dto.getTipoConsulta());
        consulta.setObservacoes(dto.getObservacoes());
        consulta.setStatus(StatusConsulta.AGENDADA);

        return consultaRepository.save(consulta);
    }

    public Consulta atualizarStatus(Long id, StatusConsulta novoStatus) {
        Consulta consulta = buscarPorId(id);
        consulta.setStatus(novoStatus);
        return consultaRepository.save(consulta);
    }

    public void cancelar(Long id) {
        atualizarStatus(id, StatusConsulta.CANCELADA);
    }
}
