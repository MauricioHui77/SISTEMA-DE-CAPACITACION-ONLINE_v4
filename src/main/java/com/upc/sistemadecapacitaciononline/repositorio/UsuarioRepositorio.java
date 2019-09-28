package com.upc.sistemadecapacitaciononline.repositorio;

import com.upc.sistemadecapacitaciononline.entidades.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepositorio extends CrudRepository <Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.idUsuario=:idUsuario")
    public Usuario buscarUsuarioPorId(@Param("idUsuario") Long idUsuario);

    @Query("SELECT u FROM Usuario u WHERE u.username=:username")
    public Usuario buscarUsuarioPorCorreo(@Param("username") String username);

    @Query("SELECT u FROM Usuario u WHERE u.idRol.id=:idRol")
    public List<Usuario> buscarUsuariosPorRol(@Param("idRol") Long idRol);
}
