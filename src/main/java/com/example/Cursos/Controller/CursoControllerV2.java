package com.example.Cursos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.Cursos.Service.CursoService;

import java.util.List;
import java.util.stream.Collectors;
import com.example.Cursos.Model.Curso; 
import com.example.Cursos.assemblers.cursoModelAssembler;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private cursoModelAssembler assembler;

    
}
