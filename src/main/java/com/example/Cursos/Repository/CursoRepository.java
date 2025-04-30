package com.example.Cursos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.Cursos.Model.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    //devulve curso por su nombre, JPQL
    @Query("SELECT c FROM Curso c WHERE c.nombreCurso = :nombre")
    Curso findByName(@Param("nombre") String nombre);


    

}
