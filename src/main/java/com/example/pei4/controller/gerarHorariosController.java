package com.example.pei4.controller;

import com.example.pei4.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/horarios")
public class gerarHorariosController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping("/gerar/{turmaId}")
    public void gerarHorarios(@PathVariable Long turmaId) {
        horarioService.gerarHorarioParaTurma(turmaId);
    }
}