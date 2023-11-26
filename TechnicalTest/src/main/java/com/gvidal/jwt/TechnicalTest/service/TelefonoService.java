package com.gvidal.jwt.TechnicalTest.service;

import com.gvidal.jwt.TechnicalTest.entity.Telefono;
import com.gvidal.jwt.TechnicalTest.entity.Usuario;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TelefonoService {
    public List<Telefono> list();

    public Optional<Telefono> listId(int id);

    public List<Telefono> listByUsuario(int idUsuario);

}
