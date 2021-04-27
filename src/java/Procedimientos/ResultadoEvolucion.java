/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procedimientos;

import com.club.ejb.PlanDeEntrenamientoFacadeLocal;
import com.club.ejb.Resultados1FacadeLocal;
import com.club.model.ItemsEvaluacion;
import com.club.model.PlanDeEntrenamiento;
import com.club.model.Resultados1;
import com.club.model.Semana1;
import com.club.model.Semana2;
import com.club.model.Semana3;
import com.club.model.Semana4;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author johan.farfan
 */
@Named(value = "resultadoEvolucion")
@RequestScoped
public class ResultadoEvolucion {

    private Integer plan;
    private Integer semana1;
    private Integer semana2;
    private Integer semana3;
    private Integer semana4;

  
    @EJB
    private Resultados1FacadeLocal resultadosEJB;
    private List<Resultados1> listaResultados;
    private Resultados1 objResultados;

    @EJB
    private PlanDeEntrenamientoFacadeLocal planEJB;
    private PlanDeEntrenamiento objPlan;
    private List<PlanDeEntrenamiento> listaPlan;

    private int resutado = 0;

    public ResultadoEvolucion() {
    }

    @PostConstruct
    public void init() {

        objResultados = new Resultados1();
        objPlan = new PlanDeEntrenamiento();

        listaResultados = resultadosEJB.findAll();
        objResultados = new Resultados1();

        listaPlan = planEJB.findAll();
        objPlan = new PlanDeEntrenamiento();

    }

    public void resultadosFinal() {

        try {

            PlanDeEntrenamiento pl = new PlanDeEntrenamiento();
            pl.setIdPlan(plan);
            objPlan.setIdPlan(plan);

            Semana1 s1 = new Semana1();
            s1.setCalificacion(semana1);
            s1.setIdPlan(new PlanDeEntrenamiento(semana1));
            s1.setIdItems(new ItemsEvaluacion(semana1));
            objResultados.setIdSemana1(s1);

            Semana2 s12 = new Semana2();
            s12.setCalificacion(semana2);
            s12.setIdPlan(new PlanDeEntrenamiento(semana2));
            s12.setIdItems(new ItemsEvaluacion(semana2));
            objResultados.setIdSemana2(s12);

            Semana3 s13 = new Semana3();
            s13.setCalificacion(semana3);
            s13.setIdPlan(new PlanDeEntrenamiento(semana3));
            s13.setIdItems(new ItemsEvaluacion(semana3));
            objResultados.setIdSemana3(s13);

            Semana4 s14 = new Semana4();
            s14.setCalificacion(semana4);
            s14.setIdPlan(new PlanDeEntrenamiento(semana4));
            s14.setIdItems(new ItemsEvaluacion(semana4));
            objResultados.setIdSemana4(s14);

            resultadosEJB.resultadosFinal(plan, semana1, semana2, semana3, semana4);
            // resultadosEJB.create(objResultados);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Proceso formativo !", "Calificado"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "No se pudo calificaer el proceso formativo"));
        }
        listaResultados.addAll(resultadosEJB.findAll());
        objResultados = new Resultados1();
        objPlan = new PlanDeEntrenamiento();

    }

    public void leer(Resultados1 Seleccion) {

        objResultados = Seleccion;
    }

    public void modificar() {

        try {

            resultadosEJB.edit(objResultados);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Proceso formativo!", " Editado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Error"));
        }
        listaResultados.addAll(resultadosEJB.findAll());
        objResultados = new Resultados1();
        objPlan = new PlanDeEntrenamiento();

    }

    public void eliminar(Resultados1 RemoRes) {

        try {
            
            resultadosEJB.remove(RemoRes);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Proceso formativo!", " Eliminado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Proceso formativo no se pudo eliminar"));
        }
        listaResultados.addAll(resultadosEJB.findAll());
        objResultados = new Resultados1();
        
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Integer getSemana1() {
        return semana1;
    }

    public void setSemana1(Integer semana1) {
        this.semana1 = semana1;
    }

    public Integer getSemana2() {
        return semana2;
    }

    public void setSemana2(Integer semana2) {
        this.semana2 = semana2;
    }

    public Integer getSemana3() {
        return semana3;
    }

    public void setSemana3(Integer semana3) {
        this.semana3 = semana3;
    }

    public Integer getSemana4() {
        return semana4;
    }

    public void setSemana4(Integer semana4) {
        this.semana4 = semana4;
    }

    public Resultados1 getObjResultados() {
        return objResultados;
    }

    public void setObjResultados(Resultados1 objResultados) {
        this.objResultados = objResultados;
    }

    public List<Resultados1> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<Resultados1> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public PlanDeEntrenamiento getObjPlan() {
        return objPlan;
    }

    public void setObjPlan(PlanDeEntrenamiento objPlan) {
        this.objPlan = objPlan;
    }

    public List<PlanDeEntrenamiento> getListaPlan() {
        return listaPlan;
    }

    public void setListaPlan(List<PlanDeEntrenamiento> listaPlan) {
        this.listaPlan = listaPlan;
    }

    public int getResutado() {
        return resutado;
    }

    public void setResutado(int resutado) {
        this.resutado = resutado;
    }

}
