package com.example.Cursos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.example.Cursos.Model.Categoria;
import com.example.Cursos.Service.CategoriaService;
import com.example.Cursos.assemblers.categoriaModelAssembler;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v2/categorias")
public class CategoriaControllerV2 {
    @Autowired
    private CategoriaService service;

    @Autowired
    private categoriaModelAssembler assembler;
    
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel <EntityModel<Categoria>> getAllCategorias() {
        List<EntityModel<Categoria>> categorias = service.findAll().stream()
                .map(assembler::toModel)
                .toList();
        
        return CollectionModel.of(categorias,
                linkTo(methodOn(CategoriaControllerV2.class).getAllCategorias()).withSelfRel());
    }

    @GetMapping(value = "/id/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Categoria> getCategoriaById(@PathVariable long id) {
        Categoria categoria = service.findById(id);
        if (categoria == null) {
            return null;
        }
        
        return assembler.toModel(categoria)
                .add(linkTo(methodOn(CategoriaControllerV2.class).getCategoriaById(id)).withSelfRel())
                .add(linkTo(methodOn(CategoriaControllerV2.class).getAllCategorias()).withRel("categorias"));
    }
    
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Categoria>> createCategoria(@RequestBody Categoria categoria) {
        
        Categoria createdCategoria = service.save(categoria);

        return ResponseEntity.created(linkTo(methodOn(CategoriaControllerV2.class).getCategoriaById(createdCategoria.getIdCategoria())).toUri())
                .body(assembler.toModel(createdCategoria)
                .add(linkTo(methodOn(CategoriaControllerV2.class).getCategoriaById(createdCategoria.getIdCategoria())).withSelfRel())
                .add(linkTo(methodOn(CategoriaControllerV2.class).getAllCategorias()).withRel("categorias")));
    }

    @PutMapping(value = "/id/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Categoria>> updateCategoria(@PathVariable long id, @RequestBody Categoria categoria) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        categoria.setIdCategoria(id);
        Categoria updatedCategoria = service.save(categoria);
        
        return ResponseEntity.ok(assembler.toModel(updatedCategoria)
                .add(linkTo(methodOn(CategoriaControllerV2.class).getCategoriaById(id)).withSelfRel())
                .add(linkTo(methodOn(CategoriaControllerV2.class).getAllCategorias()).withRel("categorias")));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Categoria>>> deleteCategoria(@PathVariable long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        service.deleteById(id);
        CollectionModel<EntityModel<Categoria>> links = CollectionModel.empty();
        links.add(linkTo(methodOn(CategoriaControllerV2.class).getAllCategorias()).withRel("categorias"));
        return ResponseEntity.noContent()
                .header("Link", linkTo(methodOn(CategoriaControllerV2.class).getAllCategorias()).withRel("categorias").toString())
                .build();
    }
}
