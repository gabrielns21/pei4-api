package com.example.pei4.service;

import com.example.pei4.model.*;
import com.example.pei4.repository.ProfessorRepository;
import com.example.pei4.repository.TurmaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HorarioService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    private static final Logger logger = LoggerFactory.getLogger(HorarioService.class);


    public void gerarHorarioParaTurma(Long turmaId) {

        Turma turma = turmaRepository.findById(turmaId).orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        List<Professor> professores = professorRepository.findProfessoresByTurmaId(turma.getId());
        List<Disciplina> disciplinas = turma.getCurso().getDisciplinas();  // Ajuste conforme necessário para obter as disciplinas da turma

        List<Disciplina> disciplinasOrdenadas = ordenarDisciplinasPorDisponibilidade(disciplinas, professores);
        Map<DayOfWeek, Set<TurnoNoturno>> horariosOcupadosPorTurma = new HashMap<>();

        for (Disciplina disciplina : disciplinasOrdenadas) {
            Professor professorSelecionado = null;
            DiaComTurno diaComTurnoSelecionado = null;

            for (Professor professor : professores) {
                if (!professor.getDisciplinas().contains(disciplina)) continue;

                for (DiaComTurno diaComTurno : professor.getDisponibilidades()) {
                    if (verificarDisponibilidade(diaComTurno, disciplina, horariosOcupadosPorTurma)) {
                        professorSelecionado = professor;
                        diaComTurnoSelecionado = diaComTurno;
                        break;
                    }
                }
                if (professorSelecionado != null) break;
            }

            if (professorSelecionado != null) {
                atualizarDisponibilidade(diaComTurnoSelecionado, disciplina, horariosOcupadosPorTurma);
                DisciplinaSemestre disciplinaSemestre = criarDiscplinaSesmestre(disciplina, professorSelecionado, diaComTurnoSelecionado);
                turma.getDiscplinasSesmestre().add(disciplinaSemestre);
                atualizarHorariosOcupadosPorTurma(horariosOcupadosPorTurma, diaComTurnoSelecionado);
            }
        }

        for (DisciplinaSemestre disciplinaSemestre : turma.getDiscplinasSesmestre()) {
            logger.info("Turma {} - Disciplina {} com Professor {} no Dia {} no Turno {}",
                    turma.getId(),
                    disciplinaSemestre.getDisciplina().getNome(),
                    disciplinaSemestre.getProfessor().getNome(),
                    disciplinaSemestre.getDayOfWeek(),
                    disciplinaSemestre.getTurnoNoturno());
        }

//        turmaRepository.save(turma);
    }



    private List<Disciplina> ordenarDisciplinasPorDisponibilidade(List<Disciplina> disciplinas, List<Professor> professores) {
        return disciplinas.stream()
                .sorted(Comparator.comparingLong(disciplina ->
                        professores.stream().filter(professor -> professor.getDisciplinas().contains(disciplina)).count()))
                .collect(Collectors.toList());
    }

    private boolean verificarDisponibilidade(DiaComTurno diaComTurno, Disciplina disciplina, Map<DayOfWeek, Set<TurnoNoturno>> horariosOcupadosPorTurma) {
        Set<TurnoNoturno> turnosOcupadosNaTurma = horariosOcupadosPorTurma.getOrDefault(diaComTurno.getDayOfWeek(), new HashSet<>()
        );

        if (disciplina.isHorarioIntegral()) {
            return diaComTurno.isDisponivelHorarioIntegral()
                    && !turnosOcupadosNaTurma.contains(TurnoNoturno.HORARIO_INTEGRAL)
                    && turnosOcupadosNaTurma.isEmpty();
        } else {
            return diaComTurno.isDisponivelHorarioParcial()
                    && (diaComTurno.getTurnoOcupado() != diaComTurno.getTurnoNoturno())
                    && !turnosOcupadosNaTurma.contains(diaComTurno.getTurnoNoturno())
                    && !turnosOcupadosNaTurma.contains(TurnoNoturno.HORARIO_INTEGRAL)
                    && (diaComTurno.getTurnoNoturno() == TurnoNoturno.PRIMEIRO_HORARIO || diaComTurno.getTurnoNoturno() == TurnoNoturno.SEGUNDO_HORARIO ? !turnosOcupadosNaTurma.contains(TurnoNoturno.PRIMEIRO_HORARIO) && !turnosOcupadosNaTurma.contains(TurnoNoturno.SEGUNDO_HORARIO) : true);
        }
    }
    private void atualizarDisponibilidade(DiaComTurno diaComTurno, Disciplina disciplina, Map<DayOfWeek, Set<TurnoNoturno>> horariosOcupadosPorTurma) {

        Set<TurnoNoturno> turnosOcupadosNaTurma = horariosOcupadosPorTurma.getOrDefault(diaComTurno.getDayOfWeek(), new HashSet<>());

        if (disciplina.isHorarioIntegral()) {
            diaComTurno.setDisponivelHorarioIntegral(false);
            diaComTurno.setDisponivelHorarioParcial(false);
            diaComTurno.setTurnoOcupado(TurnoNoturno.HORARIO_INTEGRAL);
        } else {
            if (diaComTurno.getTurnoNoturno() == TurnoNoturno.PRIMEIRO_HORARIO || diaComTurno.getTurnoNoturno() == TurnoNoturno.SEGUNDO_HORARIO) {
                diaComTurno.setDisponivelHorarioParcial(false);
                diaComTurno.setTurnoOcupado(diaComTurno.getTurnoNoturno());
            } else if (diaComTurno.getTurnoNoturno() == TurnoNoturno.HORARIO_INTEGRAL) {
                if (diaComTurno.isDisponivelHorarioIntegral()) {
                    diaComTurno.setDisponivelHorarioIntegral(false);

                    // Inicializando turnosJaOcupados corretamente
                    Set<TurnoNoturno> turnosJaOcupados = horariosOcupadosPorTurma.getOrDefault(diaComTurno.getDayOfWeek(), new HashSet<>());

                    if(turnosJaOcupados.isEmpty()){
                        diaComTurno.setTurnoOcupado(TurnoNoturno.PRIMEIRO_HORARIO);
                    }else {
                        diaComTurno.setTurnoOcupado(buscarTurnoLivre(turnosJaOcupados.stream().toList().get(0)));
                    }
                } else {
                    diaComTurno.setDisponivelHorarioIntegral(false);
                    diaComTurno.setDisponivelHorarioParcial(false);
                    diaComTurno.setTurnoOcupado(TurnoNoturno.HORARIO_INTEGRAL);
                }
            }
        }
    }


    private TurnoNoturno buscarTurnoLivre(TurnoNoturno turnoNoturno) {
        List<TurnoNoturno> turnos = Arrays.stream(TurnoNoturno.values())
                .filter(turno -> !turno.equals(TurnoNoturno.HORARIO_INTEGRAL))
                .toList();
        return turnos.stream()
                .filter(turno -> !turno.equals(turnoNoturno))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No available turnos"));
    }


    private void atualizarHorariosOcupadosPorTurma(Map<DayOfWeek, Set<TurnoNoturno>> horariosOcupadosPorTurma, DiaComTurno diaComTurno) {
        horariosOcupadosPorTurma.computeIfAbsent(diaComTurno.getDayOfWeek(), k -> new HashSet<>()).add(diaComTurno.getTurnoNoturno());
    }

    private DisciplinaSemestre criarDiscplinaSesmestre(Disciplina disciplina, Professor professor, DiaComTurno diaComTurno) {
        DisciplinaSemestre disciplinaSemestre = new DisciplinaSemestre();
        disciplinaSemestre.setDisciplina(disciplina);
        disciplinaSemestre.setProfessor(professor);
        disciplinaSemestre.setTurnoNoturno(diaComTurno.getTurnoOcupado());
        disciplinaSemestre.setDayOfWeek(diaComTurno.getDayOfWeek());
        return disciplinaSemestre;
    }
}
