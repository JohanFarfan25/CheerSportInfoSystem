/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "resultados1", catalog = "gargolasproject", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultados1.findAll", query = "SELECT r FROM Resultados1 r")})
public class Resultados1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdResultado", nullable = false)
    private Integer idResultado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NotaFinal", nullable = false)
    private int notaFinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "Resultado", nullable = false, length = 9)
    private String resultado;
    @JoinColumn(name = "IdSemana1", referencedColumnName = "IdSemana1", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Semana1 idSemana1;
    @JoinColumn(name = "IdSemana2", referencedColumnName = "IdSemana2", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Semana2 idSemana2;
    @JoinColumn(name = "IdSemana3", referencedColumnName = "IdSemana3", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Semana3 idSemana3;
    @JoinColumn(name = "IdSemana4", referencedColumnName = "IdSemana4", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Semana4 idSemana4;

    public Resultados1() {
    }

    public Resultados1(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public Resultados1(Integer idResultado, int notaFinal, String resultado) {
        this.idResultado = idResultado;
        this.notaFinal = notaFinal;
        this.resultado = resultado;
    }

    public Integer getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public int getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(int notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Semana1 getIdSemana1() {
        return idSemana1;
    }

    public void setIdSemana1(Semana1 idSemana1) {
        this.idSemana1 = idSemana1;
    }

    public Semana2 getIdSemana2() {
        return idSemana2;
    }

    public void setIdSemana2(Semana2 idSemana2) {
        this.idSemana2 = idSemana2;
    }

    public Semana3 getIdSemana3() {
        return idSemana3;
    }

    public void setIdSemana3(Semana3 idSemana3) {
        this.idSemana3 = idSemana3;
    }

    public Semana4 getIdSemana4() {
        return idSemana4;
    }

    public void setIdSemana4(Semana4 idSemana4) {
        this.idSemana4 = idSemana4;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultado != null ? idResultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultados1)) {
            return false;
        }
        Resultados1 other = (Resultados1) object;
        if ((this.idResultado == null && other.idResultado != null) || (this.idResultado != null && !this.idResultado.equals(other.idResultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.club.model.Resultados1[ idResultado=" + idResultado + " ]";
    }
    
}
