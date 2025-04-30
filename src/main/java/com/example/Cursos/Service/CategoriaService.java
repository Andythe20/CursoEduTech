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
        return repositorio.findById(id).get();
    }

    public Categoria findByName(String nombre) {
        return repositorio.findByName(nombre);
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
            return null;
        }

        //verificar si la categoria ya existe
        List<Categoria> categorias = repositorio.findAll();
        for (Categoria c : categorias) {
            if (c.getIdCategoria() == categoria.getIdCategoria()) {
                return null;
            }
        }
        //guardar la categoria
        return repositorio.save(categoria);
    }
}
