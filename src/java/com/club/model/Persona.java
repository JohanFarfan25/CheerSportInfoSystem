/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johan.farfan
 */
@Entity
@Table(name = "persona", catalog = "gargolasproject", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigo"}),
    @UniqueConstraint(columnNames = {"Documento"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Size(max = 100)
    @Column(name = "apellidos", length = 100)
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Telefono", nullable = false, length = 16)
    private int telefono;
    @Size(max = 2)
    @Column(name = "sexo", length = 2)
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "Tipo_Documento", nullable = false, length = 3)
    private String tipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Documento", nullable = false)
    private BigInteger documento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Edad", nullable = false)
    private int edad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Factor_De_Riezgo", nullable = false, length = 255)
    private String factorDeRiezgo;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    public Persona() {
    }

    public Persona(Integer codigo) {
        this.codigo = codigo;
    }

    public Persona(Integer codigo, int telefono, String tipoDocumento, BigInteger documento, int edad, String factorDeRiezgo) {
        this.codigo = codigo;
        this.telefono = telefono;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.edad = edad;
        this.factorDeRiezgo = factorDeRiezgo;


    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public BigInteger getDocumento() {
        return documento;
    }

    public void setDocumento(BigInteger documento) {
        this.documento = documento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFactorDeRiezgo() {
        return factorDeRiezgo;
    }

    public void setFactorDeRiezgo(String factorDeRiezgo) {
        this.factorDeRiezgo = factorDeRiezgo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codigo);
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "codigo=" + codigo + '}';
    }

    public void setCodigo(Usuario uselected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
