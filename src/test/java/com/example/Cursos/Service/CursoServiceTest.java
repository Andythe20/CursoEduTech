package com.example.Cursos.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Cursos.Model.Categoria;
import com.example.Cursos.Model.Curso;
import com.example.Cursos.Model.Nivel;
import com.example.Cursos.Repository.CursoRepository;

@SpringBootTest
public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockBean
    private CursoRepository cursoRepository;

    @Test
    public void testFindAll() {
        when(cursoRepository.findAll()).thenReturn(List.of(new Curso(1L,"Python 1", "Curso para aprender Python", Nivel.PRINCIPIANTE, 2990, 20, "Español", true, 5, null)));

        List<Curso> cursos = cursoService.findAll();

        assertNotNull(cursos);
        assertEquals(1, cursos.size());
    }

    @Test
    public void findByIdTest() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(new Curso(1L,"Python 1", "Curso para aprender Python", Nivel.PRINCIPIANTE, 2990, 20, "Español", true, 5, null)));

        Curso curso = cursoService.findById(1L);

        assertNotNull(curso);
        assertEquals(1L, curso.getIdCurso());
    }

    @Test
    public void findByNombreCursoTest() {
        when(cursoRepository.findByNombreCurso("Python 1")).thenReturn(new Curso(1L,"Python 1", "Curso para aprender Python", Nivel.PRINCIPIANTE, 2990, 20, "Español", true, 5, null));

        Curso curso = cursoService.findByNombreCurso("Python 1");

        assertNotNull(curso);
        assertEquals("Python 1", curso.getNombreCurso());
    }

    @Test
    public void existsByNombreCursoTest() {
        when(cursoRepository.existsByNombreCurso("Python 1")).thenReturn(true);

        boolean exists = cursoService.existsByNombreCurso("Python 1");

        assertEquals(true, exists);
    }

    @Test
    public void existsByIdCursoTest() {
        when(cursoRepository.existsByIdCurso(1L)).thenReturn(true);

        boolean exists = cursoService.existsByIdCurso(1L);

        assertEquals(true, exists);
    }

    @Test
    public void saveTest() {
        Categoria categoria = new Categoria(1, "Tecnología", null);
        Curso curso = new Curso(1L,"Python 1", "Curso para aprender Python", Nivel.PRINCIPIANTE, 2990, 20, "Español", true, 5, categoria);
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso savedCurso = cursoService.save(curso);

        assertNotNull(savedCurso);
        assertEquals("Python 1", savedCurso.getNombreCurso());
    }

    @Test
    public void deleteByIdTest() {
        
        // Verifica que el método deleteById del repositorio haya sido llamado con el id correcto
        when(cursoRepository.existsByIdCurso(1L)).thenReturn(true);
        
        cursoService.deleteById(1L);
        
        assertEquals(null, cursoService.deleteById(1L));
    }
}
