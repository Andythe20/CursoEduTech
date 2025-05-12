package com.example.Cursos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Cursos.Model.Categoria;
import com.example.Cursos.Repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository repositorio;

    public List<Categoria> findAll() {
        return (List<Categoria>) repositorio.findAll();
    }

    public Categoria findById(long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    public Categoria findByName(String nombreCat) {
        return repositorio.findByNombreCat(nombreCat);
    }

    //verificar la categoria
    public boolean isValidCategoria(Categoria c) {
        if (c.getNombreCat() != null && !c.getNombreCat().isEmpty()) {
            return true;
        }
        return false;
    }
    
    public Categoria save(Categoria categoria) {
        //validar la categoria
        if (!isValidCategoria(categoria)) {
            //return null;
            throw new IllegalArgumentException("Categoria no valida");
        }

        //guardar la categoria
        return repositorio.save(categoria);
    }

    //eliminar categoria
    public void eliminar(Categoria cat){
        repositorio.delete(cat);
    }

    public Categoria update(Categoria categoria) {
        //validar la categoria
        if (!repositorio.existsById(categoria.getIdCategoria())) {
            //return null;
            throw new IllegalArgumentException("Categoria no valida");
        }

        //guardar la categoria
        return repositorio.save(categoria);
    }
}
