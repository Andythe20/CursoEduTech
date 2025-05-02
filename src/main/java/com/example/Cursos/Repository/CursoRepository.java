package com.example.Cursos.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.Cursos.Model.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    //devulve curso por su nombre
    Curso findByNombreCurso(@Param("nombreCurso") String nombreCurso);

    //devuelve el json con los campos de categoria completos
    @Query("SELECT c FROM Curso c JOIN FETCH c.categoria WHERE c.idCurso = :id")
    Optional<Curso> findByIdWithCategoria(@Param("id") long id);
    

}
