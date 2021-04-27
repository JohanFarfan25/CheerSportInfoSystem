/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.ItemsEvaluacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author johan.farfan
 */
@Stateless
public class ItemsEvaluacionFacade extends AbstractFacade<ItemsEvaluacion> implements ItemsEvaluacionFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemsEvaluacionFacade() {
        super(ItemsEvaluacion.class);
    }
    
}
