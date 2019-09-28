package com.upc.sistemadecapacitaciononline.repositorio;

import com.upc.sistemadecapacitaciononline.entidades.Pregunta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PreguntaRepositorio extends CrudRepository<Pregunta, Long> {
    @Query("SELECT p FROM Pregunta p WHERE p.idPregunta like '%' + descripcion + '%'")
    public Pregunta buscarPreguntaPorNombre(@Param("descripcion") String descripcion);

    @Query("SELECT p FROM Pregunta p WHERE p.idPregunta=:idPregunta")
    public Pregunta buscarPreguntaPorId(@Param("idPregunta") Long idPregunta);
}
