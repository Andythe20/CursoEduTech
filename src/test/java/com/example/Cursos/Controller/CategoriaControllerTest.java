package com.example.Cursos.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Cursos.Model.Categoria;
import com.example.Cursos.Service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Categoria categoria;

    @BeforeEach
    void SetUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNombreCat("Tecnología");
        
    }

    @Test
    public void testFindAll() throws Exception {
        when(categoriaService.findAll()).thenReturn(List.of(categoria));

        mockMvc.perform(get("/api/v1/categorias/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idCategoria").value(1))
                .andExpect(jsonPath("$[0].nombreCat").value("Tecnología"));
    }

    @Test
    public void testFindById() throws Exception {
        when(categoriaService.existsById(1L)).thenReturn(true);
        when(categoriaService.findById(1L)).thenReturn(categoria);

        mockMvc.perform(get("/api/v1/categorias/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategoria").value(1))
                .andExpect(jsonPath("$.nombreCat").value("Tecnología"));
    }

    @Test
    public void testFindByNombreCat() throws Exception {
        when(categoriaService.existsByNombreCat("Tecnología")).thenReturn(true);
        when(categoriaService.findByNombreCat("Tecnología")).thenReturn(categoria);

        mockMvc.perform(get("/api/v1/categorias/nombre/Tecnología"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategoria").value(1))
                .andExpect(jsonPath("$.nombreCat").value("Tecnología"));
    }

    @Test
    public void testSave() throws Exception {
        when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        mockMvc.perform(post("/api/v1/categorias/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategoria").value(1))
                .andExpect(jsonPath("$.nombreCat").value("Tecnología"));

    }

    @Test
    public void testDeleteById() throws Exception {
        when(categoriaService.existsById(1L)).thenReturn(true);
        when(categoriaService.deleteById(1L)).thenReturn(null);
        
        mockMvc.perform(delete("/api/v1/categorias/id/1"))
                .andExpect(status().isOk());
        
        verify(categoriaService, times(1)).deleteById(1L);
    }

    
}
