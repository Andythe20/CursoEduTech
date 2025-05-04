package com.example.Cursos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Cursos.Model.Curso;
import com.example.Cursos.Repository.CursoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CursoService {

    @Autowired
    private CursoRepository repositorio;

    //listar todos los cursos
    public List<Curso> findAll(){
        return repositorio.findAll();
    }

    //buscar por id
    public Curso findById(long id){
        return repositorio.findById(id).orElse(null);
    }
    
    //buscar por nombre
    public Curso findByNombreCurso(String nombreCurso){
        if (nombreCurso == null || nombreCurso.isEmpty()){
            return null;
        }
        Curso curso = repositorio.findByNombreCurso(nombreCurso);
        if (curso == null){
            return null;
        }
        return repositorio.findByNombreCurso(nombreCurso);
    }

    //verificar el curso
    public boolean isValidCurso(Curso c){
        if (c.getNombreCurso() != null && !c.getNombreCurso().isEmpty() &&
            c.getDescripcion() != null && !c.getDescripcion().isEmpty() &&
            c.getNivel() != null && !c.getNivel().isEmpty() &&
            c.getPrecio() > 0 &&
            c.getDuracionHoras() > 0 &&
            c.getIdioma() != null && !c.getIdioma().isEmpty() &&
            c.getIdInstructor() > 0 &&
            c.getCategoria() != null){
            return true;
        }
        return false;
    }

    //guardar curso
    public Curso save(Curso curso){
        //validar el curso
        if (!isValidCurso(curso)){
            return null;
        }

        //verificar si el curso ya existe
        List<Curso> cursos = repositorio.findAll();
        for (Curso c : cursos) {
            if (c.getIdCurso() == curso.getIdCurso()) {
                return null;
            }
        }
        return repositorio.save(curso);
    }

    //eliminar curso por id
    public Curso deleteById(long id){
        Curso curso = repositorio.findByIdWithCategoria(id)
        .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        repositorio.delete(curso);
        return null;
    }

    //actualizar curso
    public Curso updateCurso(Curso curso){
        //validar el curso
        if (!isValidCurso(curso)){
            throw new IllegalArgumentException("Curso no valido");
        }

        //verificar si el curso ya existe para actualizar
        List<Curso> cursos = repositorio.findAll();
        for (Curso c : cursos) {
            if (c.getIdCurso() == curso.getIdCurso()) {
                //el metodo save de jpa maneja actualizaciones
                return repositorio.save(curso);
            }
        }

        return null;
    }
}
