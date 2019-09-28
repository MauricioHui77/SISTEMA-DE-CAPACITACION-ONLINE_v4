package com.upc.sistemadecapacitaciononline.serviciosDAO;

import com.upc.sistemadecapacitaciononline.entidades.Alternativa;
import com.upc.sistemadecapacitaciononline.entidades.Pregunta;
import com.upc.sistemadecapacitaciononline.repositorio.AlternativaRepositorio;
import com.upc.sistemadecapacitaciononline.repositorio.PreguntaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServicioAlternativaDAO {
    @Autowired
    private AlternativaRepositorio alternativaRepositorio;
    @Autowired
    private PreguntaRepositorio preguntaRepositorio;

    public Alternativa registrar (Long idPregunta, Alternativa alternativa) {
        Alternativa a;

        try {
            Pregunta pregunta = preguntaRepositorio.buscarPreguntaPorId(idPregunta);
            alternativa.setIdPregunta(pregunta);
            a = alternativaRepositorio.save(alternativa);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo crear la alternativa", e);
        }
        return a;
    }

    public List<Alternativa> registrarAlternativas (Long idPregunta, List<Alternativa> alternativas) {
        List<Alternativa> alternativaList = null;
        Alternativa a;
        try {
            Pregunta pregunta = preguntaRepositorio.buscarPreguntaPorId(idPregunta);
            for (Alternativa alternativa:alternativas) {
                alternativa.setIdPregunta(pregunta);
                a = alternativaRepositorio.save(alternativa);
            }
            alternativaList = alternativas;

        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron crear las alternativas", e);
        }
        return alternativaList;
    }
}
