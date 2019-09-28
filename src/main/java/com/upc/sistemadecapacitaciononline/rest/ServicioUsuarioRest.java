package com.upc.sistemadecapacitaciononline.rest;

import com.upc.sistemadecapacitaciononline.entidades.Usuario;
import com.upc.sistemadecapacitaciononline.serviciosDAO.ServicioUsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class ServicioUsuarioRest {
    @Autowired
    private ServicioUsuarioDAO servicioUsuarioDAO;

    @PostMapping("/registrar/{nombre}")
    public Usuario registrar (@PathVariable(name = "nombre") String nombre, @RequestBody Usuario usuario) {
        Usuario u;
        try {
            u = servicioUsuarioDAO.registrar(nombre, usuario);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo registrar al usuario");
        }
        return u;
    }

    @GetMapping("/obtenerAccesos/{username}/{password}")
    public Usuario tieneAccesos (@PathVariable(value = "username") String username,
                                 @PathVariable(value = "password") String password ) {
        return servicioUsuarioDAO.usuarioTieneAccesos(username, password);
    }

    @GetMapping("/obtenerUsuariosPorRol/{idRol}")
    public List<Usuario> obtenerUsuariosPorRol (@PathVariable(name = "idRol") Long idRol) {
        return servicioUsuarioDAO.obtenerUsuariosPorRol(idRol);
    }
}
