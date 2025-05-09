package com.example.Cursos.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private long idCurso;

    @Column(nullable=false, unique = true)
    private String nombreCurso;
    
    @Column(nullable=false)
    private String descripcion;
    
    @Column(nullable=false)
    private Nivel nivel;

    @Column(length = 5, nullable=false)
    private int precio;

    @Column(nullable=false)
    private int duracionHoras;

    @Column(nullable=false)
    private String idioma;

    @Column(nullable=false)
    private boolean certificadoDisponible;
    
    @Column(nullable=false)
    private long idInstructor;
    
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

}
