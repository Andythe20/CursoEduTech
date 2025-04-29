package com.example.Cursos.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Cursos.Model.Categoria;
import com.example.Cursos.Repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repositorio;

    public ArrayList<Categoria> listarCategorias() {
        return repositorio.listarCategorias();
    }

    public Categoria buscarIdCategoria(int id) {
        return repositorio.buscarIdCategoria(id);
    }

    public Categoria buscarNombreCategoria(String nombre) {
        return repositorio.buscarNombreCategoria(nombre);
    }

    public Categoria actualizarCategoria(Categoria categoria) {
        return repositorio.actualizarCategoria(categoria);
    }

    public Categoria eliminarCategoria(int id) {
        return repositorio.eliminarCategoria(id);
    }

    public Categoria agregarCategoria(Categoria categoria) {
        return repositorio.agregarCategoria(categoria);
    }
    
    
}
