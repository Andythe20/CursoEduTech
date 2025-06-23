package com.example.Cursos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Cursos.Model.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNombreCurso(String nombreCurso);

    boolean existsByNombreCurso(String nombreCurso);

    boolean existsByIdCurso(long idCurso);


    
    
}
