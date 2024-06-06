package com.example.pei4.model;

import lombok.Getter;

public enum TurnoNoturno {

    PRIMEIRO_HORARIO("Primeiro horario"),
    SEGUNDO_HORARIO("Segundo horario"),
    HORARIO_INTEGRAL("Horario integral")

    ;

    @Getter
    private String descricao;

    private TurnoNoturno(String descricao){
        this.descricao = descricao;
    }
}
