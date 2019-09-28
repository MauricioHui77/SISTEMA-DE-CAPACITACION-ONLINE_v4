package com.upc.sistemadecapacitaciononline.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TP_PREGUNTA")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PREGUNTA")
    private Long idPregunta;
    private String descripcion;
    private boolean estado;

    //Muchas preguntas pertenecen a una evaluación
    @ManyToOne
    @JoinColumn(name="CODIGO_EVALUACION")
    @JsonIgnore
    private Evaluacion evaluacion;

    @JsonIgnore
    @OneToMany(mappedBy="pregunta", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Alternativa> alternativas;

    public Pregunta() {
    }

    public Pregunta(Long idPregunta, String descripcion, boolean estado, List<Alternativa> alternativas) {
        this.idPregunta = idPregunta;
        this.descripcion = descripcion;
        this.estado = estado;
        this.alternativas = alternativas;
    }


    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Evaluacion getIdEvaluacion() {
        return evaluacion;
    }

    public void setIdEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
}
