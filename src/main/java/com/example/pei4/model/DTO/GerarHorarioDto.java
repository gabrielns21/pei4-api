package com.example.pei4.model.DTO;

import com.example.pei4.model.Professor;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

public class GerarHorarioDto {
    @Getter
    @Setter
    private DayOfWeek dayOfWeek;

    @Getter
    @Setter
    private List<Professor> professores;


}
