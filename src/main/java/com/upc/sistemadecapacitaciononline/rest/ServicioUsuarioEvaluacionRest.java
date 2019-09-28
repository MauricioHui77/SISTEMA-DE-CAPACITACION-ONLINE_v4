package com.upc.sistemadecapacitaciononline.rest;

import com.upc.sistemadecapacitaciononline.corenegocio.ServicioUsuarioEvaluacionCore;
import com.upc.sistemadecapacitaciononline.entidades.Resultado;
import com.upc.sistemadecapacitaciononline.entidades.UsuarioEvaluacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarioEvaluacion")
public class ServicioUsuarioEvaluacionRest {
    @Autowired
    private ServicioUsuarioEvaluacionCore servicioUsuarioEvaluacionCore;

    @PostMapping("/asignarEvaluacion/{idEvaluacion}/{idUsuario}")
    public UsuarioEvaluacion asignarEvaluacion (Long idEvaluacion, Long idUsuario) {
        return servicioUsuarioEvaluacionCore.registrarAsignacion(idEvaluacion, idUsuario);
    }

    @PostMapping("/registrarCalificacion/{idEvaluacion}/{idUsuario}")
    public UsuarioEvaluacion registrarCalificacion (Long idEvaluacion, Long idUsuario, @RequestBody List<Resultado> resultados) {
        return servicioUsuarioEvaluacionCore.registrarCalificacion(idEvaluacion, idUsuario, resultados);
    }
}
