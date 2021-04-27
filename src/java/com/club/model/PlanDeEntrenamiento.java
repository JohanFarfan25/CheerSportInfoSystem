/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "plan_de_entrenamiento", catalog = "gargolasproject", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanDeEntrenamiento.findAll", query = "SELECT p FROM PlanDeEntrenamiento p")})
public class PlanDeEntrenamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPlan", nullable = false)
    private Integer idPlan;

    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Creacion", insertable = false)
    private Date fechaCreacion;

    @Column(name = "Fecha_Inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "Fecha_Final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Size(max = 12)
    @Column(name = "Comprobante_De_Pago", length = 12)
    private String comprobanteDePago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "Estado", nullable = false, length = 8)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan", fetch = FetchType.LAZY)
    private Collection<Semana4> semana4Collection;
    @JoinColumn(name = "IdEntrenador", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idEntrenador;
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Recomendaciones", nullable = false, length = 255)
    private String recomendaciones;

    @JoinColumn(name = "IdNivel", referencedColumnName = "IdNivel", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Nivel idNivel;
    @JoinColumn(name = "IdEjercicio_1", referencedColumnName = "IdEjercicios")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ejercicios idEjercicio1;
    @JoinColumn(name = "IdEjercicio_2", referencedColumnName = "IdEjercicios")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ejercicios idEjercicio2;
    @JoinColumn(name = "IdEjercicio_3", referencedColumnName = "IdEjercicios")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ejercicios idEjercicio3;
    @JoinColumn(name = "IdEjercicio_4", referencedColumnName = "IdEjercicios")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ejercicios idEjercicio4;
    
    @JoinColumn(name = "IdDeportista", referencedColumnName = "codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    
    private Usuario idDeportista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan", fetch = FetchType.LAZY)
    private Collection<Semana3> semana3Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan", fetch = FetchType.LAZY)
    private Collection<Semana2> semana2Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan", fetch = FetchType.LAZY)
    private Collection<Semana1> semana1Collection;

    public PlanDeEntrenamiento() {
    }

    public PlanDeEntrenamiento(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public PlanDeEntrenamiento(Integer idPlan, String estado) {
        this.idPlan = idPlan;
        this.estado = estado;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getComprobanteDePago() {
        return comprobanteDePago;
    }

    public void setComprobanteDePago(String comprobanteDePago) {
        this.comprobanteDePago = comprobanteDePago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Semana4> getSemana4Collection() {
        return semana4Collection;
    }

    public void setSemana4Collection(Collection<Semana4> semana4Collection) {
        this.semana4Collection = semana4Collection;
    }

    public Usuario getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Usuario idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public Nivel getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Nivel idNivel) {
        this.idNivel = idNivel;
    }

    public Ejercicios getIdEjercicio1() {
        return idEjercicio1;
    }

    public void setIdEjercicio1(Ejercicios idEjercicio1) {
        this.idEjercicio1 = idEjercicio1;
    }

    public Ejercicios getIdEjercicio2() {
        return idEjercicio2;
    }

    public void setIdEjercicio2(Ejercicios idEjercicio2) {
        this.idEjercicio2 = idEjercicio2;
    }

    public Ejercicios getIdEjercicio3() {
        return idEjercicio3;
    }

    public void setIdEjercicio3(Ejercicios idEjercicio3) {
        this.idEjercicio3 = idEjercicio3;
    }

    public Ejercicios getIdEjercicio4() {
        return idEjercicio4;
    }

    public void setIdEjercicio4(Ejercicios idEjercicio4) {
        this.idEjercicio4 = idEjercicio4;
    }

    public Usuario getIdDeportista() {
        return idDeportista;
    }

    public void setIdDeportista(Usuario idDeportista) {
        this.idDeportista = idDeportista;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    @XmlTransient
    public Collection<Semana3> getSemana3Collection() {
        return semana3Collection;
    }

    public void setSemana3Collection(Collection<Semana3> semana3Collection) {
        this.semana3Collection = semana3Collection;
    }

    @XmlTransient
    public Collection<Semana2> getSemana2Collection() {
        return semana2Collection;
    }

    public void setSemana2Collection(Collection<Semana2> semana2Collection) {
        this.semana2Collection = semana2Collection;
    }

    @XmlTransient
    public Collection<Semana1> getSemana1Collection() {
        return semana1Collection;
    }

    public void setSemana1Collection(Collection<Semana1> semana1Collection) {
        this.semana1Collection = semana1Collection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanDeEntrenamiento)) {
            return false;
        }
        PlanDeEntrenamiento other = (PlanDeEntrenamiento) object;
        if ((this.idPlan == null && other.idPlan != null) || (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.club.model.PlanDeEntrenamiento[ idPlan=" + idPlan + " ]";
    }

}
