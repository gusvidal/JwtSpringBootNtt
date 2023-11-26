package com.gvidal.jwt.TechnicalTest.service;

import com.gvidal.jwt.TechnicalTest.entity.Telefono;
import com.gvidal.jwt.TechnicalTest.entity.Usuario;
import com.gvidal.jwt.TechnicalTest.repository.TelefonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TelefonoServiceImpl implements TelefonoService{
    @Autowired
    private TelefonoRepository telefonoRepository;
    @Override
    public List<Telefono> list() {
        return (List<Telefono>) telefonoRepository.findAll();
    }

    @Override
    public Optional<Telefono> listId(int id) {
        Optional <Telefono> fono = telefonoRepository.findById(id);
        return fono;
    }
    @Override
    public List<Telefono> listByUsuario(int idUsuario){
        List<Telefono> fonos = telefonoRepository.findTelefonoByUsuarioId(idUsuario);
        return fonos;
    }
}
