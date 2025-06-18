package com.example.Cursos;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.Cursos.Model.Categoria;
import com.example.Cursos.Model.Curso;
import com.example.Cursos.Model.Nivel;
import com.example.Cursos.Repository.CategoriaRepository;
import com.example.Cursos.Repository.CursoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Profile("test") // Solo se ejecutará en el perfil 'dev'
public class DataLoader implements CommandLineRunner {

    // declarar los repositorios
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {

        List<String> categorias = List.of(
                "Programación",
                "Diseño Gráfico",
                "Marketing Digital",
                "Ciencia de Datos",
                "Desarrollo Web",
                "Inteligencia Artificial",
                "Finanzas Personales",
                "Fotografía",
                "Idiomas",
                "Salud y Bienestar"

        );

        // declarar Faker y random
        Faker faker = new Faker();
        Random random = new Random();

        // generar categorias
        for (int i = 0; i < 10; i++) {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(i + 1);
            categoria.setNombreCat(categorias.get(i));
            categoriaRepository.save(categoria);
        }

        // Obtener la lista de categorias desde el repositorio
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        
        // Obtener los valores del enum y convertirlos a List
        List<Nivel> listaNiveles = Arrays.asList(Nivel.values());
        
        //crear idiomas
        List<String> idiomas = List.of("Español", "Inglés", "Francés", "Alemán", "Italiano", "Portugués", "Chino", "Japonés", "Ruso", "Árabe");

        //crear nombres de cursos
        List<String> nombresCursos = List.of(
                "Curso de Programación Avanzada",
                "Diseño Gráfico para Principiantes",
                "Marketing Digital: Estrategias Efectivas",
                "Ciencia de Datos con Python",
                "Desarrollo Web Full Stack",
                "Inteligencia Artificial y Machine Learning",
                "Finanzas Personales para Jóvenes",
                "Fotografía Profesional: Técnicas y Consejos",
                "Aprende Inglés Rápidamente",
                "Salud y Bienestar Integral"
        );
        
        // generar cursos
        for (int i = 0; i < 10; i++) {
            Curso curso = new Curso();
            curso.setIdCurso(i + 1);
            curso.setNombreCurso(nombresCursos.get(i));
            curso.setDescripcion(faker.lorem().paragraph());
            curso.setNivel(listaNiveles.get(random.nextInt(listaNiveles.size())));
            curso.setPrecio(faker.number().numberBetween(2999, 50001));
            curso.setDuracionHoras(faker.number().numberBetween(1, 100));
            curso.setIdioma(idiomas.get(random.nextInt(idiomas.size())));
            curso.setCertificadoDisponible(faker.bool().bool());
            curso.setIdInstructor(faker.number().numberBetween(1, 1000));
            curso.setCategoria(listaCategorias.get(random.nextInt(listaCategorias.size())));
            cursoRepository.save(curso);
        }

    }

}