package com.example.Cursos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Cursos.Model.Curso;
import com.example.Cursos.Model.DTO.UserDTO;
import com.example.Cursos.Service.CursoService;
import com.example.Cursos.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Cursos", description = "Operaciones CRUD para gestionar cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Operation(summary = "Listar todos los cursos", description = "Obtiene una lista de todos los cursos disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida correctamente"),
        @ApiResponse(responseCode = "204", description = "No se encontraron cursos")
    })
    public ResponseEntity<List<Curso>> listar(){

        List<Curso> cursos = cursoService.findAll();

        if (cursos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Obtener curso por ID", description = "Obtiene un curso específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<Curso> listarCursoId(@PathVariable int id){

        if (cursoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Curso cursoEncontrado = cursoService.findById(id);
        return ResponseEntity.ok(cursoEncontrado);
        
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Obtener curso por nombre", description = "Obtiene un curso específico por su nombre")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<Curso> listarCursoNombre(@PathVariable String nombre){

        if (cursoService.findByNombreCurso(nombre) == null){
            return ResponseEntity.notFound().build();
        }
        Curso cursoEncontrado = cursoService.findByNombreCurso(nombre);
        return ResponseEntity.ok(cursoEncontrado);
        
    }

    @PostMapping("/")
    @Operation(summary = "Guardar nuevo curso", description = "Crea un nuevo curso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Curso creado correctamente",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso, @RequestParam(value = "idUsuario", required = false) Integer idUsuario){

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
            
        }
        cursoService.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);

    }
    
    @DeleteMapping("/id/{id}")
    @Operation(summary = "Eliminar curso por ID", description = "Elimina un curso específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<Curso> eliminar(@PathVariable Long id){

        if (!cursoService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(cursoService.deleteById(id));
        
    }

    @PutMapping("/")
    @Operation(summary = "Actualizar curso", description = "Actualiza un curso existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<Curso> actualizarCurso(@RequestBody Curso curso){

        if (curso == null) {
            return ResponseEntity.badRequest().build();
        }
        
        if (!cursoService.existsById(curso.getIdCurso())) {
            return ResponseEntity.notFound().build();
        } 

        cursoService.updateCurso(curso);
        return ResponseEntity.ok(cursoService.findById(curso.getIdCurso()));
    }

}
