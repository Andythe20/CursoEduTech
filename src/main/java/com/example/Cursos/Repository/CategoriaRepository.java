package com.example.Cursos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Cursos.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    Categoria findByNombreCat(String nombreCat);
    boolean existsByNombreCat(String nombreCat);

}
