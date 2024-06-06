//package com.example.pei4.service;
//
//import com.example.pei4.model.*;
//import com.example.pei4.repository.ProfessorRepository;
//import com.example.pei4.repository.TurmaRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.DayOfWeek;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//public class HorarioServiceTest3 {
//
//    private static final Logger logger = LoggerFactory.getLogger(HorarioServiceTest2.class);
//
//    @InjectMocks
//    private HorarioService horarioService;
//
//    @Mock
//    private ProfessorRepository professorRepository;
//
//    @Mock
//    private TurmaRepository turmaRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGerarHorarioParaTurma() {
//        // Initialize mocks
//
//        // Given
//        Turma turma = new Turma();
//        turma.setId(1L);
//        Curso curso = new Curso();
//        curso.setDisciplinas(new ArrayList<>());
//        turma.setCurso(curso);
//        turma.setDiscplinasSesmestre(new ArrayList<>());
//
//        // Professores e Disciplinas com base na tabela fornecida
//        List<Professor> professores = new ArrayList<>();
//
//        // Criando os professores existentes
//        // Isadora Got
//        Professor isadora = new Professor();
//        isadora.setId(1L);
//        isadora.setNome("Isadora Got");
//        Disciplina estruturasConcretoII = new Disciplina(1L, "Estruturas de Concreto II", false);
//        isadora.setDisciplinas(Arrays.asList(estruturasConcretoII));
//
//        DiaComTurno diaIsadora = new DiaComTurno();
//        diaIsadora.setId(1L);
//        diaIsadora.setDayOfWeek(DayOfWeek.MONDAY);
//        diaIsadora.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
//        diaIsadora.setDisponivelHorarioIntegral(false);
//        diaIsadora.setDisponivelHorarioParcial(true);
//
//        isadora.setDisponibilidades(Arrays.asList(diaIsadora));
//        professores.add(isadora);
//
//        // Daiana Valt
//        Professor daiana = new Professor();
//        daiana.setId(2L);
//        daiana.setNome("Daiana Valt");
//        Disciplina geotecnia = new Disciplina(2L, "Geotecnia", false);
//        Disciplina pei = new Disciplina(3L, "PEI", false);
//        daiana.setDisciplinas(Arrays.asList(geotecnia, pei));
//
//        DiaComTurno diaDaiana1 = new DiaComTurno();
//        diaDaiana1.setId(2L);
//        diaDaiana1.setDayOfWeek(DayOfWeek.TUESDAY);
//        diaDaiana1.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
//        diaDaiana1.setDisponivelHorarioIntegral(false);
//        diaDaiana1.setDisponivelHorarioParcial(true);
//
//        DiaComTurno diaDaiana2 = new DiaComTurno();
//        diaDaiana2.setId(3L);
//        diaDaiana2.setDayOfWeek(DayOfWeek.TUESDAY);
//        diaDaiana2.setTurnoNoturno(TurnoNoturno.SEGUNDO_HORARIO); // 21:00 as 22:00
//        diaDaiana2.setDisponivelHorarioIntegral(false);
//        diaDaiana2.setDisponivelHorarioParcial(true);
//
//        daiana.setDisponibilidades(Arrays.asList(diaDaiana1, diaDaiana2));
//        professores.add(daiana);
//
//        // Natan Sian
//        Professor natan = new Professor();
//        natan.setId(3L);
//        natan.setNome("Natan Sian");
//        Disciplina analiseEstruturalI = new Disciplina(4L, "Análise Estrutural I", false);
//        Disciplina estruturasMetalicas = new Disciplina(5L, "Estruturas Metálicas", false);
//        natan.setDisciplinas(Arrays.asList(analiseEstruturalI, estruturasMetalicas));
//
//        DiaComTurno diaNatan1 = new DiaComTurno();
//        diaNatan1.setId(4L);
//        diaNatan1.setDayOfWeek(DayOfWeek.MONDAY);
//        diaNatan1.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
//        diaNatan1.setDisponivelHorarioIntegral(false);
//        diaNatan1.setDisponivelHorarioParcial(true);
//
//        DiaComTurno diaNatan2 = new DiaComTurno();
//        diaNatan2.setId(5L);
//        diaNatan2.setDayOfWeek(DayOfWeek.TUESDAY);
//        diaNatan2.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
//        diaNatan2.setDisponivelHorarioIntegral(false);
//        diaNatan2.setDisponivelHorarioParcial(true);
//
//        natan.setDisponibilidades(Arrays.asList(diaNatan1, diaNatan2));
//        professores.add(natan);
//
//        // Rômulo Ger.
//        Professor romulo = new Professor();
//        romulo.setId(4L);
//        romulo.setNome("Rômulo Ger.");
//        Disciplina saneamentoBasicoII = new Disciplina(6L, "Saneamento Básico II", false);
//        Disciplina fenomenosTransporte = new Disciplina(7L, "Fenômenos de Transporte", false);
//        romulo.setDisciplinas(Arrays.asList(saneamentoBasicoII, fenomenosTransporte));
//
//        DiaComTurno diaRomulo1 = new DiaComTurno();
//        diaRomulo1.setId(6L);
//        diaRomulo1.setDayOfWeek(DayOfWeek.WEDNESDAY);
//        diaRomulo1.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
//        diaRomulo1.setDisponivelHorarioIntegral(true);
//        diaRomulo1.setDisponivelHorarioParcial(false);
//
//        DiaComTurno diaRomulo2 = new DiaComTurno();
//        diaRomulo2.setId(7L);
//        diaRomulo2.setDayOfWeek(DayOfWeek.THURSDAY);
//        diaRomulo2.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
//        diaRomulo2.setDisponivelHorarioIntegral(false);
//        diaRomulo2.setDisponivelHorarioParcial(true);
//
//        romulo.setDisponibilidades(Arrays.asList(diaRomulo1, diaRomulo2));
//        professores.add(romulo);
//
//        // Lindemberg
//        Professor lindemberg = new Professor();
//        lindemberg.setId(5L);
//        lindemberg.setNome("Lindemberg");
//        Disciplina fisicaII = new Disciplina(8L, "Física II", false);
//        Disciplina elementosMaquinasII = new Disciplina(9L, "Elementos de Máquinas II", false);
//        Disciplina resistenciaMateriaisI = new Disciplina(10L, "Resistência dos Materiais I", false);
//        lindemberg.setDisciplinas(Arrays.asList(fisicaII, elementosMaquinasII, resistenciaMateriaisI));
//
//        DiaComTurno diaLindemberg1 = new DiaComTurno();
//        diaLindemberg1.setId(8L);
//        diaLindemberg1.setDayOfWeek(DayOfWeek.MONDAY);
//        diaLindemberg1.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
//        diaLindemberg1.setDisponivelHorarioIntegral(false);
//        diaLindemberg1.setDisponivelHorarioParcial(true);
//
//        DiaComTurno diaLindemberg2 = new DiaComTurno();
//        diaLindemberg2.setId(9L);
//        diaLindemberg2.setDayOfWeek(DayOfWeek.WEDNESDAY);
//        diaLindemberg2.setTurnoNoturno(TurnoNoturno.PRIMEIRO_HORARIO); // 18:50 as 20:50
//        diaLindemberg2.setDisponivelHorarioIntegral(false);
//        diaLindemberg2.setDisponivelHorarioParcial(true);
//
//        DiaComTurno diaLindemberg3 = new DiaComTurno();
//        diaLindemberg3.setId(10L);
//        diaLindemberg3.setDayOfWeek(DayOfWeek.THURSDAY);
//        diaLindemberg3.setTurnoNoturno(TurnoNoturno.HORARIO_INTEGRAL); // 18:50 as 22:00
//        diaLindemberg3.setDisponivelHorarioIntegral(true);
//        diaLindemberg3.setDisponivelHorarioParcial(false);
//
//        lindemberg.setDisponibilidades(Arrays.asList(diaLindemberg1, diaLindemberg2, diaLindemberg3));
//        professores.add(lindemberg);
//
//        // Log dos horários disponíveis de cada professor
//        for (Professor professor : professores) {
//            StringBuilder disponiblidades = new StringBuilder();
//            disponiblidades.append("Professor: ").append(professor.getNome()).append(" - Disponibilidades: ");
//            for (DiaComTurno disponibilidade : professor.getDisponibilidades()) {
//                disponiblidades.append(disponibilidade.getDayOfWeek()).append(": ")
//                        .append(disponibilidade.isDisponivelHorarioIntegral() ? "Integral" : "Parcial (")
//                        .append(disponibilidade.getTurnoNoturno()).append("), ");
//            }
//            logger.info(disponiblidades.toString());
//        }
//
//        // When
//        when(professorRepository.findAll()).thenReturn(professores);
//        when(turmaRepository.findById(1L)).thenReturn(java.util.Optional.of(turma));
//
//        // Then
//        List<Horario> horarios = horarioService.gerarHorarioParaTurma(1L);
//        // Verificações adicionais podem ser feitas aqui dependendo da lógica do seu método gerarHorarioParaTurma
//        assertEquals(0, horarios.size());
//    }
//}
