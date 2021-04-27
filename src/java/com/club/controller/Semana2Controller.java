/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.ItemsEvaluacionFacadeLocal;
import com.club.ejb.PlanDeEntrenamientoFacadeLocal;
import com.club.ejb.Semana2FacadeLocal;
import com.club.model.ItemsEvaluacion;
import com.club.model.PlanDeEntrenamiento;
import com.club.model.Semana2;
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
@Named("Semana2Controller")
@ManagedBean(name = "semana_2")
@ViewScoped
public class Semana2Controller implements Serializable {

    @EJB
    private Semana2FacadeLocal ejbFacade;

    @EJB
    private PlanDeEntrenamientoFacadeLocal ejbPlan;

    @EJB
    private ItemsEvaluacionFacadeLocal ejbItemsEvalu;

    private List<Semana2> items = null;
    private List<PlanDeEntrenamiento> Plan;
    private List<ItemsEvaluacion> ItemsE;

    private Semana2 selected;

    @PostConstruct
    public void inicial() {

        selected = new Semana2();
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
         selected = new Semana2();
    }

    public void leer(Semana2 Seleccion) {

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

        ejbFacade.remove(selected);

        items = ejbFacade.findAll();
        selected = new Semana2();
    }

    public Semana2FacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(Semana2FacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Semana2> getItems() {
        return items;
    }

    public void setItems(List<Semana2> items) {
        this.items = items;
    }

    public Semana2 getSelected() {
        return selected;
    }

    public void setSelected(Semana2 selected) {
        this.selected = selected;
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

}
