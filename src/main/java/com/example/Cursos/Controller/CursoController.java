package com.example.Cursos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Cursos.Model.Curso;
import com.example.Cursos.Service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping("/")
    public ResponseEntity<List<Curso>> listar(){
        List<Curso> cursos = cursoService.findAll();
        if (cursos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Curso> listarCursoId(@PathVariable int id){
        if (cursoService.findById(id) == null) {
            return ResponseEntity.badRequest().build();
        }
        Curso cursoEncontrado = cursoService.findById(id);
        return ResponseEntity.ok(cursoEncontrado);
        
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Curso> listarCursoNombre(@PathVariable String nombre){
        if (cursoService.findByName(nombre) == null){
            return ResponseEntity.badRequest().build();
        }
        Curso cursoEncontrado = cursoService.findByName(nombre);
        return ResponseEntity.ok(cursoEncontrado);
        
    }

    @PostMapping("/")
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso){
        if (curso == null){
            return ResponseEntity.badRequest().build();
        }
        cursoService.save(curso);
        return ResponseEntity.ok(curso);
        
    }
    
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Curso> eliminar(@PathVariable int id){
        if (cursoService.findById(id) == null){
            return ResponseEntity.badRequest().build();
        }
        cursoService.deleteById(id);
        return ResponseEntity.ok().build();
        
    }

}
