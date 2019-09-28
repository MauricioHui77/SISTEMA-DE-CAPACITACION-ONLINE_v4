package com.upc.sistemadecapacitaciononline.serviciosDAO;

import com.upc.sistemadecapacitaciononline.entidades.Evaluacion;
import com.upc.sistemadecapacitaciononline.entidades.UsuarioEvaluacion;
import com.upc.sistemadecapacitaciononline.repositorio.EvaluacionRepositorio;
import com.upc.sistemadecapacitaciononline.repositorio.UsuarioEvaluacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ServicioEvaluacionDAO {
    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;
    @Autowired
    private UsuarioEvaluacionRepositorio usuarioEvaluacionRepositorio;

    public Evaluacion registrar (Evaluacion evaluacion) {
        Evaluacion e;
        try {
            Calendar calendar = new GregorianCalendar();

            String dia = Integer.toString(calendar.get(Calendar.DATE));
            String mes = Integer.toString(calendar.get(Calendar.MONTH));
            String annio = Integer.toString(calendar.get(Calendar.YEAR));

            int hora, minutos, segundos;
            hora = calendar.get(Calendar.HOUR_OF_DAY);
            minutos = calendar.get(Calendar.MINUTE);
            segundos = calendar.get(Calendar.SECOND);

            evaluacion.setFechaCreacion(dia+"/"+mes+"/"+annio+" "+hora+":"+minutos+":"+segundos);
            e = evaluacionRepositorio.save(evaluacion);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo crear la evaluacion", ex);
        }
        return e;
    }

    public List<Evaluacion> obtenerEvaluaciones (Long idUsuario) {
        List<UsuarioEvaluacion> usuarioEvaluacionList;
        List<Evaluacion> evaluacionList = null;
        try {
            usuarioEvaluacionList = usuarioEvaluacionRepositorio.obtenerEvaluacionesPorUsuario(idUsuario);

            for (UsuarioEvaluacion usuarioEvaluacion:
                    usuarioEvaluacionList) {
                Evaluacion evaluacion = usuarioEvaluacion.getEvaluacion();
                Long idEvaluacion = evaluacion.getIdEvaluacion();
                Evaluacion getEvaluacion = evaluacionRepositorio.buscarEvaluacionPorId(idEvaluacion);
                evaluacionList.add(getEvaluacion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron evaluaciones" , e);
        }
        return evaluacionList;
    }

    public List<Evaluacion> obtenerEvaluacionAll() {

        return evaluacionRepositorio.obtenerEvaluaciones();
    }
}
