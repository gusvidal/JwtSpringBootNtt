package com.gvidal.jwt.TechnicalTest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gvidal.jwt.TechnicalTest.entity.Telefono;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class UsuarioDto {
    int id;
    String nombre;
    String correo;
    String password;
    String fechaCreacion;
    String fechaLogin;
    String token;
    List<Telefono> fonos;

    public UsuarioDto(int id, String nombre, String correo, String password, String fechaCreacion,String fechaLogin,String token,List<Telefono> fonos){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.fechaCreacion = fechaCreacion;
        this.fechaLogin = fechaLogin;
        this.token = token;
        this.fonos = fonos;
    }
}
