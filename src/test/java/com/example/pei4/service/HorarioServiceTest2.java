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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HorarioServiceTest2 {

    private static final Logger logger = LoggerFactory.getLogger(HorarioServiceTest.class);

    @InjectMocks
    private HorarioService horarioService;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private TurmaRepository turmaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGerarHorarioParaTurma() {
        // Initialize mocks

        // Given
        Turma turma = new Turma();
        turma.setId(1L);
        Curso curso = new Curso();
        curso.setDisciplinas(new ArrayList<>());
        turma.setCurso(curso);
        turma.setDiscplinasSesmestre(new ArrayList<>());
        Optional<Turma> turmaOptional = Optional.of(turma);

        Long turmaId = 1L;

        // Professores e Disciplinas com base na tabela fornecida
        List<Professor> professores = new ArrayList<>();

        // Isadora Got
        Professor isadora = new Professor();
        isadora.setId(1L);
        isadora.setNome("Isadora Got");
        Disciplina estruturasConcretoII = new Disciplina(1L, "Estruturas de Concreto II", false);
        isadora.setDisciplinas(Arrays.asList(estruturasConcretoII));


        DiaComTurno diaIsadora = new DiaComTurno();
        diaIsadora.setId(1L);
        diaIsadora.setDayOfWeek(DayOfWeek.MONDAY);
        diaIsadora.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaIsadora.setDisponivelHorarioIntegral(false);
        diaIsadora.setDisponivelHorarioParcial(true);

        isadora.setDisponibilidades(Arrays.asList(diaIsadora));
        professores.add(isadora);

        // Daiana Valt
        Professor daiana = new Professor();
        daiana.setId(2L);
        daiana.setNome("Daiana Valt");
        Disciplina geotecnia = new Disciplina(2L, "Geotecnia", false);
        Disciplina pei = new Disciplina(3L, "PEI", false);
        daiana.setDisciplinas(Arrays.asList(geotecnia, pei));

        DiaComTurno diaDaiana1 = new DiaComTurno();
        diaDaiana1.setId(2L);
        diaDaiana1.setDayOfWeek(DayOfWeek.TUESDAY);
        diaDaiana1.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaDaiana1.setDisponivelHorarioIntegral(false);
        diaDaiana1.setDisponivelHorarioParcial(true);

        DiaComTurno diaDaiana2 = new DiaComTurno();
        diaDaiana2.setId(3L);
        diaDaiana2.setDayOfWeek(DayOfWeek.TUESDAY);
        diaDaiana2.setTurnoNoturno(TurnoNoturno.SEGUNDO_HORARIO); // 21:00 as 22:00
        diaDaiana2.setDisponivelHorarioIntegral(false);
        diaDaiana2.setDisponivelHorarioParcial(true);

        daiana.setDisponibilidades(Arrays.asList(diaDaiana1, diaDaiana2));
        professores.add(daiana);

        // Natan Sian
        Professor natan = new Professor();
        natan.setId(3L);
        natan.setNome("Natan Sian");
        Disciplina analiseEstruturalI = new Disciplina(4L, "Análise Estrutural I", false);
        Disciplina estruturasMetalicas = new Disciplina(5L, "Estruturas Metálicas", false);
        natan.setDisciplinas(Arrays.asList(analiseEstruturalI, estruturasMetalicas));

        DiaComTurno diaNatan1 = new DiaComTurno();
        diaNatan1.setId(4L);
        diaNatan1.setDayOfWeek(DayOfWeek.MONDAY);
        diaNatan1.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaNatan1.setDisponivelHorarioIntegral(false);
        diaNatan1.setDisponivelHorarioParcial(true);

        DiaComTurno diaNatan2 = new DiaComTurno();
        diaNatan2.setId(5L);
        diaNatan2.setDayOfWeek(DayOfWeek.TUESDAY);
        diaNatan2.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaNatan2.setDisponivelHorarioIntegral(false);
        diaNatan2.setDisponivelHorarioParcial(true);

        natan.setDisponibilidades(Arrays.asList(diaNatan1, diaNatan2));
        professores.add(natan);

        // Rômulo Ger.
        Professor romulo = new Professor();
        romulo.setId(4L);
        romulo.setNome("Rômulo Ger.");
        Disciplina saneamentoBasicoII = new Disciplina(6L, "Saneamento Básico II", false);
        Disciplina fenomenosTransporte = new Disciplina(7L, "Fenômenos de Transporte", false);
        romulo.setDisciplinas(Arrays.asList(saneamentoBasicoII, fenomenosTransporte));

        DiaComTurno diaRomulo1 = new DiaComTurno();
        diaRomulo1.setId(6L);
        diaRomulo1.setDayOfWeek(DayOfWeek.WEDNESDAY);
        diaRomulo1.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
        diaRomulo1.setDisponivelHorarioIntegral(true);
        diaRomulo1.setDisponivelHorarioParcial(false);

        DiaComTurno diaRomulo2 = new DiaComTurno();
        diaRomulo2.setId(7L);
        diaRomulo2.setDayOfWeek(DayOfWeek.THURSDAY);
        diaRomulo2.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaRomulo2.setDisponivelHorarioIntegral(false);
        diaRomulo2.setDisponivelHorarioParcial(true);

        romulo.setDisponibilidades(Arrays.asList(diaRomulo1, diaRomulo2));
        professores.add(romulo);

        // Lindemberg
        Professor lindemberg = new Professor();
        lindemberg.setId(5L);
        lindemberg.setNome("Lindemberg");
        Disciplina fisicaII = new Disciplina(8L, "Física II", false);
        Disciplina elementosMaquinasII = new Disciplina(9L, "Elementos de Máquinas II", false);
        Disciplina resistenciaMateriaisI = new Disciplina(10L, "Resistência dos Materiais I", false);
        lindemberg.setDisciplinas(Arrays.asList(fisicaII, elementosMaquinasII, resistenciaMateriaisI));

        DiaComTurno diaLindemberg1 = new DiaComTurno();
        diaLindemberg1.setId(8L);
        diaLindemberg1.setDayOfWeek(DayOfWeek.MONDAY);
        diaLindemberg1.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaLindemberg1.setDisponivelHorarioIntegral(false);
        diaLindemberg1.setDisponivelHorarioParcial(true);

        DiaComTurno diaLindemberg2 = new DiaComTurno();
        diaLindemberg2.setId(9L);
        diaLindemberg2.setDayOfWeek(DayOfWeek.WEDNESDAY);
        diaLindemberg2.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaLindemberg2.setDisponivelHorarioIntegral(false);
        diaLindemberg2.setDisponivelHorarioParcial(true);

        DiaComTurno diaLindemberg3 = new DiaComTurno();
        diaLindemberg3.setId(10L);
        diaLindemberg3.setDayOfWeek(DayOfWeek.THURSDAY);
        diaLindemberg3.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
        diaLindemberg3.setDisponivelHorarioIntegral(true);
        diaLindemberg3.setDisponivelHorarioParcial(false);

        lindemberg.setDisponibilidades(Arrays.asList(diaLindemberg1, diaLindemberg2, diaLindemberg3));
        professores.add(lindemberg);

        // Ciro Neto
        Professor ciro = new Professor();
        ciro.setId(6L);
        ciro.setNome("Ciro Neto");
        Disciplina fisicaIIEci = new Disciplina(11L, "Física II (ECI/ECA/ECO)", false);
        Disciplina dinamica1 = new Disciplina(12L, "Dinâmica 1º", false);
        ciro.setDisciplinas(Arrays.asList(fisicaIIEci, dinamica1));

        DiaComTurno diaCiro1 = new DiaComTurno();
        diaCiro1.setId(11L);
        diaCiro1.setDayOfWeek(DayOfWeek.MONDAY);
        diaCiro1.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaCiro1.setDisponivelHorarioIntegral(false);
        diaCiro1.setDisponivelHorarioParcial(true);

        DiaComTurno diaCiro2 = new DiaComTurno();
        diaCiro2.setId(12L);
        diaCiro2.setDayOfWeek(DayOfWeek.TUESDAY);
        diaCiro2.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
        diaCiro2.setDisponivelHorarioIntegral(true);
        diaCiro2.setDisponivelHorarioParcial(false);

        ciro.setDisponibilidades(Arrays.asList(diaCiro1, diaCiro2));
        professores.add(ciro);

        // Mec. Contrat Eduardo Junior
        Professor eduardo = new Professor();
        eduardo.setId(7L);
        eduardo.setNome("Mec. Contrat Eduardo Junior");
        Disciplina termodinamica = new Disciplina(13L, "Termodinâmica", false);
        Disciplina vibracoesMecanicas = new Disciplina(14L, "Vibrações Mecânicas", false);
        Disciplina motoresCombustao = new Disciplina(15L, "Motores de Combustão", false);
        eduardo.setDisciplinas(Arrays.asList(termodinamica, vibracoesMecanicas, motoresCombustao));

        DiaComTurno diaEduardo1 = new DiaComTurno();
        diaEduardo1.setId(13L);
        diaEduardo1.setDayOfWeek(DayOfWeek.MONDAY);
        diaEduardo1.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
        diaEduardo1.setDisponivelHorarioIntegral(true);
        diaEduardo1.setDisponivelHorarioParcial(false);

        DiaComTurno diaEduardo2 = new DiaComTurno();
        diaEduardo2.setId(14L);
        diaEduardo2.setDayOfWeek(DayOfWeek.TUESDAY);
        diaEduardo2.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaEduardo2.setDisponivelHorarioIntegral(false);
        diaEduardo2.setDisponivelHorarioParcial(true);

        DiaComTurno diaEduardo3 = new DiaComTurno();
        diaEduardo3.setId(15L);
        diaEduardo3.setDayOfWeek(DayOfWeek.WEDNESDAY);
        diaEduardo3.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaEduardo3.setDisponivelHorarioIntegral(false);
        diaEduardo3.setDisponivelHorarioParcial(true);

        eduardo.setDisponibilidades(Arrays.asList(diaEduardo1, diaEduardo2, diaEduardo3));
        professores.add(eduardo);

        // Júlio César
        Professor julio = new Professor();
        julio.setId(8L);
        julio.setNome("Júlio César");
        Disciplina introducaoCalculo = new Disciplina(16L, "Introdução ao Cálculo", false);
        Disciplina calculoII = new Disciplina(17L, "Cálculo II", false);
        Disciplina ga = new Disciplina(18L, "GA", false);
        julio.setDisciplinas(Arrays.asList(introducaoCalculo, calculoII, ga));

        DiaComTurno diaJulio1 = new DiaComTurno();
        diaJulio1.setId(16L);
        diaJulio1.setDayOfWeek(DayOfWeek.FRIDAY);
        diaJulio1.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
        diaJulio1.setDisponivelHorarioIntegral(true);
        diaJulio1.setDisponivelHorarioParcial(true);

        DiaComTurno diaJulio2 = new DiaComTurno();
        diaJulio2.setId(17L);
        diaJulio2.setDayOfWeek(DayOfWeek.TUESDAY);
        diaJulio2.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 17:50 as 19:50 e 20:00 as 22:00
        diaJulio2.setDisponivelHorarioIntegral(true);
        diaJulio2.setDisponivelHorarioParcial(false);

        DiaComTurno diaJulio3 = new DiaComTurno();
        diaJulio3.setId(18L);
        diaJulio3.setDayOfWeek(DayOfWeek.WEDNESDAY);
        diaJulio3.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
        diaJulio3.setDisponivelHorarioIntegral(true);
        diaJulio3.setDisponivelHorarioParcial(false);

        DiaComTurno diaJulio4 = new DiaComTurno();
        diaJulio4.setId(19L);
        diaJulio4.setDayOfWeek(DayOfWeek.THURSDAY);
        diaJulio4.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
        diaJulio4.setDisponivelHorarioIntegral(true);
        diaJulio4.setDisponivelHorarioParcial(false);

        julio.setDisponibilidades(Arrays.asList(diaJulio1, diaJulio2, diaJulio3, diaJulio4));
        professores.add(julio);

        // Diego
        Professor diego = new Professor();
        diego.setId(9L);
        diego.setNome("Diego");
        diego.setDisciplinas(new ArrayList<>()); // Assuming no disciplines assigned

        // Assuming no availability data for Diego in the table
        diego.setDisponibilidades(new ArrayList<>());
        professores.add(diego);

        // Aline Azevedo
        Professor aline = new Professor();
        aline.setId(10L);
        aline.setNome("Aline Azevedo");
        Disciplina desenhoArquitetonicoComputacional = new Disciplina(19L, "Desenho Arquitetônico e Computacional", false);
        Disciplina desenhoTecnico = new Disciplina(20L, "Desenho Técnico", false);
        aline.setDisciplinas(Arrays.asList(desenhoArquitetonicoComputacional, desenhoTecnico));

        DiaComTurno diaAline = new DiaComTurno();
        diaAline.setId(20L);
        diaAline.setDayOfWeek(DayOfWeek.WEDNESDAY);
        diaAline.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
        diaAline.setDisponivelHorarioIntegral(false);
        diaAline.setDisponivelHorarioParcial(true);

        aline.setDisponibilidades(Arrays.asList(diaAline));
        professores.add(aline);


        professores.stream().forEach( professor -> turma.getCurso().getDisciplinas().addAll(professor.getDisciplinas()));


        // Mocking the repository responses
        when(professorRepository.findProfessoresByTurmaId(turmaId)).thenReturn(professores);
        when(turmaRepository.findById(turmaId)).thenReturn(turmaOptional);


        for (Professor professor : professores) {
            logger.info("Professor: " + professor.getNome());
            for (Disciplina disciplina: professor.getDisciplinas()){
                logger.info(disciplina.getNome());
            }
            for (DiaComTurno disponibilidade : professor.getDisponibilidades()) {
                logger.info("Disponibilidade - Dia: " + disponibilidade.getDayOfWeek() +
                        ", Turno Noturno: " + disponibilidade.getTurnoNoturno() +
                        ", Disponível Horário Integral: " + disponibilidade.isDisponivelHorarioIntegral() +
                        ", Disponível Horário Parcial: " + disponibilidade.isDisponivelHorarioParcial());
            }
        }

        for (Disciplina disciplina: turma.getCurso().getDisciplinas()){
            logger.info(disciplina.getNome() +", horario integral: " + disciplina.isHorarioIntegral());
        }

        // Action
        horarioService.gerarHorarioParaTurma(turmaId);

        // Logging the generated schedule
         for (DisciplinaSemestre disciplinaSemestre : turma.getDiscplinasSesmestre()) {
            logger.info("Turma {} - Disciplina {} com Professor {} no Dia {} no Turno {}",
                    turma.getId(),
                    disciplinaSemestre.getDisciplina().getNome(),
                    disciplinaSemestre.getProfessor().getNome(),
                    disciplinaSemestre.getDayOfWeek(),
                    disciplinaSemestre.getTurnoNoturno());
        }

        // Assert
        // Assuming here you want to assert the total number of disciplines assigned
        // Adjust based on what you want to validate
//        assertEquals(20, turma.getDiscplinasSesmestre().size());
    }
}
