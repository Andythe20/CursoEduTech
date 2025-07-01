package com.example.Cursos.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correoUsuario;

}
