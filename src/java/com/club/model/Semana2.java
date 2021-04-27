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
@Table(name = "semana_2", catalog = "gargolasproject", schema = "")

public class Semana2 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSemana2", nullable = false)
    private Integer idSemana2;

    @Column(name = "Calificacion", nullable = false)
    private int calificacion;

    @JoinColumn(name = "IdPlan", referencedColumnName = "IdPlan", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PlanDeEntrenamiento idPlan;

    @JoinColumn(name = "IdItems", referencedColumnName = "IdItems", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ItemsEvaluacion idItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSemana2", fetch = FetchType.LAZY)
    private Collection<Resultados1> resultados1Collection;

    public Semana2() {
    }

    public Integer getIdSemana2() {
        return idSemana2;
    }

    public void setIdSemana2(Integer idSemana2) {
        this.idSemana2 = idSemana2;
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
        hash = 37 * hash + Objects.hashCode(this.idSemana2);
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
        final Semana2 other = (Semana2) obj;
        if (!Objects.equals(this.idSemana2, other.idSemana2)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Semana2{" + "idSemana2=" + idSemana2 + '}';
    }

}
