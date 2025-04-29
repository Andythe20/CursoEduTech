package com.example.Cursos.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Cursos.Model.Curso;
import com.example.Cursos.Service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {
    @Autowired
    private CursoService service;

    @GetMapping()
    public ArrayList<Curso> listarCursos(){
        return service.listarCursos();
    }

    @GetMapping("/{id}")
    public Curso buscarIdCurso(@PathVariable int id){
        return service.buscarIdCurso(id);
    }

    @GetMapping("/{nombre}")
    public Curso buscarNombreCurso(@PathVariable String nombre){
        return service.buscarNombreCurso(nombre);
    }

    @PutMapping("/actualizar")
    public Curso actualizarCurso(@RequestBody Curso curso){
        return service.actualizarCurso(curso);
    }

    @DeleteMapping("/{id}")
    public Curso eliminarCurso(@PathVariable int id){
        return service.eliminarCurso(id);
    }

    @PostMapping()
    public Curso guardarCurso(@RequestBody Curso curso){
        return service.guardarCurso(curso);
    }

    

}
