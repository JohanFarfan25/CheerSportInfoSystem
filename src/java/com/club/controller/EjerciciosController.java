/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.EjerciciosFacadeLocal;
import com.club.model.Ejercicios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author johan.farfan
 */
@ManagedBean(name = "ejercicios")
@Named(value = "ejerciciosController")
@ViewScoped
public class EjerciciosController implements Serializable{
 @EJB
    private EjerciciosFacadeLocal ejbFacade;
    private List<Ejercicios> items = null;
    private Ejercicios selected;
    

    @PostConstruct
    public void inicial() {

        items = ejbFacade.findAll();
        selected = new Ejercicios();

    }
    
           public int catidadEjercicios() {
        return ejbFacade.count();
    }
    
    public EjerciciosController() {
    }
    

    public void registrar() {
        try {

            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Ejercicio!", " Creado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "El ejercicio no se´pudo crear"));
        }
        selected = new Ejercicios();
    }

    public void leer(Ejercicios Seleccion) {

        selected = Seleccion;
    }

    public void modificar() {

        try {

            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Ejercicio!", " Editado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "El ejercicio no se´pudo editar"));
        }
        items = ejbFacade.findAll();
        selected = new Ejercicios();
    }

    public void eliminar() {

        try {

            ejbFacade.remove(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Ejercicio!", " Eliminado correctamente"));
        } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "El ejercicio esta asignado a un plan de entrenamiento"));
        }
        items = ejbFacade.findAll();
    }


    public List<Ejercicios> getItems() {
        return items;
    }

    public void setItems(List<Ejercicios> items) {
        this.items = items;
    }

    public Ejercicios getSelected() {
        return selected;
    }

    public void setSelected(Ejercicios selected) {
        this.selected = selected;
    }

    public EjerciciosFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(EjerciciosFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
   
}
