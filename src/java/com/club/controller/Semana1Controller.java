/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.ItemsEvaluacionFacadeLocal;
import com.club.ejb.PlanDeEntrenamientoFacadeLocal;
import com.club.ejb.Semana1FacadeLocal;
import com.club.model.ItemsEvaluacion;
import com.club.model.PlanDeEntrenamiento;
import com.club.model.Semana1;
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
@Named("Semana1Controller")
@ManagedBean(name = "semana_1")
@ViewScoped
public class Semana1Controller implements Serializable {

    @EJB
    private Semana1FacadeLocal ejbFacade;

    @EJB
    private PlanDeEntrenamientoFacadeLocal ejbPlan;

    @EJB
    private ItemsEvaluacionFacadeLocal ejbItemsEvalu;

    private List<Semana1> items = null;
    private List<PlanDeEntrenamiento> Plan;
    private List<ItemsEvaluacion> ItemsE;

    private Semana1 selected;
    private ItemsEvaluacion Item1;
    private PlanDeEntrenamiento objPlan;

    @PostConstruct
    public void inicial() {

        selected = new Semana1();
        Item1 = new ItemsEvaluacion();
        objPlan = new PlanDeEntrenamiento();

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
    }

    public void leer(Semana1 Seleccion) {

        selected = Seleccion;
    }

    public void modificar() {

        try {

            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Calificacion!", "Editada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Error"));
        }
        items = ejbFacade.findAll();
        selected = new Semana1();
    }

    public void eliminar( Semana1 sem1Rem) {
               try {

            ejbFacade.remove(sem1Rem);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Calificacion!", "Eliminada Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Error"));
        }
        items = ejbFacade.findAll();
       
    }

    public Semana1FacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(Semana1FacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Semana1> getItems() {
        return items;
    }

    public void setItems(List<Semana1> items) {
        this.items = items;
    }

    public Semana1 getSelected() {
        return selected;
    }

    public void setSelected(Semana1 selected) {
        this.selected = selected;
    }

    public PlanDeEntrenamientoFacadeLocal getEjbPlan() {
        return ejbPlan;
    }

    public void setEjbPlan(PlanDeEntrenamientoFacadeLocal ejbPlan) {
        this.ejbPlan = ejbPlan;
    }

    public List<PlanDeEntrenamiento> getPlan() {
        return Plan;
    }

    public void setPlan(List<PlanDeEntrenamiento> Plan) {
        this.Plan = Plan;
    }

    public ItemsEvaluacionFacadeLocal getEjbItemsEvalu() {
        return ejbItemsEvalu;
    }

    public void setEjbItemsEvalu(ItemsEvaluacionFacadeLocal ejbItemsEvalu) {
        this.ejbItemsEvalu = ejbItemsEvalu;
    }

    public List<ItemsEvaluacion> getItemsE() {
        return ItemsE;
    }

    public void setItemsE(List<ItemsEvaluacion> ItemsE) {
        this.ItemsE = ItemsE;
    }

    public ItemsEvaluacion getItem1() {
        return Item1;
    }

    public void setItem1(ItemsEvaluacion Item1) {
        this.Item1 = Item1;
    }

    public PlanDeEntrenamiento getObjPlan() {
        return objPlan;
    }

    public void setObjPlan(PlanDeEntrenamiento objPlan) {
        this.objPlan = objPlan;
    }

    
}
