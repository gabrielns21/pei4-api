package com.example.pei4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;


@Entity
public class DisciplinaSemestre {

    @Id
    @Getter
    @Setter
    private  Long id;

    @Getter
    @Setter
    @ManyToOne
    private Disciplina disciplina;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Getter
    @Setter
    @ManyToOne
    private Professor professor;

    @Getter
    @Setter
    private TurnoNoturno turnoNoturno;


}
