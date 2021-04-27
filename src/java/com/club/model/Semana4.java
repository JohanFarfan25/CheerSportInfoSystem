/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "semana_4", catalog = "gargolasproject", schema = "")

public class Semana4 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSemana4", nullable = false)
    private Integer idSemana4;

    @Column(name = "Calificacion", nullable = false)
    private int calificacion;

    @JoinColumn(name = "IdPlan", referencedColumnName = "IdPlan", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PlanDeEntrenamiento idPlan;

    @JoinColumn(name = "IdItems", referencedColumnName = "IdItems", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ItemsEvaluacion idItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSemana4", fetch = FetchType.LAZY)
    private Collection<Resultados1> resultados1Collection;

    public Semana4() {
    }

    public Integer getIdSemana4() {
        return idSemana4;
    }

    public void setIdSemana4(Integer idSemana4) {
        this.idSemana4 = idSemana4;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public PlanDeEntrenamiento getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(PlanDeEntrenamiento idPlan) {
        this.idPlan = idPlan;
    }

    public ItemsEvaluacion getIdItems() {
        return idItems;
    }

    public void setIdItems(ItemsEvaluacion idItems) {
        this.idItems = idItems;
    }

    public Collection<Resultados1> getResultados1Collection() {
        return resultados1Collection;
    }

    public void setResultados1Collection(Collection<Resultados1> resultados1Collection) {
        this.resultados1Collection = resultados1Collection;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idSemana4);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Semana4 other = (Semana4) obj;
        if (!Objects.equals(this.idSemana4, other.idSemana4)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Semana4{" + "idSemana4=" + idSemana4 + '}';
    }

}
