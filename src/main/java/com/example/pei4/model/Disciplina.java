package com.example.pei4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Disciplina {

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

//    @Getter
//    @Setter
//    @ManyToMany
//    private List<Curso> cursos;

    @Getter
    @Setter
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean horarioIntegral;

    // Construtor com parâmetros id, nome, horarioIntegral
    public Disciplina(Long id, String nome, boolean horarioIntegral) {
        this.id = id;
        this.nome = nome;
        this.horarioIntegral = horarioIntegral;
    }

    // Construtor vazio necessário para o JPA
    public Disciplina() {
    }
}
