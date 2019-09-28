package com.upc.sistemadecapacitaciononline.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="TP_USUARIOEVALUACION")
public class UsuarioEvaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_USUEVALUACION")
    private Long idUsuarioEvaluacion;

    //Un usuario tiene muchas evaluaciones
    @OneToOne
    @JoinColumn(name="CODIGO_USUARIO")
    @JsonIgnore
    private Usuario usuario;

    //Una evaluacion tiene muchos usuarios
    @OneToOne
    @JoinColumn(name="CODIGO_EVALUACION")
    @JsonIgnore
    private Evaluacion evaluacion;

    @Temporal(TemporalType.DATE)
    private Calendar fechaAsignacion;

    private int calificacion;
    private boolean estado;

    public Long getIdUsuarioEvaluacion() {
        return idUsuarioEvaluacion;
    }

    public void setIdUsuarioEvaluacion(Long idUsuarioEvaluacion) {
        this.idUsuarioEvaluacion = idUsuarioEvaluacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Calendar getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Calendar fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
