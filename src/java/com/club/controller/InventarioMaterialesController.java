package com.club.controller;

import com.club.ejb.InventarioMaterialesFacadeLocal;
import com.club.model.InventarioMateriales;
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
@Named("InventarioMaterialesController")
@ManagedBean(name = "inventario_materiales")
@ViewScoped
public class InventarioMaterialesController implements Serializable {

    @EJB
    private InventarioMaterialesFacadeLocal ejbFacade;
    private List<InventarioMateriales> items = null;
    private InventarioMateriales selected;

    @PostConstruct
    public void inicial() {

        items = ejbFacade.findAll();
        selected = new InventarioMateriales();

    }
    
          public int catidadMateriales() {
        return ejbFacade.count();
    }


    public void registrar() {
        try {

            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Equipo!", "Registrado corretamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "El equipo no se pudo registrar"));
        }
        items = ejbFacade.findAll();
        selected = new InventarioMateriales();
    }

    public void leer(InventarioMateriales Seleccion) {

        selected = Seleccion;
    }

    public void modificar() {

        try {

            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Equipo!", " Editado corretamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "El equipo no se pudo Editar"));
        }
        items = ejbFacade.findAll();
        selected = new InventarioMateriales();

    }

    public void eliminar() {

        try {

            ejbFacade.remove(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Equipo!", " Eliminado corretamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "El equipo no se pudo eliminar"));
        }
        items = ejbFacade.findAll();

    }

    public InventarioMaterialesFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(InventarioMaterialesFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<InventarioMateriales> getItems() {
        return items;
    }

    public void setItems(List<InventarioMateriales> items) {
        this.items = items;
    }

    public InventarioMateriales getSelected() {
        return selected;
    }

    public void setSelected(InventarioMateriales selected) {
        this.selected = selected;
    }

}
