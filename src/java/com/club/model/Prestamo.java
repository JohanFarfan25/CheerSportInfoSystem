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
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "prestamo", catalog = "gargolasproject", schema = "")

public class Prestamo implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPrestamo")
    private Integer idPrestamo;
   
    @JoinColumn(name = "IdMaterial")
    private Integer IdMaterial;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Prestamo", insertable = false)
    private Date fechaPrestamo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Devolucion", nullable = false)
    private Date fechaDevolucion;

    @Column(name = "IdEntrenador")
    private int idEntrenador;

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdMaterial() {
        return IdMaterial;
    }

    public void setIdMaterial(Integer IdMaterial) {
        this.IdMaterial = IdMaterial;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idPrestamo);
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
        final Prestamo other = (Prestamo) obj;
        if (!Objects.equals(this.idPrestamo, other.idPrestamo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "idPrestamo=" + idPrestamo + '}';
    }

    
    
}
