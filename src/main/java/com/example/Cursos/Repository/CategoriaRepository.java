package com.example.Cursos.Repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.Cursos.Model.Categoria;

@Repository
public class CategoriaRepository {
    private ArrayList<Categoria> categorias = new ArrayList<>();

    //listar categorias
    public ArrayList<Categoria> listarCategorias(){
        return categorias;
    }

    //buscar por id
    public Categoria buscarIdCategoria(int id){
        for (Categoria c : categorias) {
            if (c.getIdCategoria() == id) {
                return c;
            }
        }
        return null;
    }

    //buscar por nombre
    public Categoria buscarNombreCategoria(String nombre){
        for (Categoria c : categorias) {
            if (c.getNombreCat().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    //actualizar
    public Categoria actualizarCategoria(Categoria categoria){
        for (Categoria c : categorias) {
            if (c.getIdCategoria() == categoria.getIdCategoria()) {
                c.setNombreCat(categoria.getNombreCat());
                return c;
            }
        }
        return null;
    }

    //eliminar
    public Categoria eliminarCategoria(int id){
        for (Categoria c : categorias) {
            if (c.getIdCategoria() == id) {
                categorias.remove(c);
                return null;
            }
        }
        return new Categoria();
    }

    //agregar
    public Categoria agregarCategoria(Categoria categoria){
        for (Categoria c : categorias) {
            if (c.getIdCategoria() == categoria.getIdCategoria()) {
                return null;
            }
        }
        categorias.add(categoria);
        return categoria;
    }

    

}
