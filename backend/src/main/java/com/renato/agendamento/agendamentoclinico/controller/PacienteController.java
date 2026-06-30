package com.renato.agendamento.agendamentoclinico.controller;


import com.renato.agendamento.agendamentoclinico.dto.PacienteDTO;
import com.renato.agendamento.agendamentoclinico.model.Paciente;
import com.renato.agendamento.agendamentoclinico.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente criar(@Valid @RequestBody PacienteDTO dto) {
        return pacienteService.criar(dto);
    }

    @PutMapping("/{id}")
    public Paciente atualizar(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        return pacienteService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
    }
}
