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
@Table(name = "semana_1", catalog = "gargolasproject", schema = "")

public class Semana1 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSemana1", nullable = false)
    private Integer idSemana1;

    @Column(name = "Calificacion", nullable = false)
    private int calificacion;

    @JoinColumn(name = "IdPlan", referencedColumnName = "IdPlan", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PlanDeEntrenamiento idPlan;

    @JoinColumn(name = "IdItems", referencedColumnName = "IdItems", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ItemsEvaluacion idItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSemana1", fetch = FetchType.LAZY)
    private Collection<Resultados1> resultadosCollection;

    public Semana1() {
    }

    public Integer getIdSemana1() {
        return idSemana1;
    }

    public void setIdSemana1(Integer idSemana1) {
        this.idSemana1 = idSemana1;
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

    public Collection<Resultados1> getResultadosCollection() {
        return resultadosCollection;
    }

    public void setResultadosCollection(Collection<Resultados1> resultadosCollection) {
        this.resultadosCollection = resultadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.idSemana1);
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
        final Semana1 other = (Semana1) obj;
        if (!Objects.equals(this.idSemana1, other.idSemana1)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Semana1{" + "idSemana1=" + idSemana1 + '}';
    }

}
