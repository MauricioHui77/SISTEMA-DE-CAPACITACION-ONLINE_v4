package com.upc.sistemadecapacitaciononline.rest;

import com.upc.sistemadecapacitaciononline.entidades.Pregunta;
import com.upc.sistemadecapacitaciononline.serviciosDAO.ServicioPreguntaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pregunta")
public class ServicioPreguntaRest {
    @Autowired
    private ServicioPreguntaDAO servicioPreguntaDAO;

    @PostMapping("/registrar/{evaluacion}")
    public Pregunta registrar (@PathVariable(name = "evaluacion") String evaluacion, @RequestBody Pregunta pregunta) {
        return servicioPreguntaDAO.registrar(evaluacion, pregunta);
    }

    @PostMapping("/registrarPreguntas/{evaluacion}")
    public List<Pregunta> registrarPreguntas (@PathVariable(name = "evaluacion") Long idEvaluacion, @RequestBody List<Pregunta> preguntas) {
        return servicioPreguntaDAO.registrarPreguntas(idEvaluacion, preguntas);
    }
}
