package com.example.pei4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Turma {

    @Id
    @Getter
    @Setter
    private Long id;


    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private Integer periodo;


    @Getter
    @Setter
    @ManyToOne
    private Curso curso;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "turma_id") // Especifica a coluna de chave estrangeira
    private List<DisciplinaSemestre> discplinasSesmestre;



}
