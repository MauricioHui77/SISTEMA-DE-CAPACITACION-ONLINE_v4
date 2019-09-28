package com.upc.sistemadecapacitaciononline.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TP_ALTERNATIVA")
public class Alternativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlternativa;
    private String descripcion;
    private boolean estado;
    private boolean esCorrecta;

    public Alternativa() {
    }

    public Alternativa(Long idAlternativa, String descripcion, boolean estado, boolean esCorrecta) {
        this.idAlternativa = idAlternativa;
        this.descripcion = descripcion;
        this.estado = estado;
        this.esCorrecta = esCorrecta;
    }

    //MUCHOS A UNO PREGUNTA
    @ManyToOne
    @JoinColumn(name="CODIGO_PREGUNTA")//no genera
    @JsonIgnore
    private Pregunta pregunta;

    public Long getIdAlternativa() {
        return idAlternativa;
    }

    public void setIdAlternativa(Long idAlternativa) {
        this.idAlternativa = idAlternativa;
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

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public Pregunta getIdPregunta() {
        return pregunta;
    }

    public void setIdPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
}
