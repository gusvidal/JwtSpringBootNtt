package com.gvidal.jwt.TechnicalTest.service;

import com.gvidal.jwt.TechnicalTest.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public List<Usuario> list();

    public Optional<Usuario> listId(int id);

    public int save(Usuario usuario);

    public void delete(int id);

    public Usuario findByEmail(String email);
}
