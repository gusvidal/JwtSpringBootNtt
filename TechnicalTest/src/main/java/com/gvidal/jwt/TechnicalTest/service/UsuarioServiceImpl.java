package com.gvidal.jwt.TechnicalTest.service;

import com.gvidal.jwt.TechnicalTest.entity.Usuario;
import com.gvidal.jwt.TechnicalTest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> list() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> listId(int id) {
         Optional <Usuario> usr = usuarioRepository.findById(id);
         return usr;
    }

    @Override
    public int save(Usuario usuario) {
        int result=0;
        Usuario usr=usuarioRepository.save(usuario);
        if(!usr.equals(null)) {
            result=1;
        }
        return result;
    }

    @Override
    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
