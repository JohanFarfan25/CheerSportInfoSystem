/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "inventario_materiales", catalog = "gargolasproject", schema = "")
public class InventarioMateriales implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMaterial")
    private Integer idMaterial;

    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Ingreso", insertable = false)
    private Date fechaIngreso;

    @Column(name = "Nombre_Material")
    private String nombreMaterial;

    @Column(name = "Unidades_Disponibles")
    private int unidadesDisponibles;

    @Column(name = "Categoria")
    private String categoria;

    @Column(name = "IdAdministrador")
    private int idAdministrador;

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idMaterial);
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
        final InventarioMateriales other = (InventarioMateriales) obj;
        if (!Objects.equals(this.idMaterial, other.idMaterial)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InventarioMateriales{" + "idMaterial=" + idMaterial + '}';
    }


 
    
}
