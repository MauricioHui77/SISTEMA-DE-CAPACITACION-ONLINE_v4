package com.upc.sistemadecapacitaciononline.rest;

        import com.upc.sistemadecapacitaciononline.entidades.Evaluacion;
        import com.upc.sistemadecapacitaciononline.entidades.EvaluacionesAll;
        import com.upc.sistemadecapacitaciononline.serviciosDAO.ServicioEvaluacionDAO;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.server.ResponseStatusException;

        import java.util.List;

@RestController
@RequestMapping("/api/evaluacion")
public class ServicioEvaluacionRest {
    @Autowired
    private ServicioEvaluacionDAO servicioEvaluacionDAO;

    @PostMapping("/registrar")
    public Evaluacion registrarEvaluacion(@RequestBody Evaluacion evaluacion) {
        return servicioEvaluacionDAO.registrar(evaluacion);
    }

    @GetMapping("/obtenerEvaluaciones/{idUsuario}")
    public List<Evaluacion> obtenerEvaluacionesPorUsuario (@PathVariable(name = "idUsuario") Long idUsuario) {
        return servicioEvaluacionDAO.obtenerEvaluaciones(idUsuario);
    }

    @GetMapping("/getAll")
    public EvaluacionesAll obtenerEvaluacionAll() {

        EvaluacionesAll evaluacionesAll = new EvaluacionesAll();

        List<Evaluacion> evaluaciones;

        try{
            evaluaciones = servicioEvaluacionDAO.obtenerEvaluacionAll();
            evaluacionesAll.setEvaluaciones(evaluaciones);
        }
        catch (Exception e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND,"No encontrado",e);
        }

        return evaluacionesAll;
    }
}
