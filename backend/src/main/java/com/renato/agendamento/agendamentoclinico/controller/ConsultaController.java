package com.renato.agendamento.agendamentoclinico.controller;


import com.renato.agendamento.agendamentoclinico.dto.ConsultaDTO;
import com.renato.agendamento.agendamentoclinico.model.Consulta;
import com.renato.agendamento.agendamentoclinico.model.StatusConsulta;
import com.renato.agendamento.agendamentoclinico.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    public List<Consulta> listar(@RequestParam(required = false) StatusConsulta status) {
        if (status != null) {
            return consultaService.listarPorStatus(status);
        }
        return consultaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Consulta buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta agendar(@Valid @RequestBody ConsultaDTO dto) {
        return consultaService.agendar(dto);
    }

    @PatchMapping("/{id}/status")
    public Consulta atualizarStatus(@PathVariable Long id, @RequestParam StatusConsulta status) {
        return consultaService.atualizarStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public Consulta cancelar(@PathVariable Long id) {
        consultaService.cancelar(id);
        return consultaService.buscarPorId(id);
    }
}
