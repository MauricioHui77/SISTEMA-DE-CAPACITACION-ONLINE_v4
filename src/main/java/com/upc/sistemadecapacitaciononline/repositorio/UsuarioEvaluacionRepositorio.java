package com.upc.sistemadecapacitaciononline.repositorio;

import com.upc.sistemadecapacitaciononline.entidades.UsuarioEvaluacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioEvaluacionRepositorio extends CrudRepository <UsuarioEvaluacion, Long> {
    @Query("SELECT ue FROM UsuarioEvaluacion ue WHERE ue.usuario.idUsuario=:idUsuario")
    public List<UsuarioEvaluacion> obtenerEvaluacionesPorUsuario (@Param("idUsuario") Long idUsuario);
}
