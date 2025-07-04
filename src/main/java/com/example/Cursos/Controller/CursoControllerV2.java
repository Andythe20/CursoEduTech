package com.example.Cursos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.Cursos.Service.CursoService;
import com.example.Cursos.Service.UserService;

import java.util.List;
import com.example.Cursos.Model.Curso;
import com.example.Cursos.Model.DTO.UserDTO;
import com.example.Cursos.assemblers.cursoModelAssembler;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private cursoModelAssembler assembler;

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Curso>> getAllCursos() {
        List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
                .map(assembler::toModel)
                .toList();
        
        return CollectionModel.of(cursos,
                linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withSelfRel());
    }

    @GetMapping(value = "/id/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Curso> getCursoById(@PathVariable long id) {
        Curso curso = cursoService.findById(id);
        if (curso == null) {
            return null;
        }
        
        return assembler.toModel(curso)
                .add(linkTo(methodOn(CursoControllerV2.class).getCursoById(id)).withSelfRel())
                .add(linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withRel("cursos"));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody Curso curso, @RequestParam(value = "idUsuario", required = false) Integer idUsuario) {
        if (curso == null){
            return ResponseEntity.badRequest().build();
        }

        if (idUsuario != null) {
            try {
                UserDTO user = userService.getUserById(idUsuario);
                curso.setIdInstructor(user.getIdUsuario());
                curso.setNombreInstructor(user.getNombreUsuario() + " " + user.getApellidoUsuario());
                
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
            // Aquí podrías agregar lógica para buscar el usuario por idUsuario si es necesario
        }
        
        cursoService.save(curso);
        return ResponseEntity.created(linkTo(methodOn(CursoControllerV2.class).getCursoById(curso.getIdCurso())).toUri())
                .body(assembler.toModel(curso)
                .add(linkTo(methodOn(CursoControllerV2.class).getCursoById(curso.getIdCurso())).withSelfRel())
                .add(linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withRel("cursos")));
    }

    @PutMapping(value = "/id/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable long id, @RequestBody Curso curso) {
        
        if (!cursoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        curso.setIdCurso(id);
        Curso updatedCurso = cursoService.save(curso);
        
        return ResponseEntity.ok(assembler.toModel(updatedCurso)
                .add(linkTo(methodOn(CursoControllerV2.class).getCursoById(id)).withSelfRel())
                .add(linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withRel("cursos")));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCurso(@PathVariable long id) {
        if (!cursoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        cursoService.deleteById(id);
        CollectionModel<EntityModel<Curso>> links = CollectionModel.empty();
        links.add(linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withRel("cursos"));
        return ResponseEntity.noContent()
                .header("Link", linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withRel("cursos").toString())
                .build();
    }
}
