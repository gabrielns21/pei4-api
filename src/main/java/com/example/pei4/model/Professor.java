package com.example.pei4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Professor {

    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String nome;

    @OneToMany
    @JoinColumn(name = "professor_id") // Especifica a coluna de chave estrangeira
    @Getter
    @Setter
    private List<DiaComTurno> disponibilidades;

    @Getter
    @Setter
    @ManyToMany
    private List<Disciplina> disciplinas;

    @Getter
    @Setter
    @OneToMany(mappedBy = "professor")
    private List<DisciplinaSemestre> disciplinasSemestres;



}
