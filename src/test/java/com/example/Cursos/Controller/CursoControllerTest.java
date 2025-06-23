package com.example.Cursos.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Cursos.Model.Categoria;
import com.example.Cursos.Model.Curso;
import com.example.Cursos.Model.Nivel;
import com.example.Cursos.Service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private  MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Curso curso;
    private Categoria categoria;

    @BeforeEach
    void SetUp() {
        categoria = new Categoria(1L, "Tecnología", null);

        curso = new Curso();
        curso.setIdCurso(1L);
        curso.setNombreCurso("Python 1");
        curso.setDescripcion("Python para principiantes");
        curso.setNivel(Nivel.PRINCIPIANTE);
        curso.setPrecio(5000);
        curso.setDuracionHoras(20);
        curso.setIdioma("Español");
        curso.setCertificadoDisponible(false);
        curso.setIdInstructor(5L);
        curso.setCategoria(categoria);
    }

    @Test
    public void testFindAll() throws Exception {
        when(cursoService.findAll()).thenReturn(List.of(curso));

        mockMvc.perform(get("/api/v1/cursos/"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].idCurso").value(1))
                        .andExpect(jsonPath("$[0].nombreCurso").value("Python 1"))
                        .andExpect(jsonPath("$[0].descripcion").value("Python para principiantes"))
                        .andExpect(jsonPath("$[0].nivel").value(Nivel.PRINCIPIANTE.toString()))
                        .andExpect(jsonPath("$[0].precio").value(5000))
                        .andExpect(jsonPath("$[0].duracionHoras").value(20))
                        .andExpect(jsonPath("$[0].idioma").value("Español"))
                        .andExpect(jsonPath("$[0].certificadoDisponible").value(false))
                        .andExpect(jsonPath("$[0].idInstructor").value(5))
                        .andExpect(jsonPath("$[0].categoria.idCategoria").value(1));
    }

    @Test
    public void testFindById() throws Exception {
        when(cursoService.existsById(1L)).thenReturn(true);
        when(cursoService.findById(1L)).thenReturn(curso);

        mockMvc.perform(get("/api/v1/cursos/id/1"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.idCurso").value(1))
                        .andExpect(jsonPath("$.nombreCurso").value("Python 1"))
                        .andExpect(jsonPath("$.descripcion").value("Python para principiantes"))
                        .andExpect(jsonPath("$.nivel").value(Nivel.PRINCIPIANTE.toString()))
                        .andExpect(jsonPath("$.precio").value(5000))
                        .andExpect(jsonPath("$.duracionHoras").value(20))
                        .andExpect(jsonPath("$.idioma").value("Español"))
                        .andExpect(jsonPath("$.certificadoDisponible").value(false))
                        .andExpect(jsonPath("$.idInstructor").value(5))
                        .andExpect(jsonPath("$.categoria.idCategoria").value(1));
    }

    @Test
    public void testFindByNombreCurso() throws Exception {
        when(cursoService.existsByNombreCurso("Python 1")).thenReturn(true);
        when(cursoService.findByNombreCurso("Python 1")).thenReturn(curso);

        mockMvc.perform(get("/api/v1/cursos/nombre/Python 1"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.idCurso").value(1))
                        .andExpect(jsonPath("$.nombreCurso").value("Python 1"))
                        .andExpect(jsonPath("$.descripcion").value("Python para principiantes"))
                        .andExpect(jsonPath("$.nivel").value(Nivel.PRINCIPIANTE.toString()))
                        .andExpect(jsonPath("$.precio").value(5000))
                        .andExpect(jsonPath("$.duracionHoras").value(20))
                        .andExpect(jsonPath("$.idioma").value("Español"))
                        .andExpect(jsonPath("$.certificadoDisponible").value(false))
                        .andExpect(jsonPath("$.idInstructor").value(5))
                        .andExpect(jsonPath("$.categoria.idCategoria").value(1));
    }

    @Test
    public void testSave() throws Exception {
        when(cursoService.save(curso)).thenReturn(curso);

        mockMvc.perform(post("/api/v1/cursos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCurso").value(1))
                .andExpect(jsonPath("$.nombreCurso").value("Python 1"))
                .andExpect(jsonPath("$.descripcion").value("Python para principiantes"))
                .andExpect(jsonPath("$.nivel").value(Nivel.PRINCIPIANTE.toString()))
                .andExpect(jsonPath("$.precio").value(5000))
                .andExpect(jsonPath("$.duracionHoras").value(20))
                .andExpect(jsonPath("$.idioma").value("Español"))
                .andExpect(jsonPath("$.certificadoDisponible").value(false))
                .andExpect(jsonPath("$.idInstructor").value(5))
                .andExpect(jsonPath("$.categoria.idCategoria").value(1));
    }

    @Test
    public void testDeleteById() throws Exception {
        when(cursoService.existsById(1L)).thenReturn(true);
        when(cursoService.deleteById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/cursos/id/1"))
                .andExpect(status().isOk());
        

    }
}
