package com.gvidal.jwt.TechnicalTest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nombre;
    @Email(message = "El e-mail no posee formato correcto", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "El e-mail no puede estar vacio")
    String email;
    String password;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    String fechaCreacion;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    String fechaLogin;
    String token;
}
