package com.upc.sistemadecapacitaciononline.repositorio;

import com.upc.sistemadecapacitaciononline.entidades.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RolRepositorio extends CrudRepository <Rol, Long> {
    @Query("SELECT r FROM Rol r WHERE r.nombre=:nombre")
    public Rol buscarRolPorNombre(@Param("nombre") String nombre);
}
