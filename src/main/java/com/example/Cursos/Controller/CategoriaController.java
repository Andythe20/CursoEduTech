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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Operaciones CRUD para gestionar categorías de cursos")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    
    @GetMapping("/")
    @Operation(summary = "Listar todas las categorías", description = "Obtiene una lista de todas las categorías disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida correctamente"),
        @ApiResponse(responseCode = "204", description = "No se encontraron categorías")
    })
    public ResponseEntity<List<Categoria>> listar(){
        List<Categoria> categorias = service.findAll();
        if (categorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Obtener categoría por ID", description = "Obtiene una categoría específica por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Categoria> listarIdCategoria(@PathVariable long id){
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/nombre/{nombreCat}")
    @Operation(summary = "Obtener categoría por nombre", description = "Obtiene una categoría específica por su nombre")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Categoria> listarNombreCategoria(@PathVariable String nombreCat){
        if (!service.existsByNombreCat(nombreCat)) {
            return ResponseEntity.notFound().build();
        }
        Categoria cat = service.findByNombreCat(nombreCat);
        return ResponseEntity.ok(cat);
    }

    @PostMapping("/")
    @Operation(summary = "Guardar nueva categoría", description = "Crea una nueva categoría")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoría creada correctamente",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = Categoria.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria){
        if (categoria == null){
            return ResponseEntity.badRequest().build();
        }

        if (service.isValidCategoria(categoria)){
            return ResponseEntity.badRequest().build();    
        }

        service.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/id/{id}")
    @Operation(summary = "Eliminar categoría por ID", description = "Elimina una categoría específica por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Categoria> eliminarIdCategoria(@PathVariable Long id){
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(service.deleteById(id));
    }


    @PutMapping("/")
    @Operation(summary = "Actualizar categoría", description = "Actualiza una categoría existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = Categoria.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Categoria> actualizarCategoria(@RequestBody Categoria cat){
        if (cat == null) {
            return ResponseEntity.badRequest().build();
        } 
        
        if (!service.existsById(cat.getIdCategoria())){
            return ResponseEntity.notFound().build();
        }
        
        if (service.isValidCategoria(cat)){
            return ResponseEntity.badRequest().build();
            
        }

        service.save(cat);
        return ResponseEntity.ok().body(cat);
    }
}
