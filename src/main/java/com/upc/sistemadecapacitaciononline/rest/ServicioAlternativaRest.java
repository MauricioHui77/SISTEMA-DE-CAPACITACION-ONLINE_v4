package com.upc.sistemadecapacitaciononline.rest;

import com.upc.sistemadecapacitaciononline.entidades.Alternativa;
import com.upc.sistemadecapacitaciononline.serviciosDAO.ServicioAlternativaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alternativa")
public class ServicioAlternativaRest {
    @Autowired
    private ServicioAlternativaDAO servicioAlternativaDAO;

    @PostMapping("/registrar/{idPregunta}")
    public Alternativa registrar (@PathVariable(name = "idPregunta") Long idPregunta, @RequestBody Alternativa alternativa) {
        return servicioAlternativaDAO.registrar(idPregunta, alternativa);
    }

    @PostMapping("/registrarAlternativas/{idPregunta}")
    public List<Alternativa> registrarPreguntas (@PathVariable(name = "idPregunta") Long idPregunta, @RequestBody List<Alternativa> alternativas) {
        return servicioAlternativaDAO.registrarAlternativas(idPregunta, alternativas);
    }
}
