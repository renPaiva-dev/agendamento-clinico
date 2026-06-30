package com.renato.agendamento.agendamentoclinico.service;


import com.renato.agendamento.agendamentoclinico.dto.PacienteDTO;
import com.renato.agendamento.agendamentoclinico.exception.RegraDeNegocioException;
import com.renato.agendamento.agendamentoclinico.model.Paciente;
import com.renato.agendamento.agendamentoclinico.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Paciente não encontrado"));
    }

    public Paciente criar(PacienteDTO dto) {
        if (pacienteRepository.existsByCpf(dto.getCpf())) {
            throw new RegraDeNegocioException("Já existe paciente cadastrado com esse CPF");
        }
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());
        paciente.setTipo(dto.getTipo());
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizar(Long id, PacienteDTO dto) {
        Paciente paciente = buscarPorId(id);
        paciente.setNome(dto.getNome());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setTelefone(dto.getTelefone());
        paciente.setTipo(dto.getTipo());
        return pacienteRepository.save(paciente);
    }

    public void deletar(Long id) {
        Paciente paciente = buscarPorId(id);
        pacienteRepository.delete(paciente);
    }
}