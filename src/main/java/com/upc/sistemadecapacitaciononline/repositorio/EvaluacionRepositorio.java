package com.upc.sistemadecapacitaciononline.repositorio;

import com.upc.sistemadecapacitaciononline.entidades.Evaluacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluacionRepositorio extends CrudRepository<Evaluacion, Long> {
    @Query("SELECT e FROM Evaluacion e WHERE e.descripcion=:descripcion")
    public Evaluacion buscarEvaluacionPorNombre(@Param("descripcion") String descripcion);

    @Query("SELECT e FROM Evaluacion e WHERE e.idEvaluacion=:idEvaluacion")
    public Evaluacion buscarEvaluacionPorId(@Param("idEvaluacion") Long idEvaluacion);

    @Query("SELECT e FROM Evaluacion e")
    public List<Evaluacion> obtenerEvaluaciones ();
}
