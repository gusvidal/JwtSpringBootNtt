package com.gvidal.jwt.TechnicalTest.repository;

import com.gvidal.jwt.TechnicalTest.entity.Telefono;
import com.gvidal.jwt.TechnicalTest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findByEmail(String email);
}
