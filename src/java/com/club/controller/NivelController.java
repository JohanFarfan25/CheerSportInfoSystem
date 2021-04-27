/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.NivelFacadeLocal;
import com.club.model.Nivel;
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
@Named("NivelController")
@ManagedBean(name="nivel")
@ViewScoped
public class NivelController implements Serializable {



   @EJB
    private NivelFacadeLocal ejbFacade;
    private List<Nivel> items = null;
    private Nivel selected;
    
     @PostConstruct
    public void inicial(){
    
       items =  ejbFacade.findAll();
       selected = new Nivel();
    
    }
    
         public void registrar(){
        try {
       
            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Nivel!"," Creado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!","Error"));
        }
    }
    
    public void leer(Nivel Seleccion){
         
         selected = Seleccion;
     }
     
     public void modificar(){
             
          try {
       
            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Nivel!"," Editado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!","Error"));
        }
     }
     
     public void eliminar(){
         
         ejbFacade.remove(selected);
         
           items =  ejbFacade.findAll();
       selected = new Nivel();
     }
    

    public NivelFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(NivelFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Nivel> getItems() {
        return items;
    }

    public void setItems(List<Nivel> items) {
        this.items = items;
    }

    public Nivel getSelected() {
        return selected;
    }

    public void setSelected(Nivel selected) {
        this.selected = selected;
    }
    

    
}
