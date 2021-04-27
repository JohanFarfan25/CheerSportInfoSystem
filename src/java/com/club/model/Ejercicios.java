/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "ejercicios", catalog = "gargolasproject", schema = "")

public class Ejercicios implements Serializable {

    public static void add(Ejercicios ejercicios) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEjercicios")
    private Integer idEjercicios;

    @Column(name = "Nombre_Ejercicio")
    private String nombreEjercicio;

    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)

    @Column(name = "Categoria_Ejercicio")
    private String categoriaEjercicio;
    

    public Ejercicios() {
    }

 
    public Ejercicios(String nombreEjercicio, String descripcion, String categoriaEjercicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getIdEjercicios() {
        return idEjercicios;
    }

    public void setIdEjercicios(Integer idEjercicios) {
        this.idEjercicios = idEjercicios;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoriaEjercicio() {
        return categoriaEjercicio;
    }

    public void setCategoriaEjercicio(String categoriaEjercicio) {
        this.categoriaEjercicio = categoriaEjercicio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idEjercicios);
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
        final Ejercicios other = (Ejercicios) obj;
        if (!Objects.equals(this.idEjercicios, other.idEjercicios)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ejercicios{" + "idEjercicios=" + idEjercicios + '}';
    }

   
   
    
}
