/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.PersonaFacadeLocal;
import com.club.ejb.UsuarioFacadeLocal;
import com.club.model.Persona;
import com.club.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author johan.farfan
 */
@Named("personaController")
@ManagedBean(name = "persona")
@ViewScoped
public class PersonaController implements Serializable {

    @EJB
    private PersonaFacadeLocal ejbFacade;
    private List<Persona> items = null;
    private Persona selected;

    @EJB
    private UsuarioFacadeLocal ujbFacade;
    private List<Usuario> uitems = null;
    private Usuario uselected;

    @PostConstruct
    public void inicial() {

        items = ejbFacade.findAll();
        selected = new Persona();

    }

    public int catidadUsuarios() {
        return ejbFacade.count();
    }

    public void leer(Persona Seleccion) {

        selected = Seleccion;
    }

    public void modificar() {

        try {

            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Persona!", " Editada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "La persona no se pudo editar"));
        }

        items = ejbFacade.findAll();
        selected = new Persona();
    }

    public void eliminar() {

        try {

            ejbFacade.remove(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Persona!", " Eliminada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "La persona esta asignada a un plan de entrenamiento"));
        }
        items = ejbFacade.findAll();
    }

    public void setItems(List<Persona> items) {
        this.items = items;
    }

    public Persona getSelected() {
        return selected;
    }

    public void setSelected(Persona selected) {
        this.selected = selected;
    }

    public List<Usuario> getUitems() {
        return uitems;
    }

    public void setUitems(List<Usuario> uitems) {
        this.uitems = uitems;
    }

    public Usuario getUselected() {
        return uselected;
    }

    public void setUselected(Usuario uselected) {
        this.uselected = uselected;
    }

}
