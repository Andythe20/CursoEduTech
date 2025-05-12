package com.example.Cursos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> listar(){
        List<Categoria> categorias = service.findAll();
        if (categorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Categoria> listarIdCategoria(@PathVariable long id){
        Categoria categoria = service.findById(id);
        if (categoria == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Categoria> listarIdNombre(@PathVariable String nombre){
        Categoria categoria = service.findByName(nombre);
        if (categoria == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping("/")
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria){
        if (categoria == null){
            return ResponseEntity.badRequest().build();
        }
        service.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> eliminarIdCategoria(@PathVariable Long id){
        Categoria categoria = service.findById(id);
        if (categoria == null) {
            return ResponseEntity.badRequest().build();
        }
        service.eliminar(categoria);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/")
    public ResponseEntity<Categoria> eliminarCategoria(@RequestBody Categoria cat){
        Categoria categoria = service.findById(cat.getIdCategoria());
        if (categoria == null) {
            return ResponseEntity.badRequest().build();
        }
        service.eliminar(categoria);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/")
    public ResponseEntity<Categoria> actualizarCategoria(@RequestBody Categoria cat){
        Categoria catEncontrada = service.findById(cat.getIdCategoria());
        if (catEncontrada == null) {
            return ResponseEntity.badRequest().build();
        }
        service.update(cat);
        return ResponseEntity.ok().body(cat);
    }
}
