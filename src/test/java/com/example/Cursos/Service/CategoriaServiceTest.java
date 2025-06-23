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
import com.example.Cursos.Repository.CategoriaRepository;

@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @MockBean
    private CategoriaRepository categoriaRepository;

    @Test
    public void testFindAll() {

        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Categoria.
        when(categoriaRepository.findAll()).thenReturn(List.of(new Categoria(1, "Tecnología", null)));

        // Llama al método findAll() del servicio.
        List<Categoria> categorias = categoriaService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Categoria.
        assertNotNull(categorias);
        assertEquals(1, categorias.size());
    }

    @Test
    public void findByIdTest(){

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Categoria opcional.
        when(categoriaRepository.findById((long) 1)).thenReturn(Optional.of(new Categoria(1, "Tecnología", null)));

        // Llama al método findById() del servicio.
        Categoria categoria = categoriaService.findById(1);

        // Verifica que la Categoria devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(categoria);
        assertEquals(1, categoria.getIdCategoria());
    }

    @Test
    public void findByNombreCatTest(){

        when(categoriaRepository.findByNombreCat("Tecnología")).thenReturn(new Categoria(1, "Tecnología", null));

        Categoria categoria = categoriaService.findByNombreCat("Tecnología");

        assertNotNull(categoria);
        assertEquals("Tecnología", categoria.getNombreCat());
    }

    @Test
    public void existsByIdTest() {

        // Define el comportamiento del mock: cuando se llame a existsById() con "1", devuelve true.
        when(categoriaRepository.existsById((long) 1)).thenReturn(true);

        // Llama al método existsById() del servicio.
        boolean exists = categoriaService.existsById(1);

        // Verifica que el resultado sea true.
        assertEquals(true, exists);
    }

    @Test
    public void existsByNombreCatTest() {

        // Define el comportamiento del mock: cuando se llame a existsByNombreCat() con "Tecnología", devuelve true.
        when(categoriaRepository.existsByNombreCat("Tecnología")).thenReturn(true);

        // Llama al método existsByNombreCat() del servicio.
        boolean exists = categoriaService.existsByNombreCat("Tecnología");

        // Verifica que el resultado sea true.
        assertEquals(true, exists);
    }

    @Test
    public void saveTest() {

        // Crea una nueva Categoria para guardar.
        Categoria categoria = new Categoria(1, "Tecnología", null);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la misma Categoria.
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        // Llama al método save() del servicio.
        Categoria savedCategoria = categoriaService.save(categoria);

        // Verifica que la Categoria guardada no sea nula y que su nombre coincida con el esperado.
        assertNotNull(savedCategoria);
        assertEquals("Tecnología", savedCategoria.getNombreCat());
    }

    @Test
    public void deleteByIdTest() {

        // Define el comportamiento del mock: cuando se llame a deleteById() con "1", no hace nada.
        when(categoriaRepository.existsById((long) 1)).thenReturn(true);

        // Llama al método deleteById() del servicio.
        categoriaService.deleteById(1L);

        // Verifica que el método deleteById() se haya llamado correctamente.
        assertEquals(true, categoriaService.deleteById(1L));
    }

    @Test
    public void isValidCategoriaTest() {

        // Crea una Categoria con un nombre válido.
        Categoria categoria = new Categoria(1, "Tecnología", null);

        // Verifica que la Categoria sea válida.
        boolean isValid = categoriaService.isValidCategoria(categoria);

        // Verifica que el resultado sea false (la Categoria es válida).
        assertEquals(false, isValid);
    }
}
