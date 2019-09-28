package com.upc.sistemadecapacitaciononline.serviciosDAO;

import com.upc.sistemadecapacitaciononline.entidades.Rol;
import com.upc.sistemadecapacitaciononline.repositorio.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServicioRolDAO {
    @Autowired
    private RolRepositorio rolRepositorio;

    public Rol registrar (Rol rol) {
        Rol r;
        try {
            r = rolRepositorio.save(rol);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear el usuario", ex);
        }
        return r;
    }

    public Rol buscarRolPorNombre(String nombre) {
        Rol r;
        try {
            r = rolRepositorio.buscarRolPorNombre(nombre);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear el usuario", ex);
        }
        return r;
    }
}
