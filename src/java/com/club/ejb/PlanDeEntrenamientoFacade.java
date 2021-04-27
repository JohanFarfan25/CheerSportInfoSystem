/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.PlanDeEntrenamiento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author johan.farfan
 */
@Stateless
public class PlanDeEntrenamientoFacade extends AbstractFacade<PlanDeEntrenamiento> implements PlanDeEntrenamientoFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanDeEntrenamientoFacade() {
        super(PlanDeEntrenamiento.class);
    }

    @Override
    public boolean actualizarEjercicios(int ejercicio, int plan) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE `plan_de_entrenamiento` SET `IdEjercicio_1` = ? WHERE `plan_de_entrenamiento`.`IdPlan` = ? ");
            qt.setParameter(1, ejercicio);
            qt.setParameter(2, plan);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizarEjercicios2(int ejercicio, int plan) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE `plan_de_entrenamiento` SET `IdEjercicio_2` = ? WHERE `plan_de_entrenamiento`.`IdPlan` = ? ");
            qt.setParameter(1, ejercicio);
            qt.setParameter(2, plan);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizarEjercicios3(int ejercicio, int plan) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE `plan_de_entrenamiento` SET `IdEjercicio_3` = ? WHERE `plan_de_entrenamiento`.`IdPlan` = ? ");
            qt.setParameter(1, ejercicio);
            qt.setParameter(2, plan);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizarEjercicios4(int ejercicio, int plan) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE `plan_de_entrenamiento` SET `IdEjercicio_4` = ? WHERE `plan_de_entrenamiento`.`IdPlan` = ? ");
            qt.setParameter(1, ejercicio);
            qt.setParameter(2, plan);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
        @Override
    public boolean actualizarNivel(int acNivel, int plan) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE `plan_de_entrenamiento` SET `IdNivel` =  ? WHERE `plan_de_entrenamiento`.`IdPlan` = ? ");
            qt.setParameter(1, acNivel);
            qt.setParameter(2, plan);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
