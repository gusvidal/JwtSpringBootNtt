package com.gvidal.jwt.TechnicalTest.repository;

import com.gvidal.jwt.TechnicalTest.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TelefonoRepository extends JpaRepository<Telefono, Integer> {

    @Query(value="select t.id, t.usuario_id, t.numero, t.codigo_ciudad, t.codigo_pais from Telefono t where t.usuario_id = :idUsuario", nativeQuery=true)
    public List<Telefono> findTelefonoByUsuarioId(@Param("idUsuario") int idUsuario);
}
