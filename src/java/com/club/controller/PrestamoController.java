/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.InventarioMaterialesFacadeLocal;
import com.club.ejb.PrestamoFacadeLocal;
import com.club.model.InventarioMateriales;
import com.club.model.Prestamo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
@Named("PrestamoController")
@ManagedBean(name = "prestamo")
@ViewScoped
public class PrestamoController implements Serializable {

    @EJB
    private PrestamoFacadeLocal ejbFacade;
    private List<Prestamo> items = null;
    private Prestamo selected;

    @EJB
    InventarioMaterialesFacadeLocal MaterialesEJB;
    private InventarioMateriales objMateriales = new InventarioMateriales();
    private ArrayList<InventarioMateriales> listaMateriales = new ArrayList<>();

    @PostConstruct
    public void inicial() {

        items = ejbFacade.findAll();
        selected = new Prestamo();

        objMateriales = new InventarioMateriales();
        listaMateriales.addAll(MaterialesEJB.findAll());

    }

    public int catidadPrestamos() {
        return ejbFacade.count();
    }

    public void registrar() {
        try {

            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Equipo!", "Prestado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "No se pudo realizar el prestamo"));
        }
        selected = new Prestamo();
        items = ejbFacade.findAll();
        listaMateriales.addAll(MaterialesEJB.findAll());
    }

    public void devolver() {
        try {
            selected.setFechaDevolucion(Calendar.getInstance().getTime());
            ejbFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Devolución!", "Efectiva"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Error"));
        }
    }

    public void leer(Prestamo Seleccion) {

        selected = Seleccion;
    }

    public void modificar() {

        ejbFacade.edit(selected);
    }

    public void eliminar(Prestamo presRem) {
        try {

            ejbFacade.remove(presRem);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Devolución!", "Eliminada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Error"));
        }
        items = ejbFacade.findAll();
        selected = new Prestamo();
        listaMateriales.addAll(MaterialesEJB.findAll());
    }

    public PrestamoFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PrestamoFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Prestamo> getItems() {
        return items;
    }

    public void setItems(List<Prestamo> items) {
        this.items = items;
    }

    public Prestamo getSelec() {
        return selected;
    }

    public void setSelec(Prestamo selec) {
        this.selected = selec;
    }

    public Prestamo getSelected() {
        return selected;
    }

    public void setSelected(Prestamo selected) {
        this.selected = selected;
    }

    public InventarioMateriales getObjMateriales() {
        return objMateriales;
    }

    public void setObjMateriales(InventarioMateriales objMateriales) {
        this.objMateriales = objMateriales;
    }

    public ArrayList<InventarioMateriales> getListaMateriales() {
        return listaMateriales;
    }

    public void setListaMateriales(ArrayList<InventarioMateriales> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }

}
