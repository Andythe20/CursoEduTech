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

    //verificar si existe el curso
    public boolean existsByNombreCurso(String nombreCurso){
        return repositorio.existsByNombreCurso(nombreCurso);
    }

    //verificar si existe el curso por id
    public boolean existsById(long idCurso){
        return repositorio.existsById(idCurso);
    }

    //verificar el curso
    public boolean isValidCurso(Curso c){
        if (c.getNombreCurso() != null && !c.getNombreCurso().isEmpty() &&
            c.getDescripcion() != null && !c.getDescripcion().isEmpty() &&
            c.getNivel() != null &&
            c.getPrecio() >= 0 &&
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
        return repositorio.save(curso);
    }

    //eliminar curso por id
    public Curso deleteById(long id){
        
        repositorio.deleteById(id);
        return null;
    }

    //actualizar curso
    public Curso updateCurso(Curso curso){
        //validar el curso
        if (!isValidCurso(curso)){
            throw new IllegalArgumentException("Curso no valido");
        }

        return repositorio.save(curso);
    }
}
