/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.EjerciciosFacadeLocal;
import com.club.ejb.NivelFacadeLocal;
import com.club.ejb.PersonaFacadeLocal;
import com.club.ejb.PlanDeEntrenamientoFacadeLocal;
import com.club.ejb.UsuarioFacadeLocal;
import com.club.model.Ejercicios;
import com.club.model.Nivel;
import com.club.model.Persona;
import com.club.model.PlanDeEntrenamiento;
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
@Named("PlanDeEntrenamientoController")
@ManagedBean(name = "plan_de_entrenamiento")
@ViewScoped
public class PlanDeEntrenamientoController implements Serializable {

    @EJB
    private PlanDeEntrenamientoFacadeLocal ejbFacade;
    private PlanDeEntrenamiento selected;

    @EJB
    private NivelFacadeLocal ejbNiveles;
    private Nivel objNivel;
    @EJB
    private UsuarioFacadeLocal ejbUsuarios;
    private Usuario objUsuario;

    @EJB
    private EjerciciosFacadeLocal ejbEjercicios;
    private Ejercicios objEjercicios;

    @EJB
    private PersonaFacadeLocal ejbpersona;
    private Persona objPersona;

    private List<PlanDeEntrenamiento> items = null;
    private List<Nivel> niveles;
    private List<Usuario> usuarios;
    private List<Usuario> entrenador;
    private List<Ejercicios> ejercicios;
    
    private int ejercicio;
    private int ejercicio2;
    private int ejercicio3;
    private int ejercicio4;
    private int acNivel;


    @PostConstruct
    public void inicial() {

        selected = new PlanDeEntrenamiento();

        selected.setIdNivel(new Nivel());
        selected.setIdEjercicio1(new Ejercicios());
        selected.setIdEjercicio2(new Ejercicios());
        selected.setIdEjercicio3(new Ejercicios());
        selected.setIdEjercicio4(new Ejercicios());

        selected.setIdDeportista(new Usuario());
        selected.getIdDeportista().setCodigo(new Persona());

        selected.setIdEntrenador(new Usuario());
        selected.getIdEntrenador().setCodigo(new Persona());

        items = ejbFacade.findAll();
        niveles = ejbNiveles.findAll();
        ejercicios = ejbEjercicios.findAll();
        usuarios = ejbUsuarios.listarDeportistas();
        entrenador = ejbUsuarios.listarEntrenador();

    }
    
        public int catidadPlanes() {
        return ejbFacade.count();
    }

    public void registrar() {
        try {

            ejbFacade.create(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Plan de entrenamiento!", " Creado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "No se pudo crear"));
        }
        selected = new PlanDeEntrenamiento();
        items = ejbFacade.findAll();

    }

    public void leerPlan(PlanDeEntrenamiento plan) {

        this.selected = plan;
    }

    public void modificar() {
        
        try {
             
            ejbFacade.edit(selected);
            ejbFacade.actualizarEjercicios(ejercicio,selected.getIdPlan());
            ejbFacade.actualizarEjercicios2(ejercicio2,selected.getIdPlan());
            ejbFacade.actualizarEjercicios3(ejercicio3,selected.getIdPlan());
            ejbFacade.actualizarEjercicios4(ejercicio4,selected.getIdPlan());
            ejbFacade.actualizarNivel(acNivel, selected.getIdPlan());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Plan de entrenamiento!", " Editado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "No se pudo editar"));
        }
        items = ejbFacade.findAll();
    }

    public void eliminarPlan(PlanDeEntrenamiento planRem) {

        try {

            ejbFacade.remove(planRem);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Plan de entrenamiento!", " Eliminado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "No se pudo eliminar"));
        }
        items = ejbFacade.findAll();
    }

    public String stringDeportista() {
        return selected.getIdDeportista().getCodigo().getNombre() == null ? " " : (selected.getIdDeportista().getCodigo().getNombre() + " " + selected.getIdDeportista().getCodigo().getApellidos());
    }

    public String stringEntrenador() {
        return selected.getIdEntrenador().getCodigo().getNombre() == null ? " " : (selected.getIdEntrenador().getCodigo().getNombre() + " " + selected.getIdEntrenador().getCodigo().getApellidos());
    }

    public PlanDeEntrenamientoFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PlanDeEntrenamientoFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<PlanDeEntrenamiento> getItems() {
        return items;
    }

    public void setItems(List<PlanDeEntrenamiento> items) {
        this.items = items;
    }

    public PlanDeEntrenamiento getSelected() {
        return selected;
    }

    public void setSelected(PlanDeEntrenamiento selected) {
        this.selected = selected;
    }

    public List<Usuario> getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(List<Usuario> entrenador) {
        this.entrenador = entrenador;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Ejercicios> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicios> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public Nivel getObjNivel() {
        return objNivel;
    }

    public void setObjNivel(Nivel objNivel) {
        this.objNivel = objNivel;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public Ejercicios getObjEjercicios() {
        return objEjercicios;
    }

    public void setObjEjercicios(Ejercicios objEjercicios) {
        this.objEjercicios = objEjercicios;
    }

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getEjercicio2() {
        return ejercicio2;
    }

    public void setEjercicio2(int ejercicio2) {
        this.ejercicio2 = ejercicio2;
    }

    public int getEjercicio3() {
        return ejercicio3;
    }

    public void setEjercicio3(int ejercicio3) {
        this.ejercicio3 = ejercicio3;
    }

    public int getEjercicio4() {
        return ejercicio4;
    }

    public void setEjercicio4(int ejercicio4) {
        this.ejercicio4 = ejercicio4;
    }

    public int getAcNivel() {
        return acNivel;
    }

    public void setAcNivel(int acNivel) {
        this.acNivel = acNivel;
    }


}
