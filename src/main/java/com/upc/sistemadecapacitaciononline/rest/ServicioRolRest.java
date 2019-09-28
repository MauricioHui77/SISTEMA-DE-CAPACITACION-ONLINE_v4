package com.upc.sistemadecapacitaciononline.rest;

import com.upc.sistemadecapacitaciononline.entidades.Rol;
import com.upc.sistemadecapacitaciononline.serviciosDAO.ServicioRolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/rol")
public class ServicioRolRest {

    @Autowired
    private ServicioRolDAO servicioRolDAO;

    @PostMapping("/registrar")
    public Rol registrar (@RequestBody Rol rol) {
        Rol r;
        try {
            r = servicioRolDAO.registrar(rol);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo registrar al usuario");
        }
        return r;
    }
}
