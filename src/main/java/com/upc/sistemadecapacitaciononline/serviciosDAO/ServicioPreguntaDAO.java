package com.upc.sistemadecapacitaciononline.serviciosDAO;

import com.upc.sistemadecapacitaciononline.entidades.Evaluacion;
import com.upc.sistemadecapacitaciononline.entidades.Pregunta;
import com.upc.sistemadecapacitaciononline.repositorio.EvaluacionRepositorio;
import com.upc.sistemadecapacitaciononline.repositorio.PreguntaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServicioPreguntaDAO {
    @Autowired
    private PreguntaRepositorio preguntaRepositorio;
    @Autowired
    private EvaluacionRepositorio  evaluacionRepositorio;

    public Pregunta registrar(String nombreEvaluacion, Pregunta pregunta) {
        Pregunta p;

        try{
            Evaluacion evaluacion = evaluacionRepositorio.buscarEvaluacionPorNombre(nombreEvaluacion);
            pregunta.setIdEvaluacion(evaluacion);
            p = preguntaRepositorio.save(pregunta);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo crear la pregunta", e);
        }
        return p;
    }

    public List<Pregunta> registrarPreguntas (Long idEvaluacion, List<Pregunta> preguntas) {
        List<Pregunta> preguntasList = null;
        Pregunta p;

        try{
            Evaluacion evaluacion = evaluacionRepositorio.buscarEvaluacionPorId(idEvaluacion);
            for (Pregunta pregunta:preguntas) {
                pregunta.setIdEvaluacion(evaluacion);
                p = preguntaRepositorio.save(pregunta);
            }

            preguntasList = preguntas;
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron registrar las preguntas", e);
        }
        return preguntasList;
    }
}
