package com.example.pei4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;

@Entity
public class DiaComTurno {

    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TurnoNoturno turnoNoturno;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    TurnoNoturno turnoOcupado;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean disponivelHorarioIntegral;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean disponivelHorarioParcial;




}
