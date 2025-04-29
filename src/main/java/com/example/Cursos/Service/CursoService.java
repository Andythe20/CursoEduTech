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
        return repositorio.findById(id).get();
    }
    
    //buscar por nombre
    public Curso findByName(String nombre){
        return repositorio.findByName(nombre);
    }

    //guardar curso
    public Curso save(Curso curso){
        return repositorio.save(curso);
    }

}
