package com.upc.sistemadecapacitaciononline.corenegocio;

import com.upc.sistemadecapacitaciononline.entidades.*;
import com.upc.sistemadecapacitaciononline.repositorio.AlternativaRepositorio;
import com.upc.sistemadecapacitaciononline.repositorio.EvaluacionRepositorio;
import com.upc.sistemadecapacitaciononline.repositorio.UsuarioEvaluacionRepositorio;
import com.upc.sistemadecapacitaciononline.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.List;

@Service
public class ServicioUsuarioEvaluacionCore {
    @Autowired
    private UsuarioEvaluacionRepositorio usuarioEvaluacionRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;
    @Autowired
    private AlternativaRepositorio alternativaRepositorio;

    public UsuarioEvaluacion registrarAsignacion (Long idEvaluacion, Long idUsuario) {
        UsuarioEvaluacion ue;
        UsuarioEvaluacion usuarioEvaluacion = new UsuarioEvaluacion();
        Calendar calendar = Calendar.getInstance();

        try {
            Usuario usuario = usuarioRepositorio.buscarUsuarioPorId(idUsuario);
            Evaluacion evaluacion = evaluacionRepositorio.buscarEvaluacionPorId(idEvaluacion);
            usuarioEvaluacion.setUsuario(usuario);
            usuarioEvaluacion.setEvaluacion(evaluacion);
            usuarioEvaluacion.setFechaAsignacion(calendar);
            usuarioEvaluacion.setCalificacion(0);
            ue = usuarioEvaluacionRepositorio.save(usuarioEvaluacion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo asignar la evaluación", e);
        }
        return ue;
    }

    public UsuarioEvaluacion registrarCalificacion (Long idEvaluacion, Long idUsuario, List<Resultado> resultados) {
        UsuarioEvaluacion usuarioEvaluacion;
        UsuarioEvaluacion ue;

        try {
            ue = registrarAsignacion(idEvaluacion, idUsuario);

            int preguntasCorrectas = 0;
            int calificacion = 0;

            for (Resultado resultado:
                    resultados) {
                Long idAlt = resultado.getIdAlternativa();
                Alternativa alternativa = alternativaRepositorio.buscarAlternativaPorId(idAlt);
                boolean esCorrecta = alternativa.isEsCorrecta();

                if (esCorrecta) {
                    preguntasCorrectas++;
                }
                else
                    continue;
            }

            calificacion = preguntasCorrectas*4;
            ue.setCalificacion(calificacion);
            usuarioEvaluacion = usuarioEvaluacionRepositorio.save(ue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo asignar la evaluación", e);
        }
        return usuarioEvaluacion;
    }
}
