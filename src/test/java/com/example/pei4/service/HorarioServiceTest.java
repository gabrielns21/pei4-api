package com.example.pei4.service;

import com.example.pei4.model.*;
import com.example.pei4.repository.ProfessorRepository;
import com.example.pei4.repository.TurmaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;



public class HorarioServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(HorarioServiceTest.class);

    @InjectMocks
    private HorarioService horarioService;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private TurmaRepository turmaRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGerarHorarioParaTurma() {
        // Setup
        Turma turma = new Turma();
        turma.setId(1L);
        Curso curso = new Curso();
        curso.setDisciplinas(new ArrayList<>());
        turma.setCurso(curso);
        turma.setDiscplinasSesmestre(new ArrayList<>());

        Long turmaId = 1L;

        Disciplina disc1 = new Disciplina();
        disc1.setId(1L);
        disc1.setNome("Disciplina 1");
        disc1.setHorarioIntegral(false);
        Disciplina disc2 = new Disciplina();
        disc2.setId(2L);
        disc2.setNome("Disciplina 2");
        disc2.setHorarioIntegral(true);

        curso.getDisciplinas().add(disc1);
        curso.getDisciplinas().add(disc2);

        Professor prof1 = new Professor();
        prof1.setId(1L);
        prof1.setNome("Professor 1");
        prof1.setDisciplinas(Arrays.asList(disc1, disc2));
        prof1.setDisponibilidades(new ArrayList<>());

        Professor prof2 = new Professor();
        prof2.setId(2L);
        prof2.setNome("Professor 2");
        prof2.setDisciplinas(Arrays.asList(disc1));
        prof2.setDisponibilidades(new ArrayList<>());

        DiaComTurno dia1 = new DiaComTurno();
        dia1.setId(1L);
        dia1.setDayOfWeek(DayOfWeek.MONDAY);
        dia1.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO);
        dia1.setDisponivelHorarioIntegral(true);
        dia1.setDisponivelHorarioParcial(true);

        DiaComTurno dia2 = new DiaComTurno();
        dia2.setId(2L);
        dia2.setDayOfWeek(DayOfWeek.TUESDAY);
        dia2.setTurnoNoturno(TurnoNoturno.SEGUNDO_HORARIO);
        dia2.setDisponivelHorarioIntegral(true);
        dia2.setDisponivelHorarioParcial(true);

        prof1.getDisponibilidades().add(dia1);
        prof2.getDisponibilidades().add(dia2);

        when(professorRepository.findProfessoresByTurmaId(turma.getId())).thenReturn(Arrays.asList(prof1, prof2));



        // Act
        horarioService.gerarHorarioParaTurma(turmaId);

        // Assert
        assertEquals(2, turma.getDiscplinasSesmestre().size());
        DisciplinaSemestre ds1 = turma.getDiscplinasSesmestre().get(0);
        DisciplinaSemestre ds2 = turma.getDiscplinasSesmestre().get(1);

        // Log os valores para verificar
        System.out.println("DiscplinaSesmestre 1: " + ds1.getDisciplina().getNome() + ", " + ds1.getProfessor().getNome() + ", " + ds1.getTurnoNoturno());
        System.out.println("DiscplinaSesmestre 2: " + ds2.getDisciplina().getNome() + ", " + ds2.getProfessor().getNome() + ", " + ds2.getTurnoNoturno());

        // Asserções detalhadas para verificar as atribuições corretas
        if (ds1.getDisciplina().equals(disc1)) {
            assertTrue(ds1.getProfessor().equals(prof1) || ds1.getProfessor().equals(prof2));
        } else if (ds1.getDisciplina().equals(disc2)) {
            assertEquals(prof1, ds1.getProfessor());
        }

        if (ds2.getDisciplina().equals(disc1)) {
            assertTrue(ds2.getProfessor().equals(prof1) || ds2.getProfessor().equals(prof2));
        } else if (ds2.getDisciplina().equals(disc2)) {
            assertEquals(prof1, ds2.getProfessor());
        }
    }
}