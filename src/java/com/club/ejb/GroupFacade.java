/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Group;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author johan.farfan
 */
@Stateless
public class GroupFacade extends AbstractFacade<Group> implements GroupFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupFacade() {
        super(Group.class);
    }
    
    @Override
    public boolean actualizarEmail2(String emailAnterior, String emailNuevo2) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE `user_groups` SET `email` = ? WHERE `user_groups`.`email` = ?");
            qt.setParameter(1, emailNuevo2);
            qt.setParameter(2, emailAnterior);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
}
