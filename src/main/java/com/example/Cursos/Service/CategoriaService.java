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

    //listar todas las categorias
    public List<Categoria> findAll() {
        return (List<Categoria>) repositorio.findAll();
    }

    //listar categoria por id
    public Categoria findById(long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    //listar categoria por nombre
    public Categoria findByNombreCat(String nombreCat) {
        return repositorio.findByNombreCat(nombreCat);
    }

    //verificar si existe la categoria por id
    public boolean existsById(long id){
        return repositorio.existsById(id);
    }

    //verificar si existe la categoria por nombre
    public boolean existsByNombreCat(String nombreCat){
        return repositorio.existsByNombreCat(nombreCat);
    }

    //verificar si la categoria es valida
    public boolean isValidCategoria(Categoria c) {
        if (c.getNombreCat() != null && !c.getNombreCat().isEmpty()) {
            return true;
        }
        return false;
    }

    //guardar categoria
    public Categoria save(Categoria categoria) {
        //validar la categoria
        if (!isValidCategoria(categoria)) {
            throw new IllegalArgumentException("Categoria no valida");
        }

        return repositorio.save(categoria);
    }

    //eliminar categoria
    public void eliminar(Categoria cat){
        repositorio.delete(cat);
    }

    //actualizar categoria
    public Categoria update(Categoria categoria) {

        if (!repositorio.existsById(categoria.getIdCategoria())) {
            throw new IllegalArgumentException("Categoria no valida");
        }
        

        return repositorio.save(categoria);
    }
}
