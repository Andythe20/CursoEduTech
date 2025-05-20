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
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/nombre/{nombreCat}")
    public ResponseEntity<Categoria> listarNombreCategoria(@PathVariable String nombreCat){
        if (!service.existsByNombreCat(nombreCat)) {
            return ResponseEntity.notFound().build();
        }
        Categoria cat = service.findByNombreCat(nombreCat);
        return ResponseEntity.ok(cat);
    }

    @PostMapping("/")
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria){
        if (categoria == null){
            return ResponseEntity.badRequest().build();
        }
        service.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Categoria> eliminarIdCategoria(@PathVariable Long id){
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.eliminar(service.findById(id));
        return ResponseEntity.ok().body(null);
    }


    @PutMapping("/")
    public ResponseEntity<Categoria> actualizarCategoria(@RequestBody Categoria cat){
        if (cat == null) {
            return ResponseEntity.badRequest().build();
        } else if (!service.existsById(cat.getIdCategoria())){
            return ResponseEntity.notFound().build();
        } 
        service.update(cat);
        return ResponseEntity.ok().body(cat);
    }
}
