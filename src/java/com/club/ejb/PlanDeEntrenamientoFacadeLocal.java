/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.PlanDeEntrenamiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface PlanDeEntrenamientoFacadeLocal {

    void create(PlanDeEntrenamiento planDeEntrenamiento);

    void edit(PlanDeEntrenamiento planDeEntrenamiento);

    void remove(PlanDeEntrenamiento planDeEntrenamiento);

    PlanDeEntrenamiento find(Object id);

    List<PlanDeEntrenamiento> findAll();

    List<PlanDeEntrenamiento> findRange(int[] range);

    int count();

    public boolean actualizarEjercicios(int ejercicio, int plan);
    
    public boolean actualizarEjercicios2(int ejercicio, int plan);
    
    public boolean actualizarEjercicios3(int ejercicio, int plan);
    
    public boolean actualizarEjercicios4(int ejercicio, int plan);

    public boolean actualizarNivel(int acNivel, int plan);
 
    
}
