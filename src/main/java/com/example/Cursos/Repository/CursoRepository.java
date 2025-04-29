package com.example.Cursos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.Cursos.Model.Curso;
import java.util.List;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    //Devuelve todos los cursos, setencia sql nativa
    @Query(value = "SELECT * FROM curso", nativeQuery = true)
    List<Curso> listarCursos();

    //devulve curso por su nombre
    @Query(value = "SELECT * FROM curso WHERE nombre_curso = nombre", nativeQuery = true)
    Curso findByName(@Param("nombre") String nombre);


    

}
