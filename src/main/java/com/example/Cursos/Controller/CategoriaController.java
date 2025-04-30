package com.example.Cursos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Cursos.Model.Categoria;
import com.example.Cursos.Service.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        List<Categoria> categorias = service.findAll();
        if (categorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria){
        if (categoria == null){
            return ResponseEntity.badRequest().build();
        }
        service.save(categoria);
        return ResponseEntity.ok(categoria);
    }
}
