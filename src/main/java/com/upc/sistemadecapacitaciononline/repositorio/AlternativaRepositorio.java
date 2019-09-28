package com.upc.sistemadecapacitaciononline.repositorio;

import com.upc.sistemadecapacitaciononline.entidades.Alternativa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AlternativaRepositorio extends CrudRepository<Alternativa, Long> {
    @Query("SELECT a FROM Alternativa a WHERE a.idAlternativa=:idAlternativa")
    public Alternativa buscarAlternativaPorId (@Param("idAlternativa") Long idAlternativa);
}
