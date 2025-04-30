package com.example.Cursos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private CursoService service;

    @GetMapping
    public ResponseEntity<List<Curso>> listar(){
        List<Curso> cursos = service.findAll();
        if (cursos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @PostMapping
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso){
        if (curso == null){
            return ResponseEntity.badRequest().build();
        }
        service.save(curso);
        return ResponseEntity.ok(curso);
        
    }
    

}
