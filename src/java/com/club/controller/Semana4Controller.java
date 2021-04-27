/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.ItemsEvaluacionFacadeLocal;
import com.club.ejb.PlanDeEntrenamientoFacadeLocal;
import com.club.ejb.Semana4FacadeLocal;
import com.club.model.ItemsEvaluacion;
import com.club.model.PlanDeEntrenamiento;
import com.club.model.Semana4;
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
@Named("Semana4Controller")
@ManagedBean(name = "semana_4")
@ViewScoped
public class Semana4Controller implements Serializable {

    @EJB
    private Semana4FacadeLocal ejbFacade;

    @EJB
    private PlanDeEntrenamientoFacadeLocal ejbPlan;

    @EJB
    private ItemsEvaluacionFacadeLocal ejbItemsEvalu;

    private List<Semana4> items = null;
    private List<PlanDeEntrenamiento> Plan;
    private List<ItemsEvaluacion> ItemsE;

    private Semana4 selected;

    @PostConstruct
    public void inicial() {

        selected = new Semana4();
        selected.setIdPlan(new PlanDeEntrenamiento());
        selected.setIdItems(new ItemsEvaluacion());

        items = ejbFacade.findAll();
        Plan = ejbPlan.findAll();
        ItemsE = ejbItemsEvalu.findAll();
    }

    public void registrar() {
        try {

            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Calificacion!", "Asignada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Error"));
        }
         selected = new Semana4();
    }

    public void leer(Semana4 Seleccion) {

        selected = Seleccion;
    }

    public void modificar() {

        try {

            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Calificacion!", "Editada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Error"));
        }
    }

    public void eliminar() {
        
         try {

             ejbFacade.remove(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Calificacion!", "Eliminada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Error"));
        }

       

        items = ejbFacade.findAll();
        selected = new Semana4();
    }

    public Semana4FacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(Semana4FacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public PlanDeEntrenamientoFacadeLocal getEjbPlan() {
        return ejbPlan;
    }

    public void setEjbPlan(PlanDeEntrenamientoFacadeLocal ejbPlan) {
        this.ejbPlan = ejbPlan;
    }

    public ItemsEvaluacionFacadeLocal getEjbItemsEvalu() {
        return ejbItemsEvalu;
    }

    public void setEjbItemsEvalu(ItemsEvaluacionFacadeLocal ejbItemsEvalu) {
        this.ejbItemsEvalu = ejbItemsEvalu;
    }

    public List<Semana4> getItems() {
        return items;
    }

    public void setItems(List<Semana4> items) {
        this.items = items;
    }

    public List<PlanDeEntrenamiento> getPlan() {
        return Plan;
    }

    public void setPlan(List<PlanDeEntrenamiento> Plan) {
        this.Plan = Plan;
    }

    public List<ItemsEvaluacion> getItemsE() {
        return ItemsE;
    }

    public void setItemsE(List<ItemsEvaluacion> ItemsE) {
        this.ItemsE = ItemsE;
    }

    public Semana4 getSelected() {
        return selected;
    }

    public void setSelected(Semana4 selected) {
        this.selected = selected;
    }

}
