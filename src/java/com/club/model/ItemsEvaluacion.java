/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "items_evaluacion", catalog = "gargolasproject", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemsEvaluacion.findAll", query = "SELECT i FROM ItemsEvaluacion i")})
public class ItemsEvaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdItems", nullable = false)
    private Integer idItems;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "Nombre_Item", nullable = false, length = 18)
    private String nombreItem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItems", fetch = FetchType.LAZY)
    private Collection<Semana4> semana4Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItems", fetch = FetchType.LAZY)
    private Collection<Semana3> semana3Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItems", fetch = FetchType.LAZY)
    private Collection<Semana2> semana2Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItems", fetch = FetchType.LAZY)
    private Collection<Semana1> semana1Collection;

    public ItemsEvaluacion() {
    }

    public ItemsEvaluacion(Integer idItems) {
        this.idItems = idItems;
    }

    public ItemsEvaluacion(Integer idItems, String nombreItem) {
        this.idItems = idItems;
        this.nombreItem = nombreItem;
    }

    public Integer getIdItems() {
        return idItems;
    }

    public void setIdItems(Integer idItems) {
        this.idItems = idItems;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    @XmlTransient
    public Collection<Semana4> getSemana4Collection() {
        return semana4Collection;
    }

    public void setSemana4Collection(Collection<Semana4> semana4Collection) {
        this.semana4Collection = semana4Collection;
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
        hash += (idItems != null ? idItems.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemsEvaluacion)) {
            return false;
        }
        ItemsEvaluacion other = (ItemsEvaluacion) object;
        if ((this.idItems == null && other.idItems != null) || (this.idItems != null && !this.idItems.equals(other.idItems))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.club.model.ItemsEvaluacion[ idItems=" + idItems + " ]";
    }
    
}
