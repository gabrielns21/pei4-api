package com.example.pei4.repository;

import com.example.pei4.model.Curso;
import com.example.pei4.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query(nativeQuery = true, value = "SELECT itr.romaneio_id FROM item_romaneio itr WHERE itr.pedido_id = ?1")
    List<Professor> findByCursos(Long cursoId, int periodo);


    @Query(value = "SELECT p.* FROM professor p " +
            "JOIN professor_disciplinas pd ON pd.professor_id = p.id " +
            "JOIN disciplina d ON d.id = pd.disciplinas_id " +
            "JOIN curso_disciplinas cd ON cd.disciplinas_id = d.id " +
            "JOIN turma t ON t.curso_id = cd.curso_id " +
            "WHERE t.id = :turmaId", nativeQuery = true)
    List<Professor> findProfessoresByTurmaId(@Param("turmaId") Long turmaId);
    }